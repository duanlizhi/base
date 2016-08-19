package com.xcj.admin.entity.tests;
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.xcj.admin.base.BaseEntity;
@Entity(name="TestReport")
@Table(name="test_report")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_test_report")
public class TestReport extends BaseEntity  implements java.io.Serializable{
	private static final long serialVersionUID = 1422479140761643092L;

	private String  question;//问题

    private String  score;//分数

    private Integer  testsPostedId;//测评id

    private String  kp;//知识点

    private String  kpParent;//知识点父类

    private String  courseNumber;//课件编号

    private String  userEmail;//用户邮箱

    private String  domainUsername;//域名

    private String  sessionId;//sessionId

    private String  testsPublicId;//testsPublicId

    private String  answer;//用户答案

    private String  rightAnswer;//正确答案

    private java.util.Date  createDate;//

    @Column(name="question",nullable = false, length=255)
    public String  getQuestion(){
       return question;
    }

    public  void  setQuestion(String question){
       this.question=question;
    }


    @Column(name="score",nullable = false, length=10)
    public String  getScore(){
       return score;
    }

    public  void  setScore(String score){
       this.score=score;
    }


    @Column(name="tests_posted_id",nullable = false, length=10)
    public Integer  getTestsPostedId(){
       return testsPostedId;
    }

    public  void  setTestsPostedId(Integer testsPostedId){
       this.testsPostedId=testsPostedId;
    }


    @Column(name="kp",nullable = false, length=255)
    public String  getKp(){
       return kp;
    }

    public  void  setKp(String kp){
       this.kp=kp;
    }


    @Column(name="kp_parent",nullable = false, length=255)
    public String  getKpParent(){
       return kpParent;
    }

    public  void  setKpParent(String kpParent){
       this.kpParent=kpParent;
    }


    @Column(name="course_number",nullable = false, length=255)
    public String  getCourseNumber(){
       return courseNumber;
    }

    public  void  setCourseNumber(String courseNumber){
       this.courseNumber=courseNumber;
    }


    @Column(name="user_email",nullable = false, length=255)
    public String  getUserEmail(){
       return userEmail;
    }

    public  void  setUserEmail(String userEmail){
       this.userEmail=userEmail;
    }


    @Column(name="domain_username",nullable = false, length=255)
    public String  getDomainUsername(){
       return domainUsername;
    }

    public  void  setDomainUsername(String domainUsername){
       this.domainUsername=domainUsername;
    }


    @Column(name="session_id",nullable = false, length=255)
    public String  getSessionId(){
       return sessionId;
    }

    public  void  setSessionId(String sessionId){
       this.sessionId=sessionId;
    }


    @Column(name="tests_public_id",nullable = false, length=255)
    public String  getTestsPublicId(){
       return testsPublicId;
    }

    public  void  setTestsPublicId(String testsPublicId){
       this.testsPublicId=testsPublicId;
    }


    @Column(name="answer",nullable = true, length=255)
    public String  getAnswer(){
       return answer;
    }

    public  void  setAnswer(String answer){
       this.answer=answer;
    }


    @Column(name="right_answer",nullable = true, length=255)
    public String  getRightAnswer(){
       return rightAnswer;
    }

    public  void  setRightAnswer(String rightAnswer){
       this.rightAnswer=rightAnswer;
    }


    @Column(name="create_date",nullable = false, length=19)
    public java.util.Date  getCreateDate(){
       return createDate;
    }

    public  void  setCreateDate(java.util.Date createDate){
       this.createDate=createDate;
    }

}
