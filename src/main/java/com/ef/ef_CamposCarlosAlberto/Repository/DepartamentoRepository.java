package com.ef.ef_CamposCarlosAlberto.Repository;

import com.ef.ef_CamposCarlosAlberto.Model.Departamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends CrudRepository<Departamento, Long> {
}
