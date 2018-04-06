package e.carlos.proyecto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;
import java.util.Properties;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import e.carlos.proyecto.dao.DaoApplication;
import e.carlos.proyecto.modelos.Asignatura;
import e.carlos.proyecto.modelos.DaoSession;
import e.carlos.proyecto.modelos.Tema;
import e.carlos.proyecto.modelos.TemaDao;

public class ListaTemas extends AppCompatActivity implements TemaAdapter.OnTemaItemClickListener {

    @BindView(R.id.rv_temas)
    RecyclerView rvTemas;

    private List<Tema> temas;
    private TemaAdapter temaAdapter;
    private DaoSession daoSession;
    private Asignatura asignatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_temas);
        ButterKnife.bind(this);

        DaoApplication daoApplication = (DaoApplication) getApplication();
        daoSession = daoApplication.getDaoSession();

        asignatura = getIntent().getParcelableExtra("asignatura");

        temaAdapter = new TemaAdapter(this);

        rvTemas.setLayoutManager(new LinearLayoutManager(this));
        rvTemas.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvTemas.setAdapter(temaAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        temas = daoSession.getTemaDao().queryBuilder().where(TemaDao.Properties.AsignaturaId.eq(asignatura.getId())).list();
        temaAdapter.addList(temas);

        if(temas.size()==0){
            Toast.makeText(this, "No hay temas en esta asignatura", Toast.LENGTH_SHORT).show();
        }
    }

    public static void start (Context context, Asignatura asignatura){
        Intent starter = new Intent(context,ListaTemas.class);
        starter.putExtra("asignatura", asignatura);
        context.startActivity(starter);
    }

    @OnClick(R.id.btn_tema_agregar)
    public void onViewClicked() {
        TemasForm.start(this,null,asignatura);
    }

    @Override
    public void onEditarTemaClick(Tema tema) {
        TemasForm.start(this,tema,asignatura);
    }

    @Override
    public void onEliminarTemaClick(Tema tema) {
        daoSession.delete(tema);
        temas = daoSession.getTemaDao().queryBuilder().where(TemaDao.Properties.AsignaturaId.eq(asignatura.getId())).list();
        temaAdapter.addList(temas);
        Toast.makeText(this, "Tema Eliminado", Toast.LENGTH_SHORT).show();
    }
}
