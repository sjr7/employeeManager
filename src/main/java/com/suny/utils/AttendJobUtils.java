package com.suny.utils;

import com.suny.service.impl.EmpManagerServiceImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 自动调度任务执行类
 * 孙建荣
 * 2016/12/18 18:56
 */
public class AttendJobUtils implements Job {

    @Autowired
    private EmpManagerServiceImpl empManagerServiceImpl;

    //判断作业是否执行的旗标
    private boolean isRunning =false;

    /**
     * 执行自动添加考勤方法
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Scheduled(cron = "0 0 7,12 ? * MON-FRI")
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
      if(!isRunning){
          System.out.println("开始自动添加缺勤");
          isRunning=true;
          //调用业务逻辑方法
          empManagerServiceImpl.autoPunch();
          isRunning=false;
      }

    }


}



