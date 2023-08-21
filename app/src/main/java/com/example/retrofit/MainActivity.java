package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    JSONPlaceHolderApi api;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.txt);
        //int num = getIntent().getExtras().getInt("number");
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(JSONPlaceHolderApi.class);

        /*Map<String,String> parameters = new HashMap<>();
        parameters.put("userId","1");
        parameters.put("_sort","id");
        parameters.put("_order","desc");*/

        //Get_Posts(null,null,1,2,6);
        //Get_Comments(num);
        //Add_Post(23,"Android development","this is my android development story ...");
        //Put_Post(20,"New text");
        //Patch_Post(20,"New text 2");
        Delete_Post(2);
    }

    private void Get_Posts(String sort,String order,Integer... userID){
        Call<List<Posts>> call = api.Get_Posts(sort,order,userID);

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if(!response.isSuccessful()) {
                    txt.setText("Code : " + response.code());
                    return;
                } else {
                    List<Posts> posts = response.body();
                    for(Posts p : posts) {
                        String Content = "";
                        Content += "ID : " + p.getID()+"\n";
                        Content += "User ID : " + p.getUser_ID()+"\n";
                        Content += "Body : " + p.getText()+"\n";
                        Content += "------------------------------------\n";
                        txt.append(Content);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                txt.setText(t.getMessage());
            }
        });
    }

    private void Get_Comments(int Post_ID){
        Call<List<Comment>> call = api.Get_Comment(Post_ID);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful()) {
                    txt.setText("Code : "+response.code());
                } else {
                    List<Comment> comments = response.body();
                    for(Comment comment:comments) {
                        String Content = "";
                        Content += "ID : " + comment.getId()+"\n";
                        Content += "Post ID : " + comment.getPostId()+"\n";
                        Content += "Name : " + comment.getName()+"\n";
                        Content += "E-mail : " + comment.getEmail()+"\n";
                        Content += "Body : " + comment.getBody() + "\n";
                        Content += "-------------------------------------\n";
                        txt.append(Content);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                txt.setText(t.getMessage());
            }
        });
    }

    private void Add_Post(int user_ID,String title,String text){
        Posts posts = new Posts(user_ID,title,text);
        Call<Posts> call = api.Create_Post(/*posts*/user_ID,title,text);
        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                if(!response.isSuccessful()) {
                    txt.setText("Code : " + response.code());
                } else {
                    Posts post = response.body();
                    String content = "";
                    content += "Code : " + response.code()+"\n";
                    content += "ID : " + post.getID()+"\n";
                    content += "User ID : " + post.getUser_ID()+"\n";
                    content += "Title : " + post.getTitle()+"\n";
                    content += "Text : " + post.getText()+"\n";
                    txt.setText(content);
                }
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                txt.setText(t.getMessage());
            }
        });
    }

    private void Put_Post(int user_ID,String text) {
        Posts posts = new Posts(user_ID,text);
        Call<Posts> call = api.Put_Post(2,posts);
        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                Posts post = response.body();
                if(!response.isSuccessful()) {
                    txt.setText("Code : " + response.code());
                } else {
                    String content = "";
                    content += "Code : " + response.code()+"\n";
                    content += "ID : " + post.getID()+"\n";
                    content += "user ID : " + post.getUser_ID()+"\n";
                    content += "title : " + post.getTitle()+"\n";
                    content += "text : " + post.getText()+"\n";
                    txt.setText(content);
                }
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                txt.setText(t.getMessage());
            }
        });
    }

    private void Patch_Post(int user_id,String text) {
        Posts posts = new Posts(user_id,text);
        Call<Posts> call = api.Patch_Post(2,posts);
        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                Posts post = response.body();
                if(!response.isSuccessful()) {
                    txt.setText("Code : " + response.code());
                } else {
                    String content = "";
                    content += "Code : " + response.code()+"\n";
                    content += "ID : " + post.getID()+"\n";
                    content += "user ID : " + post.getUser_ID()+"\n";
                    content += "title : " + post.getTitle()+"\n";
                    content += "text : " + post.getText()+"\n";
                    txt.setText(content);
                }
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                txt.setText(t.getMessage());
            }
        });
    }

    private void Delete_Post(int id) {
        Call<Void> call = api.Delete_Post(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                txt.setText("Code : "+ response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                txt.setText(t.getMessage());
            }
        });
    }
}