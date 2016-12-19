package com.example.user.foodtracker;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;

/**
 * Created by user on 18/12/2016.
 */

public class SearchableActivity extends AppCompatActivity {

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.search_view, menu);
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
//        if (searchView != null) {
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
//                @Override
//                public boolean onClose() {
//                    return false;
//                }
//            });
//            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                @Override
//                public boolean onQueryTextSubmit(String s) {
//                    return false;
//                }
//                @Override
//                public boolean onQueryTextChange(String s) {
//                    if (s.length() > 1) {
//                    }else if (s.length() == 0) {
//                    }
//                    return false;
//                }
//            });
//        }
//        return true;
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            final String query = intent.getStringExtra(SearchManager.QUERY);
////            doMySearch(query);
//        }
//    }
}
