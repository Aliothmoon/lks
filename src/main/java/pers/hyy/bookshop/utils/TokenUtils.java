package pers.hyy.bookshop.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import pers.hyy.bookshop.entity.User;

import java.util.Date;

public class TokenUtils {
    //    过期时间
    private static final long stint = 1000 * 60 * 60 * 2;
    private static final String keyName = "username";
    private static final String keyWord = "password";
    private static final String signature = "1145141919810AliothMoon";

    //    Jwt工具类
    private static String EncryptionJwt(String username, String password) {
        Date expiresTime = new Date(System.currentTimeMillis() + stint);
        return JWT.create()
                .withClaim(keyName, username)
                .withClaim(keyWord, password)
                .withExpiresAt(expiresTime)
                .sign(Algorithm.HMAC256(signature));
    }

    private static DecodedJWT DecryptJwt(String token) {

        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(signature)).build();
            return jwtVerifier.verify(token);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getToken(User user) {
        return EncryptionJwt(user.getUsername(), user.getPassword());
    }

    public static Boolean verifyToken(String token) {

        if (token.length() < 12)
            return false;
        try {
            JWT.require(Algorithm.HMAC256(signature)).build().verify(token);
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    public static String getName(String token) {
        DecodedJWT decodedJWT = DecryptJwt(token);
        if (decodedJWT != null) {
            return decodedJWT.getClaim(keyName).asString();
        }
        return null;

    }

    public static User getUser(String token) {
        DecodedJWT decodedJWT = DecryptJwt(token);
        if (decodedJWT != null) {
            String username = decodedJWT.getClaim(keyName).asString();
            String password = decodedJWT.getClaim(keyWord).asString();
            return new User(username, password, 1);
        }
        return null;

    }
}
