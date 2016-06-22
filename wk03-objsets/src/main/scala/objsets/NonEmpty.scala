package objsets

/**
  * Created by grewalr on 22/06/2016.
  */
class NonEmpty(elem: Tweet, left: TweetSet, right: TweetSet) extends TweetSet
{

	def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet =
		if(p(elem)) left.filterAcc(p, right.filterAcc(p, acc.incl(elem)))
		else left.filterAcc(p, right.filterAcc(p, acc))

	/**
		* The following methods are already implemented
		*/

	def contains(x: Tweet): Boolean =
		if(x.text < elem.text) left.contains(x)
		else if(elem.text < x.text) right.contains(x)
		else true

	def incl(x: Tweet): TweetSet =
	{
		if(x.text < elem.text) new NonEmpty(elem, left.incl(x), right)
		else if(elem.text < x.text) new NonEmpty(elem, left, right.incl(x))
		else this
	}

	def remove(tw: Tweet): TweetSet =
		if(tw.text < elem.text) new NonEmpty(elem, left.remove(tw), right)
		else if(elem.text < tw.text) new NonEmpty(elem, left, right.remove(tw))
		else left.union(right)

	def foreach(f: Tweet => Unit): Unit =
	{
		f(elem)
		left.foreach(f)
		right.foreach(f)
	}

  override def union(that: TweetSet): TweetSet = ((left union right) union that) incl elem

  override def isEmpty = false

  override def mostRetweeted: Tweet =
  {
    if (left.isEmpty && right.isEmpty) elem
    else if (left.isEmpty && !right.isEmpty) maxRetweets(right.mostRetweeted)
    else if (!left.isEmpty && right.isEmpty) maxRetweets(left.mostRetweeted)
    else maxRetweets(elem)
  }

  private def maxRetweets(maxElem: Tweet): Tweet = if (maxElem.retweets > elem.retweets) maxElem else elem
}
