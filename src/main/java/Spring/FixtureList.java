package Spring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
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
			URL = "http://www.laoisgaa.ie/upcomingFixtures/";
		}
		 
		try 
		{
			 //jsoup gets the html page as a document
			 Document doc = Jsoup.connect(URL).get();
			
			 //item odd is the tr for each match.
			 Elements trMatch = doc.getElementsByClass("item odd");
			 Elements trCompetition = doc.getElementsByClass("competition");
			 Elements trDate = doc.getElementsByClass("date");
		
			 for (Element trElem : trMatch) 
			 {
			        //System.out.println(trElem.toString());
			 }
			 
			 for (Element trElem : trCompetition) 
			 {
				 	
				 	System.out.println("Size: " + trElem.child(0).html().toString());
			 }
			 
			 for (Element trElem : trDate) 
			 {
			        System.out.println(trElem.childNodes().toString());
			 }
		
		//obj = new JSONObject();
		}
		catch(Exception e)
		{
		    System.out.println("Got here" + e);
		}
    }
}