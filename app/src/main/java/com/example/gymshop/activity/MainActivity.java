package com.example.gymshop.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.gymshop.R;
import com.example.gymshop.adapter.ProductAdapter;
import com.example.gymshop.adapter.ProductTypeAdapter;
import com.example.gymshop.model.Product;
import com.example.gymshop.model.ProductType;
import com.example.gymshop.util.CheckConnection;
import com.example.gymshop.util.Server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewMain;
    NavigationView navigationView;
    ListView listViewMain;
    DrawerLayout drawerLayout;
    ArrayList<ProductType> productTypeArrayList;
    ArrayList<Product> productArrayList;
    ProductTypeAdapter productTypeAdapter;
    ProductAdapter productAdapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mapping();
        setSupportActionBar(toolbar);
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            ActionBar();
            //Set khung quảng cáo
            ActionViewFlipper();
            GetDataType();
            GetDataProduct();
        }else{
            CheckConnection.ShowToast(getApplicationContext(),"Check Connection Again!");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        return true;
    }

    private void ActionBar(){
        //Băt sự kiện chuyển sang cửa sổ Menu
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void ActionViewFlipper(){
        ArrayList<String> advertisement=new ArrayList<>();
        advertisement.add("https://wheyshop.vn/wp-content/uploads/2020/04/Whey-Gold-Standard-5lbs-23kg-1.jpg");
        advertisement.add("https://bizweb.dktcdn.net/thumb/1024x1024/100/011/344/products/scitec-nutrition-100-percentage-whey-protein-isolate-4000-gams-160-servings.jpg?v=1587755179340");
        advertisement.add("https://suppcare.vn/pub/media/catalog/product/cache/cf3f2243ef4940fd5c66f2ff035145ac/s/c/scitec_100_whey_protein_professional_2_35_kg_kiwi_banana__2_2.jpg");
        //Đổ đường dẫn hình ảnh thông qua ImageView vào View Flipper
        for(int i=0;i<advertisement.size();i++){
            ImageView imageView=new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(advertisement.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        //Set thời gian tự chạy của viewFlipper
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        //Khởi tạo hiệu ứng cho viewFlipper
        Animation animation_slide_in= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);

    }
    private  void GetDataType(){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, Server.linkType, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                    if(response!=null) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                productTypeArrayList.add(new ProductType(
                                        object.getInt("id"),
                                        object.getString("name"),
                                        object.getString("image")
                                ));
                                productTypeAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                    }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    private void GetDataProduct(){
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, Server.linkProduct, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response!=null){
                            for(int i=0;i<response.length();i++){
                                try {
                                    JSONObject object=response.getJSONObject(i);
                                    float weight= BigDecimal.valueOf(object.getDouble("weight")).floatValue();
                                    productArrayList.add(new Product(
                                            object.getInt("id"),
                                            object.getInt("price"),
                                            object.getInt("typeId"),
                                            object.getString("name"),
                                            object.getString("image"),
                                            object.getString("color"),
                                            object.getString("taste"),
                                            object.getString("desc"),
                                            weight
                                    ));
                                    Log.d("AAA",object.getInt("id")+" "+object.getString("name")+" "+object.getInt("price"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                productAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonArrayRequest);
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void Mapping() {
        toolbar=(Toolbar) findViewById(R.id.toolbarMain);
        viewFlipper=(ViewFlipper) findViewById(R.id.viewfilpper);
        recyclerViewMain=(RecyclerView) findViewById(R.id.recyclerView);
        navigationView=(NavigationView) findViewById(R.id.navigationView);
        listViewMain=(ListView) findViewById(R.id.listViewMain);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawerLayout);
        productTypeArrayList=new ArrayList<>();
        productArrayList=new ArrayList<>();
        productTypeAdapter=new ProductTypeAdapter(this,R.layout.line_product_type,productTypeArrayList);
        productAdapter=new ProductAdapter(this,R.layout.line_product,productArrayList);
        listViewMain.setAdapter(productTypeAdapter);
        recyclerViewMain.setHasFixedSize(true);
        recyclerViewMain.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewMain.setAdapter(productAdapter);

    }
}
