ALTER TABLE course_ware ADD pass_condition int(11) comment '实际得分大于该数值为通过';
ALTER TABLE course_ware ADD complete_condition int(11) comment '实际得分大于该数值为完成';

ALTER TABLE sbt_fault ADD pass_condition int(11) comment '实际得分大于该数值为通过';
ALTER TABLE sbt_fault ADD complete_condition int(11) comment '实际得分大于该数值为完成';

ALTER TABLE course_ware CHANGE pass_score sum_score int(11);

ALTER TABLE sbt_question ADD kp varchar(255) comment '知识点编号';
ALTER TABLE sbt_question ADD type int(11) comment '题型1单选2多选3表格单选4单选+多选5表格多选';

ALTER TABLE sbt_scores ADD kp varchar(255) comment '知识点编号';


use xcjdemonew;
ALTER TABLE course_ware CHANGE pass_score sum_score int(11);
ALTER TABLE course_ware ADD pass_condition int(11) comment '实际得分大于该数值为通过';
ALTER TABLE course_ware ADD complete_condition int(11) comment '实际得分大于该数值为完成';