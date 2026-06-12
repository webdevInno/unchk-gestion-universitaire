package sn.unchk.unchk_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.unchk.unchk_backend.entity.Annonce;
import sn.unchk.unchk_backend.repository.AnnonceRepository;
import java.util.List;

@Service
public class AnnonceService {

    @Autowired
    private AnnonceRepository annonceRepository;

    public List<Annonce> getAll() { return annonceRepository.findAll(); }
    public List<Annonce> getByCible(String cible) { return annonceRepository.findByCible(cible); }
    public Annonce save(Annonce annonce) { return annonceRepository.save(annonce); }
    public void delete(Long id) { annonceRepository.deleteById(id); }
}