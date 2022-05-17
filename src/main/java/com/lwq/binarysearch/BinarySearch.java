package com.lwq.binarysearch;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 12, 12, 12, 11};
        int[] arr1 = {3,2,3,2,3};
        int[] arr2 = {1,2,3,1};
        int target = 12;
        int index = binarySearch(arr, target);
        int indexleft = mostLeftLessNumIndex(arr, target);
        int indexright = mostrightLessNumIndex(arr, target);
        int indexmin = oneMinIndex(arr1);
        int indexmax = oneMaxIndex(arr2);
        System.out.println(index);
        System.out.println(indexleft);
        System.out.println(indexright);
        System.out.println(indexmin);
        System.out.println(indexmax);
    }

    /**
     * 一个数组，无序，相邻数字不相等
     * <p>
     * 要求返回某一个区间最小值：这个数小于他的左右相邻数
     * <p>
     * 使用二分：
     * 如果0位置小于1位置：直接返回0位置
     * 如果n-1位置小于n-2位置，直接返回n-1位置
     * <p>
     * 否则，中间一定有一个区间最小值。
     * 此时取mid为中间的位置：
     * 如果mid位置小于mid-1位置，从mid往右找
     * 如果mid位置大于mid-1位置，从mid往左找
     *
     * @param arr
     * @return
     */
    private static int oneMinIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int length = arr.length;
        if (length == 1) {
            return 0;
        }
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[length - 2] > arr[length - 1]) {
            return length - 1;
        }
        int left = 0;
        int right = length - 1;
        int index = -1;
        //这里有一种边界情况，就是mid-1或者mid+1不在L到R中，此时L和R只有两个数
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                return mid;
            } else {
                // 左边 < 我 < 右边,递增
                // 左边 < 我 > 右边,我最大
                // 左边 > 我 > 右边,递减
                if (arr[mid] > arr[mid - 1]) {
//                    对应上面的情况1,2，此时直接去左边搜索，肯定有一个区间最小值
                    right = mid - 1;
                    continue;
                }
                if (arr[mid] > arr[mid + 1]) {
                    left = mid + 1;
                    continue;
                }
            }
        }
        return arr[left] < arr[right] ? left : right;
    }

    public static int oneMaxIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int length = arr.length;
        if (length == 1) {
            return 0;
        }
        if (arr[0] > arr[1]) {
            return 0;
        }
        if (arr[length - 2] < arr[length - 1]) {
            return length - 1;
        }
        int left = 0;
        int right = length - 1;
        int index = -1;
        //这里有一种边界情况，就是mid-1或者mid+1不在L到R中，此时L和R只有两个数
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return mid;
            } else {
                // 左边 > 我 > 右边,递减
                // 左边 > 我 < 右边,我最小
                // 左边 < 我 < 右边,递增
                if (arr[mid] > arr[mid - 1]) {
//                    对应上面的情况3，此时直接去右边搜索，肯定有一个区间最大值
                    left = mid + 1;
                    continue;
                }
                if (arr[mid] < arr[mid + 1]) {
                    right = mid - 1;
                    continue;
                }
            }
        }
        return arr[left] > arr[right] ? left : right;
    }

    /**
     * 找到有序数组中target的最右侧的下标，找不到返回-1
     *
     * @param arr
     * @param target
     * @return
     */
    private static int mostrightLessNumIndex(int[] arr, int target) {
        if (arr.length == 0 || arr == null) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                left = mid + 1;
                index = mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return index;
    }

    /**
     * 找到有序数组中target的最左侧的下标，找不到返回-1
     *
     * @param arr
     * @param target
     * @return
     */
    private static int mostLeftLessNumIndex(int[] arr, int target) {
        if (arr.length == 0 || arr == null) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                right = mid - 1;
                index = mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return index;
    }

    /**
     * 找到有序数组中target的下标，找不到返回-1
     *
     * @param arr
     * @param target
     * @return
     */
    private static int binarySearch(int[] arr, int target) {
        if (arr.length == 0 || arr == null) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 生成随机长度，随机值的数组，相邻值不等
     */
    private static int[] randomArray(int randomlength, int randomMax) {
        int[] arr = new int[(int) (Math.random() * randomlength)];
        int length = arr.length;
        if (length > 0) {
            arr[0] = (int) (Math.random() * randomMax);
            for (int i = 1; i < length; i++) {
                do {
                    arr[i] = (int) (Math.random() * randomMax);
                } while (arr[i] == arr[i - 1]);
            }
        }
        return arr;
    }
}
