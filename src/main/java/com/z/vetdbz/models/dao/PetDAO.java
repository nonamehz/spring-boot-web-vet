package com.z.vetdbz.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.z.vetdbz.models.entity.Pet;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PetDAO implements IPet {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true) // Indica que solo vamos a leer en la BD
    public List<Pet> petList() {
        return entityManager.createQuery("FROM Pet", Pet.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Pet petById(Integer idPet) {
        return entityManager.find(Pet.class, idPet);
    }

    @Override
    @Transactional
    public void savePet(Pet pet) {
        if (pet.getId() == null) {
            entityManager.persist(pet);
        } else {
            entityManager.merge(pet);
        }
    }

    @Override
    @Transactional
    public void deletePet(Integer id) {
        entityManager.remove(petById(id));
    }

}
