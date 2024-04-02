package cn.wxl475.pojo.base.department;

import cn.wxl475.pojo.base.Vaccine;

import java.util.List;

public class ImmuneRoom extends Department{
    List<Vaccine> immuneRoomVaccines;

    public ImmuneRoom(Department department, List<Vaccine> immuneRoomVaccines) {
        super(department.getDepartmentId(), department.getDepartmentName(), department.getDepartmentType(), department.getDepartmentPrincipal(), department.getDepartmentFunction(),department.getDepartmentRoomNumber(), department.getCreateTime(), department.getUpdateTime(), department.getDeleted());
        this.immuneRoomVaccines = immuneRoomVaccines;
    }
}
