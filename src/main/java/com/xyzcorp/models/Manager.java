package com.xyzcorp.models;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Manager extends Employee {
    private List<Employee> employeeList;


    public Manager(String firstName, String lastName, int salary, List<Employee> employeeList) {
        super(firstName, lastName, salary);
        this.employeeList = employeeList;
    }

    public List<Employee> getEmployeeList() {
        return Collections.unmodifiableList(employeeList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return Objects.equals(employeeList, manager.employeeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), employeeList);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Manager.class.getSimpleName() + "[", "]")
            .add("employeeList=" + employeeList)
            .toString();
    }
}
