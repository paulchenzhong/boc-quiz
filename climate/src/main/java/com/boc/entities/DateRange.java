package com.boc.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class DateRange {

	// Set date ranger for filter
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateFrom;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateTo;

	public Date getDateFrom() {
		return dateFrom;
	}

	public DateRange() {
		
	}
	
	public DateRange(Date dateFrom, Date dateTo) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
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
