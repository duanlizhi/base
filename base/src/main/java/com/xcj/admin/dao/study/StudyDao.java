package com.xcj.admin.dao.study;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.xcj.admin.base.BaseDao;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.course.CourseWareAction;
import com.xcj.admin.entity.course.CourseWareParts;
import com.xcj.admin.entity.course.CourseWareTool;
import com.xcj.admin.entity.course.SbtQuestion;
import com.xcj.admin.entity.course.StudyResult;
import com.xcj.admin.entity.vo.StudyHistory;

/**
 * 
  * <b>function:</b> 
  * @project base 
  * @package com.xcj.admin.dao.study  
  * @fileName com.xcj.*
  * @createDate 2015-2-6 下午01:21:29 
  * @author hehujun 
  * @email hehujun@126.com
 */
public interface StudyDao extends BaseDao {

	/**
	 * 
	   * <b>function:</b> getCourseWares()
	   * @project base
	   * @package com.xcj.admin.dao.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-6 下午01:21:34
	   * @return List<T>
	   * @author hehujun
	 */
	public <T extends CourseWare> List<T> getCourseWares(String domineName, String type) throws DataAccessException;
	
	/**
	 * 
	   * <b>function:</b> getStudyTotalInfos()
	   * @project base
	   * @package com.xcj.admin.dao.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-6 下午01:21:38
	   * @return StudyHistory
	   * @author hehujun
	 */
	public StudyHistory getStudyTotalInfos(String userEmail, String courseNum,String faultNumber,String studyFlag) throws DataAccessException;
	
	/**
	 * 
	   * <b>function:</b> getAndSaveStudyScores()
	   * @project base
	   * @package com.xcj.admin.dao.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-8 上午09:50:31
	   * @return List<T>
	   * @author hehujun
	 */
	public List<StudyResult> getAndSaveStudyScores(List<StudyResult> results) throws DataAccessException;
	
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
	public StudyResult getStudyResultBeforeSaveActionOrAnswer(String courseNumer, String domainUserName, String userEmail, String sessionId) throws DataAccessException;
	
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
	public CourseWareAction getCourseWareActionByActionId(String actionId) throws DataAccessException;
	
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
	public CourseWareParts getCourseWarePartsByActionId(String partId) throws DataAccessException;
	
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
	public CourseWareTool getCourseWareToolByActionId(String partId) throws DataAccessException;
	
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
	public SbtQuestion getSbtQuestionByQuestionId(String questionId) throws DataAccessException;
	
}
