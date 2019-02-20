package Spring;

import org.json.JSONException;
import org.json.JSONObject;

public class Matches extends Object
{
    //member variables
    private String time = "";
    private String homeTeam = "";
    private String awayTeam = "";
    private String venue = "";
    private String competition = "";
    private String date = "";
    private String homeTeamScore = "";
    private String awayTeamScore = "";
    private String winner = "";
    private String county = "";
    private int id = 0;

    private String teamName;
    private String played;
    private String won;
    private String drawn;
    private String lost;
    private String pointsFor;
    private String pointsAgainst;
    private String pointsDiff;
    private String pointsTotal;
    private String leaguePos;

    public Matches(JSONObject obj, String leaguePos) throws JSONException
    {
        this.setTeamName(obj.get("name").toString());
        this.setPlayed(obj.get("played").toString());
        this.setWon(obj.get("won").toString());
        this.setDrawn(obj.get("drawn").toString());
        this.setLost(obj.get("lost").toString());
        this.setPointsFor(obj.get("pointsFor").toString());
        this.setPointsAgainst(obj.get("pointsAgainst").toString());
        this.setPointsDiff(obj.get("pointsDiff").toString());
        this.setPointsTotal(obj.get("pointsTotal").toString());
    }

    public Matches(JSONObject obj) throws JSONException
    {
        this.teamName = "";
        this.played = "";
        this.won = "";
        this.drawn = "";
        this.lost = "";
        this.pointsFor = "";
        this.pointsAgainst = "";
        this.pointsDiff = "";
        this.pointsTotal = "";
        this.leaguePos = "";

        //create a hash of the following string
        this.setId((obj.get("Home") + "-" + obj.get("Away") + "-" + obj.get("Date")).hashCode());

        this.setHomeTeam((String) obj.get("Home"));
        this.setAwayTeam((String) obj.get("Away"));
        this.setCompetition((String) obj.get("Competition"));
        this.setDate((String) obj.get("Date"));
        this.setCounty((String) obj.get("County"));

        if (obj.has("HomeScore"))
        {
            if (!obj.get("HomeScore").equals("-"))
            {

                this.setHomeTeamScore((String) obj.get("HomeScore"));
                this.setAwayTeamScore((String) obj.get("AwayScore"));
                this.setWinner((String) obj.get("Winner"));
            }

            //check if time is in the object
            if (obj.has("Time"))
            {
                this.setTime((String) obj.get("Time"));

            }

            if (obj.has("Venue"))
            {
                this.setVenue((String) obj.get("Venue"));
            }
        }
        else
        {
            this.setTime((String) obj.get("Time"));
            this.setVenue((String) obj.get("Venue"));
        }
    }

    public String getTeamName()
    {
        return teamName;
    }

    public void setTeamName(String teamName)
    {
        this.teamName = teamName;
    }

    public String getPlayed()
    {
        return played;
    }

    public void setPlayed(String played)
    {
        this.played = played;
    }

    public String getWon()
    {
        return won;
    }

    public void setWon(String won)
    {
        this.won = won;
    }

    public String getDrawn()
    {
        return drawn;
    }

    public void setDrawn(String drawn)
    {
        this.drawn = drawn;
    }

    public String getLost()
    {
        return lost;
    }

    public void setLost(String lost)
    {
        this.lost = lost;
    }

    public String getPointsFor()
    {
        return pointsFor;
    }

    public void setPointsFor(String pointsFor)
    {
        this.pointsFor = pointsFor;
    }

    public String getPointsAgainst()
    {
        return pointsAgainst;
    }

    public void setPointsAgainst(String pointsAgainst)
    {
        this.pointsAgainst = pointsAgainst;
    }

    public String getPointsDiff()
    {
        return pointsDiff;
    }

    public void setPointsDiff(String pointsDiff)
    {
        this.pointsDiff = pointsDiff;
    }

    public String getPointsTotal()
    {
        return pointsTotal;
    }

    public void setPointsTotal(String pointsTotal)
    {
        this.pointsTotal = pointsTotal;
    }

    public String getLeaguePos()
    {
        return leaguePos;
    }

    public void setLeaguePos(String leaguePos)
    {
        this.leaguePos = leaguePos;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getHomeTeam()
    {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam)
    {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam()
    {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam)
    {
        this.awayTeam = awayTeam;
    }

    public String getVenue()
    {
        return venue;
    }

    public void setVenue(String venue)
    {
        this.venue = venue;
    }

    public String getCompetition()
    {
        return competition;
    }

    public void setCompetition(String competition)
    {
        this.competition = competition;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getWinner()
    {
        return winner;
    }

    public void setWinner(String winner)
    {
        this.winner = winner;
    }

    public String getAwayTeamScore()
    {
        return awayTeamScore;
    }

    public void setAwayTeamScore(String awayTeamScore)
    {
        this.awayTeamScore = awayTeamScore;
    }

    public String getHomeTeamScore()
    {
        return homeTeamScore;
    }

    public void setHomeTeamScore(String homeTeamScore)
    {
        this.homeTeamScore = homeTeamScore;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getCounty()
    {
        return county;
    }

    public void setCounty(String county)
    {
        this.county = county;
    }

}
