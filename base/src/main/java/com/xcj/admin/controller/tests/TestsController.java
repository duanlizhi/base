package com.xcj.admin.controller.tests;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.base.BaseController;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.knowledgePoint.KnowledgePoint;
import com.xcj.admin.entity.tests.Paper;
import com.xcj.admin.entity.tests.TestsPosted;
import com.xcj.admin.service.admin.AdminService;
import com.xcj.admin.service.course.CourseWareService;
import com.xcj.admin.service.tests.PaperService;
import com.xcj.admin.service.tests.PapersCourseService;
import com.xcj.admin.service.tests.TestsPostedService;
import com.xcj.common.entity.Principal;
import com.xcj.common.page.Page;
import com.xcj.common.util.Constants;
import com.xcj.common.util.common.DateUtil;
import com.xcj.common.util.common.HttpUtil;

/**
 * <b>function:</b>
 * 
 * @project base
 * @package com.xcj.admin.controller.tests
 * @fileName com.xcj.*
 * @createDate 2015-2-4 下午06:41:55
 * @author dapeng_wu
 */
@Controller("testsController")
@RequestMapping("/admin/tests")
public class TestsController extends BaseController {

	private static final Logger log = LoggerFactory
			.getLogger(TestsController.class);

	@Resource(name = "paperServiceImpl")
	private PaperService paperService;

	@Resource(name = "testsPostedServiceImpl")
	private TestsPostedService testsPostedService;

	@Resource(name = "courseWareServiceImpl")
	private CourseWareService courseWareService;

	@Resource(name = "papersCourseServiceImpl")
	private PapersCourseService papersCourseService;

	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	/**
	 * <b>function:</b> 测评库主页--附加搜索关键字
	 * 
	 * @project base
	 * @package com.xcj.admin.controller.course
	 * @fileName com.xcj.***
	 * @createDate 2015-2-3 下午07:08:10
	 * @return String
	 * @author dapeng_wu
	 */
	@RequestMapping(value = "/index")
	public String courseInit(Model model, Page<Paper> page, String search) {
		try {
			// 每页显示条数
			page.setPageSize(10);
			Page<Paper> pageList = paperService.getIndexPaperList(page, search);
			List<Integer> issue = paperService.getIssue(pageList);
			model.addAttribute("page", pageList);
			model.addAttribute("issue", issue);
			if (search != null && !(search.equals(""))) {
				model.addAttribute("search", search);
			}
			
			//以下获取token sessionId publicId
			Subject currentUser = SecurityUtils.getSubject();
			Principal principal = (Principal) currentUser.getPrincipal();
			String email = adminService.getCurrentByPageParam(principal.getUsername()).getEmail();
			HashMap<String, String> map =new HashMap<String, String>();
			map.put("domainAccount", "contentadmin");
			map.put("domainPassword","admin");
			map.put("userEmail",email);
			JSONObject data =new JSONObject();
			data.put("userEmail",email);
			model.addAttribute("tsp", data.toJSONString());
		} catch (Exception e) {
			log.error("测评库初主页初始化出错：" + e.getMessage());
		}
		return "/admin/tests/index_jsp";
	}

	/**
	 * <b>function:</b> 测评库是否禁用
	 * 
	 * @project base
	 * @package com.xcj.admin.controller.course
	 * @fileName com.xcj.***
	 * @createDate 2015-2-4 上午09:09:08
	 * @return String
	 * @author dapeng_wu
	 */
	@ResponseBody
	@RequestMapping(value = "/disable")
	public String courseLimit(Model model, String id, String state) {
		try {
			//dapeng_wu success:1  faile:0 exception:0
			return paperService.updateDisable(id, state) ? "1" : "0";
		} catch (Exception e) {
			log.error("测评库是否禁用出错：" + e.getMessage());
			return "0";
		}
	}

	/**
	 * <b>function:</b>批量删除试卷
	 * 
	 * @project base
	 * @package com.xcj.admin.controller.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-4 上午10:49:50
	 * @return String
	 * @author dapeng_wu
	 */
	@ResponseBody
	@RequestMapping(value = "/deletePaper")
	public String deletePaper(Model model, String ids) {
		try {
			return paperService.deletePaper(ids) ? "1" : "0";
		} catch (Exception e) {
			log.error("测评库删除出错：" + e.getMessage());
			return "1";
		}
	}

	/**
	 * <b>function:</b> 试卷编辑
	 * 
	 * @project base
	 * @package com.xcj.admin.controller.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-5 上午09:35:43
	 * @return String
	 * @author dapeng_wu
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePaper")
	public String updatePaper(Model model, Paper paper) {
		try {
			return paperService.updatePaper(paper) ? "true" : "false";
		} catch (Exception e) {
			log.error("试卷编辑出错：" + e.getMessage());
			return "false";
		}
	}

	/**
	 * <b>function:</b> 查看发布记录
	 * 
	 * @project base
	 * @package com.xcj.admin.controller.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-5 下午01:47:31
	 * @return String
	 * @author dapeng_wu
	 */
	@ResponseBody
	@RequestMapping(value = "/issuePaper")
	public JSONObject issuePaper(Model model, Paper paper,
			HttpServletRequest request, Page<TestsPosted> page) {
		JSONObject pageAsjson = new JSONObject();
		try {
			Page<TestsPosted> issuePage = testsPostedService.issuePaper(paper,
					page);
			pageAsjson = testsPostedService.pageAsjson(issuePage, request);
		} catch (Exception e) {
			log.error("查看发布记录出错：" + e.getMessage());
		}
		return pageAsjson;
	}

	/**
	 * <b>function:</b> 发布保存局部刷新分页
	 * 
	 * @project base
	 * @package com.xcj.admin.controller.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-6 下午02:09:18
	 * @return String
	 * @author dapeng_wu
	 */
	@RequestMapping(value = "/saveTestsPosted")
	public String saveTestsPosted(Integer currenPage, Integer totalsCount,
			String search, ModelMap model, TestsPosted testsPosted,String startTime,
			String endTime,String answerTime, Page<Paper> page) {
		try {
			if (testsPosted == null || currenPage == null
					|| totalsCount == null) {
				return "redirect:/admin/tests/index_jsp";
			}
			// 保存到发布测评到数据库
			testsPosted.setStartTime(DateUtil.StrToDate(startTime+":00"));
			testsPosted.setEndTime(DateUtil.StrToDate(endTime+":00"));
			testsPosted.setAnswerTime(DateUtil.StrToDate(answerTime+":00"));
			testsPosted.setReleaseTime(DateUtil.getCurrentTimeByDate());
			testsPosted.setCreateDate(DateUtil.getCurrentTimeByDate());
			testsPosted.setModifyDate(DateUtil.getCurrentTimeByDate());
			testsPosted.setScore(paperService.getById(Paper.class, testsPosted.getPaperId()).getScore());
			List<KnowledgePoint> testsPostedKp = testsPostedService.getTestsPostedKp(testsPosted.getPaperId().toString());
			//wbt成绩
			List<KnowledgePoint> wbtScore = testsPostedService.getWbtScore(testsPosted.getPaperId().toString());
			List<KnowledgePoint> sbtScore = testsPostedService.getSbtScore(testsPosted.getPaperId().toString());
			StringBuffer kpSb = new StringBuffer("");
			StringBuffer sbtSb = new StringBuffer("");
			StringBuffer wbtSb = new StringBuffer("");
			for (KnowledgePoint knowledgePoint : testsPostedKp) {
				kpSb.append(knowledgePoint.getId()).append(",");
				boolean flag = true;
				for (KnowledgePoint sbtKnowledgePoint : sbtScore) {
					if(knowledgePoint.getId() == sbtKnowledgePoint.getId()){
						sbtSb.append(sbtKnowledgePoint.getScore()).append(",");
						flag = false;
					}
				}
				if(flag){
					sbtSb.append("0").append(",");
				}
				flag = true;
				for (KnowledgePoint wbtKnowledgePoint : wbtScore) {
					if(knowledgePoint.getId() == wbtKnowledgePoint.getId()){
						wbtSb.append(wbtKnowledgePoint.getScore()).append(",");
						flag = false;
					}
				}
				if(flag){
					wbtSb.append("0").append(",");
				}
			}
			
			if("".equals(kpSb.toString())){
				testsPosted.setKp("");
				testsPosted.setKpScore("");
			}else{
				testsPosted.setKp(kpSb.substring(0,kpSb.length()-1).toString());
				String[] sbtSplit = sbtSb.substring(0, sbtSb.length()-1).split(",");
				String[] wbtSplit = wbtSb.substring(0, wbtSb.length()-1).split(",");
				StringBuffer sumSb = new StringBuffer("");
				for (int i = 0; i < wbtSplit.length; i++) {
					sumSb.append(Float.parseFloat(sbtSplit[i])+Float.parseFloat(wbtSplit[i])).append(",");
				}
				testsPosted.setKpScore(sumSb.substring(0,sumSb.length()-1).toString());
			}
			Integer testsPostedId = testsPostedService.saveAndGetIdByInteger(testsPosted);
			
			//TODO 同步到demo2q
			testsPosted.setId(testsPostedId);
			courseWareService.syncTestPosted(JSON.toJSONString(testsPosted));
			
			// 每页显示条数(提交完回当前页面)
			page.setPageSize(8);
			page.setCurrentPage(currenPage);
			page.setTotalsCount(totalsCount);
			Page<Paper> pageList = paperService.getIndexPaperList(page, search);
			List<Integer> issue = paperService.getIssue(pageList);
			model.addAttribute("page", pageList);
			model.addAttribute("issue", issue);
			return "/admin/tests/index_jsp";
		} catch (Exception e) {
			log.error("发布保存出错：" + e.getMessage());
			return "redirect:/admin/tests/index_jsp";
		}
	}

	/**
	 * <b>function:</b> 获取题组集合带分页（status弹出层是否显示，courseWareIds选中的题组）
	 * 
	 * @project base
	 * @package com.xcj.admin.controller.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-7 上午10:48:10
	 * @return String
	 * @author dapeng_wu
	 */
	@RequestMapping(value = "/groupTitleInit")
	public String groupTitleInit(Model model, Page<CourseWare> page,
			String search, String status, String cids, Integer currentPage) {
		try {
			// 每页显示数量
			page.setPageSize(5);
			if (currentPage != null) {
				page.setCurrentPage(currentPage);
			}
			Page<CourseWare> pageCourseWare = courseWareService.getByPage(page,
					search);
			model.addAttribute("page", pageCourseWare);
			model.addAttribute("status", status);
			model.addAttribute("cids", cids);
			if (search != null && !(search.equals(""))) {
				model.addAttribute("search", search);
			}
		} catch (Exception e) {
			log.error("发布保存出错：" + e.getMessage());
			return "redirect:/admin/tests/index_jsp";
		}
		return "/admin/tests/groupTitle_jsp";
	}

	/**
	 * <b>function:</b> 根据id找到courseWare集合
	 * 
	 * @project base
	 * @package com.xcj.admin.controller.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-8 上午10:25:49
	 * @return JSONObject
	 * @author dapeng_wu
	 */
	@ResponseBody
	@RequestMapping(value = "/getCourseWareByIds")
	public JSONObject getCourseWareByIds(Model model, String ids) {
		JSONObject jsonObject = new JSONObject();
		try {
			if (ids != null && !("".equals(ids))) {
				ids = ids.substring(0, ids.length() - 1);
				try {
					// 如果ids里有字符则解决异常
					String[] split = ids.split(",");
					for (int i = 0; i < split.length; i++) {
						Integer.parseInt(split[i]);
					}
					jsonObject.put("data", courseWareService
							.getCourseWareByIds(ids));
				} catch (Exception e) {
					log.error("查看发布记录ids解析出错：" + e.getMessage());
				}
			}
		} catch (Exception e) {
			log.error("查看发布记录出错：" + e.getMessage());
		}
		return jsonObject;
	}

	/**
	 * <b>function:</b> 保存组卷
	 * 
	 * @project base
	 * @package com.xcj.admin.controller.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-8 下午01:39:50
	 * @return String
	 * @author dapeng_wu
	 */
	@RequestMapping(value = "/savePaper")
	public String savePaper(Model model, String courseWareIds, Paper paper,HttpServletRequest request) {
		try {
			if (courseWareIds == null || "".equals(courseWareIds)) {
				return "redirect:/admin/tests/groupTitleInit_jsp";
			}
			String[] cIds = courseWareIds.substring(0,
					courseWareIds.length() - 1).split(",");
			String[] paperCourseWareScoreId = request.getParameterValues("paperCourseWareScoreId");
			String[] courseWareScore = request.getParameterValues("courseWareScore");
			papersCourseService.savePapersCourse(paper, cIds, paperCourseWareScoreId, courseWareScore);
		} catch (Exception e) {
			log.error("保存组卷出错：" + e.getMessage());
		}
		return "redirect:/admin/tests/groupTitleInit_jsp";
	}
	
	/**
	 * 
	   * <b>function:</b> validate校验试卷名称唯一
	   * @project base
	   * @package com.xcj.admin.controller.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-19 下午01:33:32
	   * @return String
	   * @author dapeng_wu
	 */
	@ResponseBody
	@RequestMapping(value = "/validatePaperName")
	public String validatePaperName(Model model,String name) {
		try {
			List<Paper> paperList = paperService.getPaperByName(name);
			if(paperList == null || paperList.size()<1){
				return "1";
			}else{
				return "0";
			}
		} catch (Exception e) {
			log.error("validate校验试卷名唯一：" + e.getMessage());
		}
		return "0";
	}
	
	/**
	   * <b>function:</b> 根据试卷名称获取courseware集合
	   * @project base
	   * @package com.xcj.admin.controller.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-31 下午03:27:00
	   * @return String
	   * @author dapeng_wu
	 */
	@ResponseBody
	@RequestMapping(value = "/getCourseWareByPaperId")
	public JSONObject getCourseWareByPaperId(Model model,String paperId) {
		JSONObject jsonObject = new JSONObject();
		try {
			List<CourseWare> courseWareList = paperService.getCourseWareByPaperId(Integer.parseInt(paperId));
			jsonObject.put("courseWareList",courseWareList);
			return jsonObject;
		} catch (Exception e) {
			log.error("根据试卷名称获取courseware集合错误：" + e.getMessage());
		}
		return jsonObject;
	}
}
