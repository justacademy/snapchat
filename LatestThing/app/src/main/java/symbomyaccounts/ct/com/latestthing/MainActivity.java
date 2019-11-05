package symbomyaccounts.ct.com.latestthing;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button languagebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
        languagebtn = (Button) findViewById(R.id.languagebtn);
        languagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showlanguagedialog();
            }
        });
    }
    private void showlanguagedialog(){
        final  String[] listitem = {"हिंदी","French","English"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose Language");
        builder.setSingleChoiceItems(listitem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0){
                    setLocale("hi");
                    recreate();
                }

                if(which==1){
                    setLocale("fr");
                    recreate();
                }

                if(which==2){
                    setLocale("en");
                    recreate();
                }

                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale =locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Setting",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();

    }
    public  void  loadLocale(){
        SharedPreferences preference = getSharedPreferences("Setting", Activity.MODE_PRIVATE);
        String language = ((SharedPreferences) preference).getString("My_Lang","");
        setLocale(language);

    }
}
