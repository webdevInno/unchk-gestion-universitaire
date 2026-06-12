package sn.unchk.unchk_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.unchk.unchk_backend.entity.Devoir;
import sn.unchk.unchk_backend.repository.DevoirRepository;
import java.util.List;

@Service
public class DevoirService {

    @Autowired
    private DevoirRepository devoirRepository;

    public List<Devoir> getAll() { return devoirRepository.findAll(); }
    public List<Devoir> getByFormateur(Long id) { return devoirRepository.findByCoursFormateurId(id); }
    public Devoir save(Devoir devoir) { return devoirRepository.save(devoir); }
    public void delete(Long id) { devoirRepository.deleteById(id); }
}