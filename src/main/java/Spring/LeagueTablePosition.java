package Spring;

import org.json.JSONException;
import org.json.JSONObject;

public class LeagueTablePosition
{
    private String teamName = "";
    private String played;
    private String won;
    private String drawn;
    private String lost;
    private String pointsFor;
    private String pointsAgainst;
    private String pointsDiff;
    private String pointsTotal;

    public LeagueTablePosition(JSONObject obj) throws JSONException
    {
        this.teamName = obj.get("name").toString();
        this.played = obj.get("played").toString();
        this.won = obj.get("won").toString();
        this.drawn = obj.get("drawn").toString();
        this.lost = obj.get("lost").toString();
        this.pointsFor = obj.get("pointsFor").toString();
        this.pointsAgainst = obj.get("pointsAgainst").toString();
        this.pointsDiff = obj.get("pointsDiff").toString();
        this.pointsTotal = obj.get("pointsTotal").toString();
    }
}
