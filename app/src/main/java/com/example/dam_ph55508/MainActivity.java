package com.example.dam_ph55508;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsets;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.dam_ph55508.fragment.DoanhThuFragment;
import com.example.dam_ph55508.fragment.DoiMatKhauFragment;
import com.example.dam_ph55508.fragment.QuanLyLoaiSachFragment;
import com.example.dam_ph55508.fragment.QuanLyPhieuMuonFragment;
import com.example.dam_ph55508.fragment.QuanLySachFragment;
import com.example.dam_ph55508.fragment.QuanLyThanhVienFragment;
import com.example.dam_ph55508.fragment.Top10Fragment;
import com.google.android.material.navigation.NavigationView;



public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View decoreView = getWindow().getDecorView();
        decoreView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @NonNull
            @Override
            public WindowInsets onApplyWindowInsets(@NonNull View view, @NonNull WindowInsets windowInsets) {
                int left = windowInsets.getSystemWindowInsetLeft();
                int top = windowInsets.getSystemWindowInsetTop();
                int bottom = windowInsets.getSystemWindowInsetBottom();
                int right = windowInsets.getSystemWindowInsetRight();
                view.setPadding(left, top, right, bottom);
                return windowInsets.consumeSystemWindowInsets();
            }
        });

        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);

        View headerLayout = navigationView.getHeaderView(0);
        TextView txtTem = headerLayout.findViewById(R.id.txtTen);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);

        getSupportFragmentManager().beginTransaction().replace(R.id.linerlayout, new QuanLyLoaiSachFragment()).commit();

        SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
        String loaiTk = sharedPreferences.getString("loaitaikhoan", "");
        if (!loaiTk.equals("admin")){
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.mDoanhThu).setVisible(false);
            menu.findItem(R.id.mTop10).setVisible(false);
        }

        String hoten = sharedPreferences.getString("hoten", "");
        txtTem.setText("Welcome, "+ hoten);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if(item.getItemId() == R.id.mQLS){
                    fragment = new QuanLySachFragment();
                }else if (item.getItemId() == R.id.mQLPM){
                    fragment = new QuanLyPhieuMuonFragment();
                } else if (item.getItemId() == R.id.mQLLS){
                    fragment = new QuanLyLoaiSachFragment();
                }else if (item.getItemId() == R.id.mQLTV){
                    fragment = new QuanLyThanhVienFragment();
                } else if (item.getItemId() == R.id.mTop10) {
                    fragment = new Top10Fragment();
                } else if (item.getItemId() == R.id.mDoanhThu) {
                    fragment = new DoanhThuFragment();
                } else if (item.getItemId() == R.id.mDoiMatKhau) {
                    fragment = new DoiMatKhauFragment();
                } else if (item.getItemId() == R.id.mDangXuat) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else {

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.linerlayout, fragment).commit();
                getSupportActionBar().setTitle(item.getTitle());
                drawerLayout.closeDrawer(GravityCompat.START);

                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }



}