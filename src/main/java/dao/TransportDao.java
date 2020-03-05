package dao;

import exception.DAOException;

public interface TransportDao {

   String read() throws DAOException;

    void write(String text) throws DAOException;
}
