package d.dao.easylife.ui.view;

import java.util.List;

import d.dao.easylife.bean.robot.BaseRobotResponseData;

/**
 * Created by dao on 6/4/16.
 * 机器人
 */
public interface IRobotView {
    /**
     * 获取机器人应答成功
     * @param data 数据
     */
    void onGetRobotResponseSuccess(BaseRobotResponseData data);

    /**
     * 失败
     * @param e
     */
    void onGetRobotResponseFailure(Throwable e);
}
