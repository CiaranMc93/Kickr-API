package Spring;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class FixtureList {

    @RequestMapping(value = "/fixtures/{county}")
    public ArrayList<Matches> fixtures(@PathVariable("county") String county) 
    {
		String URL = "";
		
		//get the correct county URL
		if(county.toLowerCase().equals("laois"))
		{
			//define our lists
			ArrayList<JSONObject> matches = new ArrayList<JSONObject>();
			
			JSONObject match = null;
			
			URL = "http://www.laoisgaa.ie/upcomingFixtures/";
			
			try 
			{
				 //jsoup gets the html page as a document
				 Document doc = Jsoup.connect(URL).get();
				 
				 Element tableBody = doc.getElementsByClass("frData upcoming_fixtures").get(0);
				 //get all rows from the table
				 Elements rows = tableBody.select("tr");
				 
				 //we need temp variables for date and competition
				 String tempComp = "";
				 String tempDate = "";
				 
				 for(Element fixtures : rows)
				 {
					 //competition denotes a new section
					 if(fixtures.toString().contains("competition"))
					 {
						 tempComp = fixtures.child(0).html().toString();
					 }
					 else if(fixtures.toString().contains("date"))
					 {
						 tempDate = fixtures.child(0).html().toString();
					 }
					 else if(fixtures.toString().contains("item odd") || fixtures.toString().contains("item even"))
					 {
						 match = new JSONObject();
						 
						 match.put("Time", fixtures.child(0).html().toString());
						 match.put("Home", fixtures.child(1).html().toString());
						 match.put("Away", fixtures.child(3).html().toString());
						 match.put("Venue", fixtures.child(4).html().toString());
						 match.put("Date", tempDate);
						 match.put("Competition", tempComp);
						 
						 //add to list
						 matches.add(match);
						 
					 }
				 }
				 
				 //array of matches objects
				 ArrayList<Matches> matchArr = new ArrayList<Matches>();
				 Matches matchObj;
				 
				 //loop through the list of strings and create the JSONObject/Array
				 for(int i=0; i<matches.size(); i++)
				 {
					 //System.out.println("Comp Size: " + competition.size() + "Match SIze: " + matches.size() + "Date Size: " + date.size());
					 //create an object for each match
					 matchObj = new Matches(matches.get(i));
					 //create a list of matches
					 matchArr.add(matchObj);
				 }	 
				 
				 return matchArr;
			}
			catch(Exception e)
			{
			    System.out.println("Got here" + e);
			}
		}
		return null;	
    }
}