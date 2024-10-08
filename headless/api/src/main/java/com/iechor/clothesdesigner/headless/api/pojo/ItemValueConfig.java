package com.iechor.clothesdesigner.headless.api.pojo;

import com.iechor.clothesdesigner.common.pojo.DateConf;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/** 导入字典的可选配置 */
@Data
public class ItemValueConfig {

    private Long metricId;
    private List<String> blackList = new ArrayList<>();
    private List<String> whiteList = new ArrayList<>();
    private List<String> ruleList = new ArrayList<>();
    private Long limit;
    private DateConf dateConf;
}
