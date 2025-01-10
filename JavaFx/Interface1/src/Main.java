public class Main {
    public static void main(String[] args) {
        // Initial array for testing
        int[] arr = {45, 20, 50, 15, 10};

        // Bubble Sort
        System.out.print("\nBubbleSort: ");
        int[] bubbleArr = arr.clone();
        BubbleSort.bubbleSort(bubbleArr, bubbleArr.length);
        printArray(bubbleArr);

        // Selection Sort
        System.out.print("\nSelectionSort: ");
        int[] selectionArr = arr.clone();
        SelectionSort.selectionSort(selectionArr);
        printArray(selectionArr);

        // Quick Sort
        System.out.print("\nQuickSort: ");
        int[] quickArr = arr.clone();
        QuickSort.quickSort(quickArr, 0, quickArr.length - 1);
        printArray(quickArr);

        // Merge Sort
        System.out.print("\nMergeSort: ");
        int[] mergeArr = arr.clone();
        MergeSort.mergeSort(mergeArr, 0, mergeArr.length - 1);
        printArray(mergeArr);
    }

    // Method to print an array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
