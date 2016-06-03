package d.dao.easylife.bean.joke;

/**
 * Created by dao on 5/31/16.
 * 笑话1
 */
public class BaseJokeFirstData {
//    "id": 1115,        ----数据库自增id，没有任何意义
//    "xhid": "90851",   ----笑话id，判断笑话新旧用的
//    "author": "xxx",   ----笑话作者
//    "content": "xxx",  ----笑话内容
//    "picUrl": "",      ----笑话的图片（如果有）
//    "status": "1"      ----笑话状态（能返回的都是1）
    private int id;
    private String xhid;
    private String author;
    private String content;
    private String picUrl;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getXhid() {
        return xhid;
    }

    public void setXhid(String xhid) {
        this.xhid = xhid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BaseJokeFirstData{" +
                "id=" + id +
                ", xhid='" + xhid + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
