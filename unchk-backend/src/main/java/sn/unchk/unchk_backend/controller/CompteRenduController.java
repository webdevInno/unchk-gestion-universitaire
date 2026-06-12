package sn.unchk.unchk_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.unchk.unchk_backend.entity.CompteRendu;
import sn.unchk.unchk_backend.service.CompteRenduService;
import java.util.List;

@RestController
@RequestMapping("/api/comptes-rendus")
@CrossOrigin(origins = "*")
public class CompteRenduController {

    @Autowired
    private CompteRenduService compteRenduService;

    @GetMapping
    public List<CompteRendu> getAllCompteRendus() {
        return compteRenduService.getAllCompteRendus();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompteRendu> getCompteRenduById(@PathVariable Long id) {
        return compteRenduService.getCompteRenduById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CompteRendu createCompteRendu(@RequestBody CompteRendu compteRendu) {
        return compteRenduService.createCompteRendu(compteRendu);
    }

    @PutMapping("/{id}")
    public CompteRendu updateCompteRendu(@PathVariable Long id, @RequestBody CompteRendu compteRendu) {
        return compteRenduService.updateCompteRendu(id, compteRendu);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompteRendu(@PathVariable Long id) {
        compteRenduService.deleteCompteRendu(id);
        return ResponseEntity.ok().build();
    }
}