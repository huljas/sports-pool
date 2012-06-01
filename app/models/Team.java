package models;

import play.db.jpa.JPABase;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Collections;
import java.util.List;

/**
 * @author exthulja
 */
public class Team {

    public String name;
    public int position;
    public String movement;
    public String url;
    public int played;
    public int points;

    public static List<Team> getFinalists() {
        return Collections.emptyList();
    }
}
