package com.SDETtraining.demo;

import java.util.List;

public class ReadCSV {

	public static void main(String[] args) {
		List<String[]> newUsers = datadriven.CSV.get("C:/Users/Alec/Google Drive/Work/SDET Training/Test Data/dataset.csv");
		for (String[] user : newUsers) {
			System.out.println("NEW USER");
			System.out.println("----------");

			for (int i=0; i<user.length; i++) {
				System.out.print(user[i] + ", ");
			}
			System.out.println("\n");

		}
	}
}