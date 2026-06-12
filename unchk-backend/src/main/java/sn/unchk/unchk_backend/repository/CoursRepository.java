package sn.unchk.unchk_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.unchk.unchk_backend.entity.Cours;
import java.util.List;

public interface CoursRepository extends JpaRepository<Cours, Long> {
    List<Cours> findByFormateurId(Long formateurId);
    List<Cours> findByFormation(String formation);
}