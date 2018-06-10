package edu.mum.coffee.repository;

import edu.mum.coffee.domain.Orderline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderlineRepository extends JpaRepository<Orderline, Integer> {

}
