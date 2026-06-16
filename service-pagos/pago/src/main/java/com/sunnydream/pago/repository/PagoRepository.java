package com.sunnydream.pago.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sunnydream.pago.model.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long>{
    
    List<Pago> findByIdVenta(Long idVenta);

    List<Pago> findByIdCliente(Long idCliente);

}
