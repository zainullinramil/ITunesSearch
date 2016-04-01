package com.zainullinramil.itunessearch;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;


import java.util.ArrayList;


public class ItemListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<ArrayList<ItemSearch>> {
    public static String LOG_TAG = "my_log";
    private ItemListAdapter itemListAdapter;
    String searchText;


    public ItemListFragment() {

        // Required empty public constructor
    }

    public void setSearchText(String searchText){
        this.searchText = searchText;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d(LOG_TAG, "onActivityCreated");

        setEmptyText(this.getString(R.string.nodata_text));

        savedInstanceState = getArguments();

        if (savedInstanceState != null) {
            searchText = savedInstanceState.getString("stext");
        }


        itemListAdapter = new ItemListAdapter(getActivity());
        setListAdapter(itemListAdapter);


        setListShown(false);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<ArrayList<ItemSearch>> onCreateLoader(int id, Bundle args) {
        Log.d(LOG_TAG, "DataListLoader");
        return new DataListLoader(getActivity(), searchText);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<ItemSearch>> loader, ArrayList<ItemSearch> data) {

        Log.d(LOG_TAG, "onLoadFinished");
        
        itemListAdapter.setData(data);

        if (isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<ItemSearch>> loader) {

    }
}
