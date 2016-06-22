package objsets

/**
  * Created by grewalr on 22/06/2016.
  */
object GoogleVsApple
{
	val google = List("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus")
	val apple = List("ios", "iOS", "iphone", "iPhone", "ipad", "iPad")

	lazy val googleTweets: TweetSet = ???
	lazy val appleTweets: TweetSet = ???

	/**
		* A list of all tweets mentioning a keyword from either apple or google,
		* sorted by the number of retweets.
		*/
	lazy val trending: TweetList = ???
}
