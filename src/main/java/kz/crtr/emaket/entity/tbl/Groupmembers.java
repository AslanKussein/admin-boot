package kz.crtr.emaket.entity.tbl;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author a.kussein
 */
@Entity
@Table(name = "GROUPMEMBERS")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Groupmembers.findByGMember", query = "SELECT j FROM Groupmembers j where j.gMember = :gMember")
})
@Getter
public class Groupmembers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Size(min = 1, max = 50)
    @Column(name = "ID")
    private String id;
    @Size(min = 1, max = 200)
    @Column(name = "G_MEMBER")
    private String gMember;
    @JoinColumn(name = "G_NAME", referencedColumnName = "G_NAME")
    @ManyToOne(optional = false)
    private Groups gName;

    public Groupmembers() {
    }
}