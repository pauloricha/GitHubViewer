package br.com.agilecontent.githubviewer.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pmoreirr on 04/03/2018.
 */

public class GitHubRepo implements Parcelable {

    private String name;
    private String language;

    public GitHubRepo() {
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int arg1) {
        // TODO Auto-generated method stub
        dest.writeString(name);
        dest.writeString(language);
    }

    public GitHubRepo(Parcel in) {
        name = in.readString();
        language = in.readString();
    }

    public static final Parcelable.Creator<GitHubRepo> CREATOR = new Parcelable.Creator<GitHubRepo>() {
        public GitHubRepo createFromParcel(Parcel in) {
            return new GitHubRepo(in);
        }

        public GitHubRepo[] newArray(int size) {
            return new GitHubRepo[size];
        }
    };
}
