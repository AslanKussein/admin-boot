package kz.crtr.app.entity.tbl;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author a.kussein
 */
@Entity
@Table(name = "T_IMAGE")
@Getter
@Setter
@Proxy(lazy = false)
public class TImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "T_ID")
    private String id;
    @Size(min = 1, max = 200)
    @Column(name = "file_name")
    private String 	fileName;
    @Lob
    @Column(name = "fileB")
    private byte[] content;
    @JoinColumn(name = "T_ID", referencedColumnName = "IMG_ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TData tData;

    public TImage() {
    }

    public TImage(String id) {
        this.id = id;
    }
}
