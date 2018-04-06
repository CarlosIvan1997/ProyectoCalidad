package e.carlos.proyecto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import e.carlos.proyecto.dao.DaoApplication;
import e.carlos.proyecto.modelos.Asignatura;
import e.carlos.proyecto.modelos.DaoSession;

public class ListaATemas extends AppCompatActivity implements ATemasAdapter.OnATemaItemClickListener {

    @BindView(R.id.rv_atemas)
    RecyclerView rvAtemas;

    private List<Asignatura> asignaturas;
    private ATemasAdapter aTemasAdapter;
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_atemas);
        ButterKnife.bind(this);

        DaoApplication daoApplication = (DaoApplication) getApplication();
        daoSession = daoApplication.getDaoSession();

        aTemasAdapter = new ATemasAdapter(this);

        rvAtemas.setLayoutManager(new LinearLayoutManager(this));
        rvAtemas.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvAtemas.setAdapter(aTemasAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        asignaturas = daoSession.getAsignaturaDao().loadAll();
        aTemasAdapter.addList(asignaturas);

        if(asignaturas.size()==0){
            Toast.makeText(this, "Debe crear una asignatura para agregar temas", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public static void start(Context context){
        Intent starter = new Intent(context, ListaATemas.class);
        context.startActivity(starter);
    }

    @Override
    public void onItemClick(Asignatura asignatura) {
        ListaTemas.start(this,asignatura);
    }
}
