package xyz.tgprojects.buildmeabudget.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import xyz.tgprojects.buildmeabudget.R;
import xyz.tgprojects.buildmeabudget.models.Budget;
import xyz.tgprojects.buildmeabudget.models.Category;
import xyz.tgprojects.buildmeabudget.utils.FormatUtils;

public class BudgetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Category> categories;
    Budget budget;

    public BudgetAdapter(Context context, List<Category> categories, Budget budget) {
        this.context = context;
        this.categories = categories;
        this.budget = budget;
    }

    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if ( viewType == 1 ){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.budget_chart, parent, false);
            return new BudgetViewHolder(v);
        }
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card, parent, false);
        return new CardViewHolder(v);
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Category category = categories.get(position);
        if ( holder instanceof CardViewHolder ) {
            buildCardView((CardViewHolder) holder, category);
        } else {
            buildChartView((BudgetViewHolder) holder);
        }
    }

    @Override public int getItemCount() {
        return categories.size();
    }

    @Override public int getItemViewType(int position) {
        if ( position == 0 ){
            return 1;
        }
        return super.getItemViewType(position);
    }

    public void buildCardView(CardViewHolder holder, Category category){
        holder.title.setText(category.getName());
        holder.description.setText(category.getDescription());
        holder.yearly.setText(FormatUtils.dollarFormatter(category.getYearly()));
        holder.monthly.setText(FormatUtils.dollarFormatter(category.getMonthly()));
        holder.biWeekly.setText(FormatUtils.dollarFormatter(category.getBiWeekly()));
        holder.weekly.setText(FormatUtils.dollarFormatter(category.getWeekly()));
        holder.percentage.setText(FormatUtils.percentFormatter(category.getPercentage()));
    }

    public void buildChartView(BudgetViewHolder holder){
        buildChartData(holder.pieChart);
    }

    private void buildChartData(PieChart pieChart){
        List<Entry> entries = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        int i=0;
        for (Category category: budget.getCategoryList()) {
            entries.add(new Entry(category.getPercentage(), i++));
            titles.add(category.getName());
        }

        ArrayList<Integer> colors = new ArrayList<Integer>();

        int[] MATERIAL_COLORS = {
                Color.rgb(211,47,47), Color.rgb(194,24,91), Color.rgb(123,31,162), Color.rgb(81,45,168),
                Color.rgb(48,63,159), Color.rgb(2,136,209), Color.rgb(0,151,167),
                Color.rgb(0,121,107), Color.rgb(56,142,60), Color.rgb(104,159,56), Color.rgb(175,180,43),
                Color.rgb(251,192,45), Color.rgb(255,160,0), Color.rgb(245,124,0), Color.rgb(230,74,25)
        };

        for (int c : MATERIAL_COLORS){
            colors.add(c);
            Collections.shuffle(colors);
        }

        colors.add(ColorTemplate.getHoloBlue());

        PieDataSet pieDataSet = new PieDataSet(entries, "Budget");
        pieDataSet.setColors(colors);
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);
        PieData pieData = new PieData(titles, pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(11f);
        pieData.setValueTextColor(Color.WHITE);

        Legend legend = pieChart.getLegend();

        legend.setWordWrapEnabled(true);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);

        pieChart.highlightValues(null);
        pieChart.setDescription("");
        pieChart.setCenterText("");
        pieChart.setDrawSliceText(false);
        pieChart.setDrawSlicesUnderHole(false);
        pieChart.setUsePercentValues(false);
        pieChart.setHoleColor(context.getResources().getColor(R.color.primary_dark));
        pieChart.setData(pieData);
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView description;
        TextView yearly;
        TextView monthly;
        TextView biWeekly;
        TextView weekly;
        TextView percentage;

        public CardViewHolder(View itemView) {
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

    public class BudgetViewHolder extends RecyclerView.ViewHolder {

        PieChart pieChart;

        public BudgetViewHolder(View itemView) {
            super(itemView);
            pieChart = (PieChart) itemView.findViewById(R.id.budget_pie_chart);
        }
    }
}
