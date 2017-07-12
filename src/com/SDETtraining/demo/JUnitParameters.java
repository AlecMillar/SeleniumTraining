package com.SDETtraining.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class JUnitParameters {
	
	String name;
	String email;
	String phone;
	
	// Test method here
	@Test
	public void RunnerTest() {
		System.out.println("NEW RECORD\n----------" + "\n" + name + "\n" + email + "\n" + phone + "\n");
	}
	
	// This annotated method is designed to pass parameters into the class
	@Parameters
	public static List<String[]> getTestData() {
		return datadriven.CSV.get("C:/Users/Alec/Google Drive/Work/SDET Training/Test Data/dataset.csv");
	}
	
	// Constructor method:
	// Takes parameters from the @Parameters method
	// Parse each element into the arguments
	// Iterates this for each record
	public JUnitParameters(String name, String email, String phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

}
