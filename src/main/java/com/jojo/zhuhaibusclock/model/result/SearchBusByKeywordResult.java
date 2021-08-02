package com.jojo.zhuhaibusclock.model.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import com.jojo.zhuhaibusclock.model.entity.Route;
import com.jojo.zhuhaibusclock.model.entity.Station;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author JoJoWu
 */
@NoArgsConstructor
@Data
public class SearchBusByKeywordResult {
    @JSONField(name = "stations")
    private List<Station> stations;
    @JSONField(name = "routes")
    private List<Route> routes;
}
