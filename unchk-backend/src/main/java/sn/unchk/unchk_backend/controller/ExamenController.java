package sn.unchk.unchk_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.unchk.unchk_backend.entity.Examen;
import sn.unchk.unchk_backend.service.ExamenService;
import java.util.List;

@RestController
@RequestMapping("/api/examens")
@CrossOrigin(origins = "http://localhost:4200")
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    @GetMapping
    public List<Examen> getAll() { return examenService.getAll(); }

    @GetMapping("/formateur/{id}")
    public List<Examen> getByFormateur(@PathVariable Long id) {
        return examenService.getByFormateur(id);
    }

    @PostMapping
    public Examen create(@RequestBody Examen examen) {
        return examenService.save(examen);
    }

    @PutMapping("/{id}")
    public Examen update(@PathVariable Long id, @RequestBody Examen examen) {
        examen.setId(id);
        return examenService.save(examen);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        examenService.delete(id);
    }
}