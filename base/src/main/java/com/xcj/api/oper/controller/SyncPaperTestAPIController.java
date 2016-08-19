package com.xcj.api.oper.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.knowledgePoint.KnowledgePoint;
import com.xcj.admin.entity.tests.Paper;
import com.xcj.admin.entity.tests.PapersCourse;
import com.xcj.admin.entity.tests.TestsPosted;
import com.xcj.admin.service.domain.DomainService;
import com.xcj.admin.service.tests.PaperService;
import com.xcj.admin.service.tests.PapersCourseService;
import com.xcj.admin.service.tests.TestsPostedService;
import com.xcj.common.util.APIConstants;
import com.xcj.common.util.Constants;
import com.xcj.common.util.common.ApiUtil;
import com.xcj.common.util.common.DateUtil;
import com.xcj.common.util.common.StringUtil;

/**
 * 
  * 同步试卷和测评信息
  * <b>function:</b> 
  * @project base 
  * @package com.xcj.api.oper.controller  
  * @fileName com.xcj.*
  * @createDate 2015-2-6 上午09:18:12 
  * @author yang.yan 
  * @email yang.yan@126.com
 */
@Controller("syncPaperTestAPIController")
@RequestMapping("/api/oper/")
public class SyncPaperTestAPIController {
	
	private static Log log = LogFactory.getLog(SyncPaperTestAPIController.class);
	@Resource(name = "papersCourseServiceImpl")
	private PapersCourseService papersCourseService;
	@Resource(name = "paperServiceImpl")
	private PaperService paperService;
	@Resource(name = "testsPostedServiceImpl")
	private TestsPostedService testsPostedService;
	
	 @Resource(name="domainServiceImpl")
	    private DomainService domainService;


	/**
	 * 
	   * 根据题目组合试卷
	   * <b>function:</b> GenerateTestPaper()
	   * @project base
	   * @package com.xcj.admin.controller.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-2 下午03:04:08
	   * @return ModelAndView
	   * @author yang.yan
	 */
	@ResponseBody
	@RequestMapping(value = "/generateTestPaper", produces = "application/json;charset=UTF-8")
	public JSONObject GenerateTestPaper(String objData) {
//		objData="{'courseWare':[{'id':'1','courseNumber':'wbt001'},{'id':'2','courseNumber':'wbt002'}],'paperName':'试卷一','answerNumber':'3','papersType':'sbt','score':'100','description':'考试用的','version':'11','totalTime':'120','token':'sdaf234324','username':'admin','userEmail':'userEmail'}";
		JSONObject data = JSONObject.parseObject(objData);
		String paperName = data.getString("paperName");
		String answerNumber = data.getString("answerNumber");
		String papersType = data.getString("papersType");
		String score = data.getString("score");
		String description = data.getString("description");
		String version = data.getString("version");
		String totalTime = data.getString("totalTime");
		String token = data.getString("token");
		String domainUsername = data.getString("domainUsername");
		String userEmail = data.getString("userEmail");
		JSONArray courseWareArray = data.getJSONArray("courseWare");  
        Integer questionSize = courseWareArray.size();  
		if (ApiUtil.isParamNull(paperName, answerNumber, papersType, score,description,version,totalTime,token,domainUsername,userEmail)) {
			return ApiUtil.returnParamNull();
		}
		
		data.clear();
		
		try {
			//TODO 需要调试讲此方法注释
			 if(!domainService.judgeTokenIsTime(token, domainUsername, userEmail)){
					data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_TOKEN_ISTIMING);
					data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_TOKEN_ISTIMING);
					return data;
			}
			Paper paper=new Paper();
			paper.setPaperName(paperName);
			paper.setQuestionSize(questionSize);
			paper.setAnswerNumber(Integer.parseInt(answerNumber));
			paper.setPapersType(papersType);
			paper.setScore(Integer.parseInt(score));
			paper.setTotalTime(Integer.parseInt(totalTime));
			paper.setVersion(version);
			paper.setState(1);
			paper.setDescription(description);
			paper.setCreateDate(DateUtil.getCurrentTimeByDate());
			paper.setModifyDate(DateUtil.getCurrentTimeByDate());
//			保存试卷
			Integer saveAndGetIdByInteger = paperService.saveAndGetIdByInteger(paper);
			for (int i = 0; i < questionSize; i++) {  
                JSONObject courseWare = courseWareArray.getJSONObject(i);  
                PapersCourse papersCourse=new PapersCourse();
                papersCourse.setCourseId(Integer.parseInt(courseWare.get("id").toString()));
                papersCourse.setPaperId(paper.getId());
                papersCourse.setScore(Integer.parseInt(courseWare.get("score").toString()));
//			保存试卷与课程的关系
                papersCourseService.save(papersCourse);
			}
			data.put("id",saveAndGetIdByInteger);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_SUCCESS);
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_SUCCESS);
		}  catch (Exception e) {
			log.error("同步试卷出错：" + e.getMessage());
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRCODE_FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRMSG__FU1);
			return data;
		}
		
		return data;
	}
	
	
	/**
	 * 
	 * 获取试卷列表信息
	 * <b>function:</b> getPaperList()
	 * @project base
	 * @package com.xcj.admin.controller.study  
	 * @fileName com.xcj.*** 
	 * @createDate 2015-2-2 下午03:04:08
	 * @return ModelAndView
	 * @author yang.yan
	 */
	@ResponseBody
	@RequestMapping(value = "/getPaperList", produces = "application/json;charset=UTF-8")
	public JSONObject getPaperList(String objData) {
		JSONObject data = JSONObject.parseObject(objData);
		String currentPage = "1";
		String pageSize = "10000";
		String token = data.getString("token");
		String domainUsername = data.getString("domainAccount");
		String userEmail = data.getString("userEmail");
		if (ApiUtil.isParamNull(token,domainUsername,userEmail)) {
			return ApiUtil.returnParamNull();
		}
		
		data.clear();
		try {
			if(!domainService.judgeTokenIsTime(token, domainUsername, userEmail)){
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_TOKEN_ISTIMING);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_TOKEN_ISTIMING);
				return data;
			}
			List<Paper> paperList=paperService.getListByPage(currentPage,pageSize);
			for (Paper paper : paperList) {
				List<CourseWare> courseWareByPaperId = paperService.getCourseWareByPaperId(paper.getId());
				for (CourseWare courseWare : courseWareByPaperId) {
					courseWare.setTempScore(papersCourseService.
								getPapersCourseScore(courseWare.getId().toString(), paper.getId().toString()));
				}
				paper.setCourseWareList(courseWareByPaperId);
				
			}
			data.put(Constants.XCJ_RETURN_API_DATA, paperList);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_SUCCESS);
		}  catch (Exception e) {
			log.error("获取试卷列表信息接口：" + e.getMessage());
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRCODE_FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRMSG__FU1);
			return data;
		}
		return data;
	}
	
	
	/**
	 * 
	 * 获取试卷信息
	 * <b>function:</b> getPaper()
	 * @project base
	 * @package com.xcj.admin.controller.study  
	 * @fileName com.xcj.*** 
	 * @createDate 2015-2-2 下午03:04:08
	 * @return ModelAndView
	 * @author yang.yan
	 */
	@ResponseBody
	@RequestMapping(value = "/getPaper", produces = "application/json;charset=UTF-8")
	public JSONObject getPaper(String objData) {
		//String	objData="{'id':'32','token':'BEC81CBB9ADA05E7F3EF7A8E2F86B78C','domainUsername':'admin','userEmail':'sujiansoft@163.com'}";
		JSONObject data = JSONObject.parseObject(objData);
		String id = data.getString("id");
		String token = data.getString("token");
		String domainUsername = data.getString("domainUsername");
		String userEmail = data.getString("userEmail");
		if (ApiUtil.isParamNull(id,token,domainUsername,userEmail)) {
			return ApiUtil.returnParamNull();
		}
		
		data.clear();
		
		try {
			if(!domainService.judgeTokenIsTime(token, domainUsername, userEmail)){
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_TOKEN_ISTIMING);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_TOKEN_ISTIMING);
				return data;
		 }
			Paper paper = paperService.getById(Paper.class, Integer.parseInt(id));
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,Constants.XCJ_RETURN_SUCCESS);
			data.put(Constants.XCJ_RETURN_API_DATA, paper);
		}  catch (Exception e) {
			log.error(":题目组合试卷出错：" + e.getMessage());
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRCODE_FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRMSG__FU1);
			return data;
		}
		return data;
	}
	
	
	/**
	 * 
	 * 根据获取试卷组织考试
	 * <b>function:</b> initStudy()
	 * @project base
	 * @package com.xcj.admin.controller.study  
	 * @fileName com.xcj.*** 
	 * @createDate 2015-2-2 下午03:04:08
	 * @return ModelAndView
	 * @author yang.yan
	 */
	@ResponseBody
	@RequestMapping(value = "/getPaperTest", produces = "application/json;charset=UTF-8")
	public JSONObject getPaperTest(String	objData) {
//		objData="{'testId':[{'id':'1'}],'version':'1.0','startTime':'2015-03-04','endTime':'2015-03-04','answerTime':'2015-03-04','releaseTime':'2015-03-04','paperIds':'1','limitCount':'3','scoreType':'100','paperType':'1','testName':'1','token':'sbt3234455667','username':'admin','userEmail':'userEmail'}";
		JSONObject data = JSONObject.parseObject(objData);
		String startTime = data.getString("startTime");
		String endTime = data.getString("endTime");
		String answerTime = data.getString("answerTime");
		String paperId = data.getString("paperId");
		String limitCount = data.getString("limitCount");
		String version = data.getString("version");
		String releaseTime = data.getString("releaseTime");
		String scoreType = data.getString("scoreType");
		String testName = data.getString("testName");
		String score = data.getString("score");
		String token = data.getString("token");
		String domainUsername = data.getString("domainUsername");
		String userEmail = data.getString("userEmail");
		String contentTestId=data.getString("contentTestId");
		if (ApiUtil.isParamNull(startTime,endTime,answerTime,paperId,limitCount,releaseTime,testName,score,token,domainUsername,userEmail)) {
			return ApiUtil.returnParamNull();
		}
		
		data.clear();
		try {
			if(!domainService.judgeTokenIsTime(token, domainUsername, userEmail)){
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_TOKEN_ISTIMING);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_TOKEN_ISTIMING);
				return data;
			}
	        TestsPosted testsPosted=new TestsPosted();
	        testsPosted.setPaperId(Integer.parseInt(paperId));	 
			testsPosted.setAnswerTime(new Date(Long.parseLong(answerTime)));
			testsPosted.setStartTime(new Date(Long.parseLong(startTime)));
			testsPosted.setEndTime(new Date(Long.parseLong(endTime)));
			testsPosted.setLimitCount(Integer.parseInt(limitCount));
			testsPosted.setPublisher(domainUsername);
			testsPosted.setReleaseTime(new Date(Long.parseLong(releaseTime)));
			if(!StringUtil.isEmptyYl(scoreType)) testsPosted.setScoreType(Integer.parseInt(scoreType));
			testsPosted.setTestName(testName);
			testsPosted.setScore(Integer.parseInt(score));
			if(!StringUtil.isEmptyYl(version)) testsPosted.setVersion(version);
			testsPosted.setCreateDate(DateUtil.getCurrentTimeByDate());
			testsPosted.setModifyDate(DateUtil.getCurrentTimeByDate());
			//插入知识点
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
			//判断当前方法是更新还是保存
			Integer saveAndGetIdByInteger =0;
			if(contentTestId==null){
				saveAndGetIdByInteger = testsPostedService.saveAndGetIdByInteger(testsPosted);
			}else{
				testsPosted.setId(Integer.valueOf(contentTestId));
				testsPosted.setModifyDate(DateUtil.getCurrentTimeByDate());
				testsPostedService.update(testsPosted);
			}
			data.put("id",saveAndGetIdByInteger);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,Constants.XCJ_RETURN_SUCCESS);
			data.put(Constants.XCJ_RETURN_API_DATA, testsPosted);
		}  catch (Exception e) {
			log.error("同步测评出错：" + e.getMessage());
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRCODE_FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRMSG__FU1);
			return data;
		}
		
		return data;
	}
}
