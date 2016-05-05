package xyz.tgprojects.buildmeabudget.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import xyz.tgprojects.buildmeabudget.MainActivity;
import xyz.tgprojects.buildmeabudget.R;
import xyz.tgprojects.buildmeabudget.adapters.BudgetAdapter;
import xyz.tgprojects.buildmeabudget.fragments.EditBudgetFragment;
import xyz.tgprojects.buildmeabudget.models.Budget;
import xyz.tgprojects.buildmeabudget.utils.FormatUtils;

public class BudgetActivity extends AppCompatActivity {

    Toolbar toolbar;
    long annualIncome;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    BudgetAdapter budgetAdapter;
    Budget newBudget;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        Bundle bundle = getIntent().getExtras();

        annualIncome = bundle.getInt(MainActivity.ANNUAL_INCOME);
        buildBudget();

        toolbar = (Toolbar) findViewById(R.id.budget_toolbar);
        String title = newBudget.getAllocatedPercentage() + "% allocated for " + FormatUtils.dollarFormatter(annualIncome) + " income";
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        budgetAdapter = new BudgetAdapter(this, newBudget.getCategoryList());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(budgetAdapter);
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

    private void buildBudget(){
        newBudget = new Budget(annualIncome);
    }


    private void goToEditFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.budget_container, EditBudgetFragment.newInstance()).commit();
    }




}
