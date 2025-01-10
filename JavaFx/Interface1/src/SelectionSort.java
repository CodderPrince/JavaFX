/*
Selection Sort
 */
public class SelectionSort {

  // create selection sort
  public static void selectionSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
      int min_element = i; // Assume the min element is at index i
      for (int j = i + 1; j < n; j++) {
        if (arr[j] < arr[min_element]) {
          min_element = j; // Found a new minimum, update min_element
        }
      }
      // Swap only if min_element has changed
      if (min_element != i) {
        swap(arr, i, min_element);
      }
    }
  }

  // Swap method that swaps elements at indices i and j
  public static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void main(String[] args) {

    int[] arr = {5, 2, 1, 3, 4};
    System.out.print("Initial Array: ");
    for (int i : arr) {
      System.out.print(i + " ");
    }

    // selection sort
    selectionSort(arr);
    System.out.print("\nSorted Array: ");
    for (int i : arr) {
      System.out.print(i + " ");
    }
  }
}
