package com.xcj.common.util;

public class Constants {
	
	public static final String TRAINZONE_DOAMIN_NAME = "trainzone";
	public static final String SCHOOL_DOAMIN_NAME = "school";
	public static final String DEMO2Q_DOAMIN_NAME = "demo2q";

	public static final String PROXYVALIDATE = "http://cas.xautoservice.com/proxyValidate";

	public static final String TOKEN_URL = APIConstants.BASE_URL_PREFIX + "/api/oper/base/getToken";
	public static final String TOKEN_URL_TSP = APIConstants.BASE_URL_PREFIX + "/api/oper/base/getTsp";

	public static final String TOKEN_TESTS_URL = APIConstants.BASE_URL_PREFIX + "/api/oper/tests/getToken";
	public static final String TOKEN_TESTS_URL_TSP = APIConstants.BASE_URL_PREFIX + "/api/oper/tests/getTsp";

	public static final String MEMCACHE_KEY_PREFIX = "BASEFRAME";

	public static final String XCJ_PT_NAME="contentadmin";
	
	/**
	 * 路径分隔符
	 */
	public static final String SPT = "/";

	/**
	 * HTTP POST请求
	 */
	public static final String POST = "POST";
	/**
	 * HTTP GET请求
	 */
	public static final String GET = "GET";

	/**
	 * 系统版本
	 */
	public static final String SYSTEM_VERSION = "1.0.1";
	/**
	 * app-config.xml文件路径
	 */
	public static final String APP_XML_PATH = "/conf/app-config.xml";
	/**
	 * 字典数据在缓存中的前缀 ，取数据的时候记得加此前缀
	 */
	public static final String MEM_DICT_PREFIX = "DICT_";

	/**
	 * 字典数据在缓存中的前缀 ，取数据的时候记得加此前缀
	 */
	public static final String PARTNER_PASSWORD = "111111";

	/**
	 * 缓存企业列表常量
	 */
	public static final String MEM_PAR_PREFIX = "XPAR_PARTNERLIST";

	/**
	 * 缓存区域列表常量
	 */
	public static final String MEM_AREA_PREFIX = "AREA_FOURSAREALIST";

	/**
	 * 缓存4s店列表常量
	 */
	public static final String MEM_SHOP_PREFIX = "AREA_FOURSSHOPLIST";

	/**
	 * cookie的名字
	 */
	public static final String XCJ_HT_COOKIE_NAME = "xhtusername";

	public static final String XCJ_COOKIE_NAME = "xusername";

	/**
	 * cookie的名字
	 */
	public static final String XCJ_COOKIE_PATH = "/";

	/**
	 * cookie的名字
	 */
	public static final String XCJ_COOKIE_DOAMIN = "";

	/**
	 * cookie的名字
	 */
	public static final Integer XCJ_COOKIE_TIME = 3600;
	/**
	 * 返回状json固定根errcode
	 */
	public static final String XCJ_RETURN_API_ERRCODE = "errCode";
	/**
	 * 返回状json固定根errmsg
	 */
	public static final String XCJ_RETURN_API_ERRMSG = "errMsg";
	/**
	 * 返回状json固定根data
	 */
	public static final String XCJ_RETURN_API_DATA = "data";
	/**
	 * 返回状json登陆成功
	 */
	public static final String XCJ_RETURN_API_LOGINSUCCESS = "登陆成功";
	/**
	 * 返回状json登陆失败
	 */
	public static final String XCJ_RETURN_API_LOGINFAILURES = "登陆失败";

	/**
	 * 返回状态码0成功
	 */
	public static final String XCJ_RETURN_SUCCESS = "0";

	/**
	 * 缓存信息
	 */
	public static final String XCJ_MEMCACHED_CONSTANTS = "content_";

	/**
	 * 缓存信息
	 */
	public static final String XCJ_MEMCACHED_CONSTANTS_STUDY= "content_study_";
	
	
	/**
	 * 学习token信息
	 */
	public static final String XCJ_MEMCACHED_CONSTANTS_STUDY_TOKEN = "content_tests_study_Token";
	/**
	 * 测评token信息
	 */
	public static final String XCJ_MEMCACHED_CONSTANTS_STUDY_SESSIONID = "content_tests_study_session";
	
	
	
	/**
	 * 缓存信息
	 */
	public static final String XCJ_MEMCACHED_CONSTANTS_TEST = "content_tests_";

	/**
	 * 测评token信息
	 */
	public static final String XCJ_MEMCACHED_CONSTANTS_TEST_TOKEN = "content_tests_testToken";
	
	/**
	 * 测评sessonid信息
	 */
	public static final String XCJ_MEMCACHED_CONSTANTS_TEST_SESSION = "content_tests_testSessionId";
	/**
	 * 测评sessonid信息
	 */
	public static final String XCJ_MEMCACHED_CONSTANTS_TEST_PUBLIC = "content_tests_testPublicId";
	
	/**
	 * 测评开始时间
	 */
	public static final String XCJ_MEMCACHED_CONSTANTS_TEST_STARTTIME = "content_tests_startTime";

	/**
	 * 用户信息
	 */
	public static final String XCJ_MEMCACHED_USER_NAME = "user_name_";

	/**
	 * 缓存信息
	 */
	public static final Integer XCJ_MEMCACHED_TOKEN_TIMING = 432000;
	/**
	 * 缓存信息用户名登陆后的答题的有效时间
	 */
	public static final Integer XCJ_MEMCACHED_TOKEN_LOGIN_TIMING = 432000;
	/**
	 * 考试次数缓存信息
	 */
	public static final Integer XCJ_MEMCACHED_COURSE_COUNT_TIMING = 86400;

	// 缓存过期时间
	public static final Integer XCJ_MEMCACHED_TOKEN_TIMING_TIMES = 432000;

	// 缓存过期时间
	public static final Integer XCJ_MEMCACHED_TOKEN_TIMING_TEST_TIMES = 432000;

	/**
	 * 组卷缓存信息
	 */
	public static final String XCJ_MEMCACHED_CONSTANTS_COMBINE = "content_combine_";

	/**
	 * 组卷token信息
	 */
	public static final String XCJ_MEMCACHED_CONSTANTS_COMBINE_TOKEN = "content_combine_combineToken";

	/**
	 * 不可实例化
	 */
	private Constants() {
	}
}
