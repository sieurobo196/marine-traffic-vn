package com.marine.traffic.dao;

import com.marine.traffic.config.JdbcProvider;
import com.marine.traffic.constant.CommonChSQL;
import com.marine.traffic.model.MarineTraffic;
import com.marine.traffic.utils.Resource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MarineDao {
    private final JdbcProvider jdbcProvider;

    public MarineDao(JdbcProvider jdbcProvider) {
        this.jdbcProvider = jdbcProvider;
    }

    //    INSERT INTO traffic_log_marine.data_signal
//            (mmsi, tagblock_times, second, shipname, status, type, lat, lon, speed,
//    course, turnrate, heading, repeat, imo, shiptype, callsign, band, ship_max_speed,
//    length, beam, draught, grosstonnage, deadweight, build, destination)
//    VALUES
//            (123456789, '2024-10-11 10:00:00', 60, 'Ship A', 'Active', 1, 10.123456, 106.123456, 12.5,
//                     300, 0, 200, 0, 'IMO1234567', 'Cargo', 'CS123', 'A', '25',
//                     '150', '25', '10.5', '50000', '25000', '2005', 'Port A')
    public boolean createMarineTraffic(MarineTraffic marineTraffic) {
        try {
            String sql = Resource.readSql(CommonChSQL.INSERT_MARINE_TRAFFIC);
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("mmsi", marineTraffic.getMaritimeMobileServiceId());
            param.addValue("tagblock_times", marineTraffic.getTagBlockTime());
            param.addValue("second", marineTraffic.getSecond());
            param.addValue("shipname", marineTraffic.getShipName());
            param.addValue("status", marineTraffic.getStatus());
            param.addValue("type", marineTraffic.getType());
            param.addValue("lat", marineTraffic.getLat());
            param.addValue("lon", marineTraffic.getLon());
            param.addValue("speed", marineTraffic.getSpeed());
            param.addValue("course", marineTraffic.getCourse());
            param.addValue("turnrate", marineTraffic.getTurnRate());
            param.addValue("heading", marineTraffic.getHeading());
            param.addValue("repeat", marineTraffic.getRepeat());
            param.addValue("imo", marineTraffic.getImo());
            param.addValue("shiptype", marineTraffic.getShipType());
            param.addValue("callsign", marineTraffic.getCallSign());
            param.addValue("band", marineTraffic.getBand());
            param.addValue("ship_max_speed", marineTraffic.getShipMaxSpeed());
            param.addValue("length", marineTraffic.getLengthShip());
            param.addValue("beam", marineTraffic.getBeam());
            param.addValue("draught", marineTraffic.getDraught());
            param.addValue("grosstonnage", marineTraffic.getGrossTonnage());
            param.addValue("deadweight", marineTraffic.getDeadWeight());
            param.addValue("build", marineTraffic.getBuildShip());
            param.addValue("destination", marineTraffic.getDestination());

            return jdbcProvider.clickhouse().update(sql, param) > 0;
        } catch (Exception exception) {
            System.out.println(exception);
            return false;
        }

    }

    public boolean createMarineTrafficBatch(List<MarineTraffic> marineTraffics) {
        try {
            String sql = Resource.readSql(CommonChSQL.INSERT_MARINE_TRAFFIC);

            // Create a list of parameters for each MarineTraffic object
            List<MapSqlParameterSource> params = new ArrayList<>();
            for (MarineTraffic marineTraffic : marineTraffics) {
                MapSqlParameterSource param = new MapSqlParameterSource();
                param.addValue("mmsi", marineTraffic.getMaritimeMobileServiceId());
                param.addValue("tagblock_times", marineTraffic.getTagBlockTime());
                param.addValue("second", marineTraffic.getSecond());
                param.addValue("shipname", marineTraffic.getShipName());
                param.addValue("status", marineTraffic.getStatus());
                param.addValue("type", marineTraffic.getType());
                param.addValue("lat", marineTraffic.getLat());
                param.addValue("lon", marineTraffic.getLon());
                param.addValue("speed", marineTraffic.getSpeed());
                param.addValue("course", marineTraffic.getCourse());
                param.addValue("turnrate", marineTraffic.getTurnRate());
                param.addValue("heading", marineTraffic.getHeading());
                param.addValue("repeat", marineTraffic.getRepeat());
                param.addValue("imo", marineTraffic.getImo());
                param.addValue("shiptype", marineTraffic.getShipType());
                param.addValue("callsign", marineTraffic.getCallSign());
                param.addValue("band", marineTraffic.getBand());
                param.addValue("ship_max_speed", marineTraffic.getShipMaxSpeed());
                param.addValue("length", marineTraffic.getLengthShip());
                param.addValue("beam", marineTraffic.getBeam());
                param.addValue("draught", marineTraffic.getDraught());
                param.addValue("grosstonnage", marineTraffic.getGrossTonnage());
                param.addValue("deadweight", marineTraffic.getDeadWeight());
                param.addValue("build", marineTraffic.getBuildShip());
                param.addValue("destination", marineTraffic.getDestination());

                params.add(param);
            }

            int[] updateCounts = jdbcProvider.clickhouse().batchUpdate(sql, params.toArray(new MapSqlParameterSource[0]));

            // Check if all updates were successful
            for (int count : updateCounts) {
                if (count == 0) return false;
            }

            return true;
        } catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
    }

    //    INSERT INTO traffic_log_marine.data_signal
//            (mmsi, tagblock_times, second, shipname, status, type, lat, lon, speed,
//    course, turnrate, heading, repeat, imo, shiptype, callsign, band, ship_max_speed,
//    length, beam, draught, grosstonnage, deadweight, build, destination)
//    VALUES (123456789, '2024-10-11 10:00:00', 60, 'Ship A', 'Active', 1, 10.123456, 106.123456, 12.5,
//                     300, 0, 200, 0, 'IMO1234567', 'Cargo', 'CS123', 'A', '25',
//                     '150', '25', '10.5', '50000', '25000', '2005', 'Port A')
    public List<MarineTraffic> getMarineTrafficList() {
        String sql = Resource.readSql(CommonChSQL.GET_MARINE_TRAFFIC);
        MapSqlParameterSource params = new MapSqlParameterSource("MMSI", 1);
        return jdbcProvider.clickhouse().query(sql, params, rs -> {
                    List<MarineTraffic> list = new ArrayList<>();
                    while (rs.next()) {
                        MarineTraffic marineTraffic = MarineTraffic.builder()
                                .maritimeMobileServiceId(rs.getLong("mmsi"))
                                .tagBlockTime(rs.getTimestamp("tagblock_times"))
                                .second(rs.getInt("second")).shipName(rs.getString("shipname"))
                                .status(rs.getString("status")).type(rs.getInt("type"))
                                .lat(rs.getDouble("lat")).lon(rs.getDouble("lon"))
                                .speed(rs.getDouble("speed")).course(rs.getDouble("course"))
                                .turnRate(rs.getDouble("turnrate")).heading(rs.getString("heading"))
                                .repeat(rs.getString("repeat")).imo(rs.getString("imo")).shipType(rs.getString("shiptype"))
                                .callSign(rs.getString("callsign")).band(rs.getString("band")).shipMaxSpeed(rs.getString("ship_max_speed"))
                                .lengthShip(rs.getString("length")).beam(rs.getString("beam")).draught(rs.getString("draught"))
                                .grossTonnage(rs.getString("grosstonnage")).deadWeight(rs.getString("deadweight"))
                                .buildShip(rs.getString("build")).destination(rs.getString("destination"))
                                .build();

                        list.add(marineTraffic);
                    }
                    return list;
                }

        );
    }

    public MarineTraffic getMarineTrafficDetail(Integer maritimeMobileServiceId) {
        String sql = Resource.readSql(CommonChSQL.GET_MARINE_TRAFFIC_DETAIL);
        MapSqlParameterSource params = new MapSqlParameterSource("MMSI", maritimeMobileServiceId);
        return jdbcProvider.clickhouse().query(sql, params, rs -> {
                    MarineTraffic marineTraffic = MarineTraffic.builder().build();
                    while (rs.next()) {
                        marineTraffic = MarineTraffic.builder()
                                .maritimeMobileServiceId(rs.getLong("mmsi"))
                                .tagBlockTime(rs.getTimestamp("tagblock_times"))
                                .second(rs.getInt("second")).shipName(rs.getString("shipname"))
                                .status(rs.getString("status")).type(rs.getInt("type"))
                                .lat(rs.getDouble("lat")).lon(rs.getDouble("lon"))
                                .speed(rs.getDouble("speed")).course(rs.getDouble("course"))
                                .turnRate(rs.getDouble("turnrate")).heading(rs.getString("heading"))
                                .repeat(rs.getString("repeat")).imo(rs.getString("imo")).shipType(rs.getString("shiptype"))
                                .callSign(rs.getString("callsign")).band(rs.getString("band")).shipMaxSpeed(rs.getString("ship_max_speed"))
                                .lengthShip(rs.getString("length")).beam(rs.getString("beam")).draught(rs.getString("draught"))
                                .grossTonnage(rs.getString("grosstonnage")).deadWeight(rs.getString("deadweight"))
                                .buildShip(rs.getString("build")).destination(rs.getString("destination"))
                                .build();

                    }
                    return marineTraffic;
                }
        );
    }
}
