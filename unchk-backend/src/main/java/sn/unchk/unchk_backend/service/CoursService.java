package sn.unchk.unchk_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.unchk.unchk_backend.entity.Cours;
import sn.unchk.unchk_backend.repository.CoursRepository;
import java.util.List;

@Service
public class CoursService {

    @Autowired
    private CoursRepository coursRepository;

    public List<Cours> getAll() { return coursRepository.findAll(); }
    public List<Cours> getByFormateur(Long id) { return coursRepository.findByFormateurId(id); }
    public Cours save(Cours cours) { return coursRepository.save(cours); }
    public void delete(Long id) { coursRepository.deleteById(id); }
}