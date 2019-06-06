package com.example.SimulacroParcial.repository;

import com.example.SimulacroParcial.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
}
