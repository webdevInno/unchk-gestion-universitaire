package sn.unchk.unchk_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.unchk.unchk_backend.entity.Note;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    // Toutes les notes d'un étudiant
    List<Note> findByEtudiantId(Long etudiantId);

    // Toutes les notes d'un étudiant pour un cours
    List<Note> findByEtudiantIdAndCoursId(Long etudiantId, Long coursId);

    // Moyenne générale d'un étudiant
    @Query("SELECT AVG(n.valeur * n.coefficient) / AVG(n.coefficient) " +
            "FROM Note n WHERE n.etudiant.id = :etudiantId")
    Double getMoyenneGenerale(@Param("etudiantId") Long etudiantId);
}