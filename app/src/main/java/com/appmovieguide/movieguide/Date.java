package com.appmovieguide.movieguide;

import java.io.Serializable;

public class Date {
	
	private String day;
	private String month;
	private String year;

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Date(String day, String month, String year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public Date(String date) {
		String[] addedDate = date.split(" ");
		//System.out.println(date);
		if(addedDate.length == 3) {
			this.day = addedDate[0];
			this.month = addedDate[1];
			this.year = addedDate[2];
		}
		else if(addedDate.length == 1) {
			this.day = "-1";
			this.month = "-1";
			this.year = date;
		}
		else {
			this.day = "-1";
			this.month = addedDate[0];
			this.year = addedDate[1];
		}
		
	}

	@Override
	public String toString() {
		return day + "/" + month + "/" + year ;
	}

	

}
