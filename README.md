# 简单的考勤管理系统，实现了考勤管理，管理员审批，成员信息管理功能

------
项目结构

├─src
│  │  rebel.xml
│  │
│  ├─com
│  │  └─suny
│  │      ├─controller
│  │      │  └─impl
│  │      │          ApproveController.java
│  │      │          AttendController.java
│  │      │          CodeController.java
│  │      │          EmployeeController.java
│  │      │          FileSourceController.java
│  │      │          LoginController.java
│  │      │          ManagerController.java
│  │      │          PasswordController.java
│  │      │
│  │      ├─dao
│  │      │  └─impl
│  │      │          ApproveDao.java
│  │      │          AttendDao.java
│  │      │          EmployeeDao.java
│  │      │          FileSourceDao.java
│  │      │          ManagerDao.java
│  │      │
│  │      ├─entity
│  │      │      Application.java
│  │      │      Attend.java
│  │      │      AttendType.java
│  │      │      CheckBack.java
│  │      │      Employee.java
│  │      │      Manager.java
│  │      │
│  │      ├─exception
│  │      │      PasswordNotMatchException.java
│  │      │
│  │      ├─filter
│  │      │      LoginCheckFilter.java
│  │      │
│  │      ├─interceptor
│  │      │      UserInterceptor.java
│  │      │
│  │      ├─service
│  │      │  └─impl
│  │      │          ApproveService.java
│  │      │          AttendService.java
│  │      │          EmployeeService.java
│  │      │          EmpManagerService.java
│  │      │          FileSourceService.java
│  │      │
│  │      └─utils
│  │              Page.java
│  │              RandomID.java
│  │
│  └─config
│          Spring.xml
│          SpringMVC-servlet.xml
│
└─web
    │  error.jsp
    │  index.jsp
    │
    ├─common
    │      common.jsp
    │      footer.jsp
    │      menu.jsp
    │      top.jsp
    │      welcome.jsp
    │
    ├─css
    │      bootstrap.min.css
    │      error.css
    │
    ├─fonts
    │      glyphicons-halflings-regular.ttf
    │      glyphicons-halflings-regular.woff
    │
    ├─images
    │  │  3.jpg
    │  │  attend.png
    │  │  banner - Copy.jpg
    │  │  banner.jpg
    │  │  manage.png
    │  │  user.png
    │  │  view.png
    │  │
    │  └─error
    │          404.png
    │          404_msg.png
    │          404_to_index.png
    │          error_bg.jpg
    │          error_cloud.png
    │
    ├─script
    │      bootstrap.min.js
    │      jquery-3.1.1.min.js
    │
    ├─upload
    └─WEB-INF
        │  success.jsp
        │  web.xml
        │
        ├─classes
        │  │  rebel.xml
        │  │
        │  ├─com
        │  │  └─suny
        │  │      ├─controller
        │  │      │  └─impl
        │  │      │          ApproveController.class
        │  │      │          AttendController.class
        │  │      │          CodeController.class
        │  │      │          EmployeeController.class
        │  │      │          FileSourceController.class
        │  │      │          LoginController.class
        │  │      │          ManagerController.class
        │  │      │          PasswordController.class
        │  │      │
        │  │      ├─dao
        │  │      │  └─impl
        │  │      │          ApproveDao.class
        │  │      │          AttendDao.class
        │  │      │          EmployeeDao.class
        │  │      │          FileSourceDao.class
        │  │      │          ManagerDao.class
        │  │      │
        │  │      ├─entity
        │  │      │      Application.class
        │  │      │      Attend.class
        │  │      │      AttendType.class
        │  │      │      CheckBack.class
        │  │      │      Employee.class
        │  │      │      Manager.class
        │  │      │
        │  │      ├─exception
        │  │      │      LoginNameNotFoundException.class
        │  │      │      PasswordNotMatchException.class
        │  │      │
        │  │      ├─filter
        │  │      │      LoginCheckFilter.class
        │  │      │
        │  │      ├─interceptor
        │  │      │      UserInterceptor.class
        │  │      │
        │  │      ├─service
        │  │      │  └─impl
        │  │      │          ApproveService.class
        │  │      │          AttendService.class
        │  │      │          EmployeeService.class
        │  │      │          EmpManagerService.class
        │  │      │          FileSourceService.class
        │  │      │
        │  │      ├─test
        │  │      │      student.class
        │  │      │      Test.class
        │  │      │
        │  │      └─utils
        │  │              DatabaseUtils.class
        │  │              Page.class
        │  │              RandomID.class
        │  │
        │  └─config
        │          Spring.xml
        │          SpringMVC-servlet.xml
        │
        ├─lib
        │      antlr-2.7.7.jar
        │      classmate-1.0.0.jar
        │      com.springsource.net.sf.cglib-2.2.0.jar
        │      com.springsource.org.aopalliance-1.0.0.jar
        │      com.springsource.org.aspectj.weaver-1.6.8.RELEASE.jar
        │      commons-collections4-4.0.jar
        │      commons-fileupload-1.2.2.jar
        │      commons-io-2.0.1.jar
        │      commons-logging-1.1.3.jar
        │      dom4j-1.6.1.jar
        │      hibernate-commons-annotations-4.0.5.Final.jar
        │      hibernate-core-4.3.11.Final.jar
        │      hibernate-entitymanager-4.3.11.Final.jar
        │      hibernate-jpa-2.1-api-1.0.0.Final.jar
        │      hibernate-validator-5.1.3.Final.jar
        │      hibernate-validator-annotation-processor-5.1.3.Final.jar
        │      jandex-1.1.0.Final.jar
        │      javassist-3.18.1-GA.jar
        │      jboss-logging-3.1.3.GA.jar
        │      jboss-logging-annotations-1.2.0.Beta1.jar
        │      jboss-transaction-api_1.2_spec-1.0.0.Final.jar
        │      jstl-1.2.jar
        │      mysql-connector-java-5.1.7-bin.jar
        │      poi-3.15.jar
        │      poi-examples-3.15.jar
        │      poi-excelant-3.15.jar
        │      poi-ooxml-3.15.jar
        │      poi-ooxml-schemas-3.15.jar
        │      poi-scratchpad-3.15.jar
        │      spring-aop-4.0.0.RELEASE.jar
        │      spring-aspects-4.0.0.RELEASE.jar
        │      spring-beans-4.0.0.RELEASE.jar
        │      spring-context-4.0.0.RELEASE.jar
        │      spring-core-4.0.0.RELEASE.jar
        │      spring-expression-4.0.0.RELEASE.jar
        │      spring-jdbc-4.0.0.RELEASE.jar
        │      spring-orm-4.0.0.RELEASE.jar
        │      spring-tx-4.0.0.RELEASE.jar
        │      spring-web-4.0.0.RELEASE.jar
        │      spring-webmvc-4.0.0.RELEASE.jar
        │      validation-api-1.1.0.Final.jar
        │      xmlbeans-2.6.0.jar
        │
        └─pages
            │  Login.jsp
            │
            ├─adminView
            │      addStudent.jsp
            │      adminWeb.jsp
            │      approveList.jsp
            │      attendanceRecord.jsp
            │      changePassword.jsp
            │      manageStudent.jsp
            │      studentsList.jsp
            │      upload.jsp
            │      UploadExcel.jsp
            │      viewModifyOperation.jsp
            │      viewStudent.jsp
            │
            ├─home
            └─userView
                    changePassword.jsp
                    studentDetail.jsp
                    user_index.jsp

