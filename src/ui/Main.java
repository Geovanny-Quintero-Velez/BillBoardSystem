package ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import model.BillBoard;
import model.BillBoardList;

public class Main {
	public static Scanner lector = new Scanner(System.in);
	static BillBoardList bbl;
	
	public static void main(String[] args) throws IOException {
		bbl = new BillBoardList();
		bbl.loadBillBoards("..\\file\\SerializedData1.csv");
		int option;
		boolean continu = true;
		while(continu == true) {
			option = menu();
			switch(option) {
			case 1: importCSVData();
				break;
			case 2: addBillboard();
				break;
			case 3: showBillboards();
				break;
			case 4:exportHazardReport();
				break;
			case 5: continu = false;
				break;
			}
			serialize();
		}
	}
	
	public static int menu() {
		System.out.println("Please enter the option you wish to perform:");
		System.out.println("1. Import csv data.");
		System.out.println("2. Add billboard.");
		System.out.println("3. Show billboards.");
		System.out.println("4. Export hazard report.");
		System.out.println("5. Exit.");
		int option = lector.nextInt();
		lector.nextLine();
		return option;
	}
	
	public static void importCSVData(){
		System.out.println("Please enter the absolute path of the CSV file you want to import.");
		String path = lector.nextLine();
		try {
			System.out.println(bbl.loadBillBoards(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addBillboard() {
		System.out.println("Please enter the information of the billboard separed by ++. For example: \n 200++300++true++Mister Wings. ");
		String info = lector.nextLine();
		String[] data = info.split("\\+\\+");
		int height = Integer.parseInt(data[0]);
		int width = Integer.parseInt(data[1]);
		boolean using = Boolean.parseBoolean(data[2]);
		String brand = data[3];
		bbl.addBillBoard(height, width, using, brand);
		
	}
	
	public static void showBillboards() {
		System.out.println("The billboards are: ");
		ArrayList<BillBoard> billboarsito = bbl.getBillBoards();
		for(int i=0;i<billboarsito.size();i++) {
			System.out.println("Height: \n"+billboarsito.get(i).getHeight());
			System.out.println("Width: \n"+billboarsito.get(i).getWidth());
			System.out.println("In use: \n"+billboarsito.get(i).isUsing());
			System.out.println("Brand: \n"+billboarsito.get(i).getBrand());
			System.out.println("-----------------------------------");
		}
		System.out.println("TOTAL: "+billboarsito.size()+" vallas.");
	}
	
	public static void exportHazardReport() {
		try {
			File file = new File("..\\file\\report.txt");
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			
			String line = "===========================";
			bw.write(line);
			bw.newLine();
			System.out.println(line);
			line = "DANGEROUS BILLBOARD REPORT ";
			bw.write(line);
			bw.newLine();
			System.out.println(line);
			line = "===========================";
			bw.write(line);
			bw.newLine();
			System.out.println(line);
			ArrayList<BillBoard> billboards = bbl.getBillBoards();
			for(int i = 0;i<billboards.size();i++) {
				if(BillBoard.DANGEROUS_AREA<(billboards.get(i).getHeight()*billboards.get(i).getWidth())) {
					line = i+" Billboard <"+billboards.get(i).getBrand()+"> with area <"+(billboards.get(i).getHeight()*billboards.get(i).getWidth())+">.";
					bw.write(line);
					bw.newLine();
					System.out.println(line);
				}
			}
			bw.close();
			fw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void serialize() throws IOException {
		File file = new File("..\\file\\SerializedData1.csv");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(bbl.getBillBoards());
		oos.close();
		fos.close();
		
		System.out.println("Serialized.");
	}
}
