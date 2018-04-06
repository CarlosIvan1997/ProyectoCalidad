package e.carlos.proyecto;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import e.carlos.proyecto.modelos.Asignatura;

public class AsignaturaAdapter extends RecyclerView.Adapter<AsignaturaAdapter.AsignaturaViewHolder>{

    private List<Asignatura> asignaturas;

    private OnAsignaturaItemClickListener onAsignaturaItemClick;

    public AsignaturaAdapter(OnAsignaturaItemClickListener onAsignaturaItemClick){
        this.onAsignaturaItemClick = onAsignaturaItemClick;
    }

    public void addList(List<Asignatura> asignaturas){
        this.asignaturas=asignaturas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AsignaturaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_asignatura,parent,false);
        return new AsignaturaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AsignaturaViewHolder holder, int position) {
        final Asignatura asignatura = asignaturas.get(position);

        holder.tvNombre.setText(asignatura.getNombre().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onAsignaturaItemClick!=null){
                    onAsignaturaItemClick.onItemClick(asignatura);
                }
            }
        });

        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onAsignaturaItemClick!=null){
                    onAsignaturaItemClick.onEditarAsignaturaClick(asignatura);
                }
            }
        });

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onAsignaturaItemClick!=null){
                    onAsignaturaItemClick.onEliminarAsignaturaClick(asignatura);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(asignaturas==null){
            return 0;
        }else{
            return asignaturas.size();
        }
    }

    class AsignaturaViewHolder extends RecyclerView.ViewHolder{

        TextView tvNombre;
        Button btnEditar, btnEliminar;

        public AsignaturaViewHolder(View itemView) {
            super(itemView);
            tvNombre=itemView.findViewById(R.id.tv_nombre);
            btnEditar=itemView.findViewById(R.id.btn_editar);
            btnEliminar=itemView.findViewById(R.id.btn_eliminar);
        }
    }

    public interface OnAsignaturaItemClickListener{
        void onItemClick(Asignatura asignatura);
        void onEditarAsignaturaClick(Asignatura asignatura);
        void onEliminarAsignaturaClick(Asignatura asignatura);
    }
}


