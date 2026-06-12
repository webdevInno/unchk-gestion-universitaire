package sn.unchk.unchk_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.unchk.unchk_backend.entity.Annonce;
import java.util.List;

public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
    List<Annonce> findByCible(String cible);
    List<Annonce> findByAuteurId(Long auteurId);
}