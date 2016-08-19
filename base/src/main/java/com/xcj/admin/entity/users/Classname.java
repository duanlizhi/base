package com.xcj.admin.entity.users;
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.xcj.admin.base.BaseEntity;
@Entity(name="Classname")
@Table(name="classname")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_classname")
public class Classname extends BaseEntity  implements java.io.Serializable{
   private static final long serialVersionUID = 1L;
    private String  name;//

    @Column(name="name",nullable = true, length=255)
    public String  getName(){
       return name;
    }

    public  void  setName(String name){
       this.name=name;
    }

}
