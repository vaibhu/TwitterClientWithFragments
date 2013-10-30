package com.vsharma.apps.twitterclient.fragments;

import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.vsharma.apps.twitterclient.ProfileActivity;
import com.vsharma.apps.twitterclient.TwitterClientApp;
import com.vsharma.apps.twitterclient.models.Tweet;

public class UserTimelineFragment extends TweetsListFragment {

	boolean isClear = false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		load(true);
	}

	public void load(boolean reload) {
		// TODO Auto-generated method stub
		isClear = reload;
		long id = ((ProfileActivity) this.getActivity()).getId();
		TwitterClientApp.getRestClient().getUserTimeline(id, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				Log.d("DEBUG", jsonTweets.toString());
				tweets = Tweet.fromJson(jsonTweets);
				if(isClear)
					tweetsAdapter.clear();
				tweetsAdapter.addAll(tweets);
				
//				if(tweets.size()>0) {
//					Tweet tweet = tweets.get(tweets.size()-1);
//					minId = tweet.getId();
//				}
//				isUpdating = false;
					
			}
		});
	}
}
