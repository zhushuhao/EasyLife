package d.dao.easylife.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anupcowkur.reservoir.ReservoirGetCallback;
import com.camnter.easyrecyclerview.widget.decorator.EasyBorderDividerItemDecoration;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import d.dao.easylife.R;
import d.dao.easylife.adapter.NewsAdapter;
import d.dao.easylife.adapter.SimpleFragmentPagerAdapter;
import d.dao.easylife.bean.news.BaseNewsData;
import d.dao.easylife.constants.BaseUrl;
import d.dao.easylife.manager.NavigationManager;
import d.dao.easylife.presenter.impl.NewsPresenterImpl;
import d.dao.easylife.ui.view.IMainView;
import d.dao.easylife.utils.ReservoirUtils;
import d.dao.easylife.utils.ToastUtils;

import static android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

public class JokeActivity extends BaseToolbarActivity
        implements View.OnClickListener {
    private Context mContext;
    private SimpleFragmentPagerAdapter pagerAdapter;

    private ViewPager viewPager;

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_joke;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        mContext = JokeActivity.this;
        super.setHomeTrue();
        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
