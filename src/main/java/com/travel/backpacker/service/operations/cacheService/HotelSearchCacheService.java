package com.travel.backpacker.service.operations.cacheService;

import com.travel.backpacker.dto.rsearch.accommodatioon.HotelDTO;
import com.travel.backpacker.model.accommodation.Hotel;
import com.travel.backpacker.repository.accommodation.HotelDao;
import com.travel.backpacker.service.cache.CacheMonitorService;
import com.travel.backpacker.service.operations.OperationRequiredComponents;
import com.travel.backpacker.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class HotelSearchCacheService {
    @Autowired
    private CacheMonitorService cacheMonitorService;

    @PostConstruct
    public void init() {
        System.out.println("cacheMonitorService: " + (cacheMonitorService == null ? "null" : "Injected"));
    }

    @Cacheable(value = "longLived", key = "#city")
    public List<HotelDTO> getHotelSearchResults(String city, OperationRequiredComponents requiredComponents) {
        cacheMonitorService.printCacheStatistics("shortLived");
        System.out.println("Fetching hotels from the Database for city : " + city);
        HotelDao hotelDao = requiredComponents.getHotelDao();
        for (int x = 0; x < 1000000; x++) { //delayed testing
            int count = 0;
            count += x;
        }
        List<Hotel> hotelList;
        if (StringUtils.isNullOrEmpty(city)) {
            hotelList = hotelDao.findAll();
        } else {
            hotelList = hotelDao.getAllByCity(city);
        }

        return hotelList.stream().map(hotel -> new HotelDTO(hotel.getHotelId(), hotel.getName(), hotel.getStreet(),
                hotel.getCity(), hotel.getState(), hotel.getZip(), hotel.getCountry(), hotel.getWebsite(), hotel.getDescription(), hotel.getStarRating(), hotel.getPhoneNumber(), hotel.getEmail())).toList();
    }

    @CacheEvict(value = "hotels", allEntries = true)
    public void clearHotelCache() {
        System.out.println("clearHotelCache called");
    }
}
