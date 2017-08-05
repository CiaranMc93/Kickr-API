package Spring;

import org.json.JSONException;
import org.json.JSONObject;

public class Matches 
{
	//member variables
	private String time = "";
	private String homeTeam = "";
	private String awayTeam = "";
	private String venue = "";
	private String competition = "";
	private String date = "";
	
	public Matches(JSONObject obj)
	{
		try 
		{
			this.setTime((String) obj.get("Time"));
			this.setHomeTeam((String) obj.get("Home"));
			this.setAwayTeam((String) obj.get("Away"));
			this.setVenue((String) obj.get("Venue"));
			this.setCompetition((String) obj.get("Competition"));
			this.setDate((String) obj.get("Date"));
		} 
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getCompetition() {
		return competition;
	}

	public void setCompetition(String competition) {
		this.competition = competition;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
