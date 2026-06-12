package sn.unchk.unchk_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.unchk.unchk_backend.entity.SeanceCours;
import sn.unchk.unchk_backend.repository.SeanceCoursRepository;
import java.util.List;

@Service
public class SeanceCoursService {

    @Autowired
    private SeanceCoursRepository seanceRepository;

    public List<SeanceCours> getAll() { return seanceRepository.findAll(); }
    public List<SeanceCours> getByFormateur(Long id) { return seanceRepository.findByCoursFormateurId(id); }
    public List<SeanceCours> getByFormation(String formation) { return seanceRepository.findByCoursFormation(formation); }
    public SeanceCours save(SeanceCours seance) { return seanceRepository.save(seance); }
    public void delete(Long id) { seanceRepository.deleteById(id); }
}