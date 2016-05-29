package d.dao.easylife.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import d.dao.easylife.R;
import d.dao.easylife.bean.News;
import d.dao.easylife.ui.BaseToolbarActivity;
import d.dao.easylife.ui.view.IMainView;
import d.dao.easylife.ui.widget.DividerItemDecoration;

public class MainActivity extends BaseToolbarActivity
        implements NavigationView.OnNavigationItemSelectedListener,IMainView{
    private Context mContext;

    private DrawerLayout mDrawer;// 抽屉
    private NavigationView mNavigationView;//

    private SwipeRefreshLayout mSwipe;//刷新视图
    private RecyclerView mRecyclerView;// 数据列表


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = MainActivity.this;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        //mDrawer
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        //mNavigationView
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        //mSwipe
        mSwipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mSwipe.setColorSchemeResources(android.R.color.holo_purple, android.R.color.holo_blue_bright, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        //mRecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置adapter
        mRecyclerView.setAdapter(null);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                mContext, DividerItemDecoration.HORIZONTAL_LIST));

    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initListeners() {
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 获取新闻成功
     * @param list
     */
    @Override
    public void onGetNewsSuccess(List<News> list) {

    }

    /**
     * 获取新闻失败
     */
    @Override
    public void onGetNewsFailure() {

    }
}
