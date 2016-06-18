package funsets

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

/**
  * This class is a test suite for the methods in object FunSets. To run
  * the test suite, you can either:
  * - run the "test" command in the SBT console
  * - right-click the file in eclipse and chose "Run As" - "JUnit Test"
  */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite
{

  /**
    * Link to the scaladoc - very clear and detailed tutorial of FunSuite
    *
    * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
    *
    * Operators
    * - test
    * - ignore
    * - pending
    */

  /**
    * Tests are written using the "test" operator and the "assert" method.
    */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
    * For ScalaTest tests, there exists a special equality operator "===" that
    * can be used inside "assert". If the assertion fails, the two values will
    * be printed in the error message. Otherwise, when using "==", the test
    * error message will only say "assertion failed", without showing the values.
    *
    * Try it out! Change the values so that the assertion fails, and look at the
    * error message.
    */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented")
  {
    assert(contains(x => true, 100))
  }

  /**
    * When writing tests, one would often like to re-use certain values for multiple
    * tests. For instance, we would like to create an Int-set and have multiple test
    * about it.
    *
    * Instead of copy-pasting the code for creating the set into every test, we can
    * store it in the test class using a val:
    *
    * val s1 = singletonSet(1)
    *
    * However, what happens if the method "singletonSet" has a bug and crashes? Then
    * the test methods are not even executed, because creating an instance of the
    * test class fails!
    *
    * Therefore, we put the shared values into a separate trait (traits are like
    * abstract classes), and create an instance inside each test method.
    *
    */

  trait TestSets
  {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s11 = singletonSet(11)

    val s1000 = singletonSet(1000)
    val s_1000 = singletonSet(-1000)

    val s1001 = singletonSet(1001)
    val s_1001 = singletonSet(-1001)
  }

  /**
    * This test is currently disabled (by using "ignore") because the method
    * "singletonSet" is not yet implemented and the test would fail.
    *
    * Once you finish your implementation of "singletonSet", exchange the
    * function "ignore" by "test".
    */
  test("singletonSet(1) contains 1")
  {

    /**
      * We create a new instance of the "TestSets" trait, this gives us access
      * to the values "s1" to "s3".
      */
    new TestSets
    {
      /**
        * The string argument of "assert" is a message that is printed in case
        * the test fails. This helps identifying which assertion failed.
        */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set")
  {
    new TestSets
    {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("test for all")
  {
    new TestSets
    {
      val f1 = (x: Int) => x < bound
      val f2 = (x: Int) => x > bound
      val f3 = (x: Int) => x > -bound
      val f4 = (x: Int) => x < -bound

      val f5 = (x: Int) => x < 10

      assert(forall(s1, f1) === true, "For all 1 less than upper bound")
      assert(forall(s1, f2) === false, "For all 1 not greater than upper bound")
      assert(forall(s1, f3) === true, "For all 1 greater than lower bound")
      assert(forall(s1, f4) === false, "For all 1 not less than lower bound")

      assert(forall(s2, f1) === true, "For all 2 less than upper bound")
      assert(forall(s2, f2) === false, "For all 2 not greater than upper bound")
      assert(forall(s2, f3) === true, "For all 2 greater than lower bound")
      assert(forall(s2, f4) === false, "For all 2 not less than lower bound")

      assert(forall(s3, f1) === true, "For all 3 less than upper bound")
      assert(forall(s3, f2) === false, "For all 3 not greater than upper bound")
      assert(forall(s3, f3) === true, "For all 3 greater than lower bound")
      assert(forall(s3, f4) === false, "For all 3 not less than lower bound")

      assert(forall(s1, f5) === true, "For all 1 less than 10")
      assert(forall(s2, f5) === true, "For all 2 less than 10")
      assert(forall(s2, f5) === true, "For all 3 less than 10")
    }
  }


//  test("Forall")
//  {
//    new TestSets
//    {
//      val p1 = (x: Int) => x > 0
//      val p2 = (x: Int) => x > -bound
//      val p3 = (x: Int) => x >= -bound
//
//      val s = (x: Int) => true
//      assert(!forall(s, p1), "forall negative value")
//      assert(!forall(s, p2), "forall boundaries")
//      assert(forall(s1, p1), "forall subset")
//      assert(forall(s, p3), "forall all")
//    }
//  }


}
