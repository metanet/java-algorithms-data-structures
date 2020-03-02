package problems.leetcode;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.println(searchOne(nums, 1));
    }

    public static int searchOne(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] >= nums[l]) {
                // mid element is larger than the first element, i.e.,
                // the first to mid subarray is not rotated.
                // if the target is in this part, go left. otherwise, go right.
                if (target >= nums[l] && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                // mid element is smaller than the first element, i.e.,
                // the rotation is somewhere between first element and mid.
                // then the mid to end subarray is not rotated.
                // if the target is in this part, go right. otherwise, go left.
                if (target <= nums[r] && target > nums[mid]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }

        return -1;
    }


    //  0 1 2 3 4 5 6
    // [4,5,6,7,0,1,2]
    // [6,7,0,1,2,4,5]

    // [4,5,6,7,0,1]


    private static int binarySearch(int[] nums, int l, int r, int key) {
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= key) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return nums[l] == key ? l : -1;
    }

}
