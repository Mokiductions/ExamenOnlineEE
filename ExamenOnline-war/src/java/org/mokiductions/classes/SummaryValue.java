/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mokiductions.classes;

import java.util.Date;

/**
 *
 * @author Miquel Ginés Borràs
 * @mail ginesborrasm@gmail.com
 */
public class SummaryValue {

    private String user;
    private Date starttime;
    private Date endtime;
    private double score;

    public SummaryValue(String user, Date starttime, Date endtime, double score) {
        this.user = user;
        this.starttime = starttime;
        this.endtime = endtime;
        this.score = score;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
