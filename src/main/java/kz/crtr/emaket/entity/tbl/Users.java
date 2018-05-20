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
@Table(name = "USERS")
@Getter
@Proxy(lazy = false)
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "EMP_ID")
    private Long empId;
    @Size(min = 1, max = 200)
    @Column(name = "U_NAME")
    private String uName;
    @Size(max = 50)
    @Column(name = "U_PASSWORD")
    private String uPassword;
    @Size(max = 1000)
    @Column(name = "U_DESCRIPTION")
    private String uDescription;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "empId")
    private UserDetail userDetail;

    public Users() {
    }

    public Users(Long empId) {
        this.empId = empId;
    }
}
