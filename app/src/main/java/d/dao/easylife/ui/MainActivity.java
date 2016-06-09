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
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import d.dao.easylife.bean.news.BaseNewsData;
import d.dao.easylife.bean.weather.Weather;
import d.dao.easylife.constants.BaseUrl;
import d.dao.easylife.manager.NavigationManager;
import d.dao.easylife.presenter.impl.NewsPresenterImpl;
import d.dao.easylife.ui.view.IMainView;
import d.dao.easylife.utils.ReservoirUtils;
import d.dao.easylife.utils.ToastUtils;

import static android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

public class MainActivity extends BaseToolbarActivity
        implements NavigationView.OnNavigationItemSelectedListener, IMainView,View.OnClickListener, OnRefreshListener {
    private Context mContext;

    private DrawerLayout mDrawer;// 抽屉
    private NavigationView mNavigationView;//

    private SwipeRefreshLayout mSwipe;//刷新视图
    private RecyclerView mRecyclerView;// 数据列表

    private NewsPresenterImpl mPresenter;

    private List<BaseNewsData> mList = new ArrayList<>();

    private NewsAdapter mAdapter;
    private EasyBorderDividerItemDecoration dataDecoration;//
    private ReservoirUtils reservoirUtils;//缓存

    private boolean isRefreshing = false;//是否正在请求数据

    private boolean firstRefresh = true;//是否第一次进入时自动刷新

    private RelativeLayout rl_content_root;//主界面内容
    private LinearLayout ll_error_root;//错误界面
    private TextView tv_loadOnceMore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mContext = MainActivity.this;
        reservoirUtils = ReservoirUtils.getInstance();

        rl_content_root = (RelativeLayout) findViewById(R.id.content_main_root);
        ll_error_root = (LinearLayout) findViewById(R.id.error_root);
        tv_loadOnceMore = (TextView) findViewById(R.id.tv_load_once_more);

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
        mAdapter = new NewsAdapter(mContext, mList);
        mAdapter.setOnItemClickListener(new NewsAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("position", "" + position);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //EasyRecyclerView
        this.dataDecoration = new EasyBorderDividerItemDecoration(
                this.getResources().getDimensionPixelOffset(R.dimen.data_border_divider_height),
                this.getResources()
                        .getDimensionPixelOffset(R.dimen.data_border_padding_infra_spans));
        //添加分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(
//                mContext, DividerItemDecoration.HORIZONTAL_LIST));
        mRecyclerView.addItemDecoration(dataDecoration);
    }


    @Override
    protected void initData() {
        this.mPresenter = new NewsPresenterImpl();
        mPresenter.attachView(this);
        //初次进入加载
        mSwipe.measure(0, 0);
        mSwipe.setRefreshing(true);
        isRefreshing = true;
        Log.e("laodnews", "开始加载");
        this.mPresenter.loadNews(true);
    }


    @Override
    protected void initListeners() {
        tv_loadOnceMore.setOnClickListener(this);
        mNavigationView.setNavigationItemSelectedListener(this);

        mSwipe.setOnRefreshListener(this);
        this.mRecyclerView.addOnScrollListener(this.getRecyclerViewOnScrollListener());
        this.mAdapter.setOnItemClickListener(new NewsAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString(BaseUrl.WEBVIEW_TITLE,mList.get(position).getTitle());
                bundle.putString(BaseUrl.WEBVIEW_URL,mList.get(position).getArticle_url());
                NavigationManager.gotoActivity(mContext,WebViewActivity.class,bundle);
            }
        });
        this.mAdapter.setOnItemLongClickListener(new NewsAdapter.OnRecyclerViewItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    /**
     * LinearLayoutManager 时的滚动监听
     *
     * @return RecyclerView.OnScrollListener
     */
    public RecyclerView.OnScrollListener getRecyclerViewOnScrollListener() {
        return new RecyclerView.OnScrollListener() {
            private boolean toLast = false;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    // 正在向下滑动
//                    Log.e("MainActivity", "向下滚动中");
                    this.toLast = true;
                } else {
                    // 停止滑动或者向上滑动
//                    Log.e("MainActivity", "向上滚动中");
                    this.toLast = false;
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
                    // 不滚动
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        // 最后完成显示的item的position 正好是 最后一条数据的index
                        if (toLast && manager.findLastCompletelyVisibleItemPosition() ==
                                (manager.getItemCount() - 1)) {
                            Log.e("MainActivity", "加载更多");
                            MainActivity.this.loadMoreRequest();
                        }
                    }
                } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                    StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) layoutManager;
                    // 不滚动
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        /*
                         * 由于是StaggeredGridLayoutManager
                         * 取最底部数据可能有两个item，所以判断这之中有一个正好是 最后一条数据的index
                         * 就OK
                         */
                        int[] bottom = manager.findLastCompletelyVisibleItemPositions(new int[2]);
                        int lastItemCount = manager.getItemCount() - 1;
                        if (toLast && (bottom[0] == lastItemCount || bottom[1] == lastItemCount)) {
                            MainActivity.this.loadMoreRequest();
                        }
                    }
                }
            }
        };
    }

    //加载更多
    private void loadMoreRequest() {
        if (!isRefreshing) {
            if (mList != null && mList.size() > 0) {
                //加载更多
                mPresenter.loadMore(mList.get(mList.size() - 1).getBehot_time());
                isRefreshing = true;
                mSwipe.setRefreshing(true);
            }

        }
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
        switch (item.getItemId()){
            // 新闻
            case R.id.nav_news:
                mDrawer.closeDrawer(GravityCompat.START);
                break;
            //笑话
            case R.id.nav_joke:
                mDrawer.closeDrawer(GravityCompat.START);
                NavigationManager.gotoActivity(mContext,JokeActivity.class);
                break;
            //天气
            case R.id.nav_weather:
                mDrawer.closeDrawer(GravityCompat.START);
                NavigationManager.gotoActivity(mContext,WeatherActivity.class);
                break;
            //机器人
            case R.id.nav_robot:
                mDrawer.closeDrawer(GravityCompat.START);
                NavigationManager.gotoActivity(mContext,RobotActivity.class);
                break;

            //常用查询
            case R.id.nav_find:
                mDrawer.closeDrawer(GravityCompat.START);
                NavigationManager.gotoActivity(mContext,QueryActivity.class);


                break;

            //分享
            case R.id.nav_share:
                break;

            //设置
            case R.id.nav_setting:
                break;
        }

        return true;
    }

    /**
     * 刷新时获取新闻成功
     *
     * @param list
     */
    @Override
    public void onGetNewsSuccess(List<BaseNewsData> list) {
        Log.e("success", "success");
        if(ll_error_root.getVisibility()==View.VISIBLE){
            ll_error_root.setVisibility(View.GONE);
        }
        isRefreshing = false;
        if(this.mSwipe.isRefreshing()){
            this.mSwipe.setRefreshing(false);
        }
        if (firstRefresh) {
            firstRefresh = false;
            this.mList = list;
            mAdapter.setList(mList);
            mAdapter.notifyDataSetChanged();
        } else {
            //排除重复的数据
            int i = 0;
            Log.e("list", "排除u重复的元素");
            List<BaseNewsData> temp = new ArrayList<>();
            temp.addAll(list);
            //截取前10条数据,只用前10条数据与得到的数据进行比较
            List<BaseNewsData> tempFather = mList.subList(0, 10);
            Log.e("size", "" + tempFather.size());
            for (BaseNewsData data : tempFather) {
                long timeStamp = data.getBehot_time();
                for (BaseNewsData data2 : list) {
                    if (data2.getBehot_time() == timeStamp) {
                        temp.remove(data2);
                        i++;
//                        Log.e("i", "" + i);
                    }
                }
            }
            Log.e("list", "排除finish");
            if(temp.size()==0){
                //如果得到的十条数据都重复
                ToastUtils.show(mContext,"暂无更新数据",0);
            }else{
                // 把所有刷新的到的数据放到原有的数据前面
                temp.addAll(mList);
                mList.clear();
                mList.addAll(temp);
                mAdapter.setList(mList);
                mAdapter.notifyDataSetChanged();
            }

        }
    }

    /**
     * 刷新时获取新闻失败
     */
    @Override
    public void onGetNewsFailure(Throwable e) {
        isRefreshing = false;
        Log.e("error", e.toString());
        this.mSwipe.setRefreshing(false);
        ToastUtils.show(mContext, "刷新失败", 0);

        //根据mList的大小来判断,如果为0,说明网络请求失败,获取缓存失败
        if(mList == null || mList.size()==0){
            ll_error_root.setVisibility(View.VISIBLE);
        }else{//不为0,就不显示
            ll_error_root.setVisibility(View.GONE);
        }
    }

    /**
     * 向下加载更多时获取新闻成功
     *
     * @param list 新闻数据列表
     */

    @Override
    public void onLoadNewsSuccess(List<BaseNewsData> list) {
        isRefreshing = false;
        this.mList.addAll(list);
        this.mSwipe.setRefreshing(false);
        this.mAdapter.setList(mList);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 向下加载更多时获取新闻失败
     *
     * @param e
     */
    @Override
    public void onLoadNewsFailure(Throwable e) {
        isRefreshing = false;
        this.mSwipe.setRefreshing(false);
        ToastUtils.show(mContext, "加载失败", 0);
    }


    //swipe
    @Override
    public void onRefresh() {
        isRefreshing = true;
        Log.e("laodnews", "开始加载");
        this.mPresenter.loadNews(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView(this);
        reservoirUtils.refresh("news",mList);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_load_once_more:
                Log.e("loadoncemore","clicked");
                mSwipe.setRefreshing(true);
                isRefreshing = true;
                Log.e("laodnews", "开始加载");
                this.mPresenter.loadNews(false);
                ll_error_root.setVisibility(View.GONE);
                break;
        }
    }
}
