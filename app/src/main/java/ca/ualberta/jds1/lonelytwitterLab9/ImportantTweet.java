package ca.ualberta.jds1.lonelytwitterLab9;

import java.io.Serializable;
import java.util.Date;

public class ImportantTweet extends Tweet implements Serializable {

        private static final long serialVersionUID = 1L;

        public ImportantTweet() {
        }

        public ImportantTweet(String text, Date date) {
            this.tweetDate = date;
            this.tweetBody = text;
        }

        public boolean isValidButIsNowANewMethodName() {
            if (getTweetBody().trim().length() == 0
                    || getTweetBody().trim().length() > 20) {
                return false;
            }

            return true;
        }

}

