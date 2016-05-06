package xyz.tgprojects.buildmeabudget.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.autofit.et.lib.AutoFitEditText;
import xyz.tgprojects.buildmeabudget.BMABApplication;
import xyz.tgprojects.buildmeabudget.R;
import xyz.tgprojects.buildmeabudget.models.Budget;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Toolbar toolbar;
    AutoFitEditText incomeEditText;
    Budget budget;
    BMABApplication app;
    FloatingActionButton build;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (BMABApplication) getApplication();
        budget = app.getBudget();

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setUpToolbar();
        incomeEditText = (AutoFitEditText) findViewById(R.id.main_activity_income_edittext);
        build = (FloatingActionButton) findViewById(R.id.main_activity_build_button);
        setUpBuildButton();

        if ( budget.getGrossIncome() != 0 ){
            incomeEditText.setText(String.valueOf(budget.getGrossIncome()));
        }
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

        if ( id == R.id.reset_button ){
            resetBudget();
        }

        return super.onOptionsItemSelected(item);
    }

    public void setUpToolbar(){
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        TextView toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitle.setTextSize(20f);
    }

    public void setUpBuildButton(){
        build.setOnClickListener(this);
        build.setBackgroundColor(getResources().getColor(R.color.accent));

    }

    private void goToBudgetActivity(){
        Intent intent = new Intent(this, BudgetActivity.class);
        startActivity(intent);
    }

    private long getInputIncome(){
        String income = incomeEditText.getText().toString();
        if ( !income.isEmpty() ){
            return Long.valueOf(income);
        }
        return 0;
    }

    public void resetBudget(){
        app.saveBudget(budget.reset());
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_activity_build_button:
                long income = getInputIncome();
                if ( income != 0 ){
                    budget.setGrossIncome(income);
                    app.saveBudget(budget);
                    goToBudgetActivity();
                }
        }
    }
}
