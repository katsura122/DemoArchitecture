package com.katsuraf.demoarchitecture.db.model;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.katsuraf.demoarchitecture.db.bean.ListItemEntity;
import com.katsuraf.demoarchitecture.db.bean.PageStateEntity;

import com.katsuraf.demoarchitecture.db.model.ListItemEntityDao;
import com.katsuraf.demoarchitecture.db.model.PageStateEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig listItemEntityDaoConfig;
    private final DaoConfig pageStateEntityDaoConfig;

    private final ListItemEntityDao listItemEntityDao;
    private final PageStateEntityDao pageStateEntityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        listItemEntityDaoConfig = daoConfigMap.get(ListItemEntityDao.class).clone();
        listItemEntityDaoConfig.initIdentityScope(type);

        pageStateEntityDaoConfig = daoConfigMap.get(PageStateEntityDao.class).clone();
        pageStateEntityDaoConfig.initIdentityScope(type);

        listItemEntityDao = new ListItemEntityDao(listItemEntityDaoConfig, this);
        pageStateEntityDao = new PageStateEntityDao(pageStateEntityDaoConfig, this);

        registerDao(ListItemEntity.class, listItemEntityDao);
        registerDao(PageStateEntity.class, pageStateEntityDao);
    }
    
    public void clear() {
        listItemEntityDaoConfig.clearIdentityScope();
        pageStateEntityDaoConfig.clearIdentityScope();
    }

    public ListItemEntityDao getListItemEntityDao() {
        return listItemEntityDao;
    }

    public PageStateEntityDao getPageStateEntityDao() {
        return pageStateEntityDao;
    }

}