package com.synectiks.security.utils;

import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

public class TestUtils {
	public static int getRandomInt() {
		Random random = new Random();
		return random.nextInt(10000);
	}
	
	public static String getRandomString() {
		  int length = 4;
		    boolean useLetters = true;
		    boolean useNumbers = false;
		    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		    return generatedString;
	}
}
