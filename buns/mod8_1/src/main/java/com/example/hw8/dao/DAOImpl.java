package com.example.hw8.dao;

import com.example.hw8.entity.Emp;
//import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DAOImpl implements DAO {

    @Autowired
    private EntityManager manager;

    @Override
    public List<Emp> getAll() {
        Query query = manager.createQuery("from Employee");
        return query.getResultList();
    }

    @Override
    public void save(Emp employee) {
        Emp newEmp = manager.merge(employee);
        employee.setId(newEmp.getId());
    }

    @Override
    public Emp getEmployee(int id) {
        return manager.find(Emp.class, id);
    }

    @Override
    public void delete(int id) {

        Query query = manager.createQuery("delete from Employee where id =:empId");
        query.setParameter("empId", id);
        query.executeUpdate();
    }
}
