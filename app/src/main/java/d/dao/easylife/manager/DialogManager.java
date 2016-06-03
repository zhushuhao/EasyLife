package d.dao.easylife.manager;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by dao on 6/3/16.
 * 通用的加载进度框管理
 */
public class DialogManager {
    private static DialogManager mInstance;

    static {
        mInstance = new DialogManager();
    }

    public static synchronized DialogManager getInstance() {
        return mInstance;
    }

    private ProgressDialog pd;

    // 显示进度框
    public void showDialog(Context context) {
        pd = ProgressDialog.show(context, "", "加载中", false, false);
        if(pd!=null){

        }else{
            pd = ProgressDialog.show(context, "", "加载中", false, false);
        }
        pd.show();
    }

    //隐藏进度框
    public void hiddenDialog() {
        if(pd.isShowing()){
            pd.hide();
        }

    }


}
