package com.rocky.latte.ec.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.rocky.latte.ec.entity.DaoMaster;
import com.rocky.latte.ec.entity.DaoSession;
import com.rocky.latte.ec.entity.UserDao;

import org.greenrobot.greendao.database.Database;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/25
 */

public class DaoManager {
    private DaoSession mDaoSession = null;
    private UserDao mUserDao = null;

    private static final class Holder {
        private static final DaoManager INSTANCE = new DaoManager();
    }

    public DaoManager() {
    }

    public static DaoManager getInstance() {
        return Holder.INSTANCE;
    }

    public DaoManager init(Context context) {
        initDao(context);
        return this;
    }

    private void initDao(Context context) {
        ReleaseOpenHelper openHelper = new ReleaseOpenHelper(context, "fest_ec.db");
        Database db = openHelper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mUserDao = mDaoSession.getUserDao();
    }

    public final UserDao getUserDao() {
        return mUserDao;
    }
}
