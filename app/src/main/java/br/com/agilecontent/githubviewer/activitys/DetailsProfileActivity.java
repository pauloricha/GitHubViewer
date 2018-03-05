package br.com.agilecontent.githubviewer.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.agilecontent.githubviewer.adapters.AdapterRepositoriesList;
import br.com.agilecontent.githubviewer.R;
import br.com.agilecontent.githubviewer.models.GitHubRepo;

public class DetailsProfileActivity extends AppCompatActivity {

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_profile);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        TextView textView = findViewById(R.id.txt_name);
        ListView listViewRepositories = findViewById(R.id.lv_repositories);

        Bundle bundle = getIntent().getExtras();
        ArrayList<GitHubRepo> arraylist = bundle.getParcelableArrayList("listRepositories");

        name = bundle.getString("name");
        textView.setText(name);

        AdapterRepositoriesList adapterRepositoriesList = new AdapterRepositoriesList(getApplicationContext(), arraylist);
        listViewRepositories.setAdapter(adapterRepositoriesList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
