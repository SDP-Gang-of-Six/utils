package cn.wxl475.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaperCreater {
    private Long paperId;
    private String paperName;
    private Integer examTime;
    private Integer totalScore;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private ArrayList<PaperScoreCreater> paperScores;
}
