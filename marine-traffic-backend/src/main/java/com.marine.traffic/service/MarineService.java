package com.marine.traffic.service;

import com.marine.traffic.constant.CommonConstant;
import com.marine.traffic.dao.MarineDao;
import com.marine.traffic.model.MarineTraffic;
import com.marine.traffic.model.ResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class MarineService {
    private final MarineDao marineDao;

    public MarineService(MarineDao marineDao) {
        this.marineDao = marineDao;
    }

    public ResponseDto createMarineTraffic(MarineTraffic payload) {
        try {
            boolean insertData = marineDao.createMarineTraffic(payload);
            if (!insertData) {
                return new ResponseDto(CommonConstant.FAILED, null, null);
            }
            return new ResponseDto(CommonConstant.SUCCESS, null, new ArrayList<>());
        } catch (Exception exception) {
            return new ResponseDto(CommonConstant.FAILED, null, null);
        }
    }

    public ResponseDto createMarineTrafficBatch(List<MarineTraffic> payload) {
        try {
            boolean insertData = marineDao.createMarineTrafficBatch(payload);
            if (!insertData) {
                return new ResponseDto(CommonConstant.FAILED, null, null);
            }
            return new ResponseDto(CommonConstant.SUCCESS, null, new ArrayList<>());
        } catch (Exception exception) {
            return new ResponseDto(CommonConstant.FAILED, null, null);
        }
    }


    public ResponseDto getListMarineTraffic() {
        try {
            List<MarineTraffic> marineTraffic = marineDao.getMarineTrafficList();
            return new ResponseDto(CommonConstant.SUCCESS, null, marineTraffic);
        } catch (Exception exception) {
            return new ResponseDto(CommonConstant.FAILED, null, null);
        }
    }

    public ResponseDto getMarineTrafficDetail(Integer maritimeMobileServiceId) {
        try {
            MarineTraffic marineTraffic = marineDao.getMarineTrafficDetail(maritimeMobileServiceId);
            return new ResponseDto(CommonConstant.SUCCESS, null, marineTraffic);
        } catch (Exception exception) {
            return new ResponseDto(CommonConstant.FAILED, null, null);
        }
    }

    public ResponseDto getCreateDataFake(int count) {
        try {
            List<MarineTraffic> payload = generateMarineTrafficData(1);
            boolean insertData = marineDao.createMarineTrafficBatch(payload);
            if (!insertData) {
                return new ResponseDto(CommonConstant.FAILED, null, null);
            }
            return new ResponseDto(CommonConstant.SUCCESS, null, new ArrayList<>());
        } catch (Exception exception) {
            return new ResponseDto(CommonConstant.FAILED, null, null);
        }
    }
    public static List<MarineTraffic> generateMarineTrafficData(int count) {
        List<MarineTraffic> dataList = new ArrayList<>();
        Random random = new Random();
        String[] shipNames = {"Oceanic", "Voyager", "Seafarer", "Explorer", "Mariner"};
        String[] statuses = {"Active", "Anchored", "Underway", "Moored"};
        String[] shipTypes = {"Cargo", "Tanker", "Container", "Passenger", "Fishing"};
        String[] destinations = {"Singapore", "Tokyo", "Los Angeles", "Rotterdam", "Shanghai"};

        for (int i = 1; i <= count; i++) {
            MarineTraffic marineTraffic = MarineTraffic.builder().build();
            marineTraffic.setMaritimeMobileServiceId(100000000L + i);
            marineTraffic.setTagBlockTime(new Date(System.currentTimeMillis() - random.nextInt(1000000000)));
            marineTraffic.setSecond(random.nextInt(60));
            marineTraffic.setShipName(shipNames[random.nextInt(shipNames.length)] + " " + i);
            marineTraffic.setStatus(statuses[random.nextInt(statuses.length)]);
            marineTraffic.setType(random.nextInt(5) + 1);
            marineTraffic.setLat(-90 + (random.nextDouble() * 180)); // Latitude range: -90 to 90
            marineTraffic.setLon(-180 + (random.nextDouble() * 360)); // Longitude range: -180 to 180
            marineTraffic.setSpeed(random.nextDouble() * 30); // Speed in knots
            marineTraffic.setCourse(random.nextDouble() * 360); // Course in degrees
            marineTraffic.setTurnRate(random.nextDouble() * 10); // Turn rate in degrees/minute
            marineTraffic.setShipType(shipTypes[random.nextInt(shipTypes.length)]);
            marineTraffic.setHeading("HDG-" + random.nextInt(360));
            marineTraffic.setRepeat("RPT-" + random.nextInt(10));
            marineTraffic.setImo("IMO-" + (1000000 + i));
            marineTraffic.setCallSign("CALL-" + i);
            marineTraffic.setBand("BAND-" + (random.nextInt(5) + 1));
            marineTraffic.setShipMaxSpeed(String.format("%.2f", 10 + random.nextDouble() * 20)); // Speed in knots
            marineTraffic.setLengthShip(String.format("%.1f", 50 + random.nextDouble() * 100)); // Length in meters
            marineTraffic.setBeam(String.format("%.1f", 10 + random.nextDouble() * 20)); // Beam in meters
            marineTraffic.setDraught(String.format("%.1f", 5 + random.nextDouble() * 10)); // Draught in meters
            marineTraffic.setGrossTonnage(String.format("%d", 10000 + random.nextInt(90000))); // Tonnage
            marineTraffic.setDeadWeight(String.format("%d", 5000 + random.nextInt(50000))); // Deadweight tonnage
            marineTraffic.setBuildShip("" + (1980 + random.nextInt(40))); // Build year
            marineTraffic.setDestination(destinations[random.nextInt(destinations.length)]);

            dataList.add(marineTraffic);
        }

        return dataList;
    }

}
