package org.trams.hello.rest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trams.hello.bean.jpa.CounselingSessionEntity;
import org.trams.hello.business.service.CounselingSessionService;
import org.trams.hello.business.service.RtcService;
import org.trams.hello.rest.common.AbstractRestController;

/**
 * Created by bryanlee on 2/3/17.
 */
@RequestMapping("/v1/auth/rtc")
@Controller
public class RtcRestController extends AbstractRestController {

    @Autowired
    private RtcService rtcService;
    @Autowired
    private CounselingSessionService counselingSessionService;

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> join(
            @RequestParam Map<String, Object> params
    ) {
        try {
            params.putIfAbsent("roleId", "speaker");
            Map<String, Object> participant = rtcService.createParticipant(params);
            String roomId = (String) params.get("roomId");
            CounselingSessionEntity counselingSession =  counselingSessionService.findOneByRoomId(roomId);
            if(counselingSession.getEntryUserTime() == null){
            	counselingSession.setEntryUserTime(new Date());
            	counselingSessionService.saveOrUpdate(counselingSession);
            }
            participant.put("current", new Date().getTime());
            participant.put("disconnectCount", counselingSession.getDisconnectCount());
            return SUCCESS(participant);
        } catch (RuntimeException e) {
            return FAIL(e.getMessage());
        }
    }
    
    @RequestMapping(value = "/start-time", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> rejoin(
            @RequestParam Map<String, Object> params
    ) {
        try {
        	Integer counselingSessionId = Integer.parseInt(params.get("counselingSessionId").toString());
            Map<String, Object> ret = new HashMap<String, Object>();
            CounselingSessionEntity cs = counselingSessionService.findOne(counselingSessionId);
            Long startTime = null;
            
            Date counselorEntry = cs.getEntryCounselorTime();
            Date userEntry = cs.getEntryUserTime();
            if(counselorEntry != null && userEntry != null){
            	startTime = (counselorEntry.after(userEntry) ? counselorEntry.getTime() : userEntry.getTime());
            }
            
            ret.put("startTime", startTime);
            return SUCCESS(ret);
        } catch (RuntimeException e) {
            return FAIL(e.getMessage());
        }
    }
}
