package com.hexaware.mainvehicleprogram;

import com.hexaware.concreteclasses.Car;
import com.hexaware.concreteclasses.Bike;
import com.hexaware.concreteclasses.Truck;
import com.hexaware.abstractclasses.Vehicle;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Define a fixed-size array to hold vehicles
        Vehicle[] vehicles = new Vehicle[5];
        vehicles[0] = new Car("Sedan", 500);
        vehicles[1] = new Bike("Yamaha", 150);
        vehicles[2] = new Truck("Ford", 1000);
        vehicles[3] = new Car("Hatchback", 450);
        vehicles[4] = new Bike("Ducati", 200);

        // Create a user
        User user = new User("John Doe", 3);  // User can rent up to 3 vehicles

        while (true) {
            System.out.println("\n--- Vehicle Rental System ---");
            System.out.println("1. View Available Vehicles");
            System.out.println("2. Rent a Vehicle");
            System.out.println("3. Return a Vehicle");
            System.out.println("4. View Rented Vehicles");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Vehicles:");
                    for (Vehicle vehicle : vehicles) {
                        if (vehicle != null && !vehicle.isRented()) {
                            System.out.println(vehicle.getName() + " - Price: " + vehicle.getRentalPrice());
                        }
                    }
                    break;
                case 2:
                    System.out.println("\nEnter vehicle name to rent:");
                    String rentName = scanner.next();
                    boolean found = false;

                    // Loop through the array to rent a vehicle
                    for (Vehicle vehicle : vehicles) {
                        if (vehicle != null && vehicle.getName().equalsIgnoreCase(rentName)) {
                            if (!vehicle.isRented()) {
                                user.rentVehicle(vehicle);
                            } else {
                                System.out.println(vehicle.getName() + " is already rented.");
                            }
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Vehicle not found.");
                    }
                    break;
                case 3:
                    System.out.println("\nEnter vehicle name to return:");
                    String returnName = scanner.next();
                    user.returnVehicle(returnName);
                    break;
                case 4:
                    user.viewRentedVehicles();
                    break;
                case 5:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
