/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mokiductions.entities;

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
@Table(name = "useranswers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Useranswers.findAll", query = "SELECT u FROM Useranswers u")
    , @NamedQuery(name = "Useranswers.findByUseranswersId", query = "SELECT u FROM Useranswers u WHERE u.useranswersId = :useranswersId")
    , @NamedQuery(name = "Useranswers.findByUserquestionsAnswer", query = "SELECT u FROM Useranswers u WHERE u.userquestionsAnswer = :userquestionsAnswer")})
public class Useranswers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "useranswers_id")
    private Integer useranswersId;
    @Size(max = 255)
    @Column(name = "userquestions_answer")
    private String userquestionsAnswer;
    @JoinColumn(name = "useranswers_id_question", referencedColumnName = "userquestions_id")
    @ManyToOne
    private Userquestions useranswersIdQuestion;

    public Useranswers() {
    }

    public Useranswers(Integer useranswersId) {
        this.useranswersId = useranswersId;
    }

    public Integer getUseranswersId() {
        return useranswersId;
    }

    public void setUseranswersId(Integer useranswersId) {
        this.useranswersId = useranswersId;
    }

    public String getUserquestionsAnswer() {
        return userquestionsAnswer;
    }

    public void setUserquestionsAnswer(String userquestionsAnswer) {
        this.userquestionsAnswer = userquestionsAnswer;
    }

    public Userquestions getUseranswersIdQuestion() {
        return useranswersIdQuestion;
    }

    public void setUseranswersIdQuestion(Userquestions useranswersIdQuestion) {
        this.useranswersIdQuestion = useranswersIdQuestion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (useranswersId != null ? useranswersId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Useranswers)) {
            return false;
        }
        Useranswers other = (Useranswers) object;
        if ((this.useranswersId == null && other.useranswersId != null) || (this.useranswersId != null && !this.useranswersId.equals(other.useranswersId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.mokiductions.entities.Useranswers[ useranswersId=" + useranswersId + " ]";
    }

}
