/*
Bubble sort
 */
public class BubbleSort {

  public static void bubbleSort(int[] arr, int n) {
    for (int i = 0; i < n; i++) {
      boolean swapped = false;
      for (int j = 0; j < n - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
          swapped = true;
        }
      }
      if (!swapped) {
        break;
      }
    }
  }

  public static void main(String[] args) {

    int arr[] = {45, 20, 50, 15, 10};
    System.out.print("Initial Array: ");
    for (int i : arr) {
      System.out.print(i + " ");
    }

    int n = arr.length;
    bubbleSort(arr, n); // called function
    System.out.print("\nSorted Array: ");
    for (int i : arr) {
      System.out.print(i + " ");
    }
  }
}
