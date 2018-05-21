package kz.crtr.app.entity.tbl;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author a.kussein
 */
@Entity
@Table(name = "GROUPS")
@Getter
public class Groups implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Size(min = 1, max = 200)
    @Column(name = "G_NAME")
    private String gName;
    @Size(max = 1000)
    @Column(name = "G_DESCRIPTION")
    private String gDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gName")
    private List<Groupmembers> groupmembersList;

    public Groups() {
    }
}