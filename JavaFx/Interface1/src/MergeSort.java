/*
Merge Sort [Divide and Conquer]
 */

public class MergeSort {
  // create merges two array
  public static void mergeArray(int arr[], int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    int[] arr1 = new int[n1];
    int[] arr2 = new int[n2];

    for (int i = 0; i < n1; i++) {
      arr1[i] = arr[left + i];
    }

    for (int j = 0; j < n2; j++) {
      arr2[j] = arr[mid + 1 + j];
    }

    // merge subarrays
    int i = 0, j = 0;
    int k = left;

    while (i < n1 && j < n2) {
      if (arr1[i] <= arr2[j]) {
        arr[k] = arr1[i];
        i++;
      } else {
        arr[k] = arr2[j];
        j++;
      }
      k++;
    }

    // copy remaining elements, if any
    while (i < n1) {
      arr[k] = arr1[i];
      i++;
      k++;
    }

    while (j < n2) {
      arr[k] = arr2[j];
      j++;
      k++;
    }
  }

  // merge sort
  public static void mergeSort(int arr[], int left, int right) {
    if (left == right) {
      return;
    }

    int mid = left + (right - left) / 2;
    mergeSort(arr, left, mid);
    mergeSort(arr, mid + 1, right);

    // merge sorted halves
    mergeArray(arr, left, mid, right);
  }

  public static void main(String[] args) {

    int[] arr = {5, 4, 1, 3, 2};
    System.out.print("Initial Array: ");
    for (int i : arr) {
      System.out.print(i + " ");
    }

    int n = arr.length;
    mergeSort(arr, 0, n - 1);
    System.out.print("\nSorted Array: ");
    for (int i : arr) {
      System.out.print(i + " ");
    }
  }
}
