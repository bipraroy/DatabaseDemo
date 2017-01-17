package com.yotto.databasedemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bipra on 17/1/17.
 */

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonViewHolder> {

    private List<Person> personList;
    public PersonListAdapter(List<Person> personList){
        this.personList = personList;
    }
    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_person,parent,false);
        return new PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
     Person p = personList.get(position);
        holder.textName.setText(p.getName());
        holder.textPhone.setText(p.getPhone());
        holder.textAge.setText(String.valueOf(p.getAge()));
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder{

        TextView textName;
        TextView textPhone;
        TextView textAge;
        public PersonViewHolder(View itemView) {

            super(itemView);
            textName= (TextView)itemView.findViewById(R.id.text_name);
            textPhone = (TextView)itemView.findViewById(R.id.text_phone);
            textAge = (TextView)itemView.findViewById(R.id.text_age);

        }
    }
}
