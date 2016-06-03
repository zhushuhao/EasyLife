package d.dao.easylife.presenter;

import d.dao.easylife.ui.view.IRobotView;

/**
 * Created by dao on 6/4/16.
 * 机器人
 */
public interface IRobotPresenter {
    void attachView(IRobotView view);

    /**
     *
     * @param info 用户说的话
     * @param key  聚合数据key
     */
    void loadRobot(String info,String key);

    void detachView(IRobotView view);

}
