package sn.unchk.unchk_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.unchk.unchk_backend.entity.Document;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    // Filtrer par type
    List<Document> findByType(String type);

    // Filtrer par statut
    List<Document> findByStatut(String statut);

    // Filtrer par rôle d'accès
    List<Document> findByRoleAcces(String roleAcces);

    // Filtrer par type ET statut
    List<Document> findByTypeAndStatut(String type, String statut);

    List<Document> findByAuteurId(Long auteurId);

    // Compter par type
    long countByType(String type);

    // Compter par statut
    long countByStatut(String statut);

    // Recherche globale
    @Query("SELECT d FROM Document d WHERE " +
            "LOWER(d.titre) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(d.type) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(d.reference) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(d.description) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Document> rechercheGlobale(@Param("search") String search);

    // Documents accessibles par rôle
    @Query("SELECT d FROM Document d WHERE " +
            "d.roleAcces = 'Tous' OR d.roleAcces = :role")
    List<Document> findByRole(@Param("role") String role);
}