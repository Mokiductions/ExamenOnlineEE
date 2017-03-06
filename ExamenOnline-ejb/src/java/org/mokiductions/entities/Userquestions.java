/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mokiductions.entities;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Miquel Ginés Borràs
 * @mail ginesborrasm@gmail.com
 */
@Entity
@Table(name = "userquestions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userquestions.findAll", query = "SELECT u FROM Userquestions u")
    , @NamedQuery(name = "Userquestions.findByUserquestionsId", query = "SELECT u FROM Userquestions u WHERE u.userquestionsId = :userquestionsId")})
public class Userquestions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userquestions_id")
    private Integer userquestionsId;
    @JoinColumn(name = "userquestions_id_question", referencedColumnName = "questions_id")
    @ManyToOne
    private Questions userquestionsIdQuestion;
    @JoinColumn(name = "userquestions_id_exam", referencedColumnName = "userexams_id")
    @ManyToOne
    private Userexams userquestionsIdExam;
    @OneToMany(mappedBy = "useranswersIdQuestion")
    private List<Useranswers> useranswersList;

    public Userquestions() {
    }

    public Userquestions(Integer userquestionsId) {
        this.userquestionsId = userquestionsId;
    }

    public Integer getUserquestionsId() {
        return userquestionsId;
    }

    public void setUserquestionsId(Integer userquestionsId) {
        this.userquestionsId = userquestionsId;
    }

    public Questions getUserquestionsIdQuestion() {
        return userquestionsIdQuestion;
    }

    public void setUserquestionsIdQuestion(Questions userquestionsIdQuestion) {
        this.userquestionsIdQuestion = userquestionsIdQuestion;
    }

    public Userexams getUserquestionsIdExam() {
        return userquestionsIdExam;
    }

    public void setUserquestionsIdExam(Userexams userquestionsIdExam) {
        this.userquestionsIdExam = userquestionsIdExam;
    }

    @XmlTransient
    public List<Useranswers> getUseranswersList() {
        return useranswersList;
    }

    public void setUseranswersList(List<Useranswers> useranswersList) {
        this.useranswersList = useranswersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userquestionsId != null ? userquestionsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userquestions)) {
            return false;
        }
        Userquestions other = (Userquestions) object;
        if ((this.userquestionsId == null && other.userquestionsId != null) || (this.userquestionsId != null && !this.userquestionsId.equals(other.userquestionsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.mokiductions.entities.Userquestions[ userquestionsId=" + userquestionsId + " ]";
    }

}
