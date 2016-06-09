package d.dao.easylife.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import d.dao.easylife.R;
import d.dao.easylife.ui.fragment.fragment_query_ip;
import d.dao.easylife.ui.fragment.fragment_query_express;
import d.dao.easylife.ui.fragment.Fragment3;
import d.dao.easylife.ui.fragment.Fragment4;

/**
 * 天气
 */
public class QueryActivity extends BaseToolbarActivity
        implements View.OnClickListener{
    private Context mContext;
    private RadioGroup radioGroup;

    public static final String fragment1Tag = "fragment1";
    public static final String fragment2Tag = "fragment2";
    public static final String fragment3Tag = "fragment3";
    public static final String fragment4Tag = "fragment4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = new fragment_query_ip();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment, fragment1Tag).commit();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_query;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        mContext = QueryActivity.this;
        super.setHomeTrue();

        radioGroup = (RadioGroup) findViewById(R.id.activity_group_radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment1 = fm.findFragmentByTag(fragment1Tag);
                Fragment fragment2 = fm.findFragmentByTag(fragment2Tag);
                Fragment fragment3 = fm.findFragmentByTag(fragment3Tag);
                Fragment fragment4 = fm.findFragmentByTag(fragment4Tag);
                if (fragment1 != null) {
                    ft.hide(fragment1);
                }
                if (fragment2 != null) {
                    ft.hide(fragment2);
                }
                if (fragment3 != null) {
                    ft.hide(fragment3);
                }
                if (fragment4 != null) {
                    ft.hide(fragment4);
                }
                switch (checkedId) {
                    case R.id.query_ip:
                        if (fragment1 == null) {
                            fragment1 = new fragment_query_ip();
                            ft.add(R.id.container, fragment1, fragment1Tag);
                        } else {
                            ft.show(fragment1);
                        }
                        break;
                    case R.id.query_express:
                        if (fragment2 == null) {
                            fragment2 = new fragment_query_express();
                            ft.add(R.id.container, fragment2, fragment2Tag);
                        } else {
                            ft.show(fragment2);
                        }
                        break;
                    case R.id.query_third:
                        if (fragment3 == null) {
                            fragment3 = new Fragment3();
                            ft.add(R.id.container, fragment3,
                                    fragment3Tag);
                        } else {
                            ft.show(fragment3);
                        }
                        break;
                    case R.id.query_forth:
                        if (fragment4 == null) {
                            fragment4 = new Fragment4();
                            ft.add(R.id.container, fragment4, fragment4Tag);
                        } else {
                            ft.show(fragment4);
                        }
                        break;
                    default:
                        break;
                }
                ft.commit();
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void loadData(String city){

    }
    @Override
    protected void initListeners(){
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_weather, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            //返回
            case android.R.id.home:
                finish();
                break;

        }


        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton mTab = (RadioButton) radioGroup.getChildAt(i);
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentByTag((String) mTab.getTag());
            FragmentTransaction ft = fm.beginTransaction();
            if (fragment != null) {
                if (!mTab.isChecked()) {
                    ft.hide(fragment);
                }
            }
            ft.commit();
        }
    }


}
