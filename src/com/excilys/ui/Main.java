package com.excilys.ui;

import com.excilys.persistence.MysqlConnect;

public class Main {

	public static void main(String[] args) {
	MysqlConnect msc = MysqlConnect.getDbCon();
	System.out.println("Choose an option below :");
	System.out.println("a - List computers");
	System.out.println("b - List companies");
	System.out.println("c - Show computer details");
	System.out.println("d - Create a computer");
	System.out.println("e - Update a computer");
	System.out.println("e - Delete a computer");

}
}
