package ca.ualberta.jds1.lonelytwitter;

import java.io.Serializable;
import java.util.Date;

public class NormalLonelyTweet extends Tweet implements Serializable {

	private static final long serialVersionUID = 1L;

	public NormalLonelyTweet() {
	}

	public NormalLonelyTweet(String text, Date date) {
		this.tweetDate = date;
		this.tweetBody = text;
	}

	public boolean isValidButIsNowANewMethodName() {
		if (getTweetBody().trim().length() == 0
				|| getTweetBody().trim().length() > 10) {
			return false;
		}

		return true;
	}

}