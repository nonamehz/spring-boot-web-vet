package com.z.vetdbz.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

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
            model.addAttribute("title", "Registrar Mascota");
            model.addAttribute("pet", new Pet());
        } else {
            model.addAttribute("title", "Editar Mascota");
            model.addAttribute("pet", iPet.petById(id));
        }
        return "pages/form_mascotas";
    }

    @PostMapping("/form-mascota")
    public String newPetPost(@Valid Pet pet, BindingResult result, Model model, @RequestParam MultipartFile file,
            SessionStatus sessionStatus) {

        if (result.hasErrors()) {
            return "pages/form_mascotas";
        }

        if (!file.isEmpty()) {
            String urlImg = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get("uploads").resolve(urlImg);
            Path raiz = uploadPath.toAbsolutePath();
            try {
                Files.copy(file.getInputStream(), raiz);
                pet.setUrlImg(urlImg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        iPet.savePet(pet);
        sessionStatus.setComplete();
        return "redirect:/mascotas";
    }

    @GetMapping("/mascota/eliminar/{id}")
    public String petDelete(@PathVariable Integer id) {
        // Eliminar foto
        Pet pet = iPet.petById(id);

        if (pet.getUrlImg() != null) {
            Path urlPath = Paths.get("uploads").resolve(pet.getUrlImg()).toAbsolutePath();
            File photo = urlPath.toFile();

            if (photo.exists() && photo.canRead()) {
                photo.delete();
            }
        }

        iPet.deletePet(id);

        return "redirect:/mascotas";
    }

    @GetMapping("/mascota/perfil/{id}")
    public String petProfile(@PathVariable Integer id, Model model) {
        model.addAttribute("pet", iPet.petById(id));
        return "pages/mascota_perfil";
    }

}
