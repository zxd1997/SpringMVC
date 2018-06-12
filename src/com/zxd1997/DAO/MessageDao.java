package com.zxd1997.DAO;
import com.zxd1997.Beans.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessageDao {
    public int add(Message message) throws SQLException;

    public List<Message> findall() throws SQLException;

    public int delete(int id) throws SQLException;

    public List<Message> find_range(int a, int b) throws SQLException;

    public int getPages() throws SQLException;
}
