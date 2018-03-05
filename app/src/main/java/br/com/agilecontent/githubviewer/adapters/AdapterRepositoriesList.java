package br.com.agilecontent.githubviewer.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

import br.com.agilecontent.githubviewer.R;
import br.com.agilecontent.githubviewer.models.GitHubRepo;

/**
 * Created by pmoreirr on 04/03/2018.
 */

public class AdapterRepositoriesList extends BaseAdapter {

    private final Context context;
    private ArrayList<GitHubRepo> values;

    public AdapterRepositoriesList(Context context, ArrayList<GitHubRepo> values) {
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.layout_list_repositories, null);

        TextView txt_title = view.findViewById(R.id.txt_title);
        TextView txt_subtitle = view.findViewById(R.id.txt_subtitle);

        GitHubRepo data = values.get(position);
        String name = capitalize(data.getName());

        txt_title.setText(name);
        txt_subtitle.setText(data.getLanguage());

        return view;
    }

    private String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
}
