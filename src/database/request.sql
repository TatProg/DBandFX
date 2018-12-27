--6. Для каждой пиццерии вывести суммарное количество работников, количество позиций, средний размер пиццы.

-- SELECT pizzaTypes.restaurant,
--       --COUNT(pizzaTypes.name),
--       quantityTable.quantity,
--       AVG(pizzaTypes.weight),
--       SUM(officesAddress.members)/3
-- FROM pizzaTypes, officesAddress, quantityTable
-- WHERE pizzaTypes.restaurant = officesAddress.restaurant
--   AND pizzaTypes.restaurant = quantityTable.restaurant
--   AND quantityTable.restaurant = officesAddress.restaurant
-- GROUP BY pizzaTypes.restaurant;


CREATE TABLE pizzaWeightTRASH (
  pizzaName TEXT NOT NULL,
  weight    INT  NOT NULL
);
INSERT INTO pizzaWeightTRASH (pizzaName, weight)
VALUES ('wow', 12),
       ('puf', 23),
       ('wow', 12),
       ('wow', 12),
       ('wow', 12),
       ('zap', 34);
SELECT *
FROM pizzaWeightTRASH
GROUP BY pizzaName;
DROP TABLE pizzaWeightTRASH;