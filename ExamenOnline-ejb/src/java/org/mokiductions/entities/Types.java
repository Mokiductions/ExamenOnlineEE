/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mokiductions.entities;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Miquel Ginés Borràs
 * @mail ginesborrasm@gmail.com
 */
@Entity
@Table(name = "types")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Types.findAll", query = "SELECT t FROM Types t")
    , @NamedQuery(name = "Types.findByTypesId", query = "SELECT t FROM Types t WHERE t.typesId = :typesId")
    , @NamedQuery(name = "Types.findByTypesType", query = "SELECT t FROM Types t WHERE t.typesType = :typesType")})
public class Types implements Serializable {

    @Expose
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "types_id")
    @Expose
    private Integer typesId;
    @Size(max = 255)
    @Column(name = "types_type")
    @Expose
    private String typesType;
    @OneToMany(mappedBy = "questionsIdType")
    private List<Questions> questionsList;

    public Types() {
    }

    public Types(Integer typesId) {
        this.typesId = typesId;
    }

    public Integer getTypesId() {
        return typesId;
    }

    public void setTypesId(Integer typesId) {
        this.typesId = typesId;
    }

    public String getTypesType() {
        return typesType;
    }

    public void setTypesType(String typesType) {
        this.typesType = typesType;
    }

    @XmlTransient
    public List<Questions> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<Questions> questionsList) {
        this.questionsList = questionsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typesId != null ? typesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Types)) {
            return false;
        }
        Types other = (Types) object;
        if ((this.typesId == null && other.typesId != null) || (this.typesId != null && !this.typesId.equals(other.typesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.mokiductions.entities.Types[ typesId=" + typesId + " ]";
    }

}
