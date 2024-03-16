package cn.wxl475.pojo;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user", schema = "user")
public class User {
    //对应数据库中的主键（UUID、自增id、雪花算法、redis、zookeeper）
    @TableId(type = IdType.ASSIGN_ID)
    private Long uid;

    private String nickname;

    private String password;

    private String username;

    private Boolean userType;

    private Boolean deleted;

    //注解填充字段 @TableField(.. fill = FieldFill.INSERT) 生成器策略部分也可以配置！
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
