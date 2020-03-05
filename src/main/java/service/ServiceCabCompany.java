package service;

import dao.TransportDao;
import dao.TransportDaoImpl;
import exception.DAOException;
import exception.ServiceException;
import entity.Car;
import entity.CarManufacturer;
import entity.FuelType;
import entity.Truck;
import validator.Validator;

import java.time.LocalDate;
import java.util.*;


public class ServiceCabCompany {

    private Validator validator = new Validator();
    private TransportDao transportDao = new TransportDaoImpl();
    private ArrayList<Car> cars;

    public ServiceCabCompany() throws ServiceException {
        CarFactory carFactory = new CarFactory();
        String s;
        try {
            s = transportDao.read();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        this.cars = carFactory.getCars(s);
    }

    public List<Car> showList() {
        return cars;
    }

    public Double getDepreciation() {
        Depreciation depreciation = new Depreciation();
        double deprecations = 0;
        for (Car car : cars) {
            double price = car.getInitialCost();
            int lifeTime = car.getLifeTime();
            LocalDate past = car.getDateOfProduction();
            deprecations = deprecations + depreciation.calculateDepreciation(price, lifeTime, past);
        }
        return deprecations;
    }

    public List<Car> sortCars(String function) throws ServiceException {

        Sorter sorter = new Sorter();
        List<Car> sortCars = new ArrayList<>(cars);
        String[] values = function.trim().split(",");
        int length = values.length;
        if (length > 5) {
            throw new ServiceException("Введено больше 5 значений в параметры сортировки");
        }
        for (String value : values) {
            validator.validateInt(value, 0, 5);
        }
        if (length == 1) {
            return sorter.sort(sortCars, sorter.comparators.get(values[0]));
        } else if (length == 2) {
            return sorter.sort(sortCars, sorter.comparators.get(values[0]), sorter.comparators.get(values[1]));
        } else if (length == 3) {
            return sorter.sort(sortCars, sorter.comparators.get(values[0]), sorter.comparators.get(values[1]),
                    sorter.comparators.get(values[2]));
        } else if (length == 4) {
            return sorter.sort(sortCars, sorter.comparators.get(values[0]), sorter.comparators.get(values[1]),
                    sorter.comparators.get(values[2]), sorter.comparators.get(values[3]));
        } else if (length == 5) {
            return sorter.sort(sortCars, sorter.comparators.get(values[0]), sorter.comparators.get(values[1]),
                    sorter.comparators.get(values[2]), sorter.comparators.get(values[3]), sorter.comparators.get(values[4]));
        }
        return sortCars;
    }

    public List findCar(String[] values) throws ServiceException {
        Finder finder = new Finder(cars);
        try {
            int seat = validator.validateInt(values[0], 0);
            int weight = validator.validateInt(values[1], 0);
            return finder.findValues(seat, weight);
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void addCar(String line) throws ServiceException {
        CarFactory carFactory = new CarFactory();
        Queue<String> carDetailsQueue = new LinkedList<>();
        String[] carDetails = line.trim().split(";");
        Collections.addAll(carDetailsQueue, carDetails);
        if (!carDetails[0].equals("")) {
            String number = carDetails[0];
            carFactory.checkDuplicate(cars, number);
            try {
                cars.add(carFactory.checkAndCreateCar(carDetailsQueue));
            } catch (Exception e) {
                e.printStackTrace();
            }
            writeToFile();
        }
    }

    public void removeCar(String number) throws ServiceException {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getNumber().equals(number)) {
                cars.remove(i);
                break;
            }
        }
        writeToFile();
    }

    public Car getCarForUpdate(String number) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getNumber().equals(number)) {
                Car car = cars.get(i);
                cars.remove(i);
                return car;
            }
        }
        return null;
    }

    public void update(String choice, String newValue, Car car) throws ServiceException {
        CarFactory carFactory = new CarFactory();
        int num = validator.validateInt(choice, 1, 14);
        try {
            switch (num) {
                case 1:
                    String newNumber = validator.validateNumber(newValue);
                    carFactory.checkDuplicate(cars, newNumber);
                    car.setNumber(newValue);
                    break;
                case 2:
                    CarManufacturer manufacturer = validator.validateCarManufacturer(newValue);
                    car.setCarManufacturer(manufacturer);
                    break;
                case 3:
                    car.setModel(newValue);
                    break;
                case 4:
                    LocalDate date = validator.validateDate(newValue);
                    car.setDateOfProduction(date);
                    break;
                case 5:
                    int mileage = validator.validateInt(newValue, 0);
                    car.setMileage(mileage);
                    break;
                case 6:
                    FuelType fuelType = validator.validateFuelType(newValue);
                    car.setFuelType(fuelType);
                    break;
                case 7:
                    int seat = validator.validateInt(newValue, 0);
                    car.setSeat(seat);
                    break;
                case 8:
                    boolean working = validator.validateBoolean(newValue);
                    car.setWorking(working);
                    break;
                case 9:
                    double price = validator.validateDouble(newValue);
                    car.setInitialCost(price);
                    break;
                case 10:
                    int lifeTime = validator.validateInt(newValue, 0);
                    car.setLifeTime(lifeTime);
                    break;
            }
            if (car instanceof Truck) {
                switch (num) {
                    case 11:
                        int carryingCapacity = validator.validateInt(newValue, 0);
                        ((Truck) car).setCarryingCapacity(carryingCapacity);
                        break;
                    case 12:
                        int high = validator.validateInt(newValue, 0);
                        ((Truck) car).setBaggageHigh(high);
                        break;
                    case 13:
                        int width = validator.validateInt(newValue, 0);
                        ((Truck) car).setBaggageWidth(width);

                        break;
                    case 14:
                        int length = validator.validateInt(newValue, 0);
                        ((Truck) car).setBaggageLength(length);
                        break;
                }
            }
        } catch (ServiceException e) {
            cars.add(car);
            throw new ServiceException(e.getMessage());
        }
        cars.add(car);
        writeToFile();

    }

    private void writeToFile() throws ServiceException {
        StringBuilder file = new StringBuilder();
        for (Car car : cars) {
            String number = car.getNumber();
            String carManufacturer = String.valueOf(car.getCarManufacturer());
            String model = car.getModel();
            String date = String.valueOf(car.getDateOfProduction());
            String mileage = String.valueOf(car.getMileage());
            String fuelType = String.valueOf(car.getFuelType());
            String seat = String.valueOf(car.getSeat());
            String working = String.valueOf(car.isWorking());
            String price = String.valueOf(car.getInitialCost());
            String lifeTime = String.valueOf(car.getLifeTime());
            if (car instanceof Truck) {
                String carryingCapacityInKg = String.valueOf(((Truck) car).getCarryingCapacity());
                String baggageHighInMM = String.valueOf(((Truck) car).getBaggageHigh());
                String baggageWidthInMM = String.valueOf(((Truck) car).getBaggageWidth());
                String baggageLengthInMM = String.valueOf(((Truck) car).getBaggageLength());
                file.append(number).append(";").append(carManufacturer).append(";").append(model).append(";")
                        .append(date).append(";").append(mileage).append(";").append(fuelType).append(";").append(seat).append(";")
                        .append(working).append(";").append(price).append(";").append(lifeTime).append(";").append(carryingCapacityInKg)
                        .append(";").append(baggageHighInMM).append(";").append(baggageWidthInMM).append(";").append(baggageLengthInMM).append("\n");
            } else
                file.append(number).append(";").append(carManufacturer).append(";").append(model).append(";")
                        .append(date).append(";").append(mileage).append(";").append(fuelType).append(";").append(seat).append(";")
                        .append(working).append(";").append(price).append(";").append(lifeTime).append(";").append("\n");
        }
        try {
            transportDao.write(String.valueOf(file));
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }
}
