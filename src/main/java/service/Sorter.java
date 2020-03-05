package service;

import entity.Car;

import java.util.*;

//класс отвечает за перегрузку метода sort, где можно вносить от 1 до 5 параметров сортировки
class Sorter {

     Map<String, Comparator<Car>> comparators;
    Sorter() {
        this.comparators = new HashMap<>();

        Comparator<Car> byNumber = Comparator.comparing(Car::getNumber);

        Comparator<Car> byCarManufacturer = Comparator.comparing(Car::getCarManufacturer);

        Comparator<Car> byModel = Comparator.comparing(Car::getModel);

        Comparator<Car> byDateOfProduction = Comparator.comparing(Car::getDateOfProduction);

        Comparator<Car> byMileage = Comparator.comparingInt(Car::getMileage);

        Comparator<Car> byFuelType = Comparator.comparing(Car::getFuelType);

        Comparator<Car> bySeat = Comparator.comparingInt(Car::getSeat);

        Comparator<Car> byWorking = Comparator.comparing(Car::isWorking);

        Comparator<Car> byInitialCost = Comparator.comparingDouble(Car::getInitialCost);

        Comparator<Car> byLifeTime = Comparator.comparingInt(Car::getLifeTime);

        comparators.put("1", byNumber);
        comparators.put("2", byCarManufacturer);
        comparators.put("3", byModel);
        comparators.put("4", byDateOfProduction);
        comparators.put("5", byMileage);
        comparators.put("6", byFuelType);
        comparators.put("7", bySeat);
        comparators.put("8", byWorking);
        comparators.put("9", byInitialCost);
        comparators.put("10", byLifeTime);
    }

    List<Car> sort(List<Car> arrayCar, Comparator<Car> anyComp1, Comparator<Car> anyComp2, Comparator<Car> anyComp3,
                   Comparator<Car> anyComp4, Comparator<Car> anyComp5) {
        arrayCar.sort(anyComp1.thenComparing(anyComp2.thenComparing(anyComp3.thenComparing(anyComp4).thenComparing(anyComp5))));
        return  arrayCar;
    }

    List<Car> sort(List<Car> arrayCar, Comparator<Car> anyComp1, Comparator<Car> anyComp2, Comparator<Car> anyComp3,
              Comparator<Car> anyComp4) {
        arrayCar.sort(anyComp1.thenComparing(anyComp2.thenComparing(anyComp3.thenComparing(anyComp4))));
        return  arrayCar;
    }

    List<Car> sort(List<Car> arrayCar, Comparator<Car> anyComp1, Comparator<Car> anyComp2, Comparator<Car> anyComp3) {
        arrayCar.sort(anyComp1.thenComparing(anyComp2.thenComparing(anyComp3)));
        return  arrayCar;
    }

    List<Car> sort(List<Car> arrayCar, Comparator<Car> anyComp1, Comparator<Car> anyComp2) {
        arrayCar.sort(anyComp1.thenComparing(anyComp2));
        return  arrayCar;
    }

    List<Car> sort(List<Car> arrayCar, Comparator<Car> anyComp1) {
        arrayCar.sort(anyComp1);
        return  arrayCar;
    }
}
