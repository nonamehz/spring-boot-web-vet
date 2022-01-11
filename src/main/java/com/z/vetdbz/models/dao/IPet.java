package com.z.vetdbz.models.dao;

import java.util.List;

import com.z.vetdbz.models.entity.Pet;

public interface IPet {
    // Listar todas las mascotas
    public List<Pet> petList();

    // Listar una mascota por ID
    public Pet petById(Integer idPet);

    // Registrar mascota
    public void savePet(Pet pet);

    // Eliminar una mascota
    public void deletePet(Integer id);
}
