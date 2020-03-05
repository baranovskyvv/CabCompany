package view;


import entity.Car;
import entity.Truck;
import util.PrinterCabCompany;
import util.PrinterConsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Console implements ViewerCompany {
    private static PrinterCabCompany printer = new PrinterConsole();

    static {
        printer.print("Добрый день! Вас приветствует программа \"Таксопарк 3000\"!");

    }

    @Override
    public String showMainMenu() {
        printer.print("Введите номер функции которая для Вас необходима:" + "\n" +
                "1. Вывод всего автотранспорта." + "\n" +
                "2. Рассчет стоимости автопарка на сегодняшний день." + "\n" +
                "3. Сортировка автопарка по необходимым критериям." + "\n" +
                "4. Поиск грузового транспорта по количеству сидений и грузоподъемности" + "\n" +
                "5. Добавление транспортной единицы." + "\n" +
                "6. Удаление транспортной единицы" + "\n" +
                "7. Измененеие характеристик транспортной единицы" + "\n" +
                "0. Выход");
        return getValue();
    }

    @Override
    public void exit() {
        printer.print("Спасибо, что пользуетесь нашим программным продуктом!");
    }

    @Override
    public void gettingWrongValueInMainMenu() {
        printer.print("Ошибка. Данного значения не существует. Проверьте номер функции и попробуйте еще раз.");
    }

    @Override
    public void showCars(List cars) {
        printer.print(cars.toString());
    }

    @Override
    public void showDepreciation(Double depreciation) {
        printer.print("Остаточная стоимость на сегодняшний день составляет: " + new DecimalFormat("#.##")
                .format(depreciation));
    }

    @Override
    public String showSortMenu() {
        printer.print("Выберите переменные(не больше 5) по которым будет проводиться сортировка:" + "\n" +
                "1) Госномер" + "\n" +
                "2) Производитель" + "\n" +
                "3) Модель авто" + "\n" +
                "4) Дата производства" + "\n" +
                "5) Пробег" + "\n" +
                "6) Тип топлива" + "\n" +
                "7) Количество сидений" + "\n" +
                "8) Рабочее состояние" + "\n" +
                "9) Первоначальная стоимость" + "\n" +
                "10)Срок службы" + "\n" +
                "Вводить данные необходимо через ЗАПЯТУЮ например(5,1,3)");
        return getValue();
    }

    @Override
    public String[] showFindMenu() {
        String[] values = new String[2];
        printer.print("Введите необходимое количество сидений:");
        values[0] = getValue();
        printer.print("Введите вес перевозимого груза");
        values[1] = getValue();
        return values;
    }

    @Override
    public String getValue() {
        String function = null;
        try {
            function = enterValue();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return function;
    }

    @Override
    public String showMenuForAdd() {
        printer.print("1. Добавить грузовое транспортное средство." + "\n" +
                "2. Добавить легковой автомобиль.");
        return getValue();
    }

    public String showAddCar() {
        printer.print("Данные должны быть введены согласно шаблону и разделены  ; . " + "\n" +
                "Шаблон для легкового авто: госномер(1234-AA-1);Производитель(на английском языке)" + "\n" +
                ";Модель авто;дата производства(2000-01-02);" + "\n" +
                "пробег;Вид топлива(на английском и с большой буквы);количество сидений;" + "\n" +
                "Работает ил не работает авто(true,false);стоимость авто;срок службы в месяцах");
        return getValue();
    }

    public String showAddTruck() {
        printer.print("Шаблон для грузового авто: госномер(1234-AA-1);Производитель(на английском языке);" + "\n" +
                "дата производства(2000-01-02);пробег;Вид топлива(на английском языке);" + "\n" +
                "количество сидений;Работает или не работает авто(true,false);" +
                "стоимость авто(в бел.руб.);срок службы в месяцах;грузоподъёмность(в кг);" + "\n" +
                "высота грузового отсека;ширина;длинна;");
        return getValue();
    }

    @Override
    public String getNumber() {
        printer.print("Введите номер автомобиля:");
        return getValue();
    }

    @Override
    public void printException(Exception e) {
        printer.print(e.getMessage());
    }

    @Override
    public void showInfoCar(Car car) {
        printer.print("Выберите поле которое необходимо изменить." + "\n" +
                "Автомобиль и его исходные данные:" + "\n" +
                "1. Гос.номер: " + car.getNumber() + "\n" +
                "2. Производитель: " + car.getCarManufacturer() + "\n" +
                "3. Модель:" + car.getModel() + "\n" +
                "4. Дата производства:" + car.getDateOfProduction() + "\n" +
                "5. Пробег:" + car.getMileage() + "\n" +
                "6. Тип топлива:" + car.getFuelType() + "\n" +
                "7. Количество мест:" + car.getSeat() + "\n" +
                "8. Является ли рабочим:" + car.isWorking() + "\n" +
                "9. Начальная стоимость:" + car.getInitialCost() + "\n" +
                "10. Срок службы:" + car.getLifeTime() + "\n");
    }

    @Override
    public void showInfoTruck(Car car) {
        showInfoCar(car);
        printer.print("11. Грузоподъемность: " + ((Truck) car).getCarryingCapacity() + "\n" +
                "12. Высота грузового отсека: " + ((Truck) car).getBaggageHigh() + "\n" +
                "13. Ширина грузового отсека: " + ((Truck) car).getBaggageWidth() + "\n" +
                "14. Длинна грузового отсека: " + ((Truck) car).getBaggageLength());
    }

    private static String enterValue() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    @Override
    public String getAnswerForUpdate(String choice) {
        Map<String, String> answers = new HashMap<>();
        answers.put("1", "Введите исправленнный номер авто, в соответствии с форматом():");
        answers.put("2", "Введите изменеие в производителе:");
        answers.put("3", "Введите измеенение в модели авто:");
        answers.put("4", "Введите изменение в дате производства:");
        answers.put("5", "Введите измение в пробеге:");
        answers.put("6", "Введите измение в топливе:");
        answers.put("7", "Введите измение в количестве мест:");
        answers.put("8", "Введите измение в работе авто(true/false):");
        answers.put("9", "Введите измение в начальной стоимости:");
        answers.put("10", "Введите измение в сроке службе(в месяцах):");
        answers.put("11", "Введите измение в грузоподъемности:");
        answers.put("12", "Введите измение в высоте грузового отсека:");
        answers.put("13", "Введите измение в ширене грузового отсека:");
        answers.put("14", "Введите измение в длинне грузового отсека:");
        printer.print(answers.get(choice));

        return getValue();

    }

}
