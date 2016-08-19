/** 
  * <dl>
  * <dt><span class="strong">类说明:</span></dt>
  * <dd>主要是实现了什么功能</dd>
  * </dl> 
  * <dl><dt><span class="strong">逻辑说明:</span></dt>
  *  <dd>1、xx(处理逻辑是什么样子的)</dd>
  *  <dd>2、xx</dd>
  *  <dd>3、xx</dd></dl>  
  *  <dl><dt><span class="strong">创建时间:</span></dt>
  *  <dd> 2015年10月13日 下午4:37:48</dd></dl> 
  * </dl> 
  * @author su_jian
  * @see {@linkplain java.lang.String }
  * @since 1.0
  */
package com.xcj.api.sync;

import java.util.Properties;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendResult;
import com.xcj.common.util.APIConstants;

/** 
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>主要是实现了什么功能</dd>
 * </dl> 
 * <dl><dt><span class="strong">逻辑说明:</span></dt>
 *  <dd>1、xx(处理逻辑是什么样子的)</dd>
 *  <dd>2、xx</dd>
 *  <dd>3、xx</dd></dl>  
 *  <dl><dt><span class="strong">创建时间:</span></dt>
 *  <dd> 2015年7月31日 上午10:06:36</dd></dl> 
 * </dl> 
 * @author su_jian
 * @see {@linkplain java.lang.String }
 * @since 1.0
 */
public class MQProducerUtil {
	public static Producer producer_kp;//知识点
	public static Producer producer_tsr;//学习结果
	
	public static Producer producer_test_result;//测评结果
	
	public static Producer producer_test_report;//测评报告

	public static Producer producer_tsr_zx;//学习结果
	
	public static Producer producer_text_score_zx;//sbt工单
	
	public static Producer producer_demo_courseware;//课件
	
	public static Producer producer_demo_sbtfault;//sbt故障
	
	//mq开关标志
	public static boolean mqStatus = false;
	/**
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>生产者发送数据--知识点</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2015年10月13日 下午4:47:59</dd></dl> 
	  *  <dt><span class="strong">author:</span></dt>
	  *  <dd>su_jian </dd>
	  * </dl> 
	  * @param <T> 对象类型 
	  * @param entity 对象 
	  * @see {@linkplain java.lang.String }
	  * @return boolean true/false
	  * @since 1.0
	 */
	public static boolean Send(String jsonText,Integer type,String tag,String topic,String producerId){
		   Properties properties = new Properties();
	       properties.put(PropertyKeyConst.ProducerId,producerId);
	       properties.put(PropertyKeyConst.AccessKey, APIConstants.AccessKey_value);
	       properties.put(PropertyKeyConst.SecretKey, APIConstants.SecretKey_value);
	       properties.put(PropertyKeyConst.SendMsgTimeoutMillis, APIConstants.SendMsgTimeoutMillis_value);
	       if(producer_kp==null){
	    	   producer_kp = ONSFactory.createProducer(properties);
	    	   producer_kp.start();
	       }
	       Message msg = new Message(
   	            //Message Topic
	    		 topic, 
   	            //Message Tag,
   	            //可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在ONS服务器过滤        
   	            tag,
   	            //Message Body
   	            //任何二进制形式的数据，ONS不做任何干预，需要Producer与Consumer协商好一致的序列化和反序列化方式
   	            (jsonText).getBytes()
   	        );
	   	   msg.setKey("ORDERID_100");
	   	   if(mqStatus){
	   		   SendResult sendResult = producer_kp.send(msg);
	   	   }
		return true;
	} 
	
	/**
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>生产者发送数据——学习结果</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2015年10月13日 下午4:47:59</dd></dl> 
	  *  <dt><span class="strong">author:</span></dt>
	  *  <dd>su_jian </dd>
	  * </dl> 
	  * @param <T> 对象类型 
	  * @param entity 对象 
	  * @see {@linkplain java.lang.String }
	  * @return boolean true/false
	  * @since 1.0
	 */
	public static boolean SendTotalStudyResult(String jsonText,Integer type,String tag,String topic,String producerId){
		   Properties properties = new Properties();
	       properties.put(PropertyKeyConst.ProducerId,producerId);
	       properties.put(PropertyKeyConst.AccessKey, APIConstants.AccessKey_value);
	       properties.put(PropertyKeyConst.SecretKey, APIConstants.SecretKey_value);
	       properties.put(PropertyKeyConst.SendMsgTimeoutMillis, APIConstants.SendMsgTimeoutMillis_value);
	       if(producer_tsr==null){
	    	   producer_tsr = ONSFactory.createProducer(properties);
	    	   producer_tsr.start();
	       }
	       Message msg = new Message(
  	            //Message Topic
	    		 topic, 
  	            //Message Tag,
  	            //可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在ONS服务器过滤        
  	            tag,
  	            //Message Body
  	            //任何二进制形式的数据，ONS不做任何干预，需要Producer与Consumer协商好一致的序列化和反序列化方式
  	            (jsonText).getBytes()
  	        );
	   	   msg.setKey("ORDERID_1000");
	   	   if(mqStatus){
	   		   SendResult sendResult = producer_tsr.send(msg);
	   	   }
		return true;
	} 
	
	/**
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>生产者发送数据——测评结果</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2015年10月13日 下午4:47:59</dd></dl> 
	  *  <dt><span class="strong">author:</span></dt>
	  *  <dd>su_jian </dd>
	  * </dl> 
	  * @param <T> 对象类型 
	  * @param entity 对象 
	  * @see {@linkplain java.lang.String }
	  * @return boolean true/false
	  * @since 1.0
	 */
	public static boolean SendTestResult(String jsonText,Integer type,String tag,String topic,String producerId){
		   Properties properties = new Properties();
	       properties.put(PropertyKeyConst.ProducerId,producerId);
	       properties.put(PropertyKeyConst.AccessKey, APIConstants.AccessKey_value);
	       properties.put(PropertyKeyConst.SecretKey, APIConstants.SecretKey_value);
	       properties.put(PropertyKeyConst.SendMsgTimeoutMillis, APIConstants.SendMsgTimeoutMillis_value);
	       if(producer_test_result==null){
	    	   producer_test_result = ONSFactory.createProducer(properties);
	    	   producer_test_result.start();
	       }
	       Message msg = new Message(
 	            //Message Topic
	    		 topic, 
 	            //Message Tag,
 	            //可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在ONS服务器过滤        
 	            tag,
 	            //Message Body
 	            //任何二进制形式的数据，ONS不做任何干预，需要Producer与Consumer协商好一致的序列化和反序列化方式
 	            (jsonText).getBytes()
 	        );
	   	   msg.setKey("ORDERID_1001");
	   	   if(mqStatus){
	   		   SendResult sendResult = producer_test_result.send(msg);
	   	   }
		return true;
	} 
	
	/**
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>生产者发送数据——学习结果</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2015年10月13日 下午4:47:59</dd></dl> 
	  *  <dt><span class="strong">author:</span></dt>
	  *  <dd>su_jian </dd>
	  * </dl> 
	  * @param <T> 对象类型 
	  * @param entity 对象 
	  * @see {@linkplain java.lang.String }
	  * @return boolean true/false
	  * @since 1.0
	 */
	public static boolean SendTestReport(String jsonText,Integer type,String tag,String topic,String producerId){
		   Properties properties = new Properties();
	       properties.put(PropertyKeyConst.ProducerId,producerId);
	       properties.put(PropertyKeyConst.AccessKey, APIConstants.AccessKey_value);
	       properties.put(PropertyKeyConst.SecretKey, APIConstants.SecretKey_value);
	       properties.put(PropertyKeyConst.SendMsgTimeoutMillis, APIConstants.SendMsgTimeoutMillis_value);
	       if(producer_test_report==null){
	    	   producer_test_report = ONSFactory.createProducer(properties);
	    	   producer_test_report.start();
	       }
	       Message msg = new Message(
 	            //Message Topic
	    		 topic, 
 	            //Message Tag,
 	            //可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在ONS服务器过滤        
 	            tag,
 	            //Message Body
 	            //任何二进制形式的数据，ONS不做任何干预，需要Producer与Consumer协商好一致的序列化和反序列化方式
 	            (jsonText).getBytes()
 	        );
	   	   msg.setKey("ORDERID_1000");
	   	  //SendResult sendResult = producer_test_report.send(msg);
	   	  // System.out.println(":getMessageId:"+sendResult.getMessageId());
		return true;
	} 
	
	
	/**
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>生产者发送数据——学习结果_zx</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2015年10月13日 下午4:47:59</dd></dl> 
	  *  <dt><span class="strong">author:</span></dt>
	  *  <dd>su_jian </dd>
	  * </dl> 
	  * @param <T> 对象类型 
	  * @param entity 对象 
	  * @see {@linkplain java.lang.String }
	  * @return boolean true/false
	  * @since 1.0
	 */
	public static boolean SendTotalStudyResultZx(String jsonText,Integer type,String tag,String topic,String producerId){
		Properties properties = new Properties();
		properties.put(PropertyKeyConst.ProducerId,producerId);
		properties.put(PropertyKeyConst.AccessKey, APIConstants.AccessKey_value);
		properties.put(PropertyKeyConst.SecretKey, APIConstants.SecretKey_value);
		properties.put(PropertyKeyConst.SendMsgTimeoutMillis, APIConstants.SendMsgTimeoutMillis_value);
		if(producer_tsr_zx==null){
			producer_tsr_zx = ONSFactory.createProducer(properties);
			producer_tsr_zx.start();
		}
		Message msg = new Message(
				//Message Topic
				topic, 
				//Message Tag,
				//可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在ONS服务器过滤        
				tag,
				//Message Body
				//任何二进制形式的数据，ONS不做任何干预，需要Producer与Consumer协商好一致的序列化和反序列化方式
				(jsonText).getBytes()
				);
		msg.setKey("ORDERID_1000");
   	    if(mqStatus){
   	    	SendResult sendResult = producer_tsr_zx.send(msg);
   	    }
		return true;
	} 
	
	
	public static boolean SendSbtTestScoreZx(String jsonText,Integer type,String tag,String topic,String producerId){
		Properties properties = new Properties();
		properties.put(PropertyKeyConst.ProducerId,producerId);
		properties.put(PropertyKeyConst.AccessKey, APIConstants.AccessKey_value);
		properties.put(PropertyKeyConst.SecretKey, APIConstants.SecretKey_value);
		properties.put(PropertyKeyConst.SendMsgTimeoutMillis, APIConstants.SendMsgTimeoutMillis_value);
		if(producer_text_score_zx==null){
			producer_text_score_zx = ONSFactory.createProducer(properties);
			producer_text_score_zx.start();
		}
		Message msg = new Message(
				//Message Topic
				topic, 
				//Message Tag,
				//可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在ONS服务器过滤        
				tag,
				//Message Body
				//任何二进制形式的数据，ONS不做任何干预，需要Producer与Consumer协商好一致的序列化和反序列化方式
				(jsonText).getBytes()
				);
		msg.setKey("ORDERID_1000");
   	    if(mqStatus){
   	    	SendResult sendResult = producer_text_score_zx.send(msg);
   	    }
		return true;
	} 
	
	public static boolean SendCourseware(String jsonText,Integer type,String tag,String topic,String producerId){
		Properties properties = new Properties();
		properties.put(PropertyKeyConst.ProducerId,producerId);
		properties.put(PropertyKeyConst.AccessKey, APIConstants.AccessKey_value);
		properties.put(PropertyKeyConst.SecretKey, APIConstants.SecretKey_value);
		properties.put(PropertyKeyConst.SendMsgTimeoutMillis, APIConstants.SendMsgTimeoutMillis_value);
		if(producer_demo_courseware==null){
			producer_demo_courseware = ONSFactory.createProducer(properties);
			producer_demo_courseware.start();
		}
		Message msg = new Message(
				//Message Topic
				topic, 
				//Message Tag,
				//可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在ONS服务器过滤        
				tag,
				//Message Body
				//任何二进制形式的数据，ONS不做任何干预，需要Producer与Consumer协商好一致的序列化和反序列化方式
				(jsonText).getBytes()
				);
		msg.setKey("ORDERID_1000");
		if(mqStatus){
			SendResult sendResult = producer_demo_courseware.send(msg);
		}
		return true;
	} 
	
	
	public static boolean SendSbtFault(String jsonText,Integer type,String tag,String topic,String producerId){
		Properties properties = new Properties();
		properties.put(PropertyKeyConst.ProducerId,producerId);
		properties.put(PropertyKeyConst.AccessKey, APIConstants.AccessKey_value);
		properties.put(PropertyKeyConst.SecretKey, APIConstants.SecretKey_value);
		properties.put(PropertyKeyConst.SendMsgTimeoutMillis, APIConstants.SendMsgTimeoutMillis_value);
		if(producer_demo_sbtfault==null){
			producer_demo_sbtfault = ONSFactory.createProducer(properties);
			producer_demo_sbtfault.start();
		}
		Message msg = new Message(
				//Message Topic
				topic, 
				//Message Tag,
				//可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在ONS服务器过滤        
				tag,
				//Message Body
				//任何二进制形式的数据，ONS不做任何干预，需要Producer与Consumer协商好一致的序列化和反序列化方式
				(jsonText).getBytes()
				);
		msg.setKey("ORDERID_1000");
		if(mqStatus){
			SendResult sendResult = producer_demo_sbtfault.send(msg);
		}
		return true;
	} 
	
	public static void main(String[] args) {
		//MQProducerUtil.Send("xxxxx", 1, "kp", "xcjkp",APIConstants.ProducerId_value_kp);
	}
	

}
