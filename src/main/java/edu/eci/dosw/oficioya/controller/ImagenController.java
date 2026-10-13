package edu.eci.dosw.oficioya.controller;

import edu.eci.dosw.oficioya.model.document.ImagenDocument;
import edu.eci.dosw.oficioya.service.ImagenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/imagenes")
public class ImagenController {

    private final ImagenService imagenService;

    public ImagenController(ImagenService imagenService) {
        this.imagenService = imagenService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> subir(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "referenciaExterna", required = false) String referenciaExterna)
            throws IOException {
        ImagenDocument guardada = imagenService.guardar(file, referenciaExterna);
        return ResponseEntity.status(HttpStatus.CREATED).body(metadatos(guardada));
    }

    @GetMapping
    public List<Map<String, Object>> listar() {
        return imagenService.listar().stream().map(this::metadatos).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> obtener(@PathVariable String id) {
        ImagenDocument img = imagenService.buscarPorId(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(img.getTipoContenido()))
                .body(img.getDatos());
    }

    @GetMapping("/referencia/{referenciaExterna}")
    public List<Map<String, Object>> listarPorReferencia(@PathVariable String referenciaExterna) {
        return imagenService.listarPorReferencia(referenciaExterna).stream()
                .map(this::metadatos).toList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        imagenService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private Map<String, Object> metadatos(ImagenDocument i) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", i.getId());
        m.put("nombre", i.getNombre());
        m.put("tipoContenido", i.getTipoContenido());
        m.put("tamano", i.getTamano());
        m.put("fechaCarga", i.getFechaCarga());
        m.put("referenciaExterna", i.getReferenciaExterna());
        return m;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> noEncontrado(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> invalido(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }
}