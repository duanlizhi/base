/**
 * 
 */
package com.xcj.common.util.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.course.CourseWareAction;
import com.xcj.admin.entity.course.SbtFault;
import com.xcj.admin.entity.course.SbtQuestion;
import com.xcj.admin.entity.course.SbtScores;
import com.xcj.admin.entity.course.StudyResult;

/**
 * @author xcj
 *
 */
public class xmlUtil {
	
	
	
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
	 * 
     * <b>function:</b>读取wbtText
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public static String readWbtText(String filePath){
		String texts = ""; 
		try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
               
                while((lineTxt = bufferedReader.readLine()) != null){
                  
                    texts = texts+lineTxt;
                    
                }
                
                
                read.close();
	    }else{
	        System.out.println("找不到指定的文件");
	    }
	    } catch (Exception e) {
	        System.out.println("读取文件内容出错");
	       
	    }
		return texts;
	}
	
	
	 
	       
	 
	
	/**
	 * 
	 * 
     * <b>function:</b> 之前播放器读取xml方法
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public static JSONObject analysisXML(String url,StudyResult study,String toke,String sessionIdss,String entry){
		JSONObject data = new JSONObject();
		JSONObject chapter = new JSONObject();
		JSONObject scorej = new JSONObject();
		JSONObject menuj = new JSONObject();
		JSONObject obj = new JSONObject();
		JSONArray js = new JSONArray();
		JSONArray menujs = new JSONArray();
		JSONArray chapterjs = new JSONArray();
		try {
			SAXReader sax = new SAXReader();  
	        Document xmlDoc = sax.read(new File(url));  
	        Element root = xmlDoc.getRootElement();//根节点  
	        Element name = (Element) root.element("name");
	        Element courseNumber = (Element) root.element("courseNumber");
	        Element type = (Element) root.element("type");
	        List list = root.selectNodes("//chapter");  
	        for(int i = 0; i < list.size(); i++){  
	        	Element pro = (Element)list.get(i);
	        	chapter.put("id", pro.attributeValue("id")); 
	        	chapter.put("name", pro.attributeValue("name")); 
	        	chapter.put("url", pro.attributeValue("url")); 
	        	chapter.put("type", pro.attributeValue("type")); 
	            for(Iterator iterInner = pro.elementIterator(); iterInner.hasNext();){
	            	Element elementInner = (Element) iterInner.next();
	            	JSONObject pagej = new JSONObject();
	            	pagej.put("id", elementInner.attributeValue("id")); 
	            	pagej.put("url", elementInner.attributeValue("url")); 
	            	pagej.put("is_commit", elementInner.attributeValue("is_commit")); 
	            	pagej.put("canBook", elementInner.attributeValue("canBook")); 
	            	pagej.put("canTool", elementInner.attributeValue("canTool")); 
	            	pagej.put("canFailure", elementInner.attributeValue("canFailure")); 
	            	pagej.put("canHistory", elementInner.attributeValue("canHistory")); 
	            	chapterjs.add(pagej);
	            }
	            chapter.put("page", chapterjs);
	        }  
	        List menus = root.selectNodes("//menu");  
	        for(int m = 0; m < menus.size(); m++){  
	        	Element menu = (Element)menus.get(m);  
	        	menuj.put("id", menu.attributeValue("id")); 
	        	menuj.put("name", menu.attributeValue("name")); 
	        	menuj.put("url", menu.attributeValue("url")); 
	        	menuj.put("type", menu.attributeValue("type")); 
	            for(Iterator iterInner = menu.elementIterator(); iterInner.hasNext();){
	            	Element page = (Element) iterInner.next();
	            	JSONObject pagesj = new JSONObject();
	            	pagesj.put("id", page.attributeValue("id")); 
	            	pagesj.put("url", page.attributeValue("url")); 
	            	pagesj.put("style", page.attributeValue("style")); 
	            	pagesj.put("name", page.attributeValue("name")); 
	            	pagesj.put("is_commit", page.attributeValue("is_commit")); 
	            	menujs.add(pagesj);
	            }
	            menuj.put("page", menujs);
	        }
	       
	        data.put("chapter",chapter );
	        data.put("menu", menuj);
	        data.put("name", name.getText());
	        data.put("courseNumber", courseNumber.getText());
	        if(study!=null){
	        	if(study.getProgress()!=null	&&	!study.getProgress().equals("")){
	        		data.put("progress", study.getProgress());
	        	}else{
	        		data.put("progress", entry);
	        	}
	        	
	        }else{
        		data.put("progress", entry);
        	}
	        data.put("entry", entry);
	        data.put("type", type.getText()); 
	        data.put("token", toke); 
	        data.put("sessionId", sessionIdss); 
	        data.put("statue", 1);
	        obj.put("courseware", data);
	        
	    } catch (DocumentException e) {  
	        e.printStackTrace();  
	    }  
		return obj;
	}
	
	/**
	 * 
		    * <b>function:</b>教学
		    * @project xcjedu
		    * @package com.xcj.common.util  
		    * @fileName @param url
		    * @fileName @param study
		    * @fileName @param toke
		    * @fileName @param sessionIdss
		    * @fileName @return
		    * @createDate Oct 24, 2014 5:02:15 PM
		    * @author yy.niu
	 */
	public static JSONObject wbtThreeXML(String url,StudyResult study,String toke,String sessionIdss,String entry){
		JSONObject data = new JSONObject();
		JSONObject obj = new JSONObject();
		JSONArray menujs = new JSONArray();
		JSONArray chapterjs = new JSONArray();
		JSONArray pagejs = new JSONArray();
		try {
			SAXReader sax = new SAXReader();  
	        Document xmlDoc = sax.read(new File(url));  
	        Element root = xmlDoc.getRootElement();//根节点  
	        Element name = (Element) root.element("name");
	        Element courseNumber = (Element) root.element("courseNumber");
	        Element type = (Element) root.element("type");
	        List list = root.selectNodes("//chapter");  
	        for(int i = 0; i < list.size(); i++){  
	        	JSONObject chapter = new JSONObject();
	        	Element pro = (Element)list.get(i);
	        	chapter.put("id", pro.attributeValue("id")); 
	        	chapter.put("name", pro.attributeValue("name")); 
	        	chapter.put("url", pro.attributeValue("url")); 
	        	chapter.put("type", pro.attributeValue("type")); 
	        	chapterjs.add(chapter);
	        	
	            for(Iterator iterInner = pro.elementIterator(); iterInner.hasNext();){
	            	Element elementInner = (Element) iterInner.next();
	            	JSONObject pagej = new JSONObject();
	            	pagej.put("id", elementInner.attributeValue("id")); 
	            	pagej.put("url", elementInner.attributeValue("url")); 
	            	pagej.put("is_commit", elementInner.attributeValue("is_commit")); 
	            	pagej.put("canBook", elementInner.attributeValue("canBook")); 
	            	pagej.put("canTool", elementInner.attributeValue("canTool")); 
	            	pagej.put("canFailure", elementInner.attributeValue("canFailure")); 
	            	pagej.put("canHistory", elementInner.attributeValue("canHistory")); 
	            	pagejs.add(pagej);
	            }
	           
	        }  
	       
	        List menus = root.selectNodes("//menu");  
	        for(int m = 0; m < menus.size(); m++){  
	        	Element menu = (Element)menus.get(m); 
	        	JSONObject menuj = new JSONObject();
	        	menuj.put("id", menu.attributeValue("id")); 
	        	menuj.put("name", menu.attributeValue("name")); 
	        	menuj.put("url", menu.attributeValue("url")); 
	        	menuj.put("type", menu.attributeValue("type")); 
	        	for(Iterator iterInner = menu.elementIterator(); iterInner.hasNext();){
	            	Element page = (Element) iterInner.next();
	            	JSONObject tools = new JSONObject();
	            	tools.put("id", page.attributeValue("id")); 
	            	tools.put("url", page.attributeValue("url")); 
	            	tools.put("style", page.attributeValue("style")); 
	            	tools.put("name", page.attributeValue("name")); 
	            	tools.put("is_commit", page.attributeValue("is_commit")); 
	            	menujs.add(tools);
	            }
	        	 
	        }
	        data.put("menu", menujs);
	        data.put("page", pagejs);
	        data.put("chapter",chapterjs);
	        data.put("name", name.getText());
	        data.put("courseNumber", courseNumber.getText());
	        if(study!=null){
	        	data.put("progress", study.getProgress());
	        }else{
	        	data.put("progress", entry);
	        }
	        data.put("entry",entry);
	        data.put("type", type.getText()); 
	        data.put("token", toke); 
	        data.put("sessionId", sessionIdss); 
	        data.put("statue", 1);
	        obj.put("courseware", data);
	        
	        
	    } catch (DocumentException e) {  
	        e.printStackTrace();  
	    }
	    
		return obj;
	}
	 
	/**
	 * 
     * <b>function:</b> 读取分数
     * @project xcjedu
     * @package com.xcj.common.util  
     * @fileName @param url
     * @fileName @return
     * @createDate Jul 16, 2014 3:04:03 PM
     * @author yy.niu
	 */
	public static Map score(String url){
		Map map = new HashMap();
		try {
			SAXReader sax = new SAXReader();  
	        Document xmlDoc = sax.read(new File(url));  
	        Element root = xmlDoc.getRootElement();//根节点  
	        List scores = root.selectNodes("//score");  
	        for(int s = 0; s < scores.size(); s++){  
	        	Element score = (Element)scores.get(s); 
	            for(Iterator iterInner = score.elementIterator(); iterInner.hasNext();){
	            	Element question = (Element) iterInner.next();
	            	map.put(question.attributeValue("pageid"), question.attributeValue("rate"));
	            }
	          
	        }
	        
	    } catch (DocumentException e) {  
	        e.printStackTrace();  
	    }  
		return map;
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
	
	 
	
 
	 
	
	
	public static void main(String args[]) throws DocumentException{
		
		 String filePath = "D:\\apache-tomcat-6.0.37\\webapps\\ROOT\\data\\sbtwbt\\01653792拆装课程描述.txt";
//	      "res/";
	     readWbtText(filePath);
	}

}
