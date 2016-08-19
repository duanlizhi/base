package com.xcj.api.sync;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.xcj.common.util.properties.ReadPropertiesUtil;

public class RabbitMQProducerUtil {

	/**
	 * 向MQ队列发送消息
	 * @param query_name 队列名称
	 * @param msg 消息内容
	 * @return  是否发送成功，目前只要不抛出异常就算发送成功
	 */
	public static boolean sendMsgToQuery(String query_name,String msg){
		ConnectionFactory factory = new ConnectionFactory();
	    try {
	    	factory.setHost(ReadPropertiesUtil.getProperties("rabbitmq.ip"));
	 	    factory.setUsername(ReadPropertiesUtil.getProperties("rabbitmq.username"));
	 	    factory.setPassword(ReadPropertiesUtil.getProperties("rabbitmq.password"));
	 	    Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			channel.queueDeclare(query_name, false, false, false, null);
			channel.basicPublish("", query_name, null, (msg).getBytes());
			channel.close();
			connection.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
	}
	
	
	public static void main(String[] args) {
		String msg = "{\"totalsCount\":8,\"totalsPage\":2,\"errMsg\":\"请求成功\",\"data\":[{\"id\":438,\"model\":\"XCJ\",\"modelVersion\":\"1.0\",\"name\":\"排除车窗不能工作故障-1\",\"courseNumber\":\"WBT_FCO_EL_WS_001\",\"type\":\"2\",\"purpose\":\"电气系统\",\"requires\":\"无\",\"keyword\":\"排除车窗不能工作故障-测评\",\"description\":\"无\",\"duration\":\"10\",\"passScore\":60,\"entry\":\"WBT-FCO-EL-WS-001/wbtThreeplayStart.html\",\"client\":\"PC，PAD\",\"version\":\"V0.1\",\"language\":\"中文\",\"difficulty\":1,\"category\":1,\"fileurl\":\"data/sbtwbt/34921586WBT-FCO-EL-WS-001.xml\",\"isStop\":1,\"createDate\":1432026333000,\"modifyDate\":1432026333000,\"contentId\":6,\"tempScore\":null,\"coursewareType\":\"2\"},{\"id\":437,\"model\":\"XCJ\",\"modelVersion\":\"1.0\",\"name\":\"诊断电控发动机怠速抖动故障\",\"courseNumber\":\"SBT-FCO-ES-IG-001\",\"type\":\"1\",\"purpose\":\"通过学习本课程能够对发动机怠速抖动故障进行再现，通过诊断过程培养学员针对发动机怠速抖动故障的诊断思路。\",\"requires\":\"无\",\"keyword\":\"发动机怠速抖动\",\"description\":\"无\",\"duration\":\"0\",\"passScore\":null,\"entry\":\"SBT-FCO-ES-IG-001/index.html\",\"client\":\"PC，PAD\",\"version\":\"V0.1\",\"language\":\"中文\",\"difficulty\":0,\"category\":1,\"fileurl\":\"data/sbtwbt/83542176action.xml\",\"isStop\":1,\"createDate\":1432030770000,\"modifyDate\":1432030770000,\"contentId\":8,\"tempScore\":null,\"coursewareType\":\"1\"},{\"id\":436,\"model\":\"XCJ\",\"modelVersion\":\"1.0\",\"name\":\"EVAP学习系统\",\"courseNumber\":\"RM-FCO-ES-EC-001\",\"type\":\"3\",\"purpose\":\"技术方案测试\",\"requires\":\"无\",\"keyword\":\"学习系统\",\"description\":\"无\",\"duration\":\"0\",\"passScore\":null,\"entry\":\"RM-FCO-ES-EC-001/index.html\",\"client\":\"PC\",\"version\":\"V0.1\",\"language\":\"中文\",\"difficulty\":0,\"category\":1,\"fileurl\":\"data/sbtwbt/12568479emu001.xml\",\"isStop\":1,\"createDate\":1432179153000,\"modifyDate\":1432179153000,\"contentId\":9,\"tempScore\":null,\"coursewareType\":\"3\"},{\"id\":435,\"model\":\"XCJ\",\"modelVersion\":\"1.0\",\"name\":\"车辆拆装培训\",\"courseNumber\":\"CX-FCO-EM-1ZR-001\",\"type\":\"4\",\"purpose\":\"车辆拆装培训\",\"requires\":\"无\",\"keyword\":\"技术方案一\",\"description\":\"无\",\"duration\":\"10\",\"passScore\":60,\"entry\":\"CX-FCO-EM-1ZR-001/index.html\",\"client\":\"PC\",\"version\":\"V0.1\",\"language\":\"中文\",\"difficulty\":1,\"category\":1,\"fileurl\":\"data/sbtwbt/08142657CX-FCO-EM-1ZR-001.xml\",\"isStop\":1,\"createDate\":1432187338000,\"modifyDate\":1432187338000,\"contentId\":10,\"tempScore\":null,\"coursewareType\":\"4\"},{\"id\":434,\"model\":\"XCJ\",\"modelVersion\":\"1.0\",\"name\":\"EVAP学习系统\",\"courseNumber\":\"RM_FCO_ES_EC_001\",\"type\":\"3\",\"purpose\":\"技术方案测试\",\"requires\":\"无\",\"keyword\":\"学习系统\",\"description\":\"无\",\"duration\":\"0\",\"passScore\":null,\"entry\":\"RM_FCO_ES_EC_001/index.html\",\"client\":\"PC\",\"version\":\"V0.1\",\"language\":\"中文\",\"difficulty\":0,\"category\":1,\"fileurl\":\"data/sbtwbt/83651204RM_FCO_ES_EC_001.xml\",\"isStop\":1,\"createDate\":1441880769000,\"modifyDate\":1441880769000,\"contentId\":11,\"tempScore\":null,\"coursewareType\":\"3\"}],\"pageSize\":5,\"errCode\":\"0\",\"currentPage\":1}";
		//while(true){
			//RabbitMQProducerUtil.sendMsgToQuery("xcjkp", msg);//知识点同步调用
			RabbitMQProducerUtil.sendMsgToQuery("xcj_total_study_result",msg);//学习结果同步调用
			//RabbitMQProducerUtil.sendMsgToQuery("xcj_test_result",msg);//测评结果同步调用
			//RabbitMQProducerUtil.sendMsgToQuery("xcj_test_report",msg);//测评报告
		//}
	}
	
}
