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
    public void fixtures(@PathVariable("county") String county) 
    {
		String URL = "";
		
		//get the correct county URL
		if(county.toLowerCase().equals("laois"))
		{
			//define our lists
			ArrayList<JSONObject> matches = new ArrayList<JSONObject>();
			ArrayList<JSONObject> competition = new ArrayList<JSONObject>();
			ArrayList<JSONObject> date = new ArrayList<JSONObject>();
			ArrayList<JSONObject> matchAll = new ArrayList<JSONObject>();
			
			JSONObject match = null;
			JSONObject comp = null;
			JSONObject dates = null;
			JSONObject matchInfo = null;
			JSONObject matchOvr = new JSONObject();
			
			
			URL = "http://www.laoisgaa.ie/upcomingFixtures/";
			
			try 
			{
				 //jsoup gets the html page as a document
				 Document doc = Jsoup.connect(URL).get();
				
				 //extract the information via the element names
				 Elements trMatch = doc.getElementsByClass("item odd");
				 Elements trCompetition = doc.getElementsByClass("competition");
				 Elements trDate = doc.getElementsByClass("date");
				 
				 //for each match in the webpage
				 for (Element trElem : trMatch) 
				 {
					 match = new JSONObject();
					 
					 match.put("Time", trElem.child(0).html().toString());
					 match.put("Home", trElem.child(1).html().toString());
					 match.put("Away", trElem.child(3).html().toString());
					 match.put("Venue", trElem.child(4).html().toString());

					 //add to list
					 matches.add(match);
				 }
				 
				 //for each competition in the webpage
				 for (Element trElem : trCompetition) 
				 {
					 comp = new JSONObject();
					 
					 comp.put("Competition",trElem.child(0).html().toString());
					 competition.add(comp);
				 }
				 
				 //for each date in the webpage
				 for (Element trElem : trDate) 
				 {
					 dates = new JSONObject();
					 
					 dates.put("Date",trElem.child(0).html().toString());
					 date.add(dates);
				 }
				 
				 //loop through the list of strings and create the JSONObject/Array
				 for(int i=0; i<35; i++)
				 {
					 //create the main object
					 matchAll.add(matches.get(i));
					 matchAll.add(competition.get(i));
					 matchAll.add(date.get(i));
				 }

				 System.out.println("JSON:" + matchAll.toString());
				 
			}
			catch(Exception e)
			{
			    System.out.println("Got here" + e);
			}
		}	
    }
}