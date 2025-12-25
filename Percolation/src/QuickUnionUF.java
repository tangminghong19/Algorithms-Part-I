// Worst Case: M*N
public class QuickUnionUF 
{
    private int[] id;

    // O(N)
    public QuickUnionUF(int N)
    {
        id = new int[N];
        for (int i = 0; i < N; i++) id[i] = i;
    }

    private int root(int i)
    {
        while (id[i] != i) i = id[i];
        return i;
    }

    // O(N) for the worst case
    public boolean isConnected(int p, int q)
    {
        return root(p) == root(q);
    }

    // O(N)
    public void union(int p, int q)
    {
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }
}
