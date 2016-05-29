package d.dao.easylife.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dao on 5/29/16.
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.getLayoutId());
        this.initToolbar(savedInstanceState);
        this.initViews(savedInstanceState);
        this.initData();
        this.initListeners();
    }

    //布局ID
    protected abstract int getLayoutId();
    protected abstract void initToolbar(Bundle savedInstanceState);
    protected abstract void initViews(Bundle savedInstanceState);
    protected abstract void initData();
    protected abstract void initListeners();


}
