package com.swsadWeb.service;



import com.swsadWeb.entity.UserInfo;

import java.util.Map;

//Service层接口
// @Transactional，事务注解，也应该加在Service层，因为如果我们的事务注解@Transactional加在dao层
// 那么只要与数据库做增删改，就要提交一次事务，如此做事务的特性就发挥不出来
// 尤其是事务的一致性，当出现并发问题是，用户从数据库查到的数据都会有所偏差
public interface UserInfoService {

    //检验用户登录
    UserInfo checkLogin(String username, String password);
    String findUser(String username);
    void Regist(UserInfo userInfo);

    /**
     * 新增
     */
    void insertUser(UserInfo userInfo);

    Boolean insertTest1();

    Boolean insertTest2();

    /**
     * 删除
     */
    Boolean deleteUser(int id);

    /**
     * 更新
     */
    void updateUserInfo(UserInfo userInfo);

    void updateNicknameByUsername(UserInfo userInfo);
    void updatePhoneByUsername(UserInfo userInfo);
    void updateEmailByUsername(UserInfo userInfo);
    void updateSexByUsername(UserInfo userInfo);
    void updateIconPathByUsername(UserInfo userInfo);

    void updateUniversityByUsername(UserInfo userInfo);

    void updateAcademyByUsername(UserInfo userInfo);

    void updateGradeByUsername(UserInfo userInfo);

    void updateMoneyByUsername(UserInfo userInfo);

    void updateCreditByUsername(UserInfo userInfo);


    UserInfo findByUsername(String username);

    UserInfo findById(Long id);

    UserInfo getUserInfoById(Long id);

    /**
     * 根據主鍵 id 查詢
     */
    UserInfo loadUser(int id);

    /**
     * 分页查询
     */
    Map<String,Object> pageList(int offset, int pagesize);
}