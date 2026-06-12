package sn.unchk.unchk_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.unchk.unchk_backend.entity.CompteRendu;
import sn.unchk.unchk_backend.repository.CompteRenduRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CompteRenduService {

    @Autowired
    private CompteRenduRepository compteRenduRepository;

    public List<CompteRendu> getAllCompteRendus() {
        return compteRenduRepository.findAll();
    }

    public Optional<CompteRendu> getCompteRenduById(Long id) {
        return compteRenduRepository.findById(id);
    }

    public CompteRendu createCompteRendu(CompteRendu compteRendu) {
        return compteRenduRepository.save(compteRendu);
    }

    public CompteRendu updateCompteRendu(Long id, CompteRendu compteRendu) {
        compteRendu.setId(id);
        return compteRenduRepository.save(compteRendu);
    }

    public void deleteCompteRendu(Long id) {
        compteRenduRepository.deleteById(id);
    }
}