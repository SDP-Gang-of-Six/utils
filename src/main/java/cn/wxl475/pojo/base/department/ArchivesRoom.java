package cn.wxl475.pojo.base.department;

import cn.wxl475.pojo.base.Record;

import java.util.List;

public class ArchivesRoom extends Department{
    List<Record> archivesRoomRecords;

    public ArchivesRoom(Department department, List<Record> archivesRoomRecords) {
        super(department.getDepartmentId(), department.getDepartmentName(), department.getDepartmentType(), department.getDepartmentPrincipal(), department.getDepartmentFunction(), department.getDepartmentRoomNumber(), department.getCreateTime(), department.getUpdateTime(), department.getDeleted());
        this.archivesRoomRecords = archivesRoomRecords;
    }
}
