package com.wzg.view.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.wzg.R;
import com.wzg.view.fragment.AccountManageFragment;

public class MenuActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private NavigationView mNavView;
    private ActionBar mActionBar;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initControl();


    }

    /**
     * 初始化控件
     *
     */
    private void initControl() {
        findViewById(R.id.mainTitle).setVisibility(View.INVISIBLE);
        findViewById(R.id.recharge).setVisibility(View.INVISIBLE);
        findViewById(R.id.batch).setVisibility(View.INVISIBLE);



        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeAsUpIndicator(R.drawable.menu);
        }


        mDrawerLayout = findViewById(R.id.drawer_layout);


        mNavView = findViewById(R.id.nav_view);
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_userManage:


                        findViewById(R.id.mainTitle).setVisibility(View.VISIBLE);
                        findViewById(R.id.recharge).setVisibility(View.VISIBLE);
                        findViewById(R.id.batch).setVisibility(View.VISIBLE);
                        replaceFragment(new AccountManageFragment());
                        break;
                    default:
                        Toast.makeText(MenuActivity.this, "出错了", Toast.LENGTH_SHORT).show();
                        break;
                }
                item.setChecked(true);
                mDrawerLayout.closeDrawers();


                return true;
            }
        });







    }


    /**
     * 碎片的替换
     *
     * @param fragment
     */
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.page_layout, fragment);
        transaction.commit();

    }


    /**
     *
     * 顶部菜单的选择项
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return false;
    }
}
