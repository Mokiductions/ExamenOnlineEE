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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "questions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Questions.findAll", query = "SELECT q FROM Questions q")
    , @NamedQuery(name = "Questions.findByQuestionsId", query = "SELECT q FROM Questions q WHERE q.questionsId = :questionsId")
    , @NamedQuery(name = "Questions.findByQuestionsTitle", query = "SELECT q FROM Questions q WHERE q.questionsTitle = :questionsTitle")})
public class Questions implements Serializable {

    @Expose
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "questions_id")
    @Expose
    private Integer questionsId;
    @Size(max = 255)
    @Column(name = "questions_title")
    @Expose
    private String questionsTitle;
    @OneToMany(mappedBy = "userquestionsIdQuestion")
    private List<Userquestions> userquestionsList;
    @OneToMany(mappedBy = "answersIdQuestion")
    private List<Answers> answersList;
    @OneToMany(mappedBy = "optionsIdQuestion")
    @Expose
    private List<Options> optionsList;
    @JoinColumn(name = "questions_id_type", referencedColumnName = "types_id")
    @ManyToOne
    @Expose
    private Types questionsIdType;

    public Questions() {
    }

    public Questions(Integer questionsId) {
        this.questionsId = questionsId;
    }

    public Integer getQuestionsId() {
        return questionsId;
    }

    public void setQuestionsId(Integer questionsId) {
        this.questionsId = questionsId;
    }

    public String getQuestionsTitle() {
        return questionsTitle;
    }

    public void setQuestionsTitle(String questionsTitle) {
        this.questionsTitle = questionsTitle;
    }

    @XmlTransient
    public List<Userquestions> getUserquestionsList() {
        return userquestionsList;
    }

    public void setUserquestionsList(List<Userquestions> userquestionsList) {
        this.userquestionsList = userquestionsList;
    }

    @XmlTransient
    public List<Answers> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(List<Answers> answersList) {
        this.answersList = answersList;
    }

    @XmlTransient
    public List<Options> getOptionsList() {
        return optionsList;
    }

    public void setOptionsList(List<Options> optionsList) {
        this.optionsList = optionsList;
    }

    public Types getQuestionsIdType() {
        return questionsIdType;
    }

    public void setQuestionsIdType(Types questionsIdType) {
        this.questionsIdType = questionsIdType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionsId != null ? questionsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Questions)) {
            return false;
        }
        Questions other = (Questions) object;
        if ((this.questionsId == null && other.questionsId != null) || (this.questionsId != null && !this.questionsId.equals(other.questionsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.mokiductions.entities.Questions[ questionsId=" + questionsId + " ]";
    }

}
