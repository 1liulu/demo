package com.example.demo1.util;


import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo1.bean.Account;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by bh on 2018/12/17.
 *
 * @author liulu
 */
public class Token {
    /**
     * 发行者
     */
    public static final String ISSUSER = "ssta";
    /**
     * 密令
     */
    public static final String SECRET = "smartcityxfzhzxgl";

    /**
     * token有效期时长：8小时
     */
    public static final long OVERTIME = 1000 * 60 * 60 * 8;

    /**
     * * 创建token
     *
     * @param subject 加密内容：用户信息、权限等
     * @return
     */
    public static String createToken(String subject) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);

            String token = JWT.create()
                    .withIssuer(ISSUSER)
                    .withSubject(subject)
                    .withExpiresAt(getTimeout())
                    .sign(algorithm);
            return token;
        } catch (UnsupportedEncodingException exception) {
            //UTF-8 encoding not supported
            return Constant.GENERATE_TOKEN_ERROR;
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
            return Constant.GENERATE_TOKEN_ERROR;
        }
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    public static String verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUSER)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            String subject = jwt.getSubject();
            return subject;
        } catch (UnsupportedEncodingException exception) {
            //UTF-8 encoding not supported
            return Constant.GENERATE_TOKEN_ERROR;
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
            return Constant.GENERATE_TOKEN_ERROR;
        }
    }


    public static Date getTimeout() {
        long timeStamp = System.currentTimeMillis() + OVERTIME;
        Date timeout = new Date(Long.parseLong(String.valueOf(timeStamp)));
        return timeout;
    }

    public static Account getDataByTokenSubject(String subject) {
      return   JSON.toJavaObject(JSON.parseObject(subject).getJSONObject("account"),Account.class)      ;
    }
}
