package e.carlos.proyecto;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import e.carlos.proyecto.modelos.Asignatura;

public class ATemasAdapter extends RecyclerView.Adapter<ATemasAdapter.ATemasViewHolder>{

    private List<Asignatura> asignaturas;

    private OnATemaItemClickListener onATemaItemClick;

    public ATemasAdapter(OnATemaItemClickListener onATemaItemClick){
        this.onATemaItemClick=onATemaItemClick;
    }

    public void addList(List<Asignatura> asignaturas){
        this.asignaturas = asignaturas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ATemasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_atemas, parent, false);
        return new ATemasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ATemasViewHolder holder, int position) {
        final Asignatura asignatura = asignaturas.get(position);

        holder.tvAnombre.setText(asignatura.getNombre().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onATemaItemClick!=null){
                    onATemaItemClick.onItemClick(asignatura);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(asignaturas==null){
            return 0;
        }else {
            return asignaturas.size();
        }
    }

    class ATemasViewHolder extends RecyclerView.ViewHolder{
        TextView tvAnombre;

        public ATemasViewHolder (View itemView){
            super(itemView);
            tvAnombre= itemView.findViewById(R.id.tv_anombre);
        }
    }
    public interface OnATemaItemClickListener{
        void onItemClick(Asignatura asignatura);
    }
}
