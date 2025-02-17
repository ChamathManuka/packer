package com.travel.backpacker.service.operations;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.travel.backpacker.dto.iuser.AdminUser;
import com.travel.backpacker.dto.UserWrapper;
import com.travel.backpacker.dto.rcontentdownload.RContentType;
import com.travel.backpacker.model.accommodation.Hotel;
import com.travel.backpacker.model.accommodation.Room;
import com.travel.backpacker.model.accommodation.RoomType;
import com.travel.backpacker.repository.accommodation.HotelDao;
import com.travel.backpacker.repository.accommodation.RoomDao;
import com.travel.backpacker.repository.accommodation.RoomTypeDao;
import com.travel.backpacker.utils.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ContentDownloadOperation extends AbstractContentDownloadOperation implements Operation<AdminUser>
{
    private final RContentType type;
    private final HotelDao hotelDao;
    private final RoomDao roomDao;
    private final RoomTypeDao roomTypeDao;
    public ContentDownloadOperation(UserWrapper userWrapper, OperationRequiredComponents requiredComponents, RContentType type) {
        super(userWrapper, requiredComponents);
        this.type = type;
        this.hotelDao = requiredComponents.getHotelDao();
        this.roomDao = requiredComponents.getRoomDao();
        this.roomTypeDao = requiredComponents.getRoomTypeDao();
    }


    @Override
    public HttpEntity execute(AdminUser adminUser, Object... params) {

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStreamHotel = classLoader.getResourceAsStream("hotel_data.csv");
        InputStream inputStreamRoom = classLoader.getResourceAsStream("room_data.csv");
        InputStream inputStreamRoomType = classLoader.getResourceAsStream("room_data.csv");
        if (inputStreamHotel == null || inputStreamRoom == null || inputStreamRoomType == null) {
            System.out.println("Hotel or Room or RoomType data not found");
            return HttpEntity.EMPTY;
        }

        try {

            CSVReader hotelCSVReader = new CSVReader(new InputStreamReader(inputStreamHotel));
            CSVReader roomCSVReader = new CSVReader(new InputStreamReader(inputStreamRoom));
            CSVReader roomTypeCSVReader = new CSVReader(new InputStreamReader(inputStreamRoomType));
            hotelCSVReader.readNext();
            roomCSVReader.readNext();
            roomTypeCSVReader.readNext();

            String[] hotelLine;
            ArrayList<List<String>> hotelList = new ArrayList<>();
            while ((hotelLine = hotelCSVReader.readNext()) != null) {
                hotelList.add(Arrays.asList(hotelLine));
            }
            hotelCSVReader.close();
            int hotelSize = hotelList.size();

            String[] roomLine;
            ArrayList<List<String>> roomList = new ArrayList<>();
            while ((roomLine = roomCSVReader.readNext()) != null) {
                roomList.add(Arrays.asList(roomLine));
            }
            roomCSVReader.close();
            int roomSize = roomList.size();

            String[] roomTypeLine;
            ArrayList<List<String>> roomTypeList = new ArrayList<>();
            while ((roomTypeLine = roomTypeCSVReader.readNext()) != null) {
                roomTypeList.add(Arrays.asList(roomTypeLine));
            }
            roomTypeCSVReader.close();
            saveRoomTypeData(roomTypeList);//saving room types

            ExecutorService hotelExecutorService = Executors.newFixedThreadPool(2);
            List<Callable<Boolean>> hotelCallableList = Arrays.asList(
                    () -> saveHotelData(hotelList.subList(0, hotelSize / 2)),
                    () -> saveHotelData(hotelList.subList(hotelSize / 2, hotelSize))
            );
            List<Future<Boolean>> hotelFutureList = hotelExecutorService.invokeAll(hotelCallableList);
            hotelExecutorService.shutdown();

            for (Future<Boolean> hotelBooleanFuture : hotelFutureList) {
                if (!hotelBooleanFuture.get()) {
                    return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
                }
            }

            ExecutorService roomExecutorService = Executors.newFixedThreadPool(roomSize);
            List<Callable<Boolean>> roomCallableList = Arrays.asList(
                    () -> saveRoomData(roomList.subList(0, roomSize/2)),
                    () -> saveRoomData(roomList.subList(roomSize/2, roomSize))
            );
            List<Future<Boolean>> roomFutureList = roomExecutorService.invokeAll(roomCallableList);
            roomExecutorService.shutdown();

            for (Future<Boolean> roomBooleanFuture : roomFutureList) {
                if (!roomBooleanFuture.get()) {
                    return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
                }
            }

            return new ResponseEntity<>(HttpStatus.OK);


        } catch (FileNotFoundException e) {
            throw new RuntimeException("Required file is not available");
        } catch (InterruptedException | CsvValidationException | IOException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean saveRoomTypeData(List<List<String>> roomTypeList)
    {
        for (List<String> line : roomTypeList)
        {
            RoomType roomType = new RoomType();
            if (StringUtils.isNotNullOrEmpty(line.get(0)) && StringUtils.isNotNullOrEmpty(line.get(1)))
            {
                roomType.setRoomTypeId(Long.parseLong(line.get(0)));
                roomType.setName(line.get(1));
                roomType.setDescription(line.get(2));
                roomType.setAmenities(line.get(3));
                try
                {
                    if(roomDao.findRoomByRoomId(roomType.getRoomTypeId()) == null)
                    {
                        roomTypeDao.save(roomType);
                    }
                }
                catch (RuntimeException e)
                {
                    throw new RuntimeException(e);
                }

            }
        }
        return true;
    }

    private boolean saveRoomData(List<List<String>> arrayList) {
        try {
            for (List<String> line : arrayList) {
                Room room = new Room();
                if (StringUtils.isNotNullOrEmpty(line.get(0)) && StringUtils.isNotNullOrEmpty(line.get(1)) && StringUtils.canBeLong(line.get(0)) && StringUtils.canBeLong(line.get(1))) {
                    room.setRoomId(Long.parseLong(line.get(0)));
                    room.setHotel(hotelDao.findHotelByHotelId(Long.parseLong(line.get(1))));
                    room.setRoomNumber(line.get(2));
                    room.setRoomType(roomTypeDao.findRoomTypeByRoomTypeId(Long.parseLong(line.get(3))));
                    room.setCapacity(Integer.parseInt(line.get(4)));
                    room.setAmenities(line.get(5));
                    room.setPrice(BigDecimal.valueOf(Long.parseLong(line.get(6))));
                    room.setDescription(line.get(7));

                    if(roomDao.findRoomByRoomId(room.getRoomId()) == null)
                    {
                        roomDao.save(room);
                    }

                }
            }
        } catch (RuntimeException e)
        {
            throw new RuntimeException("Exception while adding the data to database");
        }
        return true;
    }

    private boolean saveHotelData(List<List<String>> arrayList) {
        try {
            for (List<String> line : arrayList) {
                Hotel hotel = new Hotel();
                if (StringUtils.isNotNullOrEmpty(line.get(0)) && StringUtils.isNotNullOrEmpty(line.get(1)) && StringUtils.canBeLong(line.get(0))) {
                    hotel.setHotelId(Long.parseLong(line.get(0)));
                    hotel.setName(line.get(1));
                    hotel.setStreet(line.get(2));
                    hotel.setCity(line.get(3));
                    hotel.setState(line.get(4));
                    hotel.setZip(line.get(5));
                    hotel.setCountry(line.get(6));
                    hotel.setPhoneNumber(line.get(7));
                    hotel.setEmail(line.get(8));
                    hotel.setWebsite(line.get(9));
                    hotel.setDescription(line.get(10));
                    hotel.setStarRating(StringUtils.canBeInt(line.get(11)) ? Integer.parseInt(line.get(11)) : 0);
                    hotel.setAmenities(line.get(12));

                    if(hotelDao.findHotelByHotelId(hotel.getHotelId()) == null)
                    {
                        hotelDao.save(hotel);
                    }

                }
            }
        } catch (RuntimeException e)
        {
            throw new RuntimeException("Exception while adding the data to database");
        }
        return true;

    }
}
