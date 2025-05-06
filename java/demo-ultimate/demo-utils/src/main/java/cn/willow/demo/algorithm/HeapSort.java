package cn.willow.demo.algorithm;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author willow
 * @since 2025/2/7
 */
public class HeapSort {
    public static void main(String[] args) {
        {
            //普通数组
            int[] nums = new int[]{3, 5, 8, 1, 2, 9, 4, 7, 6};
            System.out.println(Arrays.toString(nums));
            Solution solution = new Solution();
            solution.sortArray(nums);
            System.out.println(Arrays.toString(nums));
            System.out.println();
        }
    }
    static class Solution {
        public int[] sortArray(int[] nums) {
            heapSort(nums);

            return nums;
        }

        public void heapSort(int[] nums) {
            //针对所有元素，初始化堆
            int len = nums.length - 1; //最后一个元素下标
            initHeap(nums, len);
            //依次将堆顶元素（最大值）与最后一个元素交换，堆大小减一（排除了当前最大值），然后针对新堆重新调整堆顶元素，使其满足最大堆性质
            for(int i = len; i>=1; i--) {
                swap(nums, i, 0);
                len--;
                maxHeapify(nums, 0, len);
            }
        }

        //初始化堆
        void initHeap(int[] nums, int len) {
            for(int i=len>>1; i>=0; i--){
                maxHeapify(nums, i, len);
            }
        }

        //根据当前元素下标维护最大堆
        void maxHeapify(int[] nums, int i, int len) {
            //循环退出条件，如果没有左子节点，即当前是叶子节点会退出循环
            for(; (i<<1)+1 <= len;){
                int leftSon = (i<<1) + 1;
                int rightSon =(i<<1) + 2;
                int large = 0;
                if(leftSon <= len && nums[leftSon] > nums[i]){
                    large = leftSon;
                } else {
                    large = i;
                }
                if(rightSon <= len && nums[rightSon] > nums[large]){
                    large = rightSon;
                }
                if(large != i){
                    swap(nums, large, i);
                    i = large; // 如果 large 已经是叶子节点的下标了，下一轮循环条件自然会退出
                } else {
                    break;
                }
            }
        }

        private void swap(int[] input, int left, int right) {
            if (input[left] == input[right]) {
                return;
            }
            int temp = input[left];
            input[left] = input[right];
            input[right] = temp;
        }
    }
}
