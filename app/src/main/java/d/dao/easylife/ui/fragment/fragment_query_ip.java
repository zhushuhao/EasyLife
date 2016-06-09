package d.dao.easylife.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;

import d.dao.easylife.R;
import d.dao.easylife.adapter.SearchResultsListAdapter;
import d.dao.easylife.bean.ip.BaseIpData;
import d.dao.easylife.constants.BaseUrl;
import d.dao.easylife.presenter.impl.QueryIpPresenterImpl;
import d.dao.easylife.ui.view.IQueryIpView;
import d.dao.easylife.utils.ToastUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_query_ip extends Fragment implements IQueryIpView {

    private final String TAG = "MainActivity";

    public static final long FIND_SUGGESTION_SIMULATED_DELAY = 250;

    private FloatingSearchView mSearchView;

    private RecyclerView mSearchResultsList;
    private SearchResultsListAdapter mSearchResultsAdapter;


    private Context mContext;
    private EditText et_ip;
    private TextView tv_query;

    private TextView tv_area,tv_location;

    private QueryIpPresenterImpl mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        this.mPresenter = new QueryIpPresenterImpl();
        this.mPresenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_query_ip, container, false);
        et_ip = (EditText) view.findViewById(R.id.et_ip);
        tv_query = (TextView) view.findViewById(R.id.tv_query);

        tv_area = (TextView) view.findViewById(R.id.tv_area);
        tv_location = (TextView) view.findViewById(R.id.tv_location);


        tv_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = et_ip.getText().toString().trim();
                if (!TextUtils.isEmpty(text)) {
                    mPresenter.queryIp(text, BaseUrl.IP_KEY);
                } else {
                    ToastUtils.show(mContext, "IP地址不能为空", 0);
                }
            }
        });


        return view;
    }

    /**
     * 查询成功
     *
     * @param data 返回的IP信息
     */

    @Override
    public void onQueryIpSuccess(BaseIpData data) {

        Log.e("data",data.toString());


        String area = data.getArea();
        String location = data.getLocation();

        tv_area.setText(area);
        tv_location.setText(location);

    }

    /**
     * 查询失败
     *
     * @param e
     */
    @Override
    public void onQueryIpFailure(Throwable e) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mPresenter.detachView(this);
    }
}
