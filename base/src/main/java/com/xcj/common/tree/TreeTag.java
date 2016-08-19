package com.xcj.common.tree;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xcj.admin.entity.knowledgePoint.KnowledgePoint;
import com.xcj.admin.service.knowledgePoint.KnowledgePointService;

public class TreeTag extends TagSupport {
	private static final long serialVersionUID = -1880256695454846003L;
	
	private KnowledgePointService knowledgePointService;
	 
	private Integer treeId; // 要遍历树的id

	public Integer getTreeId() {
		return treeId;
	}

	public void setTreeId(Integer treeId) {
		this.treeId = treeId;
	}
	/**
	 * 递归遍历父节点
	 * @param treeNode
	 * @throws IOException
	 */
	public void iteratorTree(KnowledgePoint treeNode) throws IOException{
		 JspWriter out = this.pageContext.getOut();
		 out.println("<ul>");
		 if (treeNode.getKnowledgePoints() != null) {
			 //遍历子节点
	            for (KnowledgePoint index : treeNode.getKnowledgePoints()) {
	            	out.println("<li>");
	            	out.println("<span value='"+index.getId()+"'>                    ");
//	            	判断有没有孩子节点
	            	if (index.getKnowledgePoints() != null && index.getKnowledgePoints().size() > 0) {
	            		out.println("        <i class='icon-minus-sign'>");
					} else {
	            		out.println("        <i class='icon-leaf'>");
					}
	            	out.println("        </i>");
	            	out.println(index.getCode()+"_"+index.getName());
	            	out.println("</span>");
//	            	有孩子节点继续走
	                if (index.getKnowledgePoints() != null && index.getKnowledgePoints().size() > 0 ) {
	                	iteratorTree(index);
	                }
	            	out.println("</li>");
	            	
	            }
	        }
		 out.println("</ul>");
    }
     
	public int doStartTag() throws JspException {
//		获取当前运行的spring容器
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		knowledgePointService = (KnowledgePointService)ctx.getBean("knowledgePointServiceImpl");
		try {
			 List<KnowledgePoint> knowledgePoints = knowledgePointService.getKnowledgePoints(treeId);
			 if(knowledgePoints.size()>0){
			 KnowledgePoint knowledgePoint2 = knowledgePoints.get(0);
				 //最外层
				  JspWriter out = this.pageContext.getOut();
					out.println("<ul>");
		        	out.println("<li>");
		        	out.println("<span value='"+knowledgePoint2.getId()+"'>                    ");
		        	out.println("        <i class='icon-folder-open'>");
		        	out.println("        </i>");
		        	out.println(knowledgePoint2.getCode()+"_"+knowledgePoint2.getName());
		        	out.println("</span>");
		        	iteratorTree(knowledgePoint2);
		           	out.println("</li>");
		           	out.println("</ul>");
			 }
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	 public int doEndTag() throws JspException {
	        return EVAL_PAGE;

	    }
}
