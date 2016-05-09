package xyz.tgprojects.buildmeabudget.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import xyz.tgprojects.buildmeabudget.BMABApplication;
import xyz.tgprojects.buildmeabudget.R;
import xyz.tgprojects.buildmeabudget.adapters.BudgetAdapter;
import xyz.tgprojects.buildmeabudget.models.Budget;
import xyz.tgprojects.buildmeabudget.utils.FormatUtils;

public class BudgetActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    BudgetAdapter budgetAdapter;
    Budget budget;
    BMABApplication app;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        app = (BMABApplication) getApplication();
        budget = app.getBudget();

        toolbar = (Toolbar) findViewById(R.id.budget_toolbar);
        String title = budget.getAllocatedPercentage() + "% for " + FormatUtils.dollarFormatter(budget.getAnnualIncome()) + " net income";
        toolbar.setTitle("");
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


    private void goToEditFragment() {
        Intent intent = new Intent(this, EditBudgetActivity.class);
        startActivity(intent);
    }




}
