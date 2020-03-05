package entity;


import java.time.LocalDate;
import java.util.Objects;

public abstract class Car {

    private String number;
    private CarManufacturer carManufacturer;
    private String model;
    private LocalDate dateOfProduction;
    private int mileage;
    private FuelType fuelType;
    private int seat;
    private boolean working;
    private double initialCost;
    private int lifeTime;

    Car() {
    }

    Car(String number, CarManufacturer carManufacturer, String model, LocalDate dateOfProduction, int mileage, FuelType fuelType, int seat, boolean working, double initialCost, int lifeTime) {
        this.number = number;
        this.carManufacturer = carManufacturer;
        this.model = model;
        this.dateOfProduction = dateOfProduction;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.seat = seat;
        this.working = working;
        this.initialCost = initialCost;
        this.lifeTime = lifeTime;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public CarManufacturer getCarManufacturer() {
        return carManufacturer;
    }

    public void setCarManufacturer(CarManufacturer carManufacturer) {
        this.carManufacturer = carManufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getDateOfProduction() {
        return dateOfProduction;
    }

    public void setDateOfProduction(LocalDate dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public double getInitialCost() {
        return initialCost;
    }

    public void setInitialCost(double initialCost) {
        this.initialCost = initialCost;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return mileage == car.mileage &&
                seat == car.seat &&
                working == car.working &&
                Double.compare(car.initialCost, initialCost) == 0 &&
                lifeTime == car.lifeTime &&
                Objects.equals(number, car.number) &&
                carManufacturer == car.carManufacturer &&
                Objects.equals(model, car.model) &&
                Objects.equals(dateOfProduction, car.dateOfProduction) &&
                fuelType == car.fuelType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Гос.номер='" + number + '\'' +
                ", Производитель=" + carManufacturer +
                ", Модель='" + model + '\'' +
                ", Дата производства=" + dateOfProduction +
                ", Пробег=" + mileage +
                ", Тип топлива=" + fuelType +
                ", Количество сидений=" + seat +
                ", Статус работы=" + working +
                ", Первоначальная стоимость=" + initialCost +
                ", Срок использования=" + lifeTime;

    }


}
