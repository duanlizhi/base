package com.xcj.admin.controller.knowledgePoint;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xcj.admin.base.BaseController;
import com.xcj.admin.entity.course.CourseWareKp;
import com.xcj.admin.entity.course.WbtSbtKp;
import com.xcj.admin.entity.knowledgePoint.KnowledgePoint;
import com.xcj.admin.entity.knowledgePoint.KnowledgePointVO;
import com.xcj.admin.service.course.CourseWareKpService;
import com.xcj.admin.service.course.WbtSbtKpService;
import com.xcj.admin.service.knowledgePoint.KnowledgePointService;
import com.xcj.api.sync.RabbitMQProducerUtil;
import com.xcj.common.util.APIConstants;
import com.xcj.common.util.bean.BeanUtil;
import com.xcj.common.util.common.DateUtil;

/**
 * <b>function:</b> knowledge_point知识点树
 * 
 * @package com.xcj.admin.controller.knowledgePoint.
 * @fileName com.xcj.admin.*
 * @createDate Mon Feb 09 18:29:05 CST 2015
 * @author name yang.yan
 */
@Controller("knowledgePointController")
@RequestMapping("/admin/knowledgePoint")
public class KnowledgePointController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(KnowledgePointController.class);
	@Resource(name = "knowledgePointServiceImpl")
	private KnowledgePointService knowledgePointService;
	@Resource(name = "wbtSbtKpServiceImpl")
	private  WbtSbtKpService wbtSbtKpServiceImpl;
	@Resource(name = "courseWareKpServiceImpl")
	private CourseWareKpService courseWareKpService;

	/**
	 * 
	 * <b>function:</b> knowledge_point知识点树:分页获取数据的方法
	 * 
	 * @project base
	 * @package com.xcj.admin.controller.knowledgePoint.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 09 18:29:05 CST 2015
	 * @return List<KnowledgePoint>
	 * @author name yang.yan
	 **/
	@RequestMapping(value = "/knowledgePointList")
	public String knowledgePointList(Model model, Integer id) {
		try {
			KnowledgePoint knowledgePoint = new KnowledgePoint();
			// 获取1级菜单
			List<KnowledgePoint> knowledgePointList = knowledgePointService
					.findFirstGrade();
			if (id != null) {
				knowledgePoint = knowledgePointService.getById(
						KnowledgePoint.class, id);
			} else {
				knowledgePoint = knowledgePointService.getFirst();
			}
			//保存根节点
			
			KnowledgePoint knowledgePointRoot = knowledgePointService.savefindRoot();
			model.addAttribute("knowledgePoint", knowledgePoint);
			model.addAttribute("knowledgePointRoot", knowledgePointRoot);
			model.addAttribute("knowledgePointList", knowledgePointList);
		} catch (Exception e) {
			log.error("知识点树:获取分页对象出错：" + e.getMessage());
		}
		return "/admin/knowledgePoint/knowledgePointList_jsp";
	}

	/**
	 * 
	 * <b>function:</b> *knowledge_point知识点树:保存的方法
	 * 
	 * @project base
	 * @package com.xcj.admin.controller.knowledgePoint.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 09 18:29:05 CST 2015
	 * @return String
	 * @author name yang.yan
	 **/
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Validated KnowledgePoint knowledgePoint, Model model,HttpServletRequest request) {
		String parentId = request.getParameter("hiddenKnowledge");
		try {
			knowledgePoint.setGrade(0);
			knowledgePoint.setCreateDate(DateUtil.getCurrentTimeByDate());
			knowledgePoint.setModifyDate(DateUtil.getCurrentTimeByDate());
			knowledgePointService.save(knowledgePoint);
			
			//TODO  同步----同步知识点   保存  start
			KnowledgePointVO knowledgePointVO =new KnowledgePointVO();
			BeanUtil.copyProperties(knowledgePoint, knowledgePointVO);
			knowledgePointVO.setPid(knowledgePoint.getKnowledgePoint().getId());
			knowledgePointVO.setRank(knowledgePoint.getRank()==null?999:knowledgePoint.getRank());
			knowledgePointVO.setScore(knowledgePoint.getScore()==null?"":knowledgePoint.getScore());
			knowledgePointVO.setUrl(knowledgePoint.getUrl()==null?"":knowledgePoint.getUrl());
			knowledgePointVO.setIconid(knowledgePoint.getIconid()==null?999:knowledgePoint.getIconid());
			//MQProducerUtil.Send(JSON.toJSONString(knowledgePointVO), 1, "kpsave", APIConstants.Topic_kp_value,APIConstants.ProducerId_value_kp);
			RabbitMQProducerUtil.sendMsgToQuery(APIConstants.Topic_kp_value, JSON.toJSONString(knowledgePointVO));
			// 同步知识点end
		} catch (Exception e) {
			log.error("知识点树:保存对象出错：" + e.getMessage());

		}
		return "redirect:/admin/knowledgePoint/knowledgePointList?id="+parentId+"_jsp";
	}


	/**
	 * 
	 * <b>function:</b> knowledge_point知识点树:更新初始化的方法
	 * 
	 * @project base
	 * @package com.xcj.admin.controller.knowledgePoint.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 09 18:29:06 CST 2015
	 * @return String
	 * @author name yang.yan
	 */
	@ResponseBody
	@RequestMapping(value = "/updateById", method = RequestMethod.GET)
	public String updateById( Integer id, Model model) {
		String str=null;
		
		try {
			// 根据id获取当前标签
			KnowledgePoint knowledgePoint		= knowledgePointService.getById(
					KnowledgePoint.class, id);
			str=knowledgePoint.getId()+","+knowledgePoint.getName()+","+knowledgePoint.getCode();
		} catch (Exception e) {
			log.error("知识点树:更新对象初始化对象出错：" + e.getMessage());
		}
		return str;
	}

	/**
	 * 
	 * <b>function:</b> knowledge_point知识点树:更新数据的方法
	 * 
	 * @project base
	 * @package com.xcj.admin.controller.knowledgePoint.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 09 18:29:06 CST 2015
	 * @return KnowledgePoint
	 * @author name yang.yan
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Validated KnowledgePoint knowledgePoint,String editKpCode,Model model,HttpServletRequest request) {
		String parentId = request.getParameter("hiddenKnowledge");
		KnowledgePoint oldknowledgePoint = new KnowledgePoint();
		try {
			oldknowledgePoint = knowledgePointService.getById(
					KnowledgePoint.class, knowledgePoint.getId());
			oldknowledgePoint.setName(knowledgePoint.getName());
			oldknowledgePoint.setCode(editKpCode);
			oldknowledgePoint.setCreateDate(oldknowledgePoint.getCreateDate());
			knowledgePointService.update(oldknowledgePoint);
			//TODO  同步----同步知识点   修改  start
			KnowledgePointVO knowledgePointVO =new KnowledgePointVO();
			BeanUtil.copyProperties(oldknowledgePoint, knowledgePointVO);
			knowledgePointVO.setPid(oldknowledgePoint.getKnowledgePoint().getId());
			knowledgePointVO.setRank(oldknowledgePoint.getRank()==null?999:oldknowledgePoint.getRank());
			knowledgePointVO.setScore(oldknowledgePoint.getScore()==null?"":oldknowledgePoint.getScore());
			knowledgePointVO.setUrl(oldknowledgePoint.getUrl()==null?"":oldknowledgePoint.getUrl());
			knowledgePointVO.setIconid(oldknowledgePoint.getIconid()==null?999:oldknowledgePoint.getIconid());
			//MQProducerUtil.Send(JSON.toJSONString(knowledgePointVO), 1, "kpupdate", APIConstants.Topic_kp_value,APIConstants.ProducerId_value_kp);
			RabbitMQProducerUtil.sendMsgToQuery(APIConstants.Topic_kp_value, JSON.toJSONString(knowledgePointVO));
			// 同步知识点end
		} catch (Exception e) {
			log.error("知识点树:更新对象出错：" + e.getMessage());
		}
		return "redirect:/admin/knowledgePoint/knowledgePointList?id="+parentId+"_jsp";
	}

	/**
	 * 
	 * <b>function:</b> knowledge_point知识点树---删除单条数据的方法
	 * 
	 * @project base
	 * @package com.xcj.admin.controller.knowledgePoint.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 09 18:29:07 CST 2015
	 * @return String
	 * @author name yang.yan
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Integer id,Integer pid) {
		String str="";
		try {
//			知识点下面是否有课件和sbt故障
			List<WbtSbtKp> sbtFaultKpList = wbtSbtKpServiceImpl.findByProperty(WbtSbtKp.class, "kpId", id);
			List<CourseWareKp> courseWareKpList = courseWareKpService.findByProperty(CourseWareKp.class,"kpId", id);
			
//			如果下面有数据，则不让删除
			if(sbtFaultKpList.size()>0||courseWareKpList.size()>0){
				str="亲，知识点下面有课件，不能删除";
				return str;
			}
			
//			如果自身下面有孩子节点，则不可删除
			KnowledgePoint knowledgePoint = knowledgePointService.getById(KnowledgePoint.class, id);
			if(knowledgePoint.getKnowledgePoints().size()>0){
				str="亲，知识点下面有孩子节点，不能删除";
				return str;
			}
			knowledgePointService.removeById(id); 
			try {
//				当此节点为父节点时，删除后，让其跳转到第一个页面
				KnowledgePoint knowledgePoint2 = knowledgePointService.getById(KnowledgePoint.class, pid);
			} catch (Exception e) {
			 	str="0";
			}
		} catch (Exception e) {
			log.error("知识点树:删除对象出错：" + e.getMessage());
		}
		return str;
	}

	/**
	 * 递归算法解析成树形结构可以做成json格式
	 * 
	 * @param cid
	 * @return
	 * @author  
	 */
	public KnowledgePoint recursiveTree(int cid) {
		// 根据cid获取节点对象 
		KnowledgePoint node = knowledgePointService.getById(KnowledgePoint.class,cid);
		// 查询cid下的所有子节点 
		List<KnowledgePoint> childTreeNodes;
		try {
			childTreeNodes = knowledgePointService.getKnowledgePoints(node.getId());
			// 遍历子节点
			for (KnowledgePoint child : childTreeNodes) {
				KnowledgePoint n = recursiveTree(child.getId()); // 递归
				node.getKnowledgePoints().add(n);
			}
		} catch (Exception e) {
			log.error("递归算法解析成树形结构可以做成json格式出错：" + e.getMessage());
		}
		return node;
	}
	
	/**
	   * <b>function:</b> 验证知识点编号是否唯一
	   * @project base
	   * @package com.xcj.admin.controller.knowledgePoint  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-4-1 上午09:53:20
	   * @return String
	   * @author dapeng_wu
	 */
	@ResponseBody
	@RequestMapping(value = "/validateKpCode")
	public String validateKpCode(String code){
		try {
			List<KnowledgePoint> listByCode = knowledgePointService.getListByCode(code);
			if(listByCode != null && listByCode.size()>0){
				return "0";
			}
		} catch (Exception e) {
			log.error("验证知识点编号是否唯一出错：" + e.getMessage());
		}
		return "1";
	}
}
