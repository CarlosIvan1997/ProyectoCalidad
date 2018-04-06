package e.carlos.proyecto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import e.carlos.proyecto.dao.DaoApplication;
import e.carlos.proyecto.modelos.Asignatura;
import e.carlos.proyecto.modelos.DaoSession;

public class AsignaturasForm extends AppCompatActivity {

    @BindView(R.id.et_nombre)
    EditText etNombre;
    @BindView(R.id.btn_guardar)
    Button btnGuardar;

    private Asignatura asignatura;
    private boolean actualizar;
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignaturas_form);
        ButterKnife.bind(this);

        asignatura = getIntent().getParcelableExtra("asignatura");

        DaoApplication daoApplication = (DaoApplication) getApplication();
        daoSession = daoApplication.getDaoSession();

        if (asignatura == null) {
            btnGuardar.setText("AGREGAR");
            actualizar=false;
        }else{
            btnGuardar.setText("EDITAR");
            etNombre.setText(asignatura.getNombre().toString());
            actualizar=true;
        }
    }

    public static void start(Context context, Asignatura asignatura) {
        Intent starter = new Intent(context, AsignaturasForm.class);
        starter.putExtra("asignatura", asignatura);
        context.startActivity(starter);
    }

    @OnClick(R.id.btn_guardar)
    public void onViewClicked() {
        if (actualizar){
            editarAsignatura();
        }else{
            agregarAsignatura();
        }
    }

    public void agregarAsignatura(){
        if(etNombre.getText().toString().trim().equals("")){
            Toast.makeText(this, "Ingrese la asignatura", Toast.LENGTH_SHORT).show();
        }else{
            Asignatura miAsignatura = new Asignatura();
            miAsignatura.setNombre(etNombre.getText().toString().toUpperCase().trim());

            daoSession.getAsignaturaDao().insert(miAsignatura);
            Toast.makeText(this, "Se agregó la asignatura " + etNombre.getText().toString().toUpperCase().trim(), Toast.LENGTH_SHORT).show();

            finish();
        }
    }

    public void editarAsignatura(){
        if(etNombre.getText().toString().trim().equals("")){
            Toast.makeText(this, "Ingrese la asignatura", Toast.LENGTH_SHORT).show();
        }else{
            asignatura.setNombre(etNombre.getText().toString().toUpperCase().trim());

            daoSession.getAsignaturaDao().update(asignatura);

            Toast.makeText(this, "La asignatura se ha actualizado correctamente", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
