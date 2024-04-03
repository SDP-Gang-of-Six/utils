package cn.wxl475.pojo.base.department;

import cn.wxl475.pojo.base.Item.Item;

import java.util.List;

public class AssayRoom extends Department{
    List<Item> assayRoomItems;

    public AssayRoom(Department department, List<Item> assayRoomItems) {
        super(department.getDepartmentId(), department.getDepartmentName(), department.getDepartmentType(), department.getDepartmentPrincipal(), department.getDepartmentFunction(),department.getDepartmentRoomNumber(), department.getCreateTime(), department.getUpdateTime(), department.getDeleted());
        this.assayRoomItems = assayRoomItems;
    }
}
