package com.vsharma.apps.twitterclient.fragments;

import java.util.ArrayList;
import java.util.List;

import com.vsharma.apps.twitterclient.ProfileActivity;
import com.vsharma.apps.twitterclient.R;
import com.vsharma.apps.twitterclient.TweetsAdapter;
import com.vsharma.apps.twitterclient.models.Tweet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;

public class TweetsListFragment extends Fragment implements OnScrollListener {

	List<Tweet> tweets = new ArrayList<Tweet>();
	TweetsAdapter tweetsAdapter;
	ListView lvTweets;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		lvTweets = (ListView) getActivity().findViewById(R.id.lvTweets);
		tweetsAdapter = new TweetsAdapter(getActivity(), tweets);
		lvTweets.setAdapter(tweetsAdapter);
		
		lvTweets.setOnScrollListener(this);
		
		lvTweets.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View parent, int position,
					long rowId) {
				Intent intent = new Intent(getActivity(), ProfileActivity.class);
				Tweet tweet = tweets.get(position);
				intent.putExtra("name", tweet.getUser().getName());
				intent.putExtra("tagline", tweet.getUser().getTagline());
				intent.putExtra("followers", ""+tweet.getUser().getFollowersCount());
				intent.putExtra("following", ""+tweet.getUser().getFriendsCount());
				intent.putExtra("image", tweet.getUser().getProfileImageUrl());
				intent.putExtra("id", tweet.getUser().getId());
				startActivity(intent);
			}
			
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragments_tweets_list, parent, false);
	}
	
	public TweetsAdapter getAdapter() {
		return tweetsAdapter;
	}
	
	public void load(boolean reload) {
		
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		
	}
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		
	}

}
