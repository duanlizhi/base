/*package com.xcj.web.controller.article;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xcj.admin.base.BaseController;
import com.xcj.admin.entity.article.Article;
import com.xcj.admin.entity.article.ArticleStatistics;
import com.xcj.admin.service.article.ArticleService;
import com.xcj.admin.service.article.ArticleStatisticsService;
import com.xcj.common.util.DateUtil;
@Controller("wArticleController")
@RequestMapping("/article")
public class ArticleController extends BaseController {
	 
	 @Resource(name="articleServiceImpl")
	 private ArticleService articleService;
	
	 @Resource(name="articleStatisticsServiceImpl")
	 private ArticleStatisticsService articleStatisticsService;
	 
	*//**
	 * 点击数
	 *//*
	@ResponseBody
	@RequestMapping(value = "/hits/{id}", produces = "application/json;charset=UTF-8")
	public String hits(@PathVariable Integer id,String system,String bappName,
			String bversion,String from,String isappinstalled,String userAgent,
			HttpServletRequest request, HttpServletResponse response) {
		if(id==null){
			return "0";
		}
		Article article=articleService.getById(Article.class, id);
		article.setHits(article.getHits()+1);
		articleService.merge(article);
		ArticleStatistics articleStatistics =new ArticleStatistics();
		articleStatistics.setArticleId(article.getId());
		articleStatistics.setArticleTitle(article.getTitle());
		articleStatistics.setBoring(article.getBoring());
		articleStatistics.setLoves(article.getLoves());
		articleStatistics.setHits(article.getHits()+1);
		articleStatistics.setSystem(system==null?"":system);
		articleStatistics.setCreateDate(DateUtil.getCurrentTimeByDate());
		articleStatistics.setStype(1);//点击的日志
		articleStatistics.setModifyDate(DateUtil.getCurrentTimeByDate());
		articleStatistics.setUserAgent(userAgent);
		if(from!=null&&from.equals("timeline")){
			articleStatistics.setIsWeixin(1);
		}else{
			articleStatistics.setIsWeixin(0);
		}
		articleStatistics.setBrowserName(bappName==null?"":bappName);
		if(system.toLowerCase().contains("WeiXin".toLowerCase())){
			articleStatistics.setIsWeixin(1);
		}
		articleStatistics.setBrowserVersion(bversion==null?"":bversion);
		try {
			articleStatisticsService.save(articleStatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article.getHits().toString();
	}
	
	*//**
	 *  点击 喜欢数量
	 *//*
	@ResponseBody
	@RequestMapping(value = "/cloves/{id}", produces = "application/json;charset=UTF-8")
	public String cloves(@PathVariable Integer id,String system,String bappName,
			String bversion,String from,String isappinstalled,String userAgent,
			HttpServletRequest request, HttpServletResponse response) {
		if(id==null){
			return "0";
		}
		Article article=articleService.getById(Article.class, id);
		article.setLoves(article.getLoves()+1);
		articleService.merge(article);
		 ArticleStatistics articleStatistics =new ArticleStatistics();
		articleStatistics.setArticleId(article.getId());
		articleStatistics.setArticleTitle(article.getTitle());
		articleStatistics.setBoring(article.getBoring());
		articleStatistics.setLoves(article.getLoves());
		articleStatistics.setHits(article.getHits()+1);
		articleStatistics.setSystem(system==null?"":system);
		articleStatistics.setCreateDate(DateUtil.getCurrentTimeByDate());
		articleStatistics.setModifyDate(DateUtil.getCurrentTimeByDate());
		articleStatistics.setUserAgent(userAgent);
		articleStatistics.setStype(2);
		if(from!=null&&from.equals("timeline")){
			articleStatistics.setIsWeixin(1);
		}else{
			articleStatistics.setIsWeixin(0);
		}
		articleStatistics.setBrowserName(bappName==null?"":bappName);
		if(system.toLowerCase().contains("WeiXin".toLowerCase())){
			articleStatistics.setIsWeixin(1);
		}
		articleStatistics.setBrowserVersion(bversion==null?"":bversion);
		try {
			articleStatisticsService.save(articleStatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article.getLoves().toString();
	}
	*//**
	 *  点击 不喜欢数量
	 *//*
	@ResponseBody
	@RequestMapping(value = "/cborings/{id}", produces = "application/json;charset=UTF-8")
	public String cborings(@PathVariable Integer id,String system,String bappName,
			String bversion,String from,String isappinstalled,String userAgent,
			HttpServletRequest request, HttpServletResponse response) {
		if(id==null){
			return "0";
		}
		Article article=articleService.getById(Article.class, id);
		article.setBoring(article.getBoring()+1);
		articleService.merge(article);
		ArticleStatistics articleStatistics =new ArticleStatistics();
		articleStatistics.setArticleId(article.getId());
		articleStatistics.setArticleTitle(article.getTitle());
		articleStatistics.setBoring(article.getBoring());
		articleStatistics.setLoves(article.getLoves());
		articleStatistics.setHits(article.getHits()+1);
		articleStatistics.setSystem(system==null?"":system);
		articleStatistics.setCreateDate(DateUtil.getCurrentTimeByDate());
		articleStatistics.setModifyDate(DateUtil.getCurrentTimeByDate());
		articleStatistics.setUserAgent(userAgent);
		articleStatistics.setStype(3);
		if(from!=null&&from.equals("timeline")){
			articleStatistics.setIsWeixin(1);
		}else{
			articleStatistics.setIsWeixin(0);
		}
		articleStatistics.setBrowserName(bappName==null?"":bappName);
		if(system.toLowerCase().contains("WeiXin".toLowerCase())){
			articleStatistics.setIsWeixin(1);
		}
		articleStatistics.setBrowserVersion(bversion==null?"":bversion);
		try {
			articleStatisticsService.save(articleStatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article.getBoring().toString();
	}
	
	
	*//**
	 * 不喜欢数量 只获取数量
	 *//*
	@ResponseBody
	@RequestMapping(value = "/borings/{id}", produces = "application/json;charset=UTF-8")
	public String borings(@PathVariable Integer id,
			HttpServletRequest request, HttpServletResponse response) {
		if(id==null){
			return "0";
		}
		Article article=articleService.getById(Article.class, id);
		return article.getBoring().toString();
	}
	
	*//**
	 * 喜欢数量  只获取数量
	 *//*
	@ResponseBody
	@RequestMapping(value = "/loves/{id}", produces = "application/json;charset=UTF-8")
	public String loves(@PathVariable Integer id,String system,String bappName,
			String bversion,String from,String isappinstalled,String userAgent,
			HttpServletRequest request, HttpServletResponse response) {
		if(id==null){
			return "0";
		}
		Article article=articleService.getById(Article.class, id);
		return article.getLoves().toString();
	}
	

}*/