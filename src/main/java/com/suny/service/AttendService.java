package com.suny.service;

import com.suny.entity.Employee;
import com.suny.utils.Page;

public interface AttendService {
    Page<Employee> getByName(String username, int pageCount, int currentPage);

    Page getAllRecord(int currentPage, int pageCount);

    int getMaxPage(int pageCount);
}
