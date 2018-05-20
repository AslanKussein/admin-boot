package kz.crtr.emaket.entity.tbl;


import lombok.Getter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author a.kussein
 */
@Entity
@Table(name = "USER_DETAIL")
@Getter
@Proxy(lazy = false)
public class UserDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    private String id;
    @Size(max = 12)
    @Column(name = "IIN")
    private String iin;
    @Size(max = 200)
    @Column(name = "LASTNAME")
    private String lastname;
    @Size(max = 200)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Size(max = 200)
    @Column(name = "MIDDLENAME")
    private String middlename;
    @JoinColumn(name = "EMP_ID", referencedColumnName = "EMP_ID")
    @OneToOne(optional = false)
    private Users empId;

    public UserDetail() {
    }
}
