package com.xcj.api.person;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.base.BaseController;
import com.xcj.admin.dao.person.PersonDao;
import com.xcj.admin.entity.person.Person;
import com.xcj.common.util.common.ApiUtil;


@Controller
@RequestMapping("/oper/person")
public class PersonApiController extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(PersonApiController.class);
	
	@Autowired
	private PersonDao personDao;
	
	@ResponseBody
	@RequestMapping(value = "/testA", produces = "application/json;charset=UTF-8")
	public String testA(String str){
		return "@@"+str;
	}
	
	@ResponseBody
	@RequestMapping(value = "/testB", produces = "application/json;charset=UTF-8")
	public String testB(String str,Integer i,int j){
		return "@@"+str+i+j;
	}
	
	@RequestMapping(value = "/selfDefine", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ModelAndView selfDefine(ModelMap map) {
		map.addAttribute("param1", "This is a test param!");
		return new ModelAndView("selfDefine_jsp",map);
	}
	
	@RequestMapping(value = "/flashAttr", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String flashAttr(final RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("param2", "This is a flash attr!");
		return "redirect:/admin/person/persons_jsp";
	}
	
	@RequestMapping(value = "/upload", method=RequestMethod.POST)
	public String upload() {
		return "selfDefine_jsp";
	}
	
	@ResponseBody
	@RequestMapping(value = "/common")
	public ModelAndView common(String name, Integer age) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("name", name);
		modelAndView.addObject("age", age);
		modelAndView.setViewName("selfDefine_jsp");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "/complex")
	public String complex(String username) {
		// 状态
		String code = "";
		
		// 1 根据username查找
		List<Person> persons = personDao.findByHql("from Person where username = '" + username + "'");
		
		// 2 如果找到往下走  找不多插入新数据
		if(persons.size() > 0) {
			Person oldPerson = (Person)persons.get(0);
			// 3 根据旧的id+1 查找新的数据  修改新数据   如果没有不操作
			List<Person> newPersons = personDao.findByHql("from Person where id = " + (oldPerson.getId()+1));
			
			if(newPersons.size() > 0) {
				Person oldPersonPlus = newPersons.get(0);
				oldPersonPlus.setEmail("新:"+oldPerson.getEmail());
				oldPersonPlus.setModifyDate(new Timestamp(System.currentTimeMillis()));
				personDao.update(oldPersonPlus);
				
				code = "找到"+username+",\t找到"+oldPersonPlus.getUsername();
				
			} else {
				code = "找到"+username+",找不到新数据";
			}
		} else {
			
			code = "没有找到"+username+",插入新数据";
			
			Person person = new Person();
			person.setAddress("北京");
			person.setUsername(username);
			person.setPassword("123456");
			person.setIntegral(1);
			person.setIsTag(1);
			person.setUserGradeId(1);

			person.setCreateDate(new Timestamp(System.currentTimeMillis()));
			person.setEmail("lisi@lisi.com");
			person.setEnterpriseInfo("哦了");
			person.setModifyDate(new Timestamp(System.currentTimeMillis()));
			personDao.save(person);
		}
		return code;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/testApiInit")
	public JSONObject testApiInit(String username) {
		if(ApiUtil.isParamNull(username)){return ApiUtil.returnParamNull();}
		return null;
	}
}
