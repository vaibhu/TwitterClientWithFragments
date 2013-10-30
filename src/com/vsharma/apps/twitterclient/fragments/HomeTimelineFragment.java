package com.vsharma.apps.twitterclient.fragments;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.vsharma.apps.twitterclient.R;
import com.vsharma.apps.twitterclient.TweetsAdapter;
import com.vsharma.apps.twitterclient.TwitterClientApp;
import com.vsharma.apps.twitterclient.models.Tweet;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

public class HomeTimelineFragment extends TweetsListFragment  {

	private boolean isUpdating = false;
	private long  minId=0;
	private List<Tweet> tweets = new ArrayList<Tweet>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		load(true);
	}
	
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		if(isUpdating || totalItemCount == 0)
			return;
		if((totalItemCount - firstVisibleItem)<8)
			load(false);
	}
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Log.d("DEBUG", "On Pause ");
	}
	@Override
	public void onResume() {
		super.onResume();
		Log.d("DEBUG", "On Resume ");
		tweetsAdapter.clear();
		load(true);
	}
	public void load(boolean reload) {
		if(reload)
			minId = 0;
		isUpdating = true;
		Log.d("DEBUG", "MINID "+minId);
		TwitterClientApp.getRestClient().getHomeTimeline(minId, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				Log.d("DEBUG", jsonTweets.toString());
				tweets = Tweet.fromJson(jsonTweets);
				tweetsAdapter.addAll(tweets);
				
				if(tweets.size()>0) {
					Tweet tweet = tweets.get(tweets.size()-1);
					minId = tweet.getId();
				}
				isUpdating = false;
					
			}
		});
	}
}
