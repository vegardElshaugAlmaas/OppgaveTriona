package com.example.mastermindtriona;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MAIN_ACTIVITY";
    private ArrayList<ListViewItem> items;
    private CustomAdapter customAdapter;
    private String userName;
    private RequestQueue requestQueue;
    private Button gues;
    private EditText editText;
    private EditText name;
    private ListView guesesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent name = getIntent();
        userName = name.getStringExtra("user");
        requestQueue = Volley.newRequestQueue(this);
        //sendUser();

        gues = findViewById(R.id.guesButton);
        editText = findViewById(R.id.playerGueses);
        guesesList = findViewById(R.id.simpleListView);

        items = new ArrayList<>();

        customAdapter = new CustomAdapter(getApplicationContext(), items);
        guesesList.setAdapter(customAdapter);

        gues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().length() == 4) {
                    items.add(new ListViewItem(editText.getText().toString(), R.drawable.test_icon));
                    customAdapter.notifyDataSetChanged();
                }else{
                    editText.getText().clear();
                }
            }
        });
    }

    private void sendUser(){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("user", "vegard");
            jsonObject.put("password", "12345");
        }catch(Exception e){
            Log.e(TAG, e.getMessage());
        }

        String url =getString(R.string.end_point);
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.getMessage() != null){
                    Log.e(TAG, error.getMessage());
                }
                Log.e(TAG, "Error logging in");
            }
        }
        );
        requestQueue.add(postRequest);
    }

    /*
    private int sendGues(){
        return 0;
    }

    private void generateAnswer(){
        Random rand = new Random();
        for(int i=0; i<4;i++){
            switch(rand.nextInt(6)){
                case 0: answer[i] = 'A';
                case 1: answer[i] = 'B';
                case 2: answer[i] = 'C';
                case 3: answer[i] = 'D';
                case 4: answer[i] = 'E';
                case 5: answer[i] = 'F';
                default: answer[i] = 'A';
            }
        }
    }
     */
}
