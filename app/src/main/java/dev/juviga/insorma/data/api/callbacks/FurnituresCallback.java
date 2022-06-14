package dev.juviga.insorma.data.api.callbacks;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

import dev.juviga.insorma.data.model.Product;
import dev.juviga.insorma.data.repository.ProductRepository;
import dev.juviga.insorma.data.shared.SharedData;
import dev.juviga.insorma.utils.Closer;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FurnituresCallback implements Callback<ResponseBody> {

    @Override
    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
        try (Closer closer = new Closer()) {
            ResponseBody body = closer.add(response.body());

            JSONObject obj = (JSONObject) new JSONTokener(body.string()).nextValue();
            JSONArray array = obj.getJSONArray("furnitures");

            for (int i = 0; i < array.length(); i++) {
                JSONObject rawProduct = array.getJSONObject(i);
                Product product = Product.fromJsonObject(rawProduct);

                ProductRepository productRepo = SharedData.PRODUCT_REPOSITORY;
                productRepo.insert(product);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
        t.printStackTrace();
    }

}
