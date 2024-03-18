import java.util.ArrayList;
import java.util.Scanner;

public class Temp2 {
    public static void main(String[] args) {
        // ArrayList to store the days of the week
        ArrayList<String> daysOfWeek = new ArrayList<>();
        // Populate days of the week when the ArrayList is created
        daysOfWeek.add("Monday");
        daysOfWeek.add("Tuesday");
        daysOfWeek.add("Wednesday");
        daysOfWeek.add("Thursday");
        daysOfWeek.add("Friday");
        daysOfWeek.add("Saturday");
        daysOfWeek.add("Sunday");

        // ArrayList to store the corresponding temperatures
        ArrayList<Double> temperatures = new ArrayList<>();

        // Scanner object to read user input
        Scanner scnr = new Scanner(System.in);

        String inputDay;
        String userInput;
        String overwriteChoice;
        Double existingTemp;
        double temperature;
        int index;
        
        while (true) {
            // Ask user for the specific day of the week or 'week'
            System.out.print("Enter the day of the week (Monday through Sunday) or 'week' to see weekly summary, 'quit' to exit: ");
            userInput = scnr.next(); // Read input

            // Capitalize the first letter of the input day and convert to lowercase
            inputDay = userInput.substring(0, 1).toUpperCase() + userInput.substring(1).toLowerCase();

            // Check if the user wants to quit
            if (inputDay.equals("Quit") || inputDay.equals("Q")) {
                // Print weekly summary if temperatures have been entered
                if (!temperatures.isEmpty()) {
                    printWeeklySummary(daysOfWeek, temperatures);
                }
                break;
            }

            // Handle the case for displaying weekly summary
            if (inputDay.equals("Week")) {
                // Print weekly summary
                if (!temperatures.isEmpty()) {
                    printWeeklySummary(daysOfWeek, temperatures);
                } else {
                    System.out.println("No temperatures entered yet.");
                }
            } else if (daysOfWeek.contains(inputDay)) {
                index = daysOfWeek.indexOf(inputDay);
                existingTemp = temperatures.size() > index ? temperatures.get(index) : null;

                // Check if temperature for this day already exists
                if (existingTemp != null) {
                    // Prompt the user about overwriting existing data
                    System.out.println("Temperature data for " + inputDay + " already exists (" + existingTemp + "F).");
                    System.out.print("Do you want to overwrite it? (yes/no): ");
                    
                    while (true) {
                        overwriteChoice = scnr.next().toLowerCase();
                        if (overwriteChoice.equals("yes") || overwriteChoice.equals("y")) {
                            break;
                        } else if (overwriteChoice.equals("no") || overwriteChoice.equals("n")) {
                            break;
                        } else {
                            System.out.print("Invalid input! Please enter 'yes' or 'no': ");
                        }
                    }
                    if (overwriteChoice.equals("no") || overwriteChoice.equals("n")) {
                        continue; // Skip to the next iteration of the loop
                    }
                }

                // Input temperature for the specified day
                System.out.print("Enter temperature for " + inputDay + " (-60°F to 150°F): ");
                
                while (true) {
                    if (scnr.hasNextDouble()) {
                        temperature = scnr.nextDouble();
                        if (temperature >= -60 && temperature <= 150) {
                            break; // Valid temperature, exit the loop
                        } else {
                            System.out.println("Temperature must be between -60°F and 150°F. Please try again: ");
                        }
                    } else {
                        // Invalid input, clear scanner buffer
                        System.out.println("Invalid input! Please enter a valid temperature: ");
                        scnr.next(); // Clear scanner buffer
                    }
                }

                // If temperature ArrayList size is less than index + 1, resize it
                while (temperatures.size() < index + 1) {
                    temperatures.add(null);
                }

                temperatures.set(index, temperature); // Add or overwrite the temperature at the correct index

                // Display the entered temperature along with the day of the week
                System.out.println("Temperature for " + inputDay + ": " + temperature + "F");
            } else {
                // Invalid input
                System.out.println("Invalid input! Please enter a valid day of the week or 'week' to see weekly summary, 'quit' to exit.");
            }
        }

        // Close the Scanner
        scnr.close();
    }

    // Method to print weekly summary
    private static void printWeeklySummary(ArrayList<String> daysOfWeek, ArrayList<Double> temperatures) {
        
        String dayOfWeek;
        Double temp;
        double sum;
        double weeklyAverage;
        int i;
        int count;
              
    	// Display temperatures for each day that has been entered
        for (i = 0; i < Math.min(daysOfWeek.size(), temperatures.size()); i++) {
            dayOfWeek = daysOfWeek.get(i);
            temp = temperatures.get(i);
            if (temp != null) { // Check if temperature has been entered
                // Capitalize the first letter of the day of the week
                dayOfWeek = Character.toUpperCase(dayOfWeek.charAt(0)) + dayOfWeek.substring(1);
                System.out.println(dayOfWeek + ": " + temp + "F");
            }
        }
        // Calculate and display the average temperature
        sum = 0;
        count = 0;
        for (Double temp1 : temperatures) {
            if (temp1 != null) { // Check if temperature has been entered
                sum += temp1;
                count++;
            }
        }
        weeklyAverage = (count != 0) ? sum / count : 0; // Prevent division by zero
        System.out.println("Weekly average temperature: " + weeklyAverage + "F");
    }
}