package cn.wxl475.pojo.base.department;

import java.util.List;

public class SubDepartment <DATA> extends Department{
    List<DATA> data;

    public SubDepartment(Department department, List<DATA> data) {
        super(department.getDepartmentId(), department.getDepartmentName(), department.getDepartmentType(), department.getDepartmentPrincipal(), department.getDepartmentFunction(), department.getDepartmentRoomNumber(), department.getCreateTime(), department.getUpdateTime());
        this.data = data;
    }
}
