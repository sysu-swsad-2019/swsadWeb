package com.swsadWeb.service.impl;

import com.swsadWeb.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

/**
 * @auther wangjing
 * @date 2019/6/13
 */
@Component
public class PasswordHelper {

    //实例化RandomNumberGenerator对象，用于生成一个随机数
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    //散列算法名称
    private String algorithName = "MD5";
    //散列迭代次数
    private int hashInterations = 2;

    public RandomNumberGenerator getRandomNumberGenerator() {
        return randomNumberGenerator;
    }

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public String getAlgorithName() {
        return algorithName;
    }

    public void setAlgorithName(String algorithName) {
        this.algorithName = algorithName;
    }

    public int getHashInterations() {
        return hashInterations;
    }

    public void setHashInterations(int hashInterations) {
        this.hashInterations = hashInterations;
    }

    //加密算法
    public void encryptPassword(User user){
        if (user.getPassword() != null){
            //对user对象设置盐：salt；这个盐值是randomNumberGenerator生成的随机数，所以盐值并不需要我们指定
            //spring-shiro.xml中,storedCredentialsHexEncoded=true 则需要.toHex()
            //spring-shiro.xml中,storedCredentialsHexEncoded=false 则需要.toBase64()
            user.setSalt(randomNumberGenerator.nextBytes().toHex());

            //调用SimpleHash指定散列算法参数：1、算法名称；2、用户输入的密码；3、盐值（随机生成的）；4、迭代次数
            //user实体的方法getCredentialsSalt获取的salt'=username+salt，而salt为一串随机序列
            String newPassword = new SimpleHash(
                    algorithName,
                    user.getPassword(),
                    ByteSource.Util.bytes(user.getCredentialsSalt()),
                    hashInterations).toHex();

            user.setPassword(newPassword);
        }
    }
}

