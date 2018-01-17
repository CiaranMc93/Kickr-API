package Spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
public class FixtureList {

	@CrossOrigin
	//get the fixtures for the county specified
    @RequestMapping(value = "/fixtures/{county}")
    public ArrayList<Matches> fixtures(@PathVariable("county") String county) 
    {
		String URL = "";
		
		//get the correct county URL
		if(county.toLowerCase().equals("laois"))
		{
			URL = "http://www.laoisgaa.ie/upcomingFixtures/";
			
			try 
			{
				 //jsoup gets the html page as a document
				 Document doc = Jsoup.connect(URL).get();
				 
				 return getMatches(doc,"fixtures",county);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(county.toLowerCase().equals("kerry"))
		{
			URL = "http://www.kerrygaa.ie/upcomingFixtures";
			
			try 
			{
				 //jsoup gets the html page as a document
				 Document doc = Jsoup.connect(URL).get();
				 
				 return getMatches(doc,"fixtures",county);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(county.toLowerCase().equals("waterford"))
		{
			URL = "http://www.waterfordgaa.ie/upcomingFixtures";
			
			try 
			{
				 //jsoup gets the html page as a document
				 Document doc = Jsoup.connect(URL).get();
				 
				 return getMatches(doc,"fixtures",county);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(county.toLowerCase().equals("cork"))
		{
			URL = "http://www.gaacork.ie/upcomingFixtures";
			
			try 
			{
				 //jsoup gets the html page as a document
				 Document doc = Jsoup.connect(URL).get();
				 
				 return getMatches(doc,"fixtures",county);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(county.toLowerCase().equals("carlow"))
		{
			URL = "http://www.carlowgaa.ie/wp-admin/admin-ajax.php?action=fixtures&countyBoardID=3&fixturesOnly=Y&daysAfter=7";
			
			try 
			{
				 //jsoup gets the html page as a document
				 Document doc = Jsoup.connect(URL).get();
				 
				 return carlowMatches(doc,"fixtures",county);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return null;	
    }
    
	@CrossOrigin
    //get the results for the county specified
    @RequestMapping(value = "/results/{county}")
    public ArrayList<Matches> results(@PathVariable("county") String county) 
    {
		String URL = "";
		
		//get the correct county URL
		if(county.toLowerCase().equals("laois"))
		{
			URL = "http://www.laoisgaa.ie/latestResults/";
			
			try 
			{
				 //jsoup gets the html page as a document
				 Document doc = Jsoup.connect(URL).get();
				 
				 return getMatches(doc,"results",county);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(county.toLowerCase().equals("kerry"))
		{
			URL = "http://www.kerrygaa.ie/latestResults";
			
			try 
			{
				 //jsoup gets the html page as a document
				 Document doc = Jsoup.connect(URL).get();
				 
				 return getMatches(doc,"results",county);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(county.toLowerCase().equals("waterford"))
		{
			URL = "http://www.waterfordgaa.ie/latestResults";
			
			try 
			{
				 //jsoup gets the html page as a document
				 Document doc = Jsoup.connect(URL).get();
				 
				 return getMatches(doc,"results",county);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(county.toLowerCase().equals("cork"))
		{
			URL = "http://www.gaacork.ie/latestResults";
			
			try 
			{
				 //jsoup gets the html page as a document
				 Document doc = Jsoup.connect(URL).get();
				 
				 return getMatches(doc,"results",county);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(county.toLowerCase().equals("carlow"))
		{
			URL = "http://www.carlowgaa.ie/wp-admin/admin-ajax.php?action=results&countyBoardID=3&resultsOnly=Y&reverseDateOrder=Y&daysPrevious=7";
			
			try 
			{
				 //jsoup gets the html page as a document
				 Document doc = Jsoup.connect(URL).get();
				 
				 return carlowMatches(doc,"results",county);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return null;	
    }
	
	//return an array filled with matches that have been populated
    private ArrayList<Matches> carlowMatches(Document doc, String option,String county)
    {
    	ArrayList<JSONObject> matches = new ArrayList<JSONObject>();
    	
    	try 
		{	 
    		 Elements carlowMatches = null;
    		
    		 carlowMatches = doc.getElementsByClass("fixture single");
			 
			 //get the array of matches
			 matches = carlowMatchJSONObject(carlowMatches,option,county);
			 
			 if(null == matches)
			 {
				 return null;
			 }

			 //array of matches objects
			 ArrayList<Matches> matchArr = new ArrayList<Matches>();
			 Matches matchObj;
			 
			 //loop through the list of strings and create the JSONObject/Array
			 for(int i=0; i<matches.size(); i++)
			 {
				 //create an object for each match
				 matchObj = new Matches(matches.get(i));
				 //create a list of matches
				 matchArr.add(matchObj);
			 }	 
			 
			 return matchArr;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    	
		return null;	
    }
    
    //return an array filled with matches that have been populated
    private ArrayList<Matches> getMatches(Document doc, String option, String county)
    {
    	ArrayList<JSONObject> matches = new ArrayList<JSONObject>();
    	
    	try 
		{	 
    		 Element tableBody = null;
    		
    		 if(option.equals("fixtures"))
    		 {
    			 tableBody = doc.getElementsByClass("frData upcoming_fixtures").get(0);
    		 }
    		 else
    		 {
    			 tableBody = doc.getElementsByClass("frData latest_results").get(0);
    		 }
			
			 //get all rows from the table
			 Elements rows = tableBody.select("tr");
			 
			 //get the array of matches
			 matches = matchJSONObj(rows,option,county);
			 
			 if(null == matches)
			 {
				 return null;
			 }

			 //array of matches objects
			 ArrayList<Matches> matchArr = new ArrayList<Matches>();
			 Matches matchObj;
			 
			 //loop through the list of strings and create the JSONObject/Array
			 for(int i=0; i<matches.size(); i++)
			 {
				 //create an object for each match
				 matchObj = new Matches(matches.get(i));
				 //create a list of matches
				 matchArr.add(matchObj);
			 }	 
			 
			 return matchArr;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    	
		return null;	
    }
    
    private ArrayList<JSONObject> carlowMatchJSONObject(Elements rows,String option,String county)
    {
    	 //define our lists
		 ArrayList<JSONObject> matches = new ArrayList<JSONObject>();
    	 JSONObject match = null;
    	    	
    	 //we need temp variables for date and competition
		 String competition = "";
		 String date = "";
		 String time = "";
		 String venue = "";
		 String matchDetails = "";
		 String homeTeam = "";
		 String awayTeam = "";
		 String homeScore = "";
		 String awayScore = "";
		 
		 try{

	    	 //for each element in table rows
			 for(Element fixtures : rows)
			 {
				 competition = fixtures.child(0).text();
				 
				 //split out the venue field of this div
				 matchDetails = fixtures.child(1).text();
				 List<String> matchTeamInfo = Arrays.asList(matchDetails.split(" vs "));
				 
				 homeTeam = matchTeamInfo.get(0);
				 awayTeam = matchTeamInfo.get(1);
				 
				 if(option.equals("results"))
				 {
					 //get the scores
					 //split out the venue field of this div
					 matchDetails = fixtures.child(2).text();
					 List<String> matchResult = Arrays.asList(matchDetails.split(" - "));
					 
					 homeScore = matchResult.get(0);
					 awayScore = matchResult.get(1);
					 
					 //split out the venue field of this div
					 matchDetails = fixtures.child(4).text();
					 List<String> matchDetailsList = Arrays.asList(matchDetails.split(" / "));
					 
					 //make sure there are no issues.
					 if(matchDetailsList.size() < 3)
					 {
						 date = matchDetailsList.get(0);
						 time = matchDetailsList.get(1);
						 venue = "Not announced";
					 }
					 else
					 {
						 date = matchDetailsList.get(0);
						 time = matchDetailsList.get(1);
						 venue = matchDetailsList.get(2);
					 }
				 }
				 else
				 {
					//split out the venue field of this div
					 matchDetails = fixtures.child(3).text();
					 List<String> matchDetailsList = Arrays.asList(matchDetails.split(" / "));
					 
					 //make sure there are no issues.
					 if(matchDetailsList.size() < 3)
					 {
						 date = matchDetailsList.get(0);
						 time = matchDetailsList.get(1);
						 venue = "TBC";
					 }
					 else
					 {
						 date = matchDetailsList.get(0);
						 time = matchDetailsList.get(1);
						 venue = matchDetailsList.get(2);
					 }
				 }

				 if(option.equals("fixtures"))
				 {
					 match = new JSONObject();
					 
					 match.put("Time", time);
					 match.put("Home", homeTeam);
					 match.put("Away", awayTeam);
					 match.put("Venue", venue);
					 match.put("Date", date);
					 match.put("Competition", competition);
					 match.put("County", county);
					 
					 //add to list
					 matches.add(match);
				 }
				 else if(option.equals("results"))
				 {
					 match = new JSONObject();
					 
					 match.put("Time", time);
					 match.put("Home", homeTeam);
					 match.put("HomeScore", homeScore);
					 match.put("Away", awayTeam);
					 match.put("AwayScore", awayScore);
					 match.put("Venue", venue);
					 match.put("Date", date);
					 match.put("Competition", competition);
					 match.put("County", county);
					 
					 //select the winning team
					 if(winningTeam(homeScore,awayScore, " : ").equals("Home"))
					 {
						 match.put("Winner", homeTeam);
					 }
					 else if(winningTeam(homeScore,awayScore, " : ").equals("Away"))
					 {
						 match.put("Winner", awayTeam);
					 }
					 else if(winningTeam(homeScore,awayScore, " : ").equals("Draw"))
					 {
						 match.put("Winner", "Draw");
					 }
					 else
					 {
						 match.put("Winner", "N/A");
					 }
					 
					 //add to list
					 matches.add(match);
				 }
			 }
			 
			 return matches;
		 }
		 catch(Exception e)
		 {
			 System.out.println("Got here carlow obj" + e);
		 }
		 
		return matches;
    }
    
    private ArrayList<JSONObject> matchJSONObj(Elements rows,String option,String county)
    {
    	 //define our lists
		 ArrayList<JSONObject> matches = new ArrayList<JSONObject>();
    	 JSONObject match = null;
    	    	
    	 //we need temp variables for date and competition
		 String tempComp = "";
		 String tempDate = "";
		 
		 try{

	    	 //for each element in table rows
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
					 if(option.equals("fixtures"))
					 {
						 match = new JSONObject();
						 
						 match.put("Time", fixtures.child(0).html().toString());
						 match.put("Home", fixtures.child(1).html().toString());
						 match.put("Away", fixtures.child(3).html().toString());
						 match.put("Venue", fixtures.child(4).html().toString());
						 match.put("Date", tempDate);
						 match.put("Competition", tempComp);
						 match.put("County", county);
						 
						 //add to list
						 matches.add(match);
					 }
					 else if(option.equals("results"))
					 {
						 match = new JSONObject();
						 
						 if(fixtures.child(1).html().toString().equals("-") &&  fixtures.child(3).html().toString().equals("-"))
						 {
							 //match has been conceded - continue to next iteration
							 continue;
						 }
						 
						 match.put("Home", fixtures.child(0).html().toString());
						 match.put("HomeScore", fixtures.child(1).html().toString());
						 match.put("Away", fixtures.child(4).html().toString());
						 match.put("AwayScore", fixtures.child(3).html().toString());
						 
						 //select the winning team
						 if(winningTeam(fixtures.child(1).html().toString(),fixtures.child(3).html().toString(), "-").equals("Home"))
						 {
							 match.put("Winner", fixtures.child(0).html().toString());
						 }
						 else if(winningTeam(fixtures.child(1).html().toString(),fixtures.child(3).html().toString(), "-").equals("Away"))
						 {
							 match.put("Winner", fixtures.child(4).html().toString());
						 }
						 else if(winningTeam(fixtures.child(1).html().toString(),fixtures.child(3).html().toString(), "-").equals("Draw"))
						 {
							 match.put("Winner", "Draw");
						 }
						 else
						 {
							 match.put("Winner", "N/A");
						 }
						 
						 match.put("Date", tempDate);
						 match.put("Competition", tempComp);
						 match.put("County", county);
						 
						 //add to list
						 matches.add(match);
					 }
				 }
			 }
			 
			 return matches;
		 }
		 catch(Exception e)
		 {
			 System.out.println("Got here" + e);
		 }
		 
    	return null;
    }

	private String winningTeam(String homeScore, String awayScore,String splitterChar)
	{
		//calculate the home score
		String[] homeParts = homeScore.split(splitterChar);
		
		int homeGoals = Integer.parseInt(homeParts[0]);
		int homePoints = Integer.parseInt(homeParts[1]);
		
		int homeTotal = (homeGoals * 3) + homePoints;
		
		//calculate the away score
		String[] awayParts = awayScore.split(splitterChar);
		
		int awayGoals = Integer.parseInt(awayParts[0]);
		int awayPoints = Integer.parseInt(awayParts[1]);
		
		int awayTotal = (awayGoals * 3) + awayPoints;
		
		//compare the scores
		if(homeTotal > awayTotal)
		{
			return "Home";
		}
		else if(homeTotal < awayTotal)
		{
			return "Away";
		}
		else if(homeTotal == awayTotal)
		{
			return "Draw";
		}
		
		return "";
	}
}