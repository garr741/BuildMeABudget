package xyz.tgprojects.buildmeabudget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import xyz.tgprojects.buildmeabudget.activities.BudgetActivity;

public class MainActivity extends AppCompatActivity {

    public static final String ANNUAL_INCOME = "Annual Income";

    Toolbar toolbar;
    EditText incomeEditText;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        incomeEditText = (EditText) findViewById(R.id.main_activity_income_edittext);

        setSupportActionBar(toolbar);

        toolbar.setTitle(R.string.app_name);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if ( id == R.id.build_button ){
            int income = getInputIncome();
            if ( income != 0 ){
                goToBudgetActivity(income);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void goToBudgetActivity(int income){
        Intent intent = new Intent(this, BudgetActivity.class);
        intent.putExtra(ANNUAL_INCOME, income);
        startActivity(intent);
    }

    private int getInputIncome(){
        String income = incomeEditText.getText().toString();
        if ( !income.isEmpty() ){
            return Integer.parseInt(income);
        }
        return 0;
    }
}
