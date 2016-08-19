package com.xcj.admin.entity.tests;
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.xcj.admin.base.BaseEntity;
@Entity(name="TestQuestion")
@Table(name="test_question")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_test_question")
public class TestQuestion extends BaseEntity  implements java.io.Serializable{
   private static final long serialVersionUID = 1L;
    private String  question;//问题

    private String  answer;//答案

    private Integer  rank;//排序

    private String  description;//描述

    private String  questionId;//question_id

    private java.util.Date  createDate;//创建时间

    private java.util.Date  modifyDate;//修改时间

    private Integer  testResultId;//学习结果id

    @Column(name="question",nullable = true, length=200)
    public String  getQuestion(){
       return question;
    }

    public  void  setQuestion(String question){
       this.question=question;
    }


    @Column(name="answer",nullable = true, length=200)
    public String  getAnswer(){
       return answer;
    }

    public  void  setAnswer(String answer){
       this.answer=answer;
    }


    @Column(name="rank",nullable = false, length=10)
    public Integer  getRank(){
       return rank;
    }

    public  void  setRank(Integer rank){
       this.rank=rank;
    }


    @Column(name="description",nullable = true, length=200)
    public String  getDescription(){
       return description;
    }

    public  void  setDescription(String description){
       this.description=description;
    }


    @Column(name="question_id",nullable = true, length=20)
    public String  getQuestionId(){
       return questionId;
    }

    public  void  setQuestionId(String questionId){
       this.questionId=questionId;
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


    @Column(name="test_result_id",nullable = false, length=10)
    public Integer  getTestResultId(){
       return testResultId;
    }

    public  void  setTestResultId(Integer testResultId){
       this.testResultId=testResultId;
    }

}
