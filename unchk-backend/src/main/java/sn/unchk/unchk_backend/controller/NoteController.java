package sn.unchk.unchk_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.unchk.unchk_backend.entity.Note;
import sn.unchk.unchk_backend.service.NoteService;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService service;

    // GET toutes les notes d'un étudiant
    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<Note>> getByEtudiant(@PathVariable Long etudiantId) {
        return ResponseEntity.ok(service.getByEtudiant(etudiantId));
    }

    // GET notes d'un étudiant pour un cours
    @GetMapping("/etudiant/{etudiantId}/cours/{coursId}")
    public ResponseEntity<List<Note>> getByEtudiantEtCours(
            @PathVariable Long etudiantId,
            @PathVariable Long coursId) {
        return ResponseEntity.ok(service.getByEtudiantEtCours(etudiantId, coursId));
    }

    // GET moyenne générale d'un étudiant
    @GetMapping("/moyenne/{etudiantId}")
    public ResponseEntity<Double> getMoyenne(@PathVariable Long etudiantId) {
        return ResponseEntity.ok(service.getMoyenneGenerale(etudiantId));
    }

    // POST ajouter une note
    @PostMapping
    public ResponseEntity<Note> create(@RequestBody Note note) {
        return ResponseEntity.ok(service.create(note));
    }

    // PUT modifier une note
    @PutMapping("/{id}")
    public ResponseEntity<Note> update(
            @PathVariable Long id,
            @RequestBody Note note) {
        return ResponseEntity.ok(service.update(id, note));
    }

    // DELETE supprimer une note
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Note supprimée avec succès");
    }
}