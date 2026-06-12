package sn.unchk.unchk_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sn.unchk.unchk_backend.entity.Document;
import sn.unchk.unchk_backend.service.DocumentService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/documents")

@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService service;

    // ✅ Par ceci
    private final String uploadDir = System.getProperty("user.dir") + "/uploads/";
    // GET tous les documents
    @GetMapping
    public ResponseEntity<List<Document>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // GET par ID
    @GetMapping("/{id}")
    public ResponseEntity<Document> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // GET recherche globale
    @GetMapping("/search")
    public ResponseEntity<List<Document>> search(@RequestParam String q) {
        return ResponseEntity.ok(service.recherche(q));
    }

    // GET filtrer par type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Document>> getByType(@PathVariable String type) {
        return ResponseEntity.ok(service.getByType(type));
    }

    // GET filtrer par statut
    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Document>> getByStatut(@PathVariable String statut) {
        return ResponseEntity.ok(service.getByStatut(statut));
    }

    // GET filtrer par rôle
    @GetMapping("/role/{role}")
    public ResponseEntity<List<Document>> getByRole(@PathVariable String role) {
        return ResponseEntity.ok(service.getByRole(role));
    }
    // GET par auteur
    @GetMapping("/auteur/{id}")
    public ResponseEntity<List<Document>> getByAuteur(@PathVariable Long id) {
        return ResponseEntity.ok(service.getByAuteur(id));
    }
    // GET stats
    @GetMapping("/stats/actifs")
    public ResponseEntity<Long> countActifs() {
        return ResponseEntity.ok(service.countActifs());
    }

    @GetMapping("/stats/archives")
    public ResponseEntity<Long> countArchives() {
        return ResponseEntity.ok(service.countArchives());
    }

    // POST créer un document
    @PostMapping
    public ResponseEntity<Document> create(@RequestBody Document document) {
        return ResponseEntity.ok(service.create(document));
    }

    // PUT modifier un document
    @PutMapping("/{id}")
    public ResponseEntity<Document> update(
            @PathVariable Long id,
            @RequestBody Document document) {
        return ResponseEntity.ok(service.update(id, document));
    }

    // PUT archiver un document
    @PutMapping("/{id}/archiver")
    public ResponseEntity<Document> archiver(@PathVariable Long id) {
        return ResponseEntity.ok(service.archiver(id));
    }

    // DELETE supprimer un document
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Document supprimé avec succès");
    }

    // POST upload fichier
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFichier(
            @RequestParam("file") MultipartFile file) {
        try {
            String nomFichier = service.uploadFichier(file);
            return ResponseEntity.ok(nomFichier);
        } catch (IOException e) {
            return ResponseEntity.badRequest()
                    .body("Erreur lors de l'upload : " + e.getMessage());
        }
    }

    // GET télécharger fichier
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFichier(
            @PathVariable String filename) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                System.out.println("Fichier introuvable : " + filePath.toAbsolutePath());
                return ResponseEntity.notFound().build();
            }

            System.out.println("Fichier trouvé : " + filePath.toAbsolutePath());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + filename + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}