package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite
{

  trait TestTrees
  {
    val t1 = Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5)
    val t2 = Fork(Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5), Leaf('d', 4), List('a', 'b', 'd'), 9)
    val t3 = Fork(Fork(Leaf('a', 2), Leaf('c', 10), List('a', 'c'), 12), Leaf('d', 4), List('a', 'c', 'd'), 16)
  }


  test("weight of a larger tree")
  {
    new TestTrees
    {
      assert(weight(t1) === 5)
      assert(weight(t3) === 16)
      assert(weight(makeCodeTree(t1, t2)) === 14)
      assert(weight(makeCodeTree(t1, t3)) === 21)
      assert(weight(makeCodeTree(t2, t3)) === 25)
    }
  }


  test("chars of a larger tree")
  {
    new TestTrees
    {
      assert(chars(t2) === List('a', 'b', 'd'))
      assert(chars(t3) === List('a', 'c', 'd'))
      assert(chars(makeCodeTree(t1, t2)) === List('a', 'b', 'a', 'b', 'd'))
      assert(chars(makeCodeTree(t1, t3)) === List('a', 'b', 'a', 'c', 'd'))
      assert(chars(makeCodeTree(t2, t3)) === List('a', 'b', 'd', 'a', 'c', 'd'))
    }
  }

  test("frequency pairs")
  {
    println(times("HelloWorld".toList))
  }

  test("string2chars(\"hello, world\")")
  {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }


  test("makeOrderedLeafList for some frequency table")
  {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 3)))
  }


  test("combine of some leaf list")
  {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e', 1), Leaf('t', 2), List('e', 't'), 3), Leaf('x', 4)))
  }


  test("decode and encode a very short text should be identity")
  {
    new TestTrees
    {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

}
