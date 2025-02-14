package com.travel.backpacker.controller.accesscontroller;

import com.travel.backpacker.controller.AccessController;
import com.travel.backpacker.dto.*;
import com.travel.backpacker.dto.iuser.AdminUser;
import com.travel.backpacker.dto.iuser.UnknownUser;
import com.travel.backpacker.dto.ruser.RAdmin;
import com.travel.backpacker.dto.ruser.RPassenger;
import com.travel.backpacker.service.operations.Operation;
import com.travel.backpacker.service.operations.factory.AdminUserOperationFactory;
import com.travel.backpacker.service.operations.factory.UnknownUserOperationFactory;
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
public class SignController
{
	private final AccessController<UnknownUser> unknownUserAccessController;
	private final AccessController<AdminUser> adminUserAccessController;
	private final UnknownUserOperationFactory unknownUserOperationFactory;
	private final AdminUserOperationFactory adminUserOperationFactory;

	@Autowired
	public SignController(@Qualifier("access.exception.controller") AccessController<UnknownUser> unknownUserAccessController, @Qualifier("access.exception.controller")AccessController<AdminUser> adminUserAccessController, UnknownUserOperationFactory unknownUserOperationFactory, AdminUserOperationFactory adminUserOperationFactory )
	{
		this.unknownUserAccessController = unknownUserAccessController;
        this.adminUserAccessController = adminUserAccessController;
        this.unknownUserOperationFactory = unknownUserOperationFactory;
		this.adminUserOperationFactory = adminUserOperationFactory;
	}

	@PostMapping("/login")
	public HttpEntity login(@ModelAttribute("wrapper") UserWrapper wrapper, BindingResult bindingResult, @RequestBody LoginData loginData, BindingResult bindingResult1
			, @RequestHeader(name = "Packer-Platform", required = false) String originPlatform )
	{
		Operation<UnknownUser> operation = unknownUserOperationFactory.getLoginOperation( loginData, wrapper, originPlatform );
		if ( bindingResult1.hasErrors() )
		{
			//do something
		}
		return unknownUserAccessController.execute( wrapper, operation );
	}

	@PostMapping("/register/user")
	public HttpEntity registerUser(@ModelAttribute("wrapper") UserWrapper wrapper, @Valid @RequestBody RPassenger rPassenger,
								   @RequestHeader(name = "Packer-Platform", required = false) String originPlatform )
	{
		Operation<UnknownUser> operation = unknownUserOperationFactory.getPassengerRegisterOperation( rPassenger, wrapper, originPlatform );
		return unknownUserAccessController.execute( wrapper, operation );
	}

	@PostMapping("/register/admin")
	public HttpEntity registerAdmin(@ModelAttribute("wrapper") UserWrapper wrapper, @Valid @RequestBody RAdmin rAdmin,
								   @RequestHeader(name = "Packer-Platform", required = false) String originPlatform )
	{
		Operation<AdminUser> operation = adminUserOperationFactory.getAdminRegisterOperation( rAdmin, wrapper, originPlatform );
		return adminUserAccessController.execute( wrapper, operation );
	}

	@GetMapping("/get/{id}")
	public HttpEntity getUser( @ModelAttribute("wrapper") UserWrapper userWrapper,
			@Pattern(regexp = "[0-9]{6}", message = "Invalid user Id") @PathVariable("id") long id )
	{
		return new ResponseEntity( HttpStatus.OK );
	}
}
