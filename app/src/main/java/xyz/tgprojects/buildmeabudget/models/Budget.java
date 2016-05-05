package xyz.tgprojects.buildmeabudget.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Budget implements Serializable {
    private long grossIncome;
    private long income;
    private List<Category> categoryList;

    private static Budget instance;

    public Budget(){}

    public static Budget getInstance(){
        if ( instance == null ){
            return new Budget();
        }
        return instance;
    }

    public long getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(long grossIncome) {
        this.grossIncome = grossIncome;
        setAnnualIncome(grossIncome);
    }

    public long getAnnualIncome() { return income; }

    public void setAnnualIncome(long grossIncome){
        if (grossIncome < 9225) {
            this.income =  grossIncome - (long)(grossIncome * .10);
        } else if (grossIncome < 37450) {
            this.income = grossIncome - (long)(grossIncome * .15);
        } else if (grossIncome < 90750) {
            this.income =  grossIncome - (long)(grossIncome * .25);
        } else if (grossIncome < 189300) {
            this.income = grossIncome - (long)(grossIncome * .28);
        } else if (grossIncome < 411350) {
            this.income = grossIncome - (long)(grossIncome * .33);
        } else {
            this.income = grossIncome - (long)(grossIncome * .35);
        }
    }

    private List<Category> createCategoryList() {
        categoryList = new ArrayList<>();
        categoryList.add(new Category("Housing", "Mortgages, Taxes, Rent, Insurance", 30, income));
        categoryList.add(new Category("Utilities", "Phone, Electricity, Gas, Cable, Internet, Water", 15, income));
        categoryList.add(new Category("Transportation", "Bus, Uber, Insurance, Gas, Maintenance, Parking", 10, income));
        categoryList.add(new Category("Food", "Groceries, Personal Care", 10, income));
        categoryList.add(new Category("Personal and Entertainment", "Entertainment, Recreation, Alcohol, Eating Out, gaming, Hair Cuts, Hobbies, Amazon, Netflix, Shopping", 10, income));
        categoryList.add(new Category("Health", "Health Care Premiums, Deductibles, Insurance, Medications", 5, income));
        categoryList.add(new Category("Savings", "Savings, Emergency Funds, Retirement, Investments", 10, income));
        categoryList.add(new Category("Debts", "Loans, Credit Cards, Medical Bills", 10, income));
        return categoryList;
    }

    public int getAllocatedPercentage(){
        if ( categoryList == null ){
            getCategoryList();
        }
        int allocatedPercentage = 0;
        for (Category category: getCategoryList()){
            allocatedPercentage += category.getPercentage();
        }
        return allocatedPercentage;
    }

    public List<Category> getCategoryList(){
        if ( categoryList == null ){
            return createCategoryList();
        }
        return categoryList;
    }

    public Budget updateCategories(){
        int i=0;
        for (Category category : categoryList){
            int percent = category.getPercentage();
            String description = category.getDescription();
            String name = category.getName();
            categoryList.set(i, new Category(name, description, percent, income));
            i++;
        }
        return this;
    }

    public long getMonthlyIncome() {
        return getAnnualIncome() / 52;
    }

    public long getBiWeeklyincome() {
        return getAnnualIncome() / 26;
    }

    public long getWeeklyIncome() {
        return getBiWeeklyincome() / 2;
    }

    public Budget reset(){
        instance = null;
        return getInstance();
    }
}
