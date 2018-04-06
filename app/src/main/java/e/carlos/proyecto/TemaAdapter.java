package e.carlos.proyecto;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import e.carlos.proyecto.modelos.Tema;

public class TemaAdapter extends RecyclerView.Adapter<TemaAdapter.TemaViewHolder>{

    private List<Tema> temas;

    private OnTemaItemClickListener onTemaItemClick;

    public TemaAdapter(OnTemaItemClickListener onTemaItemClick){
        this.onTemaItemClick = onTemaItemClick;
    }

    public void addList(List<Tema> temas){
        this.temas = temas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TemaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tema, parent, false);
        return new TemaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TemaViewHolder holder, int position) {
        final Tema tema = temas.get(position);

        holder.tvTemaNombre.setText(tema.getNombreTema().toString());

        holder.btnEliminarTema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onTemaItemClick!=null){
                    onTemaItemClick.onEliminarTemaClick(tema);
                }
            }
        });

        holder.btnEditarTema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onTemaItemClick!=null){
                    onTemaItemClick.onEditarTemaClick(tema);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (temas == null) {
            return 0;
        } else {
            return temas.size();
        }
    }


    class TemaViewHolder extends RecyclerView.ViewHolder{
        TextView tvTemaNombre;
        Button btnEditarTema, btnEliminarTema;

        public TemaViewHolder(View itemView){
            super(itemView);
            tvTemaNombre = itemView.findViewById(R.id.tv_tema_nombre);
            btnEditarTema = itemView.findViewById(R.id.btn_tema_editar);
            btnEliminarTema = itemView.findViewById(R.id.btn_tema_eliminar);
        }
    }

    public interface OnTemaItemClickListener{
        void onEditarTemaClick(Tema tema);
        void onEliminarTemaClick(Tema tema);
    }
}
