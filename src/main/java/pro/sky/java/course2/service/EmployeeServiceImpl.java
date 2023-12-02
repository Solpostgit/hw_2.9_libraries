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
    public Employee addEmployee(String surname, String firstName, int department, int salary) {
        validateNames(surname, firstName);

        Employee employee = new Employee(surname, firstName, department, salary);

        if(storage.containsKey(getKey(surname, firstName))) {
            throw new EmployeeAlreadyExistException("Employee " + surname + " " + firstName + " already exists");
        }
        storage.put(getKey(surname, firstName), employee);

        return employee;
    }

    @Override
    public Employee removeEmployee(String surname, String firstName) {
        validateNames(surname, firstName);
        if (!storage.containsKey(getKey(surname, firstName))) {
            throw new EmployeeNotFoundException("Employee " + surname + " " + firstName + " not found");
        }
        return storage.remove(getKey(surname, firstName));
    }

    @Override
    public Employee findEmployee(String surname, String firstName) {
        validateNames(surname, firstName);
        if (!storage.containsKey(getKey(surname, firstName))) {
            throw new EmployeeNotFoundException("Employee " + surname + " " + firstName + " not found");
        }
        return storage.get(getKey(surname, firstName));
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(storage.values());
    }

    private String getKey(String surname, String firstName) {
        return (surname + "_" + firstName).toLowerCase();
    }

    private void validateNames(String... names) {
        for (String name: names){
            if (!StringUtils.isAlpha(name)) {
                throw new InvalidNameException(name + "is invalid");
            }
        }
    }
}
