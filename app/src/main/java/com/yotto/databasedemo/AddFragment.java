package com.yotto.databasedemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.yotto.databasedemo.database.DatabaseOperation;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {

    private EditText editName;
    private EditText editPhone;
    private EditText editAge;
    private AppCompatButton buttonSave;

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add, container, false);
        initialize(v);
        return v;
    }

    private void initialize(View v) {
        editName = (EditText)v.findViewById(R.id.edit_name);
        editPhone = (EditText)v.findViewById(R.id.edit_phone);
        editAge = (EditText)v.findViewById(R.id.edit_age);
        buttonSave = (AppCompatButton)v.findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Person person = new Person();
                person.setName(editName.getText().toString());
                person.setPhone(editPhone.getText().toString());
                person.setAge(Integer.parseInt(editAge.getText().toString()));
                if(new DatabaseOperation(getContext()).addPerson(person) > 0){
                    Toast.makeText(getContext(),"Inserted successfully",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
