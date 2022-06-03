import javax.swing.tree.TreeNode;
import java.util.*;

public class main {

    private static int MaxSumSubArrayOfSizeK(int[] nums, int k){

        int sum = 0;
        for(int i = 0; i < k; i++){
            sum += nums[i];
        }

        int max = sum;

        for(int i = k; i < nums.length; i++){
            sum -= nums[i-k];
            sum += nums[i];
            max = Math.max(sum, max);
        }

        return max;
    }


    private static boolean pathWithGiven(int[] seq, TreeNode root){
        if(root == null){
            if (seq.length == 0)
                return true;
            else
                return false;
        }
        return helper(root, seq, 0);
    }

    private static boolean helper(TreeNode root, int[] seq, int i) {
        if(root == null){
            return false;
        }
        if(i > seq.length || seq[i] != root.val){
            return false;
        }
        if(i == seq[seq.length-1] && root.right == null && root.left == null){
            return true;
        }
        return helper(root.left, seq, i+1) || helper(root.right, seq, i+1);
    }

    private static int knapsack(int[] profits, int[] weights, int capacity){
        Integer[][] dp = new Integer[profits.length][capacity+1];
        return recursive(dp, profits, weights, capacity, 0);
    }

    private static int recursive(Integer[][] dp, int[] profits, int[] weights, int capacity, int index) {
        if(capacity <= 0 || index >= profits.length){
            return 0;
        }

        if(dp[capacity][index] != null){
            return dp[capacity][index];
        }

        int profit1 = 0;
        if(weights[index] <= capacity){
            profit1 = profits[index] + recursive(dp, profits, weights,
                    capacity - weights[index], index + 1);
        }
        int profit2 = recursive(dp, profits, weights, capacity, index+1);

        dp[capacity][index] = Math.max(profit1, profit2);
        return dp[capacity][index];
    }

    public static List<Integer> sort(int vertices, int[][] edges){
        List<Integer> sortedOrder = new ArrayList<>();
        if(vertices <= 0){
            return sortedOrder;
        }

        HashMap<Integer, Integer> inDegree = new HashMap<>();
        HashMap<Integer, List<Integer>> graph = new HashMap<>();

        for(int i = 0; i < vertices; i++){
            inDegree.put(i, 0);
            graph.put(i, new ArrayList<>());
        }

        for(int i = 0; i < edges.length; i++){
            int parent = edges[i][0];
            int child = edges[i][1];

            graph.get(parent).add(child);
            inDegree.put(child, inDegree.get(child) + 1);
        }

        Queue<Integer> sources = new LinkedList<>();
        for(int key: inDegree.keySet()){
            if(inDegree.get(key) == 0){
                sources.add(key);
            }
        }

        while (!sources.isEmpty()){
            int vertex = sources.poll();
            sortedOrder.add(vertex);
            List<Integer> children = graph.get(vertex);
            for(int c: children){
                inDegree.put(c, inDegree.get(c) - 1);
                if(inDegree.get(c) == 0){
                    sources.add(c);
                }
            }

        }
        return sortedOrder;
    }





    public static void main(String[] args) {
        int res = MaxSumSubArrayOfSizeK(new int[] {2,1,5,1,3,2}, 3);

        System.out.println(res);
    }
}
