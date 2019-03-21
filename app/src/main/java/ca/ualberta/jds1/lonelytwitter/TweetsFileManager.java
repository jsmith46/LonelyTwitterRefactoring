package ca.ualberta.jds1.lonelytwitter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

public class TweetsFileManager {

	public static final String LONELY_TWITTER = "LonelyTwitter";
	public static final String FILE_SAV = "file.sav";
	private Context ctx;

	public TweetsFileManager(Context ctx) {
		this.ctx = ctx;
	}

	@SuppressWarnings("unchecked")
	public List<Tweet> loadTweets() {
		List<Tweet> tweets = new ArrayList<Tweet>();

		try {
			FileInputStream fis = ctx.openFileInput(FILE_SAV);
			ObjectInputStream ois = new ObjectInputStream(fis);

			Object o = ois.readObject();

			if (o instanceof ArrayList) {
				tweets = (ArrayList<Tweet>) o;
			} else {
				Log.i(LONELY_TWITTER, "Error casting");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return tweets;
	}

	public void saveTweets(List<Tweet> tweets) {
		try {
			FileOutputStream fos = ctx.openFileOutput("file.sav", 0);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(tweets);

			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}