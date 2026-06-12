package sn.unchk.unchk_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.unchk.unchk_backend.entity.Examen;
import sn.unchk.unchk_backend.repository.ExamenRepository;
import java.util.List;

@Service
public class ExamenService {

    @Autowired
    private ExamenRepository examenRepository;

    public List<Examen> getAll() { return examenRepository.findAll(); }
    public List<Examen> getByFormateur(Long id) { return examenRepository.findByCoursFormateurId(id); }
    public Examen save(Examen examen) { return examenRepository.save(examen); }
    public void delete(Long id) { examenRepository.deleteById(id); }
}