/*
author: abinesh-b
*/
public class SegmentTreeWithLazyPropagation {

    private int[] seg;
    private int[] lazy;
    int n;
    public SegmentTreeWithLazyPropagation (int[] arr)
    {
        n = arr.length;
        seg = new int[4*n];
        lazy = new int[4*n];
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

    public void updateRange( int l, int r, int val)
    {
        updateRange(0, 0, n-1, l , r, val);
    }

    private void updateRange(int index, int low, int high, int l, int r, int val)
    {
        //do lazy update if available
        if(lazy[index]!=0)
        {
            seg[index] += (high-low+1) * lazy[index];
            if(low!=high)
            {
                lazy[2*index+1] += lazy[index];
                lazy[2*index+2] += lazy[index];
            }
            lazy[index] = 0;
        }

        //completely outside intervals
        if(l> high || r< low) return;

        //completely lies inside, update and propagate it
        if(low>=l && high<=r)
        {
            seg[index] += (high-low+1) * val;
            if(low!=high)
            {
                lazy[2*index+1] += val;
                lazy[2*index+2] += val;
            }
            return;
        }

        int mid = (low+high)/2;
        updateRange(2*index+1, low, mid, l, r, val);
        updateRange(2*index+2, mid+1, high, l, r, val);

        seg[index] = seg[2*index+1] + seg[2*index+2];

    }

    public int querySum(int l, int r)
    {
        return querySum(0, 0, n-1, l, r);
    }

    private int querySum(int index, int low, int high, int l, int r)
    {
        //do lazy update
        if(lazy[index]!=0)
        {
            seg[index] += (high-low+1) * lazy[index];
            if(low!=high)
            {
                lazy[2*index+1] += lazy[index];
                lazy[2*index+2] += lazy[index];
            }
            lazy[index] = 0;
        }
        //completely outside
        if(r< low || l>high) return 0;
        //completely inside
        if(low>=l && high<=r) return seg[index];

        //overlapping
        int mid = (low+high)/2;
        return querySum(2*index+1, low, mid, l, r) +querySum(2*index+2, mid+1, high, l, r);

    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        SegmentTreeWithLazyPropagation st = new SegmentTreeWithLazyPropagation(arr);

         //Initial query from 0 to 5 (sum of all elements)
        System.out.println(st.querySum(0, 5)); // Expected: 36

        // Update range (1, 3) by adding 10 (affects arr[1], arr[2], arr[3])
        st.updateRange(1, 3, 10);
        System.out.println(st.querySum(0, 5)); // Expected: 66 (because arr[1], arr[2], arr[3] are increased by 10 each)
        System.out.println(st.querySum(1, 3)); // Expected: 45 (13 + 15 + 17)

        // Query range (2, 4)
        System.out.println(st.querySum(2, 4)); // Expected: 41 (15 + 17 + 9)

    }


}
