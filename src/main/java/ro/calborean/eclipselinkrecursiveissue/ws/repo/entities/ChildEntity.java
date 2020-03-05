package ro.calborean.eclipselinkrecursiveissue.ws.repo.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "Child")
public class ChildEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdChild")
    private Long idChild;

    @Column(name = "Name", nullable = false)
    private String name;

    @JoinTable(name = "ParentChildrens",
            inverseJoinColumns = {
                @JoinColumn(name = "IdParent", referencedColumnName = "IdParent")},
            joinColumns = {
                @JoinColumn(name = "IdChild", referencedColumnName = "IdChild")})
    private List<ParentEntity> parents;

    @Version
    @Column(name = "RecordVersion", nullable = false)
    protected Long recordVersion;

    public Long getIdChild() {
        return idChild;
    }

    public void setIdChild(Long idChild) {
        this.idChild = idChild;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<ParentEntity> getParents() {
        return parents;
    }

    public void setParents(List<ParentEntity> parents) {
        this.parents = parents;
    }

    public Long getRecordVersion() {
        return recordVersion;
    }

    public void setRecordVersion(Long recordVersion) {
        this.recordVersion = recordVersion;
    }

}
