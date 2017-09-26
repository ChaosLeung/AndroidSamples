/*
 * Copyright 2016 Chaos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gexne.bottombardemo;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gexne.bottombardemo.dummy.DummyContent;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import org.greenrobot.eventbus.EventBus;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Chaos
 *         7/11/16
 */
public class TestActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    private BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mBottomBar = (BottomBar) findViewById(R.id.bottom_bar);
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.nav_home:
                        switchPage(PAGE_HOME);
                        break;
                    case R.id.nav_rank:
                        switchPage(PAGE_RANK);
                        break;
                    case R.id.nav_hot_articles:
                        switchPage(PAGE_HOT_ARTICLES);
                        break;
                    case R.id.nav_hot_comments:
                        switchPage(PAGE_HOT_COMMENT);
                        break;
                }
            }
        });
    }

    private static final String TAG = "TestActivity";

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Log.d(TAG, "onListFragmentInteraction() called with: item = [" + item + "]");
    }

    @StringDef({PAGE_HOME, PAGE_RANK, PAGE_HOT_ARTICLES, PAGE_HOT_COMMENT})
    @Retention(RetentionPolicy.SOURCE)
    @interface Page {
    }

    private static final String PAGE_HOME = "HomeFragment";
    private static final String PAGE_RANK = "RankFragment";
    private static final String PAGE_HOT_ARTICLES = "HotArticlesFragment";
    private static final String PAGE_HOT_COMMENT = "HotCommentFragment";

    private String mCurrentPageTag;

    private void switchPage(@Page String pageTag) {
        if (pageTag.equals(mCurrentPageTag)) {
            return;
        }

        mCurrentPageTag = pageTag;
        Fragment fragment = null;
        fragment = ItemFragment.newInstance(1);
        EventBus.getDefault().register(fragment);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
        EventBus.getDefault().post("Test for page: " + pageTag);
    }
}
