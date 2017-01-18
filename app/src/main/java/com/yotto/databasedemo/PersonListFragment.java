package com.yotto.databasedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.yotto.databasedemo.database.DatabaseOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bipra on 17/1/17.
 */

public class PersonListFragment extends Fragment {

    private RecyclerView recycler;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_personlist,container,false);
        intialize(v);
        setHasOptionsMenu(true);
        return v;
    }

    private void intialize(View v) {
        recycler = (RecyclerView)v.findViewById(R.id.recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        setData();
    }

    private void setData() {
        List<Person> personList = new ArrayList<>();
        personList = new DatabaseOperation(getContext()).getPerson();
        recycler.setAdapter(new PersonListAdapter(personList));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_person_frag,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_delete:
                intiateDeleteDialog();
                break;
            case R.id.action_update:
                break;
            case R.id.action_filter:
                break;
            case R.id.action_search:
                break;
        }
        return true;
    }

    private void intiateDeleteDialog() {
        final AppCompatDialog dialog = new AppCompatDialog(getContext());
        dialog.setContentView(R.layout.dialog_delete_layout);
        dialog.setTitle("Delete items");
        dialog.findViewById(R.id.button_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editName = (EditText)dialog.findViewById(R.id.edit_name);
                String personName = editName.getText().toString();
                if(new DatabaseOperation(getContext()).deletePerson(personName)) {
                    Toast.makeText(getContext(),"Deleted successfully",Toast.LENGTH_SHORT).show();
                    setData();
                }else
                    Toast.makeText(getContext(),"Deletion not done",Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
        dialog.findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


}
