package com.xcj.admin.entity.course;
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.xcj.admin.base.BaseEntity;
@Entity(name="WbtScores")
@Table(name="wbt_scores")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_wbt_scores")
public class WbtScores extends BaseEntity  implements java.io.Serializable{
    /**
	 * 
     * <b>function:</b> 读取SBT故障
     * @project base
     * @package com.xcj.common.util  
     * @fileName @return
     * @createDate February 16, 2015 3:05:04 PM
     * @author yy.niu
	 */
	private static final long serialVersionUID = 4399978746364180500L;

	private String  questionId;//question_id

    private String  pageid;//页面id

    private String  type;//类型

    private String  rate;//比率

    private Integer  rank;//排序

    private String  rightAnswers;//正确答案

    private String  questionName;//题目名称

    private java.util.Date  createDate;//创建时间

    private java.util.Date  modifyDate;//修改时间

    private Integer  courseWareId;//课件id

    @Column(name="question_id",nullable = true, length=20)
    public String  getQuestionId(){
       return questionId;
    }

    public  void  setQuestionId(String questionId){
       this.questionId=questionId;
    }


    @Column(name="pageid",nullable = true, length=20)
    public String  getPageid(){
       return pageid;
    }

    public  void  setPageid(String pageid){
       this.pageid=pageid;
    }


    @Column(name="type",nullable = true, length=20)
    public String  getType(){
       return type;
    }

    public  void  setType(String type){
       this.type=type;
    }


    @Column(name="rate",nullable = true, length=20)
    public String  getRate(){
       return rate;
    }

    public  void  setRate(String rate){
       this.rate=rate;
    }


    @Column(name="rank",nullable = true, length=10)
    public Integer  getRank(){
       return rank;
    }

    public  void  setRank(Integer rank){
       this.rank=rank;
    }


    @Column(name="right_answers",nullable = true, length=500)
    public String  getRightAnswers(){
       return rightAnswers;
    }

    public  void  setRightAnswers(String rightAnswers){
       this.rightAnswers=rightAnswers;
    }


    @Column(name="question_name",nullable = true, length=500)
    public String  getQuestionName(){
       return questionName;
    }

    public  void  setQuestionName(String questionName){
       this.questionName=questionName;
    }


    @Column(name="create_date",nullable = true, length=19)
    public java.util.Date  getCreateDate(){
       return createDate;
    }

    public  void  setCreateDate(java.util.Date createDate){
       this.createDate=createDate;
    }


    @Column(name="modify_date",nullable = true, length=19)
    public java.util.Date  getModifyDate(){
       return modifyDate;
    }

    public  void  setModifyDate(java.util.Date modifyDate){
       this.modifyDate=modifyDate;
    }


    @Column(name="course_ware_id",nullable = true, length=10)
    public Integer  getCourseWareId(){
       return courseWareId;
    }

    public  void  setCourseWareId(Integer courseWareId){
       this.courseWareId=courseWareId;
    }

}
