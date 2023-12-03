package pro.sky.java.course2.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.Employee;
import pro.sky.java.course2.exception.EmployeeAlreadyExistException;
import pro.sky.java.course2.exception.EmployeeNotFoundException;
import pro.sky.java.course2.exception.InvalidNameException;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final Map<String, Employee> storage = new HashMap<>();

    @Override
    public Employee addEmployee(String firstName, String lastName, int salary, int department) {
        validateNames(firstName, lastName);

        Employee employee = new Employee(firstName, lastName, salary, department);

        if(storage.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeAlreadyExistException("Employee " + firstName + " " + lastName + " already exists");
        }
        storage.put(getKey(firstName, lastName), employee);

        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        validateNames(firstName, lastName);
        if (!storage.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeNotFoundException("Employee " + firstName + " " + lastName + " not found");
        }
        return storage.remove(getKey(firstName, lastName));
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        validateNames(firstName, lastName);
        if (!storage.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeNotFoundException("Employee " + firstName + " " + lastName + " not found");
        }
        return storage.get(getKey(firstName, lastName));
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(storage.values()); //было return Collections.unmodifiableCollection(storage.values());
    }

    private String getKey(String firstName, String lastName) {
        return (firstName + "_" + lastName).toLowerCase();
    }

    private void validateNames(String... names) {
        for (String name: names){
            if (!StringUtils.isAlpha(name)) {
                throw new InvalidNameException(name + "is invalid");
            }
        }
    }
}
