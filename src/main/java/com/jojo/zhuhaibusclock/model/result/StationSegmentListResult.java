package com.jojo.zhuhaibusclock.model.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import com.jojo.zhuhaibusclock.model.entity.Segment;
import com.jojo.zhuhaibusclock.model.entity.Station;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author JoJoWu
 */
@NoArgsConstructor
@Data
public class StationSegmentListResult {
    @JSONField(name = "segmentlist")
    private List<Segment> segmentList;
    @JSONField(name = "stationinfo")
    private Station stationInfo;
}
