-- Создать VIEW на основе запросов, которые вы сделали в ДЗ к уроку 3
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `number of employees` AS
    SELECT 
        COUNT(0) AS `AmountOfEmployees`,
        `departments`.`dept_name` AS `Department`
    FROM
        (`dept_emp`
        JOIN `departments` ON ((`dept_emp`.`dept_no` = `departments`.`dept_no`)))
    GROUP BY `dept_emp`.`dept_no`

-- Создать функцию, которая найдет менеджера по имени и фамилии.
USE `eml_full`;
DROP function IF EXISTS `fio_maneger`;
DELIMITER $$
USE `eml_full`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `fio_maneger`(`name` varchar(55), `surname` varchar(55)) 
RETURNS varchar(55) 
CHARSET utf8mb4
READS SQL DATA
BEGIN
RETURN
(select * from dept_manager d join employees e on d.emp_no = e.emp_no 
where e.first_name = `name` and e.last_name = `surname`);
END$$

DELIMITER ;

-- Создать триггер, который при добавлении нового сотрудника будет выплачивать ему вступительный бонус, занося запись об этом в таблицу salary.
DROP TRIGGER IF EXISTS `eml_full`.`employees_AFTER_INSERT`;
DELIMITER $$
USE `eml_full`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `employees_AFTER_INSERT` AFTER INSERT ON `employees` FOR EACH ROW BEGIN
INSERT INTO `eml_full`.`salaries`(`emp_no`,`salary`,`from_date`,`to_date`)
VALUES (New.emp_no, '15000','1998-09-18','2008-09-18');
END$$
DELIMITER ;