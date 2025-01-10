/** quick sort */
public class QuickSort {

  // partiition
  public static int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = (low - 1);
    for (int j = low; j < high; j++) {
      if (arr[j] <= pivot) {
        i++;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }

    i++;
    int temp = arr[i];
    arr[i] = pivot;
    arr[high] = temp;
    return i; // pivot index
  }

  // quick sort
  public static void quickSort(int[] arr, int low, int high) {
    // base case
    if (low < high) {
      int pivot_index = partition(arr, low, high);
      quickSort(arr, low, pivot_index - 1);
      quickSort(arr, pivot_index + 1, high);
    }
  }

  public static void main(String[] args) {
    int[] arr = {6, 3, 9, 5, 2, 8};
    System.out.print("Initial array: ");
    for (int j : arr) {
      System.out.print(j + " ");
    }
    System.out.println();
    int n = arr.length;

    // call quicksort
    quickSort(arr, 0, n - 1);

    System.out.print("Sorted array: ");
    for (int j : arr) {
      System.out.print(j + " ");
    }
    System.out.println();
  }
}
