package xyz.tgprojects.buildmeabudget.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import xyz.tgprojects.buildmeabudget.R;
import xyz.tgprojects.buildmeabudget.adapters.EditBudgetAdapter;
import xyz.tgprojects.buildmeabudget.models.Budget;

public class EditBudgetActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    EditBudgetAdapter adapter;
    Budget budget;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_budget);

        budget = (Budget) getIntent().getSerializableExtra("budget");

        toolbar = (Toolbar) findViewById(R.id.edit_budget_toolbar);

        String title = Integer.toString(budget.getAllocatedPercentage()) + "% allocated";
        toolbar.setTitle(title);

        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.edit_budget_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new EditBudgetAdapter(this, budget.getCategoryList());
        recyclerView.setAdapter(adapter);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_budget_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if ( id == R.id.save_button ){
            save();
        }
        return super.onOptionsItemSelected(item);
    }

    public void save(){
        Intent intent = new Intent(this, BudgetActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("budget", budget);
        startActivity(intent);
    }
}
