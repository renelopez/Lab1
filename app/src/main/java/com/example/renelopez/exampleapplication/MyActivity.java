package com.example.renelopez.exampleapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MyActivity extends Activity implements BlankFragment.OnFragmentInteractionListener{

    TextView txtTop;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        initUI();
    }

    private void initUI() {
        Button btnAdd=(Button)findViewById(R.id.button_add);
        txtTop=(TextView)findViewById(R.id.text_top);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MyActivity.this,ListGridActivity.class);
                startActivity(intent);
                //FragmentManager manager = getFragmentManager();
                //manager.beginTransaction().replace(R.id.container,BlankFragment.newInstance("Rene Lopez" + i++)).commit();
            }
        });
    }

    @Override
    public void onFragmentInteraction(String string) {
        txtTop.setText(string);
    }
}
