/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.bossiodjc.consiti_prueba_final.log.entity;

import dev.bossiodjc.consiti_prueba_final.security.entity.Usuario;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author bossi
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "registro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registro.findAll", query = "SELECT r FROM Registro r"),
    @NamedQuery(name = "Registro.findByIdregistro", query = "SELECT r FROM Registro r WHERE r.idregistro = :idregistro"),
    @NamedQuery(name = "Registro.findByConsulta", query = "SELECT r FROM Registro r WHERE r.consulta = :consulta"),
    @NamedQuery(name = "Registro.findByExitoso", query = "SELECT r FROM Registro r WHERE r.exitoso = :exitoso")})
public class Registro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idregistro")
    private Integer idregistro;
    @Size(max = 100)
    @Column(name = "consulta")
    private String consulta;
    @Size(max = 2)
    @Column(name = "exitoso")
    private String exitoso;
    @Lob
    @Size(max = 1073741824)
    @Column(name = "respuesta", columnDefinition ="json")
    private String respuesta;
    @JoinColumn(name = "usuario", referencedColumnName = "email")
    @ManyToOne
    private Usuario usuario;
    
    public Registro(Integer idregistro) {
        this.idregistro = idregistro;
    }

    public Registro(String consulta, String exitoso, String respuesta, Usuario usuario) {
        this.consulta = consulta;
        this.exitoso = exitoso;
        this.respuesta = respuesta;
        this.usuario = usuario;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idregistro != null ? idregistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registro)) {
            return false;
        }
        Registro other = (Registro) object;
        if ((this.idregistro == null && other.idregistro != null) || (this.idregistro != null && !this.idregistro.equals(other.idregistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dev.bossiodjc.consiti_prueba_final.log.entity.Registro[ idregistro=" + idregistro + " ]";
    }
    
}
