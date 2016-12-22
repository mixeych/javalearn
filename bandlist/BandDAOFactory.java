package com.company.bandlist;

public class BandDAOFactory
{
	
	public static BandDAO getBandDAO()
	{
		return new BandSimpleDAO();
	}
}
