package com.solarsim.Backend.Repository.ProductRepository;

import com.solarsim.Backend.Model.Product.Inverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InverterRepository extends JpaRepository<Inverter, String> {
}
