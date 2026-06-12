package sn.unchk.unchk_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.unchk.unchk_backend.entity.SeanceCours;
import java.util.List;

public interface SeanceCoursRepository extends JpaRepository<SeanceCours, Long> {
    List<SeanceCours> findByCoursFormateurId(Long formateurId);
    List<SeanceCours> findByCoursFormation(String formation);
}