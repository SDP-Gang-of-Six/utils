package cn.wxl475.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page <T>{
    private Integer totalNumber;
    private ArrayList<T> data;
}
