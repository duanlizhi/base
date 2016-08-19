package com.xcj.admin.entity.course;
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.xcj.admin.base.BaseEntity;
@Entity(name="CourseWareQuestion")
@Table(name="course_ware_question")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_course_ware_question")
public class CourseWareQuestion extends BaseEntity  implements java.io.Serializable{
    /**
		 * 
	     * <b>function:</b>
	     * @project base
	     * @package com.xcj.common.util  
	     * @fileName @return
	     * @createDate February 16, 2015 3:05:04 PM
	     * @author yy.niu
		 */
	private static final long serialVersionUID = -6774681584027471751L;

	private String  questionId;//问题

    private String  description;//描述

    private Integer  rank;//排序

    private java.util.Date  createDate;//创建时间

    private java.util.Date  modifyDate;//修改时间

    private Integer  courseWareId;//courseWareId id
    
    private Integer type;//提型
    
    private String kp;//知道点

    @Column(name="question_id",nullable = true, length=20)
    public String  getQuestionId(){
       return questionId;
    }

    public  void  setQuestionId(String questionId){
       this.questionId=questionId;
    }


    @Column(name="description",nullable = true, length=200)
    public String  getDescription(){
       return description;
    }

    public  void  setDescription(String description){
       this.description=description;
    }


    @Column(name="rank",nullable = false, length=10)
    public Integer  getRank(){
       return rank;
    }

    public  void  setRank(Integer rank){
       this.rank=rank;
    }


    @Column(name="create_date",nullable = false, length=19)
    public java.util.Date  getCreateDate(){
       return createDate;
    }

    public  void  setCreateDate(java.util.Date createDate){
       this.createDate=createDate;
    }


    @Column(name="modify_date",nullable = false, length=19)
    public java.util.Date  getModifyDate(){
       return modifyDate;
    }

    public  void  setModifyDate(java.util.Date modifyDate){
       this.modifyDate=modifyDate;
    }

    @Column(name="course_ware_id",nullable = false, length=10)
    public Integer getCourseWareId() {
		return courseWareId;
	}

	public void setCourseWareId(Integer courseWareId) {
		this.courseWareId = courseWareId;
	}

    @Column(name="type",nullable = false, length=10)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name="kp",nullable = false, length=255)
	public String getKp() {
		return kp;
	}

	public void setKp(String kp) {
		this.kp = kp;
	}

}
