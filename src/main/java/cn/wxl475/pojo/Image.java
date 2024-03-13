package cn.wxl475.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "images",schema = "data")
public class Image {
    //对应数据库中的主键（UUID、自增id、雪花算法、redis、zookeeper）
    @TableId(type = IdType.ASSIGN_ID)
    private Long imageId;
    @TableField("user_id")
    private Long userId;
    @TableField("image_url")
    private String imageUrl;
    @TableField("image_name")
    private String imageName;
    @TableField("image_type")
    private String imageType;
    @TableField("image_size")
    private Long imageSize;
    //注解填充字段 @TableField(.. fill = FieldFill.INSERT) 生成器策略部分也可以配置！
    @TableField(fill = FieldFill.INSERT)
    private Data createTime;
    //注解填充字段 @TableField(.. fill = FieldFill.INSERT) 生成器策略部分也可以配置！
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Data updateTime;
    @TableLogic
    private Integer Deleted;
}
