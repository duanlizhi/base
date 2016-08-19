package com.xcj.admin.entity.users;
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.xcj.admin.base.BaseEntity;
@Entity(name="Score")
@Table(name="score")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_score")
public class Score extends BaseEntity  implements java.io.Serializable{
   private static final long serialVersionUID = 1L;
    private Integer  userId;//

    private String  course;//

    private Integer  result;//

    @Column(name="user_id",nullable = true, length=10)
    public Integer  getUserId(){
       return userId;
    }

    public  void  setUserId(Integer userId){
       this.userId=userId;
    }


    @Column(name="course",nullable = true, length=255)
    public String  getCourse(){
       return course;
    }

    public  void  setCourse(String course){
       this.course=course;
    }


    @Column(name="result",nullable = true, length=10)
    public Integer  getResult(){
       return result;
    }

    public  void  setResult(Integer result){
       this.result=result;
    }

}
