package com.vsharma.apps.twitterclient;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.vsharma.apps.twitterclient.fragments.HomeTimelineFragment;
import com.vsharma.apps.twitterclient.fragments.MentionsFragment;
import com.vsharma.apps.twitterclient.fragments.TweetsListFragment;
import com.vsharma.apps.twitterclient.models.Tweet;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AbsListView;
import android.widget.ListView;

public class TimelineActivity extends FragmentActivity implements TabListener{

	//private TweetsListFragment tweetsListFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		
		//tweetsListFragment = (TweetsListFragment)getSupportFragmentManager().findFragmentById(R.id.frame_container);
		setUpNavigationTabs();
	}

	private void setUpNavigationTabs() {
		
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		
		Tab tabHome = actionBar.newTab().setText("Home").setTag("HomeTimelineFragment").setIcon(R.drawable.ic_home)
				.setTabListener(this);
		Tab tabMentions = actionBar.newTab().setText("Mentions").setTag("MentionsFragment").setIcon(R.drawable.ic_at)
				.setTabListener(this);
		
		actionBar.addTab(tabHome);
		actionBar.addTab(tabMentions);
		actionBar.selectTab(tabHome);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timeline, menu);
		return true;
	}

	public void composeTweet(MenuItem mi) {
		Intent intent = new Intent(getApplicationContext(), TweetActivity.class);
		startActivityForResult(intent, 100);
	}
	
	public void onProfileView(MenuItem mi) {
		Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
		startActivity(intent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d("DEBUG", "onActivityResult");
		if(requestCode == 100) {
			if(resultCode==RESULT_OK) {
//				tweetsListFragment.getAdapter().clear();
//				tweetsListFragment.load(true);
				Log.d("DEBUG", "onActivityResult-2");
			}
		}
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		if(tab.getTag() == "HomeTimelineFragment") {
			fragmentTransaction.replace(R.id.frame_container, new HomeTimelineFragment());
		} else {
			fragmentTransaction.replace(R.id.frame_container, new MentionsFragment());
		}
		fragmentTransaction.commit();
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
	}
}
