/*
	An interactive Java program to show how Java can easily pull information 
	from a RESTful API, and interpret the resulting JSON, using the open source
	library JSON.Simple.
	
	Make sure JSON.Simple is downloaded and your classpath is pointed to where 
	the jar file is stored in your system.
	
	The API used is the COVID-19 REST API, https://api.covid19api.com/
*/

import java.io.*;
import java.lang.*;
import java.util.*;
import java.net.HttpURLConnection;
import java.net.URL;

/*
	special libraries we will need
*/
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class api_interact
{
	public static void main (String[] args)
	{		
		/*
			Connect to the URL, pull data from it or throw an exception
		*/
		try
		{	
			// Connection setup
			URL url = new URL("https://api.covid19api.com/summary");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			
			// Try to get a response, proceed only if response is 200 - ok
			int responseCode = conn.getResponseCode();
			if (responseCode != 200)
			{
				throw new RuntimeException("HttpResponseCode: " + responseCode);
			}
			else
			{							
				//Write JSON data from the API into string, getLine
				Scanner scanner = new Scanner(url.openStream());
				String getLine = " ";

				while (scanner.hasNext()) 
				{
					getLine += scanner.nextLine();
				}
				scanner.close();
				
				//Convert data to JSON Simple object.						
				JSONParser parse = new JSONParser();
				JSONObject allObj = (JSONObject) parse.parse(getLine);
				
				//Get countries array and go through it for requested data
				char found;
				char yn = 'y';
				while(yn == 'y')
				{
					found = 'n';
					Scanner scanner_2 = new Scanner(System.in);
					System.out.println("Enter a country: ");
					String countrySlug = scanner_2.nextLine();
					countrySlug = countrySlug.toLowerCase();
					System.out.println("");
					
					JSONArray arr = (JSONArray) allObj.get("Countries");
					for (int i = 0; i < arr.size(); i++) 
					{
						JSONObject obj = (JSONObject) arr.get(i);
						
						if (obj.get("Slug").equals(countrySlug)) 
						{
							found = 'y';
							System.out.println("Country: " + obj.get("Country"));
							System.out.println("New confirmed cases: " + obj.get("NewConfirmed"));
							System.out.println("Total confirmed cases: " + obj.get("TotalConfirmed"));
							System.out.println("New deaths: " + obj.get("NewDeaths"));
							System.out.println("Total deaths: " + obj.get("TotalDeaths"));
							System.out.println("New recovered cases: " + obj.get("NewRecovered"));
							System.out.println("Total recovered cases: " + obj.get("TotalRecovered"));
							System.out.println("Data date: " + obj.get("Date"));
							break;
						}
					}
					
					if (found == 'n')
					{
						System.out.println("Couldn't find country.");
					}
					System.out.println("");
					
					System.out.print("Continue (y/n)? ");
					yn = scanner_2.next().charAt(0);
					yn = Character.toLowerCase(yn);
					while (yn != 'y' && yn != 'n')
					{
						System.out.println("Please choose (y)es or (n)o.");
						System.out.println("");
						System.out.print("Continue (y/n)? ");
						yn = scanner_2.next().charAt(0);
						yn = Character.toLowerCase(yn);
					}
					System.out.println("");					
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("An error was thrown attempting to interact with the API");
		}
	}
}