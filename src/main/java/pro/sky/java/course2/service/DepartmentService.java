package pro.sky.java.course2.service;

import pro.sky.java.course2.Employee;


import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getEmployeeWithMaxSalary(Integer departmentId);
    Employee getEmployeeWithMinSalary(Integer departmentId);
    List<Employee> getAllEmployeesByDepartment(Integer departmentId);
    Map<Integer, List<Employee>> getAllEmployees();
}
