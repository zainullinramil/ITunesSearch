package com.zainullinramil.itunessearch;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;
import android.widget.SearchView;

/**
 * Created by user on 31.03.2016.
 */
public class SearchActivityTest extends ActivityInstrumentationTestCase2<SearchActivity> {
    private SearchActivity sActivity;

    public SearchActivityTest(Class<SearchActivity> activityClass) {
        super(activityClass);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        sActivity = getActivity();

    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testEmtyText() throws Exception {
        assertNotNull(sActivity);

    }
}
