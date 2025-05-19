package com.solarsim.Backend.Service.ProductService;

import com.solarsim.Backend.Model.Product.Inverter;
import com.solarsim.Backend.Repository.ProductRepository.InverterRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InverterService {

    private final InverterRepository inverterRepository;

    public InverterService(InverterRepository inverterRepository) {
        this.inverterRepository = inverterRepository;
    }

    public List<Inverter> getAllInverters() {
        return inverterRepository.findAll();
    }

    public Optional<Inverter> getInverterById(String id) {
        return inverterRepository.findById(id);
    }

    @Transactional
    public void addInverter(Inverter inverter) {
        inverterRepository.save(inverter);
    }

    @Transactional
    public boolean updateInverter(Inverter inverter) {
        Optional<Inverter> existing = inverterRepository.findById(inverter.getId());
        if (existing.isPresent()) {
            inverterRepository.save(inverter);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteInverter(String id) {
        Optional<Inverter> existing = inverterRepository.findById(id);
        if (existing.isPresent()) {
            inverterRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
