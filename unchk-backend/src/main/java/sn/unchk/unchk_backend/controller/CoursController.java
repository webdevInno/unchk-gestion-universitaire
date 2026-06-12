package sn.unchk.unchk_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.unchk.unchk_backend.entity.Cours;
import sn.unchk.unchk_backend.service.CoursService;
import java.util.List;

@RestController
@RequestMapping("/api/cours")
@CrossOrigin(origins = "http://localhost:4200")
public class CoursController {

    @Autowired
    private CoursService coursService;

    @GetMapping
    public List<Cours> getAll() { return coursService.getAll(); }

    @GetMapping("/formateur/{id}")
    public List<Cours> getByFormateur(@PathVariable Long id) {
        return coursService.getByFormateur(id);
    }

    @PostMapping
    public Cours create(@RequestBody Cours cours) {
        return coursService.save(cours);
    }

    @PutMapping("/{id}")
    public Cours update(@PathVariable Long id, @RequestBody Cours cours) {
        cours.setId(id);
        return coursService.save(cours);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        coursService.delete(id);
    }
}