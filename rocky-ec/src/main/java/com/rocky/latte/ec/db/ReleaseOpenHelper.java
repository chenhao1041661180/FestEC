package com.rocky.latte.ec.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.rocky.latte.ec.entity.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/25
 */

public class ReleaseOpenHelper extends DaoMaster.OpenHelper {
    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    public ReleaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }
}
