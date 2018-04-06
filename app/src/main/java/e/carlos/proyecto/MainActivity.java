package e.carlos.proyecto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_asignaturas, R.id.btn_temas})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_asignaturas:
                ListaAsignaturas.start(this);
                break;
            case R.id.btn_temas:
                ListaATemas.start(this);
                break;
        }
    }
}


