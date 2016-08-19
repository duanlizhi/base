package com.xcj.admin.service.impl.course;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.course.WbtSbtKpDao;
import com.xcj.admin.service.course.WbtSbtKpService;

/**
 *
  * <b>function:</b>  wbt_sbt_kpwbt、sbt和知识点的中间表
  * @package com.xcj.admin.service.impl.course.
  * @fileName com.xcj.admin.*
  * @createDate Thu Feb 12 17:27:13 CST 2015
  * @author name yang.yan
 */
@Service("wbtSbtKpServiceImpl")
public class WbtSbtKpServiceImpl extends BaseServiceImpl implements WbtSbtKpService{

	@Resource(name ="wbtSbtKpDaoImpl")
	private WbtSbtKpDao wbtSbtKpDao;

}
