package models;

import play.db.jpa.JPABase;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author exthulja
 */
@Entity
public class FinalistsBet extends Model {

    @ManyToOne
    public Player player;

    public String first;
    public String second;

}
