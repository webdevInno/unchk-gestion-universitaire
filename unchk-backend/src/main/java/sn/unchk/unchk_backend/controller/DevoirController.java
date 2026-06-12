package sn.unchk.unchk_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.unchk.unchk_backend.entity.Devoir;
import sn.unchk.unchk_backend.service.DevoirService;
import java.util.List;

@RestController
@RequestMapping("/api/devoirs")
@CrossOrigin(origins = "http://localhost:4200")
public class DevoirController {

    @Autowired
    private DevoirService devoirService;

    @GetMapping
    public List<Devoir> getAll() { return devoirService.getAll(); }

    @GetMapping("/formateur/{id}")
    public List<Devoir> getByFormateur(@PathVariable Long id) {
        return devoirService.getByFormateur(id);
    }

    @PostMapping
    public Devoir create(@RequestBody Devoir devoir) {
        return devoirService.save(devoir);
    }

    @PutMapping("/{id}")
    public Devoir update(@PathVariable Long id, @RequestBody Devoir devoir) {
        devoir.setId(id);
        return devoirService.save(devoir);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        devoirService.delete(id);
    }
}