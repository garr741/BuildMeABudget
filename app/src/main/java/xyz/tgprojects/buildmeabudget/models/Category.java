package xyz.tgprojects.buildmeabudget.models;

import java.io.Serializable;

/**
 * Created by tylor.garrett on 5/3/16.
 */
public class Category implements Serializable{
    private String name;
    private String description;
    private long percentage;
    private long income;

    public Category(String name, String description, int percentage, long income) {
        this.name = name;
        this.description = description;
        this.percentage = percentage;
        this.income = income;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPercentage() {
        return percentage;
    }

    public void setPercentage(long percentage) {
        this.percentage = percentage;
    }

    public long getIncome() { return income; }

    public void setIncome(long income) { this.income = income; }

    public long getYearly() {
        return (income*percentage)/100;
    }

    public long getMonthly() {
        return getYearly()/12;
    }

    public long getBiWeekly() {
        return getYearly()/26;
    }

    public long getWeekly() {
        return getBiWeekly()/2;
    }
}
