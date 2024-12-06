package com.marine.traffic.dao;

import com.marine.traffic.config.JdbcProvider;
import com.marine.traffic.constant.CommonChSQL;
import com.marine.traffic.constant.CommonMySQL;
import com.marine.traffic.model.MarineTraffic;
import com.marine.traffic.model.PortDto;
import com.marine.traffic.utils.Resource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PortDao {
    private final JdbcProvider jdbcProvider;

    public PortDao(JdbcProvider jdbcProvider) {
        this.jdbcProvider = jdbcProvider;
    }

    public List<PortDto> findByCountry(String countryName) {
        String sql = Resource.readSql(CommonChSQL.GET_LIST_PORTS);
        MapSqlParameterSource params = new MapSqlParameterSource("Country", countryName);
        return jdbcProvider.mySQL().query(sql, params, rs -> {
                    List<PortDto> list = new ArrayList<>();
                    while (rs.next()) {
                        PortDto marineTraffic = PortDto.builder()
                                .id(rs.getLong("id"))
                                .country(rs.getString("country"))
                                .port(rs.getString("port"))
                                .unLocode(rs.getString("un_locode"))
                                .photos(rs.getInt("photos"))
                                .vesselsInPort(rs.getInt("vessels_in_port"))
                                .departuresLast24hrs(rs.getInt("departures_last_24hrs"))
                                .arrivalsLast24hrs(rs.getInt("arrivals_last_24hrs"))
                                .expectedArrivals(rs.getTimestamp("expected_arrivals"))
                                .localTime(rs.getTimestamp("local_time"))
                                .relatedAnchorage(rs.getString("related_anchorage"))
                                .areaGlobal(rs.getString("area_global"))
                                .coverage(rs.getString("coverage"))
                                .build();

                        list.add(marineTraffic);
                    }
                    return list;
                }

        );
    }
}
