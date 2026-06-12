package sn.unchk.unchk_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.unchk.unchk_backend.entity.Devoir;
import java.util.List;

public interface DevoirRepository extends JpaRepository<Devoir, Long> {
    List<Devoir> findByCoursFormateurId(Long formateurId);
    List<Devoir> findByCoursFormation(String formation);
}