package com.example.capstoneprojectdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

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

public class FragmentListProduct extends ListFragment {

    private String urlGetData = "http://192.168.3.4/capstonePJ/getdata.php";//current pc ip
//    private String urlGetData = "http://10.22.178.239//capstonePJ/getdata.php";//university ip
    ListView lvProduction;
    ArrayList<Production> arrProduction;
    ProductionAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_production,container,false);

        lvProduction = view.findViewById(android.R.id.list);
        arrProduction = new ArrayList<>();
        adapter = new ProductionAdapter(getActivity().getApplicationContext(),R.layout.display_product,arrProduction);
        lvProduction.setAdapter(adapter);
        getData(urlGetData);


        return view;
    }



    private void getData(String urlGetData) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
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
//                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT);

                    }
                });
        requestQueue.add(jsonArrayRequest);
    }


    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        FragmentDetailProduct detailProduct = new FragmentDetailProduct(arrProduction.get(position));
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.flFragmentContaintAM,detailProduct,"")
                .addToBackStack(null)
                .commit();
    }
}
