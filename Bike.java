package com.hexaware.concreteclasses;

import com.hexaware.abstractclasses.Vehicle;

public class Bike extends Vehicle {

    public Bike(String name, double rentalPrice) {
        super(name, rentalPrice);
    }

    @Override
    public void rentVehicle() {
        if (!isRented()) {
            setRented(true);
            System.out.println(getName() + " (Bike) has been rented at price: " + getRentalPrice());
        } else {
            System.out.println(getName() + " (Bike) is already rented.");
        }
    }

    @Override
    public void returnVehicle() {
        if (isRented()) {
            setRented(false);
            System.out.println(getName() + " (Bike) has been returned.");
        } else {
            System.out.println(getName() + " (Bike) was not rented.");
        }
    }
}
