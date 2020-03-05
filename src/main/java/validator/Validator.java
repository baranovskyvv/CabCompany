package validator;


import exception.ServiceException;
import entity.CarManufacturer;
import entity.FuelType;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validator {

    //регулярное выражение соответствует шаблонам 1234-BC-7 или 5-TAX-1234.
    private final String NUMBER = "^[0-9]{1}[-0-9]{1}[0-9,E,T,Y,I,O,P,A,H,K,X,C,B,M]{2}[-E,T,Y,I,O,P,A,H,K,X,C,B,M]{2}" +
            "[0-9E,T,Y,I,O,P,A,H,K,X,C,B,M]{1}[-0-9]{1}[0-9]{1,2}$";

    //валидатор отвечает за введение гос номера в соответствующем формате
    public String validateNumber(String number) throws ServiceException {
        Pattern pattern = Pattern.compile(NUMBER);
        Matcher matcher = pattern.matcher(number);
        boolean test = matcher.find();
        if (test) {
            return number;
        } else
            throw new ServiceException("Некорректный номер автомобиля " + number +
                    "(проверьте что включен английский язык). " +
                    "Сделайте согласно шаблонам: 1234-BC-7 или 5-TAX-1234");

    }

    //валидатор проверяет соответствует ли введенный производитель, производителям из списка(enam)
    public CarManufacturer validateCarManufacturer(String manufacturer) throws ServiceException {
        CarManufacturer carManufacturer;
        try {
            carManufacturer = CarManufacturer.valueOf(manufacturer.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ServiceException("Производителя вашего автомобиля " + manufacturer +
                    " нет в списке, проверьте правильность введенных данных или обратитесь в службу поддержки");
        }
        return carManufacturer;
    }

    //валидатор проверяет в правильном ли формате записано число, а так же выполняет прорверку, что введенная
// дата не позже сегодняшнего числа
    public LocalDate validateDate(String date) throws ServiceException {
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new ServiceException("Ошибка программы.В записи данных даты." + date +
                    " Дата записывается в формате 2000-11-01");
        }
        if (localDate.isAfter(LocalDate.now())) {
            throw new ServiceException("Дата не может быть больше, чем сегоднешняя дата.");
        }
        return localDate;
    }

    //валидатор проверяет что число введенное явялется целым и больше 0
    public int validateInt(String number) throws ServiceException {
        int numeric;
        try {
            numeric = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new ServiceException("Число " + number + " должно быть целым и не содержать лишних символов");
        }
        return numeric;
    }

    public int validateInt(String number, int min) throws ServiceException {
        int numeric;
        try {
            numeric = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new ServiceException("Число " + number + " должно быть целым и не содержать лишних символов");
        }
        if (numeric <= min) {
            throw new ServiceException("Ошибка в числе " + numeric + "Число должно быть больше " + min);
        }
        return numeric;
    }

    public int validateInt(String number, int min, int max) throws ServiceException {
        int numeric;
        try {
            numeric = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new ServiceException("Число " + number + " должно быть целым и не содержать лишних символов");
        }
        if (numeric <= min || numeric > max) {
            throw new ServiceException("Ошибка в числе " + numeric + "Число должно быть больше " + min + "и меньше " + max);
        }
        return numeric;
    }


    //валидатор проверяет соответствует ли введенный тип топлива, типу топлива из списка(enam)
    public FuelType validateFuelType(String fuelType) {
        FuelType source;
        try {
            source = FuelType.valueOf(fuelType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Данного топлива автомобиля " + fuelType +
                    " нет в списке, проверьте правильность введенных данных или обратитесь в службу поддержки");
        }
        return source;
    }

    //валидатор проверяет, что число введенное больше 0
    public double validateDouble(String number) throws ServiceException {
        double numeric;
        try {
            numeric = Double.parseDouble(number);
        } catch (NumberFormatException e) {
            throw new ServiceException("Число " + number + " должно быть целым и не содержать лишних символов");
        }
        if (numeric <= 0) {
            throw new ServiceException("Ошибка в числе " + numeric + "Число должно быть больше нуля");
        }
        return numeric;
    }

    // валидатор проверяет корректность введения boolean
    public boolean validateBoolean(String value) throws ServiceException {
        if (value.equals("true")) {
            return true;
        } else if (value.equals("false")) {
            return false;
        } else {
            throw new ServiceException("Ошибка, программ должна принимать занчение либо true, либо false.");
        }
    }


}

