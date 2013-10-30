package com.vsharma.apps.twitterclient;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.FlickrApi;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
    public static final String REST_URL = "https://api.twitter.com/1.1"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "P8Ae0pVZuVbNglOEduB0A";       // Change this
    public static final String REST_CONSUMER_SECRET = "YDkhOQ1UXAqMHDLe2iT6TMlkRQKReAXreRl2zdyko3U"; // Change this
    public static final String REST_CALLBACK_URL = "oauth://mytwitterapp"; // Change this (here and in manifest)
    
    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }
    
    public void getHomeTimeline(long minId, AsyncHttpResponseHandler responseHandler) {
    	String url = getApiUrl("statuses/home_timeline.json");
    	RequestParams requestParams = new RequestParams();
    	requestParams.put("max_id", minId+"");
    	if(minId>0)
    		client.get(url, requestParams, responseHandler);
    	else
    		client.get(url, null, responseHandler);
    }
    
    public void getMentions(long minId, AsyncHttpResponseHandler responseHandler) {
    	String url = getApiUrl("statuses/mentions_timeline.json");
    	RequestParams requestParams = new RequestParams();
    	requestParams.put("max_id", minId+"");
    	if(minId>0)
    		client.get(url, requestParams, responseHandler);
    	else
    		client.get(url, null, responseHandler);
    }
    
    public void getUserTimeline(long userId, AsyncHttpResponseHandler responseHandler) {
    	String url = getApiUrl("statuses/user_timeline.json");
    	RequestParams requestParams = new RequestParams();
    	requestParams.put("user_id", userId+"");
    	if(userId>0)
    		client.get(url, requestParams, responseHandler);
    	else
    		client.get(url, null, responseHandler);
    }
    
    public void getLoggedInUserInfo(AsyncHttpResponseHandler responseHandler) {
    	String url = getApiUrl("account/verify_credentials.json");
    	client.get(url, null, responseHandler);
    }
    
    public void postTweet(String status, AsyncHttpResponseHandler responseHandler) {
    	Log.d("DEBUG", "tweet!!!!");
    	String url = getApiUrl("statuses/update.json");
    	RequestParams requestParams = new RequestParams();
    	requestParams.put("status", status);
    	client.post(url, requestParams, responseHandler);
    }
    
    // CHANGE THIS
    // DEFINE METHODS for different API endpoints here
    public void getInterestingnessList(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("?nojsoncallback=1&method=flickr.interestingness.getList");
        // Can specify query string params directly or through RequestParams.
        RequestParams params = new RequestParams();
        params.put("format", "json");
        client.get(apiUrl, params, handler);
    }
    
    /* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
     * 	  i.e getApiUrl("statuses/home_timeline.json");
     * 2. Define the parameters to pass to the request (query or body)
     *    i.e RequestParams params = new RequestParams("foo", "bar");
     * 3. Define the request method and make a call to the client
     *    i.e client.get(apiUrl, params, handler);
     *    i.e client.post(apiUrl, params, handler);
     */
}