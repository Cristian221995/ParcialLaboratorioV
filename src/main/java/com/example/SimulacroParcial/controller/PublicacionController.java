package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.model.Comentario;
import com.example.SimulacroParcial.model.Publicacion;
import com.example.SimulacroParcial.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@Controller
@RequestMapping("/publicaciones")
@RestController
public class PublicacionController {
    @Autowired
    private PublicacionRepository publicacionRepository;

    private String PUBLICACION_NOT_FOUND = "La publicacion no existe";

    @PostMapping("/{idPublic}")
    private void addComentario(@PathVariable final Integer idPublic,@RequestBody final Comentario c){
        Publicacion p = publicacionRepository.findById(idPublic).orElseThrow(()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(PUBLICACION_NOT_FOUND, idPublic)));
        p.getComentarios().add(c);
        c.setPublicacion(p);
        c.setUsuario(p.getUsuario());
        publicacionRepository.save(p);
    }

    @GetMapping("/deleteComents/{idUsuario}")
    private void deleteComentariosByUsuario(@PathVariable final Integer idUsuario){
        for(Publicacion p : publicacionRepository.findAll()){
            for(Comentario c : p.getComentarios()){
                if(c.getUsuario().getId().equals(idUsuario)){
                    p.getComentarios().remove(c);
                    publicacionRepository.save(p);
                }
            }
        }
    }

    @Scheduled(fixedDelay = 60000)
    public void deleteComents(){
        for(Publicacion p : publicacionRepository.findAll()){

        }
    }

}
