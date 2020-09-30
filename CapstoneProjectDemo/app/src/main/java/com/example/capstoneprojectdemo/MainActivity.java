package com.example.capstoneprojectdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    private String urlGetData = "http://192.168.3.4/capstonePJ/getdata.php";//current pc ip
    ListView lvProduction;
    ArrayList<Production> arrProduction;
    ProductionAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        lvProduction = findViewById(R.id.lvTest);
//        arrProduction = new ArrayList<>();
//        adapter = new ProductionAdapter(this,R.layout.display_product,arrProduction);
//        lvProduction.setAdapter(adapter);
//        getData(urlGetData);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FragmentListProduct sample = new FragmentListProduct();
        fragmentTransaction.add(R.id.flFragmentContaintAM, sample);
        fragmentTransaction.commit();

    }

    private void getData(String urlGetData) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlGetData, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                arrProduction.add(new Production(object.getInt("Id"),
                                        object.getInt("SellerId"),
                                        object.getDouble("Price"),
                                        object.getInt("Quantity"),
                                        object.getInt("Selled_quantity"),
                                        object.getString("SaleDate")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT);

                    }
                });
        requestQueue.add(jsonArrayRequest);
    }
}