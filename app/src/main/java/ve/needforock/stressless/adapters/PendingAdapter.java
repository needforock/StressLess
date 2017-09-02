package ve.needforock.stressless.adapters;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import ve.needforock.stressless.R;
import ve.needforock.stressless.data.Queries;
import ve.needforock.stressless.models.Pending;

/**
 * Created by Soporte on 14-Aug-17.
 */

public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.ViewHolder>{


    private List<Pending> pendings = new Queries().pendings();
    private PendingClickListener listener;

    public PendingAdapter(PendingClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pending, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Pending pending = pendings.get(position);
        holder.textView.setText(pending.getName());
        holder.checkBox.setChecked(pending.isDone());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int auxPosition = holder.getAdapterPosition();
                            Pending auxPending = pendings.get(auxPosition);
                            auxPending.setDone(true);
                            auxPending.save();
                            pendings.remove(auxPosition);
                            notifyItemRemoved(auxPosition);

                        }
                    },400);
                }

            }
        });

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pending auxPending = pendings.get(holder.getAdapterPosition());
                listener.clickedId(auxPending.getId());

            }
        });

    }

    /*public void add(Pending pending){
        pendings.add(pending);
    }*/

    @Override
    public int getItemCount() {
        return pendings.size();
    }

    public void update (Pending pending){
        List<Pending> nuevalista = new Queries().pendings();
        pendings.clear();
        pendings.addAll(nuevalista);
        notifyDataSetChanged();

    }

    public void updateByName(String name){
        List<Pending> byName = new Queries().byName(name);
        pendings.clear();
        pendings.addAll(byName);
        notifyDataSetChanged();

    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private CheckBox checkBox;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            checkBox = (CheckBox) itemView.findViewById(R.id.pendingCb);
            textView = (TextView) itemView.findViewById(R.id.pendingTv);
        }
    }
}
