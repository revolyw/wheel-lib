package cn.willow.demo.algorithm;

import java.util.Arrays;

/**
 * 0、1背包问题
 * 有 n 个物品，第 i 个物品的体积为 w[i]，价值为 v[i]，每个物品至多选一个，求体积不超过 capacity 时的最大价值和是多少
 *
 * 回溯三问：
 * 1. 当前操作是什么？枚举第 i 个物品选或不选：不选，则剩余容量不变；选，则剩余容量减少 w[i]
 * 2. 子问题是什么？在剩余容量为 c 时，从前 i 个物品中得到最大价值和
 * 3. 下一个子问题是什么？分类讨论如下
 *      不选：在剩余容量为 c 时，从前 i-1 个物品中得到的最大价值和
 *      选：在剩余容量为 c - w[i] 时，从前 i-1 个物品中得到的最大价值和
 *  dfs(i, c) = max(dfs(i-1, c), dfs(i-1, c-w[i]) + v[i])
 * @author willow
 * @since 2025/3/25
 */
public class ZeroOneBackpack {
    public static void main(String[] args) {
        int[] w = new int[]{1,2,3,2,3};
        int[] v = new int[]{2,3,4,1,5};
        int capacity = 5;

        {
            //1,2,2 2+3+1=6
            //2(第二个),3（最后一个） 3+5=8
            int ans = 8;
            int result = findTargetSumWays(capacity, w, v);
            System.out.println("ans:" + ans + " result:" + result);
        }
    }

    public static int findTargetSumWays(int capacity, int[] w, int[] v){
        return dfs(capacity, w, v, w.length - 1);
    }

    /**
     * 动态规划
     * @param c
     * @param w
     * @param v
     * @param i
     * @return
     */
    public static int dfs(int c, int[] w, int[] v, int i){
        if(i < 0){
            return 0;
        }
        if(c < w[i]){
            return dfs(c, w, v, i - 1);
        }
        return Math.max(dfs(c, w, v, i-1), dfs(c - w[i] , w, v , i-1) + v[i]);
    }
}
