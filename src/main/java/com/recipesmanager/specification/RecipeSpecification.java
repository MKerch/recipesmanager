package com.recipesmanager.specification;

import com.recipesmanager.model.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class RecipeSpecification<T> {

    public Specification<T> getSearchSpecification(List<SearchCriteria> criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (SearchCriteria sc : criteria) {
                Predicate predicate = null;
                if (sc.getOperation().equalsIgnoreCase("=")) {
                    if (root.get(sc.getKey()).getJavaType() == Set.class) {
                        predicate = criteriaBuilder.isMember(sc.getValue(), root.get(sc.getKey()));
                    } else {
                        predicate = criteriaBuilder.equal(root.get(sc.getKey()), sc.getValue());
                    }
                } else if (sc.getOperation().equalsIgnoreCase("!=")) {
                    if (root.get(sc.getKey()).getJavaType() == Set.class) {
                        predicate = criteriaBuilder.isNotMember(sc.getValue(), root.get(sc.getKey()));
                    } else {
                        predicate = criteriaBuilder.notEqual(root.get(sc.getKey()), sc.getValue());
                    }
                }
                predicates.add(predicate);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

//    public Specification<T> getSearchSpecification(List<SearchCriteria> criteria) {
//        return (root, query, criteriaBuilder) -> {
//
//            List<Predicate> predicates = new ArrayList<>();
//            for (SearchCriteria sc : criteria) {
//                Predicate predicate = null;
//                if (sc.getOperation().equalsIgnoreCase("=")) {
//                    if (root.get(sc.getKey()).getJavaType() == Set.class) {
//                        predicate = criteriaBuilder.isMember(sc.getValue(), root.get(sc.getKey()));
//                    } else {
//                        predicate = criteriaBuilder.equal(root.get(sc.getKey()), sc.getValue());
//                    }
//                } else if (sc.getOperation().equalsIgnoreCase("!=")) {
//                    predicate = criteriaBuilder.notEqual(root.get(sc.getKey()), sc.getValue());
//                }
//                predicates.add(predicate);
//            }
//
//            Predicate and = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        };
//    }


}
