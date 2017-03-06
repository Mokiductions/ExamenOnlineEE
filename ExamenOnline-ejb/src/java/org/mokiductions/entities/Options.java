/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mokiductions.entities;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miquel Ginés Borràs
 * @mail ginesborrasm@gmail.com
 */
@Entity
@Table(name = "options")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Options.findAll", query = "SELECT o FROM Options o")
    , @NamedQuery(name = "Options.findByOptionsId", query = "SELECT o FROM Options o WHERE o.optionsId = :optionsId")
    , @NamedQuery(name = "Options.findByOptionsOption", query = "SELECT o FROM Options o WHERE o.optionsOption = :optionsOption")})
public class Options implements Serializable {
    @Expose
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "options_id")
    @Expose
    private Integer optionsId;
    @Size(max = 255)
    @Column(name = "options_option")
    @Expose
    private String optionsOption;
    @JoinColumn(name = "options_id_question", referencedColumnName = "questions_id")
    @ManyToOne
    private Questions optionsIdQuestion;

    public Options() {
    }

    public Options(Integer optionsId) {
        this.optionsId = optionsId;
    }

    public Integer getOptionsId() {
        return optionsId;
    }

    public void setOptionsId(Integer optionsId) {
        this.optionsId = optionsId;
    }

    public String getOptionsOption() {
        return optionsOption;
    }

    public void setOptionsOption(String optionsOption) {
        this.optionsOption = optionsOption;
    }

    public Questions getOptionsIdQuestion() {
        return optionsIdQuestion;
    }

    public void setOptionsIdQuestion(Questions optionsIdQuestion) {
        this.optionsIdQuestion = optionsIdQuestion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (optionsId != null ? optionsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Options)) {
            return false;
        }
        Options other = (Options) object;
        if ((this.optionsId == null && other.optionsId != null) || (this.optionsId != null && !this.optionsId.equals(other.optionsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.mokiductions.entities.Options[ optionsId=" + optionsId + " ]";
    }

}
