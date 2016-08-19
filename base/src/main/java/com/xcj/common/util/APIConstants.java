/**
 * 
 */
package com.xcj.common.util;

/**
 * <b>function:</b>
 * 
 * @project base_frame
 * @package com.xcj.common.util
 * @fileName com.xcj.*
 * @createDate Jun 6, 2014 4:40:44 PM
 * @author su_jian
 */
public class APIConstants {
	
		//TODO 上线需要修改
		
		// 阿里云 rocket MQ start 外网
		//知识点话题
		public static final String Topic_kp_value="xcjkp";
		//学习结果话题
		public static final String Topic_total_study_result="xcj_total_study_result";
		//学习结果_职校
		public static final String Topic_total_study_result_zx="xcj_total_study_result_zx_nw";
		
		public static final String Topic_total_study_result_demo2q="xcj_total_study_result_demo2q_nw";
		//测评结果
		public static final String Topic_xcj_test_result="xcj_test_result";		
		//测评报告 
		public static final String Topic_xcj_test_report="xcj_test_report";
		public static final String Topic_demo_test_report = "demo_test_report_nw";
		
		//sbt试题
		public static final String Topic_xcj_sbt_test_score_zx="xcj_sbt_test_score_zx_nw";
				
		//知识点消费者ID
		public static final String ConsumerId_value="CID_kp_001";
		
		
		//知识点同步的生产者ID
		public static final String ProducerId_value_kp="PID_kp_001";
		//学习结果同步的生产者ID
		public static final String ProducerId_value_total_study_result="PID_tsr_001";
		//学习结果同步的生产者ID_职校
		public static final String ProducerId_value_total_study_result_zx="PID_tsr_001_zx_nw";
		public static final String ProducerId_value_xcj_sbt_test_score_zx="PID_tsr_002_zx_nw";
		
		//测评结果
		public static final String ProducerId_value_xcj_test_result="PID_test_result_001";
		//sbt测评题组
		//public static final String ProducerId_value_xcj_sbt_test_score_zx="PID_sbt_test_score_001_zx";
		//测评报告 
		public static final String ProducerId_value_xcj_test_report="PID_test_report_001";
		
		
		//用户名以及密码过期时间等信息
		public static final String AccessKey_value="A253byb4X2r3NB3m";
		public static final String SecretKey_value="kiXcEqr2VianvEkC3Lv4rfpL1H0g34";
		public static final Integer SendMsgTimeoutMillis_value=10000;
		// 阿里云 rocket MQ end
		
		/**
		// 阿里云 rocket MQ start   内网
		//知识点话题
		public static final String Topic_kp_value="xcjkp_nw";
		//知识点消费者ID
		public static final String ConsumerId_value="CID_kp_001_nw";
		//知识点同步的生产者ID
		public static final String ProducerId_value_kp="PID_kp_001_nw";
		//学习结果话题
		public static final String Topic_total_study_result="xcj_total_study_result_nw";
		//学习结果同步的生产者ID
		public static final String ProducerId_value_total_study_result="PID_tsr_001_nw";
		//测评结果
		public static final String Topic_xcj_test_result="xcj_test_result_nw";
		public static final String ProducerId_value_xcj_test_result="PID_test_result_001_nw";
		//测评报告 
		public static final String Topic_xcj_test_report="xcj_test_report_nw";
		public static final String ProducerId_value_xcj_test_report="PID_test_report_001_nw";
		//用户名以及密码过期时间等信息
		public static final String AccessKey_value="A253byb4X2r3NB3m";
		public static final String SecretKey_value="kiXcEqr2VianvEkC3Lv4rfpL1H0g34";
		public static final Integer SendMsgTimeoutMillis_value=10000;
		// 阿里云 rocket MQ end   内网
		*/
		
	/**
	 * 需要修改的URL，base项目的访问路径
	 */
	//TODO
	public static final String BASE_URL_PREFIX = "http://content.xiaochejiang.com";
	//public static final String BASE_URL_PREFIX = "http://a.xiaochejiang.com";

	/**
	 * 需要修改的URL，casbase项目的访问路径
	 */
	//public static final String CAS_BASE_URL_PREFIX = "http://10.0.0.199:8081";
	public static final String CAS_BASE_URL_PREFIX = "http://cbase.xautoservice.com";
	
	/**
	 * 需要修改的URL，cas项目的访问路径
	 */

	//public static final String CAS_URL_PREFIX = "http://10.0.0.199:8082";
	//public static final String CAS_URL_PREFIX = "http://cas.xautoservice.com";
	/**
	 * 需要修改的URL，rest接口登录常量
	 */
	public static final String CAS_URL_PREFIX_REST = "http://10.162.71.72:91";
	
	
	
	
	/**
	 * API字符窜常量
	 */
	public static final String XCJ_MESSAGE_API = "XCJ";
	/**
	 * API字符窜常量测试
	 */
	public static final String XCJ_MESSAGE_API_TESTS = "TESTS";

	/**
	 * API字符窜常量
	 */
	public static final String XCJ_MESSAGE_TEST = "XCJTEST";

	/**
	 * CAS URL
	 */

	//public static final String CAS_URL = CAS_URL_PREFIX + "/cas";
	//public static final String CAS_URL = CAS_URL_PREFIX;


	/**
	 * CASbase URL
	 */
	public static final String CAS_BASE_URL = CAS_BASE_URL_PREFIX + "/user/api/open/getXcjInfo";

	/**
	 * base修改账户是否可用 同步casbase
	 */
	public static final String CAS_UPDATE_ADMIN_URL_ISABLED = CAS_BASE_URL_PREFIX + "/user/oper/isAbled";

	/**
	 * base修改账户是否可用 同步casbase
	 */
	public static final String CAS_UPDATE_ADMIN_URL_DEL_USERS = CAS_BASE_URL_PREFIX + "/user/oper/delUser";

	/**
	 * 用户修改信息
	 */
	public static final String CAS_BASE_USER_URL = CAS_BASE_URL_PREFIX + "/page/tab1";

	/**
	 * 请求次数常量
	 */
	public static final String XCJ_REQUEST_TIMES = "times";

	/**
	 * 请求次数常量
	 */
	public static final String XCJ_REQUEST_TEST_TIMES = "testtimes";

	/**
	 * 返回状json字符窜，固定KEY errcode
	 */
	public static final String XCJ_RETURN_API_ERRCODE = "errCode";
	/**
	 * 返回状json字符窜，固定KEY errMSG
	 */
	public static final String XCJ_RETURN_API_ERRMSG = "errMsg";

	public static final String ERRCODE_FU1 = "-1";
	public static final String ERRMSG__FU1 = "系统繁忙";

	public static final String ERRCODE_SUCCESS = "0";
	public static final String ERRMSG_SUCCESS = "请求成功";

	public static final String XCJ_RETURN_PARAMNULL = "20001";
	public static final String XCJ_RETURN_PARAMNULLMSG = "parameter is null";
	public static final String XCJ_RETURN_PARAMNULL_MSG = "参数不能为空";

	public static final String ERRCODE_20004 = "20004";
	public static final String ERRMSG_20004 = "用户名和密码不正确";

	public static final String ERRCODE_RECHONGFU = "0";//此处是合法的 以前是20005
	public static final String ERRMSG_RECHONGFU = "规定时间内不能重复请求";

	public static final String ERRCODE_TOKEN_INVALIDATE = "20006";
	public static final String ERRMSG_TOKEN_INVALIDATE = "token 已经失效或者无效的token";

	public static final String ERRCODE_COURSE_NUMBER_NOT_EXISTS = "20007";
	public static final String ERRMSG_COURSE_NUMBER_NOT_EXISTS = "课程编号不存在";

	public static final String ERRCODE_TOKEN_ISTIMING = "20008";
	public static final String ERRMSG_TOKEN_ISTIMING = "token 已经失效或者无效的token(平台)";

	public static final String ERRCODE_UP_TIMES = "20009";
	public static final String ERRMSG_UP_TIMES = "今天已经达到请求的次数";
	
	public static final String ERRCODE_characterA = "20010";
	public static final String ERRMSG_characterA = "characterA格式不正确";

	public static final String ERRCODE_EMPTY_RECORD = "30004";
	public static final String ERRMSG_EMPTY_RECORD = "没有找到符合的记录";

	public static final String ERRCODE_SESSIONID_INVALIDATE = "30005";
	public static final String ERRMSG_SESSIONID_INVALIDATE = "sessionId失效";

	public static final String ERRCODE_TESTPUBLICID_INVALIDATE = "30007";
	public static final String ERRMSG_TESTPUBLICID_INVALIDATE = "testsPublicId失效";

	public static final String ERRCODE_STUDY_SAVE_RECORD = "30006";
	public static final String ERRMSG_STUDY_SAVE_RECORD = "保存失败";

	// 课程库
	public static final String ERRMSG_ADD_SUCCESS = "添加成功";

	public static final String ERRCODE_DATA_ERROR = "40001";
	public static final String ERRMSG_DATA_ERROR = "数据内容格式错误";

	public static final String ERRCODE_NUMBER_ERROR = "40002";
	public static final String ERRMSG_NUMBER_ERROR = "COURSENUMBER已存在";

	public static final String ERRCODE_TYPE_ERROR = "40003";
	public static final String ERRMSG_TYPE_ERROR = "TYPES错误";

	public static final String ERRCODE_TYPENULL_ERROR = "40004";
	public static final String ERRMSG_TYPENULL_ERROR = "TYPE类型错误或不能为空";

	public static final String ERRCODE_IDNULL_ERROR = "40005";
	public static final String ERRMSG_IDNULL_ERROR = "ID不能为空";

	public static final String ERRCODE_COURSEDELL_ERROR = "40006";
	public static final String ERRMSG_COURSEDELL_ERROR = "删除错误";

	public static final String ERRCODE_QUESTIONDELL_ERROR = "40007";
	public static final String ERRMSG_QUESTIONDELL_ERROR = "删除错误";

	public static final String ERRCODE_COURSENUMBER_ERROR = "40008";
	public static final String ERRMSG_COURSENUMBER_ERROR = "COURSENUMBER和TYPE不能为空格";

	public static final String ERRCODE_WRITETEXT_ERROR = "40009";
	public static final String ERRMSG_WRITETEXT_ERROR = "写入数据失败";

	public static final String ERRCODE_KP_ERROR = "40010";
	public static final String ERRMSG_KP_ERROR = "知识点没有关联";

	public static final String ERRCODE_KPS_ERROR = "40011";
	public static final String ERRMSG_KPS_ERROR = "节点知识点没有关联";

	public static final String ERRCODE_UPLOADSBT_ERROR = "40012";
	public static final String ERRMSG_UPLOADSBT_ERROR = "请先上传SBT课件";

	// 课程和域的关联表
	public static final String ERRCODE_DOMAINCOURSEWARE_DELETEFAILE_ERROR = "50001";
	public static final String ERRMSG_DOMAINCOURSEWARE_DELETEFAILE_ERROR = "删除失败";

	public static final String ERRCODE_DOMAINCOURSEWARE_BATCHFAILE_ERROR = "50001";
	public static final String ERRMSG_DOMAINCOURSEWARE_BATCHFAILE_ERROR = "批量插入失败";

	// 测评相关
	public static final String ERRCODE_TESTS_HISTORY_ERROR = "60001";
	public static final String ERRMSG_TESTS_HISTORY_ERROR = "历史数据为空";

	/**
	 * 参数内容或格式不正确10024
	 */
	public static final String XCJ_DATE_PARAM_ERROR = "10024";
	public static final String XCJ_DATE_PARAM_ERROR_MSG = "参数内容或格式不正确";
	
	/**
	 * 分页名称
	 */
	public static final String XCJ_RETURN_API_CURRENTPAGE="currentPage";
	public static final String XCJ_RETURN_API_TOTALSPAGE="totalsPage";
	public static final String XCJ_RETURN_API_PAGESIZE="pageSize";
	public static final String XCJ_RETURN_API_TOTALSCOUNT="totalsCount";
	
	/**
	 * 返回状json固定根data
	 */
	public static final String XCJ_RETURN_API_DATA = "data";
}
