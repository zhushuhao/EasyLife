package d.dao.easylife.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import d.dao.easylife.R;
import d.dao.easylife.bean.joke.BaseJokeFirstData;

/**
 * Created by dao on 6/1/16.
 */
public class JokeFirstAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static int TYPE_NONE = 0;//没有图片
    private static int TYPE_WITH = 1;//有图片

    private List<BaseJokeFirstData> mList = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mInflater;

    private int[] colors = {Color.RED, Color.BLACK, Color.BLUE, Color.GREEN, Color.GRAY};

    public JokeFirstAdapter(Context context, List<BaseJokeFirstData> list) {
        this.mList = list;
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = mInflater.inflate(R.layout.item_joke_first, parent, false);
            AutoUtils.autoSize(view);
            return new JokeFirstHolder(view);
        } else {
            View view = mInflater.inflate(R.layout.item_joke_first_with_photo, parent, false);
            AutoUtils.autoSize(view);
            return new JokeFirstHolderWithPhoto(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof JokeFirstHolder) {
            bindJokeFirstHolder((JokeFirstHolder) holder, position);
        } else if (holder instanceof JokeFirstHolderWithPhoto) {
            bindJokeFirstHolderWithPhoto((JokeFirstHolderWithPhoto) holder, position);

        }
    }

    //绑定数据
    private void bindJokeFirstHolder(JokeFirstHolder holder, int position) {
        int index = position % colors.length;
        holder.divider.setBackgroundColor(colors[index]);
        holder.tv_author.setText(mList.get(position).getAuthor());
        holder.tv_content.setText(mList.get(position).getContent());
    }

    //绑定数据
    private void bindJokeFirstHolderWithPhoto(JokeFirstHolderWithPhoto holder, int position) {
        int index = position % colors.length;
        holder.divider.setBackgroundColor(colors[index]);
        holder.tv_author.setText(mList.get(position).getAuthor());
        holder.tv_content.setText(mList.get(position).getContent());
        Log.e("url",mList.get(position).getPicUrl());
        Glide.with(mContext)
                .load(mList.get(position).getPicUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .fitCenter()
                .into(holder.iv_photo)
        ;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void setList(List<BaseJokeFirstData> list) {
        this.mList.clear();
        this.mList.addAll(list);
    }

    @Override
    public int getItemViewType(int position) {
        if (TextUtils.isEmpty(mList.get(position).getPicUrl())) {
            //如果图片网址为空
            return TYPE_NONE;
        } else {
            return TYPE_WITH;
        }
    }


    class JokeFirstHolder extends RecyclerView.ViewHolder {
        TextView tv_author;//作者
        TextView tv_content;//内容
        View divider;

        public JokeFirstHolder(View itemView) {
            super(itemView);
            tv_author = (TextView) itemView.findViewById(R.id.tv_author);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            divider = itemView.findViewById(R.id.divider);
        }
    }


    class JokeFirstHolderWithPhoto extends RecyclerView.ViewHolder {
        View divider;
        TextView tv_author;//作者
        TextView tv_content;//内容
        ImageView iv_photo;

        public JokeFirstHolderWithPhoto(View itemView) {
            super(itemView);
            tv_author = (TextView) itemView.findViewById(R.id.tv_author);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            iv_photo = (ImageView) itemView.findViewById(R.id.iv_photo);
            divider = itemView.findViewById(R.id.divider);
        }
    }
}
