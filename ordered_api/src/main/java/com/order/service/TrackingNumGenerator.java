package com.order.service;

import java.security.SecureRandom;

public class TrackingNumGenerator {

	private static final String CHARACTERS="abcdefghijklmnopqrstuvwxyz0123456789";
	private static final SecureRandom random=new SecureRandom();
	
	
	public static String generateTrakingnum()
	{
		StringBuilder trackingnum=new StringBuilder();
		
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<5;j++)
			{
				trackingnum.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
			}
			if(i<2)
			{
				trackingnum.append("-");
			}
		}
		return trackingnum.toString();
	}
	
}
