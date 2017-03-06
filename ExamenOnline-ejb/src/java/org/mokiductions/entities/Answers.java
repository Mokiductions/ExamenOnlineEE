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
@Table(name = "answers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Answers.findAll", query = "SELECT a FROM Answers a")
    , @NamedQuery(name = "Answers.findByAnswersId", query = "SELECT a FROM Answers a WHERE a.answersId = :answersId")
    , @NamedQuery(name = "Answers.findByAnswersAnswer", query = "SELECT a FROM Answers a WHERE a.answersAnswer = :answersAnswer")})
public class Answers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "answers_id")
    private Integer answersId;
    @Size(max = 255)
    @Column(name = "answers_answer")
    private String answersAnswer;
    @JoinColumn(name = "answers_id_question", referencedColumnName = "questions_id")
    @ManyToOne
    private Questions answersIdQuestion;

    public Answers() {
    }

    public Answers(Integer answersId) {
        this.answersId = answersId;
    }

    public Integer getAnswersId() {
        return answersId;
    }

    public void setAnswersId(Integer answersId) {
        this.answersId = answersId;
    }

    public String getAnswersAnswer() {
        return answersAnswer;
    }

    public void setAnswersAnswer(String answersAnswer) {
        this.answersAnswer = answersAnswer;
    }

    public Questions getAnswersIdQuestion() {
        return answersIdQuestion;
    }

    public void setAnswersIdQuestion(Questions answersIdQuestion) {
        this.answersIdQuestion = answersIdQuestion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (answersId != null ? answersId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Answers)) {
            return false;
        }
        Answers other = (Answers) object;
        if ((this.answersId == null && other.answersId != null) || (this.answersId != null && !this.answersId.equals(other.answersId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.mokiductions.entities.Answers[ answersId=" + answersId + " ]";
    }

}
