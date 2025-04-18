package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BurgerDaoImpl implements BurgerDao {

    private final EntityManager entityManager;

    public BurgerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Burger save(Burger burger) {
        entityManager.persist(burger);
        return burger;
    }


    @Override
    public Burger findById(Long id) {
        Burger burger = entityManager.find(Burger.class, id);
        if (burger == null) {
            throw new BurgerException("Burger bulunamadÄ±: " + id, HttpStatus.NOT_FOUND);
        }
        return burger;
    }


    @Override
    public List<Burger> findAll() {
        String jpql = "SELECT b FROM Burger b";
        TypedQuery<Burger> query = entityManager.createQuery(jpql, Burger.class);
        return query.getResultList();
    }

    @Override
    public List<Burger> findByPrice(int price) {
        String jpql = "SELECT b FROM Burger b WHERE b.price = :price";
        TypedQuery<Burger> query = entityManager.createQuery(jpql, Burger.class);
        query.setParameter("price", price);
        return query.getResultList();
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        String jpql = "SELECT b FROM Burger b WHERE b.breadType = :breadType";
        TypedQuery<Burger> query = entityManager.createQuery(jpql, Burger.class);
        query.setParameter("breadType", breadType);
        return query.getResultList();
    }

    @Override
    public List<Burger> findByContent(String content) {
        String jpql = "SELECT b FROM Burger b WHERE b.content LIKE :content";
        TypedQuery<Burger> query = entityManager.createQuery(jpql, Burger.class);
        query.setParameter("content", "%" + content + "%");
        return query.getResultList();
    }

    @Override
    @Transactional
    public Burger update(Burger burger) {
        return entityManager.merge(burger);
    }

    @Override
    @Transactional
    public Burger remove(Long id) {
        Burger foundBurger = entityManager.find(Burger.class, id);
        if (foundBurger != null) {
            entityManager.remove(foundBurger);
        }
        return foundBurger;
    }
}