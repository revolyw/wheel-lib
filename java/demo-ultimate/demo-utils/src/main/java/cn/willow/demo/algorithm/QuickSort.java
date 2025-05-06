package cn.willow.demo.algorithm;

import org.springframework.remoting.rmi.RmiInvocationHandler;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author willow
 * @since 2024/12/13
 */
public class QuickSort {
    //15:04 ~ 15:28
    public static void main(String[] args) {
//        {
//            //普通数组
//            int[] input = new int[]{3, 5, 8, 1, 2, 9, 4, 7, 6};
//            System.out.println(Arrays.toString(input));
//            quickSort(input, 0, input.length - 1);
//            System.out.println(Arrays.toString(input));
//            System.out.println();
//        }
//        {
//            //空数组
//            int[] input = new int[]{};
//            System.out.println(Arrays.toString(input));
//            quickSort(input, 0, input.length - 1);
//            System.out.println(Arrays.toString(input));
//            System.out.println();
//        }
//        {
//            //单元素数组
//            int[] input = new int[]{42};
//            System.out.println(Arrays.toString(input));
//            quickSort(input, 0, input.length - 1);
//            System.out.println(Arrays.toString(input));
//            System.out.println();
//        }
//        {
//            //重复元素数组
//            int[] input = new int[]{1, 2, 2, 2, 3, 3, 4, 4, 4, 4};
//            System.out.println(Arrays.toString(input));
//            quickSort(input, 0, input.length - 1);
//            System.out.println(Arrays.toString(input));
//            System.out.println();
//        }
//        {
//            //逆序数组
//            int[] input = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
//            System.out.println(Arrays.toString(input));
//            quickSort(input, 0, input.length - 1);
//            System.out.println(Arrays.toString(input));
//            System.out.println();
//        }
//        {
//            //有序数组
//            int[] input = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
//            System.out.println(Arrays.toString(input));
//            quickSort(input, 0, input.length - 1);
//            System.out.println(Arrays.toString(input));
//            System.out.println();
//        }
//        {
//            //负数数组
//            int[] input = new int[]{-9, -8, -7, -6, -5, -4, -3, -2, -1};
//            System.out.println(Arrays.toString(input));
//            quickSort(input, 0, input.length - 1);
//            System.out.println(Arrays.toString(input));
//            System.out.println();
//        }
//        {
//            //包含 0 的数组
//            int[] input = new int[]{1, 2, 3, 0, 4, 5, 6, 7, 8};
//            System.out.println(Arrays.toString(input));
//            quickSort(input, 0, input.length - 1);
//            System.out.println(Arrays.toString(input));
//            System.out.println();
//        }
//        {
//            //随机数组
//            int[] input = new int[100];
//            for (int i = 0; i < input.length; i++) {
//                input[i] = (int) (Math.random() * 1000);
//            }
//            System.out.println(Arrays.toString(input));
//            quickSort(input, 0, input.length - 1);
//            System.out.println(Arrays.toString(input));
//            System.out.println();
//        }
        {
            //大数组。leetcode 易超时用例
            int[] input = new int[50000];
            input[0] = 3;
            for (int i = 1; i < input.length; i++) {
                input[i] = 2;
            }
            quickSort(input, 0, input.length - 1);
            System.out.println(Arrays.toString(input));
            System.out.println();
        }
    }

    private static void quickSort(int[] input, int low, int high) {
        if (low > high) {
            return;
        }
        int pivotIndex = partition(input, low, high);
        quickSort(input, low, pivotIndex - 1);
        quickSort(input, pivotIndex + 1, high);
    }

    private static int partition(int[] input, int low, int high) {
        final int pivotIndex = chosePivotIndex(input, low, high);
        final int pivot = input[pivotIndex];
        int left = low, right = high;
        while (left < right) {
            while (left < right && input[left] <= pivot) {
                left++;
            }
            while (left < right && input[right] >= pivot) {
                right--;
            }
            swap(input, left, right);
        }
        swap(input, left, pivotIndex);
        return left;
    }

    private static int chosePivotIndex(int[] input, int low, int high) {
        return low + (int) (Math.random() * (high - low + 1));
    }

    private static void swap(int[] input, int left, int right) {
        if (input[left] == input[right]) {
            return;
        }
        int temp = input[left];
        input[left] = input[right];
        input[right] = temp;
//        input[left] = input[left] ^ input[right];
//        input[right] = input[left] ^ input[right];
//        input[left] = input[left] ^ input[right];
    }
}
