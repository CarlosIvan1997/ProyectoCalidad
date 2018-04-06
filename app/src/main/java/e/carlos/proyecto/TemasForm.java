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
import e.carlos.proyecto.modelos.Tema;
import e.carlos.proyecto.modelos.TemaDao;

public class TemasForm extends AppCompatActivity {

    @BindView(R.id.et_tema_nombre)
    EditText etTemaNombre;
    @BindView(R.id.btn_tema_guardar)
    Button btnTemaGuardar;

    private Tema tema;
    private Asignatura asignatura;
    private boolean actualizar;
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temas_form);
        ButterKnife.bind(this);

        tema = getIntent().getParcelableExtra("tema");
        asignatura = getIntent().getParcelableExtra("asignatura");

        DaoApplication daoApplication = (DaoApplication) getApplication();
        daoSession = daoApplication.getDaoSession();

        if(tema==null){
            btnTemaGuardar.setText("AGREGAR");
            actualizar=false;
        }else{
            btnTemaGuardar.setText("EDITAR");
            etTemaNombre.setText(tema.getNombreTema().toString());
            actualizar=true;
        }
    }

    public static void start(Context context, Tema tema, Asignatura asignatura){
        Intent starter = new Intent(context,TemasForm.class);
        starter.putExtra("tema", tema);
        starter.putExtra("asignatura", asignatura);
        context.startActivity(starter);
    }

    @OnClick(R.id.btn_tema_guardar)
    public void onViewClicked() {
        if(actualizar){
            editarTema();
        }else {
            agregarTema();
        }
    }

    public void agregarTema(){
        if(etTemaNombre.getText().toString().trim().equals("")){
            Toast.makeText(this, "Ingrese el tema", Toast.LENGTH_SHORT).show();
        }else{
            Tema miTema = new Tema();
            miTema.setNombreTema(etTemaNombre.getText().toString().toUpperCase().trim());
            miTema.setAsignaturaId(asignatura.getId());

            daoSession.getTemaDao().insert(miTema);
            Toast.makeText(this, "Se agregó el tema " + etTemaNombre.getText().toString().toUpperCase().trim(), Toast.LENGTH_SHORT).show();

            finish();
        }
    }

    public void editarTema(){
        if(etTemaNombre.getText().toString().trim().equals("")){
            Toast.makeText(this, "Ingrese el tema", Toast.LENGTH_SHORT).show();
        }else{
            tema.setNombreTema(etTemaNombre.getText().toString().toUpperCase().trim());

            daoSession.getTemaDao().update(tema);

            Toast.makeText(this, "El tema se ha actualizado correctamente", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
