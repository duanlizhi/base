/**
 * 
 */
package com.xcj.common.captcha;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.octo.captcha.service.CaptchaServiceException;

/**
 *<b>function:</b>图片生成器
 *@project  base_frame的验证码
 *@package  com.xcj.admin.captcha
 *@filename ImageCaptchaServlet.java
 *@createDate Apr 24, 2014 10:10:54 AM
 *@author yan.yang
 */
@Controller("imageCaptchaController")
@RequestMapping("/admin/login")
public class ImageCaptchaController {
private static final long serialVersionUID = 1L;
/**
 * 生成验证码图片.
 */
@RequestMapping(value="genernateCaptchaImage")

	private void genernateCaptchaImage(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException {
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		ServletOutputStream out = response.getOutputStream();
		try {
			String captchaId = request.getSession(true).getId();
			BufferedImage challenge = (BufferedImage)  CaptchaServiceSingleton.getInstance().getChallengeForID(captchaId, request.getLocale());
			ImageIO.write(challenge, "jpg", out);
			out.flush();
		} catch (CaptchaServiceException e) {
		} finally {
			out.close();
		}
	}
}
