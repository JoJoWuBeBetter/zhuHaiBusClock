package com.jojo.zhuhaibusclock.service;

import com.jojo.zhuhaibusclock.model.SysStation;

import java.util.List;

/**
 * @author JoJoWu
 */
public interface StationService {
    int addStation(SysStation station);

    SysStation findStation(String stationId);

    void deleteStation(String stationId);

    void updateStation(SysStation sysStation);

}
