package com.sml.greendao.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.sml.greendao.bean.Meizi;

import com.sml.greendao.dao.MeiziDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig meiziDaoConfig;

    private final MeiziDao meiziDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        meiziDaoConfig = daoConfigMap.get(MeiziDao.class).clone();
        meiziDaoConfig.initIdentityScope(type);

        meiziDao = new MeiziDao(meiziDaoConfig, this);

        registerDao(Meizi.class, meiziDao);
    }
    
    public void clear() {
        meiziDaoConfig.clearIdentityScope();
    }

    public MeiziDao getMeiziDao() {
        return meiziDao;
    }

}
