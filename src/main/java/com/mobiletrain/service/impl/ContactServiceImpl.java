package com.mobiletrain.service.impl;

import com.mobiletrain.dao.ContactDAO;
import com.mobiletrain.dao.impl.ContactDAOImpl;
import com.mobiletrain.domain.Contact;
import com.mobiletrain.service.ContactService;

import java.util.List;

public class ContactServiceImpl implements ContactService {
    private ContactDAO dao = new ContactDAOImpl();

    @Override
    public List<Contact> queryAll() {
        return dao.queryAll();
    }

    @Override
    public List<Contact> queryAll(int currentPage, int pageSize) {

        // 如果currentPage=1 && pageSize = 10
        // pageOffset = 0

        // 如果currentPage=2 && pageSize = 10
        // pageOffset = 10

        // 如果currentPage=3 && pageSize = 5
        // pageOffset = 10

        int pageOffset = (currentPage - 1) * pageSize;
        return dao.queryAll(pageOffset, pageSize);
    }

    @Override
    public int queryPageCount(int pageSize) {
        // 记录条数
        int recordCount = dao.queryCount();

        // 如果一共60条，且一页10条的话，一共几页？
        // 60 / 10 => 6
        // 66 / 10 => 7

        return (int) Math.ceil(recordCount / (double) pageSize);
    }

    @Override
    public boolean deleteById(String contactId) {
        int num = dao.deleteById(contactId);
        return num == 1;
    }

    @Override
    public Contact queryById(String contactId) {
        return dao.queryById(contactId);
    }

    @Override
    public boolean update(Contact contact) {
        int result = dao.update(contact);

        return result == 1;
    }

    @Override
    public boolean add(Contact contact) {
        int result = dao.add(contact);

        return result == 1;
    }
}
