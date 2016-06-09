package d.dao.easylife.model.impl;

import d.dao.easylife.api.EasyLife;
import d.dao.easylife.bean.ip.Ip;
import d.dao.easylife.constants.BaseUrl;
import d.dao.easylife.model.IQueryIpModel;
import rx.Observable;

/**
 * Created by dao on 6/4/16.
 * 查询ip
 */
public class QueryIpModel implements IQueryIpModel{

    private static QueryIpModel mInstance = new QueryIpModel();

    public static synchronized QueryIpModel getInstance(){
        return mInstance;
    }
    @Override
    public Observable<Ip> queryIp(String ip, String key) {
        return EasyLife.getInstance().getApiService(BaseUrl.IP).queryIp(ip,key);
    }
}
