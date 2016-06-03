package d.dao.easylife.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import d.dao.easylife.constants.BaseUrl;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by dao on 5/29/16.
 * OkHttp与Retrofit管理
 */
public class EasyLife {
    private static EasyLife mInstance;
    private ApiService mApiService;
    private OkHttpClient okHttpClient;
    private Gson gson;

    private EasyLife() {
        gson = new GsonBuilder().setDateFormat(BaseUrl.DATA_FORMAT).create();
        //初始化okHttpClient
        okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(8000, TimeUnit.MILLISECONDS);


        /*
         * 查看网络请求发送状况
         */
//        if (EasyApplication.getInstance().log) {
//            okHttpClient.interceptors().add(chain -> {
//                Response response = chain.proceed(chain.request());
//                Log.e("url",chain.request().urlString());
//                return response;
//            });
//        }
//        if(baseUrl.equals(BaseUrl.NEWS)){
//            okHttpClient.interceptors().add(new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//                    Request original = chain.request();
//
//                    Request request = original.newBuilder()
//                            .header("apikey", BaseUrl.NEWS_API_KEY)
//                            .method(original.method(), original.body())
//                            .build();
//                    Response response = chain.proceed(request);
//                    return response;
//                }
//            });
//        }


    }

    //单例
    public static EasyLife getInstance() {
        if (mInstance == null) {
            mInstance = new EasyLife();
        }
        return mInstance;
    }

    //由于每一次的baseUrl都不一样,所以要放在这里处理retrofit,
    //如果是同一个 baseUrl,直接放在上面和okHttpClient一起初始化就可以了

    //
    public ApiService getApiService(String baseUrl) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addCallAdapterFactory(
                        RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        this.mApiService = retrofit.create(ApiService.class);
        return mApiService;
    }

}
