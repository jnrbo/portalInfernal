package repository;

import java.sql.PreparedStatement;

import model.AttrsValues;

/**
 * Created by junior and ramon.
 */

public abstract class AbstractExtendedRepository<T> extends AbstractRepository<T> {

    public abstract void remove(T t);

    public abstract void update(T t);

    protected boolean delete(String table, AttrsValues.AttrVal where) {
        String sql = "DELETE FROM " + table + " WHERE" + where.getAttribute() + " = ?;";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            if (where.getValue() instanceof String) {
                statement.setString(1, (String)where.getValue());
            } else {
                statement.setInt(1, (int)where.getValue());
            }
            statement.execute();
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    protected void update(String table, AttrsValues entries, String where) {
        String sql;
        String set = "";
        PreparedStatement statement;
        int size = entries.getItens().size();
        try {
            sql = "UPDATE " + table + " SET ";
            for (int i = 0; i < size; i++) {
                set += "? = ? ";
            }
            sql = sql + set + where + " ;";
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < size; i++) {
                AttrsValues.AttrVal attrVal = entries.getItens().get(i);
                statement.setString((i + 1), attrVal.getAttribute());
                if (attrVal.getValue() instanceof String) {
                    statement.setString((i + 2), (String)attrVal.getValue());
                } else {
                    statement.setInt(i + 2, (int)attrVal.getValue());
                }
            }
            statement.execute();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //UPDATE bla SET name = "kkk WHERE id = 2;
    }
}