package controller;

import exception.ServiceException;
import entity.Car;
import entity.Truck;
import service.ServiceCabCompany;
import view.Console;
import view.ViewerCompany;


import java.util.List;

public class Director {
    private ViewerCompany view = new Console();
    private ServiceCabCompany service;

    {
        try {
            service = new ServiceCabCompany();
        } catch (ServiceException e) {
            view.printException(e);
        }
    }


    public void start() {
        String choice = view.showMainMenu();
        manageMenu(choice);
        start();
    }

    private void manageMenu(String function) {
        switch (function) {
            case "1":
                view.showCars(showList());
                break;
            case "2":
                view.showDepreciation(getDepreciation());
                break;
            case "3":
                view.showCars(sortCars());
                break;
            case "4":
                view.showCars(findCar());
                break;
            case "5":
                addCar();
                break;
            case "6":
                removeCar();
                break;
            case "7":
                updateCar();
                break;
            case "0":
                view.exit();
                System.exit(0);
            default:
                view.gettingWrongValueInMainMenu();

        }
    }

    private void updateCar() {
        String number = view.getNumber();
        Car car = service.getCarForUpdate(number);
        if (car instanceof Truck) {
            view.showInfoTruck(car);
        } else {
            view.showInfoCar(car);
        }
        String choice = view.getValue();
        String newValue = view.getAnswerForUpdate(choice);
        try {
            service.update(choice, newValue, car);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    private void removeCar() {
        String number = view.getNumber();
        try {
            service.removeCar(number);
        } catch (ServiceException e) {
            view.printException(e);
        }
    }

    private void addCar() {
        String typeCar = view.showMenuForAdd();
        if (typeCar.equals("1")) {
            String value=view.showAddCar();
            try {
                service.addCar(value);
            } catch (ServiceException e) {
                view.printException(e);
            }
        } else if (typeCar.equals("2")) {
            String value=view.showAddTruck();
            try {
                service.addCar(value);
            } catch (ServiceException e) {
                view.printException(e);
            }
        }

    }

    private List findCar() {
        String[] values = view.showFindMenu();
        try {
            return service.findCar(values);
        } catch (ServiceException e) {
            view.printException(e);
        }
        return null;
    }

    private List sortCars() {
        String function = view.showSortMenu();
        try {
            return service.sortCars(function);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Double getDepreciation() {
        return service.getDepreciation();
    }

    private List showList() {
        return service.showList();
    }

}
