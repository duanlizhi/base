package com.xcj.admin.entity.course;
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.xcj.admin.base.BaseEntity;
@Entity(name="WbtSbtKp")
@Table(name="wbt_sbt_kp")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_wbt_sbt_kp")
public class WbtSbtKp extends BaseEntity  implements java.io.Serializable{
    /**
		 * 
	     * <b>function:</b> 读取SBT故障
	     * @project base
	     * @package com.xcj.common.util  
	     * @fileName @return
	     * @createDate February 16, 2015 3:05:04 PM
	     * @author yy.niu
		 */
	private static final long serialVersionUID = -2090635704071621210L;

	private Integer  kpId;//知识点id

    private Integer  type;//1、SBT问题2、sbt成绩 3、WBT4、单个故障信息

    private Integer  sbtWbtId;//副id

    @Column(name="kp_id",nullable = false, length=10)
    public Integer  getKpId(){
       return kpId;
    }

    public  void  setKpId(Integer kpId){
       this.kpId=kpId;
    }


    @Column(name="type",nullable = false, length=10)
    public Integer  getType(){
       return type;
    }

    public  void  setType(Integer type){
       this.type=type;
    }


    @Column(name="sbt_wbt_id",nullable = false, length=10)
    public Integer  getSbtWbtId(){
       return sbtWbtId;
    }

    public  void  setSbtWbtId(Integer sbtWbtId){
       this.sbtWbtId=sbtWbtId;
    }

}
