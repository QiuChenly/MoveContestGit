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
import android.widget.TextView;
import android.widget.Toast;

import com.wzg.R;
import com.wzg.view.fragment.AccountManageFragment;
import com.wzg.view.fragment.BusInquiryFragment;

import org.w3c.dom.Text;

public class MenuActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private NavigationView mNavView;
    private ActionBar mActionBar;
    private DrawerLayout mDrawerLayout;
    private TextView mMainTitle;
    private TextView mRecharge;
    private TextView mBatch;
    private AccountManageFragment mAccountManageFragment;
    private BusInquiryFragment mBusInquiryFragment;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initControl();


    }

    /**
     * 初始化控件
     */
    private void initControl() {


        mMainTitle = findViewById(R.id.mainTitle);
        mRecharge = findViewById(R.id.recharge);
        mBatch = findViewById(R.id.batch);

        hideTitles();


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
                        hideTitles();
                        hideFragment(mTransaction);
                        mAccountManageFragment = new AccountManageFragment();
                        mMainTitle.setVisibility(View.VISIBLE);
                        mRecharge.setVisibility(View.VISIBLE);
                        mBatch.setVisibility(View.VISIBLE);
                        mMainTitle.setText("账户管理");
                        replaceFragment(mAccountManageFragment);
                        break;
                    case R.id.nav_busInquiry:

                        hideTitles();
                        hideFragment(mTransaction);

                        mBusInquiryFragment = new BusInquiryFragment();
                        mMainTitle.setVisibility(View.VISIBLE);
                        mMainTitle.setText("公交查询");
                        replaceFragment(mBusInquiryFragment);

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
        mFragmentManager = getSupportFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        mTransaction.replace(R.id.page_layout, fragment);
        mTransaction.commit();

    }


    /**
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


    /**
     * 将多余的标题隐藏
     */
    private void hideTitles() {
        mMainTitle.setVisibility(View.INVISIBLE);
        mRecharge.setVisibility(View.INVISIBLE);
        mBatch.setVisibility(View.INVISIBLE);
    }


    private void hideFragment(FragmentTransaction fragmentTransaction) {
        // 如果此Fragment不为空的话就隐藏起来
        if (mAccountManageFragment != null) {
            fragmentTransaction.hide(mAccountManageFragment);
        }

        if (mBusInquiryFragment != null) {
            fragmentTransaction.hide(mBusInquiryFragment);
        }


    }


}
