package com.travel.backpacker.service.operations;

import com.travel.backpacker.dto.UserWrapper;
import com.travel.backpacker.dto.iuser.UnknownUser;
import com.travel.backpacker.dto.rsearch.accommodatioon.HotelDTO;
import com.travel.backpacker.model.accommodation.Hotel;
import com.travel.backpacker.repository.accommodation.HotelDao;
import com.travel.backpacker.service.cache.CacheMonitorService;
import com.travel.backpacker.service.operations.cacheService.HotelSearchCacheService;
import com.travel.backpacker.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class HotelSearchOperation extends AbstractSearchOperation implements Operation<UnknownUser> {


    private final HotelSearchCacheService hotelSearchCacheService;

    @Autowired
    public HotelSearchOperation(UserWrapper userWrapper, OperationRequiredComponents requiredComponents){
        super(userWrapper, requiredComponents);
        hotelSearchCacheService = new HotelSearchCacheService();
    }

    @Override
    public HttpEntity<List<HotelDTO>> execute(UnknownUser unknownUser, Object... params)
    {
        String city = userWrapper.getrSearch().getCity();
        return new ResponseEntity<>(hotelSearchCacheService.getHotelSearchResults(city, requiredComponents), HttpStatus.OK);

    }

}
