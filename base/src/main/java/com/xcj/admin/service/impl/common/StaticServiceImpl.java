/**
 * 
 *//*
package com.xcj.admin.service.impl.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.article.ArticleDao;
import com.xcj.admin.entity.article.Article;
import com.xcj.admin.service.common.StaticService;
import com.xcj.common.qrcode.TwoDimensionCode;
import com.xcj.common.util.DateUtil;
import com.xcj.common.util.FreeMarkerConstant;
import com.xcj.common.util.SettingUtil;

*//** 
 * <b>function:</b> 
 * @project base 
 * @package com.xcj.admin.service.impl.common  
 * @fileName com.xcj.*
 * @createDate Oct 3, 2014 12:38:11 PM 
 * @author su_jian 
 *//*
@Service("staticServiceImpl")
public class StaticServiceImpl extends BaseServiceImpl implements StaticService,ServletContextAware {
	@Resource(name = "freeMarkerConfigurer")
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	@Resource(name ="articleDaoImpl")
	private ArticleDao articleDao;
	
	@Resource(name ="settingUtil")
	private SettingUtil settingUtil;
	
	*//** servletContext *//*
	private ServletContext servletContext;
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	*//**
	   * <b>function:</b>  静态化
	   * @project base
	   * @package com.xcj.admin.service.impl.common  
	   * @fileName com.xcj.*** 
	   * @createDate Oct 3, 2014 2:35:23 PM
	   * @return int
	   * @author su_jian
	 *//*
	public int build(String templatePath, String staticPath, Map<String, Object> model) {
		Assert.hasText(templatePath);
		Assert.hasText(staticPath);
		FileOutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		Writer writer = null;
		try {
			freemarker.template.Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templatePath);
			File staticFile = new File(servletContext.getRealPath(staticPath));
			File staticDirectory = staticFile.getParentFile();
			if (!staticDirectory.exists()) {
				staticDirectory.mkdirs();
			}
			fileOutputStream = new FileOutputStream(staticFile);
			outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
			writer = new BufferedWriter(outputStreamWriter);
			template.process(model, writer);
			writer.flush();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(writer);
			IOUtils.closeQuietly(outputStreamWriter);
			IOUtils.closeQuietly(fileOutputStream);
		}
		return 0;
	}
	*//**
	   * <b>function:</b>  单个文章文章静态化
	   * @project base
	   * @package com.xcj.admin.service.impl.common  
	   * @fileName com.xcj.*** 
	   * @createDate Oct 3, 2014 2:35:23 PM
	   * @return int
	   * @author su_jian
	 *//*
	public void buidSingleArticle(Integer id) {
		Article article=super.getById(Article.class, id);
		Map<String, Object> map=new HashMap<String, Object>();
		String dates=DateUtil.getFormatDateTime("yyMM", article.getCreateDate());
		String qrcodePath=FreeMarkerConstant.article_qrcodepath_m+dates+"/"+article.getId()+"."+FreeMarkerConstant.article_imageType_m;
		article.setQrcodePath(qrcodePath);
		map.put("article", article);
		String staticPath=FreeMarkerConstant.article_staticpath_m+dates+"/"+article.getId()+".html";
		this.build(FreeMarkerConstant.article_ftlpath_m,staticPath, map);
	}
	*//**
	   * <b>function:</b>  单个文章文章静态化
	   * @project base
	   * @package com.xcj.admin.service.impl.common  
	   * @fileName com.xcj.*** 
	   * @createDate Oct 3, 2014 2:35:23 PM
	   * @return int
	   * @author su_jian
	 *//*
	public void buidSingleArticle(Article ac) {
		Map<String, Object> map=new HashMap<String, Object>();
		String dates=DateUtil.getFormatDateTime("yyMM", ac.getCreateDate());
		String qrcodePath=FreeMarkerConstant.article_qrcodepath_m+dates+"/"+ac.getId()+"."+FreeMarkerConstant.article_imageType_m;
		ac.setQrcodePath(qrcodePath);
		map.put("article", ac);
		String staticPath=FreeMarkerConstant.article_staticpath_m+dates+"/"+ac.getId()+".html";
		this.build(FreeMarkerConstant.article_ftlpath_m,staticPath, map);
	}
	
	
	*//**
	   * <b>function:</b>  所有文章文章静态化
	   * @project base
	   * @package com.xcj.admin.service.impl.common  
	   * @fileName com.xcj.*** 
	   * @createDate Oct 3, 2014 2:35:23 PM
	   * @return int
	   * @author su_jian
	 *//*
	public int buidAllArticle(String acid, String stime, String etime) {
		List<Article> list=articleDao.buidAllArticle(acid,stime,etime);
		int j=0;
		for(int i = 0; i < list.size(); i++) {
			 Article ac=list.get(i);
			 buidSingleArticle(ac);
			 buidQRcode(ac.getId());
			 j++;
		}
		return j;
	}
	*//**
	   * <b>function:</b> 生成单个文件的QRcode
	   * @project base
	   * @package com.xcj.admin.service.impl.common  
	   * @fileName com.xcj.*** 
	   * @createDate Oct 3, 2014 2:35:23 PM
	   * @return int
	   * @author su_jian
	 *//*
	public void buidQRcode(Integer id) {
		Article article=super.getById(Article.class, id);
		String dates=DateUtil.getFormatDateTime("yyMM", article.getCreateDate());
		String qrcodePath=FreeMarkerConstant.article_qrcodepath_m+dates+"/"+article.getId()+"."+FreeMarkerConstant.article_imageType_m;
		String imagePath=servletContext.getRealPath(qrcodePath);
		TwoDimensionCode code =new TwoDimensionCode();
		String staticPath=FreeMarkerConstant.article_staticpath_m+dates+"/"+article.getId()+".html";
		String url=settingUtil.getSetting("siteUrl")+staticPath;
		code.encoderQRCode(url, imagePath, FreeMarkerConstant.article_imageType_m,5);
	}
}
*/