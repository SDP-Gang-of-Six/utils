package cn.wxl475.pojo.base;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.data.elasticsearch.annotations.Document;

@TableName(value = "hospitalization",schema = "base")
@Document(indexName = "hospitalization")
public class Hospitalization {
}
