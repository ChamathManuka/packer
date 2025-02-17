package com.travel.backpacker.service.operations;

import com.travel.backpacker.repository.PassengerDao;
import com.travel.backpacker.model.Address;
import com.travel.backpacker.model.Passenger;
import com.travel.backpacker.dto.ruser.RAddress;
import com.travel.backpacker.dto.ruser.RPassenger;
import com.travel.backpacker.dto.iuser.UnknownUser;
import com.travel.backpacker.dto.UserWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.Locale;

public class PassengerRegisterOperation extends AbstractAccessOperation implements Operation<UnknownUser>
{
	private final RPassenger rPassenger;
	private final PassengerDao passengerDao = requiredComponents.getPassengerDao();
	private final String plaform;

	private final SCryptPasswordEncoder passwordEncorder = requiredComponents.getPasswordEncorder();

	public PassengerRegisterOperation(UserWrapper userWrapper, OperationRequiredComponents requiredComponents, RPassenger rPassenger, String plaform )
	{
		super( userWrapper, requiredComponents );
		this.rPassenger = rPassenger;
		this.plaform = plaform;

	}

	@Override
	public ResponseEntity execute( UnknownUser unknownUser, Object... params )
	{
		Passenger passenger = passengerDao.save( createPassenger( rPassenger ) );
		return new ResponseEntity<>( passenger, HttpStatus.CREATED );

	}

	private Passenger createPassenger( RPassenger rPassenger )
	{
		Passenger passenger = new Passenger();
		passenger.setId( rPassenger.getId() );
		passenger.setFirstName( rPassenger.getFirstname() );
		passenger.setLastName( rPassenger.getLastname() );
		passenger.setUsername( rPassenger.getUsername().toLowerCase( Locale.ENGLISH ) );
		CharSequence seq = java.nio.CharBuffer.wrap( rPassenger.getPassword() );
		char[] encodedPassword = passwordEncorder.encode( seq ).toCharArray();
		passenger.setPassword( encodedPassword );
		passenger.setEmail( rPassenger.getEmail() );
		passenger.setPhone( rPassenger.getPhone() );
		passenger.setPlatform( rPassenger.getPlatform() );
		passenger.setAddress( createAddress( rPassenger.getrAddress() ) );
		return passenger;
	}

	private Address createAddress( RAddress rAddress )
	{
		Address address = new Address();
		address.setCity( rAddress.getCity() );
		address.setCounty( rAddress.getCounty() );
		address.setLine1( rAddress.getLine1() );
		address.setLine2( rAddress.getLine2() );
		address.setPostcode( rAddress.getPostcode() );
		return address;
	}
}
