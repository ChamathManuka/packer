package com.travel.backpacker.schedulers.content;

import com.travel.backpacker.dao.schedulers.content.HotelDao;
import com.travel.backpacker.entity.schedulers.content.Hotel;
import com.travel.backpacker.operations.OperationRequiredComponents;
import com.travel.backpacker.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Component
public class AccomContentDownloader extends ContentDownloader
{
	BufferedReader bufferedReader;
	private HotelDao hotelDao = operationRequiredComponents.getHotelDao();

	protected AccomContentDownloader( OperationRequiredComponents operationRequiredComponents )
	{
		super( operationRequiredComponents );
	}
	public HttpEntity execute()
	{
		try
		{
			bufferedReader = new BufferedReader( new FileReader( "accom_data.csv" ) );
			int count = (int) bufferedReader.lines().count() ;
			ArrayList<String> arrayList = new ArrayList<>(bufferedReader.lines().collect( Collectors.toList()) );
			ExecutorService executorService = Executors.newFixedThreadPool( 2 );
			List<Callable<Boolean>> callableList = Arrays.asList(
					() -> saveData( arrayList.subList( 0, count/2 ) ),
					() -> saveData( arrayList.subList( count/2, count ) )
			);
			List<Future<Boolean>> futureList = executorService.invokeAll( callableList );
			executorService.shutdown();

			for(Future<Boolean> booleanFuture: futureList)
			{
				if(!booleanFuture.get())
				{
					return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
				}
			}
			return new ResponseEntity<>( HttpStatus.OK );


		}
		catch ( FileNotFoundException e )
		{
			throw new RuntimeException( "Required file is not available" );
		}
		catch ( InterruptedException e )
		{
			throw new RuntimeException( e );
		}
		catch ( ExecutionException e )
		{
			throw new RuntimeException( e );
		}

	}

	private boolean saveData(List<String> arrayList)
	{
		boolean status = true;
		for( String line : arrayList)
		{
			try
			{
				String[] dataLine = line.split( "," );
				Hotel hotel = new Hotel();
				if(StringUtils.isNotNullOrEmpty( dataLine[0] ) && StringUtils.isNotNullOrEmpty( dataLine[5] ))
				{
					hotel.setName( dataLine[0] );
					hotel.setAddressLine1( StringUtils.isNotNullOrEmpty( dataLine[1] ) ? dataLine[1] : "" );
					hotel.setAddressLine2( StringUtils.isNotNullOrEmpty( dataLine[2] ) ? dataLine[2] : "" );
					hotel.setAddressLine3( StringUtils.isNotNullOrEmpty( dataLine[3] ) ? dataLine[3] : "" );
					hotel.setState( StringUtils.isNotNullOrEmpty( dataLine[4] ) ? dataLine[4] : "" );
					hotel.setCountry( dataLine[5] );
					hotel.setPostalCode( StringUtils.isNotNullOrEmpty( dataLine[6] ) ? dataLine[6] : "" );
					hotel.setPhone( StringUtils.isNotNullOrEmpty( dataLine[7] ) ? dataLine[7] : "" );
					hotel.setEmail( StringUtils.isNotNullOrEmpty( dataLine[8] ) ? dataLine[8] : "" );
					hotel.setCityCode( StringUtils.isNotNullOrEmpty( dataLine[9] ) ? dataLine[9] : "" );
					hotel.setSupplierCode( StringUtils.isNotNullOrEmpty( dataLine[10] ) ? dataLine[10] : "" );
					hotelDao.save( hotel );
				}
			}
			catch ( RuntimeException e )
			{
				status = false;
				throw new RuntimeException("Exception while adding the data to database");
			}
		}
		return status;
	}

}
