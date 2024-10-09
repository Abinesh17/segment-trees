# Segment Trees

A segment tree is nothing but a variation of binary tree which deals with ranges instead of single value.

When you need to query the array for a range and do a point update (single element update) multiple times , then solve it using segment tree. You can try the brute-force via iterating everything which will cost you O(n) TC. Segment trees allows you to do it via O(logn) TC.

* For point update (update one element in array), use normal version of segment tree
* For range update (update a range of elements with the given value) , use the lazy propagation verson of segment tree.

## Why initialize with 4*n?
https://stackoverflow.com/questions/28470692/how-is-the-memory-of-the-array-of-segment-tree-2-2-ceillogn-1

## Time and Space Complexity
TC: O(logn)
SC: O(n)


## Resources:
https://www.youtube.com/watch?v=-dUiRtJ8ot0&list=PLgUwDviBIf0rf5CQf_HFt35_cF04d8dHN&index=3
https://www.geeksforgeeks.org/introduction-to-segment-trees-2/
