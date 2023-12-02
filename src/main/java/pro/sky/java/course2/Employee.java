package pro.sky.java.course2;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Employee {
    static int count = 1;
    private int id;
    private String surname;
    private String firstName;
    private int department;
    private double salary;

    public Employee(String surname, String firstName, int department, double salary) {

        this.id = count;
        this.surname = StringUtils.capitalize(surname);
        this.firstName = StringUtils.capitalize(firstName);
        this.department = department;
        this.salary = salary;
        count++;
    }

    public int getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstName() {
        return firstName;
    }


    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Employee employee = (Employee) object;
        return department == employee.department && Double.compare(salary, employee.salary) == 0 && Objects.equals(surname, employee.surname) && Objects.equals(firstName, employee.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, firstName, department, salary);
    }

    public String toString() {
        return "ID:" + this.id + " " + this.surname + " " + this.firstName + " " + " Отдел " + this.department + " Зарплата " + this.salary;
    }
}