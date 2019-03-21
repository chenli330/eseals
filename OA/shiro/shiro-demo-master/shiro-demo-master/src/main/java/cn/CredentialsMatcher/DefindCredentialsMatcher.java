package cn.CredentialsMatcher;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import cn.pojo.User;
import cn.service.UserService;
import cn.util.Handler;

public class DefindCredentialsMatcher extends SimpleCredentialsMatcher {
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = userService.findByUsername((String)token.getUsername());
		if(user==null){
			return false;
		}
		Object tokenCredentials = Handler.passwordHandler(String.valueOf(token.getPassword()),user.getSalt());
		Object accountCredentials = getCredentials(info);
		// 将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false
		return equals(tokenCredentials, accountCredentials);
	}

}
