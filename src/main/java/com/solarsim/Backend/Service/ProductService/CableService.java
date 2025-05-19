package com.solarsim.Backend.Service.ProductService;

import com.solarsim.Backend.Model.Product.Cable;
import com.solarsim.Backend.Repository.ProductRepository.CableRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CableService {

    private final CableRepository cableRepository;

    public CableService(CableRepository cableRepository) {
        this.cableRepository = cableRepository;
    }

    public List<Cable> getAllCables() {
        return cableRepository.findAll();
    }

    public Optional<Cable> getCableById(String id) {
        return cableRepository.findById(id);
    }

    @Transactional
    public void addCable(Cable cable) {
        cableRepository.save(cable);
    }

    @Transactional
    public boolean updateCable(Cable cable) {
        Optional<Cable> cableOptional = cableRepository.findById(cable.getId());
        if (cableOptional.isPresent()) {
            cableRepository.save(cable);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteCable(String id) {
        Optional<Cable> cableOptional = cableRepository.findById(id);
        if (cableOptional.isPresent()) {
            cableRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
