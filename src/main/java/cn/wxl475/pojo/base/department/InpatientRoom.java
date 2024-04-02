package cn.wxl475.pojo.base.department;

import cn.wxl475.pojo.base.hospitalization.Hospitalization;

import java.util.List;

public class InpatientRoom extends Department{
    List<Hospitalization> inpatientRoomHospitalizations;

    public InpatientRoom(Department department, List<Hospitalization> inpatientRoomHospitalizations) {
        super(department.getDepartmentId(), department.getDepartmentName(), department.getDepartmentType(), department.getDepartmentPrincipal(), department.getDepartmentFunction(),department.getDepartmentRoomNumber(), department.getCreateTime(), department.getUpdateTime(), department.getDeleted());
        this.inpatientRoomHospitalizations = inpatientRoomHospitalizations;
    }
}
