package com.codegym.repository.impl;

import com.codegym.model.Customer;
import com.codegym.repository.CustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class CustomerRepositoryImpl implements CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = em.createQuery("select c from Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    public void save(Customer customer) {
        if(customer.getId() != null){
            em.merge(customer);
        }else {
            em.persist(customer);
        }
    }

    @Override
    public List<Customer> findByName(String name) {
        //Cau lenh truy van tinh

        Query query = em.createNamedQuery("findAllCustomersWithName")
                .setParameter(1, name);
        return query.getResultList();


        // Cau lenh truy van dong
        /*Query query = em.createQuery(
                "SELECT c FROM Customer c WHERE c.lastName LIKE :lName")
                .setParameter("lName", name)
                .setMaxResults(10);

        return query.getResultList();*/
    }
}
