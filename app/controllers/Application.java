package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        final List<Group> groups = Group.getGroups();
        final List<Team> finalists = Team.getFinalists();
        List<Player> players = Player.findAll();
        for (Player player : players) {
            player.calculateScore(groups, finalists);
        }
        Collections.sort(players, new Comparator<Player>() {
            public int compare(Player o1, Player o2) {
                return new Integer(o2.score).compareTo(o1.score);
            }
        });
        render(groups, players);
    }

}