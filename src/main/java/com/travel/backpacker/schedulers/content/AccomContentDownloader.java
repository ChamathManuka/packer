package com.travel.backpacker.schedulers.content;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.travel.backpacker.dao.schedulers.content.HotelDao;
import com.travel.backpacker.entity.schedulers.content.Hotel;
import com.travel.backpacker.operations.OperationRequiredComponents;
import com.travel.backpacker.utils.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@Component
public class AccomContentDownloader extends ContentDownloader {
    private HotelDao hotelDao = operationRequiredComponents.getHotelDao();

    protected AccomContentDownloader(OperationRequiredComponents operationRequiredComponents) {
        super(operationRequiredComponents);
    }

    public HttpEntity execute() {
        try {
            CSVReader hotelCSVReader = new CSVReader(new FileReader("accom_data.csv"));
            String[] nextLine;
            ArrayList<List<String>> hotelList = new ArrayList();
            while ((nextLine = hotelCSVReader.readNext()) != null) {
                hotelList.add(Arrays.asList(nextLine));
            }
            hotelCSVReader.close();
            int size = hotelList.size();
            ExecutorService executorService = Executors.newFixedThreadPool(2);
            List<Callable<Boolean>> callableList = Arrays.asList(
                    () -> saveData(hotelList.subList(0, size / 2)),
                    () -> saveData(hotelList.subList(size / 2, size))
            );
            List<Future<Boolean>> futureList = executorService.invokeAll(callableList);
            executorService.shutdown();

            for (Future<Boolean> booleanFuture : futureList) {
                if (!booleanFuture.get()) {
                    return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
                }
            }
            return new ResponseEntity<>(HttpStatus.OK);


        } catch (FileNotFoundException e) {
            throw new RuntimeException("Required file is not available");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean saveData(List<List<String>> arrayList) {
        boolean status = false;
        try {
            for (List<String> line : arrayList) {
                Hotel hotel = new Hotel();
                if (StringUtils.isNotNullOrEmpty(line.get(0)) && StringUtils.isNotNullOrEmpty(line.get(5))) {
                    hotel.setName(line.get(0));
                    hotel.setAddressLine1(StringUtils.isNotNullOrEmpty(line.get(1)) ? line.get(1) : "");
                    hotel.setAddressLine2(StringUtils.isNotNullOrEmpty(line.get(2)) ? line.get(2) : "");
                    hotel.setAddressLine3(StringUtils.isNotNullOrEmpty(line.get(3)) ? line.get(3) : "");
                    hotel.setState(StringUtils.isNotNullOrEmpty(line.get(4)) ? line.get(4) : "");
                    hotel.setCountry(line.get(5));
                    hotel.setPostalCode(StringUtils.isNotNullOrEmpty(line.get(6)) ? line.get(6) : "");
                    hotel.setPhone(StringUtils.isNotNullOrEmpty(line.get(7)) ? line.get(7) : "");
                    hotel.setEmail(StringUtils.isNotNullOrEmpty(line.get(8)) ? line.get(8) : "");
                    hotel.setCityCode(StringUtils.isNotNullOrEmpty(line.get(9)) ? line.get(9) : "");
                    hotel.setSupplierCode(StringUtils.isNotNullOrEmpty(line.get(10)) ? line.get(10) : "");
                    hotelDao.save(hotel);
                }
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Exception while adding the data to database");
        } finally {
            status = true;

        }
        return status;
    }

}
