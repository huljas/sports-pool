package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * @author exthulja
 */
@Entity
public class Player {
    @Id
    public Long id;

    public String name;
}
