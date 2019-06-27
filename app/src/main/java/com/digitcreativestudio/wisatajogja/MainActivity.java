package com.digitcreativestudio.wisatajogja;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String URL = "http://erporate.com/bootcamp/jsonBootcamp.php";

    private List<WisataModel> list_data;
    private WisataAdapter adapter;
    private RecyclerView rv_wisata;
    private LinearLayoutManager llm;
    private Button btn_logout;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        getSupportActionBar().hide();
        btn_logout = findViewById(R.id.btn_logout);

        list_data = new ArrayList<WisataModel>();
        adapter = new WisataAdapter(getApplicationContext(), list_data);
        rv_wisata = findViewById(R.id.list_wisata);
        llm = new GridLayoutManager(getApplicationContext(), 2);
        rv_wisata.setHasFixedSize(true);
        rv_wisata.setLayoutManager(llm);
        rv_wisata.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        requestListWisataByVolley();

        //onClick
        ItemClickSupport.addTo(rv_wisata)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Intent moveWithObjectIntent = new Intent(getApplicationContext(), DetailActivity.class);
                        moveWithObjectIntent.putExtra(DetailActivity.NAME, list_data.get(position).getName());
                        moveWithObjectIntent.putExtra(DetailActivity.IMAGE, list_data.get(position).getImage());
                        moveWithObjectIntent.putExtra(DetailActivity.DETAIL, list_data.get(position).getDetail());
                        moveWithObjectIntent.putExtra(DetailActivity.ADDRESS, list_data.get(position).getAddress());
                        startActivity(moveWithObjectIntent);
                    }
                });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Logout");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SharedPrefManager.getInstance(getApplicationContext())
                                .logout();

                        Toast.makeText(getApplicationContext(), "berhasil logout", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    // (fungsi) request & retrieve list wisata
    private void requestListWisataByVolley() {

        StringRequest srblogList = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject objHospital = jsonArray.getJSONObject(i);
                                WisataModel obj = new WisataModel();
                                obj.setName(objHospital.getString("nama_pariwisata"));
                                obj.setAddress(objHospital.getString("alamat_pariwisata"));
                                obj.setDetail(objHospital.getString("detail_pariwisata"));
                                obj.setImage(objHospital.getString("gambar_pariwisata"));
                                list_data.add(obj);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //request handler volley
        RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(srblogList);

    }
}
