package com.zxd1997.Services.ServiceImpl;

import com.zxd1997.Beans.Message;
import com.zxd1997.DAO.MessageDao;
import com.zxd1997.Services.MessageService;
import com.zxd1997.Util.JDBCutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageDao messageDao;

    @Override
    public boolean add(Message message) {
        boolean f;
        JDBCutil db = new JDBCutil();
        db.getConnection();
        db.beginTransation();
        try {
            if (messageDao.add(message) > 0) {
                db.commit();
                f = true;
            } else {
                f=false;
                db.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            f = false;
            db.rollback();
        } finally {
            db.close();
        }
        return f;
    }

    @Override
    public List<Message> findall() {
        JDBCutil db = new JDBCutil();
        db.getConnection();
        try {
            System.out.println("Message com.zxd1997.Service");
            return messageDao.findall();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            db.close();
        }
    }

    @Override
    public boolean delete(int id) {
        boolean f = false;
        JDBCutil db = new JDBCutil();
        db.getConnection();
        db.beginTransation();
        try {
            if (messageDao.delete(id) > 0) {
                f = true;
                db.commit();
            } else {
                db.rollback();
            }
        } catch (SQLException e) {
            db.rollback();
            e.printStackTrace();
        } finally {
            db.close();
        }
        return f;
    }

    @Override
    public List<Message> find_page(int n) {
        JDBCutil db = new JDBCutil();
        db.getConnection();
        try {
            System.out.println("Message com.zxd1997.Service");
            return messageDao.find_range(10 * (n - 1) + 1, 10 * n);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            db.close();
        }
    }

    @Override
    public int getPages() {
            JDBCutil db = new JDBCutil();
            db.getConnection();
            try {
                return messageDao.getPages();
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            } finally {
                db.close();
            }
    }
}
