package com.swsadWeb.service.impl;

import com.swsadWeb.entity.User;
import com.swsadWeb.entity.UserInfo;
import com.swsadWeb.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.swsadWeb.dao.UserInfoDao;
import com.swsadWeb.service.UserInfoService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    /*
     * 检验用户登录业务
     *
     */

    @Override
    @Transactional
    public UserInfo checkLogin(String username, String password) {

        UserInfo userInfo = userInfoDao.findByUsername(username);
        if(userInfo != null && userInfo.getPassword().equals(password)){

            return userInfo;
        }
        return null;
    }


    @Transactional
    public String findUser(String username) {

        UserInfo userInfo = userInfoDao.findByUsername(username);
        if(userInfo != null){

            return userInfo.getPassword();
        }
        return "Not Found!";
    }

    @Override
    @Transactional
    public void Regist(UserInfo userInfo) {

        userInfoDao.registerByUsernameAndPassword(userInfo.getUsername(), userInfo.getPassword());

    }

    //添加事务注解
    //1.使用 propagation 指定事务的传播行为, 即当前的事务方法被另外一个事务方法调用时
    //如何使用事务, 默认取值为 REQUIRED, 即使用调用方法的事务
    //REQUIRES_NEW: 事务自己的事务, 调用的事务方法的事务被挂起.
    //2.使用 isolation 指定事务的隔离级别, 最常用的取值为 READ_COMMITTED
    //3.默认情况下 Spring 的声明式事务对所有的运行时异常进行回滚. 也可以通过对应的
    //属性进行设置. 通常情况下去默认值即可.
    //4.使用 readOnly 指定事务是否为只读. 表示这个事务只读取数据但不更新数据,
    //这样可以帮助数据库引擎优化事务. 若真的事一个只读取数据库值的方法, 应设置 readOnly=true
    //5.使用 timeout 指定强制回滚之前事务可以占用的时间.
    @Transactional(propagation=Propagation.REQUIRES_NEW,
            isolation=Isolation.READ_COMMITTED,
            //noRollbackFor = {},
            rollbackFor = Error.class,
            readOnly=false,
            timeout=3)
    public void purchase(String username, String isbn) {}



    @Override
    @Transactional
    public void insertUser(UserInfo userInfo){
        //((UserServiceImpl)AopContext.currentProxy()).update(userInfo);

        System.out.println("to the service - insertUser");
        userInfoDao.insertUser(userInfo);
        System.out.println("id is: "+ userInfo.getId());
    }


    // Spring框架的事务基础架构代码将默认地
    // 只在抛出运行时和unchecked exceptions时才标识事务回滚
    // 也就是说，当抛出个RuntimeException或其子类例的实例时
    // 以及抛出Error的时候才会回滚
    // 而主键冲突异常DuplicateKeyException的最终父类是RuntimeException
    @Override
    @Transactional
    public Boolean insertTest1(){
        userInfoDao.insertUserTest2();
        userInfoDao.insertUserTest1();
        return true;
    }

    @Override
    @Transactional
    public Boolean insertTest2(){
        userInfoDao.insertUserTest2();
        return true;
    }

    /**
     * 删除
     */
    @Override
    public Boolean deleteUser(int id){
        return true;
    }

    /**
     * 更新
     */
    @Override
    public void updateUserInfo(UserInfo userInfo){
        userInfoDao.updateUserInfo(userInfo);
    }

    /**
     * 更新昵称
     */
    @Override
    public void updateNicknameByUsername(UserInfo userInfo){
        userInfoDao.updateNicknameByUsername(userInfo);
    }


    @Override
    public void updatePhoneByUsername(UserInfo userInfo){
        userInfoDao.updatePhoneByUsername(userInfo);
    }

    @Override
    public void updateEmailByUsername(UserInfo userInfo){
        userInfoDao.updateEmailByUsername(userInfo);
    }

    @Override
    public void updateSexByUsername(UserInfo userInfo){
        userInfoDao.updateSexByUsername(userInfo);
    }

    @Override
    public void updateIconPathByUsername(UserInfo userInfo){
        userInfoDao.updateIconPathByUsername(userInfo);
    }

    @Override
    public UserInfo findByUsername(String username){
        return userInfoDao.findByUsername(username);
    }

    @Override
    public UserInfo findById(Long id){
        return userInfoDao.findById(id);
    }

    @Override
    public void updateUniversityByUsername(UserInfo userInfo) {
        userInfoDao.updateUniversityByUsername(userInfo);
    }

    @Override
    public void updateAcademyByUsername(UserInfo userInfo) {
        userInfoDao.updateAcademyByUsername(userInfo);
    }

    @Override
    public void updateGradeByUsername(UserInfo userInfo) {
        userInfoDao.updateGradeByUsername(userInfo);
    }

    @Override
    public void updateMoneyByUsername(UserInfo userInfo) {
        userInfoDao.updateMoneyByUsername(userInfo);
    }

    @Override
    public void updateCreditByUsername(UserInfo userInfo) {
        userInfoDao.updateCreditByUsername(userInfo);
    }


    @Override
    public UserInfo getUserInfoById(Long id){
        return userInfoDao.getUserInfoById(id);
    }


    /**
     * 根據主鍵 id 查詢
     */
    @Override
    public UserInfo loadUser(int id){
        return new UserInfo();
    }

    /**
     * 分页查询
     */
    @Override
    public Map<String,Object> pageList(int offset, int pagesize){
        return null;
    }
}