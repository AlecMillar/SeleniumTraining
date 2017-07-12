package datadriven;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSV {
	
	// This method will read and return data from a CSV file
	public static List<String[]> get(String filename) {
		List<String[]> data = new ArrayList<String[]>();
		String testRow;
		try {
			// Open and read the file
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(filename));
			// Reads data as long as there is something to read
			// Parses data by comma using .split
			// Place data in array
			while ((testRow = br.readLine()) != null) {
				data.add(testRow.split(","));
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File not found " + filename);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR: Could not read " + filename);
		}
		
		return data;
	}

}
