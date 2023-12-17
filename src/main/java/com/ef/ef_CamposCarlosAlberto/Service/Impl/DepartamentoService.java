package com.ef.ef_CamposCarlosAlberto.Service.Impl;

import com.ef.ef_CamposCarlosAlberto.Model.Departamento;

import java.util.List;

public interface DepartamentoService {

    Departamento guardarDepartamento(Departamento departamento);

    Departamento actualizarDepartamento(Departamento departamento);

    void eliminarDepartamento(Long id);

    Departamento obtenerDepartamento(Long id);

    List<Departamento> listarTodosLosDepartamentos();
}

