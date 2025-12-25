// Worst Case
// weighted QU: N + M*logN
// QU + path compression: N + M*logN
// weighted QU + path compression: N + M*lg*N // N + M*alpha(M, N)
public class WeightedQuickUnionUF 
{
    private int[] id;
    private int[] sz;

    // O(N)
    public WeightedQuickUnionUF(int N)
    {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++)
        {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int p)
    {
        while (id[p] != p) 
        {
            id[p] = id[id[p]]; // path compression
            p = id[p];
        }
        return p;
    }

    // O(log N)
    public boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }

    // O(log N)
    public void union(int p, int q)
    {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
        else               { id[j] = i; sz[i] += sz[j]; }
        // Depth of any node is at most log N
    }
}
