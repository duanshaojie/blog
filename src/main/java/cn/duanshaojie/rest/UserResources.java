package cn.duanshaojie.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.duanshaojie.entity.JPartyEntity;
import cn.duanshaojie.entity.JUserEntity;
import cn.duanshaojie.enumerate.AcountStatusEnum;
import cn.duanshaojie.service.EmailService;
import cn.duanshaojie.service.JPartyService;
import cn.duanshaojie.service.JUserService;
import cn.duanshaojie.utils.CheckEmailUtil;
import cn.duanshaojie.utils.MD5Util;
import cn.duanshaojie.utils.ResponseBuildHelper;
import cn.duanshaojie.vo.UserVO;

/**
 * <b>类名：</b>UserResources.java<br>
 * <p><b>标题：</b>用户接口</p>
 * <p><b>描述：</b>用户接口</p>
 * @author <font color='blue'>edison_dsj@163.com</font>
 * @date  2017-08-14 下午
 * 
 * 桃之夭夭,灼灼其华
 */

@RestController
@RequestMapping("user")
public class UserResources {
	
	@Autowired
	private JUserService userService;
	@Autowired
	private JPartyService partyService;
	@Autowired
	private EmailService email;
	
	private final static Logger logger = LoggerFactory.getLogger(UserResources.class);
	
	@RequestMapping("register")
	@ResponseBody
	public Response helloSpringBoot(@Context HttpServletRequest request,@RequestParam String username,@RequestParam String password){
		logger.info("username is : {},password is : {}",username,password);
		if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
			return ResponseBuildHelper.BuildErrorResponse(Response.Status.BAD_REQUEST, "用户名或密码不能为空");
		}
		Boolean checkEmail = CheckEmailUtil.email(username);
		if(checkEmail){
			JUserEntity userEntity = userService.findByUsername(username);
			if(userEntity==null){
				JPartyEntity party = new JPartyEntity();
				party.setNickname("用戶"+System.currentTimeMillis());
				party = partyService.saveParty(party);
				JUserEntity user = new JUserEntity();
				user.setUsername(username);
				user.setPassword(MD5Util.MD5Encode(password, ""));
				user.setStatus(AcountStatusEnum.NOTACTIVE.getIndex());
				user.setPartyId(party.getPartyId());
				JUserEntity resultUser = userService.save(user);
				if(resultUser!=null){
					try {
						email.sendRegisterCode(username);
					} catch (Exception e) {
						logger.error("register success but email send fail");
						return ResponseBuildHelper.BuildErrorResponse(Response.Status.OK, "注册成功，激活邮件发送失败，请联系管理员");
					}
					return ResponseBuildHelper.buildSuccessResponse(Response.Status.OK, "注册成功,请到注册邮箱操作激活账户");
				}
			}
			return ResponseBuildHelper.BuildErrorResponse(Response.Status.BAD_REQUEST, "用户名已存在");
		}
		return ResponseBuildHelper.BuildErrorResponse(Response.Status.BAD_REQUEST, "邮箱格式不正确");

	}
	
	@RequestMapping("verify")
	@ResponseBody
	public String verifyEmailCode(@Context HttpServletRequest request,@RequestParam String address,@RequestParam String code){
		if(StringUtils.isEmpty(address)||StringUtils.isEmpty(code)){
			return "<html lang='en'><head><meta charset='UTF-8'><title>链接错误了</title></head><body><div><p>:(链接搞错了，请联系管理员</p> </div></body></html>";
		}
		boolean result = email.verifyCode(address, code);
		if(result){
			JUserEntity user = userService.findByUsername(address);
			user.setStatus(AcountStatusEnum.NORMAL.getIndex());
			userService.save(user);
			return "<html lang='en'><head><meta charset='UTF-8'><title>激活成功</title></head><body><div><p>:)老板，可以了，速速去拥抱社区吧---><a href='#'>点击去登录</a></p> </div></body></html>";
		}
		return "<html lang='en'><head><meta charset='UTF-8'><title>验证失败</title></head><body><div><p>:(验证链接已过期，请联系管理员</p> </div></body></html>";
	}
	
	@RequestMapping("login")
	@ResponseBody
	public Response login(@Context HttpServletRequest request,@RequestParam String username,@RequestParam String password){
		logger.info("login begin");
		if(StringUtils.isEmpty(password)||StringUtils.isEmpty(username)){
			return ResponseBuildHelper.BuildErrorResponse(Response.Status.BAD_REQUEST, "用户名和密码不能为空");
		}
		JUserEntity userUsernameEntity = userService.findByUsername(username);
		if(userUsernameEntity!=null){
			password = MD5Util.MD5Encode(password, "");
			if(password.equals(userUsernameEntity.getPassword())){
				UserVO vo = userService.getUserInfo(userUsernameEntity);
				return ResponseBuildHelper.buildSuccessResponse(Response.Status.OK, vo);
			}
			return ResponseBuildHelper.BuildErrorResponse(Response.Status.BAD_REQUEST, "密码输入错误,请重新输入");
		}
		return ResponseBuildHelper.BuildErrorResponse(Response.Status.BAD_REQUEST, "用户名不存在");
	}
}
