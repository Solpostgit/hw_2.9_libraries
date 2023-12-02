package pro.sky.java.course2.service;

import pro.sky.java.course2.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee addEmployee(String surname, String name, int department, int salary);

    Employee removeEmployee(String surname, String name);

    Employee findEmployee(String surname, String name);

    Collection<Employee> findAll();
}
