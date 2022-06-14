package dev.juviga.insorma.data.api.services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FurnitureService {

    @GET("InSOrmaJSON")
    Call<ResponseBody> getFurnitures();

}
