package com.xoj.backend.param;

import com.xoj.backend.entity.Question;
import com.xoj.backend.model.QuestionModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/***
 * @Author jianghanchen
 * @Date 1:39 2022/4/24
 ***/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EsResultParam{

    private List<QuestionModel> res;

    private int total;


}