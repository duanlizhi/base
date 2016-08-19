/**
 * 
 */
package com.xcj.common.util.common;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/** 
 * <b>function:</b> 
 * @project base 
 * @package com.xcj.common.util  
 * @fileName com.xcj.*
 * @createDate Sep 11, 2014 4:01:02 PM 
 * @author su_jian 
 */
public class FreeMarkertUtil {
	private static  Configuration config = new Configuration();   
	
	
	
	public static void processTemplate(String templateName, Map<?,?> root, Writer out){  
        try{  
            //获得模板  
            Template template=config.getTemplate(templateName,"utf-8");  
            //生成文件（这里是我们是生成html）  
            template.process(root, out);     
            out.flush();     
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (TemplateException e) {  
            e.printStackTrace();  
        }finally{  
             try {  
                out.close();  
                out=null;  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}
