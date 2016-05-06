package xyz.tgprojects.buildmeabudget.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.vi.swipenumberpicker.OnValueChangeListener;
import com.vi.swipenumberpicker.SwipeNumberPicker;
import java.util.List;
import xyz.tgprojects.buildmeabudget.R;
import xyz.tgprojects.buildmeabudget.activities.BudgetActivity;
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
        final Category category = categoryList.get(position);
        holder.title.setText(category.getName());
        holder.description.setText(category.getDescription());
        holder.percentage.setValue((int) category.getPercentage(), true);
    }

    @Override public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements TextWatcher, OnValueChangeListener{


        EditText title;
        EditText description;
        SwipeNumberPicker percentage;


        public ViewHolder(View itemView) {
            super(itemView);
            title = (EditText) itemView.findViewById(R.id.edit_budget_card_title);
            title.addTextChangedListener(this);
            description = (EditText) itemView.findViewById(R.id.edit_budget_card_description);
            description.addTextChangedListener(this);
            percentage = (SwipeNumberPicker) itemView.findViewById(R.id.number_picker);
            percentage.setOnValueChangeListener(this);
        }

        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override public void afterTextChanged(Editable s) {
            int position = getAdapterPosition();
            Category category = categoryList.get(position);
            if ( s == title.getEditableText()){
                category.setName(s.toString());
            } else if (s == description.getEditableText()){
                category.setDescription(s.toString());
            }
        }

        @Override public boolean onValueChange(SwipeNumberPicker view, int oldValue, int newValue) {
            Category category = categoryList.get(getAdapterPosition());
            category.setPercentage(newValue);
            return true;
        }
    }
}
