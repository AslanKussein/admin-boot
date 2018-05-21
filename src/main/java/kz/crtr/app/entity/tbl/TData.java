package kz.crtr.app.entity.tbl;


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
@Table(name = "T_DATA")
@Getter
@Setter
@Proxy(lazy = false)
public class TData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "DESCR")
    private String descr;
    @Column(name = "COST")
    private BigDecimal cost;
    @Column(name = "COUNT")
    private Integer count;
    @Column(name = "T_NAME")
    private String name;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tData")
    private TImage tImage;
    @Column(name = "img_id")
    private String imgId;
    @Column(name = "CAT_ID")
    private Integer catId;

    public TData() {
    }
}
