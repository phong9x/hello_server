package org.trams.hello.business.event;

import org.springframework.context.ApplicationEvent;
import org.trams.hello.bean.jpa.CounselingSessionEntity;

/**
 * Created by bryanlee on 4/21/17.
 */
public class CounselingSessionBeforeStartEvent extends ApplicationEvent {

    private CounselingSessionEntity counselingSession;
    private Integer type;
    public CounselingSessionBeforeStartEvent(Object source, CounselingSessionEntity cse, Integer type) {
        super(source);
        this.counselingSession = cse;
        this.type = type;
    }
    
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public CounselingSessionEntity getCounselingSession() {
		return counselingSession;
	}

	public void setCounselingSession(CounselingSessionEntity counselingSession) {
		this.counselingSession = counselingSession;
	}
    
   
}
