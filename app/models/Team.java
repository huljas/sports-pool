package models;

import play.db.jpa.JPABase;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author exthulja
 */
@Entity
public class Team extends JPABase {

    @Id
    public Long id;


    public String name;

    @ManyToOne
    public Group group;
}
