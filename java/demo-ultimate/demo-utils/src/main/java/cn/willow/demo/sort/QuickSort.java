package cn.willow.demo.sort;

/**
 * @author willow
 * @since 2024/12/12
 */
public class QuickSort {
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // 获取分区后的枢纽位置
            int pivotIndex = partition(arr, low, high);

            // 分别对枢纽左右两边的子数组进行递归排序
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        // 选择数组的最后一个元素作为枢纽值
        int pivot = arr[high];
        int i = (low - 1);

        // 遍历数组，将小于枢纽值的元素放到左边，大于枢纽值的元素放到右边
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;

                // 交换 arr[i] 和 arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // 将枢纽元素放到正确的位置
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        // 返回枢纽位置
        return i + 1;
    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        quickSort(arr, 0, arr.length - 1);

        // 输出排序后的数组
        for (int val : arr) {
            System.out.print(val + " ");
        }
    }
}
