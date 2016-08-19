package com.xcj.common.captcha;

import java.awt.Color;

import com.octo.captcha.component.image.backgroundgenerator.FunkyBackgroundGenerator;
import com.octo.captcha.component.image.color.SingleColorGenerator;
import com.octo.captcha.component.image.fontgenerator.TwistedRandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.BaffleTextDecorator;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.ImageCaptchaFactory;
import com.octo.captcha.image.gimpy.GimpyFactory;

/**
 * 随即验证码生成服务. <p>
 *<b>function:</b>* JCaptcha验证码图片生成引擎,
 *@project  base_frame的验证码
 *@package  com.xcj.admin.captcha
 *@filename GMailEngine.java
 *@createDate Apr 24, 2014 10:09:28 AM
 *@author yan.yang
 */

public class GMailEngine extends ListImageCaptchaEngine {
	  /**
	     * 初始化图片生成工厂
	     */
	@Override
	protected void buildInitialFactories() {
		TextPaster randomPaster = new DecoratedRandomTextPaster(new Integer(4),
				new Integer(4), new SingleColorGenerator(Color.BLACK),
				new TextDecorator[] { new BaffleTextDecorator(new Integer(0),
						Color.WHITE) });
		// 创建图片生成工厂
		ImageCaptchaFactory factory = new GimpyFactory(new RandomWordGenerator(
				"abcdefghijklmnopqrstuvwxyz0123456789"),

		new ComposedWordToImage(new TwistedRandomFontGenerator(new Integer(45),
				new Integer(50)), new FunkyBackgroundGenerator(
				new Integer(280), new Integer(100)), randomPaster));

		ImageCaptchaFactory characterFactory[] = { factory };
		this.addFactories(characterFactory);

	}
}
