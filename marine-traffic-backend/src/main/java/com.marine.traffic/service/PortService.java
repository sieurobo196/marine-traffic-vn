package com.marine.traffic.service;

import com.marine.traffic.dao.PortDao;
import com.marine.traffic.model.PortDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortService {
    private final PortDao portDao;

    public PortService(PortDao portDao) {
        this.portDao = portDao;
    }

    public List<PortDto> getPortsByCountry(String country) {
        return portDao.findByCountry(country);
    }
}
