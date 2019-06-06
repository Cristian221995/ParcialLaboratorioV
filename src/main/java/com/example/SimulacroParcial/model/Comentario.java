package com.example.SimulacroParcial.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comentario {
    @Id
    @GeneratedValue
    private Integer id;

    private String descripcion;
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id")
    @JsonIgnore
    private Publicacion publicacion;

    @PrePersist
    private void setearFecha(){
        this.setFecha(LocalDate.now());
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        //this.setFecha(LocalDate.parse(formatter.format(LocalDate.now())));
    }
}
