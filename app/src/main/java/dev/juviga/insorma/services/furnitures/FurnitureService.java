package dev.juviga.insorma.services.furnitures;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FurnitureService {

    @GET("InSOrmaJSON")
    Call<ResponseBody> getFurnitures();

}
