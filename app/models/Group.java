package models;

import play.db.jpa.JPABase;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * @author exthulja
 */
@Entity
public class Group extends JPABase {

    @Id
    public Long id;

    public String name;

    @OneToMany(mappedBy = "group")
    public Set<Team> teams = new HashSet<Team>();
}
