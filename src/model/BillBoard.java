package model;

import java.io.Serializable;

public class BillBoard implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int DANGEROUS_AREA = 200000;

	private int height;
	private int width;
	private boolean using;
	private String brand;
	
	public BillBoard(int height, int width, boolean using, String brand) {
		this.height = height;
		this.width = width;
		this.using = using;
		this.brand = brand;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public boolean isUsing() {
		return using;
	}

	public void setUsing(boolean using) {
		this.using = using;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
