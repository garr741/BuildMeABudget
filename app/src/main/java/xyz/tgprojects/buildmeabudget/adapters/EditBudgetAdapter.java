package xyz.tgprojects.buildmeabudget.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import java.util.List;
import xyz.tgprojects.buildmeabudget.R;
import xyz.tgprojects.buildmeabudget.models.Category;

/**
 * Created by tylor.garrett on 5/3/16.
 */
public class EditBudgetAdapter extends RecyclerView.Adapter<EditBudgetAdapter.ViewHolder> {

    Context context;
    List<Category> categoryList;

    public EditBudgetAdapter(Context context, List<Category> categoryList) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @Override public EditBudgetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_budget_card, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(EditBudgetAdapter.ViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.title.setText(category.getName());
        holder.description.setText(category.getDescription());
        holder.percentage.setText(Long.toString(category.getPercentage()));
    }

    @Override public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        EditText title;
        EditText description;
        EditText percentage;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (EditText) itemView.findViewById(R.id.edit_budget_card_title);
            description = (EditText) itemView.findViewById(R.id.edit_budget_card_description);
            percentage = (EditText) itemView.findViewById(R.id.edit_budget_card_percentage);
        }
    }
}
