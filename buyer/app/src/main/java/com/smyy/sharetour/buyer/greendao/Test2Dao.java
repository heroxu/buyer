package com.smyy.sharetour.buyer.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.smyy.sharetour.buyer.db.Test2;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TEST2".
*/
public class Test2Dao extends AbstractDao<Test2, Long> {

    public static final String TABLENAME = "TEST2";

    /**
     * Properties of entity Test2.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property BId = new Property(0, Long.class, "bId", true, "_id");
        public final static Property Photo = new Property(1, String.class, "photo", false, "PHOTO");
        public final static Property PhoneNum = new Property(2, String.class, "phoneNum", false, "PHONE_NUM");
    }


    public Test2Dao(DaoConfig config) {
        super(config);
    }
    
    public Test2Dao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TEST2\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: bId
                "\"PHOTO\" TEXT NOT NULL ," + // 1: photo
                "\"PHONE_NUM\" TEXT NOT NULL );"); // 2: phoneNum
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TEST2\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Test2 entity) {
        stmt.clearBindings();
 
        Long bId = entity.getBId();
        if (bId != null) {
            stmt.bindLong(1, bId);
        }
        stmt.bindString(2, entity.getPhoto());
        stmt.bindString(3, entity.getPhoneNum());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Test2 entity) {
        stmt.clearBindings();
 
        Long bId = entity.getBId();
        if (bId != null) {
            stmt.bindLong(1, bId);
        }
        stmt.bindString(2, entity.getPhoto());
        stmt.bindString(3, entity.getPhoneNum());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Test2 readEntity(Cursor cursor, int offset) {
        Test2 entity = new Test2( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // bId
            cursor.getString(offset + 1), // photo
            cursor.getString(offset + 2) // phoneNum
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Test2 entity, int offset) {
        entity.setBId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPhoto(cursor.getString(offset + 1));
        entity.setPhoneNum(cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Test2 entity, long rowId) {
        entity.setBId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Test2 entity) {
        if(entity != null) {
            return entity.getBId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Test2 entity) {
        return entity.getBId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
