package d.dao.easylife.bean.joke;

import java.util.ArrayList;

/**
 * Created by dao on 5/31/16.
 * 第一个选项卡的joke
 */
public class JokeFirst {
    public String status;
    public String desc;
    public ArrayList<BaseJokeFirstData> detail;

    @Override
    public String toString() {
        return "JokeFirst{" +
                "status='" + status + '\'' +
                ", desc='" + desc + '\'' +
                ", detail=" + detail +
                '}';
    }
}


//{
//        "status":"000000",----返回状态，六个0表示成功
//        "desc":null,----返回结果描述，六个0表示成功
//        "detail":[----具体笑话列表，是一个数组
//        {
//        "id":1115,----数据库自增id，没有任何意义
//        "xhid":"90851",----笑话id，判断笑话新旧用的
//        "author":"xxx",----笑话作者
//        "content":"xxx",----笑话内容
//        "picUrl":"",----笑话的图片（如果有）
//        "status":"1"----笑话状态（能返回的都是1）
//        },
//        {
//        "id":1110,
//        "xhid":"90850",
//        "author":"xxx",
//        "content":"xxx",
//        "picUrl":"",
//        "status":"1"
//        }
//        ]
//        }