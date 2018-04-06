package e.carlos.proyecto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import e.carlos.proyecto.dao.DaoApplication;
import e.carlos.proyecto.modelos.Asignatura;
import e.carlos.proyecto.modelos.DaoSession;

public class ListaAsignaturas extends AppCompatActivity implements AsignaturaAdapter.OnAsignaturaItemClickListener{

    @BindView(R.id.rv_asignaturas)
    RecyclerView rvAsignaturas;
    @BindView(R.id.btn_agregar)
    Button btnAgregar;

    private List<Asignatura> asignaturas;
    private AsignaturaAdapter asignaturaAdapter;
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_asignaturas);
        ButterKnife.bind(this);

        DaoApplication daoApplication = (DaoApplication) getApplication();
        daoSession = daoApplication.getDaoSession();

        asignaturaAdapter = new AsignaturaAdapter(this);

        rvAsignaturas.setLayoutManager(new LinearLayoutManager(this));
        rvAsignaturas.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rvAsignaturas.setAdapter(asignaturaAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        asignaturas = daoSession.getAsignaturaDao().loadAll();
        asignaturaAdapter.addList(asignaturas);

        if(asignaturas.size()==0){
            Toast.makeText(this, "No hay asignaturas", Toast.LENGTH_SHORT).show();
        }
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ListaAsignaturas.class);
        context.startActivity(starter);
    }

    @OnClick(R.id.btn_agregar)
    public void onViewClicked() {
        AsignaturasForm.start(this,null);
    }

    @Override
    public void onItemClick(Asignatura asignatura) {

    }

    @Override
    public void onEditarAsignaturaClick(Asignatura asignatura) {
        AsignaturasForm.start(this,asignatura);
    }

    @Override
    public void onEliminarAsignaturaClick(Asignatura asignatura) {
        daoSession.delete(asignatura);
        asignaturas = daoSession.getAsignaturaDao().loadAll();
        asignaturaAdapter.addList(asignaturas);
        Toast.makeText(this, "Asignatura Eliminada", Toast.LENGTH_SHORT).show();
    }
}
