package models;

import play.cache.Cache;
import play.cache.CacheFor;
import play.libs.WS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author huljas
 */
public class Group {

    public String name;

    public List<Team> teams = new ArrayList<Team>();

    public Group(String name) {
        this.name = name;
    }

    @CacheFor
    public static List<Group> getGroups() {
        String cacheKey = "qGroups";
        List<Group> groups = (List<Group>) Cache.get(cacheKey);
        if (groups == null) {
            String response = WS.url("http://polling.bbc.co.uk/sport/shared/football/as-it-stands/119002035?callback=aistable").get().getString();
            groups = parseResponse(response);
            Cache.add(cacheKey, groups, "1h");
        }
        return groups;
    }

    public static List<Group> parseResponse(String response) {
        Matcher groupMatcher = Pattern.compile("<h3 class='competition-name'>([^<]*)</h3>").matcher(response);
        List<Group> groups = new ArrayList<Group>();
        while (groupMatcher.find()) {
            String groupName = groupMatcher.group(1);
            Group group = new Group(groupName);
            groups.add(group);
            int start = groupMatcher.end();
            int end = response.indexOf("<h3 class='competition-name'>", start);
            end = end > 0 ? end : response.length();
            String table = response.substring(start, end);
            List<Team> teams = parseTeams(table);
            group.teams = teams;
        }

        return groups;
    }

    public static List<Team> parseTeams(String s) {
        List<Team> teams = new ArrayList<Team>();
        Matcher matcher = Pattern.compile("<tr class='team[^']*'>\\s*<td class='position'><span class='no-movement'>([^<]*)</span>\\s*<span class='position-number'>(\\d*)</span></td>\\s*<td class='team-name'>\\s*<a href='([^']*)'>([^<]*)</a>\\s*</td>\\s*<td class='played'>(\\d*)</td>\\s*<td class='goal-difference'>0</td>\\s*<td class='points'>(\\d*)</td>\\s*</tr>").matcher(s);
        while (matcher.find()) {
            String movement = matcher.group(1);
            int position = Integer.parseInt(matcher.group(2));
            String url = matcher.group(3);
            String name = matcher.group(4);
            int played = Integer.parseInt(matcher.group(5));
            int points = Integer.parseInt(matcher.group(6));
            Team team = new Team();
            team.name = name;
            team.position = position;
            team.movement = movement;
            team.url = url;
            team.played = played;
            team.points = points;
            teams.add(team);
        }
        return teams;
    }
}
