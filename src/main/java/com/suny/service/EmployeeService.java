package com.suny.service;

import com.suny.entity.Employee;
import com.suny.utils.Page;

import java.util.List;

public interface EmployeeService {
    int punch(String username);

    void checkPassword(String username, String oldPassword, String newPassword);

    void update(Employee employee, Integer id);

    void add(Employee employee);

    List getAllStudentDetails();

    void saveBatch(List<Employee> employeeList);

    Page getAll(int currentPage, int pageCount);

    Page getByName(String username, int pageCount, int currentPage);

    Employee getById(Integer id);

    void del(Integer id);

    int getMaxPage(int pageCount);
}
