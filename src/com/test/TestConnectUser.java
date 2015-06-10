package com.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.example.config.Constants;
import com.example.services.FtpService;

public class TestConnectUser {

	@Test
	public void testGoodUser() {
		FtpService ftpservice = new FtpService();
		assertEquals("<html><h1>You're now login in test</h1>"+ Constants.COME_BACK +"</html>", 
				ftpservice.connect("test", "test"));
	}
	
	@Test
	public void testGoodUserAnonymous() {
		FtpService ftpservice = new FtpService();
		assertEquals("<html><h1>You're now login in anonymous</h1>"+ Constants.COME_BACK +"</html>", 
				ftpservice.connect("anonymous", ""));
	}	
	
	@Test
	public void testBadUser() {
		FtpService ftpservice = new FtpService();
		assertEquals("<html><h1>Fail to login</h1>"+ Constants.COME_BACK +"</html>", 
				ftpservice.connect("fake", "fake"));
	}	
}