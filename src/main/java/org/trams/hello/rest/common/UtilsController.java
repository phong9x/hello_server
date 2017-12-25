package org.trams.hello.rest.common;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trams.hello.bean.jpa.CounselorEntity;
import org.trams.hello.business.service.BusinessSubService;
import org.trams.hello.business.service.CounselingSessionService;
import org.trams.hello.business.service.CounselorScheduleSettingService;
import org.trams.hello.data.repository.jpa.CounselorJpaRepository;

public class UtilsController {

	protected static Logger log = LoggerFactory.getLogger(UtilsController.class);
	
	@Resource
	private BusinessSubService businessSubService;
	@Resource
	private static CounselorJpaRepository counselorJpaRepository;
	@Resource
	private static CounselorScheduleSettingService counselorScheduleSettingService;
	@Resource
	private static CounselingSessionService counselingSessionService;
	
	public static void main(String args[]) {
		
	}
	
	public static void orderListBy(List<CounselorEntity> counselorDiamond,List<CounselorEntity> counselorGoleAndSilver, boolean isActive){
		Collections.sort(counselorDiamond, new Comparator<CounselorEntity>() {
			@Override
			public int compare(CounselorEntity o1Diamond, CounselorEntity o2Diamon) {
				if (o1Diamond.getStatusOnline().equals(o2Diamon.getStatusOnline())) {
					if (o1Diamond.getIsReservation().equals(o2Diamon.getIsReservation())) {
						if (o1Diamond.getGuidanceUsername().equals(o2Diamon.getGuidanceUsername())) {
							return o2Diamon.getGuidanceUsername().compareToIgnoreCase(o1Diamond.getGuidanceUsername());
						} else {
							return o1Diamond.getGuidanceUsername().compareToIgnoreCase(o2Diamon.getGuidanceUsername());
						}
					} else {
						return o2Diamon.getIsReservation() - o1Diamond.getIsReservation();
					}
					
				} else {
						return o2Diamon.getStatusOnline() - o1Diamond.getStatusOnline();
				}
				
			}
		});
		
		Collections.sort(counselorGoleAndSilver, new Comparator<CounselorEntity>() {
			@Override
			public int compare(CounselorEntity o1GoleAndSilver, CounselorEntity o2GoleAndSilver) {
				
				if (o1GoleAndSilver.getStatusOnline().equals(o2GoleAndSilver.getStatusOnline())) {
					if (o1GoleAndSilver.getIsReservation().equals(o2GoleAndSilver.getIsReservation())) {
						if (o1GoleAndSilver.getLevel().equals(o2GoleAndSilver.getLevel())) {
							if (o1GoleAndSilver.getGuidanceUsername().equals(o2GoleAndSilver.getGuidanceUsername())) {
								return o2GoleAndSilver.getGuidanceUsername().compareToIgnoreCase(o1GoleAndSilver.getGuidanceUsername());
							} else {
								return o1GoleAndSilver.getGuidanceUsername().compareToIgnoreCase(o2GoleAndSilver.getGuidanceUsername());
							}
						} else {
							return o2GoleAndSilver.getLevel() - o1GoleAndSilver.getLevel();
						}
					} else {
						return o2GoleAndSilver.getIsReservation() - o1GoleAndSilver.getIsReservation();
					}
				} else {
						return o2GoleAndSilver.getStatusOnline() - o1GoleAndSilver.getStatusOnline();
				}
			}
		});
	}
	
	public static void orderListByMincheck(List<CounselorEntity> counselorDiamond,List<CounselorEntity> counselorGoleAndSilver, boolean isActive){
		Collections.sort(counselorDiamond, new Comparator<CounselorEntity>() {
			@Override
			public int compare(CounselorEntity o1Diamond, CounselorEntity o2Diamon) {
				if (o1Diamond.getLevelVip().equals(o2Diamon.getLevelVip())) {
					if (o1Diamond.getStatusOnline().equals(o2Diamon.getStatusOnline())) {
						if (o1Diamond.getIsReservation().equals(o2Diamon.getIsReservation())) {
							if (o1Diamond.getGuidanceUsername().equals(o2Diamon.getGuidanceUsername())) {
								return o2Diamon.getGuidanceUsername().compareToIgnoreCase(o1Diamond.getGuidanceUsername());
							} else {
								return o1Diamond.getGuidanceUsername().compareToIgnoreCase(o2Diamon.getGuidanceUsername());
							}
						} else {
							return o2Diamon.getIsReservation() - o1Diamond.getIsReservation();
						}
						
					} else {
							return o2Diamon.getStatusOnline() - o1Diamond.getStatusOnline();
					}
				} else {
					return o2Diamon.getLevelVip() - o1Diamond.getLevelVip();
				}
			}
		});
		
		Collections.sort(counselorGoleAndSilver, new Comparator<CounselorEntity>() {
			@Override
			public int compare(CounselorEntity o1GoleAndSilver, CounselorEntity o2GoleAndSilver) {
				if (o1GoleAndSilver.getLevelVip().equals(o2GoleAndSilver.getLevelVip())) {
					if (o1GoleAndSilver.getStatusOnline().equals(o2GoleAndSilver.getStatusOnline())) {
						if (o1GoleAndSilver.getIsReservation().equals(o2GoleAndSilver.getIsReservation())) {
							if (o1GoleAndSilver.getLevel().equals(o2GoleAndSilver.getLevel())) {
								if (o1GoleAndSilver.getGuidanceUsername().equals(o2GoleAndSilver.getGuidanceUsername())) {
									return o2GoleAndSilver.getGuidanceUsername().compareToIgnoreCase(o1GoleAndSilver.getGuidanceUsername());
								} else {
									return o1GoleAndSilver.getGuidanceUsername().compareToIgnoreCase(o2GoleAndSilver.getGuidanceUsername());
								}
							} else {
								return o2GoleAndSilver.getLevel() - o1GoleAndSilver.getLevel();
							}
						} else {
							return o2GoleAndSilver.getIsReservation() - o1GoleAndSilver.getIsReservation();
						}
					} else {
							return o2GoleAndSilver.getStatusOnline() - o1GoleAndSilver.getStatusOnline();
					}
				} else {
					return o2GoleAndSilver.getLevelVip() - o1GoleAndSilver.getLevelVip();
				}
			}
		});
	}
	
}
