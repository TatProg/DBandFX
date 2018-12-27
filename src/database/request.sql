6. Для каждой пиццерии вывести суммарное количество работников, количество позиций, средний размер пиццы.

SELECT pizzaTypes.restaurant,
--COUNT(pizzaTypes.name),
quantityTable.quantity,
AVG(pizzaTypes.weight),
SUM(officesAddress.members)/3
FROM pizzaTypes, officesAddress, quantityTable
WHERE pizzaTypes.restaurant = officesAddress.restaurant
AND pizzaTypes.restaurant = quantityTable.restaurant
AND quantityTable.restaurant = officesAddress.restaurant
GROUP BY pizzaTypes.restaurant;