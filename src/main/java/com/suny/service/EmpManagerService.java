package com.suny.service;

import com.suny.entity.Employee;

public interface EmpManagerService {
    void autoPunch();

    Employee getByEmployeeAccount(String account);

    Employee getByManagerAccount(String account);

    int validLogin(String account, String password);
}
