package xyz.tgprojects.buildmeabudget;

import android.app.Application;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import xyz.tgprojects.buildmeabudget.models.Budget;

public class BMABApplication extends Application {

    private Budget budget;
    SharedPreferences mPrefs;

    @Override public void onCreate() {
        super.onCreate();
        budget = Budget.getInstance();
        mPrefs = this.getSharedPreferences("SharedPrefs", MODE_PRIVATE);
        saveBudget(budget);
    }

    public Budget getBudget(){
        Gson gson = new Gson();
        String json = mPrefs.getString("budget", "");
        return gson.fromJson(json, Budget.class);
    }

    public void saveBudget(Budget budget){
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(budget);
        prefsEditor.putString("budget", json);
        prefsEditor.commit();
    }
}
