package com.swsadWeb.dao;

import com.swsadWeb.entity.User;
import com.swsadWeb.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

//Data Access Object
//DAO类都是进行数据操作的类, 是对于数据库中的数据做增删改查等操作的代码
public interface UserInfoDao {


    /**
     * 查找用户名和密码
     * @param username 登录用户名
     * @return
     */
    UserInfo findByUsername(String username);
    /**
     * 注册用户和密码
     */
    void registerByUsernameAndPassword(@Param("username")String username,
                                       @Param("password")String password);

    /**
     * get UserInfo iconPath
     */
    String findIconByUsername(String username);

    /**
     * 注册用户和密码
     */

    Boolean insertUserTest1();

    Boolean insertUserTest2();


    UserInfo findById(Long id);





    void insertUser(UserInfo userInfo);

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

    Boolean delete(UserInfo userInfo);

    void updateUserInfo(UserInfo userInfo);

    UserInfo getUserInfoById(Long id);




}