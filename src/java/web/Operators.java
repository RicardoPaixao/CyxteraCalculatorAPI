package web;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ricardo Paixão
 */
@Entity
@Table(name = "operators")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operators.findAll", query = "SELECT o FROM Operators o"),
    @NamedQuery(name = "Operators.findById", query = "SELECT o FROM Operators o WHERE o.id = :id"),
    @NamedQuery(name = "Operators.findByCreatedAt", query = "SELECT o FROM Operators o WHERE o.createdAt = :createdAt"),
    @NamedQuery(name = "Operators.findBySessionId", query = "SELECT o FROM Operators o WHERE o.sessionId = :sessionId"),
    @NamedQuery(name = "Operators.findLastTwo", query = "SELECT o FROM Operators o WHERE o.sessionId = :sessionId ORDER BY o.createdAt DESC"),
    @NamedQuery(name = "Operators.findByValue", query = "SELECT o FROM Operators o WHERE o.value = :value")})
public class Operators implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "value")
    private float value;
        @Basic(optional = false)
    @NotNull
    @Column(name = "sessionId")
    private Integer sessionId;

    public Operators() {
    }

    public Operators(Integer id) {
        this.id = id;
    }

    public Operators(Integer id, Date createdAt, float value, Integer sessionId) {
        this.id = id;
        this.createdAt = createdAt;
        this.value = value;
        this.sessionId=sessionId;
    }

        public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;

    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operators)) {
            return false;
        }
        Operators other = (Operators) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.Operators[ id=" + id + " ]";
    }
    
}
