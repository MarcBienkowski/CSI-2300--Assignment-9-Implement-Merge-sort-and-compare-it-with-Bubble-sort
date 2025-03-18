import java.util.ArrayList;
import java.util.Scanner;

public class App {
    

    public static ArrayList<Integer> Random_Array_Generator(int array_size){
        ArrayList<Integer> Array = new ArrayList<Integer>();
        for(int index = 0; index <= array_size ;index++){
            int random_number = (int)(Math.random() * 101);
            Array.add(random_number);
        }
        return Array;
    }
    public static ArrayList<Integer> Bubble_Sort(ArrayList<Integer> array){
        ArrayList<Integer> sorted_array = new ArrayList<>(array); 
    
        boolean change_made; 
    
        do {
            change_made = false;  
            for (int index = 0; index < sorted_array.size() - 1; index++) {
                if (sorted_array.get(index) > sorted_array.get(index + 1)) {
    
                    int temp = sorted_array.get(index);
                    sorted_array.set(index, sorted_array.get(index + 1));
                    sorted_array.set(index + 1, temp);

                    change_made = true;
                }
            }
        } while (change_made);  
    
        return sorted_array;
    }
    public static ArrayList<Integer> Merge_Sort_Array_Splitter(ArrayList<Integer> array) {
        
        if (array.size() <= 1){
            return array;
        }

        int element_total = array.size();
        int element_chunk = (int)Math.floor(element_total / 2);
        ArrayList<Integer> L = new ArrayList<>(array.subList(0, element_chunk));
        ArrayList<Integer> R = new ArrayList<>(array.subList(element_chunk, element_total));

        L = Merge_Sort_Array_Splitter(L);
        R = Merge_Sort_Array_Splitter(R);

        return Merge_Sort_Merger(L,R);
    }
    public static ArrayList<Integer> Merge_Sort_Merger(ArrayList<Integer> array_L, ArrayList<Integer> array_R) {
        ArrayList<Integer> sorted = new ArrayList<Integer>();
        
        int L_index = 0;
        int R_index = 0;
        
        for(; L_index < array_L.size() && R_index < array_R.size() ;) {
            int L_value = array_L.get(L_index);
            int R_value = array_R.get(R_index);
            if(L_value <= R_value){
                sorted.add(L_value);
                L_index++;
            }else {
                sorted.add(R_value);
                R_index++;
            }
        }

        //adds potential L leftover
        for(; L_index < array_L.size() ; L_index++) {
            int L_value = array_L.get(L_index);
            sorted.add(L_value);
        }

        //adds potential R leftover
        for(; R_index < array_R.size() ; R_index++) {
            int R_value = array_R.get(R_index);
            sorted.add(R_value);
        }

        return sorted; 
    }
    
        
    public static void main(String[] args) throws Exception {
        String response;
        int converted_response;
        long start_time;
        long end_time;
        double time_bubble;
        double time_merge;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Merge sort and Bubble sort comparer program \n");

        while(true) {    
            System.out.println("Please enter a integer value and I will generate an array of that size with values ranging from 0 to 100");
            response = scanner.nextLine();

            try {
                converted_response = Integer.parseInt(response);
            } catch(NumberFormatException e) {
                System.out.println("ERROR: please enter an integer number");
                continue;
            }
            
            //generate random array of user inputted size
            ArrayList<Integer> random_array = new ArrayList<Integer>();
            random_array = Random_Array_Generator(converted_response);
            System.out.println("finished generating random array");

            //get bubble sort time
            start_time = System.currentTimeMillis();
            Bubble_Sort(random_array);
            System.out.println("Finished bubble sort");
            end_time = System.currentTimeMillis();
            time_bubble = (end_time - start_time) / 1000.0;

            

            //get merge sort time
            start_time = System.currentTimeMillis();
            Merge_Sort_Array_Splitter(random_array);
            System.out.println("finished merge sort");
            end_time = System.currentTimeMillis();
            time_merge = (end_time - start_time) / 1000.0;
           


            System.out.println("The time took to sort the array via bubble sort was " + time_bubble + " seconds.");
            System.out.println("The time took to sort the array via merge sort was " + time_merge + " seconds.");

            while(true) { //exit code
                System.out.println("Type in \"continue\" to restart the program or type in \"exit\" to close the program");
                response = scanner.nextLine();
                switch(response){
                    case "exit":
                        scanner.close();
                        System.exit(0);
                    case "continue":
                        break;
                    default:
                        System.out.println("Please enter a valid key word");
                        continue;
                }
                break;
            }



        }
            



    }
}
