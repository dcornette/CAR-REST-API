package com.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.example.config.Constants;
import com.example.services.FtpService;

public class TestDisconnectUser {

	@Test
	public void testGoodReturnMessageWhenDisconnect() {
		FtpService ftpservice = new FtpService();
		assertEquals("<html><h4>You're now disconnected</h4>"+ Constants.COME_BACK +"</html>", 
				ftpservice.disconnect());
	}
	
	@Test
	public void testWellDisconnected() {
		FtpService ftpservice = new FtpService();
		ftpservice.connect("test", "test");
		ftpservice.disconnect();
		assertEquals("<html><h1>Impossible to list</h1>"+ Constants.COME_BACK +"</html>", 
				ftpservice.list(""));
	}	
}