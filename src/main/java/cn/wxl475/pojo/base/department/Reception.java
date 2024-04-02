package cn.wxl475.pojo.base.department;

import cn.wxl475.pojo.base.Charge;

import java.util.List;

public class Reception extends Department{

    List<Charge> receptionCharges;

    public Reception(Department department, List<Charge> receptionCharges) {
        super(department.getDepartmentId(), department.getDepartmentName(), department.getDepartmentType(), department.getDepartmentPrincipal(), department.getDepartmentFunction(), department.getCreateTime(), department.getUpdateTime(), department.getDeleted());
        this.receptionCharges = receptionCharges;
    }
}
