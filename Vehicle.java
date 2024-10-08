package com.hexaware.abstractclasses;

public abstract class Vehicle {
    private String name;
    private double rentalPrice;
    private boolean isRented;

    public Vehicle(String name, double rentalPrice) {
        this.name = name;
        this.rentalPrice = rentalPrice;
        this.isRented = false; // default to not rented
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    // Abstract methods to be implemented by child classes
    public abstract void rentVehicle();
    public abstract void returnVehicle();
}
