package com.xcj.admin.entity.study;
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.xcj.admin.base.BaseEntity;
@Entity(name="StudyAction")
@Table(name="study_action")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_study_action")
public class StudyAction extends BaseEntity  implements java.io.Serializable{
   private static final long serialVersionUID = 1L;
    private String  action;//动作

    private String  status;//状态

    private String  description;//描述

    private String  actionId;//action_id

    private Integer  rank;//排序

    private String score;//分数
   
    private java.util.Date  createDate;//创建时间

    private java.util.Date  modifyDate;//修改时间

    private Integer  studyResultId;//学习结果id

    @Column(name="action",nullable = true, length=200)
    public String  getAction(){
       return action;
    }

    public  void  setAction(String action){
       this.action=action;
    }


    @Column(name="status",nullable = true, length=200)
    public String  getStatus(){
       return status;
    }

    public  void  setStatus(String status){
       this.status=status;
    }


    @Column(name="description",nullable = true, length=200)
    public String  getDescription(){
       return description;
    }

    public  void  setDescription(String description){
       this.description=description;
    }


    @Column(name="action_id",nullable = false, length=20)
    public String  getActionId(){
       return actionId;
    }

    public  void  setActionId(String actionId){
       this.actionId=actionId;
    }


    @Column(name="rank",nullable = false, length=10)
    public Integer  getRank(){
       return rank;
    }

    public  void  setRank(Integer rank){
       this.rank=rank;
    }    

	@Column(name="create_date",nullable = true, length=19)
    public java.util.Date  getCreateDate(){
       return createDate;
    }

	public  void  setCreateDate(java.util.Date createDate){
       this.createDate=createDate;
    }
	
    @Column(name="score",nullable = false, length=49)
    public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

    @Column(name="modify_date",nullable = true, length=19)
    public java.util.Date  getModifyDate(){
       return modifyDate;
    }

    public  void  setModifyDate(java.util.Date modifyDate){
       this.modifyDate=modifyDate;
    }


    @Column(name="study_result_id",nullable = false, length=10)
    public Integer  getStudyResultId(){
       return studyResultId;
    }

    public  void  setStudyResultId(Integer studyResultId){
       this.studyResultId=studyResultId;
    }

}
