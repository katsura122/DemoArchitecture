package com.katsuraf.demoarchitecture.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.katsuraf.demoarchitecture.db.bean.ListItemEntity;
import com.katsuraf.demoarchitecture.db.bean.PageStateEntity;
import com.katsuraf.demoarchitecture.db.model.DaoMaster;
import com.katsuraf.demoarchitecture.db.model.DaoSession;
import com.katsuraf.demoarchitecture.db.model.ListItemEntityDao;
import com.katsuraf.demoarchitecture.db.model.PageStateEntityDao;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class DBHelper {
    private static DBHelper msInstance;
    private ListItemEntityDao mListItemEntityDao;
    private PageStateEntityDao mPageStateEntityDao;

    private DBHelper() {
    }

    public static DBHelper getInstance() {
        if (msInstance == null) {
            Log.e("", "You must call init method first in your application");
        }
        return msInstance;
    }

    public static void init(Context context) {
        if (msInstance == null) {
            msInstance = new DBHelper();
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,
                    "news-db", null);
            SQLiteDatabase db = helper.getReadableDatabase();
            DaoMaster master = new DaoMaster(db);
            DaoSession session = master.newSession();
            msInstance.mListItemEntityDao = session.getListItemEntityDao();
            msInstance.mPageStateEntityDao = session.getPageStateEntityDao();
        }
    }

    public void insertListBeans(List<ListItemEntity> listEntities) {
        mListItemEntityDao.insertInTx(listEntities);
    }

    public List<ListItemEntity> queryListData(int listType) {
        return mListItemEntityDao.queryBuilder()
                .where(ListItemEntityDao.Properties.Type.eq(listType))
                .build()
                .list();
    }

    public void deleteListData(int listType) {
        QueryBuilder<ListItemEntity> queryBuilder = mListItemEntityDao.queryBuilder();
        DeleteQuery<ListItemEntity> deleteQuery =
                queryBuilder.where(ListItemEntityDao.Properties.Type.eq(listType)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }

    public PageStateEntity queryPageState(int listType) {
        QueryBuilder<PageStateEntity> queryBuilder = mPageStateEntityDao.queryBuilder();
        queryBuilder.where(PageStateEntityDao.Properties.ListType.eq(listType)).build();
        try {
            return queryBuilder.list().get(0);
        } catch (Exception exp) {
            return null;
        }
    }

    public void addPageState(int listType, Integer page, Long timestamp) {
        PageStateEntity pageState = new PageStateEntity();
        pageState.setListType(listType);
        pageState.setPage(page);
        pageState.setTimestamp(timestamp);
        mPageStateEntityDao.insertOrReplace(pageState);
    }

}
