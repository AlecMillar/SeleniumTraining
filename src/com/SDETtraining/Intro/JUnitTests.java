package com.SDETtraining.Intro;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JUnitTests {

	/*
	 * JUnit is a Testing Framework
	 * Pre-built test reporting and test configuration
	 * Guides our test program
	 */
	
	@Test
	public void testOne() {
		System.out.println("TEST 1: I'm running a WebDriver test to create an account");
	}
	@Test
	public void testTwo() {
		System.out.println("TEST 2: I'm running a WebDriver test to create an account");
		// DO SOME WORK
		// Test some condition
		// If condition passes; THEN GOOD
		// If it fails, then perform the fail method
		fail("Could not execute this test");
	}
	@Test
	public void testThree() {
		System.out.println("TEST 3: I'm running a WebDriver test to create an account");
	}

	// Generally put @Before & @After after all tests
	
	// @Before will tell the tester to run this block before every test
		@Before
		public void setUp() {
			System.out.println("Set browser settings and open the browser");
		}
		
	//@After will tell the tester to run this block after every test
		@After
		public void tearDown() {
			System.out.println("Closing the browser, writing results to file, generally ending test.");
		}
		
}
