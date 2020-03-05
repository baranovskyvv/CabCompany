package dao;


import exception.DAOException;

import java.io.*;


public class TransportDaoImpl implements TransportDao {

    private final static String LINK = "src\\main\\resources\\fileWithCars.txt";

    @Override
    public void write(String cars) {
        try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(LINK, false))) {
            bufferWriter.write(cars);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String read() throws DAOException {
        FileReader fileReader;
        try {
            fileReader = new FileReader(LINK);
        } catch (FileNotFoundException e) {
            throw new DAOException("Файл не найден");
        }
        BufferedReader reader = new BufferedReader(fileReader);
        String listCar;
        StringBuilder cars = new StringBuilder();
        while (true) {
            try {
                if ((listCar = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new DAOException("Некорректная работа базы данных. Ошибка.");
            }
            cars.append("\n").append(listCar);
        }
        return String.valueOf(cars);
    }


}



