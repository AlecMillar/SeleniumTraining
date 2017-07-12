package com.SDETtraining.demo;

public class ReadExcel {

	public static void main(String[] args) {
		String[][] data = datadriven.Excel.get("C:/Users/Alec/Google Drive/Work/SDET Training/Test Data/dataset.xls");
		for (String[] row : data) {
			System.out.print("[");
			for (String element : row) {
				System.out.print(element + " ");
			}
			System.out.println("]");
		}
		
	}

}
