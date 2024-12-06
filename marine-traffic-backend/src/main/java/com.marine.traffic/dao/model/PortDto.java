package com.marine.traffic.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class PortDto {
    private Long id;
    private String country;
    private String port;
    private String unLocode;
    private int photos;
    private int vesselsInPort;
    private int departuresLast24hrs;
    private int arrivalsLast24hrs;
    private Date expectedArrivals;
    private Date localTime;
    private String relatedAnchorage;
    private String areaGlobal;
    private String coverage;

}
