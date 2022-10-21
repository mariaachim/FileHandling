import java.util.Scanner;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.lang.Math;

public class App {

    private static Scanner input = new Scanner(System.in);

    static void task1() throws IOException {
        String[][] myStudents = {
            {"Sophie", "Stanfield", "Class 5"}, 
            {"James", "Kitson", "Class 5"},
            {"Zoe", "Lemon", "Class 1"},
            {"Paul", "Johns", "Class 1"},
            {"Freya", "Moore", "Class 5"},
        };
        FileWriter fw = new FileWriter("data.txt");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                fw.write(myStudents[i][j] + ",");
            }
        }
        fw.close();
    }

    static void task2() throws IOException {
        Scanner fileHandler = new Scanner(new File("task2.txt"));
        ArrayList<String> data = new ArrayList<String>(); // arraylist is used because size can be modified
        while (fileHandler.hasNextLine()) {
            data.add(fileHandler.next());
        }
        System.out.println(data.toString());
        fileHandler.close();
    }

    static void task3() throws IOException {
        System.out.println("a) 51 \nb) false \nc) winningBall is used as the index of lotteryNumbers, which must be between 0 and 51, so the return value of the RND() function is multiplied by 50 to give a maxiumum value of a number just under 50 (which returns the integer 49), and 1 is added to it so the number is just under 51 (which returns the integer 50) therefore every element in the array can be accessed");
        Boolean[] lotteryNumbers = new Boolean[51];
        Arrays.fill(lotteryNumbers, Boolean.FALSE);
        int numberOfSelectedBalls = 0;

        while (numberOfSelectedBalls != 6) {
            int winningBall = (int) (Math.random() * 50 + 1);
            if (lotteryNumbers[winningBall] == false) {
                System.out.println("this works");
                lotteryNumbers[winningBall] = true;
                System.out.println("numberOfSelectedBalls is now " + numberOfSelectedBalls);
                numberOfSelectedBalls++;
            }
        }

        FileWriter fw = new FileWriter("winning-lottery-numbers.txt");
        for (int i = 1; i < 50; i++) {
            if (lotteryNumbers[i] == true) {
                System.out.println(i);
                fw.write(i + "\t");
            }
        }
        fw.close();
    }

    static void task4() throws IOException {
        boolean continueProgram = true;
        Scanner fileHandler = new Scanner(new File("oop-terminology.txt"));
        HashMap<String, String> terms = new HashMap<String, String>();

        while (continueProgram == true) {
            System.out.println("1. Search for a term \n2. Search for a keyword in descriptions \n3. End \n\nKey in choice");
            Integer choice = Integer.valueOf(input.nextLine());
            
            while (fileHandler.hasNextLine()) {
                terms.put(fileHandler.nextLine().strip(), fileHandler.nextLine().strip());
            }

            class Procedures {
                String SearchByTerm(String searchTerm) {
                    if (terms.containsKey(searchTerm.strip().toLowerCase())) {
                        return("\nFOUND\n" + searchTerm + ": "+ terms.get(searchTerm) + "\n");
                    } else {
                        return("\nTERM NOT FOUND\n");
                    }
                }

                String SearchDescriptionsForKeyword(String keyword) {
                    boolean found = false;
                    ArrayList<String> matchedTerms = new ArrayList<String>();
                    for (Entry<String, String> entry: terms.entrySet()) {
                        if (entry.getValue().contains(keyword)) {
                            found = true;
                            matchedTerms.add("\nFOUND for " + entry.getKey() + ": " + entry.getValue());
                        }
                    }

                    if (!found) {
                        return("\nDESCRIPTION NOT FOUND\n");
                    } else {
                        return(matchedTerms.stream().collect(Collectors.joining("\n")) + "\n");
                    }
                }
            }

            if (choice == 1) {
                System.out.println("Input search term:");
                String searchTerm = input.nextLine().strip().toLowerCase();
                System.out.println(new Procedures().SearchByTerm(searchTerm));
            
            } else if (choice == 2) {
                System.out.println("Input keyword:");
                String keyword = input.nextLine().strip().toLowerCase();
                System.out.println(new Procedures().SearchDescriptionsForKeyword(keyword));

            } else if (choice == 3) {
                continueProgram = false;
                input.close();
                fileHandler.close();
            
            } else {
                System.out.println("Input not recognised\n");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("File Handling Tasks: \n1: Task 1 \n2: Task 2 \n3: Task 3 \n4: Task 4");
        
        try {
            Integer choice = Integer.valueOf(input.nextLine());
            if (choice == 1) {
                task1();
            } else if (choice == 2) {
                task2();
            } else if (choice == 3) {
                task3();
            } else if (choice == 4) {
                task4();
            } else {
                System.out.println("Input not recognised");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Input not recognised");
            System.exit(0);
        }
    }
}
