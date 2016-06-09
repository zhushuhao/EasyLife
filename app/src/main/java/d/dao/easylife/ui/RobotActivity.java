package d.dao.easylife.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.camnter.easyrecyclerview.widget.decorator.EasyBorderDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import d.dao.easylife.R;
import d.dao.easylife.adapter.RobotAdapter;
import d.dao.easylife.bean.robot.BaseRobotResponseData;
import d.dao.easylife.bean.robot.RobotMessage;
import d.dao.easylife.constants.BaseUrl;
import d.dao.easylife.presenter.impl.RobotPresenterImpl;
import d.dao.easylife.ui.view.IRobotView;
import d.dao.easylife.utils.ToastUtils;

/**
 * Created by dao on 6/3/16.
 */
public class RobotActivity extends BaseToolbarActivity implements View.OnClickListener, IRobotView {

    private RecyclerView mRecyclerView;
    private Context mContext;
    private RobotAdapter mAdapter;
    private List<RobotMessage> mList = new ArrayList<>();
    private TextView tv_send;
    private EditText et_send;

    private RobotPresenterImpl mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_robot;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.setHomeTrue();//加上返回按钮
        mContext = RobotActivity.this;
        mPresenter = new RobotPresenterImpl();
        mPresenter.attachView(this);

        tv_send = (TextView) findViewById(R.id.tv_send);
        et_send = (EditText) findViewById(R.id.et_send);

        //RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);

        mAdapter = new RobotAdapter(mContext, mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.addItemDecoration(new EasyBorderDividerItemDecoration(
//                this.getResources().getDimensionPixelOffset(R.dimen.data_border_divider_height),
//                this.getResources().getDimensionPixelOffset(R.dimen.data_border_padding_infra_spans)));
        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListeners() {
        tv_send.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_send:
                String message = et_send.getText().toString();
                Log.e("msg", message);
                if (TextUtils.isEmpty(message)) {
                    et_send.findFocus();
                    return;
                }
                mList.add(new RobotMessage(message, 0));
                mAdapter = new RobotAdapter(mContext, mList);
                mAdapter.notifyDataSetChanged();
                mRecyclerView.scrollToPosition(mList.size() - 1);
                mPresenter.loadRobot(message, BaseUrl.ROBOT_KEY);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //获取应答成功
    @Override
    public void onGetRobotResponseSuccess(BaseRobotResponseData data) {
            String result = data.getText();
            mList.add(new RobotMessage(result, 1));
            mAdapter = new RobotAdapter(mContext, mList);
            mAdapter.notifyDataSetChanged();
            mRecyclerView.scrollToPosition(mList.size() - 1);
    }



    //获取应答失败
    @Override
    public void onGetRobotResponseFailure(Throwable e) {
        ToastUtils.show(mContext, "应答失败", 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView(this);

    }
}
