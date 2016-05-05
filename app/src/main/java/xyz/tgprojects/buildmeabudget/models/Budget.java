package xyz.tgprojects.buildmeabudget.models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Budget implements Serializable {
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

    public long getAnnualIncome() { return income; }

    public void setAnnualIncome(long annualIncome) {
        this.income = annualIncome;
    }

    private List<Category> createCategoryList() {
        categoryList = new LinkedList<>();
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

    public long getMonthlyIncome() {
        return getAnnualIncome() / 52;
    }

    public long getBiWeeklyincome() {
        return getAnnualIncome() / 26;
    }

    public long getWeeklyIncome() {
        return getBiWeeklyincome() / 2;
    }
}
