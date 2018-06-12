package com.zxd1997.Util;

import java.sql.*;

public class JDBCutil {
    private static Connection conn = null;
    private PreparedStatement pst;

    //��ȡConnection����
    public Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("����Oracle�����ɹ�");
            String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
            String userName = "scott";
            String pwd = "19970226";
            if (conn == null) {
                conn = DriverManager.getConnection(url, userName, pwd);
            }
            System.out.println("��ȡConnection sucess");

        } catch (ClassNotFoundException e) {
            System.out.println("����Oracle gg");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("��ȡConnection fail");
            e.printStackTrace();
        }
        return conn;
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            System.out.println("ִ�в�ѯ���ɹ�");
        } catch (SQLException e) {
            System.out.println("ִ�в�ѯ���ʧ��");
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet executeQuery(String sql, Object obj[]) throws SQLException {
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            if (obj != null) {
                for (int i = 0; i < obj.length; i++) {
                    pst.setObject(i + 1, obj[i]);
                }
            }
            rs = pst.executeQuery();
            System.out.println("ִ�в�ѯ���ɹ�");
        } catch (SQLException e) {
            System.out.println("ִ�в�ѯ���ʧ��");
            e.printStackTrace();
        }
        return rs;
    }


    public int executeUpdate(String sql) throws SQLException {
        int count = -1;
        try {
            pst = conn.prepareStatement(sql);
            count = pst.executeUpdate();
            System.out.println("ִ�и������ɹ�");
        } catch (SQLException e) {
            System.out.println("ִ�и������ʧ��");
            e.printStackTrace();
        }
        return count;
    }

    public int executeUpdate(String sql, Object obj[]) throws SQLException {
        int count = -1;
        try {
            pst = conn.prepareStatement(sql);
            if (obj != null) {
                for (int i = 0; i < obj.length; i++) {
                    pst.setObject(i + 1, obj[i]);
                }
            }
            count = pst.executeUpdate();
            System.out.println("ִ�и������ɹ�");
        } catch (SQLException e) {
            System.out.println("ִ�и������ʧ��");
            e.printStackTrace();
        }
        return count;
    }

    public void close() {
        try {
            if (pst != null) {
                pst.close();
                pst = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
            System.out.println("�ر����ӳɹ�");
        } catch (SQLException e) {
            System.out.println("�ر�����ʧ��");
            pst = null;
            conn = null;
            e.printStackTrace();
        }
    }

    //��ʼ����
    public void beginTransation() {
        try {
            conn.setAutoCommit(false);
            System.out.println("��ʼ����");
        } catch (SQLException e) {
            System.out.println("��ʼ����ʧ��");
            e.printStackTrace();
        }
    }

    //�ύ����
    public void commit() {
        try {
            conn.commit();
            System.out.println("�ύ����ɹ�");
        } catch (SQLException e) {
            System.out.println("�ύ����ʧ��");
            e.printStackTrace();
        }
    }

    //�ع�����
    public void rollback() {
        try {
            conn.rollback();
            System.out.println("�ع�����ɹ�");
        } catch (SQLException e) {
            System.out.println("�ع�����ʧ��");
            e.printStackTrace();
        }
    }
}
