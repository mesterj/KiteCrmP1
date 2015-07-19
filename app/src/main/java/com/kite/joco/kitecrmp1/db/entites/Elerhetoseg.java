package com.kite.joco.kitecrmp1.db.entites;

import com.kite.joco.kitecrmp1.db.CrmDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;

/**
 * Created by Joco on 2015.05.24..
 */

@Table(databaseName = CrmDatabase.DATABASE_NAME)
public class Elerhetoseg extends BaseModel{

    @Column
    @PrimaryKey(autoincrement = true)
    Long id;

    @Column
    @ForeignKey(
            references = {@ForeignKeyReference(columnName = "tipus",
                    columnType = Long.class,
                    foreignColumnName = "id")},
            saveForeignKeyModel = false)
    Elerhetoseg_tipus tipus;

    @Column
    @ForeignKey(
            references = {@ForeignKeyReference(columnName = "contact_id",
                    columnType = Long.class,
                    foreignColumnName = "id")},
            saveForeignKeyModel = false)
    ForeignKeyContainer<Contact> contact_id;

    @Column
    String elerhetosegadat;

    @Column
    boolean ceges;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Elerhetoseg_tipus getTipus() {
        return tipus;
    }

    public void setTipus(Elerhetoseg_tipus tipus) {
        this.tipus = tipus;
    }

    public ForeignKeyContainer<Contact> getContact_id() {
        return contact_id;
    }

    public void setContact_id(ForeignKeyContainer<Contact> contact_id) {
        this.contact_id = contact_id;
    }

    public String getElerhetosegadat() {
        return elerhetosegadat;
    }

    public void setElerhetosegadat(String elerhetosegadat) {
        this.elerhetosegadat = elerhetosegadat;
    }

    public boolean isCeges() {
        return ceges;
    }

    public void setCeges(boolean ceges) {
        this.ceges = ceges;
    }
}
