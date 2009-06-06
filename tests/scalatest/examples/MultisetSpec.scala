package scalatest.examples

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

import scala.collection.immutable.HashMultiset

/**
 * Test description of the behavior expected by a multiset. 
 */
class MultisetSpec extends Spec with ShouldMatchers  {

  describe("A Multiset") {
    
    it("should have no element if empty.") {
      val empty = HashMultiset.empty[Int]
      
      empty should have size 0
      empty should be ('empty)
      assert(!empty.elements.hasNext)
      empty(0) should be (0)
      empty should not contain (0)
    }
    
    it("should increase the cardinality of identical elements.") {
      val mset = HashMultiset(1,1,2)
      
      mset should not be ('empty)
      mset should have size 3
      mset(1) should be (2)
      mset(2) should be (1)
      mset(3) should be (0)
    }
    
    it("should implement structural equality when comparing two multisets.") {
      val empty1 = HashMultiset.empty[Int]
      val empty2 = HashMultiset.empty[Int]
      
      val filled1 = HashMultiset(1,1,2)
      val filled2 = HashMultiset.empty[Int] + (1,1,2)
      
      empty1 should equal (empty2)
      filled1 should equal (filled2)
    }
    
    it("should held be empty if intersected with an empty multiset") {
      val mset = HashMultiset(1,1,2)
      val empty = HashMultiset.empty[Int]
      
      
      (empty ** empty) should equal (empty)
      (empty intersect empty) should equal (empty)
      
      (empty ** mset) should equal (empty)
      (empty intersect mset) should equal (empty)
      
      (mset ** empty) should equal (empty)
      (mset intersect empty) should equal (empty)
    }
    
    
  }
}