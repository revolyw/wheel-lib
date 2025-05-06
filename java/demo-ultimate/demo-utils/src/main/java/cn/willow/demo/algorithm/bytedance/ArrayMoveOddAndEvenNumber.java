package cn.willow.demo.algorithm.bytedance;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 给一个数组，使数组奇数在前半段，偶数在后半段，在原数组上操作
 * @author willow
 * @since 2025/3/25
 */
public class ArrayMoveOddAndEvenNumber {
    //O(n) O(1)
    public static void main(String[] args) {
        int[] nums = new int[]{2,4,1,5,6,7,12,5,76,4,223,4,5};
        System.out.println(Arrays.toString(nums));
        move(nums);
        System.out.println(Arrays.toString(nums));

        Deque<Integer> deque = new LinkedList<>();
    }
    public static void move(int[] nums){
        int n = nums.length;
        int left = 0, right = n-1;
        while(left < right){
            while(nums[right] % 2 == 0){
                right--;
            }
            while (nums[left] % 2 != 0) {
                left++;
            }
            if(left >= right){
                break;
            }
            swap(nums, left, right);
        }
    }
    public static void swap(int[] nums, int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
