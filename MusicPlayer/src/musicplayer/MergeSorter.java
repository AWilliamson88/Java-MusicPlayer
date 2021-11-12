package musicplayer;

import java.util.LinkedList;

/**
 * Java 3 AT 3 - Project.
 * Question 3 – Implement your solution.
 * Must contain dynamic data structures.
 * (e.g. doubly linked list or a binary tree).
 * Must contain hashing techniques.
 * Must contain sorting algorithm.
 * Must contain searching technique.
 * Must contain 3rd party library.
 * Must have a GUI.
 * Must adhere to coding standards.
 * Must have help files.
 *
 * @author Andrew Williamson / P113357
 */
public class MergeSorter {

    private static LinkedList<Song> list;

    public MergeSorter() {
    }

    // Where the sorting starts.
    public LinkedList<Song> sort(LinkedList<Song> unsortedList) {

        if (!(unsortedList == null || unsortedList.isEmpty())) {

            setList(unsortedList);
            mergeSort(0, GetList().size() - 1);
            
            return GetList();
        }
        return unsortedList;

    }

    // Separates this list into sub lists.
    private void mergeSort(int startIndex, int endIndex) {

        if (startIndex < endIndex && (endIndex - startIndex) >= 1) {

            int mid = (endIndex + startIndex) / 2;

            mergeSort(startIndex, mid);
            mergeSort(mid + 1, endIndex);

            merge(startIndex, mid, endIndex);
        }

    }

    // Does the sorting and merging.
    private void merge(int startIndex, int midIndex, int endIndex) {

        LinkedList<Song> sortedArray = new LinkedList<>();

        int leftIndex = startIndex;
        int rightIndex = midIndex + 1;

        while (leftIndex <= midIndex && rightIndex <= endIndex) {

            String liftIndexTitle = GetList().get(leftIndex).getTitle();
            String rightIndexTitle = GetList().get(rightIndex).getTitle();
            
            if (liftIndexTitle.compareTo(rightIndexTitle) <= 0 ) {
                sortedArray.add(GetList().get(leftIndex));
                leftIndex++;
            } else {
                sortedArray.add(GetList().get(rightIndex));
                rightIndex++;
            }
        }

        while (leftIndex <= midIndex) {
            sortedArray.add(GetList().get(leftIndex));
            leftIndex++;
        }
        while (rightIndex <= endIndex) {
            sortedArray.add(GetList().get(rightIndex));
            rightIndex++;
        }
        
        int j = startIndex;
        for(int i = 0; i < sortedArray.size(); i++) {
            GetList().set(j, sortedArray.get(i));
            j++;
        }
    }

    // Accessors
    public LinkedList<Song> GetList() {
        return list;
    }

    public void setList(LinkedList<Song> newList) {
        list = newList;
    }
}
