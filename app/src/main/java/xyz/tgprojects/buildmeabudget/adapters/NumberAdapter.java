package xyz.tgprojects.buildmeabudget.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.List;
import xyz.tgprojects.buildmeabudget.R;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.ViewHolder> {

    Context context;
    List<String> values;


    public NumberAdapter(Context context, List<String> values){
        this.context = context;
        this.values = values;
    }

    @Override public NumberAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.number_button, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        if ( position == 9 || position == 11 ){
            holder.button.setClickable(false);
            holder.view.setVisibility(View.GONE);
        }
        holder.button.setText(values.get(position));
    }

    @Override public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public Button button;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.number_button);
            view = (View) itemView.findViewById(R.id.horizontal_rule);
        }
    }
}
