import java.util.*;
import java.io.*;



public class AdeniregunAdedipupoA2Q1 {

    // Control the testing
    private static final int ARRAY_SIZE = 10000;
    private static final int SAMPLE_SIZE = 300; // The number of trials in each experiment.

    // Control the randomness
    private static final int NUM_SWAPS = ARRAY_SIZE / 2;
    private static Random generator = new Random( System.nanoTime() );

    // Control the base cases for hybrid quick sort:
    private static final int BREAKPOINT = 50;

    // Controls which sort is tried.
    private static final int INSERTION_SORT = 0;
    private static final int BUBBLE_SORT = 1;
    private static final int SELECTION_SORT = 2;
    private static final int MERGE_SORT = 3;
    private static final int QUICK_SORT = 4;
    private static final int HYBRID_QUICK_SORT = 5;
    private static final int SHELL_SORT = 6;

/*********** main and the method it calls *************************/

    /*******************************************************************
     * main
     *
     * Purpose: Print out "bookend" messages and call the method that
     *          does all the testing of the sorting algorithms.
     *
     ******************************************************************/
    public static void main( String[] args ) {
  System.out.println( "\n\nCOMP 2140 A2Q1 Sorting Test --- Summer 2021\n" );

  testSorts();

  System.out.println( "\nProcessing ends normally\n" );
    } // end main


    /*******************************************************************
     * testSorts
     *
     * Purpose: Run each sorting algorithm SAMPLE_SIZE times,
     *          on an array of size ARRAY_SIZE with NUM_SWAPS
     *          random swaps performed on it.
     *          Compute the arithmetic mean of the timings for each sorting algorithm.
     *
     *          Print the results.
     *
     ******************************************************************/
    private static void testSorts() {

  // Arrays used in timing experiments (create these arrays once)
  int[] array = new int[ARRAY_SIZE]; // array to be sorted
  long[] sortTime = new long[ SAMPLE_SIZE ]; // store timings for multiple runs
                                            // of a single sorting method
  // Fill array to be sorted with the numbers 0 to ARRAY_SIZE.
  // (The numbers will be randomly swapped before each sort.)
  fillArray( array );

  // Now run the experiments on all the sorts
  System.out.println("Array size: " + ARRAY_SIZE + "\nNumber of swaps: " + NUM_SWAPS);
  System.out.println("Number of trials of each sort: " + SAMPLE_SIZE );

  // Stats for each run
  System.out.println("\nInsertion sort mean: "
      + tryOneSort( array, sortTime, INSERTION_SORT )
      + " ns" );
  System.out.println("Bubble sort mean: "
      + tryOneSort( array, sortTime, BUBBLE_SORT )
      + " ns" );
  System.out.println("Selection sort mean: "
      + tryOneSort( array, sortTime, SELECTION_SORT )
      + " ns" );
  System.out.println("Merge sort mean: "
      + tryOneSort( array, sortTime, MERGE_SORT )
      + " ns" );
  System.out.println("Quick sort mean: "
      + tryOneSort( array, sortTime, QUICK_SORT )
      + " ns" );
  System.out.println("Hybrid quick sort mean: "
      + tryOneSort( array, sortTime, HYBRID_QUICK_SORT )
      + " ns" );
  System.out.println("Shell sort mean: "
      + tryOneSort( array, sortTime, SHELL_SORT )
      + " ns" );

    } // end testSorts

/*********** methods called by testSorts *************************/

    /*******************************************************************
     * tryOneSort:
     *
     * Purpose: Get an average run time for a sorting algorithm.
     *
     * Methodology: Run the chosen sorting algorithm SAMPLE_SIZE times,
     *          on an array of size ARRAY_SIZE with NUM_SWAPS
     *          random swaps performed on it.
     *          Return the arithmetic mean of the timings.
     *
     ******************************************************************/
    private static double tryOneSort( int[] array, long[] sortTime, int whichSort ) {

      long start, stop, elapsedTime;  // Time how long each sort takes.

      start = stop = 0; // because the compiler complains that they might
      // not have been initialized inside the for-loop

      for ( int i = 0; i < SAMPLE_SIZE; i++ ) {
        randomizeArray( array, NUM_SWAPS );
        if ( whichSort == INSERTION_SORT ) {
          start = System.nanoTime();
          insertionSort( array );
          stop = System.nanoTime();
          checkArray(array, "Insertion sort");
        } else if ( whichSort == BUBBLE_SORT ) {
          start = System.nanoTime();
          bubbleSort( array );
          stop = System.nanoTime();
          checkArray(array, "Bubble sort");
        } else if ( whichSort == SELECTION_SORT ) {
          start = System.nanoTime();
          selectionSort( array );
          stop = System.nanoTime();
          checkArray(array, "Selection sort");
        } else if ( whichSort == MERGE_SORT ) {
          start = System.nanoTime();
          mergeSort( array );
          stop = System.nanoTime();
          checkArray(array, "Merge sort");
        } else if ( whichSort == QUICK_SORT ) {
          start = System.nanoTime();
          quickSort( array );
          stop = System.nanoTime();
          checkArray(array, "Quick sort");
        } else if ( whichSort == HYBRID_QUICK_SORT ) {
          start = System.nanoTime();
          hybridQuickSort( array );
          stop = System.nanoTime();
          checkArray(array, "Hybrid quick sort");
        } else if ( whichSort == SHELL_SORT ) {
          start = System.nanoTime();
          shellSort( array );
          stop = System.nanoTime();
          checkArray(array, "Shell sort");
        }
        elapsedTime = stop - start;
        sortTime[i] = elapsedTime;
      } // end for
      
      return arithmeticMean( sortTime );
    } // end tryOneSort

    
    
    /*******************************************************************
     * insertionSort
     *
     * Purpose: Main method for sorting an array using insertion
     *          sort. Calls a helper method which contains the 
     *          actual insertion sort algorithm
     *
     ******************************************************************/
    public static void insertionSort(int[] array){
      insertionSort(array,0,array.length);
    }//end insertionSort
    
    
    
    /*******************************************************************
     * insertionSort
     *
     * Purpose: Helper method with insertion sort algorithm
     ******************************************************************/
    private static void insertionSort( int[] array, int start, int end){
      int siftVal;
      int j;
      
      for(int i = start+1; i < end; i++){
        siftVal = array[i];
        j = i - 1;
        
        while((j >= start) && (array[j] > siftVal)) {
          array[j+1] = array[j];
          j--;
        }//endwhile
        array[j+1] = siftVal;
      }//endfor
    }//end insertionSort

    
    
    /*******************************************************************
     * bubbleSort
     *
     * Purpose: Sort array using bubble Sort 
     ******************************************************************/
    public static void bubbleSort( int[] array ){
     
      for(int i = 0; i < array.length; i++){
        for(int j = i + 1; j < array.length; j++) {
          if(array[j] < array[i])
            swap(array,i,j);
        }//end for
      }//end for
    }//end bubbleSort

    /*******************************************************************
     * selectionSortSort
     *
     * Purpose: Sort array using selection Sort. Calls findMin 
     *          helperMethod to find the minimum value in unsorted 
     *          part of array
     ******************************************************************/
    public static void selectionSort( int[] array ) {
      for(int i = 0; i < array.length; i++) {
        swap(array,findMin(array,i,array.length),i);
      }//endfor
    }//end selectionSort

    
    
    /*******************************************************************
     * mergeSort
     *
     * Purpose: Sort an array using merge sort by calling a 
     *          helper method which contains the mergesort algorithm
     ******************************************************************/
    public static void mergeSort(int[] array) {
      int[] temp = new int[array.length];
      mergeSort(array, 0,array.length,temp);
    }//end mergeSort
    
    
    
    /*******************************************************************
     * mergeSort
     *
     * Purpose: Helper method which contains the mergesort algorithm
     *          calls the merge helper method to merge the two sorted
     *          halves of the array during mergesort
     ******************************************************************/
    private static void mergeSort(int[] array, int start, int end, int[] temp){
      //base case with two items in array
      if((end - start) == 2 && array[end-1] < array[start]){
        swap(array,start,end-1);
      }//endif
      
      else if ((end - start) > 2){
        int mid = start + ((end - start)/2);
        mergeSort(array,start,mid,temp);
        mergeSort(array,mid,end,temp);
        merge(array,start,mid,end,temp);
      }//end else if
      
      //base case with one item, do nothing
    }//end mergeSort
    
    
    
    /*******************************************************************
     * mergeSort
     *
     * Purpose: Sort an array using quickSort. Calls the quickSort
     *          helper method which contains the quicksort algorithm
     ******************************************************************/
    public static void quickSort(int[] array){
      quickSort(array,0,array.length);
    }//end quicksort
    
    
    
    /*******************************************************************
     * quickSort
     *
     * Purpose: Helper method to recursively sort an array by 
     *          using the quickSort algorithm. 
     *          Calls these helper methods: 
     *          partition(), medianOfThree()
     ******************************************************************/
    private static void quickSort(int[] array, int start, int end){
      //base case with two items in array
      if((end - start) == 2 && array[end-1] < array[start]){
        swap(array,start,end-1);
      }//endif
      
      else if ((end - start)>2){
        medianOfThree(array, start,end);
        int pivot = partition(array,start,end);
        quickSort(array,start,pivot);
        quickSort(array, pivot+1,end);
      }//end else if
      
      //base case with one item, do nothing
    }//end quickSort
    
    
    
    /*******************************************************************
     * hybridQuickSort
     *
     * Purpose: Sort an array using a hybridQuickSort method. Calls a 
     *          helper method that contains the algorithm
     ******************************************************************/
    public static void hybridQuickSort( int[] array ) {
      hybridQuickSort(array,0,array.length);
    }//end hybridQuickSort
    
    
    
    /*******************************************************************
     * hybridQuickSort
     *
     * Purpose: Helper method to recursively sort an array by 
     *          using an inserttion method to sort the array when it has
     *          less than BREAKPOINT elements as the base case but
     *          uses a regular quicksort method otherwise
     ******************************************************************/
    private static void hybridQuickSort(int[] array, int start, int end) {
      if((end-start-1) < BREAKPOINT){
        insertionSort(array,start,end);
      }//endif
      else{
        medianOfThree(array, start,end);
        int pivot = partition(array,start,end);
        hybridQuickSort(array,start,pivot);
        hybridQuickSort(array, pivot+1,end);
      }//endelse
    }//end hybridQuickSort
   
    
    
    /*******************************************************************
     * shellSort
     *
     * Purpose: Sort an array using a shellSort algorithm
     ******************************************************************/
    public static void shellSort( int[] array ) {
      int h = 1;
      int temp;
      
      while (h <= array.length){
        h = h*3 + 1;
      }//endwhile
      
      while(h>0){
        for (int outer=h; outer<array.length; outer++){ //move a[outer] into position
          temp = array[outer];
          int inner = outer;
          while (inner > h-1 && array[inner-h] >= temp){//consider every hth item
            array[inner] = array[inner-h];
            inner -= h;
          }
          array[inner] = temp;
        }//end for
        h = (h-1)/3; //Knuth�s sequence
      }//endwhile
    }//end shellSort
    
/****************** Other miscellaneous methods ********************/

    /*******************************************************************
     * swap
     *
     * Purpose: Swap the items stored in positions i and j in array.
     *
    ******************************************************************/
    private static void swap( int[] array, int i, int j ) {
      int temp = array[ i ];
      array[ i ] = array[ j ];
      array[ j ] = temp;
    } // end swap


    
    /*******************************************************************
     * isSorted
     *
     * Purpose: Return true if the input array is sorted into
     *          ascending order; return false otherwise.
     *
     * Idea: If every item is <= to the item immediately after it,
     *       then the whole list is sorted.
     *
    ******************************************************************/
    public static boolean isSorted( int[] array ) {
      boolean sorted = true;

      // Loop through all adjacent pairs in the
      // array and check if they are in proper order.
      // Stops at first problem found.
      for ( int i = 1; sorted && (i < array.length); i++ )
        sorted = array[i-1] <=  array[i];
      return sorted;
    } // end method isSorted

    /*******************************************************************
     * checkArray
     *
     * Purpose: Print an error message if array is not
     *          correctly sorted into ascending order.
     *          (If the array is correctly sorted, checkArray does nothing.)
     *
    ******************************************************************/
    private static void checkArray(int[] array, String sortType) {
      if ( !isSorted( array ) )
        System.out.println( sortType + " DID NOT SORT CORRECTLY *** ERROR!!" );
    }

    
    
    /*******************************************************************
     * fillArray
     *
     * Purpose: Fills the given array with the numbers 0 to array.length-1.
     *
    ******************************************************************/
    public static void fillArray( int[] array ) {

      for ( int i = 0; i < array.length; i++ ) {
        array[i] = i;
      } // end for

    } // end fillArray

    
    
    /*******************************************************************
     * randomizeArray
     *
     * Purpose: Does numberOfSwaps swaps of randomly-chosen positions
     *          in the given array.
     *
     ******************************************************************/
    public static void randomizeArray( int[] array, int numberOfSwaps ) {
  for ( int count = 0; count < numberOfSwaps; count++ ) {
      int i = generator.nextInt( array.length );
      int j = generator.nextInt( array.length );
      swap( array, i, j );
  }
    } // end randomizeArray


    
    /*******************************************************************
     * arithmeticMean
     *
     * Purpose: Compute the average of long values.
     *          To avoid long overflow, use type double in the computation.
     *
     ******************************************************************/
    public static double arithmeticMean(long data[]) {
      double sum = 0;
      for (int i = 0; i < data.length; i++)
        sum += (double)data[i];
      return sum / (double)data.length;
    } // end arithmeticMean
   
    
    
    /*******************************************************************
     * findMin
     *
     * Purpose: find the minimum value in an array within a bound
     *           of [start,end)
    ******************************************************************/
    private static int findMin(int[] array, int start, int end ) {
      int min = start;
      for(int i = start; i < end; i++){
        if (array[i] < array[min])
          min = i;
      }//end for
      return min;
    }//end findMin
    
    
    
    /*******************************************************************
     * merge
     *
     * Purpose: Helper method to merge the two sorted halves of an array
    ******************************************************************/
    private static void merge(int[] array, int start, int mid, int end, int[] temp){
      int currTop = start;
      int currBott = mid;

      for(int tempCount = start; tempCount < end; tempCount++){
         if((currTop < mid) && (currBott >= end || array[currTop] < array[currBott])){
            temp[tempCount] = array[currTop++];
         }
         else{
            temp[tempCount] = array[currBott++];
         }
      }
      
      for(int i = start; i < end; i++){
         array[i] = temp[i];
      }
    }//end merge
    
    
    
    /*******************************************************************
     * medianOfThree     *
     * Purpose: Helper method to find the median value of the start,
     *          middle and end of an array using a comparison based 
     *          algorithm. Puts median at the start of the array
    ******************************************************************/
    private static void medianOfThree(int[] array, int start, int end){
      int mid = start + ((end - start)/2);
      int pivot = start;
      
      if(array[start] < array[mid]){
        if (array[mid] < array[end-1])
          pivot = mid;
        if (array[start] < array[end-1])
          pivot = end-1;
      }//endif
      else{
        if (array[mid] > array[end-1])
          pivot = mid;
        if (array[start] > array[end-1])
          pivot = end-1;
      }//endelse
      
      swap(array,start,pivot);
    }//end medianOfThree
    
    
    
    /*******************************************************************
    * partition     *
    * Purpose: Helper method to partition the array with all values 
    *          smaller than the pivot at the start, then the pivot
    *          value and finally all values bigger than the pivot.
    *          Returns the index of the pivot after partitioning
    ******************************************************************/
    private static int partition(int[] array, int start, int end){
      int bigStart = start + 1;
      
      for(int curr = start+1; curr < end; curr++){
        if(array[curr] < array[start]){
          swap(array,curr,bigStart++);
        }//endif
      }//endfor
      
      swap(array,start,bigStart-1);
      return bigStart - 1;
    }//end partition
   
} // end class A2Q1SortingTemplate
/*************************************************************************************************************
  * Insertion sort had an average time faster than selection Sort. This is because insertion sort perfoms
  * less comparisons than selection sort. so even though they grow at a similar rate, the consant time
  * operations are more in selection sort leading to a consistently slower algorithm
  * 
  * Quick sort was faster than Insertion sort becasue it has an average growth rate of O(nlogn) which is slower
  * than Insertion sort's growth rate of O(n^2)
  * 
  * Hybrid quick sort was faster than quick sort because it used an insertion sort which is really quick for
  * nearly sorted small arrays instead of continuing the partitioning process.
  * 
  * I would recommend Merge sort, quick sort, hybrid quick sort or shell sort as they have a slower growth rate
  * and run faster than the other methodss. Quick sort variants and shell sort in particular also sort in place 
  * without creating extra arrays which also makes them better than merge sort when  memory usage is a concern
  * 
  * I would discourage the usage of bubble sort for its quadratic growth rate and it being generally the slowest 
  * sorting algortithm examined. I would also discourage a regular insertion sort as it needs to move a large 
  * number of elements in the array in cases where a smaller value is on the other side of the array. INstead, the
  * similar but more efficient shell sort would be a better alternative
  * 
  * 
  * *************************************************************************************************************
  * COMP 2140 A2Q1 Sorting Test --- Summer 2021
  *
  * Array size: 10000
  * Number of swaps: 5000 
  * Number of trials of each sort: 300 

  * Insertion sort mean: 1.638175097E7 ns 
  * Bubble sort mean: 1.6519156403666666E8 ns 
  * Selection sort mean: 5.430107464666667E7 ns 
  * Merge sort mean: 1577930.3933333333 ns 
  * Quick sort mean: 1152607.3466666667 ns 
  * Hybrid quick sort mean: 960787.6366666667 ns 
  * Shell sort mean: 1545678.6733333333 ns 

  * Processing ends normally
 
  * >
  * ************************************************************************************************************/
