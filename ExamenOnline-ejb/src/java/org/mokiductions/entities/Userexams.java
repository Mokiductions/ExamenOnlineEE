/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mokiductions.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Miquel Ginés Borràs
 * @mail ginesborrasm@gmail.com
 */
@Entity
@Table(name = "userexams")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userexams.findAll", query = "SELECT u FROM Userexams u")
    , @NamedQuery(name = "Userexams.findByUserexamsId", query = "SELECT u FROM Userexams u WHERE u.userexamsId = :userexamsId")
    , @NamedQuery(name = "Userexams.findByUserexamsStarttime", query = "SELECT u FROM Userexams u WHERE u.userexamsStarttime = :userexamsStarttime")
    , @NamedQuery(name = "Userexams.findByUserexamsEndtime", query = "SELECT u FROM Userexams u WHERE u.userexamsEndtime = :userexamsEndtime")})
public class Userexams implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "score")
    private Double score;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userexams_id")
    private Integer userexamsId;
    @Column(name = "userexams_starttime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userexamsStarttime;
    @Column(name = "userexams_endtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userexamsEndtime;
    @OneToMany(mappedBy = "userquestionsIdExam")
    private List<Userquestions> userquestionsList;
    @JoinColumn(name = "userexams_id_userid", referencedColumnName = "userdata_id")
    @ManyToOne
    private Userdata userexamsIdUserid;

    public Userexams() {
    }

    public Userexams(Date userexamsStarttime, Date userexamsEndtime, Userdata userexamsIdUserid) {
        this.userexamsStarttime = userexamsStarttime;
        this.userexamsEndtime = userexamsEndtime;
        this.userexamsIdUserid = userexamsIdUserid;
    }

    public Userexams(Userdata userexamsIdUserid) {
        this.userexamsIdUserid = userexamsIdUserid;
    }

    public Userexams(Integer userexamsId) {
        this.userexamsId = userexamsId;
    }

    public Integer getUserexamsId() {
        return userexamsId;
    }

    public void setUserexamsId(Integer userexamsId) {
        this.userexamsId = userexamsId;
    }

    public Date getUserexamsStarttime() {
        return userexamsStarttime;
    }

    public void setUserexamsStarttime(Date userexamsStarttime) {
        this.userexamsStarttime = userexamsStarttime;
    }

    public Date getUserexamsEndtime() {
        return userexamsEndtime;
    }

    public void setUserexamsEndtime(Date userexamsEndtime) {
        this.userexamsEndtime = userexamsEndtime;
    }

    @XmlTransient
    public List<Userquestions> getUserquestionsList() {
        return userquestionsList;
    }

    public void setUserquestionsList(List<Userquestions> userquestionsList) {
        this.userquestionsList = userquestionsList;
    }

    public Userdata getUserexamsIdUserid() {
        return userexamsIdUserid;
    }

    public void setUserexamsIdUserid(Userdata userexamsIdUserid) {
        this.userexamsIdUserid = userexamsIdUserid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userexamsId != null ? userexamsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userexams)) {
            return false;
        }
        Userexams other = (Userexams) object;
        if ((this.userexamsId == null && other.userexamsId != null) || (this.userexamsId != null && !this.userexamsId.equals(other.userexamsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.mokiductions.entities.Userexams[ userexamsId=" + userexamsId + " ]";
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

}
