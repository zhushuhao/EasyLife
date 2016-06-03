package d.dao.easylife.presenter;

import d.dao.easylife.ui.view.IJokeFirstView;
import d.dao.easylife.ui.view.IMainView;

/**
 * Created by dao on 5/31/16.
 * 第一个选项卡的笑话
 */
public interface IJokeFirstPresenter {
    void attachView(IJokeFirstView view);


    /**
     * 加载
     * @param maxId  最大ID
     * @param minId  最小ID
     * @param size   条数
     */
    void loadFirstJoke(int maxId,int minId,int size);

    /**
     * 下拉加载更多
     * @param maxId  最大ID
     * @param minId  最小ID
     * @param size   条数
     */
    void loadFirstJokeMore(int maxId,int minId,int size);
    void detachView(IJokeFirstView view);
}
