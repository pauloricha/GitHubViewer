package br.com.agilecontent.githubviewer.activitys;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.com.agilecontent.githubviewer.API;
import br.com.agilecontent.githubviewer.R;

public class HomeActivity extends AppCompatActivity {

    String searchName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);
        getSupportActionBar().setElevation(0);

        final EditText edt_Search = findViewById(R.id.edt_search);
        Button btn_Search = findViewById(R.id.btn_search);

        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edt_Search.getText().toString().trim().equalsIgnoreCase("")) {
                    edt_Search.setError("This field can not be blank");
                } else {
                    searchName = String.valueOf(edt_Search.getText());

                    new API(HomeActivity.this, searchName);

                    edt_Search.setText("");
                }
            }
        });
    }
}
