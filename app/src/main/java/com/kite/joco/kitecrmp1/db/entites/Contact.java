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
    Beosztas besztas;

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
}

