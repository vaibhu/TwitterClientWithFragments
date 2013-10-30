package com.vsharma.apps.twitterclient;

import org.json.JSONArray;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.vsharma.apps.twitterclient.fragments.UserTimelineFragment;
import com.vsharma.apps.twitterclient.models.Tweet;
import com.vsharma.apps.twitterclient.models.User;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends FragmentActivity {

	TextView tvName;
	TextView tvTagline;
	TextView tvFollowers;
	TextView tvFollowing;
	ImageView ivProfileImage;
	long id=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		tvName = (TextView) findViewById(R.id.tvName);
		tvTagline = (TextView) findViewById(R.id.tvTagline);
		tvFollowers = (TextView) findViewById(R.id.tvFollowers);
		tvFollowing = (TextView) findViewById(R.id.tvFollowing);
		ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
		
		
		String name = getIntent().getStringExtra("name");
		String tagline = getIntent().getStringExtra("tagline");
		String followers = getIntent().getStringExtra("followers");
		String following = getIntent().getStringExtra("following");
		String image = getIntent().getStringExtra("image");
		
		id = getIntent().getLongExtra("id", 0);
		Log.d("DEBUG", "set id>>>>>"+id);
		if(name==null)
			load();
		else {
			tvName.setText(name);
			tvTagline.setText(tagline);
			tvFollowers.setText(followers + " Followers");
			tvFollowing.setText(following + " Following");
			ImageLoader.getInstance().displayImage(image, ivProfileImage);
			
			UserTimelineFragment userTimelineFragment = (UserTimelineFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentUserTimeline);
			userTimelineFragment.load(true);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		
		return true;
	}

	public void load() {
		// TODO Auto-generated method stub
		
		TwitterClientApp.getRestClient().getLoggedInUserInfo( new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject jsonObject) {
				Log.d("DEBUG", jsonObject.toString());
				User user = User.fromJson(jsonObject);
				getActionBar().setTitle("@"+user.getScreenName());
				populateProfileHeader(user);
			}

			
		});
	}
	
	private void populateProfileHeader(User user) {
		tvName.setText(user.getName());
		tvTagline.setText(user.getTagline());
		tvFollowers.setText(user.getFriendsCount() + " Followers");
		tvFollowing.setText(user.getFriendsCount() + " Following");
		ImageLoader.getInstance().displayImage(user.getProfileImageUrl(), ivProfileImage);
	}
	
	public long getId() {
		Log.d("DEBUG", "get id>>>>>"+id);
		return id;
	}
}
