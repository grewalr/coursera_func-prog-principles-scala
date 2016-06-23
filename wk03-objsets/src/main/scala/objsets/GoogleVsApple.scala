package objsets

/**
  * Created by grewalr on 22/06/2016.
  */
object GoogleVsApple
{
	val google = List("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus")
	val apple = List("ios", "iOS", "iphone", "iPhone", "ipad", "iPad")

  lazy val googleTweets: TweetSet = filterTweets(google, TweetReader.allTweets)
  lazy val appleTweets: TweetSet = filterTweets(apple, TweetReader.allTweets)

	/**
		* A list of all tweets mentioning a keyword from either apple or google,
		* sorted by the number of retweets.
		*/
	lazy val trending: TweetList = (appleTweets union googleTweets).descendingByRetweet

  private def filterTweets(keywordSearch: List[String], allTweets: TweetSet): TweetSet =
    allTweets.filter(x => keywordSearch.exists(y => x.text.contains(y)))

}
