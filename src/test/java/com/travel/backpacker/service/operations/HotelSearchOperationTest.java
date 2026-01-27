package com.travel.backpacker.service.operations;


import com.travel.backpacker.dto.UserWrapper;
import com.travel.backpacker.dto.iuser.UnknownUser;
import com.travel.backpacker.dto.rsearch.RSearch;
import com.travel.backpacker.dto.rsearch.accommodatioon.HotelDTO;
import com.travel.backpacker.service.operations.cacheService.HotelSearchCacheService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HotelSearchOperationTest {

    @Mock
    private HotelSearchCacheService hotelSearchCacheService;

    @Mock
    private OperationRequiredComponents operationRequiredComponents;

    @Mock
    private UserWrapper userWrapper;

    @Mock
    private RSearch rSearch;

    @Spy
    @InjectMocks
    private HotelSearchOperation hotelSearchOperation;

    @BeforeEach
    void setUp() {
        when(userWrapper.getrSearch()).thenReturn(rSearch);
        when(rSearch.getCity()).thenReturn("San Francisco");
        when(operationRequiredComponents.getHotelSearchCacheService()).thenReturn(hotelSearchCacheService);
        // Stub the hotelSearchCacheService to return a sample list when called with "San Francisco"
        List<HotelDTO> sampleHotels = new ArrayList<>();
        when(hotelSearchCacheService.getHotelSearchResults("San Francisco", operationRequiredComponents))
                .thenReturn(sampleHotels);

        hotelSearchOperation = spy(new HotelSearchOperation(userWrapper, operationRequiredComponents));
    }

    @Test
    void testHotelSearchOperation() {

        HttpEntity<List<HotelDTO>> resultsEntity = hotelSearchOperation.execute(new UnknownUser());

        assertNotNull(resultsEntity);
        assertInstanceOf(ResponseEntity.class, resultsEntity);

    }
}
