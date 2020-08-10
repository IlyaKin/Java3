use employees;
-- Транзакция  на  удаление  менеджера! При  удалении менеджера он  должен  удалиться из 3  таблиц!! 
begin; 
DELETE from  dept_manager where emp_no = 1110039;
DELETE from  salaries where emp_no = 1110039;
DELETE from  employees where emp_no = 1110039;
rollback

-- Проанализировать несколько запросов с помощью EXPLAIN.
 EXPLAIN SELECT 
    *
FROM
    employees e
        JOIN
    dept_emp d ON e.emp_no = d.emp_no
        JOIN
    salaries s ON e.emp_no = s.emp_no

    WHERE d.to_date like '9999%' and d.dept_no like '%d002%' and s.salary < 50000 and d.from_date like '199%' 