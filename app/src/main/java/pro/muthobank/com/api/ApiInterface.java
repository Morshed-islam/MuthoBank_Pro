package pro.muthobank.com.api;

import okhttp3.ResponseBody;
import pro.muthobank.com.model.SubscriberPostModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST(HttpParams.API_SUBSCRIBER)
    Call<ResponseBody> postSubscribe(@Body SubscriberPostModel postSubscribe);
}
