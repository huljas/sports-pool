package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author exthulja
 */
@Entity
public class GroupQualifiersBet {
    @Id
    public Long id;


    @ManyToOne
    public Player player;
    @ManyToOne
    public Group group;
    @ManyToOne
    public Team first;
    @ManyToOne
    public Team second;

}
