INSERT INTO recipe (id, isVegetarian, numberOfServings) VALUES (1L, true, 4);
INSERT INTO ingredient (recipe_id, ingredients) VALUES (1L, 'pasta');
INSERT INTO instruction (recipe_id, instructions) VALUES (1L, 'pod');

INSERT INTO recipe (id, isVegetarian, numberOfServings) VALUES (2L, true, 4);
INSERT INTO ingredient (recipe_id, ingredients) VALUES (2L, 'cucumber');
INSERT INTO ingredient (recipe_id, ingredients) VALUES (2L, 'tomatoes');
INSERT INTO instruction (recipe_id, instructions) VALUES (2L, 'plate');

INSERT INTO recipe (id, isVegetarian, numberOfServings) VALUES (3L, true, 2);
INSERT INTO ingredient (recipe_id, ingredients) VALUES (3L, 'tomatoes');
INSERT INTO instruction (recipe_id, instructions) VALUES (3L, 'plate');

INSERT INTO recipe (id, isVegetarian, numberOfServings) VALUES (4L, true, 5);
INSERT INTO ingredient (recipe_id, ingredients) VALUES (4L, 'meat');
INSERT INTO ingredient (recipe_id, ingredients) VALUES (4L, 'onion');
INSERT INTO instruction (recipe_id, instructions) VALUES (4L, 'pod');

INSERT INTO recipe (id, isVegetarian, numberOfServings) VALUES (5L, false, 4);
INSERT INTO ingredient (recipe_id, ingredients) VALUES (5L, 'barbecue');
INSERT INTO instruction (recipe_id, instructions) VALUES (5L, 'grill');