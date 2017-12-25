package org.trams.hello.business.listener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.trams.hello.bean.jpa.CounselingSessionEntity;
import org.trams.hello.bean.jpa.UserEntity;
import org.trams.hello.business.event.CounselingSessionScheduleAddedEvent;
import org.trams.hello.business.service.CounselingSessionService;
import org.trams.hello.business.service.UserService;
import org.trams.hello.common.Mms;

/**
 * Created by bryanlee on 4/21/17.
 */
@Component
public class CounselingSessionScheduleAddedListener implements ApplicationListener<CounselingSessionScheduleAddedEvent> {

    private ConcurrentHashMap<Integer, Object> counselorSessionSchedule = new ConcurrentHashMap<>();
    private static final DateFormat format = new SimpleDateFormat("MM-dd(E) HH:mm", Locale.KOREA);

    @Autowired
    private UserService userService;
    @Autowired
    private CounselingSessionService counselingSessionService;

    @Override
    public void onApplicationEvent(CounselingSessionScheduleAddedEvent event) {
        try {
            Integer counselorId = event.getCounselorId();
            Integer memberId    = event.getMemberId();
            Integer sessionId   = event.getSessionId();

            UserEntity              counselor  = userService.findOne(counselorId);
            UserEntity              member  = userService.findOne(memberId);
            CounselingSessionEntity session = counselingSessionService.findOne(sessionId);
          
            Mms.send(member.getPhone(), 
            		"[Hello] ["+member.getFullname()+"]님 상담 예약 "+format.format(session.getStartTime())+"에 상담을 시작 합니다. "
            		+ "상담시간에 Hello 앱 실행 후 상담실로 입장 바라며 상담 2분 전부터 입장이 가능합니다.\n"
            		+ "\n"
            		+ "■ 필수사항\n"
            		+ "1. 배터리 체크 - 배터리 잔량 부족 시 충전기를 준비하시기 바랍니다. (50% 이상 충전 권장)\n"
            		+ "2. 데이터 체크 - LTE로 연결 시 데이터를 소모하게 됩니다. 데이터가 충분치 않을 시 상담이 종료될 수 있으니 가급적 Wi-Fi를 이용해 주세요.\n"
            		+ "3. 상담 중 외부에서 오는 전화는 받지 말아 주세요. 스피커폰 음성이 작아질 수 있고 상담의 흐름이 끊길 수 있습니다.");
           
            Mms.send(counselor.getPhone(), 
            		"[Hello] ["+member.getFullname()+"]님 상담 예약 "+format.format(session.getStartTime())+"에 상담을 시작 합니다.");
            
            Map<String, Object> data = new HashMap<>();
            data.put("sessionId", sessionId);
            counselorSessionSchedule.putIfAbsent(counselorId, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ConcurrentHashMap<Integer, Object> getCounselorSessionSchedule() {
        return counselorSessionSchedule;
    }

    public void setCounselorSessionSchedule(ConcurrentHashMap<Integer, Object> counselorSessionSchedule) {
        this.counselorSessionSchedule = counselorSessionSchedule;
    }
}
