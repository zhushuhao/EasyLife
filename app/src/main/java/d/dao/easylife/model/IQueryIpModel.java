package d.dao.easylife.model;

import d.dao.easylife.bean.ip.Ip;
import rx.Observable;

/**
 * Created by dao on 6/4/16.
 * ip查询
 */
public interface IQueryIpModel {

    /**
     * 查询IP
     * @param ip
     * @param key
     * @return
     */
    Observable<Ip> queryIp(String ip,String key);
}
