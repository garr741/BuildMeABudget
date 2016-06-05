package xyz.tgprojects.buildmeabudget.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.List;
import xyz.tgprojects.buildmeabudget.R;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.ViewHolder> {

    Context context;
    List<String> values;
    OnButtonClickedListener listener;


    public NumberAdapter(Context context, List<String> values, OnButtonClickedListener listener){
        this.context = context;
        this.values = values;
        this.listener = listener;
    }

    @Override public NumberAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.number_button, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        if ( position == 9 ){
            holder.button.setClickable(false);
            holder.view.setVisibility(View.GONE);
        } else if ( position == 11 ){
            holder.button.setText("DEL");
            holder.button.setTextSize(16f);
            holder.view.setVisibility(View.GONE);
        } else {
            holder.button.setText(values.get(position));
        }
    }

    @Override public long getItemId(int position) {
        if ( position == 9 || position == 11 ){
            return super.getItemId(position);
        }
        String value = values.get(position);
        return Long.valueOf(value);
    }

    @Override public int getItemCount() {
        return values.size();
    }

    public static interface OnButtonClickedListener{
        public void onNumberButtonClicked(int position);
    }

    public void onButtonClicked(int position){
        if ( listener != null ){
            listener.onNumberButtonClicked(position);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnTouchListener {

        public Button button;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.number_button);
            view = itemView.findViewById(R.id.horizontal_rule);
            button.setOnClickListener(this);
            button.setOnTouchListener(this);
        }

        @Override public void onClick(View v) {
            int position = getAdapterPosition();
            onButtonClicked(position);
        }

        @Override public boolean onTouch(View v, MotionEvent event) {
            if ( event.getAction() == MotionEvent.ACTION_DOWN){
                //button.setTextColor(context.getResources().getColor(R.color.white));
                //view.setBackgroundColor(context.getResources().getColor(R.color.white));
            }
            if ( event.getAction() == MotionEvent.ACTION_UP){
                //button.setTextColor(context.getResources().getColor(R.color.primary_text));
                //view.setBackgroundColor(context.getResources().getColor(R.color.black));
            }
            return false;
        }
    }
}
