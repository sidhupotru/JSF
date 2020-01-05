package com.jsf.crud;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.jsf.crud.db.operations.BooksDao;

@ManagedBean
@RequestScoped
public class Booksdata {



	private int id;
	private String book_catagoery;
	private String book_title;
	private String book_price;
	private String first_author;
	private String second_author;

	public ArrayList<Booksdata> booksListFromDB;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getBook_catagoery() {
		return book_catagoery;
	}

	public void setBook_catagoery(String book_catagoery) {
		this.book_catagoery = book_catagoery;
	}

	public String getBook_title() {
		return book_title;
	}

	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}

	public String getBook_price() {
		return book_price;
	}

	public void setBook_price(String book_price) {
		this.book_price = book_price;
	}

	public String getFirst_author() {
		return first_author;
	}

	public void setFirst_author(String first_author) {
		this.first_author = first_author;
	}

	public String getSecond_author() {
		return second_author;
	}

	public void setSecond_author(String second_author) {
		this.second_author = second_author;
	}


	/*
	 * Method Will Avoid Multiple Calls To DB For Fetching The Students Records. If
	 * This Is Not Used & Data Is Fetched From Getter Method, JSF DataTable Will
	 * Make Multiple Calls To DB
	 */
	@PostConstruct
	public void init() {
		booksListFromDB = BooksDao.getBooksListFromDB();
	}

	/* Method Used To Fetch All Records From The Database */
	public ArrayList<Booksdata> booksList() {
		return booksListFromDB;
	}


	//Method Used To Save New Student Record
	public String saveBooksDetails(Booksdata newBookData) 
	{  
		return BooksDao.saveBooksDetailsInDB(newBookData); 

	}

	//	Method Used To Edit Student Record 
	public String editBooksRecord(int id) { 
		return BooksDao.editBooksRecordInDB(id); }

	// Method Used To Update Student Record 
	public String updateBooksDetails(Booksdata updateStudentObj) { 
		return BooksDao.updateBooksDetailsInDB(updateStudentObj); }

	//	Method Used To Delete Student Record 
	public String deleteBooksRecord(int id) { 
		return BooksDao.deleteBooksRecordInDB(id); 
	}

}