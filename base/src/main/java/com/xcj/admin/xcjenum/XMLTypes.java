/**
 * 
 */
package com.xcj.admin.xcjenum;


/** 
 * <b>function:</b> 
 * @project base 
 * @package com.xcj.admin.xcjenum  
 * @fileName com.xcj.*
 * @createDate 2015-3-23 上午11:18:38 
 * @author hehujun 
 * @email hehujun@126.com
 */
public enum XMLTypes {

	SBTFAULT(1,"sbtFault"),
	SBTCOURSEWARE(2,"sbtCourseWare"),
	WBTTESTS(3,"wbtTests"),
	WBTCOURSEWARE(4,"wbtCourseWare"),
	DMCCOURSEWARE(5,"dmc"),
	EMUFAULT(6,"emuFault"),
	EMUCOURSEWARE(7,"emuCourseWare"),
	SBTQUESTION(8,"sbtQuestion");
	
	private int index;
	private String type;
	
	private XMLTypes(int index, String type) {
		this.index = index;
		this.type = type;
	}

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
	
}
