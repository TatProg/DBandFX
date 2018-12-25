--Вот два триггера, написал строго по инструкции с умных сайтов. Но оно не работает

CREATE TRIGGER checkPizzaWeight
  AFTER INSERT
  ON pizzaType
BEGIN
  DELETE
  FROM pizzaTypes
  WHERE weight < 0;
END;

CREATE TRIGGER checkRestaurant
  AFTER INSERT
  ON pizzaType
BEGIN
  DELETE
  FROM pizzaTypes
  WHERE restaurant <> 'Папа Джонс'
     OR restaurant <> 'Додо пицца'
     OR restaurant <> 'Домино пицца'
     OR restaurant <> 'А Рома';
END;