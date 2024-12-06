package com.marine.traffic.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
public class MarineTraffic {
    private Long maritimeMobileServiceId;
    private Date tagBlockTime;
    private Integer second;
    private String shipName;
    private String status;
    private Integer type;
    private Double lat;
    private Double lon;
    private Double speed;
    private Double course;
    private Double  turnRate;
    private String shipType;
    private String heading;

    private String repeat;
    private String imo;
    private String callSign;
    private String band;
    private String shipMaxSpeed;
    private String lengthShip;
    private String beam;
    private String draught;
    private String grossTonnage;
    private String deadWeight;
    private String buildShip;
    private String destination;


}
