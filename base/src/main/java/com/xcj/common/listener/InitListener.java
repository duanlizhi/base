/**
 * 
 */
package com.xcj.common.listener;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.xcj.admin.service.knowledgePoint.KnowledgePointService;
import com.xcj.common.memcached.MemcachedUtil;
import com.xcj.common.service.CommonService;
import com.xcj.common.util.Constants;

/**
 * <b>function:</b> 初始化一些参数以及缓存信息
 * 
 * @project base_frame
 * @package com.xcj.common.listener
 * @fileName com.xcj.*
 * @createDate May 7, 2014 5:34:32 PM
 * @author su_jian
 */
@Component("initListener")
public class InitListener implements ServletContextAware,
		ApplicationListener<ContextRefreshedEvent> {
	
	//@Resource(name = "dictServiceImpl")
	//private DictService dictService;
	
	@Resource(name = "commonServiceImpl")
	private CommonService commonService;
	
	@Resource(name = "knowledgePointServiceImpl")
	private KnowledgePointService knowledgePointService;
	
	//@Resource(name = "dictUtil")
	//private DictUtil dictUtil;
	/** logger此处Logger为Java.Util包的任何状态都打印启动的版本以及加载日志信息 */
	private static final Logger log = Logger.getLogger(InitListener.class
			.getName());
	/** servletContext */
	private ServletContext servletContext;

	public void setServletContext(ServletContext sc) {
		this.servletContext = sc;
	}
	/**
	 * <b>function:</b> 系统启动的时候调用此方法
	 * @project base_frame
	 * @package com.xcj.common.service.impl  
	 * @fileName com.xcj.*** 
	 * @createDate May 9, 2014 10:38:24 AM
	 * @return List<Setting>
	 * @author su_jian
	 */
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		if (servletContext != null&& contextRefreshedEvent.getApplicationContext().getParent() == null) {
			String info = "I|n|i|t|i|a|l|i|z|i|n|g| |'|b|a|s|e|f|r|a|m|e|'|  "
					+ Constants.SYSTEM_VERSION;
			log.info(info.replace("|", ""));
			try {
				//此方法存放字典到缓存信息中去
			/*	HashMap map = dictService.getDictMap();
				if(map!=null&&!map.isEmpty()){
					MemcachedUtil.putMemcacheIntance(map);
				}
				HashMap<String, String> map2=dictService.getKeyValueMap();
				if(map2!=null&&!map2.isEmpty()){
					MemcachedUtil.putMemcacheIntance(map2);
				}*/
				//此方法是存放系统设置信息到缓存中
				MemcachedUtil.putMemcacheIntance(commonService.getAllSetting());
				
				//TODO  同步----同步知识点   所有知识点 start
				/**List<KnowledgePoint> list=knowledgePointService.getList(KnowledgePoint.class);
				for (int i = 0; i < list.size(); i++) { 
					KnowledgePointVO knowledgePointVO =new KnowledgePointVO();
					BeanUtil.copyProperties(list.get(i), knowledgePointVO);
					Integer pid=0;
					if(list.get(i).getKnowledgePoint()!=null){
						pid=list.get(i).getKnowledgePoint().getId();
					}
					knowledgePointVO.setPid(pid);
					knowledgePointVO.setRank(list.get(i).getRank()==null?999:list.get(i).getRank());
					knowledgePointVO.setScore(list.get(i).getScore()==null?"":list.get(i).getScore());
					knowledgePointVO.setUrl(list.get(i).getUrl()==null?"":list.get(i).getUrl());
					knowledgePointVO.setIconid(list.get(i).getIconid()==null?999:list.get(i).getIconid());
					String jsonText=JSON.toJSONString(knowledgePointVO);
					//MQProducerUtil.Send(jsonText, 1, "kplistinit", APIConstants.Topic_kp_value,APIConstants.ProducerId_value_kp);
					RabbitMQProducerUtil.sendMsgToQuery(APIConstants.Topic_kp_value, jsonText);
				}
				start--知识点同步结束*/
				
				
				//此方法测试Util类是否正常使用的时候必须注入来使用不能进行new实例化
				// System.out.println(dictUtil.getDictNameByIdFromMem(Constants.MEM_DICT_PREFIX+"1"));
			} catch (Exception e) {
				e.printStackTrace();
				log.severe("初始化错误了");
			}
		}
	}

}
