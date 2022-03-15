package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BillBoardList {

	ArrayList<BillBoard> billBoards;
	
	public BillBoardList() {
		billBoards = new ArrayList<>();
	}
	
	public String loadBillBoards(String path) throws IOException {
		boolean flag = false;
		File file1 = new File(path);
		FileReader fr = new FileReader(file1);
		BufferedReader input = new BufferedReader(fr);
		
		input.readLine();
		while(input.ready()) {
			
			String line = input.readLine();
			String[] data = line.split("++");
			int height = Integer.parseInt(data[0]);
			int width = Integer.parseInt(data[1]);
			boolean using = Boolean.parseBoolean(data[2]);
			String brand = data[3];
			addBillBoard(height, width, using, brand);
			flag = true;
		}
		input.close();
		fr.close();
		if(flag == true)
			return "The path was sucessfully added.";
		else	
			return "The path doesn't have elements.";
		
	}
	
	public void addBillBoard(int height, int width, boolean using, String brand) {
		billBoards.add(new BillBoard(height, width, using, brand));
	}
	
	public ArrayList<BillBoard> getBillBoards(){
		return billBoards;
	}
}
