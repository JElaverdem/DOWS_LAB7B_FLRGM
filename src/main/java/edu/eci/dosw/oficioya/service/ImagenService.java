package edu.eci.dosw.oficioya.service;

import edu.eci.dosw.oficioya.model.document.ImagenDocument;
import edu.eci.dosw.oficioya.repository.ImagenRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ImagenService {

    private final ImagenRepository imagenRepository;

    public ImagenService(ImagenRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }

    public ImagenDocument guardar(MultipartFile archivo, String referenciaExterna) throws IOException {
        if (archivo == null || archivo.isEmpty()) {
            throw new IllegalArgumentException("El archivo está vacío");
        }
        String tipo = archivo.getContentType();
        if (tipo == null || !tipo.startsWith("image/")) {
            throw new IllegalArgumentException("El archivo debe ser una imagen");
        }

        ImagenDocument doc = new ImagenDocument(
                archivo.getOriginalFilename(),
                tipo,
                archivo.getSize(),
                archivo.getBytes(),
                LocalDateTime.now(),
                referenciaExterna
        );
        return imagenRepository.save(doc);
    }

    public ImagenDocument buscarPorId(String id) {
        return imagenRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Imagen no encontrada: " + id));
    }

    public List<ImagenDocument> listar() {
        return imagenRepository.findAll();
    }

    public List<ImagenDocument> listarPorReferencia(String referenciaExterna) {
        return imagenRepository.findByReferenciaExterna(referenciaExterna);
    }

    public void eliminar(String id) {
        if (!imagenRepository.existsById(id)) {
            throw new NoSuchElementException("Imagen no encontrada: " + id);
        }
        imagenRepository.deleteById(id);
    }
}