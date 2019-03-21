package ca.ualberta.jds1.lonelytwitter;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class LonelyTwitterActivity extends Activity {

	private EditText bodyText;
	private ListView oldTweetsList;

	private List<Tweet> tweets;
	private ArrayAdapter<Tweet> adapter;
	private TweetsFileManager tweetsProvider;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);
	}

	@Override
	protected void onStart() {
		super.onStart();

		tweetsProvider = new TweetsFileManager(this);
		tweets = tweetsProvider.loadTweets();
		adapter = new ArrayAdapter<Tweet>(this, R.layout.list_item,
				tweets);
		oldTweetsList.setAdapter(adapter);
	}

	public void save(View v) {
		String text = bodyText.getText().toString();
		Tweet tweet;
		if (text.startsWith("*")) {
			tweet = new ImportantTweet(text, new Date());
		} else {
			tweet = new NormalLonelyTweet(text, new Date());
		}


		//TODO: use different sub-classes (Normal or Important) based on usage of "*" in the text.
		
		if (tweet.isValidButIsNowANewMethodName()) {
			tweets.add(tweet);
			adapter.notifyDataSetChanged();

			bodyText.setText("");
			tweetsProvider.saveTweets(tweets);
		} else {
			Toast.makeText(this, "Invalid tweet", Toast.LENGTH_SHORT).show();
		}
	}

	public void clear(View v) {
		tweets.clear();
		adapter.notifyDataSetChanged();
		tweetsProvider.saveTweets(tweets);
	}

}
