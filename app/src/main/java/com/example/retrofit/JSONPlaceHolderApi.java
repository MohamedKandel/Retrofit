package com.example.retrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.*;


public interface JSONPlaceHolderApi {
    //@GET("posts")
    //Call<List<Posts>> Get_Posts();

    @GET("posts/{id}/comments")     //if we need to fetch data by user choice
    Call<List<Comment>> Get_Comment(@Path("id") int postID);

    @GET("posts")       //if we have ? in relative URL we use QUERY annotation
    Call<List<Posts>> Get_Posts(@Query("userId") int user_ID);

    @GET("posts")
    Call<List<Posts>> Get_Posts(@Query("_sort") String sort,
                                @Query("_order") String order,
                                @Query("userId") Integer... user_ID);

    @GET("/posts")      //this / will remove the last / from baseUrl with its name
    Call<List<Posts>> Get_Posts(@QueryMap Map<String,String> parameters);

    @POST("posts")
    Call<Posts> Create_Post(@Body Posts posts);

    @FormUrlEncoded         //if we need to enter inputs as separated fields we will use this annotation and @Field annotation to define this is an input
    @POST("posts")
    Call<Posts> Create_Post(@Field("userId") int user_ID,
                            @Field("title") String title,
                            @Field("body") String text);

    //we can use @FieldMap annotation instead of @Field annotation and pass Map in parameter as @QueryMap annotation

    /*
    difference between PUT and PATCH :-
    -----------------------------------
    these two methods used to update and exist data but put replace all data in this place with new data
    (if database have 4 columns and we used put, we need to pass 4 parameters if we don't put will update some data and set another with null)
    patch update data with given parameters only
    (if database have 4 columns and we used put, and we pass 2 parameters only it will update these two parameters and stay another with their values)
    */
    @PUT("posts/{id}")
    Call<Posts> Put_Post(@Path("id") int id,@Body Posts posts);

    @PATCH("posts/{id}")
    Call<Posts> Patch_Post(@Path("id") int id,@Body Posts posts);

    @DELETE("posts/{id}")
    Call<Void> Delete_Post(@Path("id") int id);     //we use void because Delete will return nothing

}
