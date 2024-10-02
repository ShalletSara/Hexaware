package com.hexaware.concreteclasses;

import com.hexaware.abstractclasses.Vehicle;

public class Truck extends Vehicle {

    public Truck(String name, double rentalPrice) {
        super(name, rentalPrice);
    }

    @Override
    public void rentVehicle() {
        if (!isRented()) {
            setRented(true);
            System.out.println(getName() + " (Truck) has been rented at price: " + getRentalPrice());
        } else {
            System.out.println(getName() + " (Truck) is already rented.");
        }
    }

    @Override
    public void returnVehicle() {
        if (isRented()) {
            setRented(false);
            System.out.println(getName() + " (Truck) has been returned.");
        } else {
            System.out.println(getName() + " (Truck) was not rented.");
        }
    }
}
