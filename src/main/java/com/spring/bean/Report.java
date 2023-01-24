package com.spring.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Report implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date_;

    private String carType;


    private String carId;


    private String bodyCarId;


    private String note_1="";


    private String note_2="";


    private String note_3="";


    private String note_4="";


    private String note_5="";


    private String note_6="";
    @Lob

    @Column(columnDefinition = "OID")
    private byte[] image_1;
    private Boolean shock_1 = false;

    @Lob

    @Column(columnDefinition = "OID")
    private byte[] image_2;
    private Boolean shock_2= false;


    @Lob
    @Column(columnDefinition = "OID")
    private byte[] image_3;
    private Boolean shock_3= false;


    @Lob

    @Column(columnDefinition = "OID")
    private byte[] image_4;
    private Boolean shock_4= false;

    @Lob

    @Column(columnDefinition = "OID")
    private byte[] image_5;
    private Boolean shock_5= false;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        return id.equals(report.id);
    }

    @Override
    public int hashCode() {
        if (id == null) {
            return super.hashCode();
        }
        return id.hashCode();
    }

    @Transient
    public boolean isNew() {
        return id == null;
    }


}
