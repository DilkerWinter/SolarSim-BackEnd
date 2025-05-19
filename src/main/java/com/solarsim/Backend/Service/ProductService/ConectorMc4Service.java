package com.solarsim.Backend.Service.ProductService;

import com.solarsim.Backend.Model.Product.ConectorMc4;
import com.solarsim.Backend.Repository.ProductRepository.ConectorMc4Repository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConectorMc4Service {

    private final ConectorMc4Repository conectorMc4Repository;

    public ConectorMc4Service(ConectorMc4Repository conectorMc4Repository) {
        this.conectorMc4Repository = conectorMc4Repository;
    }

    public List<ConectorMc4> getAllConectorMc4() {
        return conectorMc4Repository.findAll();
    }

    public Optional<ConectorMc4> getConectorMc4ById(String id) {
        return conectorMc4Repository.findById(id);
    }

    @Transactional
    public void addConectorMc4(ConectorMc4 conectorMc4) {
        conectorMc4Repository.save(conectorMc4);
    }

    @Transactional
    public boolean updateConectorMc4(ConectorMc4 conectorMc4) {
        Optional<ConectorMc4> conectorMc4Optional = conectorMc4Repository.findById(conectorMc4.getId());
        if (conectorMc4Optional.isPresent()) {
            conectorMc4Repository.save(conectorMc4);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteConectorMc4(String id) {
        Optional<ConectorMc4> conectorMc4Optional = conectorMc4Repository.findById(id);
        if (conectorMc4Optional.isPresent()) {
            conectorMc4Repository.deleteById(id);
            return true;
        }
        return false;
    }
}
