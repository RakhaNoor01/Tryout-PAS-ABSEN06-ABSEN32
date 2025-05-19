package com.example.tryout_pas.api;
import com.example.tryout_pas.model.HoloMember;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface ApiService {
    @GET("api/v1/hololive")
    Call<List<HoloMember>> getHoloMembers();
}
