package com.kite.joco.kitecrmp1.db.entites;

import android.util.Log;

import com.kite.joco.kitecrmp1.db.CrmDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Joco on 2015.05.24..
 */
@ModelContainer
@Table(databaseName = CrmDatabase.DATABASE_NAME)
public class Elerhetoseg extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    Long id;

    @Column
    Long eltipid;

    @Column
    Long contact_id;

    /*@Column
    @ForeignKey(
            references = {@ForeignKeyReference(columnName = "tipus",
                    columnType = Long.class,
                    foreignColumnName = "id")},
            saveForeignKeyModel = false)
    Elerhetoseg_tipus tipus;*/

    /*@Column
    @ForeignKey(
            references = {@ForeignKeyReference(columnName = "contact_id",
                    columnType = Long.class,
                    foreignColumnName = "id")},
            saveForeignKeyModel = false)
    ForeignKeyContainer<Contact> contact_id;*/


    @Column
    String elerhetosegadat;

    // containeres megoldással adja hozzá a contactot az elérhetőséghez
    /*public void addToContact(Contact c) {
        this.contact_id = new ForeignKeyContainer<>(Contact.class);
        this.contact_id.put(Contact$Table.ID, c.getId());
    }*/

    // container nélkül összekapcsolja a contacttal az aktuális elérhetőséget
    public void addToContact(Contact c) {
        this.setContact_id(c.getId());
    }

    // Visszaadja ennek az elérhetőségnek a contact-ját
    public Contact getContact() {

        long l = this.getContact_id();
        Contact c = new Select().from(Contact.class).where(Condition.column(Elerhetoseg$Table.CONTACT_ID).eq(l)).querySingle();
        Log.i("CRM:Elerhetoseg", " contact id  : " + c.getId());
        return c;
    }

    public void setTipus(Elerhetoseg_tipus etipus) {
        setEltipid(etipus.getId());
    }

    @Column
    boolean ceges;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public long getContact_id() {
        return contact_id;
    }

    public void setContact_id(long contact_id) {
        this.contact_id = contact_id;
    }

    public Long getEltipid() {
        return eltipid;
    }

    public void setEltipid(Long eltipid) {
        this.eltipid = eltipid;
    }
}
