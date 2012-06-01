package models;

import play.db.jpa.JPABase;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author exthulja
 */
@Entity
public class Player extends Model {

    public static int qualifierCorrect = 5;
    public static int qualifierFirstCorrect = 10;
    public static int qualifierSecondCorrect = 10;
    public static int firstCorrect = 50;
    public static int secondCorrect = 30;

    public String name;
    public Integer score;

    public int calculateScore(List<Group> qualifierGroups, List<Team> finalists) {
        List<GroupQualifiersBet> qualifiersBets = GroupQualifiersBet.find("byPlayer", this).fetch();
        List<FinalistsBet> finalistsBets = FinalistsBet.find("byPlayer", this).fetch();

        score = 0;
        for (GroupQualifiersBet bet : qualifiersBets) {
            for (Group group : qualifierGroups) {
                if (group.name.equals(bet.groupName)) {
                    String resultFirst = group.teams.get(0).name;
                    String resultSecond = group.teams.get(1).name;
                    if (bet.first.equals(resultFirst)) {
                        score += qualifierFirstCorrect;
                    } else if (bet.first.equals(resultSecond)) {
                        score += qualifierCorrect;
                    }
                    if (bet.second.equals(resultSecond)) {
                        score += qualifierSecondCorrect;
                    } else if (bet.second.equals(resultFirst)) {
                        score += qualifierCorrect;
                    }
                }
            }
        }
        return score;
    }
}
