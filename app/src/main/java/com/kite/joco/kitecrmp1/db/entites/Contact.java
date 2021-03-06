package com.kite.joco.kitecrmp1.db.entites;

import android.util.Log;

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
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;

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
    long beosztas_id;

    @Column
    long partner_id;

    List<Elerhetoseg> elerhetosegList;

    @OneToMany(methods = OneToMany.Method.ALL)
    public List<Elerhetoseg> getElerhetosegList() {
        if (elerhetosegList == null) {
            elerhetosegList = new Select().from(Elerhetoseg.class)
                    .where(Condition.column(Elerhetoseg$Table.CONTACT_ID).is(id))
                    .queryList();
        }
        return elerhetosegList;
    }

    /*/ To assiciate to partner
    public void addToPartner(Partner p){
        this.partnerForeignKeyContainer = new ForeignKeyContainer<>(Partner.class);
        this.partnerForeignKeyContainer.put(Partner$Table.ID,p.getId());
    }

*/

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


    public void setElerhetosegList(List<Elerhetoseg> elerhetosegList) {
        this.elerhetosegList = elerhetosegList;
    }

    public long getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(long partner_id) {
        this.partner_id = partner_id;
    }

    public Partner getContactPartner(){
        try {
            long keresett = this.getId();
            Partner p = new Select().from(Partner.class).where(Condition.column(Partner$Table.ID).eq(keresett)).querySingle();
            return p;
        }
        catch (Exception ex){
            Log.e("CRMDB:CONTACT"," Nem sikerült a contact partnerét kikeresni " + ex.getMessage() );
            return null;
        }
    }

    public long getBeosztas_id() {
        return beosztas_id;
    }

    public void setBeosztas_id(long beosztas_id) {
        this.beosztas_id = beosztas_id;
    }

    public Beosztas getContactBeosztas(){
        try {
            long beoid = this.getBeosztas_id();
            Beosztas b = new Select().from(Beosztas.class).where(Condition.column(Beosztas$Table.ID).eq(beoid)).querySingle();
            return b;
        }
        catch (Exception ex){
            Log.e("CDRMDB:CONTACT", " getContactBeosztas "+ ex.getLocalizedMessage());
            return null;
        }
    }
}

