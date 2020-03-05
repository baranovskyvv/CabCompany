package service;

import entity.Car;
import entity.Truck;

import java.util.List;
import java.util.stream.Collectors;

class Finder {

    private final List<Car> list;

    Finder(List<Car> list) {
        this.list = list;
    }

    //метод ищет грузовые авто, по необходимому количеству сидений и грузоподъемности
    List<Car> findValues(int seat, int carryingCapacity) {
        return list.stream()
                .filter(car -> car instanceof Truck &&
                        car.getSeat() >= seat &&
                        ((Truck) car).getCarryingCapacity() > carryingCapacity)
                .collect(Collectors.toList());
    }

}
