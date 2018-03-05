package br.com.agilecontent.githubviewer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.transition.Transition;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.agilecontent.githubviewer.activitys.DetailsProfileActivity;
import br.com.agilecontent.githubviewer.activitys.HomeActivity;
import br.com.agilecontent.githubviewer.models.GitHubRepo;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pmoreirr on 04/03/2018.
 */

public class API {

    private String searchName;
    private Context context;
    private ArrayList<GitHubRepo> listRepositories = new ArrayList<GitHubRepo>();

    public API(Context context, String searchName){
        this.context = context;
        this.searchName = searchName;
        callApi();
    }

    private void callApi(){

        final ProgressDialog progress =
                ProgressDialog.show(context, "Loading","Searching user", true);
        progress.getWindow().setGravity(Gravity.CENTER);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        String API_BASE_URL = "https://api.github.com/";
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.client(httpClient.build()).build();
        GitHubClient client =  retrofit.create(GitHubClient.class);
        Call<List<GitHubRepo>> call = client.listaRepositorios(searchName);

        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                progress.dismiss();

                if(response.isSuccessful()){
                    listRepositories.addAll(response.body());
                    Intent intent = new Intent(context, DetailsProfileActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("listRepositories", listRepositories);
                    bundle.putString("name", searchName);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                } else if(response.message().equals("Not Found")){
                    ViewDialog alert = new ViewDialog();
                    alert.showDialog((Activity) context, "User not found.\n" +
                            "Please enter another name");
                }
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                progress.dismiss();

                ViewDialog alert = new ViewDialog();
                alert.showDialog((Activity) context, "A network error has occurred. Check your Internet connection and try again\n" +
                        "later.");
            }
        });
    }
}
