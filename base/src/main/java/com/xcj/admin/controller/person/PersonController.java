package com.xcj.admin.controller.person;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xcj.admin.base.BaseController;
import com.xcj.admin.entity.person.Person;
import com.xcj.admin.service.person.PersonService;

/**
 * 
 * <b>function:</b> person
 * 
 * @package com.xcj.admin.controller.person.
 * @fileName com.xcj.admin.*
 * @createDate Tue Dec 23 18:15:50 CST 2014
 * @author name_**
 */
@Controller("personController")
@RequestMapping("/admin/person")
public class PersonController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Resource(name = "personServiceImpl")
	private PersonService personService;

	/**
	 * 
	 * <b>function:</b> person---init方法
	 * 
	 * @project testbaseframe
	 * @package com.xcj.admin.controller.person.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Dec 23 18:15:50 CST 2014
	 * @return ModelAndView
	 * @author name_**
	 */
	@RequestMapping(value = "/personInit", method = RequestMethod.GET)
	public ModelAndView personInit(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return new ModelAndView("/admin/person/personlist_jsp", model);
	}

	/**
	 * 
	 * <b>function:</b> person---获取数据的方法
	 * 
	 * @project testbaseframe
	 * @package com.xcj.admin.controller.person.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Dec 23 18:15:50 CST 2014
	 * @return List<Person>
	 * @author name_**
	 */
	@RequestMapping(value = "/personList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	List<Person> personList(Model model) {
		List<Person> list = new ArrayList<Person>();
		try {
			list = personService.getSortList();
		} catch (Exception e) {
			logger.error(PersonController.class.getName()+" personList() exception: "+e.getMessage());
		}
		return list;
	}

	/**
	 * 
	 * <b>function:</b> person---保存初始化的方法
	 * 
	 * @project testbaseframe
	 * @package com.xcj.admin.controller.person.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Dec 23 18:15:50 CST 2014
	 * @return String
	 * @author name_**
	 */
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String save(Model model) {
		return "/admin/person/personsave_jsp";
	}

	/**
	 * 
	 * <b>function:</b> person---保存的方法
	 * 
	 * @project testbaseframe
	 * @package com.xcj.admin.controller.person.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Dec 23 18:15:50 CST 2014
	 * @return String
	 * @author name_**
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Validated Person person, Model model) {
		try {
			personService.save(person);
		} catch (Exception e) {
			logger.error(PersonController.class.getName()+" save() exception: "+e.getMessage());
		}
		return "redirect:/admin/person/persons_jsp";
	}

	/**
	 * 
	 * <b>function:</b> person---查看详细信息的方法
	 * 
	 * @project testbaseframe
	 * @package com.xcj.admin.controller.person.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Dec 23 18:15:50 CST 2014
	 * @return String
	 * @author name_**
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable
	Integer id, Model model) {
		Person person = new Person();
		try {
			person = personService.getById(Person.class, id);
		} catch (Exception e) {
			logger.error(PersonController.class.getName()+" detail() exception: "+e.getMessage());
		}
		return "/admin/person/persondetail_jsp";
	}

	/**
	 * 
	 * <b>function:</b> person---更新初始化的方法
	 * 
	 * @project testbaseframe
	 * @package com.xcj.admin.controller.person.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Dec 23 18:15:51 CST 2014
	 * @return String
	 * @author name_**
	 */
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String update(@PathVariable
	Integer id, Model model) {
		Person person = new Person();
		try {
			person = personService.getById(Person.class, id);
		} catch (Exception e) {
			logger.error(PersonController.class.getName()+" update() exception: "+e.getMessage());
		}
		model.addAttribute("person", person);
		return "/admin/person/personedit_jsp";
	}

	/**
	 * 
	 * <b>function:</b> person---更新数据的方法
	 * 
	 * @project testbaseframe
	 * @package com.xcj.admin.controller.person.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Dec 23 18:15:51 CST 2014
	 * @return Person
	 * @author name_**
	 */
	@RequestMapping(value = "/updata", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	Person updata(Model model, Integer id) {
		Person person = new Person();
		try {
			person = personService.getById(Person.class, id);
		} catch (Exception e) {
			logger.error(PersonController.class.getName()+" update() exception: "+e.getMessage());
		}
		return person;
	}

	/**
	 * 
	 * <b>function:</b> person---删除单条数据的方法
	 * 
	 * @project testbaseframe
	 * @package com.xcj.admin.controller.person.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Dec 23 18:15:51 CST 2014
	 * @return String
	 * @author name_**
	 * @throws Exception
	 */
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String update(@PathVariable Integer id) throws Exception {
		
		try {
			personService.removeById(id);
		} catch (Exception e) {
			logger.error(PersonController.class.getName()+" update() exception: "+e.getMessage());
		}
		return "redirect:/admin/person/save_jsp";
	}
	
	

}
