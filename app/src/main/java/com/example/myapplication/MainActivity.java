package com.example.myapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.activity_common.ActivityGuide;
import com.example.myapplication.activity_common.ActivitySettings;
import com.example.myapplication.activity_common.ActivityUpgrade;
import com.example.myapplication.fragment.FragmentDefault;
import com.example.myapplication.fragment.FragmentTrash;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,Toolbar.OnMenuItemClickListener{

    private FragmentDefault fragmentDefault = null;
    private FragmentTrash fragmentTrash = null;
    private DrawerLayout drawer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar_main);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.id_fab_main);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,ActivityAddTask.class);
                Bundle b =new Bundle();
                b.putInt("id",-1);
                i.putExtras(b);
                startActivity(i);
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentDefault = new FragmentDefault();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.replace(R.id.id_fragment_container,fragmentDefault);
        tx.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        onNavigationItemSelectedExecute(id);

        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_create:
                Intent i = new Intent(MainActivity.this,ActivityAddTask.class);
                Bundle b =new Bundle();
                b.putInt("id",-1);
                i.putExtras(b);
                startActivity(i);
                break;
            case R.id.toolbar_edit:
                break;
            case R.id.toolbar_sort_order:
                Toast.makeText(MainActivity.this, "Sync", Toast.LENGTH_SHORT).show();
                break;
            case R.id.toolbar_setting:
                Toast.makeText(MainActivity.this, "Sync", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    //侧边栏选项处理
    private void onNavigationItemSelectedExecute(int id){
        Intent intent = null;
        switch (id){
            case R.id.nav_all_task:
                FragmentManager fm = getFragmentManager();
                FragmentTransaction tx = fm.beginTransaction();
                tx.replace(R.id.id_fragment_container,fragmentDefault);
                tx.commit();
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_upgrade:
                intent = new Intent(MainActivity.this , ActivityUpgrade.class);
                startActivity(intent);
                break;
            case R.id.nav_guide:
                intent = new Intent(MainActivity.this , ActivityGuide.class);
                startActivity(intent);
                break;
            case R.id.nav_settings:
                intent = new Intent(MainActivity.this , ActivitySettings.class);
                startActivity(intent);
                break;
            case R.id.nav_upload:

                Toast.makeText(MainActivity.this,"upload success",Toast.LENGTH_SHORT);
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_download:

                Toast.makeText(MainActivity.this,"download success",Toast.LENGTH_SHORT);
                break;
            case R.id.nav_set_lock:

                Toast.makeText(MainActivity.this,"lock success",Toast.LENGTH_SHORT);
                break;
            case R.id.nav_trash:
                //替换默认的Fragment
                fragmentTrash = new FragmentTrash();
                FragmentManager fm1 = getFragmentManager();
                FragmentTransaction tx1 = fm1.beginTransaction();
                tx1.replace(R.id.id_fragment_container,fragmentTrash);
                tx1.commit();
                drawer.closeDrawer(GravityCompat.START);
                break;
            }
    }

}
