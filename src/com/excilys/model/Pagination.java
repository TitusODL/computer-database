package com.excilys.model;

import java.util.ArrayList;

import com.excilys.ui.MenuCLI;

public class Pagination {
	
	private int actualPageNb;
	private int pageSize;
	private int maxPages;
	
	public Pagination(int maxEntities) {
		this.actualPageNb = 0;
		this.pageSize = 15;
		this.maxPages = maxEntities / this.pageSize;
	}

	public void nextPage() {
		if(this.actualPageNb < this.maxPages) {
			this.actualPageNb++;
		}
	}

	public void PrevPage() {
		if(this.actualPageNb>0) {
			this.actualPageNb--;
		}
	}
	
	public <T> void displayPageContent(ArrayList<T> pageContent) {
		System.out.println("*-------------------------------------------------------------*");
		MenuCLI.displayArrayList(pageContent);
		System.out.println("*-------------------------------------------------------------*");
	}

	public int getActualPageNb() {
		return actualPageNb;
	}

	public void setActualPageNb(int actualPageNb) {
		this.actualPageNb = actualPageNb;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getMaxPages() {
		return maxPages;
	}

	public void setMaxPages(int maxPages) {
		this.maxPages = maxPages;
	}
	
	
}