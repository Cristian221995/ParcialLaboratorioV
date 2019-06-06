package com.example.SimulacroParcial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue
    private Integer id;

    private String nombre;
    private String apellido;
    private String browser;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Publicacion> publicaciones;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Comentario> comentarios;

    /*No funciona no se por que.
    @PrePersist
    private void setearBrowser(HttpServletRequest request){
        this.setBrowser(request.getHeader("user-agent"));
    }

     */
}
