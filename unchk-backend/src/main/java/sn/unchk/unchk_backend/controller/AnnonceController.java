package sn.unchk.unchk_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.unchk.unchk_backend.entity.Annonce;
import sn.unchk.unchk_backend.service.AnnonceService;
import java.util.List;

@RestController
@RequestMapping("/api/annonces")
@CrossOrigin(origins = "http://localhost:4200")
public class AnnonceController {

    @Autowired
    private AnnonceService annonceService;

    @GetMapping
    public List<Annonce> getAll() { return annonceService.getAll(); }

    @GetMapping("/cible/{cible}")
    public List<Annonce> getByCible(@PathVariable String cible) {
        return annonceService.getByCible(cible);
    }

    @PostMapping
    public Annonce create(@RequestBody Annonce annonce) {
        return annonceService.save(annonce);
    }

    @PutMapping("/{id}")
    public Annonce update(@PathVariable Long id, @RequestBody Annonce annonce) {
        annonce.setId(id);
        return annonceService.save(annonce);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        annonceService.delete(id);
    }
}