package cn.willow.demo.algorithm;

import sun.java2d.jules.TrapezoidList;

import java.util.Arrays;

/**
 * @author willow
 * @since 2025/2/26
 */
class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int ans = solution.trap(height);
    }

    //需要分别知道每个柱子左边所有柱子高度的最大值和右边所有柱子高度中的最大值
    //然后取左右高度最大值的较小值减去当前柱子的高度就是当前这个柱子能容量的水容量
    //将每个柱子按上述方法计算出容量然后相加既是能容纳的水容量
    public int trap(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        System.out.println(Arrays.toString(leftMax));
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        System.out.println(Arrays.toString(rightMax));
        System.out.println(Arrays.toString(height));
        //计算面积
        int ans = 0;
        System.out.print("[");
        for (int i = 0; i < n; i++) {
            int h = Math.min(leftMax[i], rightMax[i]) - height[i];
            ans += h;
            System.out.print(ans);
            if (i == n - 1) {
                System.out.println("]");
            } else {
                System.out.print(", ");
            }
        }
        return ans;
    }
}
