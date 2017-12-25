/*
 * Created on 14 Apr 2017 ( Time 09:58:32 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service;

import java.util.List;

import org.trams.hello.bean.web.admin.ReservationRow;
import org.trams.hello.web.bean.search.SearchReservation;

/**
 * Business Service Interface for entity Bank.
 */
public interface ReservationService { 
	
	List<ReservationRow> searchReservationList(SearchReservation searchReservation, Integer pageSize);
	
	Long reservationListCount(SearchReservation searchReservation);

}