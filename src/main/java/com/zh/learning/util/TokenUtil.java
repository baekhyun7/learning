package com.zh.learning.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Map;

/**
 * @author zh
 * @version 1.0
 * @date 2020/11/11 10:07
 */
public class TokenUtil {

    // 过期时间5分钟
    private static final long EXPIRE_TIME = 5 * 60 * 1000;

    private static final String USER_ID = "userId";
    private static final String SECRET = "123";

    /**
     * 生成签名,5min后过期
     *
     * @param userId 用户名
     * @return 加密的token
     */
    public static String sign(String userId) {
//        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        // 附带username信息
        return JWT.create()
                .withClaim(USER_ID, userId)
                .withClaim("as", "a")
                .sign(algorithm);
    }

    public static boolean verify(String token, String userId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(USER_ID, userId)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println(token);
            System.out.println(jwt.getHeader());
            System.out.println(jwt.getPayload());
            System.out.println(jwt.getSignature());
            System.out.println(jwt.getToken());
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
    public static String getUserId(String token){
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        Claim claim = jwt.getClaim(USER_ID);
        return claim.asString();
    }

    public static void main(String[] args) {
        String zhangsan = TokenUtil.sign("zhangsan");
        System.err.println(TokenUtil.getUserId(zhangsan));
    }
}
