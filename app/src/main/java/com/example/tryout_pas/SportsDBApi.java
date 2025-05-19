package com.example.tryout_pas;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SportsDBApi {
    @GET("api/v1/json/3/search_all_teams.php?l=English%20Premier%20League\n")
    Call<TeamResponse> getTeams();
}
