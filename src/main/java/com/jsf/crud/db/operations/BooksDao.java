package com.jsf.crud.db.operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.jsf.crud.Booksdata;
import com.jsf.crud.ReadXMLFile2;

public class BooksDao {

	/* Method To Fetch The Books Records From Database */
	public static ArrayList<Booksdata> getBooksListFromDB() {
		ReadXMLFile2 xmlData = new  ReadXMLFile2();
		ArrayList<Booksdata> booksList = new ArrayList<Booksdata>();
		try {
			
			xmlData.xmlRead();

			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","sidhu","Sidhu@9677");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet resultSetObj=stmt.executeQuery("SELECT * FROM booksdata where (book_catagoery IS NOT NULL and book_title IS NOT NULL and book_price  IS NOT NULL and first_author IS NOT NULL and second_author IS NOT NULL) GROUP BY book_catagoery; ");  
			while (resultSetObj.next()) {
				Booksdata bookObj = new Booksdata();
				bookObj.setId(resultSetObj.getInt("id"));
				bookObj.setBook_catagoery(resultSetObj.getString("book_catagoery"));
				bookObj.setBook_title(resultSetObj.getString("book_title"));
				bookObj.setBook_price(resultSetObj.getString("book_price"));
				bookObj.setFirst_author(resultSetObj.getString("first_author"));
				bookObj.setSecond_author(resultSetObj.getString("second_author"));
				booksList.add(bookObj);
			}
			System.out.println("Total Records Fetched: " + booksList.size());
			con.close();
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
		}
		return booksList;
	}

	/* Method Used To Save New Books Record In Database */
	public static String saveBooksDetailsInDB(Booksdata newBookObj) {
		int saveResult = 0;
		String navigationResult = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "sidhu", "Sidhu@9677");
			PreparedStatement stmt = 
					con.prepareStatement("insert into BooksData (book_catagoery, book_title, book_price, first_author, second_author) values (?, ?, ?, ?, ?)");
			stmt.setString(1, newBookObj.getBook_catagoery());
			stmt.setString(2, newBookObj.getBook_title());
			stmt.setString(3, newBookObj.getBook_price());
			stmt.setString(4, newBookObj.getFirst_author());
			stmt.setString(5, newBookObj.getSecond_author());
			saveResult = stmt.executeUpdate();
			stmt.close();
			System.out.println("saveBookDetails+"+saveResult);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
		}
		if (saveResult != 0) {
			navigationResult = "bookList.xhtml?faces-redirect=true";
		} else {
			navigationResult = "createBookdata.xhtml?faces-redirect=true";
		}
		return navigationResult;
	}

	/* Method Used To Edit Books Record In Database */

	public static String editBooksRecordInDB(int id) {
		Booksdata
		editRecord = null;
		System.out.println("editBooksRecordInDB() :  Id: " + id);

		//  Setting The Particular Books Details In Session 
		Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		try { 
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "sidhu", "Sidhu@9677");
			Statement stmtObj=con.createStatement(); 

			ResultSet resultSetObj = stmtObj.executeQuery("select * from BOOKSDATA where id = " +id);

			if (resultSetObj != null) {
				resultSetObj.next(); 
				editRecord = new Booksdata(); 

				editRecord.setId(resultSetObj.getInt("id"));
				editRecord.setBook_catagoery(resultSetObj.getString("book_catagoery"));
				editRecord.setBook_title(resultSetObj.getString("book_title"));
				editRecord.setBook_price(resultSetObj.getString("book_price"));
				editRecord.setFirst_author(resultSetObj.getString("first_author"));
				editRecord.setSecond_author(resultSetObj.getString("second_author")); 
			}
			sessionMapObj.put("editRecordObj", editRecord);
			con.close();
		}
		catch(Exception sqlException) 
		{ sqlException.printStackTrace(); 
		}
		return "/editBooks.xhtml?faces-redirect=true"; 
	}

	//	  Method Used To Update Books Record In Database 
	public static String updateBooksDetailsInDB(Booksdata updateBooksObj) { 

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "sidhu", "Sidhu@9677");
			PreparedStatement pstmt = con.prepareStatement("update BOOKSDATA set book_catagoery=?, book_title=?, book_price=?, first_author=?, second_author=? where id=?");
			pstmt.setString(1,updateBooksObj.getBook_catagoery());
			pstmt.setString(2,updateBooksObj.getBook_title()); 
			pstmt.setString(3,updateBooksObj.getBook_price()); 
			pstmt.setString(4,updateBooksObj.getFirst_author()); 
			pstmt.setString(5,updateBooksObj.getSecond_author()); 
			pstmt.setInt(6, updateBooksObj.getId());
			pstmt.executeUpdate();
			con.close(); 
		} catch (Exception sqlException) {
			sqlException.printStackTrace(); 
		} return "/bookList.xhtml?faces-redirect=true";
	}

	//	  Method Used To Delete Books Record From Database 
	public static String deleteBooksRecordInDB(int id) {
		System.out.println("deleteBooksRecordInDB() :  Id: " + id);
		try { 
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "sidhu", "Sidhu@9677");
			PreparedStatement pstmt = con.prepareStatement("delete from BOOKSDATA where id = " +id); 
			pstmt.executeUpdate();
			con.close(); 
		} catch (Exception sqlException) {
			sqlException.printStackTrace(); 
		} return "/bookList.xhtml?faces-redirect=true"; 
	}


}