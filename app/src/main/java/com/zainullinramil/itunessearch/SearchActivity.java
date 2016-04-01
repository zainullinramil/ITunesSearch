package com.zainullinramil.itunessearch;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;
import com.zainullinramil.itunessearch.ItemListFragment;


public class SearchActivity extends AppCompatActivity {
    public static String LOG_TAG = "my_log";
    ItemListFragment itemListFragment;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        if (isOnline()) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    FragmentManager fm = getSupportFragmentManager();

                    if (fm.findFragmentById(android.R.id.content) == null) {
                        String stext = query.replace( " ", "+" );
                        Bundle bundle = new Bundle();
                        bundle.putString("stext", stext);
                        itemListFragment = new ItemListFragment();
                        itemListFragment.setArguments(bundle);
                        fm.beginTransaction().replace(R.id.conteiner, itemListFragment).commit();
                        return true;
                    }else {
                        return false;
                    }
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        } else {
            Toast.makeText(this, "No connection", Toast.LENGTH_LONG).show();

        }
        return true;
    }

    //Проверка соединения
    protected boolean isOnline() {
        String cs = Context.CONNECTIVITY_SERVICE;
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(cs);
        if (cm.getActiveNetworkInfo() == null) {
            return false;
        } else {
            return true;
        }
    }
}
