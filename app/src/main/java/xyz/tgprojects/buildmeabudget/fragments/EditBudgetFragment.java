package xyz.tgprojects.buildmeabudget.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.tgprojects.buildmeabudget.R;
import xyz.tgprojects.buildmeabudget.adapters.EditBudgetAdapter;

public class EditBudgetFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    EditBudgetAdapter editBudgetAdapter;

    public static Fragment newInstance(){
        EditBudgetFragment fragment = new EditBudgetFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public EditBudgetFragment() {}

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_budget, container, false);

        //recyclerView = (RecyclerView) v.findViewById(R.id.edit_budget_recycler_view);
        //
        //layoutManager = new LinearLayoutManager(getContext());
        //
        //recyclerView.setLayoutManager(layoutManager);
        //
        //editBudgetAdapter = new EditBudgetAdapter(getContext(), new LinkedList<Category>());
        //
        //recyclerView.setAdapter(editBudgetAdapter);

        return v;
    }
}
