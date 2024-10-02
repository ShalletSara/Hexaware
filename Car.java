package com.hexaware.concreteclasses;

import com.hexaware.abstractclasses.Vehicle;

public class Car extends Vehicle {

    public Car(String name, double rentalPrice) {
        super(name, rentalPrice);
    }

    @Override
    public void rentVehicle() {
        if (!isRented()) {
            setRented(true);
            System.out.println(getName() + " (Car) has been rented at price: " + getRentalPrice());
        } else {
            System.out.println(getName() + " (Car) is already rented.");
        }
    }

    @Override
    public void returnVehicle() {
        if (isRented()) {
            setRented(false);
            System.out.println(getName() + " (Car) has been returned.");
        } else {
            System.out.println(getName() + " (Car) was not rented.");
        }
    }
}
