# segment-trees

A segment tree is nothing but a variation of binary tree which deals with ranges instead of single value.

When you need to query the array for a range and do a point update (single element update) multiple times , then solve it using segment tree. You can try the brute-force via iterating everything which will cost you O(n) TC. Segment trees allows you to do it via O(logn) TC.

* For point update (update one element in array), use normal version of segment tree
* For range update (update a range of elements with the given value) , use the lazy propagation verson of segment tree.
