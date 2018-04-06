package e.carlos.proyecto.modelos;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.DaoException;

@Entity
public class Asignatura implements Parcelable {

    @Id (autoincrement = true)
    private Long id;

    @ToMany(referencedJoinProperty = "asignaturaId")
    private List<Tema> temas;

    private String nombre;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.nombre);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1470448509)
    public List<Tema> getTemas() {
        if (temas == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TemaDao targetDao = daoSession.getTemaDao();
            List<Tema> temasNew = targetDao._queryAsignatura_Temas(id);
            synchronized (this) {
                if (temas == null) {
                    temas = temasNew;
                }
            }
        }
        return temas;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 2089242869)
    public synchronized void resetTemas() {
        temas = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1479446679)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getAsignaturaDao() : null;
    }

    public Asignatura() {
    }

    protected Asignatura(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.nombre = in.readString();
    }

    @Generated(hash = 1693338355)
    public Asignatura(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public static final Parcelable.Creator<Asignatura> CREATOR = new Parcelable.Creator<Asignatura>() {
        @Override
        public Asignatura createFromParcel(Parcel source) {
            return new Asignatura(source);
        }

        @Override
        public Asignatura[] newArray(int size) {
            return new Asignatura[size];
        }
    };

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1684524021)
    private transient AsignaturaDao myDao;
}
