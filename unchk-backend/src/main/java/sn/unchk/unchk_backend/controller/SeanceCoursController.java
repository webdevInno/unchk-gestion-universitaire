package sn.unchk.unchk_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.unchk.unchk_backend.entity.SeanceCours;
import sn.unchk.unchk_backend.service.SeanceCoursService;
import java.util.List;

@RestController
@RequestMapping("/api/seances")
@CrossOrigin(origins = "http://localhost:4200")
public class SeanceCoursController {

    @Autowired
    private SeanceCoursService seanceService;

    @GetMapping
    public List<SeanceCours> getAll() { return seanceService.getAll(); }

    @GetMapping("/formateur/{id}")
    public List<SeanceCours> getByFormateur(@PathVariable Long id) {
        return seanceService.getByFormateur(id);
    }

    @GetMapping("/formation/{formation}")
    public List<SeanceCours> getByFormation(@PathVariable String formation) {
        return seanceService.getByFormation(formation);
    }

    @PostMapping
    public SeanceCours create(@RequestBody SeanceCours seance) {
        return seanceService.save(seance);
    }

    @PutMapping("/{id}")
    public SeanceCours update(@PathVariable Long id, @RequestBody SeanceCours seance) {
        seance.setId(id);
        return seanceService.save(seance);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        seanceService.delete(id);
    }
}