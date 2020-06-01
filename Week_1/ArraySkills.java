import java.util.*;

public class ArraySkills {


   public static void main(String[] args) {
   
      // ***********************
      // For each item below you must code the solution using a plain 
      //  Java array. You may not use any of the methods found in the 
      //  Arrays class or the Collections classes. Manually perform
      //  all operations without relying on system libraries to do the work.
      //
   
      // ***********************
      // Use this array for all operations
      String[] myData;
   
      //  1. Instantiate the given array to hold 10 Strings.
      myData = new String[10];
      
      //  2. Add your name to the Array at index 0 and a friend's name to the 
      //      Array at index 4
      myData[0] = "Steve";
      myData[4] = "Matt";
            
      //  3. Move your friend's name to index 0 (overwriting your name) in the 
      //      array and "delete" their name from index 4
      myData[0] = myData[4];
      myData[4] = null;
      
      //  4. Fill up all of the remaining indeces in the array with more names
      myData[1] = "Karen";
      myData[2] = "Mike";
      myData[3] = "John";
      myData[4] = "Stephanie";
      myData[5] = "Michelle";
      myData[6] = "Diane";
      myData[7] = "Joe";
      myData[8] = "Lola";
      myData[9] = "Allie";
      
      //  5. Swap the values at index 5 and index 1. Put the value at index 5 
      //      in index 1 and the value at index 1 in index 5.
      String temp = myData[1];
      myData[1] = myData[5];
      myData[5] = temp;
      
      //  6. Print the array from beginning to end.
      for (int i = 0; i < myData.length; i++) {
        System.out.println(myData[i]);
      }
      System.out.println("-------------------------------");
      
      //  7. Shuffle the array of strings. In other words, randomize the 
      //      contents of the array like you might a deck of cards. It's OK
      //      to use a Random object or the Math.random() method here.
      Random random = new Random();
      
      for(int i = 0; i < myData.length; i++) {
        int r = random.nextInt(10);
        String t = myData[i];
        myData[i] = myData[r];
        myData[r] = t;
      }
      
      //  8. Find and print the longest and shortest Strings in the array.
      //      It's fine to use Math class methods here.
      String longest = myData[0];
      String shortest = myData[0];
      
      for (int i = 1; i < myData.length; i++) {
        longest = myData[i].length() > longest.length() ? myData[i] : longest;
        shortest = myData[i].length() < shortest.length() ? myData[i] : shortest;
      }
      
      System.out.println("The longest string is " + longest);
      System.out.println("The shortest string is " + shortest);
      System.out.println("-------------------------------");
      
      //  9. Add up the total number of characters in all of the strings in 
      //      the array and print the answer
      int total = 0;
      
      for (int i = 0; i < myData.length; i++) {
        total += myData[i].length();
      }
      
      System.out.println("Total characters in array = " + total);
      System.out.println("-------------------------------");
   
      // 10. Prompt the user for a String and then perform a linear search 
      //      of the array to see if that string is or is not in the array. 
      //      Print if it is or is not found.
      Scanner kb = new Scanner(System.in);
      
      System.out.print("Please enter a name to search for: ");
      String input = kb.nextLine();
      
      String foundMsg = input + " NOT found in myData array";
      
      for (int i = 0; i < myData.length; i++) {
        if (myData[i].equals(input)) {
          foundMsg = input + " found in myData array";
          break;
        };
      };
      
      System.out.println(foundMsg);
      System.out.println("-------------------------------");
      
      // 11. Remove the item at index 4 of the array by shifting everything 
      //      after it one spot over. This is similar to how everybody waiting
      //      in a line steps up one step if somebody in front of them leaves 
      //      the line. This means your array should have one empty index at 
      //      the end after the shift (delete the duplicate item at the end).
      for (int i = 4; i < myData.length; i++) {
        myData[i] = (i + 1) != myData.length ? myData[i + 1] : null;
      }
       
      // 12. Create a new array that is twice as big as the current array and
      //      copy all of the items from the old array to the new array. When 
      //      complete, assign the new bigger array to the myData variable so 
      //      the old array gets garbage collected..
      String[] newArray = new String[myData.length * 2];
      
      for (int i = 0; i < myData.length; i++) {
        newArray[i] = myData[i];
      };
      
      myData = newArray;
      
      // 13. Prompt the user to enter 2 numbers within the range of the array's
      //      length. If the first is larger than the second print backwards 
      //      from that index to the first. If the second is larger than the 
      //      first, print forward in the array from the first index to the second.      
      int firstNum, secondNum;
      int maxNum = myData.length - 1;
      
      do
      {
        System.out.print("Enter a number between 0 - " + maxNum + ": " );
        firstNum = kb.nextInt();
      } while (firstNum < 0 || firstNum > maxNum);
      
      do
      {
        System.out.print("Enter another number between 0 - " + maxNum + ": ");
        secondNum = kb.nextInt();
      } while (secondNum < 0 || secondNum > maxNum);
      
      if (firstNum < secondNum) {
        for (int i = firstNum; i <= secondNum; i++) {
          System.out.println(myData[i]);
        };
      } else if (firstNum > secondNum) {
        for (int i = firstNum; i >= secondNum; i--) {
          System.out.println(myData[i]);
        };
      } else {
        System.out.println("The two numbers entered are equal - no range from array to print.");
      };
   }
}
