package d.dao.easylife.bean.news;

import java.io.Serializable;

/**
 * Created by dao on 5/30/16.
 * 每一条新闻
 */
public class BaseNewsData implements Serializable {
//    "title": "分享视频 ",    ----新闻标题
//    "source": "微博视频",    ----新闻来源
//    "article_url": "xxx",    ----新闻的url地址
//    "publish_time": 0,       ----没意义
//    "behot_time": 1425185036000,  ----新闻收录时间，以毫秒计数的整数
//    "create_time": 0,        ----没意义
//    "digg_count": 1,         ----赞的次数
//    "bury_count": 2015,      ----踩的次数
//    "repin_count": 1,        ----收藏次数
//    "group_id": "4006917770" ----新闻的id，无需关注

    private String title;
    private String source;
    private String article_url;
    private String publish_url;
    private long behot_time;
    private int create_time;
    private int digg_count;
    private int bury_count;
    private int repin_count;
    private String group_id;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }

    public String getPublish_url() {
        return publish_url;
    }

    public void setPublish_url(String publish_url) {
        this.publish_url = publish_url;
    }

    public long getBehot_time() {
        return behot_time;
    }

    public void setBehot_time(long behot_time) {
        this.behot_time = behot_time;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public int getDigg_count() {
        return digg_count;
    }

    public void setDigg_count(int digg_count) {
        this.digg_count = digg_count;
    }

    public int getBury_count() {
        return bury_count;
    }

    public void setBury_count(int bury_count) {
        this.bury_count = bury_count;
    }

    public int getRepin_count() {
        return repin_count;
    }

    public void setRepin_count(int repin_count) {
        this.repin_count = repin_count;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    @Override
    public String toString() {
        return "BaseNewsData{" +
                "title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", article_url='" + article_url + '\'' +
                ", publish_url='" + publish_url + '\'' +
                ", behot_time='" + behot_time + '\'' +
                ", create_time=" + create_time +
                ", digg_count=" + digg_count +
                ", bury_count=" + bury_count +
                ", repin_count=" + repin_count +
                ", group_id='" + group_id + '\'' +
                '}';
    }
}
