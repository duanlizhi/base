/**
 * 
 */
package com.xcj.api.sync;

/**
 * <b>function:</b>
 * 
 * @project base_frame
 * @package com.xcj.common.util
 * @fileName com.xcj.*
 * @createDate Jun 6, 2014 4:40:44 PM
 * @author su_jian
 */
public class MQConstants {
	
	//TODO 上线需要修改

		// 阿里云 rocket MQ start 外网
		//知识点话题
		public static final String Topic_kp_value="xcjkp";
		//知识点消费者ID
		public static final String ConsumerId_value="CID_kp_001_zx_nw";
		//知识点同步的生产者ID
		public static final String ProducerId_value_kp="PID_kp_001_zx";
		
		//学习结果话题 
		public static final String Topic_total_study_result="xcj_total_study_result";
		
		public static final String Topic_total_study_result_zx="xcj_total_study_result_zx_nw";

		//成绩单
		public static final String Topic_xcj_sbt_test_score_zx = "xcj_sbt_test_score_zx_nw";
		
		
		//学习结果同步的生产者ID
		public static final String ProducerId_value_total_study_result="PID_tsr_001";
		//测评结果
		public static final String Topic_xcj_test_result="xcj_test_result";
		public static final String ProducerId_value_xcj_test_result="PID_test_result_001";
		//测评报告 
		public static final String Topic_xcj_test_report="xcj_test_report";
		public static final String ProducerId_value_xcj_test_report="PID_test_report_001";
		//用户名以及密码过期时间等信息
		public static final String AccessKey_value="A253byb4X2r3NB3m";
		public static final String SecretKey_value="kiXcEqr2VianvEkC3Lv4rfpL1H0g34";
		public static final Integer SendMsgTimeoutMillis_value=1000000;
		// 阿里云 rocket MQ end

		
		//课件
		public static final String Topic_demo_course_ware="demo_course_ware_nw";
		
		//课件pid
		public static final String ProducerId_demo_course_ware="PID_demo_courseware_001";
		
		//sbt故障
		public static final String Topic_demo_sbt_fault="demo_sbt_fault_nw";
		
		//test_posted故障
		public static final String Topic_demo_tests_posted="demo_tests_posted_nw";
		
		//knowledge_point故障
		public static final String Topic_demo_knowledge_point="demo_knowledge_point_nw";
		
		//sbt故障pid
		public static final String ProducerId_demo_sbt_fault="PID_demo_sbtfault_001";
}
