package com.xcj.admin.entity.course;
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.xcj.admin.base.BaseEntity;
@Entity(name="CourseWareKp")
@Table(name="course_ware_kp")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_course_ware_kp")
public class CourseWareKp extends BaseEntity  implements java.io.Serializable{
    /**
     * <b>function:</b> 读取SBT故障
     * @project base
     * @package com.xcj.common.util  
     * @fileName @return
     * @createDate February 16, 2015 3:05:04 PM
     * @author yy.niu
	 */
	private static final long serialVersionUID = 6246686493481164676L;

	private Integer  courseWareId;//课件id

    private Integer  kpId;//知识点id

    @Column(name="course_ware_id",nullable = false, length=10)
    public Integer  getCourseWareId(){
       return courseWareId;
    }

    public  void  setCourseWareId(Integer courseWareId){
       this.courseWareId=courseWareId;
    }


    @Column(name="kp_id",nullable = true, length=10)
    public Integer  getKpId(){
       return kpId;
    }

    public  void  setKpId(Integer kpId){
       this.kpId=kpId;
    }

}
