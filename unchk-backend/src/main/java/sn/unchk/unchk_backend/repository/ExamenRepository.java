package sn.unchk.unchk_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.unchk.unchk_backend.entity.Examen;
import java.util.List;

public interface ExamenRepository extends JpaRepository<Examen, Long> {
    List<Examen> findByCoursFormateurId(Long formateurId);
    List<Examen> findByCoursFormation(String formation);
}