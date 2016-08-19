package com.xcj.admin.service.impl.users;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.users.ClassnameDao;
import com.xcj.admin.dao.users.ScoreDao;
import com.xcj.admin.dao.users.UsersDao;
import com.xcj.admin.entity.users.Classname;
import com.xcj.admin.entity.users.Score;
import com.xcj.admin.entity.users.Users;
import com.xcj.admin.service.users.UsersService;
import com.xcj.common.page.Page;

/**
 *
  * <b>function:</b>  users
  * @package com.xcj.admin.service.impl.users.
  * @fileName com.xcj.admin.*
  * @createDate Mon Jan 19 14:27:40 CST 2015
  * @author name_**
 */
@Service("usersServiceImpl")
public class UsersServiceImpl extends BaseServiceImpl implements UsersService{

	@Resource(name ="usersDaoImpl")
	private UsersDao usersDao;
	
	@Resource(name ="classnameDaoImpl")
	private ClassnameDao classnameDao;
	
	@Resource(name ="scoreDaoImpl")
	private ScoreDao scoreDao;

   /**
    *
     * <b>function:</b>  users---获取list方法
     * @project xcjtest
     * @package com.xcj.admin.service.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:40 CST 2015
     * @return List<Users>
     * @author name_**
    */
  public List<Users> getSortList() throws Exception { 
    return usersDao.getSortList(); 
   }

   /**
    *
     * <b>function:</b>  users---根据id删除数据方法
     * @project xcjtest
     * @package com.xcj.admin.service.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:40 CST 2015
     * @return void
     * @author name_**
    */
	 public void removeById(Integer id) throws Exception {
	   usersDao.removeById(id);
	 }

	  /**
	     * <b>function:</b>  增加用户
	     * @project xcjtest
	     * @package com.xcj.admin.service.users.
	     * @fileName com.xcj.admin.*
	     * @createDate Mon Jan 19 14:27:40 CST 2015
	     * @return void
	     * @author name_**
	    */
	 public void saveUsers(Users users) throws Exception{
		 List<Users> usersList = usersDao.getUsersListAndName(users.getName());
		 if(usersList == null || usersList.size() == 0){
			 usersDao.save(users);
		 }
	 }
	 /**
	    * <b>function:</b> 获取分页数据每页显示5条
	    * @project xcjtest
	    * @package com.xcj.admin.service.impl.users  
	    * @fileName com.xcj.*** 
	    * @createDate Jan 19, 2015 4:55:02 PM
	    * @return void
	    * @author dapeng_wu
	    * @return 
	  */
	 public List<Users> getUserList(int count) throws Exception{
		 	List<Users> userList = usersDao.getSortList();//注意这个数据临时的
		    Page ps = new Page();
			ps.setPageSize(5);
			ps.setCurrentPage(count);
			ps.setTotalsCount(userList.size());
			Page<Users> p = (Page<Users>)usersDao.getByPage(ps, new Users());
			return p.getResult();
	 }
	 
	 /**
	    * <b>function:</b>根据用户id修改name
	    * @project xcjtest
	    * @package com.xcj.admin.service.impl.users  
	    * @fileName com.xcj.*** 
	    * @createDate Jan 19, 2015 4:55:02 PM
	    * @return void
	    * @author dapeng_wu
	    * @return 
	  */
	 public boolean updateUsers(Integer id,String name)throws Exception{
		 List<Users> usersList = usersDao.getUsersListAndName(name);
		 if(usersList == null || usersList.size() == 0){
			 return usersDao.updateUsersAndId(id, name);
		 }
		return false;
	 }
	 /**
	    * <b>function:</b>根据用户id修改班级
	    * @project xcjtest
	    * @package com.xcj.admin.service.impl.users  
	    * @fileName com.xcj.*** 
	    * @createDate Jan 19, 2015 4:55:02 PM
	    * @return void
	    * @author dapeng_wu
	    * @return 
	  */
	 public boolean updateUsersAndClassId(Integer id,Integer classId)throws Exception{
		 List<Classname> classnameList = classnameDao.getSortList();
		 for (Classname classname : classnameList) {
			if(classId.intValue() == classname.getId().intValue()){
				usersDao.updateUsersAndClassId(id,classId);
				return true;
			}
		}
		 return false;
	 }
	 
	 /**
	    * <b>function:</b> 
	    * @project xcjtest
	    * @package com.xcj.admin.service.impl.users  
	    * @fileName com.xcj.*** 
	    * @createDate Jan 19, 2015 6:17:13 PM
	    * @return boolean
	    * @author dapeng_wu
	  */
	 public boolean delUsersAndName(String name)throws Exception{
		 List<Score> sortList = scoreDao.getSortList();
		 List<Users> usersList = usersDao.getUsersListAndName(name);
		 if(usersList == null || usersList.size() == 0){
			 return true;
		 }
		 if(sortList == null || sortList.size() == 0){
			 return true;
		 }
		 for (Score score : sortList) {
			 if(score.getUserId().intValue() == usersList.get(0).getId().intValue()){
				 return false;
			 }
		}
		usersDao.removeById(usersList.get(0).getId());
		return true;
	 }
	 /**
	    * <b>function:</b> 保存成绩并修改平均值
	    * @project xcjtest
	    * @package com.xcj.admin.service.impl.users  
	    * @fileName com.xcj.*** 
	    * @createDate Jan 20, 2015 9:54:06 AM
	    * @return boolean
	    * @author dapeng_wu
	  */
	 public boolean saveScoreAndAvgSocre(Score score)throws Exception{
		 scoreDao.save(score);
		 List<Score> scoreList = scoreDao.getScoreListAndUsersId(score.getUserId());
		 int result=0;
		 for (Score score2 : scoreList) {
			 result += score2.getResult().intValue();
		 }
		 usersDao.updateUsersAndAvgScore(score.getUserId(),result/scoreList.size());
		 return true;
	 }
	 
	 /**
	    * <b>function:</b> 保存成绩并修改平均值（修改平均分报错）
	    * @project xcjtest
	    * @package com.xcj.admin.service.impl.users  
	    * @fileName com.xcj.*** 
	    * @createDate Jan 20, 2015 9:54:06 AM
	    * @return boolean
	    * @author dapeng_wu
	  */
	 public boolean saveScoreAndAvgSocre2(Score score)throws Exception{
		 scoreDao.save(score);
		 List<Score> scoreList = scoreDao.getScoreListAndUsersId(score.getUserId());
		 int result=0;
		 for (Score score2 : scoreList) {
			 result += score2.getResult().intValue();
		 }
		 //updateUsersAndAvgScore2是一个异常的方法
		 usersDao.updateUsersAndAvgScore2(score.getUserId(),result/scoreList.size());
		 return true;
	 }
}
