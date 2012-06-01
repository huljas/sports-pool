package models;

import play.db.jpa.JPABase;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author exthulja
 */
@Entity
public class FinalistsBet extends JPABase {

    @Id
    public Long id;

    @ManyToOne
    public Player player;
    @ManyToOne
    public Team first;
    @ManyToOne
    public Team second;
    @ManyToOne
    public Team third;
}
