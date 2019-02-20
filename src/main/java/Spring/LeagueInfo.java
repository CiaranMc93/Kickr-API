package Spring;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class LeagueInfo
{
    List<String> leagueListUrls =
            Arrays.asList(
                    "136906/a_c_f_l_division1_a",
                    "136905/a_c_f_l_division1_b",
                    "136904/a_c_f_l_division2",
                    "136907/a_c_f_l_division3",
                    "136909/a_c_f_l_division4",
                    "136898/a_c_h_l_division1",
                    "136897/a_c_h_l_division1_a",
                    "136899/a_c_h_l_division2",
                    "136900/a_c_h_l_division3",
                    "136901/a_c_h_l_division4"
            );

    @CrossOrigin
    //get the fixtures for the county specified
    @RequestMapping(value = "/leagueInfo/{league}")
    public Map<String, ArrayList<Matches>> fixtures(@PathVariable("league") String leagueUrl)
    {
        String baseURL = "http://www.laoisgaa.ie/league/";

        Map<String, ArrayList<Matches>> ovrData = new HashMap<>();

        switch (leagueUrl)
        {
            case "ACFL1A":
                ovrData.put("Fixtures", getLeagueInfoHtml(baseURL + leagueListUrls.get(0)));
                ovrData.put("LeagueTable", getLeagueTableHtml(baseURL + leagueListUrls.get(0)));
                break;
            case "ACFL1B":
                ovrData.put("Fixtures", getLeagueInfoHtml(baseURL + leagueListUrls.get(1)));
                ovrData.put("LeagueTable", getLeagueTableHtml(baseURL + leagueListUrls.get(1)));
                break;
            case "ACFL2":
                ovrData.put("Fixtures", getLeagueInfoHtml(baseURL + leagueListUrls.get(2)));
                ovrData.put("LeagueTable", getLeagueTableHtml(baseURL + leagueListUrls.get(2)));
                break;
            case "ACFL3":
                ovrData.put("Fixtures", getLeagueInfoHtml(baseURL + leagueListUrls.get(3)));
                ovrData.put("LeagueTable", getLeagueTableHtml(baseURL + leagueListUrls.get(3)));
                break;
            case "ACFL4":
                ovrData.put("Fixtures", getLeagueInfoHtml(baseURL + leagueListUrls.get(4)));
                ovrData.put("LeagueTable", getLeagueTableHtml(baseURL + leagueListUrls.get(4)));
                break;
            case "ACHL1":
                ovrData.put("Fixtures", getLeagueInfoHtml(baseURL + leagueListUrls.get(5)));
                ovrData.put("LeagueTable", getLeagueTableHtml(baseURL + leagueListUrls.get(5)));
                break;
            case "ACHL1A":
                ovrData.put("Fixtures", getLeagueInfoHtml(baseURL + leagueListUrls.get(6)));
                ovrData.put("LeagueTable", getLeagueTableHtml(baseURL + leagueListUrls.get(6)));
                break;
            case "ACHL2":
                ovrData.put("Fixtures", getLeagueInfoHtml(baseURL + leagueListUrls.get(7)));
                ovrData.put("LeagueTable", getLeagueTableHtml(baseURL + leagueListUrls.get(7)));
                break;
            case "ACHL3":
                ovrData.put("Fixtures", getLeagueInfoHtml(baseURL + leagueListUrls.get(8)));
                ovrData.put("LeagueTable", getLeagueTableHtml(baseURL + leagueListUrls.get(8)));
                break;
            case "ACHL4":
                ovrData.put("Fixtures", getLeagueInfoHtml(baseURL + leagueListUrls.get(9)));
                ovrData.put("LeagueTable", getLeagueTableHtml(baseURL + leagueListUrls.get(9)));
                break;
            default:
                System.out.println("Defaulted");

        }

        return ovrData;

    }

    private ArrayList<Matches> getLeagueTableHtml(String URL)
    {
        try
        {
            //jsoup gets the html page as a document
            Document doc = Jsoup.connect(URL).get();

            return getLeagueTableInfo(doc);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private ArrayList<Matches> getLeagueTableInfo(Document doc) throws JSONException
    {
        ArrayList<JSONObject> leagueTableInfo = new ArrayList<>();

        Element leagueInfo = null;

        leagueInfo = doc.getElementsByClass("frData leagueTable").get(0);

        //get all rows from the table
        Elements rows = leagueInfo.select("tr");

        leagueTableInfo = leagueTableJsonObj(rows);

        Matches leagueTableObj;
        ArrayList<Matches> leagueTable = new ArrayList<>();

        //loop through the list of strings and create the JSONObject/Array
        for (int i = 0; i < leagueTableInfo.size(); i++)
        {
            //create an object for each match
            leagueTableObj = new Matches(leagueTableInfo.get(i), "" + (i + 1));
            //create a list of matches
            leagueTable.add(leagueTableObj);
        }

        return leagueTable;
    }

    private ArrayList<JSONObject> leagueTableJsonObj(Elements rows) throws JSONException
    {
        //define our lists
        ArrayList<JSONObject> matches = new ArrayList<JSONObject>();
        JSONObject match = null;

        for (Element row : rows)
        {
            if (row.hasClass("tableItem odd") || row.hasClass("tableItem even"))
            {
                match = new JSONObject();

                match.put("name", row.child(0).html());
                match.put("played", row.child(1).html());
                match.put("won", row.child(2).html());
                match.put("drawn", row.child(3).html());
                match.put("lost", row.child(4).html());
                match.put("pointsFor", row.child(5).html());
                match.put("pointsAgainst", row.child(6).html());
                match.put("pointsDiff", row.child(7).html());
                match.put("pointsTotal", row.child(8).html());

                matches.add(match);
            }
        }

        return matches;
    }

    private ArrayList<Matches> getLeagueInfoHtml(String URL)
    {
        try
        {
            //jsoup gets the html page as a document
            Document doc = Jsoup.connect(URL).get();

            return getLeagueInfoHtml(doc);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    //return an array filled with matches that have been populated
    private ArrayList<Matches> getLeagueInfoHtml(Document doc) throws JSONException
    {
        ArrayList<JSONObject> matches = new ArrayList<>();

        Element fixtureInfo = null;

        fixtureInfo = doc.getElementsByClass("frData league").get(0);

        //get all rows from the table
        Elements rows = fixtureInfo.select("tr");

        //get the array of matches
        matches = matchJSONObj(rows);

        Matches matchObj;
        ArrayList<Matches> fixturesData = new ArrayList<>();

        //loop through the list of strings and create the JSONObject/Array
        for (int i = 0; i < matches.size(); i++)
        {
            //create an object for each match
            matchObj = new Matches(matches.get(i));
            //create a list of matches
            fixturesData.add(matchObj);
        }

        return fixturesData;

    }

    private ArrayList<JSONObject> matchJSONObj(Elements rows) throws JSONException
    {
        //define our lists
        ArrayList<JSONObject> matches = new ArrayList<JSONObject>();
        JSONObject match = null;

        //we need temp variables for date and competition
        String tempComp = "";
        String tempDate = "";

        //for each element in table rows
        for (Element matchInfo : rows)
        {
            //competition denotes a new section
            if (matchInfo.toString().contains("competition"))
            {
                tempComp = matchInfo.child(0).html();
            }
            else if (matchInfo.toString().contains("date"))
            {
                tempDate = matchInfo.child(0).html().replaceAll("/", "-");
            }
            else if (matchInfo.toString().contains("item odd") || matchInfo.toString().contains("item even"))
            {

                match = new JSONObject();

                match.put("Time", parseTime(matchInfo.child(0).html()));
                match.put("Home", matchInfo.child(1).html());
                match.put("HomeScore", matchInfo.child(2).html());
                match.put("Away", matchInfo.child(5).html());
                match.put("AwayScore", matchInfo.child(4).html());
                match.put("Date", tempDate);
                match.put("Competition", tempComp);
                match.put("County", "Laois");

                if (matchInfo.child(2).html().equals("-") && matchInfo.child(4).html().equals("-"))
                {
                    //add to list
                    matches.add(match);
                    //match has been conceded or does not have any data - continue to next iteration
                    continue;
                }

                //select the winning team
                if (winningTeam(matchInfo.child(2).html(), matchInfo.child(4).html(), "-").equals("Home"))
                {
                    match.put("Winner", matchInfo.child(1).html());
                }
                else if (winningTeam(matchInfo.child(2).html(), matchInfo.child(4).html(), "-").equals("Away"))
                {
                    match.put("Winner", matchInfo.child(5).html());
                }
                else if (winningTeam(matchInfo.child(2).html(), matchInfo.child(4).html(), "-").equals("Draw"))
                {
                    match.put("Winner", "Draw");
                }
                else
                {
                    match.put("Winner", "N/A");
                }
            }
        }

        return matches;
    }

    private String winningTeam(String homeScore, String awayScore, String splitterChar)
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
        if (homeTotal > awayTotal)
        {
            return "Home";
        }
        else if (homeTotal < awayTotal)
        {
            return "Away";
        }
        else if (homeTotal == awayTotal)
        {
            return "Draw";
        }

        return "";
    }

    private String parseTime(String time)
    {
        if (time.contains(":"))
        {
            return time;
        }

        String parsedTime = "";

        try
        {
            SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat parseFormat = new SimpleDateFormat("hh mm a");
            Date date = parseFormat.parse(time);
            parsedTime = displayFormat.format(date);
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }

        return parsedTime;
    }

    private String parseDate(String dateToParse)
    {
        String parseDate = "";

        try
        {
            SimpleDateFormat displayFormat = new SimpleDateFormat("dd-MM-yyy");
            SimpleDateFormat parseFormat = new SimpleDateFormat("EEE dd MMM. yyyy");
            Date date = parseFormat.parse(dateToParse);
            parseDate = displayFormat.format(date);
        }
        catch (Exception e)
        {
            //parse the alternate format of the dates.
            try
            {
                SimpleDateFormat displayFormat = new SimpleDateFormat("dd-MM-yyy");
                SimpleDateFormat parseFormat = new SimpleDateFormat("EEE dd MMMMM yyyy");
                Date date = parseFormat.parse(dateToParse);
                parseDate = displayFormat.format(date);
            }
            catch (Exception j)
            {
                j.printStackTrace();
            }
        }

        return parseDate;
    }
}

      