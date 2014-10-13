package com.example.renelopez.exampleapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ListGridActivity extends Activity {
    private ListView list;
    private GridView grid;
    private Button btnUpdate;
    private ListGridAdapter gridAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_grid);
        initUI();
        setAdapter();
        setClick();
    }

    private ArrayList<Model> getDummyData(){
        ArrayList<Model> data=new ArrayList<Model>();
        Model model=new Model();
        model.setAddress("Direccion Chida");
        model.setPhone("9289832");
        model.setUsername("Username chido");
        data.add(model);
        data.add(model);
        data.add(model);
        data.add(model);
        return data;
    }

    private ArrayList<Model> getDummyDataUpdate(){
        ArrayList<Model> data=new ArrayList<Model>();
        Model model=new Model();
        model.setAddress("Actualizado Address");
        model.setPhone("928sdsd9832");
        model.setUsername("Actualizado Username");
        data.add(model);
        data.add(model);
        data.add(model);
        data.add(model);
        return data;
    }

    private void setClick() {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(ListGridActivity.this,"Item click: "+ position,Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                gridAdapter.updateData(getDummyDataUpdate());
                gridAdapter.notifyDataSetChanged();
            }
        });


    }


    private void setAdapter() {
        gridAdapter=new ListGridAdapter(this,getDummyData());
        list.setAdapter(gridAdapter);
    }

    private void initUI() {
        list=(ListView)findViewById(R.id.list);
        btnUpdate=(Button)findViewById(R.id.button_update);
    }
}
class ListGridAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<Model> data;
    public ListGridAdapter(Context context,ArrayList<Model> data){
        inflater=LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public void updateData(ArrayList<Model> data){
        this.data=data;
    }

    private class ViewHolder{
        private TextView txtUsername;
        private TextView txtAddress;
        private TextView txtPhone;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null) {
            view = inflater.inflate(R.layout.list_row, null);
            holder=new ViewHolder();
            initUIAdapter(holder,view);
            view.setTag(holder);
            Log.d("tag","Creando layout: " + position );
        }else{
            holder=(ViewHolder) view.getTag();
            Log.d("tag","Reciclando layout");
        }
            setText(holder,position);
        return view;
    }

    private void setText(ViewHolder holder,int position){
        Model model=(Model)getItem(position);
        holder.txtUsername.setText(model.getUsername());
        holder.txtPhone.setText(model.getPhone());
        holder.txtAddress.setText(model.getAddress());
    }
    private void initUIAdapter(ViewHolder holder,View view){
        holder.txtUsername=(TextView)view.findViewById(R.id.text_username);
        holder.txtAddress=(TextView)view.findViewById(R.id.text_address);
        holder.txtPhone=(TextView)view.findViewById(R.id.text_phone);
    }
}
