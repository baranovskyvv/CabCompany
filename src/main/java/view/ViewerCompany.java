package view;

import entity.Car;

import java.util.List;

public interface ViewerCompany {
    String showMainMenu();

    void exit();

    void gettingWrongValueInMainMenu();

    void showCars(List cars);

    void showDepreciation(Double depreciation);

    String showSortMenu();

    String[] showFindMenu();

    String getValue();

    String showMenuForAdd();

    String getNumber();

    void printException(Exception e);

    void showInfoCar(Car car);

    void showInfoTruck(Car car);

    String getAnswerForUpdate(String choice);

    String showAddCar();

    String showAddTruck();
}
