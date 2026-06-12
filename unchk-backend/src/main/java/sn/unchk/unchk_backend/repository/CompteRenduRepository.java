package sn.unchk.unchk_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.unchk.unchk_backend.entity.CompteRendu;

@Repository
public interface CompteRenduRepository extends JpaRepository<CompteRendu, Long> {
}