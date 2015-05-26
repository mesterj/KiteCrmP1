package com.kite.joco.kitecrmp1.db.entites;

import com.kite.joco.kitecrmp1.db.CrmDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

/**
 * Created by Joco on 2015.05.24..
 */

@ModelContainer
@Table(databaseName = CrmDatabase.DATABASE_NAME)
public class Contact extends BaseModel{

    @Column
    @PrimaryKey(autoincrement = true)
    Long id;

    @Column
    String contact_vezeteknev;
    @Column
    String contact_keresztnev;
    @Column
    String contact_titulus;
    @Column
    String contact_kozepsonev;



    @Column
    @ForeignKey(
            references = {@ForeignKeyReference(columnName = "beosztas_id",
                    columnType = Long.class,
                    foreignColumnName = "id")},
            saveForeignKeyModel = false)
    Beosztas beosztas;

    @Column
    @ForeignKey(
            references = {@ForeignKeyReference(columnName = "partner_id",
                    columnType = Long.class,
                    foreignColumnName = "id")},
            saveForeignKeyModel = false)
    Partner partner;


    List<Elerhetoseg> elerhetosegList;

    @OneToMany(methods = OneToMany.Method.ALL)
    public List<Elerhetoseg> getElerhetosegList() {
        if (elerhetosegList == null) {
            elerhetosegList = new Select().from(Elerhetoseg.class)
                    .where(Condition.column(Elerhetoseg$Table.CONTACT_ID_CONTACT_ID).is(id))
                    .queryList();
        }
        return elerhetosegList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContact_vezeteknev() {
        return contact_vezeteknev;
    }

    public void setContact_vezeteknev(String contact_vezeteknev) {
        this.contact_vezeteknev = contact_vezeteknev;
    }

    public String getContact_keresztnev() {
        return contact_keresztnev;
    }

    public void setContact_keresztnev(String contact_keresztnev) {
        this.contact_keresztnev = contact_keresztnev;
    }

    public String getContact_titulus() {
        return contact_titulus;
    }

    public void setContact_titulus(String contact_titulus) {
        this.contact_titulus = contact_titulus;
    }

    public String getContact_kozepsonev() {
        return contact_kozepsonev;
    }

    public void setContact_kozepsonev(String contact_kozepsonev) {
        this.contact_kozepsonev = contact_kozepsonev;
    }

    public Beosztas getBeosztas() {
        return beosztas;
    }

    public void setBeosztas(Beosztas beosztas) {
        this.beosztas = beosztas;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public void setElerhetosegList(List<Elerhetoseg> elerhetosegList) {
        this.elerhetosegList = elerhetosegList;
    }
}

