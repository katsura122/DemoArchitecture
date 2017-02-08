package com.katsuraf.demoarchitecture.db.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.katsuraf.demoarchitecture.db.bean.ListItemEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LIST_ITEM_ENTITY".
*/
public class ListItemEntityDao extends AbstractDao<ListItemEntity, Long> {

    public static final String TABLENAME = "LIST_ITEM_ENTITY";

    /**
     * Properties of entity ListItemEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ItemId = new Property(1, long.class, "itemId", false, "ITEM_ID");
        public final static Property Type = new Property(2, int.class, "type", false, "TYPE");
        public final static Property Title = new Property(3, String.class, "title", false, "TITLE");
        public final static Property SubTitle = new Property(4, String.class, "subTitle", false, "SUB_TITLE");
        public final static Property Link = new Property(5, String.class, "link", false, "LINK");
        public final static Property ImageLink = new Property(6, String.class, "imageLink", false, "IMAGE_LINK");
        public final static Property Date = new Property(7, String.class, "date", false, "DATE");
        public final static Property ReadCount = new Property(8, String.class, "readCount", false, "READ_COUNT");
        public final static Property CommentCount = new Property(9, String.class, "commentCount", false, "COMMENT_COUNT");
    }


    public ListItemEntityDao(DaoConfig config) {
        super(config);
    }
    
    public ListItemEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LIST_ITEM_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"ITEM_ID\" INTEGER NOT NULL ," + // 1: itemId
                "\"TYPE\" INTEGER NOT NULL ," + // 2: type
                "\"TITLE\" TEXT," + // 3: title
                "\"SUB_TITLE\" TEXT," + // 4: subTitle
                "\"LINK\" TEXT," + // 5: link
                "\"IMAGE_LINK\" TEXT," + // 6: imageLink
                "\"DATE\" TEXT," + // 7: date
                "\"READ_COUNT\" TEXT," + // 8: readCount
                "\"COMMENT_COUNT\" TEXT);"); // 9: commentCount
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LIST_ITEM_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ListItemEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getItemId());
        stmt.bindLong(3, entity.getType());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
 
        String subTitle = entity.getSubTitle();
        if (subTitle != null) {
            stmt.bindString(5, subTitle);
        }
 
        String link = entity.getLink();
        if (link != null) {
            stmt.bindString(6, link);
        }
 
        String imageLink = entity.getImageLink();
        if (imageLink != null) {
            stmt.bindString(7, imageLink);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(8, date);
        }
 
        String readCount = entity.getReadCount();
        if (readCount != null) {
            stmt.bindString(9, readCount);
        }
 
        String commentCount = entity.getCommentCount();
        if (commentCount != null) {
            stmt.bindString(10, commentCount);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ListItemEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getItemId());
        stmt.bindLong(3, entity.getType());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
 
        String subTitle = entity.getSubTitle();
        if (subTitle != null) {
            stmt.bindString(5, subTitle);
        }
 
        String link = entity.getLink();
        if (link != null) {
            stmt.bindString(6, link);
        }
 
        String imageLink = entity.getImageLink();
        if (imageLink != null) {
            stmt.bindString(7, imageLink);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(8, date);
        }
 
        String readCount = entity.getReadCount();
        if (readCount != null) {
            stmt.bindString(9, readCount);
        }
 
        String commentCount = entity.getCommentCount();
        if (commentCount != null) {
            stmt.bindString(10, commentCount);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ListItemEntity readEntity(Cursor cursor, int offset) {
        ListItemEntity entity = new ListItemEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // itemId
            cursor.getInt(offset + 2), // type
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // title
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // subTitle
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // link
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // imageLink
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // date
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // readCount
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // commentCount
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ListItemEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setItemId(cursor.getLong(offset + 1));
        entity.setType(cursor.getInt(offset + 2));
        entity.setTitle(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSubTitle(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setLink(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setImageLink(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setDate(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setReadCount(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setCommentCount(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ListItemEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ListItemEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ListItemEntity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
