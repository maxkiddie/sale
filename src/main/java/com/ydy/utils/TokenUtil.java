/**
 * 
 */
package com.ydy.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ydy.exception.MyException;
import com.ydy.model.Admin;
import com.ydy.model.User;
import com.ydy.vo.ienum.EnumSystem;

/**
 * @author xuzhaojie
 *
 *         2018年8月3日 上午10:53:20
 */
public class TokenUtil {

	/** token秘钥，请勿泄露，请勿随便修改 backups:JKKLJOoasdlfj */
	public static final String SECRET = "JKKLJOoasdlfj";
	/** token 过期时间: 10天 */
	public static final int calendarInterval = 10;

	private static final String ADMIN_ID = "AD_ID";
	private static final String ADMIN_NAME = "AD_NAME";
	private static final String USER_ID = "US_ID";
	private static final String USER_NAME = "US_NAME";

	public static final Integer ADMIN = 10000;

	/**
	 * JWT生成Token.<br/>
	 * 
	 * JWT构成: header, payload, signature
	 * 
	 * @param user_id
	 *            登录成功后用户user_id, 参数user_id不可传空
	 */
	public static String createAdminToken(Admin admin) {
		Date iatDate = new Date();
		// expire time
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.DATE, calendarInterval);
		Date expiresDate = nowTime.getTime();

		// header Map
		Map<String, Object> map = new HashMap<>();
		map.put("alg", "HS256");
		map.put("typ", "JWT");

		// build token
		// param backups {iss:Service, aud:APP}
		String token = JWT.create().withHeader(map) // header
				.withClaim(ADMIN_ID, admin.getId()).withClaim(ADMIN_NAME, admin.getUsername()).withIssuedAt(iatDate) // sign
				.withExpiresAt(expiresDate) // expire time
				.sign(Algorithm.HMAC256(SECRET)); // signature
		return token;
	}

	/**
	 * 解密Token
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Claim> verifyToken(String token) {
		if (token == null || "".equals(token)) {
			throw new MyException("token is empty");
		}
		DecodedJWT jwt = null;
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
			jwt = verifier.verify(token);
		} catch (Exception e) {
			throw new MyException(EnumSystem.TOKEN_NOT_MATCH);
		}
		return jwt.getClaims();
	}

	/**
	 * 根据Token获取user_id
	 * 
	 * @param token
	 * @return user_id
	 */
	public static Admin getAdmin(String token) {
		Map<String, Claim> claims = verifyToken(token);
		Admin admin = new Admin();
		admin.setId(claims.get(ADMIN_ID).asInt());
		admin.setUsername(claims.get(ADMIN_NAME).asString());
		return admin;
	}

	public static String createUserToken(User user) {
		Date iatDate = new Date();
		// expire time
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.DATE, calendarInterval);
		Date expiresDate = nowTime.getTime();

		// header Map
		Map<String, Object> map = new HashMap<>();
		map.put("alg", "HS256");
		map.put("typ", "JWT");

		// build token
		// param backups {iss:Service, aud:APP}
		String token = JWT.create().withHeader(map) // header
				.withClaim(USER_ID, user.getId()).withClaim(USER_NAME, user.getUsername()).withIssuedAt(iatDate) // sign
				.withExpiresAt(expiresDate) // expire time
				.sign(Algorithm.HMAC256(SECRET)); // signature
		return token;
	}

	public static User getUser(String token) {
		Map<String, Claim> claims = verifyToken(token);
		User user = new User();
		user.setId(claims.get(USER_ID).asLong());
		user.setUsername(claims.get(USER_NAME).asString());
		return user;
	}

	public static void main(String[] args) {
		User user = new User();
		user.setId(1L);
		user.setUsername("Kiddie");
		String token = createUserToken(user);
		System.out.println(token);
		Admin admin = new Admin();
		admin.setId(1);
		admin.setUsername("Kiddie");
		token = createAdminToken(admin);
		System.out.println(token);
	}
}
