package cn.willow.demo.algorithm;

import java.util.Arrays;
import java.util.Deque;
import java.util.Objects;

/**
 * 快选算法，特点：时间复杂度 O(n)
 * @author willow
 * @since 2024/12/23
 */
public class QuickSelect {
    public static void main(String[] args) {
        {
            int[] nums = new int[]{3,2,1,5,6,4};
            int k = 2;
            int expected = 5;
            int n = nums.length;
            System.out.println(Arrays.toString(nums) + " k=" + k);
            int result = quickSelect1(nums, 0, n - 1, n - k);
            System.out.println(Arrays.toString(nums) + " result:" + result + "(" + (Objects.equals(result, expected)) + ") expected:" + expected);
        }
    }

    public static int quickSelect1(int[] nums, int left, int right, int k) {
        int partitionIndex = partition(nums, left, right);
        if(partitionIndex == k) {
            return nums[k];
        } else if(partitionIndex < k) {
            return quickSelect1(nums, partitionIndex + 1, right, k);
        } else {
            return quickSelect1(nums, left, partitionIndex - 1, k);
        }
    }

    public static int partition(int[] nums, int left, int right) {
        int pivot = nums[left], i = left-1, j = right+1;
        while(i<j){
            do i++; while (nums[i] < pivot);
            do j--; while (nums[j] > pivot);
            if (i < j){
                swap(nums, i, j);
            }
        }
        return j;
    }


    public static int quickSelect2(int[] nums, int l, int r, int k) {
        if(l == r) {
            return nums[k];
        }
        int pivot = nums[l], i=l-1, j=r+1;
        while(i<j){
            do i++; while(nums[i] < pivot);
            do j--; while(nums[j] > pivot);
            if(i<j){
                swap(nums, i, j);
            }
        }
        if(k <= j){
            return quickSelect2(nums, l, j, k);
        } else {
            return quickSelect2(nums, j+1, r, k);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
