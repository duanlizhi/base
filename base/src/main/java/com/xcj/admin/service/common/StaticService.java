/**
 * 
 */
package com.xcj.admin.service.common;

import com.xcj.admin.base.BaseService;

/** 
 * <b>function:</b> 
 * @project base 
 * @package com.xcj.admin.service.common  
 * @fileName com.xcj.*
 * @createDate Oct 3, 2014 12:37:04 PM 
 * @author su_jian 
 */
public interface StaticService extends BaseService{

	 /**
	   * <b>function:</b> 生成单个文章静态
	   * @project base
	   * @package com.xcj.admin.service.common  
	   * @fileName com.xcj.*** 
	   * @createDate Oct 5, 2014 11:03:55 AM
	   * @return void
	   * @author su_jian
	   */
	void buidSingleArticle(Integer id);

	 /**
	   * <b>function:</b> 静态化所有的文章内容
	   * @project base
	   * @package com.xcj.admin.service.common  
	   * @fileName com.xcj.*** 
	   * @createDate Oct 6, 2014 12:17:11 PM
	   * @return int
	   * @author su_jian
	   */
	int buidAllArticle(String acid, String stime, String etime);

	 /**
	   * <b>function:</b> 
	   * @project base
	   * @package com.xcj.admin.service.common  
	   * @fileName com.xcj.*** 
	   * @createDate Oct 10, 2014 2:48:31 PM
	   * @return void
	   * @author su_jian
	   */
	void buidQRcode(Integer id);
	
}
