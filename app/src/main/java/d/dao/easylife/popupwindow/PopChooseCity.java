package d.dao.easylife.popupwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zhy.autolayout.AutoRelativeLayout;

import d.dao.easylife.R;
import d.dao.easylife.ui.widget.CityPicker;

/**
 * Created by dao on 5/26/16.
 * 选择性别
 */
public class PopChooseCity extends PopupWindow {

    private Context mContext;

    private View view;
    private CityPicker cityPicker;
    private TextView tv_cancel;
    private TextView tv_ok;



    public PopChooseCity(Context mContext, View.OnClickListener itemsOnClick) {

        this.view = LayoutInflater.from(mContext).inflate(R.layout.pop_choose_city, null);
        cityPicker = (CityPicker) view.findViewById(R.id.citypicker);
        tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        tv_ok = (TextView) view.findViewById(R.id.tv_ok);


        tv_ok.setOnClickListener(itemsOnClick);
        tv_cancel.setOnClickListener(itemsOnClick);

        // 取消按钮
//        btn_cancel.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // 销毁弹出框
//                dismiss();
//            }
//        });
        // 设置按钮监听


        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int top = view.findViewById(R.id.pop_layout).getTop();
                int bottom = view.findViewById(R.id.pop_layout).getBottom();

                int left = view.findViewById(R.id.pop_layout).getLeft();
                int right = view.findViewById(R.id.pop_layout).getRight();

                int x = (int) event.getX();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < top || y > bottom || x < left || x > right) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(AutoRelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(AutoRelativeLayout.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        ColorDrawable dw = new ColorDrawable(0xffffffff);

        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
//        this.setAnimationStyle(R.style.take_photo_anim);

    }

    public String getCity(){
        String city = cityPicker.getCity();
        return city;
    }

}

