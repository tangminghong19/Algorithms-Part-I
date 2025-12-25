// Worst Case: M*N

public class QuickFindUF 
{
    private int[] id;

    // O(N)
    public QuickFindUF(int N)
    {
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    // O(1)
    public boolean isConnected(int p, int q) 
    {   return id[p] == id[q];   }

    // O(N)
    public void union(int p, int q)
    {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++)
            if (id[i] == pid) id[i] = qid;
    }
}