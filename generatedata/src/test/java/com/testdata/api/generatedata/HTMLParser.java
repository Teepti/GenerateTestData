package com.testdata.api.generatedata;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup; 
import org.jsoup.nodes.Document; 
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements; 
/** * Java Program to parse/read HTML documents from File using Jsoup library. * Jsoup is an open source library which allows Java developer to parse HTML * files and extract elements, manipulate data, change style using DOM, CSS and * JQuery like method. * * @author Javin Paul */
public class HTMLParser { 
	public static void main(String[] args) throws IOException 
	{ 
		// Parse HTML String using JSoup library 
		ArrayList<String> operations = new ArrayList<String>();
		ArrayList<String> locators = new ArrayList<String>();
		File input = new File("C:\\Users\\Revaan\\Downloads\\Untitled_Test_Suite.html");
		
			Document htmlFile = Jsoup.parse(input,"UTF-8","");
		
		String title = htmlFile.title(); 
		//System.out.println(htmlFile.body());
		Elements tableElements = htmlFile.select("table");
		Elements trs = htmlFile.select("table tr");

        //remove header row
        trs.remove(0);

        for (Element tr : trs) {
            Elements tds = tr.getElementsByTag("td");
            Element td = tds.first();
            Elements td1 = tds.next();
            operations.add(td.text());
            locators.add(td1.first().ownText());
            System.out.println(td.text() + " " + td1.first().ownText());
        }
        
        System.out.println(operations);
        System.out.println(locators);
        
        FileWriter writer = new FileWriter("C:\\Users\\Revaan\\eclipse-workspace\\generatedata\\src\\test\\java\\com\\testdata\\api\\generatedata\\test.feature");
        
        	writer.write("Feature: Testing on " + locators.get(0) + System.lineSeparator());
        	writer.write("Scenario: Testing on "+ locators.get(0) + System.lineSeparator());
        	int count = 0;
        	for(String str:operations) {
        		
        			writer.write("Given user perform action "+  str + " on element "+ locators.get(count)  + System.lineSeparator());
        		
        		count++;
        	}	
        	
        
        writer.close();

        

	}
}


