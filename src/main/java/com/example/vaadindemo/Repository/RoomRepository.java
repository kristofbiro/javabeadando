package com.example.vaadindemo.repository;

import com.example.vaadindemo.entity.Room;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class RoomRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Room room) {
        em.persist(room);
    }

    public void delete(Room room) {
        em.remove(em.find(Room.class,room.getId()));
    }

    public void update(Room room) {
        em.merge(room);
    }

    public List<Room> findAll() {
        return em.createQuery("select n from Room n").getResultList();
    }
}
