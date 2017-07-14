package com.todd.framework.tools;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.todd.framework.po.User;
/**
 * 加密密码工具栏
 * @author xingzhaojun
 *
 */
public class PasswordHelper {
	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private static String algorithmName = "md5";
	private final static int hashIterations = 5;

	public  static void encryptPassword(User user) {
		String hex = randomNumberGenerator.nextBytes().toHex();
		user.setSalt(hex);
		String newPassword = new SimpleHash(algorithmName, user.getPassword(), ByteSource.Util.bytes(user.getSalt()),
				hashIterations).toHex();
		user.setPassword(newPassword);
	}
	
	public static void DecryptPassword(String salt,User user){
		String newPassword = new SimpleHash(algorithmName, user.getPassword(), ByteSource.Util.bytes(salt),
				hashIterations).toHex();
		user.setPassword(newPassword);
	}
}
