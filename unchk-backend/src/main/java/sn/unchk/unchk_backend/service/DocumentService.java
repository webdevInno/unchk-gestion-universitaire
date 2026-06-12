package sn.unchk.unchk_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sn.unchk.unchk_backend.entity.Document;
import sn.unchk.unchk_backend.repository.DocumentRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository repository;

    // ✅ Par ceci
    private final String uploadDir = System.getProperty("user.dir") + "/uploads/";

    // Récupérer tous les documents
    public List<Document> getAll() {
        return repository.findAll();
    }

    // Récupérer par ID
    public Document getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document introuvable avec l'id : " + id));
    }

    // Créer un document
    public Document create(Document document) {
        document.setCreatedAt(LocalDateTime.now());
        if (document.getStatut() == null || document.getStatut().isEmpty()) {
            document.setStatut("Actif");
        }
        if (document.getRoleAcces() == null || document.getRoleAcces().isEmpty()) {
            document.setRoleAcces("Tous");
        }
        return repository.save(document);
    }

    // Modifier un document
    public Document update(Long id, Document updated) {
        Document existing = getById(id);

        existing.setTitre(updated.getTitre());
        existing.setType(updated.getType());
        existing.setReference(updated.getReference());
        existing.setDateDoc(updated.getDateDoc());
        existing.setDescription(updated.getDescription());
        existing.setContenu(updated.getContenu());
        existing.setRoleAcces(updated.getRoleAcces());
        existing.setFichierUrl(updated.getFichierUrl()); // ✅ AJOUT
        existing.setStatut(updated.getStatut());

        return repository.save(existing);
    }

    // Supprimer un document
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Document introuvable avec l'id : " + id);
        }
        repository.deleteById(id);
    }

    // Upload fichier
    public String uploadFichier(MultipartFile file) throws IOException {
        // Créer le dossier si inexistant
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Nom unique pour éviter les doublons
        String nomFichier = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(nomFichier);

        // ✅ CORRECTION : ajout de StandardCopyOption.REPLACE_EXISTING
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return nomFichier;
    }

    // Recherche globale
    public List<Document> recherche(String search) {
        return repository.rechercheGlobale(search);
    }

    // Filtrer par type
    public List<Document> getByType(String type) {
        return repository.findByType(type);
    }

    // Filtrer par statut
    public List<Document> getByStatut(String statut) {
        return repository.findByStatut(statut);
    }

    // Filtrer par rôle
    public List<Document> getByRole(String role) {
        return repository.findByRole(role);
    }

    // Archiver un document
    public Document archiver(Long id) {
        Document doc = getById(id);
        doc.setStatut("Archivé");
        return repository.save(doc);
    }

    // Stats
    public long countByType(String type) {
        return repository.countByType(type);
    }

    public long countActifs() {
        return repository.countByStatut("Actif");
    }

    // Filtrer par auteur
    public List<Document> getByAuteur(Long id) {
        return repository.findByAuteurId(id);
    }

    public long countArchives() {
        return repository.countByStatut("Archivé");
    }
}