package cn.util;

import java.util.Random;

import org.apache.shiro.crypto.hash.Sha1Hash;
import org.junit.Test;

import cn.pojo.User;

public class Handler {
	
	/**
	 * 对用户密码进行加密,需要使用到username参与盐值生成
	 * @param user
	 * @return
	 */
	public static User passwordHandler(User user){
		int random = (int)(Math.random()*10000);
		user.setSalt(random+user.getUsername());//盐值等于随机数+用户名
		String newPassword = new Sha1Hash(user.getPassword(),user.getSalt()).toString();
		user.setPassword(newPassword);
		return user;
	}
	
	/**
	 * 对用户密码进行加密
	 * @param user
	 * @param salt
	 * @return
	 */
	public static User passwordHandler(User user,String salt){
		user.setSalt(salt);
		String newPassword = new Sha1Hash(user.getPassword(),user.getSalt()).toString();
		user.setPassword(newPassword);
		return user;
	}
	
	/**
	 * 对用户密码进行加密
	 * @param user
	 * @param salt
	 * @return
	 */
	public static String passwordHandler(String password,String salt){
		String newPassword = new Sha1Hash(password,salt).toString();
		return newPassword;
	}
	
	@Test
	public void stst(){
		User user = new User();
		user.setPassword("123");
		user.setUsername("admin");
		passwordHandler(user);
	}

}
