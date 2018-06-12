package com.zxd1997.Services;

import com.zxd1997.Beans.Message;

import java.util.List;

public interface MessageService {
    public boolean add(Message message);

    public List<Message> findall();

    public boolean delete(int id);

    public List<Message> find_page(int n);

    public int getPages();
}
