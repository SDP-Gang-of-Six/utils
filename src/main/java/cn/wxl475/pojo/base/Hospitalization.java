package cn.wxl475.pojo.base;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "hospitalization",schema = "base")
@Document(indexName = "hospitalization")
public class Hospitalization {
}
