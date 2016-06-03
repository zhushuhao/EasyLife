package d.dao.easylife.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dao on 5/29/16.
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {
    private ProgressDialog pd;

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


    //显示进度框
    protected void showProgressDialog(String title, String content) {
        pd = ProgressDialog.show(this, title, content, false, false);
        pd.show();
    }

    //隐藏进度框
    protected void hiddenProgressDialog() {
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
            pd = null;
        }
    }


}
