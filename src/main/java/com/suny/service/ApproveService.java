package com.suny.service;

import com.suny.utils.Page;

public interface ApproveService {
    int getMaxPage(int pageCount);

    void handleApply(Integer applicationId, String mgrName, boolean result) throws Exception;

    Page getTodoApplyList(int currentPage, int pageCount);
}
