package sn.unchk.unchk_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.unchk.unchk_backend.entity.Formation;

import java.util.List;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {

    // Filtrer par type
    List<Formation> findByType(String type);

    // Filtrer par niveau
    List<Formation> findByNiveau(String niveau);

    // Filtrer par statut
    List<Formation> findByStatut(String statut);

    // Compter par statut
    long countByStatut(String statut);

    // Compter par type
    long countByType(String type);

    // Recherche globale
    @Query("SELECT f FROM Formation f WHERE " +
            "LOWER(f.intitule) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(f.type) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(f.niveau) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(f.statut) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Formation> rechercheGlobale(@Param("search") String search);
}