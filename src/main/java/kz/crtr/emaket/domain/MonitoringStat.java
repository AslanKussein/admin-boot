package kz.crtr.emaket.domain;

import lombok.Data;

@Data
public class MonitoringStat {
    private int todaySaved;
    private int todaySended;
    private int todayWaiting;
    private int todayEror;    
    private int alltimeSaved;
    private int alltimeSended;
    private int alltimeWaiting;
    private int alltimeEror;
}