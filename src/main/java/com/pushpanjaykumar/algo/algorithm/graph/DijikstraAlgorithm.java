package main.java.com.pushpanjaykumar.algo.algorithm.graph;

/**
 * @author pushpanjay.kumar
 */
public class DijikstraAlgorithm {

    private static int minDistance(Boolean[] sptSet, int[] dist){
        int minIndex=-1;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<dist.length;i++){
            if(!sptSet[i] && dist[i]<=min){
                min = dist[i];
                minIndex=i;
            }
        }
        return minIndex;
    }

    private static int[] dijikstra(int[][] g, int src) {
        int v = g.length;
        int[] dist = new int[v];
        Boolean[] sptSet = new Boolean[v];
        for(int i=0;i<v;i++){
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[src]=0;

        for(int count=0;count<v-1;count++){
            int u = minDistance(sptSet, dist);
            sptSet[u]=true;

            for(int vi=0;vi<v;vi++){
                if(!sptSet[vi] && g[u][vi]!=0 && dist[u]!=Integer.MAX_VALUE && dist[u] + g[u][vi] < dist[vi]){
                    dist[vi] = dist[u] + g[u][vi];
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}};

        int[] dist = dijikstra(graph, 0);
        System.out.println();
        for(Integer i: dist){
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
