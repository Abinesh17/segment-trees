/*
author: abinesh-b
*/
public class SegmentTree {

    private int[] seg;
    int n;
    public SegmentTree (int[] arr)
    {
        n = arr.length;
        seg = new int[4*n];
        buildTree(arr);
    }

    private void buildTree(int[] arr)
    {
        build(0, 0, n-1, arr);
    }

    private void build(int index, int low, int high, int[] arr)
    {
        /*
        1. If it is leaf, update it.
        2. Else, go to left and right and update current
         */
        if(low==high)
        {
            seg[index] = arr[low];
            return;
        }

        int mid = (low+high)/2;
        build(2*index+1, low, mid, arr);
        build(2*index+2, mid+1, high, arr);
        seg[index] = seg[2*index+1] + seg[2*index+2];
    }

    public int query (int left, int right)
    {
        return query(0, 0, n-1, left, right);
    }

    private int query (int index, int low, int high, int l, int r)
    {
        /*
        1. Completely lies inside interval, return value
        2. Completely outside, return 0
        3. Else, try both the side and update current
         */
        if(low>=l && high<=r) return seg[index];
        if(l > high || r<low) return 0;

        int mid = (low+high)/2;
        int left = query(2*index+1, low, mid, l, r);
        int right = query(2*index+2, mid+1, high, l, r);
        return left+right;
    }

    public void update(int node, int val)
    {
        update(0, 0, n-1, node, val);
    }

    private void update(int index, int low, int high, int node, int val)
    {
        /*
        1. If leaf node, update it.
        2. Else, based on interval, go to left or right and update current
         */
        if(low==high)
        {
            seg[index] += val;
        }
        else
        {
            int mid = (low+high)/2;
            if(node<=mid && node>=low) update(2*index+1, low, mid, node, val);
            else update(2*index+2, mid+1, high,node, val);
            seg[index] = seg[2*index+1] + seg[2*index+2];
        }
    }


    public static void main(String[] args) {
        int[] arr = {5, 10, 15, 20, 25};
        SegmentTree st = new SegmentTree(arr);

        // Initial query for full range
        System.out.println(st.query(0, 4)); // Expected: 75

        // Update index 1 by adding 5 (10 + 5 = 15)
        st.update(1, 5);
        System.out.println(st.query(0, 4)); // Expected: 80 (5+15+15+20+25)

        // Update index 3 by adding 10 (20 + 10 = 30)
        st.update(3, 10);
        System.out.println(st.query(0, 4)); // Expected: 90 (5+15+15+30+25)

        // Query a small range (2 to 4)
        System.out.println(st.query(2, 4)); // Expected: 70 (15+30+25)
    }

}
