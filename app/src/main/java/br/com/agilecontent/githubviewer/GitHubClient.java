package br.com.agilecontent.githubviewer;

import java.util.List;

import br.com.agilecontent.githubviewer.models.GitHubRepo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by pmoreirr on 04/03/2018.
 */

public interface GitHubClient {
    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> listaRepositorios(
            @Path("user") String user
    );
}
