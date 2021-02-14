package com.saify.saifymedtrailassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.saify.saifymedtrailassignment.view.DataFragment;

public class MainActivity extends AppCompatActivity {

    private EditText editTextSearch;
    private ImageButton imgBtnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Check if there is a saved instance (for cases of orientation change)
        if (savedInstanceState != null) {
//            Hide TextView
            ((TextView) findViewById(R.id.txtViewSearch)).setVisibility(View.GONE);

            ((FrameLayout) findViewById(R.id.imgContainer)).setVisibility(View.VISIBLE);
            return;
        }


        editTextSearch = findViewById(R.id.edtSearchImages);
        imgBtnSearch = findViewById(R.id.imgBtnSearch);


        imgBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSearch = findViewById(R.id.edtSearchImages);
                String search_query = editTextSearch.getText().toString();

                if(TextUtils.isEmpty(search_query)){
                    Toast.makeText(MainActivity.this, "Please enter query in search box",Toast.LENGTH_LONG).show();
                    return;
                }

                //Hide TextView
                ((TextView) findViewById(R.id.txtViewSearch)).setVisibility(View.GONE);

                ((FrameLayout) findViewById(R.id.imgContainer)).setVisibility(View.VISIBLE);

                //Load Fragment

                Bundle bundle = new Bundle();
                bundle.putString("search", search_query);

                DataFragment dataFragment = new DataFragment();
                dataFragment.setArguments(bundle);

                getViewModelStore().clear();

                //Load and initialize new fragment
                getSupportFragmentManager().beginTransaction().replace(R.id.imgContainer, dataFragment).commit();
            }
        });

    }
}