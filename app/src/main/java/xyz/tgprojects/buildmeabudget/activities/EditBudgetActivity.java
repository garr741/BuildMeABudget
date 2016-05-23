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
import android.view.View;
import android.widget.TextView;
import org.w3c.dom.Text;
import xyz.tgprojects.buildmeabudget.BMABApplication;
import xyz.tgprojects.buildmeabudget.R;
import xyz.tgprojects.buildmeabudget.adapters.EditBudgetAdapter;
import xyz.tgprojects.buildmeabudget.models.Budget;

public class EditBudgetActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    EditBudgetAdapter adapter;
    Budget budget;
    BMABApplication app;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_budget);

        app = (BMABApplication) getApplication();
        budget = app.getBudget();

        toolbar = (Toolbar) findViewById(R.id.edit_budget_toolbar);

         setUpToolbar();

        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.edit_budget_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
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
            app.saveBudget(budget);
            save();
        }
        if ( id == R.id.reset_button ){
            resetBudget();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpToolbar(){
        TextView textView = (TextView) findViewById(R.id.toolbar_title);
        textView.setVisibility(View.GONE);
        String title = Integer.toString(budget.getAllocatedPercentage()) + "% allocated";
        toolbar.setTitle(title);
    }

    public void save(){
        Intent intent = new Intent(this, BudgetActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void resetBudget(){
        app.saveBudget(budget.reset());
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
