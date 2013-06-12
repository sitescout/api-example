package com.sitescout.ui.page;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;


/**
 * Stores the selected date range for use throughout the site.
 */
@Named
@SessionScoped
public class CalendarBean implements Serializable {

    private Date dateFrom;
    private Date dateTo;

    public Date getDateFrom() {
        return dateFrom;
    }
    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;

    }

    public Date getDateTo() {
        return dateTo;
    }
    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

}

