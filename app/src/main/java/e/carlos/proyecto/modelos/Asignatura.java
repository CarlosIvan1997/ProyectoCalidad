package e.carlos.proyecto.modelos;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Asignatura implements Parcelable {

    @Id (autoincrement = true)
    private Long id;

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
}
