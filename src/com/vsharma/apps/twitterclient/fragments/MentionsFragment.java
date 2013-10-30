package com.vsharma.apps.twitterclient.fragments;

import org.json.JSONArray;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.vsharma.apps.twitterclient.TwitterClientApp;
import com.vsharma.apps.twitterclient.models.Tweet;

import android.os.Bundle;
import android.util.Log;

public class MentionsFragment extends TweetsListFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		load(true);
	}

	public void load(boolean reload) {
		// TODO Auto-generated method stub
		TwitterClientApp.getRestClient().getMentions(0, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				Log.d("DEBUG", jsonTweets.toString());
				tweets = Tweet.fromJson(jsonTweets);
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
