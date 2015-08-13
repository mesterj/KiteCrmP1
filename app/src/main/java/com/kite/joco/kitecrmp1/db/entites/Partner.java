package com.kite.joco.kitecrmp1.db.entites;

import com.kite.joco.kitecrmp1.db.CrmDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
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
@Table(databaseName = CrmDatabase.DATABASE_NAME,tableName = "Partner")
public class Partner extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    Long id;

    @Column
    String nev;
    @Column
    String telepules;
    @Column
    String irsz;
    @Column
    String utca;
    @Column(name = "PS")
    String ps;
    @Column
    String adoszam;

    List<Contact> kapcsolatok;


    @OneToMany(methods = OneToMany.Method.ALL, variableName = "kapcsolatok")
    public List<Contact> getKapcsolatok() {
        if (kapcsolatok == null) {
            kapcsolatok = new Select().from(Contact.class)
                    .where(Condition.column(Contact$Table.PARTNERFOREIGNKEYCONTAINER_PARTNER_ID).is(id))
                    .queryList();
        }
        return kapcsolatok;
    }

    public void setKapcsolatok(List<Contact> kapcsolatok) {
        this.kapcsolatok = kapcsolatok;
    }

    public Long getId() {
        return id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getTelepules() {
        return telepules;
    }

    public void setTelepules(String telepules) {
        this.telepules = telepules;
    }

    public String getIrsz() {
        return irsz;
    }

    public void setIrsz(String irsz) {
        this.irsz = irsz;
    }

    public String getUtca() {
        return utca;
    }

    public void setUtca(String utca) {
        this.utca = utca;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getAdoszam() {
        return adoszam;
    }

    public void setAdoszam(String adoszam) {
        this.adoszam = adoszam;
    }
}
