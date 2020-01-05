package com.jsf.crud;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ReadXMLFile2 {
	 static Booksdata   booksService = new Booksdata();
 	

  public static void main(String args[]){
	
	try {
		new ReadXMLFile2().xmlRead();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

  }

  public void xmlRead() throws IOException {
	  
	 
	    try {
	    	String xmlfi = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><bookstore><book category=\"cooking\"><title lang=\"en\">Everyday Italian</title><year>2005</year><price>30.00</price><authors><author>Giada De Laurentiis</author><author>Sam T. Bruce</author></authors></book><book category=\"children\"><title lang=\"en\">Harry Potter</title><year>2005</year><price>29.99</price><authors><author>J K. Rowling</author><author>Erik T. Ray</author></authors></book><book category=\"web\"><title lang=\"en\">Learning XML</title><year>2003</year><price>39.95</price><authors><author>Erik T. Ray</author></authors></book></bookstore>" ;

	    //	String content = new String(Files.readAllBytes(Paths.get("src/main/resources/Books.xml")), "UTF-8");
	   // 	BufferedReader br = new BufferedReader(new FileReader(new File("src/main/resources/Books.xml")));

	    	File file = new File("C:\\Users\\sidhu\\Desktop\\Books.xml"); 
	    	  
	    	  BufferedReader br = new BufferedReader(new FileReader(file)); 
	    	  
	    	  
		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance() .newDocumentBuilder();

		Document doc = dBuilder.parse(new InputSource(br));

		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		if (doc.hasChildNodes()) {
			 printNote(doc.getChildNodes());
		}
		
	    } catch (Exception e) {
	    	e.printStackTrace();
		
	    }
  }
  private  static void printNote(NodeList nodeList) {
	 	 
	  
    for (int count = 0; count < nodeList.getLength(); count++) {

	Node tempNode = nodeList.item(count);

	// make sure it's element node.
	if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

		// get node name and value
		if(tempNode.getNodeName().equalsIgnoreCase("title")) {
			booksService.setBook_title(tempNode.getTextContent());
		
		}
	
		if(tempNode.getNodeName().equalsIgnoreCase("price")) {
			booksService.setBook_price(tempNode.getTextContent());
			
		}
		if(tempNode.getNodeName().equalsIgnoreCase("author")) {
			booksService.setFirst_author(tempNode.getTextContent());
			
		}
		if(tempNode.getNodeName().equalsIgnoreCase("authors")) {
			booksService.setSecond_author(tempNode.getTextContent());
			
		}
		System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
		System.out.println("Node Value =" + tempNode.getTextContent());

		if (tempNode.hasAttributes()) {

			// get attributes names and values
			NamedNodeMap nodeMap = tempNode.getAttributes();

			for (int i = 0; i < nodeMap.getLength(); i++) {
				
				Node node = nodeMap.item(i);
				if(node.getNodeName() .equals("category")) {
					booksService.setBook_catagoery(node.getNodeValue());
					//break;
				}
				
			
				System.out.println("attr name : " + node.getNodeName());
				System.out.println("attr value : " + node.getNodeValue());

			}

		}

		if (tempNode.hasChildNodes()) {

			// loop again if has child nodes
			printNote(tempNode.getChildNodes());

		}


	}

    }
	System.out.println(booksService.saveBooksDetails(booksService));

  }

}