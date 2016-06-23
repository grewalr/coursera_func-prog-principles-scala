package objsets

/**
  * Created by grewalr on 22/06/2016.
  */
class Empty extends TweetSet
{
	def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = acc

	/**
		* The following methods are already implemented
		*/
	def contains(tweet: Tweet): Boolean = false

	def incl(tweet: Tweet): TweetSet = new NonEmpty(tweet, new Empty, new Empty)

	def remove(tweet: Tweet): TweetSet = this

	def foreach(f: Tweet => Unit): Unit = ()

  override def union(that: TweetSet): TweetSet = that

  override def mostRetweeted: Tweet = throw new java.util.NoSuchElementException

  override def isEmpty = true
}
