package com.SDETtraining.demo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNGDataProvider {
	
	@Test(dataProvider = "getData")
	public void ExcelTest(String email, String name, String number) {
		System.out.println("NEW RECORD: [" + name + ", " + email + ", " + number + "]");
	}
	
	@DataProvider
	public String[][] getData() {
		return datadriven.Excel.get("C:/Users/Alec/Google Drive/Work/SDET Training/Test Data/dataset.xls");	
	}

}
