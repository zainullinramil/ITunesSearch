package com.zainullinramil.itunessearch;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by user on 30.03.2016.
 */
//адаптер списка позизий
public class ItemListAdapter extends ArrayAdapter<ItemSearch> {

    public static String LOG_TAG = "my_log";
    Context ctx;
    LayoutInflater lInflater;
    private LruCache<String, Bitmap> mMemoryCache;


    public ItemListAdapter(Context context) {
        super(context, R.layout.itemsearch);
        ctx = context;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.d(LOG_TAG, "ItemAdapter create");

        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 4;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };

    }

    //передаем данные
    public void setData(ArrayList<ItemSearch> data) {
        clear();
        if (data != null) {
            for (ItemSearch appEntry : data) {
                add(appEntry);
            }
        }
    }
    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        Log.d(LOG_TAG, "View getView" + position);
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.itemsearch, parent, false);
        }

        ItemSearch items = getISearch(position);
        ImageView imageView = (ImageView) view.findViewById(R.id.ivArtwork);

        ((TextView) view.findViewById(R.id.tvArtName)).setText(items.getArtistName());
        ((TextView) view.findViewById(R.id.tvCollName)).setText(items.getCollectionName());
        ((TextView) view.findViewById(R.id.tvTrName)).setText(items.getTrackName());
        Picasso.with(ctx).load(items.getArtworkUrl100()).placeholder(R.drawable.blue_png).error(R.drawable.ic_image_notfound).into(imageView);

        return view;
    }

    // объект по позиции
    ItemSearch getISearch(int position) {
        return ((ItemSearch) getItem(position));
    }
}
