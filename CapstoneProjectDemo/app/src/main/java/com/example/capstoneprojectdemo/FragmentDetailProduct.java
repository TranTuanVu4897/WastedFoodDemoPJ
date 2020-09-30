package com.example.capstoneprojectdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class FragmentDetailProduct extends Fragment {
    private Production production;
    private ImageView ivProduct;
    private TextView tvName;
    private TextView tvPrice;
    private TextView tvQuantity;
    private TextView tvPurchase;
    private Button btnBuy;
    private Button btnBack;
    private Button btnAdd;
    private Button btnSub;
    private int purchase;
    private String urlUpdate = "http://192.168.3.4/capstonePJ/updateProduct.php";

    public FragmentDetailProduct(Production production) {
        this.production = production;
        purchase = 0;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_product, container, false);
        tvName = view.findViewById(R.id.tvName);
        tvPrice = view.findViewById(R.id.tvPrice);
        tvQuantity = view.findViewById(R.id.tvQuantity);
        tvPurchase = view.findViewById(R.id.tvPurchase);
        ivProduct = view.findViewById(R.id.ivProductFDP);
        btnBuy = view.findViewById(R.id.btnBuy);
        btnBack = view.findViewById(R.id.btnBack);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnSub = view.findViewById(R.id.btnSub);

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyProduction(urlUpdate);
                backToList();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToList();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (purchase < production.getSelled_quantity()) {
                    purchase++;
                }
                setPurchaseText(tvPurchase, purchase);
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (purchase > 0) {
                    purchase--;
                }
                setPurchaseText(tvPurchase, purchase);

            }
        });

        tvName.setText("Product: " + production.getId());
        tvPrice.setText("Price: $" + production.getPrice());
        tvQuantity.setText("Left: " + production.getSelled_quantity() + "/" + production.getQuantity());


        return view;
    }

    private void setPurchaseText(TextView tvPurchase, int purchase) {
        tvPurchase.setText("Buy: " + purchase);
    }

    private void buyProduction(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            Toast.makeText(getActivity().getApplicationContext(), "Đặt mua thành công", Toast.LENGTH_LONG);
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "Đặt mua thất bại", Toast.LENGTH_LONG);

                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(), "Đặt mua thất bại", Toast.LENGTH_LONG);

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idProduct", String.valueOf(production.getId()));
                params.put("purchase", String.valueOf(production.getSelled_quantity() - purchase));
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void backToList() {
        FragmentListProduct fragmentListProduct = new FragmentListProduct();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.flFragmentContaintAM, fragmentListProduct, "")
                .addToBackStack(null)
                .commit();
    }

}
