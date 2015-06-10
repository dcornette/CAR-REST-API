package com.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.example.config.Constants;
import com.example.services.FtpService;

public class TestListFiles {

	@Test
	public void testGoodList() {
		FtpService ftpservice = new FtpService();
		ftpservice.connect("test", "test");
		assertTrue(ftpservice.list("") != "<html><h1>Impossible to list</h1>"+ Constants.COME_BACK +"</html>");
	}
	
	@Test
	public void testListWithoutUserConnection() {
		FtpService ftpservice = new FtpService();
		assertEquals("<html><h1>Impossible to list</h1>"+ Constants.COME_BACK +"</html>", ftpservice.list(""));
	}	
}