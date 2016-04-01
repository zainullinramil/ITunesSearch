package com.zainullinramil.itunessearch;

import android.content.Context;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Created by user on 01.04.2016.
 */
@RunWith(AndroidJUnit4.class)
public class DataListLoaderTest extends Assert {
    DataListLoader dataListLoader;
    private Context view;

    @Before
    public void setUp() throws Exception {
        dataListLoader = new DataListLoader(view, "Beatles");
    }

    @After
    public void tearDown() throws Exception {
        dataListLoader = null;
    }

    @Test
    public void testDownJson() throws Exception {
        assertNotNull(dataListLoader);

    }
}
