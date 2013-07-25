package com.sitescout.ui.page;


import javax.enterprise.event.Event;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;


/**
 * Stores the selected date range for use throughout the site.
 */
@Named
@SessionScoped
public class CalendarBean implements Serializable {

    @Inject Event<DateChangeEvent> dateChangeEvent;

    private Date dateFrom;
    private Date dateTo;

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        if (dateFrom == null) {
            this.dateFrom = null;
            this.dateTo = null;
            dateChangeEvent.fire(new DateChangeEvent());
            return;
        }
        if (dateFrom.after(new Date())) {
            return;
        }

        if (this.dateFrom != null) {
            if (!this.dateFrom.equals(dateFrom)) {
                this.dateFrom = dateFrom;
                if (dateTo != null && dateFrom.after(dateTo)) {
                    this.dateTo = null;
                }
                dateChangeEvent.fire(new DateChangeEvent());
            }
        } else {
            this.dateFrom = dateFrom;
            if (dateTo != null && dateFrom.after(dateTo)) {
                this.dateTo = null;
            }
            dateChangeEvent.fire(new DateChangeEvent());
        }
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        if (dateTo == null) {
            this.dateTo = null;
            dateChangeEvent.fire(new DateChangeEvent());
            return;
        }
        if (dateTo.after(new Date())) {
            return;
        }
        if (dateTo.after(dateFrom)) {
            if (this.dateTo != null) {
                if (!this.dateTo.equals(dateTo)) {
                    this.dateTo = dateTo;
                    dateChangeEvent.fire(new DateChangeEvent());
                }
            } else {
                this.dateTo = dateTo;
                dateChangeEvent.fire(new DateChangeEvent());
            }
        }
    }

    /**
     * An observable event notifying that the currently selected date range has been updated.
     */
    public class DateChangeEvent {

    }


}

