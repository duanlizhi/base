package com.xcj.admin.entity.course;
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.xcj.admin.base.BaseEntity;
@Entity(name="SbtScores")
@Table(name="sbt_scores")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_sbt_scores")
public class SbtScores extends BaseEntity  implements java.io.Serializable{
    /**
		 * 
	     * <b>function:</b> 读取SBT故障
	     * @project base
	     * @package com.xcj.common.util  
	     * @fileName @return
	     * @createDate February 16, 2015 3:05:04 PM
	     * @author yy.niu
		 */
	private static final long serialVersionUID = -909972230838162116L;

	private String  scoreTypeId;//score_id

    private String  type;//类型

    private String  rate;//比率

    private Integer  rank;//排序

    private String  rightAnswers;//正确答案

    private String  questionName;//题目名称

    private java.util.Date  createDate;//创建时间

    private java.util.Date  modifyDate;//创建时间

    private Integer  sbtFaultId;//sbt单个故障id
    
    private String kp;//知道点
    
    private String qid;//对应问题id

    @Column(name="score_type_id",nullable = false, length=20)
    public String  getScoreTypeId(){
       return scoreTypeId;
    }

    public  void  setScoreTypeId(String scoreTypeId){
       this.scoreTypeId=scoreTypeId;
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


    @Column(name="sbt_fault_id",nullable = true, length=10)
    public Integer  getSbtFaultId(){
       return sbtFaultId;
    }

    public  void  setSbtFaultId(Integer sbtFaultId){
       this.sbtFaultId=sbtFaultId;
    }

    @Column(name="kp",nullable = true, length=255)
	public String getKp() {
		return kp;
	}

	public void setKp(String kp) {
		this.kp = kp;
	}

	@Column(name="qid",nullable = true, length=255)
	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}
    
    

}
