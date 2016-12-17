package com.suny.service.impl;

import com.suny.dao.impl.ApproveDao;
import com.suny.entity.Application;
import com.suny.entity.Attend;
import com.suny.entity.CheckBack;
import com.suny.entity.Manager;
import com.suny.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 孙建荣
 * 2016/12/13 17:28
 */
@Service
public class ApproveService {

    @Autowired
    private ApproveDao approveDao;

    /**
     * 获取对申请修改考勤列表的最大页数
     * @param pageCount     每页显示的记录个数
     * @return    申请列表中的记录数除以每页显示的个数的出来的最大页数
     */
    public int getMaxPage(int pageCount) {
        int MaxPage;
        int totalCount = approveDao.TotalApproveListCount();    //数据库总页数
        if (totalCount % pageCount == 0) {
            MaxPage = totalCount / pageCount;
        } else {
            MaxPage = totalCount / pageCount + 1;
        }
        return MaxPage;
    }

    /**
     * 对管理员的审批进行处理
     *
     * @param applicationId 成员提出的申请列表对应的主键标示id
     * @param mgrName       处理申请的管理员名字
     * @param result        管理员处理的结果，true或者false
     * @throws Exception 要进行处理的异常
     */
    public void check(Integer applicationId, String mgrName, boolean result) throws Exception {
        Application application = (Application) approveDao.get(Application.class, applicationId);   //获取申请修改考勤结果的申请id
        CheckBack checkBack = new CheckBack();     //实例化一个审批结果类，新建一条处理结果
        checkBack.setApp(application);           //审批结果设置对应的申请修改考勤记录
        Manager manager = (Manager) approveDao.findByName(mgrName);      //查询当前修改考勤记录的管理员姓名
        if (manager == null) {                         //判断是否用户恶意冒充管理员进行审批
            throw new Exception("你是不是部长哦");
        }
        checkBack.setManager(manager);    //审批结果设置审批人
        //如果同意申请
        if (result) {
            checkBack.setResult(true);     //审批结果表设置审批结果为1，同意申请
            application.setResult(true);     //审批表里面设置已处理
            approveDao.update(application);    //更新那条申请表里面数据的申请结果
            Attend attend = application.getAttend();   //从申请修改那条数据中获取想要修改的考勤类型
            attend.setAttendType(application.getAttendType());    //设置考勤情况列表里面的考勤数据
            approveDao.update(attend);      //更新考勤状态中某成员的考勤状况
        }
        //没有通过申请
        else {
            application.setResult(true);    //设置申请列表中的是否处理申请结果，true为已经处理，false为默认的未处理
            checkBack.setResult(false);     //设置审批结果表中的是否同意该成员对某条考勤记录的异议申请
            approveDao.update(application);     //更新申请列表中的某条记录
        }
        approveDao.save(checkBack);    //保存最终的审批结果
    }

    /**
     *      获取审批列表的待审批信息
     * @param currentPage   当前页页码
     * @param pageCount     每一个的显示的行数
     * @return      分页后的待审批列表数据
     */
    public Page getApproveList(int currentPage, int pageCount) {
        Page page = new Page();                                             //实例化page
        int totalCount = approveDao.TotalApproveListCount();                            //查询总记录数
        page.setPageCount(pageCount);                                           //设置总页数
        page.setTotalCount(totalCount);                                     //设置总行数
        page.setCurrentPage(currentPage);                                   //设置当前页
        int startCount = page.StartCount(currentPage, pageCount);           //查询当前页开始行数
        List approveList = approveDao.getApproveList(startCount, pageCount);         //获取分页查询结果集
        page.setPageDate(approveList);                                          //开始设置page相关参数
        return page;
    }


}
