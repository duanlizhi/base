package com.xcj.common.top;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class TopTag extends TagSupport{
	private static final Logger log = LoggerFactory
	.getLogger(TopTag.class);
 	private static final long serialVersionUID = -8067283282274416228L;
	public int doStartTag() throws JspException {
		  try {
	            JspWriter out = this.pageContext.getOut();
	            HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	            //获取1级菜单
	            out.println("<ul id='main-nav' class='main-nav nav nav-tabs nav-stacked' style='width:300px;'>                                                 ");
	            List<Meau> meauList = MeauParam.getMeauList();
	            for (Meau meau : meauList) {
	            	List<Meau> meaus = meau.getList();
	            	String str="";
	            	for (Meau meau2 : meaus) {
	            		str+=meau2.getId()+"";
	            	}
	            	String  str2="";
	            	str2=meau.getId()+"";
	            	//15.4.16判断''（dapeng）
	            	if("''".equals(meau.getUrl())){
	            		out.println("    <li>");
	            	}else{
		            	if(str.contains(request.getParameter("MeauTag"))){
			            	out.println("    <li class='active' onclick=goUrl('"+meau.getUrl()+"')>                                                                                                                          ");
			            }else if(str2.contains(request.getParameter("MeauTag"))){
			            	out.println("    <li  class='active' onclick=goUrl('"+meau.getUrl()+"')>                                                                                                                          ");
			            }else{
			            	out.println("    <li onclick=goUrl('"+meau.getUrl()+"')>   ");
			            }
	            	}
	            out.println("        <a  href='#systemSetting"+meau.getId()+"' class='nav-header collapsed' data-toggle='collapse' >                                           ");
	            out.println("            <i class='glyphicon glyphicon-cog'></i>                                                                               ");
	            out.println(meau.getName());
	            out.println("            <span class='pull-right glyphicon glyphicon-chevron-toggle'></span>                                                   ");
	            out.println("        </a>                                                                                                                      ");
	            if(str.contains(request.getParameter("MeauTag"))){
	            	out.println("        <ul  id='systemSetting"+meau.getId()+"' class='nav nav-list secondmenu collapse in' aria-expanded='true'>                   ");
	            }else{
	            	out.println("        <ul  id='systemSetting"+meau.getId()+"' class='nav nav-list secondmenu collapse' aria-expanded='true'>                   ");
	            	
	            }
	            for (Meau meau2 : meaus) {
	            	if(request.getParameter("MeauTag").equals(meau2.getId())){
	            	   out.println("   <li class='active'><a href='"+meau2.getUrl()+"'><i class='glyphicon glyphicon-th-list'></i>"+meau2.getName()+"</a></li>             ");
	            	}else{
	            		out.println("   <li><a href='"+meau2.getUrl()+"'><i class='glyphicon glyphicon-th-list'></i>"+meau2.getName()+"</a></li>             ");
	            		
	            	}
				}
	            out.println("        </ul>                                                                                                                     ");
	            out.println("    </li>                                                                                                                         ");
				}
	            out.println("</ul>                                                                                                                             ");
	        } catch(Exception e) {
	            throw new JspException(e.getMessage());
	        }

	        return SKIP_BODY;
	}

    public int doEndTag() throws JspException {
        return EVAL_PAGE;

    }

}
