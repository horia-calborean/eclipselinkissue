/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "Parent")
public class ParentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdParent")
    private Long idParent;

    @Column(name = "Name", nullable = false)
    private String name;
    @JoinTable(name = "ParentChildrens",
            joinColumns = {
                @JoinColumn(name = "IdParent", referencedColumnName = "IdParent")},
            inverseJoinColumns = {
                @JoinColumn(name = "IdChild", referencedColumnName = "IdChild")})
    @ManyToMany
    private List<ChildEntity> children;

   @Version
    @Column(name = "RecordVersion", nullable = false)
    protected Long recordVersion;

    public Long getIdParent() {
        return idParent;
    }

    public void setIdParent(Long idParent) {
        this.idParent = idParent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChildEntity> getChildren() {
        return children;
    }

    public void setChildren(List<ChildEntity> children) {
        this.children = children;
    }

    public Long getRecordVersion() {
        return recordVersion;
    }

    public void setRecordVersion(Long recordVersion) {
        this.recordVersion = recordVersion;
    }
   
   

}
