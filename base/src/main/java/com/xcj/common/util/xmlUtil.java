/**
 * 
 */
package com.xcj.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.course.CourseWareAction;
import com.xcj.admin.entity.course.SbtFault;
import com.xcj.admin.entity.course.SbtQuestion;
import com.xcj.admin.entity.course.SbtScores;
import com.xcj.common.util.common.StringUtil;

/**
 * @author xcj
 *
 */
public class xmlUtil {
	
	
	/**
	 * 
		 * 
	     * <b>function:</b> 读取xml文件看是哪个类型
	     * @project 
	     * @package 
	     * @fileName 
	     * @createDate 
	     * @author yy.niu
	 */
	public static JSONObject fileType(String fielUrl){
		 
		JSONObject data = new JSONObject();
		JSONObject obj = new JSONObject();
		try {
			SAXReader sax = new SAXReader();  
	        Document xmlDoc;
			xmlDoc = sax.read(new File(fielUrl));
			Element root = xmlDoc.getRootElement();//根节点  
		    Element types = (Element) root.element("types");
		    Element courseNumber = (Element) root.element("courseNumber");
		    Element faultNumber = (Element) root.element("faultNumber");
		    data.put("faultNumber", StringUtil.isNull(faultNumber));
		    data.put("types", StringUtil.isNull(types));
		    data.put("courseNumber",StringUtil.isNull(courseNumber)); 
		    obj.put("fileData", data);
		} catch (DocumentException e) {
			e.printStackTrace();
		}  
		return obj;
	}
	
	/**
	 * 
     * <b>function:</b> 读取SBT动作
     * @project base
     * @package com.xcj.common.util  
     * @fileName @return
     * @createDate February 16, 2015 3:05:04 PM
     * @author yy.niu
	 */
	    
	public static CourseWare courseWare(String url){
		CourseWare courseWare = new CourseWare();
		try {
			SAXReader sax = new SAXReader();  
	        Document xmlDoc = sax.read(new File(url));  
	        Element root = xmlDoc.getRootElement();//根节点  
	        List list = root.selectNodes("//metadata");  
	        for(int l=0;l<list.size();l++){
	        	Element pro = (Element)list.get(l);  
	            System.out.println(pro.elementText("schema"));
	            System.out.println(pro.elementText("schemaversion")); 
	        }
	        Element name = (Element) root.element("name"); 
	        Element courseNumber = (Element) root.element("courseNumber"); 
	        Element type = (Element) root.element("type"); 
	        Element types = (Element) root.element("types"); 
	        Element purpose = (Element) root.element("purpose"); 
	        Element require = (Element) root.element("require"); 
	        Element keyword = (Element) root.element("keyword"); 
	        Element language = (Element) root.element("language"); 
	        Element version = (Element) root.element("version"); 
	        Element description = (Element) root.element("description"); 
	        Element client = (Element) root.element("client"); 
	        Element entry = (Element) root.element("entry"); 
	       // StringUtil.isNummO(name);
//	        courseWare.setName();
//	        courseWare.setCourseNumber(StringUtil.isNummO(courseNumber));
//	        courseWare.setType(StringUtil.isNummO(type));
//	        courseWare.setPurpose(StringUtil.isNummO(purpose));
//	        courseWare.setRequires(StringUtil.isNummO(require));
//	        courseWare.setKeyword(StringUtil.isNummO(keyword));
//	        courseWare.setLanguage(StringUtil.isNummO(language));
//	        courseWare.setVersion(StringUtil.isNummO(version));
//	        courseWare.setDescription(StringUtil.isNummO(description));
//	        courseWare.setClient(StringUtil.isNummO(client));
//	        courseWare.setEntry(StringUtil.isNummO(entry));
	        
		} catch (DocumentException e) {
			e.printStackTrace();
		}  
		return null;
	}
	
	/**
	 * 
     * <b>function:</b> 读取SBT动作
     * @project base
     * @package com.xcj.common.util  
     * @fileName @return
     * @createDate February 16, 2015 3:05:04 PM
     * @author yy.niu
	 */
	    
	public static CourseWareAction courseWareAction(String url){
		
		try {
			SAXReader sax = new SAXReader();  
	        Document xmlDoc = sax.read(new File(url));  
	        Element root = xmlDoc.getRootElement();//根节点  
	        List list = root.selectNodes("//metadata");  
	        for(int l=0;l<list.size();l++){
	        	Element pro = (Element)list.get(l);  
	            System.out.println(pro.elementText("schema"));
	            System.out.println(pro.elementText("schemaversion")); 
	        }
	        Element name = (Element) root.element("name"); 
	        Element courseNumber = (Element) root.element("courseNumber"); 
	        Element type = (Element) root.element("type"); 
	        Element types = (Element) root.element("types"); 
	        Element purpose = (Element) root.element("purpose"); 
	        Element require = (Element) root.element("require"); 
	        Element keyword = (Element) root.element("keyword"); 
	        Element language = (Element) root.element("language"); 
	        Element version = (Element) root.element("version"); 
	        Element description = (Element) root.element("description"); 
	        Element client = (Element) root.element("client"); 
	        Element entry = (Element) root.element("entry"); 
	        CourseWare courseWare = new CourseWare();
//	        courseWare.setName(StringUtil.isNummO(name));
//	        courseWare.setCourseNumber(StringUtil.isNummO(courseNumber));
//	        courseWare.setType(StringUtil.isNummO(type));
//	        courseWare.setPurpose(StringUtil.isNummO(purpose));
//	        courseWare.setRequires(StringUtil.isNummO(require));
//	        courseWare.setKeyword(StringUtil.isNummO(keyword));
//	        courseWare.setLanguage(StringUtil.isNummO(language));
//	        courseWare.setVersion(StringUtil.isNummO(version));
//	        courseWare.setDescription(StringUtil.isNummO(description));
//	        courseWare.setClient(StringUtil.isNummO(client));
//	        courseWare.setEntry(StringUtil.isNummO(entry));
	         
	        List actions = root.selectNodes("//actions");  
	        for(int s = 0; s < actions.size();s++){  
	        	Element actionsName = (Element)actions.get(s);  
	            for(Iterator iterInner = actionsName.elementIterator(); iterInner.hasNext();){
	            	Element questionName = (Element) iterInner.next();
	            	JSONObject scoreData = new JSONObject();
	            	String actionId =  questionName.attributeValue("id");
	            	String actionDiscription = questionName.attributeValue("discription");
	            	
	            }
	           
	        }
	        
	        List parts = root.selectNodes("//parts");  
	        for(int s = 0; s < parts.size();s++){  
	        	Element scoreName = (Element)parts.get(s);  
	            for(Iterator iterInner = scoreName.elementIterator(); iterInner.hasNext();){
	            	Element partsName = (Element) iterInner.next();
	            	String parId =  partsName.attributeValue("id");
	            	System.out.println(parId); 
	            	 for(Iterator iterPart = partsName.elementIterator(); iterPart.hasNext();){
	                 	Element partStateName = (Element) iterPart.next();
	                 	String stateId =  partStateName.attributeValue("id");
	                 	String stateDiscription = partStateName.attributeValue("discription");
	                 	System.out.println(stateId+stateDiscription); 
	                 }
	            }
	           
	        }
	        
	        List tools = root.selectNodes("//tools");  
	        for(int s = 0; s < tools.size();s++){  
	        	Element toolsName = (Element)tools.get(s);  
	            for(Iterator toolInner = toolsName.elementIterator(); toolInner.hasNext();){
	            	Element toolName = (Element) toolInner.next();
	            	String toolId =  toolName.attributeValue("id");
	            	System.out.println(toolId); 
	            	 for(Iterator iterPart = toolName.elementIterator(); iterPart.hasNext();){
	                 	Element toolStateName = (Element) iterPart.next();
	                 	String stateId =  toolStateName.attributeValue("id");
	                 	String stateDiscription = toolStateName.attributeValue("discription");
	                 	System.out.println(stateId+stateDiscription); 
	                 }
	            }
	           
	        }
		} catch (DocumentException e) {
			e.printStackTrace();
		}  
		return null;
	}
	
	/**
	 * 
		    * <b>function:</b> sbt题
		    * @project xcjedu
		    * @package com.xcj.common.util  
		    * @fileName @param url
		    * @fileName @return
		    * @createDate Oct 15, 2014 3:48:57 PM
		    * @author yy.niu
	 */
	public static SbtQuestion sbtQuestion(String url){
		SbtQuestion sbtQuer = new SbtQuestion();
		try{	
			SAXReader sax = new SAXReader();  
	        Document xmlDoc = sax.read(new File(url));  
	        Element root = xmlDoc.getRootElement();//根节点  
	        
	        List  questions = root.selectNodes("//questions");  
	        for(int q = 0; q < questions.size(); q++){  
	        	Element question = (Element)questions.get(q); 
	        	 
	            for(Iterator iterInner = question.elementIterator(); iterInner.hasNext();){
	            	Element questionpro = (Element) iterInner.next();
	            	//sbtQuer.setCourseNumber(courseNumber);
	            	sbtQuer.setDescription(questionpro.getText());
	            	//sbtQuer.setFaultNumber(faultNumber);
	            	//sbtQuer.setQuestionNumber(questionpro.attributeValue("qid"));
	            }
	        }
	        
	        
		}catch(Exception e){
			e.printStackTrace();
		}
		return sbtQuer;
	}
	
	 
	/**
	 * 
     * <b>function:</b> 读取SBT故障
     * @project base
     * @package com.xcj.common.util  
     * @fileName @return
     * @createDate February 16, 2015 3:05:04 PM
     * @author yy.niu
	 */
	    
	public static SbtFault sbtFault(String url){
		
		try {
			SAXReader sax = new SAXReader();  
			Document xmlDoc;
			xmlDoc = sax.read(new File("d://SBT故障.xml"));
			Element root = xmlDoc.getRootElement();//根节点  
			List list = root.selectNodes("//metadata");  
	        for(int l=0;l<list.size();l++){
	        	Element pro = (Element)list.get(l);  
	            System.out.println(pro.elementText("schema"));
	            System.out.println(pro.elementText("schemaversion")); 
	        }
	        Element name = (Element) root.element("name"); 
	        //Element courseNumber = (Element) root.element("courseNumber"); 
	        //Element faultNumber = (Element) root.element("faultNumber"); 
	        Element type = (Element) root.element("type"); 
	        Element types = (Element) root.element("types"); 
	        
	        Element purpose = (Element) root.element("purpose"); 
	        Element require = (Element) root.element("require"); 
	        Element keyword = (Element) root.element("keyword"); 
	        Element description = (Element) root.element("description"); 
	        Element duration = (Element) root.element("duration"); 
	        Element difficulty = (Element) root.element("difficulty"); 
	        Element pass_score = (Element) root.element("pass_score"); 
	        Element kp = (Element) root.element("kp"); 
	       
	        System.out.println(name.getText());
	       // System.out.println(courseNumber.getText());
	       // System.out.println(faultNumber.getText());
	        System.out.println(type.getText());
	        System.out.println(types.getText());
	        System.out.println(purpose.getText());
	        System.out.println(require.getText());
	        System.out.println(keyword.getText());
	        System.out.println(description.getText());
	        System.out.println(duration.getText());
	        System.out.println(difficulty.getText());
	        System.out.println(pass_score.getText());
	        System.out.println(kp.getText());
	        
	        List questions = root.selectNodes("//questions");  
	        for(int s = 0; s < questions.size();s++){  
	        	Element questionsName = (Element)questions.get(s);  
	            for(Iterator iterInner = questionsName.elementIterator(); iterInner.hasNext();){
	            	Element questionName = (Element) iterInner.next();
	            	JSONObject scoreData = new JSONObject();
	            	String qid =  questionName.attributeValue("qid");
	            	String discription = questionName.attributeValue("discription");
	            	System.out.println(qid+discription); 
	            }
	           
	        }
	        
	        List score = root.selectNodes("//scores");  
	        for(int s = 0; s < score.size();s++){  
	        	Element scoreName = (Element)score.get(s);  
	            for(Iterator iterInner = scoreName.elementIterator(); iterInner.hasNext();){
	            	Element questionName = (Element) iterInner.next();
	            	JSONObject scoreData = new JSONObject();
	            	String id =  questionName.attributeValue("id");
	            	String rate = questionName.attributeValue("rate");
	            	String scourType = questionName.attributeValue("type");
	            	String scourKp = questionName.attributeValue("kp");
	            	System.out.println(id+rate+scourType+scourKp); 
	            }
	           
	        }
		} catch (DocumentException e) {
			e.printStackTrace();
		}  
		return null;
	}
	 
	
	/**
	 * 
		    * <b>function:</b> sbt分数
		    * @project xcjedu
		    * @package com.xcj.common.util  
		    * @fileName @param url
		    * @fileName @return
		    * @createDate Oct 15, 2014 3:48:57 PM
		    * @author yy.niu
	 */
	public static SbtScores sbtScores(String url){
		try {
			SAXReader sax = new SAXReader();  
	        Document xmlDoc = sax.read(new File(url));  
	        Element root = xmlDoc.getRootElement();//根节点  
	        List list = root.selectNodes("//metadata");  
	        for(int l=0;l<list.size();l++){
	        	Element pro = (Element)list.get(l);  
	            System.out.println(pro.elementText("schema"));
	            System.out.println(pro.elementText("schemaversion")); 
	        }
	        Element name = (Element) root.element("name"); 
	        Element courseNumber = (Element) root.element("courseNumber"); 
	        Element type = (Element) root.element("type"); 
	        Element types = (Element) root.element("types"); 
	        Element purpose = (Element) root.element("purpose"); 
	        Element require = (Element) root.element("require"); 
	        Element keyword = (Element) root.element("keyword"); 
	        Element language = (Element) root.element("language"); 
	        Element version = (Element) root.element("version"); 
	        Element description = (Element) root.element("description"); 
	        Element client = (Element) root.element("client"); 
	        Element entry = (Element) root.element("entry"); 
	        System.out.println(name.getText());
	        System.out.println(courseNumber.getText());
	        System.out.println(type.getText());
	        System.out.println(types.getText());
	        System.out.println(purpose.getText());
	        System.out.println(require.getText());
	        System.out.println(keyword.getText());
	        System.out.println(description.getText());
	        System.out.println(version.getText());
	        System.out.println(description.getText());
	        System.out.println(client.getText());
	        System.out.println(entry.getText());
	        
	        List actions = root.selectNodes("//actions");  
	        for(int s = 0; s < actions.size();s++){  
	        	Element actionsName = (Element)actions.get(s);  
	            for(Iterator iterInner = actionsName.elementIterator(); iterInner.hasNext();){
	            	Element questionName = (Element) iterInner.next();
	            	JSONObject scoreData = new JSONObject();
	            	String actionId =  questionName.attributeValue("id");
	            	String actionDiscription = questionName.attributeValue("discription");
	            	System.out.println(actionId+actionDiscription); 
	            }
	           
	        }
	        
	        List parts = root.selectNodes("//parts");  
	        for(int s = 0; s < parts.size();s++){  
	        	Element scoreName = (Element)parts.get(s);  
	            for(Iterator iterInner = scoreName.elementIterator(); iterInner.hasNext();){
	            	Element partsName = (Element) iterInner.next();
	            	String parId =  partsName.attributeValue("id");
	            	System.out.println(parId); 
	            	 for(Iterator iterPart = partsName.elementIterator(); iterPart.hasNext();){
	                 	Element partStateName = (Element) iterPart.next();
	                 	String stateId =  partStateName.attributeValue("id");
	                 	String stateDiscription = partStateName.attributeValue("discription");
	                 	System.out.println(stateId+stateDiscription); 
	                 }
	            }
	           
	        }
	        
	        List tools = root.selectNodes("//tools");  
	        for(int s = 0; s < tools.size();s++){  
	        	Element toolsName = (Element)tools.get(s);  
	            for(Iterator toolInner = toolsName.elementIterator(); toolInner.hasNext();){
	            	Element toolName = (Element) toolInner.next();
	            	String toolId =  toolName.attributeValue("id");
	            	System.out.println(toolId); 
	            	 for(Iterator iterPart = toolName.elementIterator(); iterPart.hasNext();){
	                 	Element toolStateName = (Element) iterPart.next();
	                 	String stateId =  toolStateName.attributeValue("id");
	                 	String stateDiscription = toolStateName.attributeValue("discription");
	                 	System.out.println(stateId+stateDiscription); 
	                 }
	            }
	           
	        }
		} catch (DocumentException e) {
			e.printStackTrace();
		}  
		return null;
	}
	
	/**
	 * 
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>读取文件返回字符串</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dd>1、读取文件返回字符串</dd></dl>  
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2016年2月26日 下午3:23:05</dd></dl> 
	  *  <dt><span class="strong">author:</span></dt>
	  *  <dd>wu_dapeng </dd>
	  * </dl> 
	 */
	public static String txtString(File file){
		 String result = "";
		 try{
			 BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
			 String s = null;
			 while((s = br.readLine())!=null){//使用readLine方法，一次读一行
				 result = result + "\n" +s;
			 }
			 br.close();    
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return result;
	}
	public static void main(String args[]) throws DocumentException{
		
		SAXReader sax = new SAXReader();  
        Document xmlDoc = sax.read(new File("d://SBT课件.xml"));  
        Element root = xmlDoc.getRootElement();//根节点  
        Element types = (Element) root.element("types"); 
        System.out.println(types.getText());
         
	}

}
