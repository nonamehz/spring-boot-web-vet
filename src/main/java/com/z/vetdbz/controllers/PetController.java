package com.z.vetdbz.controllers;

import javax.validation.Valid;

import com.z.vetdbz.models.dao.IPet;
import com.z.vetdbz.models.entity.Pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("pet")
public class PetController {

    @Autowired
    private IPet iPet;

    @GetMapping({ "/", "/index" })
    public String index(Model model) {
        model.addAttribute("pets", iPet.petList());
        return "index";
    }

    @GetMapping("/mascotas")
    public String petListTable(Model model) {
        model.addAttribute("pets", iPet.petList());
        return "pages/mascotas";
    }

    @GetMapping({ "/form-mascota", "/form-mascota/{id}" })
    public String newPet(@PathVariable(required = false) Integer id, Model model) {
        if (id == null) {
            model.addAttribute("pet", new Pet());
        } else {
            model.addAttribute("pet", iPet.petById(id));
        }
        return "pages/form_mascotas";
    }

    @PostMapping("/form-mascota")
    public String newPetPost(@Valid Pet pet, BindingResult result, Model model, SessionStatus sessionStatus) {

        if (result.hasErrors()) {
            return "pages/form_mascotas";
        }
        iPet.savePet(pet);
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @GetMapping("/mascota/eliminar/{id}")
    public String petDelete(@PathVariable Integer id) {
        iPet.deletePet(id);
        return "redirect:/mascotas";
    }

    @GetMapping("/mascota/perfil/{id}")
    public String petProfile(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pet", iPet.petById(id));
        return "pages/mascota_perfil";
    }

}
