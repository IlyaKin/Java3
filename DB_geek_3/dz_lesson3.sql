use employees;
-- средняя ЗП по отделам;
SELECT 
    AVG(salary) AS AverageSalary,
    departments.dept_name AS DepartmentTitle
FROM
    salaries
        JOIN
    dept_emp ON salaries.emp_no = dept_emp.emp_no
        JOIN
    departments ON departments.dept_no = dept_emp.dept_no
GROUP BY departments.dept_no;
-- максимальная ЗП у сотрудника;
SELECT 
    s.emp_no, salary, CONCAT(first_name, ' ', last_name) AS full_name
FROM
    salaries AS s
        INNER JOIN
    employees AS e ON s.emp_no = e.emp_no
WHERE
    salary IN (SELECT 
            MAX(salary)
        FROM
            salaries);
-- удалить одного сотрудника, у которого максимальная ЗП;
DELETE FROM employees 
WHERE
    employees.emp_no = (SELECT 
        salaries.emp_no
    FROM
        salaries
    
    WHERE
        salaries.salary = (SELECT 
            MAX(salary)
        FROM
            salaries));
-- посчитать количество сотрудников во всех отделах;
SELECT 
    COUNT(*) AS AmountOfEmployees,
    departments.dept_name AS Department
FROM
    dept_emp
        JOIN
    departments ON dept_emp.dept_no = departments.dept_no
GROUP BY dept_emp.dept_no; 
-- Найти количество сотрудников в отделах и посмотреть, 
-- сколько всего денег получает отдел;
SELECT 
    COUNT(DISTINCT dept_emp.emp_no) AS AmountOfEmployees,
    departments.dept_name AS Department,
    SUM(salaries.salary) AS SumSalary
FROM
    dept_emp
        JOIN
    salaries ON dept_emp.emp_no = salaries.emp_no
        JOIN
    departments ON dept_emp.dept_no = departments.dept_no
GROUP BY dept_emp.dept_no; 
 -- Сделать запрос, в котором мы 
 -- выберем все данные о городе – регион, страна;
use geodata;
SELECT 
    _cities.title AS City,
    _countries.title AS Country,
    _regions.title AS Region
FROM
    _cities
        JOIN
    _countries ON _cities.country_id = _countries.id
        LEFT JOIN
    _regions ON _cities.region_id = _regions.id
ORDER BY Country;
-- Выбрать все города из Московской области;
SELECT 
    _cities.title AS MoscowRegionCity
FROM
    _cities
        LEFT JOIN
    _regions ON region_id = _region.id
WHERE
    _regions.title = 'Московская область'
ORDER BY MoscowRegionCity;