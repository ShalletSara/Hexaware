package com.hexaware.mainvehicleprogram;

import com.hexaware.abstractclasses.Vehicle;

public class User {
    private String name;
    private Vehicle[] rentedVehicles;
    private int rentedVehicleCount;

    // Constructor
    public User(String name, int maxRentedVehicles) {
        this.name = name;
        this.rentedVehicles = new Vehicle[maxRentedVehicles];  // Array to store rented vehicles
        this.rentedVehicleCount = 0;  // No vehicles rented initially
    }

    // Get user's name
    public String getName() {
        return name;
    }

    // Rent a vehicle
    public void rentVehicle(Vehicle vehicle) {
        if (rentedVehicleCount < rentedVehicles.length) {
            rentedVehicles[rentedVehicleCount++] = vehicle;
            vehicle.setRented(true);  // Mark the vehicle as rented
            System.out.println(name + " has rented " + vehicle.getName());
        } else {
            System.out.println(name + " cannot rent more vehicles (limit reached).");
        }
    }

    // Return a vehicle
    public void returnVehicle(String vehicleName) {
        boolean found = false;
        for (int i = 0; i < rentedVehicleCount; i++) {
            if (rentedVehicles[i].getName().equalsIgnoreCase(vehicleName)) {
                System.out.println(name + " has returned " + rentedVehicles[i].getName());
                rentedVehicles[i].setRented(false);  // Mark the vehicle as returned

                // Shift the remaining vehicles in the array
                for (int j = i; j < rentedVehicleCount - 1; j++) {
                    rentedVehicles[j] = rentedVehicles[j + 1];
                }
                rentedVehicles[--rentedVehicleCount] = null;
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(vehicleName + " not found in rented vehicles.");
        }
    }

    // View rented vehicles
    public void viewRentedVehicles() {
        if (rentedVehicleCount == 0) {
            System.out.println(name + " has no rented vehicles.");
        } else {
            System.out.println(name + " has rented the following vehicles:");
            for (int i = 0; i < rentedVehicleCount; i++) {
                System.out.println(rentedVehicles[i].getName());
            }
        }
    }
}
