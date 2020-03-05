package entity;

import java.time.LocalDate;

public class Truck extends Car {

    private int carryingCapacity;
    private int baggageHigh;
    private int baggageWidth;
    private int baggageLength;

    public int getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public int getBaggageHigh() {
        return baggageHigh;
    }

    public void setBaggageHigh(int baggageHigh) {
        this.baggageHigh = baggageHigh;
    }

    public int getBaggageWidth() {
        return baggageWidth;
    }

    public void setBaggageWidth(int baggageWidth) {
        this.baggageWidth = baggageWidth;
    }

    public  int getBaggageLength() {
        return baggageLength;
    }

    public void setBaggageLength(int baggageLength) {
        this.baggageLength = baggageLength;
    }

    public Truck() {
    }

    public Truck(String number, CarManufacturer carManufacturer, String model, LocalDate dateOfProduction, int mileage,
                 FuelType fuelType, int seat, boolean working, double initialCost, int lifeTime,
                 int carryingCapacity, int baggageHigh, int baggageWidth, int baggageLength) {
        super(number, carManufacturer, model, dateOfProduction, mileage, fuelType, seat, working, initialCost, lifeTime);
        this.carryingCapacity = carryingCapacity;
        this.baggageHigh = baggageHigh;
        this.baggageWidth = baggageWidth;
        this.baggageLength = baggageLength;
    }

    @Override
    public String toString() {
        return
                "Грузовик{" + super.toString() +", "+
                        "Грузоподъемность=" + carryingCapacity +
                        "Багажное отделение:"+
                        "Высота =" + baggageHigh +
                        ", Ширина=" + baggageWidth +
                        ", Длинна=" + baggageLength +
                        '}' + "\n";
    }


}
