package kz.crtr.emaket.entity.tbl;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author a.kussein
 */
@Entity
@Table(name = "DCATEGORY")
@Getter
@Setter
@Proxy(lazy = false)
public class DCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;

    public DCategory() {
    }
}