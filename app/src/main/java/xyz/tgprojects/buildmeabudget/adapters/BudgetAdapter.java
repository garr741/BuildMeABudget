package xyz.tgprojects.buildmeabudget.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import xyz.tgprojects.buildmeabudget.R;
import xyz.tgprojects.buildmeabudget.models.Category;
import xyz.tgprojects.buildmeabudget.utils.FormatUtils;

public class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.ViewHolder> {

    Context context;
    List<Category> categories;

    public BudgetAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override public BudgetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(BudgetAdapter.ViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.title.setText(category.getName());
        holder.description.setText(category.getDescription());
        holder.yearly.setText("Yearly " + FormatUtils.dollarFormatter(category.getYearly()));
        holder.monthly.setText(FormatUtils.dollarFormatter(category.getMonthly()) + " Monthly");
        holder.biWeekly.setText("Biweekly " + FormatUtils.dollarFormatter(category.getBiWeekly()));
        holder.weekly.setText(FormatUtils.dollarFormatter(category.getWeekly()) + " Weekly");
        holder.percentage.setText(FormatUtils.percentFormatter(category.getPercentage()));
    }

    @Override public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView description;
        TextView yearly;
        TextView monthly;
        TextView biWeekly;
        TextView weekly;
        TextView percentage;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.category_card_title_textview);
            description = (TextView) itemView.findViewById(R.id.catergory_card_description_textview);
            yearly = (TextView) itemView.findViewById(R.id.category_card_income_yearly);
            monthly = (TextView) itemView.findViewById(R.id.category_card_income_monthly);
            biWeekly = (TextView) itemView.findViewById(R.id.category_card_income_biweekly);
            weekly = (TextView) itemView.findViewById(R.id.category_card_income_weekly);
            percentage = (TextView) itemView.findViewById(R.id.category_card_percentage);
        }
    }
}
