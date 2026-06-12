package sn.unchk.unchk_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.unchk.unchk_backend.entity.Note;
import sn.unchk.unchk_backend.repository.NoteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository repository;

    // Toutes les notes d'un étudiant
    public List<Note> getByEtudiant(Long etudiantId) {
        return repository.findByEtudiantId(etudiantId);
    }

    // Notes d'un étudiant pour un cours
    public List<Note> getByEtudiantEtCours(Long etudiantId, Long coursId) {
        return repository.findByEtudiantIdAndCoursId(etudiantId, coursId);
    }

    // Moyenne générale
    public Double getMoyenneGenerale(Long etudiantId) {
        Double moyenne = repository.getMoyenneGenerale(etudiantId);
        return moyenne != null ? Math.round(moyenne * 100.0) / 100.0 : 0.0;
    }

    // Ajouter une note
    public Note create(Note note) {
        return repository.save(note);
    }

    // Modifier une note
    public Note update(Long id, Note updated) {
        Note existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note introuvable"));
        existing.setValeur(updated.getValeur());
        existing.setCoefficient(updated.getCoefficient());
        existing.setType(updated.getType());
        existing.setCommentaire(updated.getCommentaire());
        existing.setDateNote(updated.getDateNote());
        return repository.save(existing);
    }

    // Supprimer une note
    public void delete(Long id) {
        repository.deleteById(id);
    }
}