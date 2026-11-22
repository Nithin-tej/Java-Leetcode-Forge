import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private int[] newParent;
    private int[] lastSeen;
    private String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        this.newParent = Arrays.copyOf(parent, n);
        this.lastSeen = new int[26];
        Arrays.fill(this.lastSeen, -1);

        // 1. Build adjacency list for the original tree
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        // 2. Determine new parents simultaneously via DFS
        findNewParents(0);

        // 3. Build adjacency list for the modified tree
        for (int i = 0; i < n; i++) {
            adj[i].clear();
        }
        for (int i = 1; i < n; i++) {
            adj[newParent[i]].add(i);
        }

        // 4. Calculate final subtree sizes
        int[] answer = new int[n];
        calculateSizes(0, answer);
        return answer;
    }

    private void findNewParents(int u) {
        int charIdx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[charIdx];

        if (prevAncestor != -1) {
            newParent[u] = prevAncestor;
        }

        lastSeen[charIdx] = u;
        for (int v : adj[u]) {
            findNewParents(v);
        }
        // Backtrack so sibling branches aren't affected
        lastSeen[charIdx] = prevAncestor;
    }

    private int calculateSizes(int u, int[] answer) {
        int size = 1;
        for (int v : adj[u]) {
            size += calculateSizes(v, answer);
        }
        answer[u] = size;
        return size;
    }
}