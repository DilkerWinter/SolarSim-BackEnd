package com.solarsim.Backend.Repository.ProductRepository;

import com.solarsim.Backend.Model.Product.Cable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CableRepository extends JpaRepository<Cable, String> {
}
