package service;

import exception.ServiceException;
import entity.*;
import validator.Validator;

import java.time.LocalDate;
import java.util.*;

class CarFactory {

    private Validator validator = new Validator();

    ArrayList<Car> getCars(String listCars) throws ServiceException {
        ArrayList<Car> cars = new ArrayList<>();
        return checkDuplicate(listCars, cars);
    }

    private ArrayList<Car> checkDuplicate(String listCars, ArrayList<Car> cars) throws ServiceException {
        getCarsFromFile(listCars, cars);
        cars.sort(Comparator.comparing(Car::getNumber));

        for (int i = 0; i < cars.size() - 1; i++) {
            if (cars.get(i).hashCode() == cars.get(i + 1).hashCode()) {
                throw new ServiceException("Введен дублирующийся номер автомобиля " + cars.get(i).getNumber() +
                        ". Исправьте запись в fileWithCars.txt");
            }
        }
        return cars;
    }

    void checkDuplicate(List<Car> cars, String number) throws ServiceException {
        for (Car car : cars) {
            if (car.getNumber().equals(number)) {
                throw new ServiceException("Введен дублирующийся номер автомобиля " + car.getNumber());
            }
        }
    }

    //метод осуществляет получение одной строчки из текстового файла и разбиение её на отдельные части с занисением в массив
    private void getCarsFromFile(String listCar, ArrayList<Car> carsList) throws ServiceException {
        String[] cars = listCar.split("\n");
        for (String line : cars) {
            String[] carDetails = line.trim().split(";");
            Queue<String> carDetailsQueue=new LinkedList<>();
            carDetailsQueue.addAll(Arrays.asList(carDetails));
            if (!carDetails[0].equals("")) {
                carsList.add(checkAndCreateCar(carDetailsQueue));
            }
        }

    }

    //метод отвечает за определение типа транспортного средства, прорверку входящих данных
// и создание транспортных средств определенного типа
    Car checkAndCreateCar(Queue<String> carDetails) throws ServiceException {
        int size=carDetails.size();
        if (size == 10 || size == 14) {
            String number = validator.validateNumber(carDetails.remove());
            CarManufacturer carManufacturer = validator.validateCarManufacturer(carDetails.remove());
            String model = carDetails.remove();
            LocalDate date = validator.validateDate(carDetails.remove());
            int mileage = validator.validateInt(carDetails.remove(), 0);
            FuelType fuelType = validator.validateFuelType(carDetails.remove());
            int seat = validator.validateInt(carDetails.remove(), 0);
            boolean working = validator.validateBoolean(carDetails.remove());
            double price = validator.validateDouble(carDetails.remove());
            int lifeTime = validator.validateInt(carDetails.remove(), 0);
            if (size == 14) {
                int carryingCapacityInKg = validator.validateInt(carDetails.remove(), 0);
                int baggageHighInMM = validator.validateInt(carDetails.remove(), 0);
                int baggageWidthInMM = validator.validateInt(carDetails.remove(), 0);
                int baggageLengthInMM = validator.validateInt(carDetails.remove(), 0);
                return createTruck(number, carManufacturer, model, date, mileage, fuelType, seat, working, price, lifeTime,
                        carryingCapacityInKg, baggageHighInMM, baggageWidthInMM, baggageLengthInMM);
            }
            return createCar(number, carManufacturer, model, date, mileage, fuelType, seat, working, price, lifeTime);
        } else {
            throw new ServiceException("Нехватка или излишки данных об авто." +
                    "Данные должны быть введены согласно шаблону и разделены  ; . " +
                    "Шаблон для легкового авто: госномер(1234-AA-1);Производитель(на английском языке, с заглавной" +
                    " буквы);Модель авто;дата производства(2000-01-02);" +
                    "пробег;Вид топлива(на английском и с большой буквы);количество сидений;" +
                    "Работает ил не работает авто(true,false);стоимость авто;срок службы в месяцах" +
                    "Шаблон для грузового авто: госномер(1234-AA-1);Производитель(на английском языке, с заглавной буквы);" +
                    "дата производства(2000-01-02);" +
                    "пробег;Вид топлива(на английском и с большой буквы);" +
                    "количество сидений;Работает или не работает авто(true,false);" +
                    "стоимость авто;срок службы в месяцах;грузоподъёмность(в кг);" +
                    "высота грузового отсека;ширина;длинна; ");
        }
    }

    private Car createTruck(String number, CarManufacturer carManufacturer, String model, LocalDate date, int mileage,
                            FuelType fuelType, int seat, boolean working, double price, int lifeTime,
                            int carryingCapacityInKg, int baggageHighInMM, int baggageWidthInMM, int baggageLengthInMM) {
        Truck car = new Truck();
        setDetailsCar(number, carManufacturer, model, date, mileage, fuelType, seat, working, price, lifeTime, car);
        car.setCarryingCapacity(carryingCapacityInKg);
        car.setBaggageHigh(baggageHighInMM);
        car.setBaggageWidth(baggageWidthInMM);
        car.setBaggageLength(baggageLengthInMM);
        return car;
    }

    //метод отвечает за создание легкого авто либо микроавтобуса
    private Car createCar(String number, CarManufacturer carManufacturer, String model, LocalDate date,
                          int mileage, FuelType fuelType, int seat, boolean working, double price, int lifeTime) {
        Car car;
        if (seat < 6) {
            car = new SmallCar();
            setDetailsCar(number, carManufacturer, model, date,
                    mileage, fuelType, seat, working, price, lifeTime, car);
        } else {
            car = new Van();
            setDetailsCar(number, carManufacturer, model, date,
                    mileage, fuelType, seat, working, price, lifeTime, car);
        }

        return car;
    }

    private void setDetailsCar(String number, CarManufacturer carManufacturer, String model, LocalDate date,
                               int mileage, FuelType fuelType, int seat, boolean working, double price, int lifeTime, Car car) {
        car.setNumber(number);
        car.setCarManufacturer(carManufacturer);
        car.setModel(model);
        car.setDateOfProduction(date);
        car.setMileage(mileage);
        car.setFuelType(fuelType);
        car.setSeat(seat);
        car.setWorking(working);
        car.setInitialCost(price);
        car.setLifeTime(lifeTime);
    }

    //метод отвечает за создание грузового автомобиля


}
