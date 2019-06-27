package com.digitcreativestudio.wisatajogja;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    public static final String NAME = "nama_pariwisata";
    public static final String ADDRESS = "alamat_pariwisata";
    public static final String DETAIL = "detail_pariwisata";
    public static final String IMAGE = "gambar_pariwisata";

    ImageView image;
    TextView name, address, detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Detail Wisata");

        image = findViewById(R.id.image_detail);
        name = findViewById(R.id.name_detail);
        address = findViewById(R.id.address_detail);
        detail = findViewById(R.id.detail_detail);

        if(SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()){
            Bundle bundle = getIntent().getExtras();
            if(bundle != null){
                name.setText(bundle.getString(NAME));
                Glide.with(getApplicationContext()).load(bundle.getString(IMAGE)).into(image);
                address.setText(bundle.getString(ADDRESS));
                detail.setText(bundle.getString(DETAIL));
            }
        } else {
            Intent intent = new Intent(this, SplashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
}
