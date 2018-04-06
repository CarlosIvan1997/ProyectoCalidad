package e.carlos.proyecto.modelos;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Tema implements Parcelable {

    @Id(autoincrement = true)
    private Long id;

    private String nombreTema;
    private Long asignaturaId;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.nombreTema);
        dest.writeValue(this.asignaturaId);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreTema() {
        return this.nombreTema;
    }

    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
    }

    public Long getAsignaturaId() {
        return this.asignaturaId;
    }

    public void setAsignaturaId(Long asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    public Tema() {
    }

    protected Tema(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.nombreTema = in.readString();
        this.asignaturaId = (Long) in.readValue(Long.class.getClassLoader());
    }

    @Generated(hash = 1263811369)
    public Tema(Long id, String nombreTema, Long asignaturaId) {
        this.id = id;
        this.nombreTema = nombreTema;
        this.asignaturaId = asignaturaId;
    }

    public static final Parcelable.Creator<Tema> CREATOR = new Parcelable.Creator<Tema>() {
        @Override
        public Tema createFromParcel(Parcel source) {
            return new Tema(source);
        }

        @Override
        public Tema[] newArray(int size) {
            return new Tema[size];
        }
    };
}
