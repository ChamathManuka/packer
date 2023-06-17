package com.travel.backpacker.models;

import java.util.ArrayList;
import java.util.List;

public class UserWrapper
{
	private long adminId;
	private long operatorId;
	private long supplierId;
	private long passengerId;
	private long unknownUserId;
	private boolean active;

	private String remoteAddress;
	private List<User.UserType> roles = new ArrayList<>();

	//default constructor to support model attribute
	private UserWrapper()
	{
	}

	private UserWrapper( long adminId, long operatorId, long supplierId, long passengerId, long unknownUserId,
			boolean active )
	{
		this.adminId = adminId > 0L ? adminId : 0L;
		this.operatorId = operatorId > 0L ? operatorId : 0L;
		this.supplierId = supplierId > 0L ? supplierId : 0L;
		this.passengerId = passengerId > 0L ? passengerId : 0L;
		this.unknownUserId = unknownUserId > 0L ? unknownUserId : 0L;
		this.active = active;
		if ( this.adminId != 0L )
		{
			roles.add( User.UserType.ADMIN );
		}
		if ( this.operatorId != 0L )
		{
			roles.add( User.UserType.OPERATOR );
		}
		if ( this.supplierId != 0L )
		{
			roles.add( User.UserType.SUPPLIER );
		}
		if ( this.passengerId != 0L )
		{
			roles.add( User.UserType.PASSENGER );
		}
		if ( this.unknownUserId != 0L )
		{
			roles.add( User.UserType.UNKNOWNUSER );
		}
	}

	public long getAdminId()
	{
		return adminId;
	}

	public void setAdminId( long adminId )
	{
		this.adminId = adminId;
	}

	public long getUnknownUserId()
	{
		return unknownUserId;
	}

	public void setUnknownUserId( long unknownUserId )
	{
		this.unknownUserId = unknownUserId;
	}

	public long getPassengerId()
	{
		return passengerId;
	}

	public void setPassengerId( long passengerId )
	{
		this.passengerId = passengerId;
	}

	public long getOperatorId()
	{
		return operatorId;
	}

	public void setOperatorId( long operatorId )
	{
		this.operatorId = operatorId;
	}

	public long getSupplierId()
	{
		return supplierId;
	}

	public void setSupplierId( long supplierId )
	{
		this.supplierId = supplierId;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive( boolean active )
	{
		this.active = active;
	}

	public String getRemoteAddress()
	{
		return remoteAddress;
	}

	public void setRemoteAddress( String remoteAddress )
	{
		this.remoteAddress = remoteAddress;
	}

	public List<User.UserType> getRoles()
	{
		return roles;
	}

	public void setRoles( List<User.UserType> roles )
	{
		this.roles = roles;
	}

	public static class Builder
	{
		private long adminId;
		private long operatorId;
		private long supplierId;
		private long passengerId;
		private long unknownUserId;
		private boolean active = true;

		public UserWrapper buildWrapper()
		{
			return new UserWrapper( adminId, operatorId, supplierId, passengerId, unknownUserId, active );
		}
	}
}
