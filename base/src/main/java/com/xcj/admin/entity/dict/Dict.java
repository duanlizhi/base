package com.xcj.admin.entity.dict;
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.xcj.admin.base.BaseEntity;
@Entity(name="Dict")
@Table(name="dict")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_dict")
public class Dict extends BaseEntity  implements java.io.Serializable{
   private static final long serialVersionUID = 1L;
    private String  dictName;//名称

    private String  typeCode;//字典类型编码

    private String  typeName;//字典类型名称

    private String  description;//描述信息

    private java.util.Date  createDate;//创建日期

    private java.util.Date  modifyDate;//修改日期

    @Column(name="dict_name",nullable = false, length=255)
    public String  getDictName(){
       return dictName;
    }

    public  void  setDictName(String dictName){
       this.dictName=dictName;
    }


    @Column(name="type_code",nullable = true, length=20)
    public String  getTypeCode(){
       return typeCode;
    }

    public  void  setTypeCode(String typeCode){
       this.typeCode=typeCode;
    }


    @Column(name="type_name",nullable = false, length=255)
    public String  getTypeName(){
       return typeName;
    }

    public  void  setTypeName(String typeName){
       this.typeName=typeName;
    }


    @Column(name="description",nullable = true, length=255)
    public String  getDescription(){
       return description;
    }

    public  void  setDescription(String description){
       this.description=description;
    }


    @Column(name="create_date",nullable = false, length=19)
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

}
