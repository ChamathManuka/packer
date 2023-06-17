package com.travel.backpacker.controllers.endpointcontroller.access;

import com.travel.backpacker.controllers.AccessController;
import com.travel.backpacker.controllers.endpointcontroller.access.LoginData;
import com.travel.backpacker.models.ruser.RPassenger;
import com.travel.backpacker.models.UnknownUser;
import com.travel.backpacker.models.UserWrapper;
import com.travel.backpacker.operations.Operation;
import com.travel.backpacker.operations.factory.UnknownUserOperationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
//@Controller @ResponseBody
public class Access
{
	private final AccessController controller;
	private final UnknownUserOperationFactory factory;

	@Autowired
	public Access( @Qualifier("access.exception.controller") AccessController controller, UnknownUserOperationFactory factory )
	{
		this.controller = controller;
		this.factory = factory;
	}

	@PostMapping("/login")
	public HttpEntity login( @ModelAttribute("wrapper") UserWrapper wrapper, BindingResult bindingResult, @RequestBody LoginData loginData, BindingResult bindingResult1
			, @RequestHeader(name = "Packer-Platform", required = false) String originPlatform )
	{
		Operation<UnknownUser> operation = factory.getLoginOperation( loginData, wrapper, originPlatform );
		if ( bindingResult1.hasErrors() )
		{
			//do something
		}
		return controller.execute( wrapper, operation );
	}

	@PostMapping("/register")
	public HttpEntity register( @ModelAttribute("wrapper") UserWrapper wrapper, @Valid @RequestBody RPassenger rPassenger,
			@RequestHeader(name = "Packer-Platform", required = false) String originPlatform )
	{
		Operation<UnknownUser> operation = factory.getRegisterOperation( rPassenger, wrapper, originPlatform );
		return controller.execute( wrapper, operation );
	}

	@GetMapping("/get/{id}")
	public HttpEntity getUser( @ModelAttribute("wrapper") UserWrapper userWrapper,
			@Pattern(regexp = "[0-9]{6}", message = "Invalid user Id") @PathVariable("id") long id )
	{
		return new ResponseEntity( HttpStatus.OK );
	}
}
