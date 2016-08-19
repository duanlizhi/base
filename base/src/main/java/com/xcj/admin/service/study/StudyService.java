package com.xcj.admin.service.study;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.xcj.admin.base.BaseService;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.course.CourseWareAction;
import com.xcj.admin.entity.course.CourseWareParts;
import com.xcj.admin.entity.course.CourseWareTool;
import com.xcj.admin.entity.course.SbtQuestion;
import com.xcj.admin.entity.course.StudyResult;
import com.xcj.admin.entity.study.StudyLog;
import com.xcj.admin.entity.vo.StudyHistory;

/**
 * 
  * <b>function:</b> 
  * @project base 
  * @package com.xcj.admin.service.study  
  * @fileName com.xcj.*
  * @createDate 2015-2-6 下午01:14:07 
  * @author hehujun 
  * @email hehujun@126.com
 */
public interface StudyService extends BaseService {

	/**
	 * 
	   * 获取内容权限 根据域名称 
	   * <b>function:</b> getCourseWares()
	   * @project base
	   * @package com.xcj.admin.service.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-4 上午10:04:30
	   * @return List<CourseWare>
	   * @author hehujun
	 */
	public List<CourseWare> getCourseWares(String domineName, String type) throws Exception;
	
	/**
	 * 
	   * 获取内容权限 根据域名称 
	   * <b>function:</b> getCourseWares()
	   * @project base
	   * @package com.xcj.admin.service.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-4 上午10:04:23
	   * @return List<CourseWare>
	   * @author hehujun
	 */
	public List<CourseWare> getCourseWares(String domineName) throws Exception;
	
	/**
	 * 
	   * 根据用户id 课程号获取 用户学习的信息 
	   * <b>function:</b> getStudyTotalInfos()
	   * @project base
	   * @package com.xcj.admin.service.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-4 上午10:03:59
	   * @return List<TotalStudyInfo>
	   * @author hehujun
	 */
	public StudyHistory getStudyTotalInfos(String userEmail, String courseNum,String faultNumber,String studyFlag) throws Exception;
	
	/**
	 * 
	   * 更新 学习数据 记录学习日志
	   * <b>function:</b> addStudyLog()
	   * @project base
	   * @package com.xcj.admin.service.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-4 上午10:31:45
	   * @return boolean
	   * @author hehujun
	 */
	public boolean addStudyLog(StudyLog studyLog) throws Exception;

	/**
	 * 
	   * 根据条件查询StudyScore,并且保存
	   * <b>function:</b> getAndSaveStudyScores()
	   * @project base
	   * @package com.xcj.admin.service.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-8 上午09:41:17
	   * @return List<StudyScore>
	   * @author hehujun
	 */
	public List<StudyResult> getAndSaveStudyScores(List<StudyResult> results) throws Exception;
	
	/**
	 * 
	   * 获取sessionId
	   * <b>function:</b> generateSessionId()
	   * @project base
	   * @package com.xcj.admin.service.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-11 上午09:47:05
	   * @return String
	   * @author hehujun
	 */
	public String generateSessionId(String useremail) throws Exception;
	
	/**
	 * 
	   * 判断sessionId是否有效
	   * <b>function:</b> 
	   * @project base
	   * @package com.xcj.admin.service.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-11 上午10:12:56
	   * @return boolean
	   * @author hehujun
	 */
	public boolean judgeSesssionIdValidate(String sessionId, String useremail) throws Exception; 
	
	/**
	 * 
	   * 获取会话时间
	   * <b>function:</b> 
	   * @project base
	   * @package com.xcj.admin.service.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-11 下午03:28:21
	   * @return int
	   * @author hehujun
	 */
	public int getSessionTime(String useremail) throws Exception;
	
	/**
	 * 
	   * 保存studylog
	   * <b>function:</b> 
	   * @project base
	   * @package com.xcj.admin.service.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-11 下午03:41:10
	   * @return boolean
	   * @author hehujun
	 */
	public boolean save(StudyLog studyLog, String method) throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 获取学习结果id 在保存学习动作或者学习答案之前
	   * @project base
	   * @package com.xcj.admin.dao.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-6 上午11:14:56
	   * @return StudyResult
	   * @author hehujun
	 */
	public StudyResult getStudyResultBeforeSaveActionOrAnswer(String courseNumer, String domainUserName, String userEmail, String sessionId) throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 根据动作id 获取动作
	   * @project base
	   * @package com.xcj.admin.dao.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-6 下午01:34:09
	   * @return CourseWareAction
	   * @author hehujun
	 */
	public CourseWareAction getCourseWareActionByActionId(String actionId) throws Exception;
	
	/**
	 * 
	 * <b>function:</b>  根据部件id 获取部件
	 * @project base
	 * @package com.xcj.admin.dao.study  
	 * @fileName com.xcj.*** 
	 * @createDate 2015-3-6 下午01:34:09
	 * @return CourseWareAction
	 * @author hehujun
	 */
	public CourseWareParts getCourseWarePartsByActionId(String partId) throws Exception;
	
	/**
	 * 
	 * <b>function:</b>  根据工具id 获取工具
	 * @project base
	 * @package com.xcj.admin.dao.study  
	 * @fileName com.xcj.*** 
	 * @createDate 2015-3-6 下午01:34:09
	 * @return CourseWareAction
	 * @author hehujun
	 */
	public CourseWareTool getCourseWareToolByActionId(String partId) throws Exception;
	
	/**
	 * 
	 * <b>function:</b>  获取sbt题
	 * @project base
	 * @package com.xcj.admin.dao.study  
	 * @fileName com.xcj.*** 
	 * @createDate 2015-3-6 下午01:34:09
	 * @return CourseWareAction
	 * @author hehujun
	 */
	public SbtQuestion getSbtQuestionByQuestionId(String questionId) throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 保存学习动作和学习question 
	   * @project base
	   * @package com.xcj.admin.service.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-6 下午03:31:29
	   * @return boolean
	   * @author hehujun
	 */
	public boolean saveStudyActionAndQuestion(String characterA, StudyResult studyResult) throws Exception;

	/**
	   * <b>function:</b> 通过课件编号获取json文件的内容
	   * @project base
	   * @package com.xcj.admin.service.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-9 下午04:25:39
	   * @return String
	   * @author dapeng_wu
	 */
	public String getCourseWareJsonNew(HttpServletRequest request,String courseNumber,String faultNumber)throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 通过课件编号获取json文件的内容
	   * @project base
	   * @package com.xcj.admin.service.impl.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-9 下午04:26:55
	   * @return String
	   * @author dapeng_wu
	 */
	public String getCourseWareJson(HttpServletRequest request,String courseNumber)throws Exception;

}
