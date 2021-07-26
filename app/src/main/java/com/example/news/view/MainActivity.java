package com.example.news.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.news.R;
import com.example.news.viewmodel.FragmentAdapter;
import com.example.news.viewmodel.FragmentAdapter2;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    NavigationView navigationView;
    LottieAnimationView animationView;

    ActionBarDrawerToggle actionBarDrawerToggle;

    FragmentManager fragmentManager;
    FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("AppSettingPref", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Boolean isNightMode = sharedPreferences.getBoolean("NightMode", false);

        if(isNightMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager2);
        navigationView = findViewById(R.id.navigationView);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        animationView = navigationView.getHeaderView(0).findViewById(R.id.userimage);
        animationView.setPadding(-320, -320, -320, -320);
        tabLayout.addTab(tabLayout.newTab().setText("Esportes").setId(1).setIcon(R.drawable.ic_baseline_sports_football_24));
        tabLayout.addTab(tabLayout.newTab().setText("Ciência").setId(2).setIcon(R.drawable.flask_outline));
        tabLayout.addTab(tabLayout.newTab().setText("Politica").setId(3).setIcon(R.drawable.account_supervisor));
        tabLayout.addTab(tabLayout.newTab().setText("Tecnologia").setId(4).setIcon(R.drawable.cellphone));
        tabLayout.addTab(tabLayout.newTab().setText("Celebridades").setId(5).setIcon(R.drawable.star_outline));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
                switch (tab.getId()){
                    case 1:
                        tab.setIcon(R.drawable.ic_baseline_sports_football_24);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.flask);
                        break;
                    case 3:
                        tab.setIcon(R.drawable.account_group);
                        break;
                    case 4:
                        tab.setIcon(R.drawable.cellphone_text);
                        break;
                    case 5:
                        tab.setIcon(R.drawable.star_minus);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getId()){
                    case 1:
                        tab.setIcon(R.drawable.football);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.flask_outline);
                        break;
                    case 3:
                        tab.setIcon(R.drawable.account_supervisor);
                        break;
                    case 4:
                        tab.setIcon(R.drawable.cellphone);
                        break;
                    case 5:
                        tab.setIcon(R.drawable.star_outline);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        fragmentManager = getSupportFragmentManager();
        fragmentAdapter = new FragmentAdapter(fragmentManager, getLifecycle());
        viewPager2.setAdapter(fragmentAdapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                animationView.playAnimation();
                drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem.OnActionExpandListener actionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                fragmentManager = getSupportFragmentManager();
                FragmentAdapter2 fragmentAdapter2 = new FragmentAdapter2(fragmentManager, getLifecycle());
                viewPager2.setAdapter(fragmentAdapter2);
                createView();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {

                tabLayout.setVisibility(TableLayout.VISIBLE);
                FragmentManager fragmentManager2 = getSupportFragmentManager();
                fragmentAdapter = new FragmentAdapter(fragmentManager2, getLifecycle());
                viewPager2.setAdapter(fragmentAdapter);
                viewPager2.setUserInputEnabled(true);
                return true;
            }
        };

        menu.findItem(R.id.search).setOnActionExpandListener(actionExpandListener);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setQueryHint("Dados da pesquisa aqui");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fragmentManager = getSupportFragmentManager();
                FragmentAdapter2 fragmentAdapter2 = new FragmentAdapter2(fragmentManager, getLifecycle());
                fragmentAdapter2.setData(newText);
                viewPager2.setAdapter(fragmentAdapter2);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
    String s;
    private void setData(String string){
        s = string;
    }
    public String getData(){
        return s;
    }
    private void createView(){
        /*LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(100, 100, 100, 100);
        LinearLayout ll = new LinearLayout(this);

        String values[] = {"Adriley, Samuel, Ribeiro", "Barbosa", "Paula", "Rodrigues","Neves"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        ListView recyclerView = new ListView(this);
        recyclerView.setAdapter(adapter);
        LinearLayout.LayoutParams recyclerViewLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        recyclerView.setLayoutParams(recyclerViewLP);

        ll.addView(recyclerView);
        linearLayout.addView(ll);
        setContentView(linearLayout, layoutParams);*/

        tabLayout.setVisibility(TabLayout.GONE);
        viewPager2.setUserInputEnabled(false);
    }

    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        switch(item.getItemId()){
            case R.id.option_navigation_view_1:
                    startActivity(new Intent(MainActivity.this, Configuration.class));
        }
        return false;
    }
}