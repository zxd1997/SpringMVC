package com.zxd1997.DAO.DaoImpl;

import com.zxd1997.Beans.Message;
import com.zxd1997.DAO.MessageDao;
import com.zxd1997.Util.JDBCutil;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageDaoImpl implements MessageDao {
    @Override
    public int add(Message message) throws SQLException {
        JDBCutil db = new JDBCutil();
        db.getConnection();
        String sql = "insert into message values (seq_message.nextval,?,?,?,sysdate)";
        Object[] objects = {message.getName(), message.getContent(), message.getTitle()};
        return db.executeUpdate(sql, objects);
    }

    @Override
    public List<Message> findall() throws SQLException {
        System.out.println("Message com.zxd1997.Dao");
        List<Message> messages = new ArrayList<>();
        JDBCutil db = new JDBCutil();
        db.getConnection();
        String sql = "select * from message";
        ResultSet resultSet = db.executeQuery(sql);
        while (resultSet.next()) {
            messages.add(new Message(
                    resultSet.getString("name"),
                    resultSet.getString("title"),
                    resultSet.getString("content"),
                    resultSet.getInt("id"),
                    resultSet.getTime("time")
            ));
        }
        resultSet.close();
        return messages;
    }

    @Override
    public int delete(int id) throws SQLException {
        JDBCutil db = new JDBCutil();
        db.getConnection();
        String sql = "delete from message where id=?";
        Object[] object = {id};
        return db.executeUpdate(sql, object);
    }

    @Override
    public List<Message> find_range(int a, int b) throws SQLException {
        List<Message> messages = new ArrayList<>();
        JDBCutil db = new JDBCutil();
        db.getConnection();
        String sql = "select * from (select rownum as rowno,tt.*from (select * from message order by id desc)tt)t where t.rowno>=? and t.rowno<=?";
        Object[] object = {a, b};
        ResultSet resultSet = db.executeQuery(sql, object);
        while (resultSet.next()) {
            messages.add(new Message(
                    resultSet.getString("name"),
                    resultSet.getString("title"),
                    resultSet.getString("content"),
                    resultSet.getInt("id"),
                    resultSet.getTime("time")
            ));
            System.out.println(resultSet.getString("name"));
        }
        resultSet.close();
        return messages;
    }

    @Override
    public int getPages() throws SQLException {
        int t=0;
        JDBCutil db = new JDBCutil();
        db.getConnection();
        String sql = "select count(id) from message";
        ResultSet resultSet = db.executeQuery(sql);
        if (resultSet.next())
            t=resultSet.getInt(1);
        return t;
    }
}
