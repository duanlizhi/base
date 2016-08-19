package com.xcj.admin.controller.question;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@Controller("questionController")
@RequestMapping("/admin/question")
public class QuestionController extends BaseController {


	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView personInit(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return new ModelAndView("/admin/question/question-bank-index_jsp", model);
	}

	
	

}
