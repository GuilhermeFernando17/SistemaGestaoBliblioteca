package com.biblioteca.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "reservas")
public class Reserva {
    @Id
    private String id;

    @DBRef
    private Usuario usuario;

    @DBRef
    private Material material;

    private LocalDate dataReserva;
    private LocalDate dataEmprestimoPrevisto;
    private String status;

    public Reserva() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Material getMaterial() { return material; }
    public void setMaterial(Material material) { this.material = material; }
    public LocalDate getDataReserva() { return dataReserva; }
    public void setDataReserva(LocalDate dataReserva) { this.dataReserva = dataReserva; }
    public LocalDate getDataEmprestimoPrevisto() { return dataEmprestimoPrevisto; }
    public void setDataEmprestimoPrevisto(LocalDate dataEmprestimoPrevisto) { this.dataEmprestimoPrevisto = dataEmprestimoPrevisto; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
