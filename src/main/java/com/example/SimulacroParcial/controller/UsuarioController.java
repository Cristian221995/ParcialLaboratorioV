package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.model.Publicacion;
import com.example.SimulacroParcial.model.Usuario;
import com.example.SimulacroParcial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
@RestController
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private String USUARIO_NOT_FOUND = "El usuario no existe";

    @PostMapping("")
    private void addUsuario(@RequestBody final Usuario u){
        //u.setBrowser(this.setearBrowser());
        usuarioRepository.save(u);
    }

    @GetMapping("")
    private List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    @PostMapping("/public/{idUsuario}")
    private void addPublicacion(@PathVariable final Integer idUsuario, @RequestBody Publicacion p){
        Usuario u = usuarioRepository.findById(idUsuario).orElseThrow(()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(USUARIO_NOT_FOUND, idUsuario)));
        u.getPublicaciones().add(p);
        p.setUsuario(u);
        this.addUsuario(u);
    }

    @GetMapping("/delete/{idUsuario}")
    private void deleteById(@PathVariable final Integer idUsuario){
        Usuario u = usuarioRepository.findById(idUsuario).orElseThrow(()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(USUARIO_NOT_FOUND, idUsuario)));
        usuarioRepository.deleteById(idUsuario);
    }

    @GetMapping("/{idUsuario}")
    private Usuario getById(@PathVariable final Integer idUsuario){
        return usuarioRepository.findById(idUsuario).orElseThrow(()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(USUARIO_NOT_FOUND, idUsuario)));

    }
}
