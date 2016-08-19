/**
 * 
 */
package com.xcj.common.util.bean;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

/** 
 * <b>function:</b> 实体类之间拷贝的方法
 * @project xcjpar 
 * @package com.xcj.common.util.bean  
 * @fileName com.xcj.*
 * @createDate Jun 16, 2014 9:16:52 AM 
 * @author su_jian 
 */
public class BeanUtil extends org.springframework.beans.BeanUtils{
	public static void copyProperties(Object source, Object target) throws BeansException {  
	    Assert.notNull(source, "Source must not be null");  
	    Assert.notNull(target, "Target must not be null");  
	    Class<?> actualEditable = target.getClass();  
	    PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);  
	    for (PropertyDescriptor targetPd : targetPds) {  
	      if (targetPd.getWriteMethod() != null) {  
	        PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());  
	        if (sourcePd != null && sourcePd.getReadMethod() != null) {  
	          try {  
	            Method readMethod = sourcePd.getReadMethod();  
	            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {  
	              readMethod.setAccessible(true);  
	            }  
	            Object value = readMethod.invoke(source);  
	            // 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等  
	            if (value != null) {  
	              Method writeMethod = targetPd.getWriteMethod();  
	              if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {  
	                writeMethod.setAccessible(true);  
	              }  
	              writeMethod.invoke(target, value);  
	            }  
	          } catch (Throwable ex) {  
	            throw new FatalBeanException("Could not copy properties from source to target", ex);  
	          }  
	        }  
	      }  
	    }  
	  }  
	public static void main(String[] args) {
		/*SourceEntity source =new SourceEntity();
		source.setIdCard(123l);
		source.setName("苏建");
		source.setFullName("你");
		source.setCreateDate(DateUtil.getCurrentTimeByDate());
		SourceEntity target =new SourceEntity();
		target.setIdCard(123l);
		target.setName("回来");
		target.setFullName("我");
		target.setCreateDate(DateUtil.getCurrentTimeByDate());
		target.setD1(123d);
		target.setId(123123);
		BeanUtil.copyProperties(source, target);
		System.out.println(target.getFullName());*/
	}
}
