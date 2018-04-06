package e.carlos.proyecto;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import e.carlos.proyecto.modelos.DaoMaster;
import e.carlos.proyecto.modelos.DaoSession;

public class DaoApplication extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(this,"mydb");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }
}
