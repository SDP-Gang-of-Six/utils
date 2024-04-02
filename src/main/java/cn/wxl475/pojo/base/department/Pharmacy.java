package cn.wxl475.pojo.base.department;

import cn.wxl475.pojo.base.Medicine;

import java.util.List;

public class Pharmacy extends Department{
    List<Medicine> pharmacyMedicines;

    public Pharmacy(Department department, List<Medicine> pharmacyMedicines) {
        super(department.getDepartmentId(), department.getDepartmentName(), department.getDepartmentType(), department.getDepartmentPrincipal(), department.getDepartmentFunction(), department.getCreateTime(), department.getUpdateTime(), department.getDeleted());
        this.pharmacyMedicines = pharmacyMedicines;
    }
}
