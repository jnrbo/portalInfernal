package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.AttrsValues;
import model.enums.LabeledEnum;

/**
 * Created by junior and ramon.
 */

public abstract class AbstractRepository<T> {

    protected Connection connection;

    public abstract void save(T t);

    public abstract T find(int id);

    public abstract ArrayList<T> findAllBy(String attributeName, int attributeValue);

    AbstractRepository() {
        init();
    }
    public void init() {
        try {
            this.connection = ConnectionFactory.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void create(String table, AttrsValues entries) {
        String sql;
        String values = "";
        String attributes = "";
        PreparedStatement statement;
        int size = entries.getItens().size();
        try {
            sql = "INSERT INTO " + table + " (";
            for (int i = 0; i < size; i++) {
                attributes += entries.getItens().get(i).getAttribute() + ", ";
                values += "?, ";
            }
            attributes += "created_on, ";
            values += "NOW(), ";
            attributes = attributes.substring(0, attributes.length() - 2);
            values = values.substring(0, values.length() - 2);
            sql = sql + attributes + ") VALUES (" + values + ");";
            statement = connection.prepareStatement(sql);
            setValues(statement, entries);
            statement.execute();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected ResultSet select(String table, String where, Object... values) {
        String sql;
        PreparedStatement statement;
        try {
            sql = "SELECT * FROM " + table;
            if (where != null && !where.isEmpty()) {
                sql = sql + " WHERE " + where;
                statement = connection.prepareStatement(sql);
                if (values != null) {
                    int size = values.length;
                    for (int i = 0; i < size; i++) {
                        Object value = values[i];
                        if (value instanceof String) {
                            statement.setString((i + 1), (String)value);
                        } else {
                            statement.setInt((i + 1), (int)value);
                        }
                    }
                }
            } else {
                statement = connection.prepareStatement(sql);
            }
            return statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void setValues(PreparedStatement statement, AttrsValues entries) throws SQLException {
        int size = entries.getItens().size();
        for (int i = 0; i < size; i++) {
            Object value = entries.getItens().get(i).getValue();
            if (value instanceof Integer) {
                statement.setInt((i + 1), (int)value);
            } else if (value instanceof Date) {
                statement.setDate((i + 1), new java.sql.Date(((Date) value).getTime()));
            } else if (value instanceof LabeledEnum) {
                statement.setString((i + 1), value.toString());
            } else if (value instanceof Boolean) {
                statement.setBoolean((i + 1), (Boolean) value);

            } else {
                statement.setString((i + 1), (String)value);
            }
        }
    }

    protected int id(ResultSet resultSet) throws SQLException {
        return resultSet.getInt("id");
    }

    protected Date createdOn(ResultSet resultSet) throws SQLException {
        return resultSet.getDate("created_on");
    }

    protected String name(ResultSet resultSet) throws SQLException {
        return resultSet.getString("name");
    }
    //SELECT FROM X WHERE id = 3 AND
}