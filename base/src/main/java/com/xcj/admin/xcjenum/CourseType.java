/**
 * 
 */
package com.xcj.admin.xcjenum;

/** 
 * <b>function:</b> 
 * @project base 
 * @package com.xcj.web.controller.gopage  
 * @fileName com.xcj.*
 * @createDate 2015-2-12 上午10:06:12 
 * @author hehujun 
 * @email hehujun@126.com
 */
public enum CourseType {
	
	SBT(1,"1","sbt"),
	WBT(2,"2","wbt"),
	EMU(3,"3","emu"),
	DMC(4,"4","dmc");
	
	private int index;
	private String type;
	private String typeStr;
	
	private CourseType(int index, String type, String typeStr){
		this.index = index;
		this.type = type;
		this.typeStr = typeStr;
	}
	
	public static String getType(int index) {  
        for (CourseType c : CourseType.values()) {  
            if (c.getIndex() == index) {  
                return c.type;  
            }  
        }  
        return null;  
    } 
	
	public static String getTypeStr(int index) {  
        for (CourseType c : CourseType.values()) {  
            if (c.getIndex() == index) {  
                return c.typeStr;  
            }  
        }  
        return null;  
    } 
	
    // getter setter  
    public int getIndex() {  
        return index;  
    }  
    public void setIndex(int index) {  
        this.index = index;  
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}  
	
	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

}
