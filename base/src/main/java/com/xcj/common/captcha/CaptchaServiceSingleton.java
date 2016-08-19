package com.xcj.common.captcha;

import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;


/**
 *<b>function:</b>通过一个单例提供唯一验证码
 *@project  base_frame的验证码
 *@package  com.xcj.admin.captcha
 *@filename CaptchaServiceSingleton.java
 *@createDate Apr 24, 2014 10:07:39 AM
 *@author yan.yang
 */
public class CaptchaServiceSingleton {
	 private static ImageCaptchaService instance = new DefaultManageableImageCaptchaService(
			   new FastHashMapCaptchaStore(), new GMailEngine(), 180,
			   100000 , 75000);
    public static ImageCaptchaService getInstance(){
        return instance;
    }
}