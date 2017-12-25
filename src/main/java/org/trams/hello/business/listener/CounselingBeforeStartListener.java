package org.trams.hello.business.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.trams.hello.business.event.CounselingSessionBeforeStartEvent;
import org.trams.hello.common.Mms;
import org.trams.hello.web.common.ApplicationDefine;

/**
 * Created by bryanlee on 4/21/17.
 */
@Component
public class CounselingBeforeStartListener implements ApplicationListener<CounselingSessionBeforeStartEvent> {

    @Override
    public void onApplicationEvent(CounselingSessionBeforeStartEvent event) {
        try {
        	String counselorPhone =event.getCounselingSession().getCounselor().getUser().getPhone();
        	String memberName = event.getCounselingSession().getUser().getFullname();
        	String memberPhone = event.getCounselingSession().getUser().getPhone();
        	if(event.getType() == ApplicationDefine.SMS_Type.Counseling_Before_5Minutes_FromReservation.getCode()){
        		// send Mms to counselor
                Mms.send(counselorPhone, String.format("[Hello] 5분 후에 [%s]님과의 심리상담이 예정되어 있습니다.",memberName ));
        	}else if(event.getType() == ApplicationDefine.SMS_Type.Counseling_Before_1Hour_FromReservation.getCode()){
        		// send sms to member
        		Mms.send(memberPhone, "[Hello] ["+memberName+"]님 1시간 후 심리상담이 시작됩니다. "
        				+ "상담시간에 Hello 앱 실행 후 상담실로 입장 바라며 상담 2분 전부터 입장이 가능합니다.\n"
                		+ "\n"
                		+ "■ 필수사항\n"
                		+ "1. 배터리 체크 - 배터리 잔량 부족 시 충전기를 준비하시기 바랍니다. (50% 이상 충전 권장)\n"
                		+ "2. 데이터 체크 - LTE로 연결 시 데이터를 소모하게 됩니다. 데이터가 충분치 않을 시 상담이 종료될 수 있으니 가급적 Wi-Fi를 이용해 주세요.\n"
                		+ "3. 상담 중 외부에서 오는 전화는 받지 말아 주세요. 스피커폰 음성이 작아질 수 있고 상담의 흐름이 끊길 수 있습니다.");
        	}else if(event.getType() == ApplicationDefine.SMS_Type.Counseling_Before_2Minutes_FromReservation.getCode()){
        		// send Mms to member
                Mms.send(memberPhone , "[Hello] 2분 후에 심리상담이 진행됩니다. 지금 입장 가능합니다. "
                		+ "Hello 앱 실행 후 상담실로 입장 바라며 상담 2분 전부터 입장이 가능합니다. "
                		+ "입장 전 안내 사항을 다시 한번 확인 해 주세요.\n"
                		+ "\n"
                		+ "■ 필수사항\n"
                		+ "1. 배터리 체크 - 배터리 잔량 부족 시 충전기를 준비하시기 바랍니다. (50% 이상 충전 권장)\n"
                		+ "2. 데이터 체크 - LTE로 연결 시 데이터를 소모하게 됩니다. 데이터가 충분치 않을 시 상담이 종료될 수 있으니 가급적 Wi-Fi를 이용해 주세요.\n"
                		+ "3. 상담 중 외부에서 오는 전화는 받지 말아 주세요. 스피커폰 음성이 작아질 수 있고 상담의 흐름이 끊길 수 있습니다.");
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
