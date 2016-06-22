package objsets

/**
  * Created by grewalr on 22/06/2016.
  */
trait TweetList
{
	def head: Tweet

	def tail: TweetList

	def isEmpty: Boolean

	def foreach(f: Tweet => Unit): Unit =
		if(!isEmpty)
		{
			f(head)
			tail.foreach(f)
		}
}

object Nil extends TweetList
{
	def head = throw new java.util.NoSuchElementException("head of EmptyList")

	def tail = throw new java.util.NoSuchElementException("tail of EmptyList")

	def isEmpty = true
}

class Cons(val head: Tweet, val tail: TweetList) extends TweetList
{
	def isEmpty = false
}
