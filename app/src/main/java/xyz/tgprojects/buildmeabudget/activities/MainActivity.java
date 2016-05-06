package xyz.tgprojects.buildmeabudget.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import xyz.tgprojects.buildmeabudget.BMABApplication;
import xyz.tgprojects.buildmeabudget.R;
import xyz.tgprojects.buildmeabudget.models.Budget;

public class MainActivity extends AppCompatActivity {

    public static final String ANNUAL_INCOME = "Annual Income";

    Toolbar toolbar;
    EditText incomeEditText;
    Budget budget;
    SharedPreferences sharedPreferences;
    BMABApplication app;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (BMABApplication) getApplication();
        budget = app.getBudget();

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        incomeEditText = (EditText) findViewById(R.id.main_activity_income_edittext);
        if ( budget.getGrossIncome() != 0 ){
            incomeEditText.setText(String.valueOf(budget.getGrossIncome()));
        }

        setSupportActionBar(toolbar);

        toolbar.setTitle(R.string.app_name);
    }

    @Override protected void onResume() {
        super.onResume();
        budget = app.getBudget();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if ( id == R.id.build_button ){
            long income = getInputIncome();
            if ( income != 0 ){
                budget.setGrossIncome(income);
                app.saveBudget(budget);
                goToBudgetActivity();
            }
        }
        if ( id == R.id.reset_button ){
            resetBudget();
        }

        return super.onOptionsItemSelected(item);
    }

    private void goToBudgetActivity(){
        Intent intent = new Intent(this, BudgetActivity.class);
        startActivity(intent);
    }

    private long getInputIncome(){
        String income = incomeEditText.getText().toString();
        if ( !income.isEmpty() ){
            return Long.valueOf(income);
        } else {
            Snackbar.make(findViewById(android.R.id.content), R.string.insert_value, Snackbar.LENGTH_SHORT).show();
        }
        return 0;
    }

    public void resetBudget(){
        app.saveBudget(budget.reset());
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
