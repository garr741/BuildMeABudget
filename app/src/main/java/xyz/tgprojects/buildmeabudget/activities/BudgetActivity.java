package xyz.tgprojects.buildmeabudget.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import java.util.LinkedList;
import java.util.List;
import xyz.tgprojects.buildmeabudget.BMABApplication;
import xyz.tgprojects.buildmeabudget.R;
import xyz.tgprojects.buildmeabudget.adapters.BudgetAdapter;
import xyz.tgprojects.buildmeabudget.models.Budget;
import xyz.tgprojects.buildmeabudget.models.Category;
import xyz.tgprojects.buildmeabudget.utils.FormatUtils;

public class BudgetActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    BudgetAdapter budgetAdapter;
    Budget budget;
    BMABApplication app;

    PieChart pieChart;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        app = (BMABApplication) getApplication();
        budget = app.getBudget();
        pieChart = (PieChart) findViewById(R.id.budget_pie_chart);
        buildChartData(pieChart);

        toolbar = (Toolbar) findViewById(R.id.budget_toolbar);
        setUpToolbar();
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        budgetAdapter = new BudgetAdapter(this, budget.getCategoryList());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(budgetAdapter);
    }

    @Override protected void onResume() {
        super.onResume();
        budget = budget.updateCategories();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.budget_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if ( id == R.id.edit_button ){
            goToEditFragment();
        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpToolbar(){
        String title = budget.getAllocatedPercentage() + "% for " + FormatUtils.dollarFormatter(budget.getAnnualIncome()) + " net income";
        toolbar.setTitle(title);
        TextView toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitle.setVisibility(View.GONE);
    }


    private void goToEditFragment() {
        Intent intent = new Intent(this, EditBudgetActivity.class);
        startActivity(intent);
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
        pieChart.setCenterText("Budget");
        pieChart.setDrawSliceText(false);
        pieChart.setDrawSlicesUnderHole(false);
        pieChart.setUsePercentValues(false);
        pieChart.setHoleColor(getResources().getColor(R.color.primary_dark));
        pieChart.setData(pieData);
    }




}
