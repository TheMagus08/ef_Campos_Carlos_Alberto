package com.ef.ef_CamposCarlosAlberto.Service.Impl;

import com.ef.ef_CamposCarlosAlberto.Model.Empleado;

import java.util.List;

public interface EmpleadoService {
    Empleado guardarEmpleado(Empleado empleado);

    Empleado actualizarEmpleado(Empleado empleado);

    void eliminarEmpleado(Long id);

    Empleado obtenerEmpleado(Long id);

    List<Empleado> listarTodosLosEmpleados();
}
