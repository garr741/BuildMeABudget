package xyz.tgprojects.buildmeabudget.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.autofit.et.lib.AutoFitEditText;
import java.util.LinkedList;
import java.util.List;
import xyz.tgprojects.buildmeabudget.BMABApplication;
import xyz.tgprojects.buildmeabudget.R;
import xyz.tgprojects.buildmeabudget.adapters.NumberAdapter;
import xyz.tgprojects.buildmeabudget.models.Budget;
import xyz.tgprojects.buildmeabudget.utils.FormatUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NumberAdapter.OnButtonClickedListener{

    Toolbar toolbar;
    AutoFitEditText incomeEditText;
    Budget budget;
    BMABApplication app;
    FloatingActionButton build;

    RecyclerView buttonRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    NumberAdapter numberAdapter;

    Long actualIncome;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (BMABApplication) getApplication();
        budget = app.getBudget();

        actualIncome = budget.getGrossIncome();

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setUpToolbar();

        incomeEditText = (AutoFitEditText) findViewById(R.id.main_activity_income_edittext);
        setUpEditText();

        build = (FloatingActionButton) findViewById(R.id.main_activity_build_button);
        setUpBuildButton();

        buttonRecyclerView = (RecyclerView) findViewById(R.id.number_recyclerview);
        layoutManager = new GridLayoutManager(this, 3);
        buttonRecyclerView.setLayoutManager(layoutManager);


        List<String> values = getNumberList();

        numberAdapter = new NumberAdapter(this, values, this);


        buttonRecyclerView.setAdapter(numberAdapter);
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
    }

    public void setUpBuildButton(){
        build.setOnClickListener(this);
        build.setImageDrawable(getResources().getDrawable(R.drawable.dollar_sign));
    }

    public void setUpEditText(){
        incomeEditText.setText(FormatUtils.dollarFormatter(budget.getGrossIncome()));
        incomeEditText.setSingleLine(true);
    }

    public List<String> getNumberList(){
        List<String> results = new LinkedList<>();
        for ( int i=1; i<10; i++){
            String a = Integer.toString(i);
            results.add(a);
        }
        results.add("");
        results.add("0");
        results.add("");
        return results;
    }

    private void goToBudgetActivity(){
        Intent intent = new Intent(this, BudgetActivity.class);
        startActivity(intent);
    }

    private long getInputIncome(){
        String income = FormatUtils.stripClean(incomeEditText.getText().toString());
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

    @Override public void onNumberButtonClicked(int position) {
        String newCurrent;
        String current = FormatUtils.stripClean(incomeEditText.getText().toString());
        if ( position == 11 ){
            newCurrent = FormatUtils.backspace(current);
        } else if ( current.length() >= 6 ){
            return;
        } else {
            Long id = numberAdapter.getItemId(position);
            String value = Long.toString(id);
            newCurrent = FormatUtils.textAppend(current, value);
        }
        if ( !newCurrent.isEmpty() ) {
            long newValue = Long.valueOf(newCurrent);
            String latestValue = FormatUtils.dollarFormatter(newValue);
            incomeEditText.setText(latestValue);
        } else {
            incomeEditText.setText(getResources().getString(R.string.zero_dollars));
        }
    }
}
