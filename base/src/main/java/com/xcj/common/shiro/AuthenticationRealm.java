package com.xcj.common.shiro;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.xcj.admin.entity.admin.Admin;
import com.xcj.admin.entity.admin.AdminRole;
import com.xcj.admin.entity.admin.RoleAuthority;
import com.xcj.admin.service.admin.AdminService;
import com.xcj.common.entity.Principal;
import com.xcj.common.memcached.MemcachedClientFactory;
import com.xcj.common.memcached.MemcachedUtil;
import com.xcj.common.util.Constants;
import com.xcj.common.util.common.StringUtil;
import com.xcj.common.util.md5.MD5Uitl;

/*******************************************************************************
 * <b>function:</b> shiro组件
 * 
 * @project Lgguo
 * @package com.xcj.common.shiro
 * @fileName AuthenticationRealm.java
 * @createDate 2010-03-30 下午05:28:03
 * @author su_jian
 * @email sujiansoft@163.com
 */
public class AuthenticationRealm extends AuthorizingRealm {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private static final String MESSAGE = "message";
	
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	
	public AuthenticationRealm() {
		// 加密
		setCredentialsMatcher(new HashedCredentialsMatcher("SHA-1"));
	}

	/**
	 * 获取授权信息 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 * 
	 * @param principals
	 *            principals
	 * @return 授权信息
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		log.info("AuthenticationRealm doGetAuthorizationInfo 授权");
		
		Principal principal = (Principal) principals.getPrimaryPrincipal();
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		/*Subject currentUser = SecurityUtils.getSubject(); 
		
		try {
			MemcachedClient mc1=MemcachedClientFactory.getInstance();
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		
		// TODO:此处需要从数据库查询或者从而进行权限的控制
		// 杨燕修改author
		try {
			// 根据权限的用户名获取当前用户(优先从缓存中拿)
			MemcachedClient mc1 = MemcachedClientFactory.getInstance();
			String authroit = (String) mc1.get(Constants.XCJ_MEMCACHED_USER_NAME + "_authroit_"
							+ principal.getUsername());
			String role = (String) mc1.get(Constants.XCJ_MEMCACHED_USER_NAME
							+ "_role_" + principal.getUsername());
			if (!StringUtil.isEmptyYl(role)) {
				Set<String> roleSet = new HashSet<String>();
				CollectionUtils.addAll(roleSet, role.split(","));
				info.addRoles(roleSet);

				if (!StringUtil.isEmptyYl(authroit)) {
					Set<String> authroitSet = new HashSet<String>();
					CollectionUtils.addAll(authroitSet, authroit.split(","));
					info.addStringPermissions(authroitSet);
				}
			} else {
				Admin admin = adminService.getCurrentByPageParam(principal.getUsername());
				List<AdminRole> adminRoles = admin.getAdminRoles();
				if (adminRoles != null) {
					for (AdminRole adminRole : adminRoles) {
						// 添加角色信息
						info.addRole(adminRole.getRole().getName());
						List<RoleAuthority> roleAuthorities = adminRole.getRole().getRoleAuthorities();
						for (RoleAuthority roleAuthority : roleAuthorities) {
							// 添加权限信息
							info.addStringPermission(roleAuthority.getAuthorities());
						}
					}
				} else {
					info.addStringPermission("admin:common");
				}
				// 放入缓存
				String authoritys = info.getStringPermissions().toString();
				authoritys = authoritys.substring(1, authoritys.length() - 1);

				String roles = info.getRoles().toString();
				roles = roles.substring(1, roles.length() - 1);

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("_authroit_" + principal.getUsername(), authoritys);
				map.put("_role_" + principal.getUsername(), roles);
				MemcachedUtil.putMemcacheIntanceDefaultKeyByTime(map, Constants.XCJ_MEMCACHED_USER_NAME,
						Constants.XCJ_MEMCACHED_TOKEN_TIMING);
			}
			return info;
		} catch (Exception e) {
			log.info("AuthenticationRealm doGetAuthorizationInfo exception : " + e.getMessage());
			return null;
		}
		/*
		 * Principal principal = (Principal)
		 * principals.fromRealm(getName()).iterator().next(); if (principal !=
		 * null) { List<String> authorities =
		 * adminService.findAuthorities(principal.getId()); if (authorities !=
		 * null) { SimpleAuthorizationInfo authorizationInfo = new
		 * SimpleAuthorizationInfo();
		 * authorizationInfo.addStringPermissions(authorities); return
		 * authorizationInfo; }return null; }
		 */

	}

	/**
	 * 认证回调函数,登录时调用.
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			org.apache.shiro.authc.AuthenticationToken authcToken)
			throws AuthenticationException {
		
		log.info("AuthenticationRealm doGetAuthenticationInfo 认证");

		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		token.setRememberMe(true);
		String username = token.getUsername();
		String password = new String(token.getPassword());
		if (token.getPassword() != null) {
			password = new String(token.getPassword());
		}
		if (username == null || "".equals(username)) {
			this.setAttribute(MESSAGE, "用户名不能为空");
			log.info("用户名为空");
			return null;
		}
		if (password == null || "".equals(password)) {
			this.setAttribute(MESSAGE, "密码不能为空");
			log.info("密码为空");
			return null;
		}

		Admin admin = null;
		if (token.getUsername() != null && !"".equals(token.getUsername())) {
			try {
				admin = adminService.getCurrentByPageParam(username);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!DigestUtils.md5Hex(password).equals(admin.getPassword())) {
			throw new IncorrectCredentialsException();
		}
		try {
			return new SimpleAuthenticationInfo(
					new com.xcj.common.entity.Principal(Long.valueOf(admin
							.getId()), username), MD5Uitl.MD5Encode(password),
					getName());
		} catch (Exception e) {
			log.info("用户名或密码错误");
			setAttribute(MESSAGE, "用户名或密码错误");
			return null;
		}

		/*
		 * AuthenticationToken authenticationToken = (AuthenticationToken)
		 * token; String username = authenticationToken.getUsername();s String
		 * password = new String(authenticationToken.getPassword()); String
		 * captchaId = authenticationToken.getCaptchaId(); String captcha =
		 * authenticationToken.getCaptcha(); String ip =
		 * authenticationToken.getHost(); if
		 * (!captchaService.isValid(CaptchaType.adminLogin, captchaId, captcha))
		 * { throw new UnsupportedTokenException(); } if (username != null &&
		 * password != null) { Admin admin =
		 * adminService.findByUsername(username); if (admin == null) { throw new
		 * UnknownAccountException(); } if (!admin.getIsEnabled()) { throw new
		 * DisabledAccountException(); } Setting setting = SettingUtils.get();
		 * if (admin.getIsLocked()) { if
		 * (ArrayUtils.contains(setting.getAccountLockTypes(),
		 * AccountLockType.admin)) { int loginFailureLockTime =
		 * setting.getAccountLockTime(); if (loginFailureLockTime == 0) { throw
		 * new LockedAccountException(); } Date lockedDate =
		 * admin.getLockedDate(); Date unlockDate =
		 * DateUtils.addMinutes(lockedDate, loginFailureLockTime); if (new
		 * Date().after(unlockDate)) { admin.setLoginFailureCount(0);
		 * admin.setIsLocked(false); admin.setLockedDate(null);
		 * adminService.update(admin); } else { throw new
		 * LockedAccountException(); } } else { admin.setLoginFailureCount(0);
		 * admin.setIsLocked(false); admin.setLockedDate(null);
		 * adminService.update(admin); } } if
		 * (!DigestUtils.md5Hex(password).equals(admin.getPassword())) { int
		 * loginFailureCount = admin.getLoginFailureCount() + 1; if
		 * (loginFailureCount >= setting.getAccountLockCount()) {
		 * admin.setIsLocked(true); admin.setLockedDate(new Date()); }
		 * admin.setLoginFailureCount(loginFailureCount);
		 * adminService.update(admin); throw new
		 * IncorrectCredentialsException(); } admin.setLoginIp(ip);
		 * admin.setLoginDate(new Date()); admin.setLoginFailureCount(0);
		 * adminService.update(admin); return new SimpleAuthenticationInfo(new
		 * Principal(admin.getId(), username), password, getName()); }return
		 * null; throw new UnknownAccountException();
		 */

	}

	private void setAttribute(String key, String value) {
		SecurityUtils.getSubject().getSession().setAttribute(key, value);
	}

}
