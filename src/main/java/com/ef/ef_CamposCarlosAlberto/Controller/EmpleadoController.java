package com.ef.ef_CamposCarlosAlberto.Controller;

import com.ef.ef_CamposCarlosAlberto.Model.Empleado;
import com.ef.ef_CamposCarlosAlberto.Service.Impl.EmpleadoService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public List<Empleado> listarTodosLosEmpleados() {
        return empleadoService.listarTodosLosEmpleados();
    }

    @GetMapping("/{id}")
    public Empleado obtenerEmpleado(@PathVariable("id") Long id) {
        return empleadoService.obtenerEmpleado(id);
    }

    @PostMapping
    public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
        return empleadoService.guardarEmpleado(empleado);
    }

    @PutMapping("/{id}")
    public Empleado actualizarEmpleado(@PathVariable("id") Long id, @RequestBody Empleado empleado) {
        empleado.setId(id);
        return empleadoService.actualizarEmpleado(empleado);
    }

    @DeleteMapping("/{id}")
    public void eliminarEmpleado(@PathVariable("id") Long id) {
        empleadoService.eliminarEmpleado(id);
    }


    @GetMapping("/reporteEmpleados01")
    public ResponseEntity<byte[]> visualizarReporteEmpleados() throws JRException {

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(empleadoService.listarTodosLosEmpleados());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("titulo", "Reporte de Empleados");
        JasperPrint jasperPrint = JasperFillManager.fillReport("src/main/resources/reportes/reporteEmpleados.jasper", parameters, dataSource);

        byte[] reporte = JasperExportManager.exportReportToPdf(jasperPrint);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.inline().filename("empleados.pdf").build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(reporte);

    }
    @GetMapping("/reporteEmpleados02")
    public ResponseEntity<byte[]> descargarReporteEmpleados() throws JRException {
        JasperReport report = JasperCompileManager.compileReport("src/main/resources/reportes/reporteEmpleados.jrxml");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(empleadoService.listarTodosLosEmpleados());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("titulo", "Reporte de Empleados");
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);

        byte[] reporte = JasperExportManager.exportReportToPdf(jasperPrint);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.attachment().filename("empleados.pdf").build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(reporte);
    }

}
