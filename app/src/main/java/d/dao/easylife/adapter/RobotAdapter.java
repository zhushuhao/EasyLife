package d.dao.easylife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import d.dao.easylife.R;
import d.dao.easylife.bean.robot.RobotMessage;

/**
 * Created by dao on 6/3/16.
 */
public class RobotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<RobotMessage> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    public RobotAdapter(Context context, List<RobotMessage> list) {
        this.mList = list;
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View view = mInflater.inflate(R.layout.item_robot_right, parent, false);
                AutoUtils.autoSize(view);
                return new RobotHolder(view);
            case 1:
                View view2 = mInflater.inflate(R.layout.item_robot_left, parent, false);
                AutoUtils.autoSize(view2);
                return new HumanHolder(view2);
            default:
                View view3 = mInflater.inflate(R.layout.item_robot_right, parent, false);
                AutoUtils.autoSize(view3);
                return new RobotHolder(view3);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (mList.get(position).getType() == 0) {
            ((RobotHolder) holder).tv.setText(mList.get(position).getMsg());
        } else {
            ((HumanHolder) holder).tv.setText(mList.get(position).getMsg());
        }
    }

    @Override
    public int getItemViewType(int position) {

        int type = 0;
        switch (mList.get(position).getType()) {
            // 自己
            case 0:
                type = 0;
                break;
            case 1:
                type = 1;
                break;
        }
        return type;

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class RobotHolder extends RecyclerView.ViewHolder {

        private TextView tv;

        public RobotHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_msg);
        }
    }

    class HumanHolder extends RecyclerView.ViewHolder {

        private TextView tv;

        public HumanHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_msg);
        }
    }
}
