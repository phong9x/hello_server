package org.trams.hello.rest.common;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trams.hello.bean.User;
import org.trams.hello.bean.api.ReservationDate;
import org.trams.hello.bean.api.ReservationTime;
import org.trams.hello.bean.api.ReservationTimeSelect;
import org.trams.hello.bean.api.ReservedAndCounselingType;
import org.trams.hello.business.service.BusinessSubService;
import org.trams.hello.business.service.CounselingSessionService;
import org.trams.hello.web.common.utils.DataUtils;
import org.trams.hello.web.common.utils.RequestUtils;

public class Utils {

	protected static Logger log = LoggerFactory.getLogger(Utils.class);

	@Resource
	private BusinessSubService businessSubService;

	@Resource
	private CounselingSessionService counselingSessionService;

	public static void main(String args[]) {
		Calendar c = Calendar.getInstance();
		System.out.println(c.getTime().getTime());
	}

	public static String convertListStartTimeToString(HashMap<Integer, Short> map) {
		String rs = "";
		Float startTimeConvert = 0f;
		for (Integer integer : map.keySet()) {
			startTimeConvert = ((float) integer / (float) 3600);
			startTimeConvert = (float) (Math.round(startTimeConvert * 10.0) / 10.0);
			rs = rs + Float.valueOf(startTimeConvert) + ";";
		}
		return rs;
	}

	public static String convertHourToString(Integer hour, Integer minutes) {
		return hour + "." + Math.round(minutes / 10);
	}

	// public static boolean checkOnlineTime(Calendar from, Calendar to,
	// String[] restTime) {
	// String start = convertHourToString(from.get(Calendar.MINUTE),
	// to.get(Calendar.SECOND));
	// String end = convertHourToString(from.get(Calendar.MINUTE),
	// to.get(Calendar.SECOND));
	// return false;
	// }

	public static Date getDate3MonthsAgo() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -3);
		// calendar.set(Calendar.DATE,
		// calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	public static float roundingFloat2DecimalPlaces(int total, int count) {
		float result = 0;
		result = (float) total / (float) count;
		result = (float) Math.round(result * 10) / 10;

		return result;
	}

	public static List<Date> getDaysByMonth(int year, int month, Date lastOfMonth, List<ReservationDate> listSetting) {
		SimpleDateFormat sd = new SimpleDateFormat("dd");
		List<Date> list = new ArrayList<>();
		int DayLastOfMonth = Integer.valueOf(sd.format(lastOfMonth));
		List<Integer> integers = new ArrayList<>();
		// Calendar cal = Calendar.getInstance();
		for (ReservationDate elem : listSetting) {
			integers.add(Integer.valueOf(sd.format(elem.getReservationDate())));
		}

		for (int i = 1; i <= DayLastOfMonth; i++) {
			if (!integers.contains(i)) {
				Calendar c = Calendar.getInstance();
				c.set(year, month - 1, i, 0, 0);
				list.add(c.getTime());
			}
		}

		return list;
	}

	public static Date getDate(int month, int year) {
		Calendar calendar = new GregorianCalendar(year, month, 0);
		return calendar.getTime();
	}

	public static List<ReservationTime> convertRealTimeIntervalsToList(List<Float> listReservationTime,
			Integer restTime) {
		List<ReservationTime> result = new ArrayList<>();
		if (listReservationTime.size() > 0) {
			Float begin = listReservationTime.get(0);
			Float end = listReservationTime.get(0);
			for (int i = 0; i < listReservationTime.size(); i++) {
				Float f = listReservationTime.get(i);
				Float add = addHour(end, 0.1f);
				if (f.equals(add)) {
					end = listReservationTime.get(i);
				} else {
					if (i != 0) {
						ReservationTime r = new ReservationTime();
						r.setFromDate(begin);
						r.setToDate(addHour(end, restTime / 100f));
						result.add(r);
						begin = listReservationTime.get(i);
						end = listReservationTime.get(i);
					}
				}
				if (i == listReservationTime.size() - 1) {
					ReservationTime r = new ReservationTime();
					r.setFromDate(begin);
					r.setToDate(addHour(end, restTime / 100f));
					result.add(r);
				}
			}
		}

		return result;
	}

	public static List<ReservationTime> convertReservationTimeIntervalsToList(List<Float> listReservationTime,
			List<Float> listRemoveTime, Integer restTime) {
		List<ReservationTime> result = new ArrayList<>();
		if (listReservationTime.size() > 0) {
			List<Float> listReservationTimeConvert = new ArrayList<>();
			Integer restTime10Minutes = restTime / 10;
			// add rest time in the end list
			Float temp = listReservationTime.get(0);
			listReservationTimeConvert.add(temp);
			for (int i = 0; i < listReservationTime.size(); i++) {
				Float f = listReservationTime.get(i);
				Float add = addHour(temp, 0.1f);
				if (f.equals(add)) {
					listReservationTimeConvert.add(f);
					temp = f;
					if (i == listReservationTime.size() - 1) {
						for (int j = 1; j <= restTime10Minutes; j++) {
							listReservationTimeConvert.add(addHour(f, Float.valueOf(0.1f * j)));
						}
					}
				} else if (i != 0) {
					if (i < listReservationTime.size() - 1) {
						Float nextHour = listReservationTime.get(i + 1);
						for (int j = 1; j <= restTime10Minutes; j++) {
							Float restTimeAddition = addHour(temp, Float.valueOf(0.1f * j));
							if (nextHour.equals(restTimeAddition)) {
								break;
							}
							listReservationTimeConvert.add(restTimeAddition);
						}
						listReservationTimeConvert.add(null);
						listReservationTimeConvert.add(f);
					} else {
						listReservationTimeConvert.add(f);
						for (int j = 1; j <= restTime10Minutes; j++) {
							Float restTimeAddition = addHour(f, Float.valueOf(0.1f * j));
							listReservationTimeConvert.add(restTimeAddition);
						}
					}
					temp = f;
				}
			}

			List<Float> resultList = ListUtils.subtract(listReservationTimeConvert, listRemoveTime);
			System.out.println(resultList);
			List<List<Float>> listCanReserve = new ArrayList<>();
			List<Float> listTemp = new ArrayList<>();
			for (int i = 0; i < resultList.size(); i++) {
				temp = resultList.get(i);
				if (temp == null ) {
					listCanReserve.add(listTemp);
					listTemp = new ArrayList<>();
				} else if(i == resultList.size() - 1){
					listTemp.add(temp);
					listCanReserve.add(listTemp);
				}else{
					listTemp.add(temp);
				}
			}
			System.out.println(listCanReserve);
			for (List<Float> list : listCanReserve) {
				if (!list.isEmpty()) {
					Float begin = list.get(0);
					Float end = list.get(0);
					Float f = 0f;
					Float add = 0f;
					for (int i = 0; i < list.size(); i++) {
						f = list.get(i);
						add = addHour(end, 0.1f);
						if (f.equals(add)) {
							end = f;
							if (i == list.size() - 1) {
								ReservationTime r = new ReservationTime();
								r.setFromDate(begin);
								r.setToDate(end);
								result.add(r);
							}
						} else if (i != 0) {
							ReservationTime r = new ReservationTime();
							r.setFromDate(begin);
							r.setToDate(end);
							result.add(r);
							begin = list.get(i);
							end = list.get(i);
						}
					}
				}

			}
		}
		System.out.println(result);
		return result;
	}

	public static boolean checkCounselorOnlineBusy(Integer counselingTime, Integer restTime, Calendar dateReservation,
			String onlineTime, TreeMap<Float, ReservedAndCounselingType> listStartTimeCounselingSSByDay,
			boolean isOnline) {
		List<Float> listRemoveTime = new ArrayList<>();
		if (restTime == null) {
			restTime = 0;
		}

		// get list time reserved time
		for (Float i : listStartTimeCounselingSSByDay.keySet()) {
			ReservedAndCounselingType r = listStartTimeCounselingSSByDay.get(i);
			Integer index = Math.round(i * 10);
			Integer end = Math.round(r.getToDate() * 10 + restTime / 10);
			while (index < end) {
				listRemoveTime.add((float) index / 10);
				if (index % 10 == 5) {
					index += 5;
				} else {
					index++;
				}
			}
		}

		// get list time reservation time setting
		List<Float> listReservationTime = new ArrayList<>();
		Float f1;
		String split[] = onlineTime.split(";");
		for (String str : split) {
			f1 = Float.valueOf(str);
			listReservationTime.add(f1);
		}

		// filter hour before now
		Float hourNow = Utils.convertHourToFloat(dateReservation.get(Calendar.HOUR_OF_DAY),
				dateReservation.get(Calendar.MINUTE));
		Iterator<Float> i = listReservationTime.iterator();
		while (i.hasNext()) {
			Float s = i.next(); // must be called before you can call i.remove()
			if (s < hourNow) {
				i.remove();
			}
		}

		// get time can counseling
		// List<Float> listTimeEnable = new ArrayList<>();
		List<ReservationTime> reservationTimes = Utils.convertReservationTimeIntervalsToList(listReservationTime,
				listRemoveTime, restTime);

		Float counselingAndRealtime = (counselingTime + restTime) / 100f;
		float begin = hourNow;
		float end = addHour(begin, counselingAndRealtime - 0.1f);
		for (ReservationTime j : reservationTimes) {
			if (begin >= j.getFromDate() && end <= j.getToDate()) {
				isOnline = true;
				break;
			}
		}

		return isOnline;
	}

	public static List<ReservationTimeSelect> getTimeCanReserve(Integer counselingTime, Integer restTime,
			Date dateReservation, String reservationTime,
			TreeMap<Float, ReservedAndCounselingType> listStartTimeCounselingSSByDay) {

		List<Float> listRemoveTime = new ArrayList<>();
		if (restTime == null) {
			restTime = 0;
		}

		// get list time reserved time
		for (Float i : listStartTimeCounselingSSByDay.keySet()) {
			ReservedAndCounselingType r = listStartTimeCounselingSSByDay.get(i);
			Float index = i;
			Float end = addHour(r.getToDate(), restTime / 100f);
			while (index < end) {
				listRemoveTime.add(index);
				index = addHour(index, 0.1f);
			}
		}

		// get list time reservation time setting
		List<Float> listReservationTime = new ArrayList<>();
		Float f1;
		String split[] = reservationTime.split(";");
		for (String str : split) {
			f1 = Float.valueOf(str);
			listReservationTime.add(f1);
		}

		// List resultList = ListUtils.subtract(listReservationTime,
		// listRemoveTime);
		// filter hour before now 2 hour
		Calendar now = Calendar.getInstance();
		Calendar selectedDate = Calendar.getInstance();
		selectedDate.setTime(dateReservation);

		int dayOfNow = now.get(Calendar.DAY_OF_MONTH);
		int dayOfSelectedDate = selectedDate.get(Calendar.DAY_OF_MONTH);

		log.debug("dayOfNow: " + dayOfNow);
		log.debug("dayOfSelectedDate: " + dayOfSelectedDate);

		if (dayOfNow == dayOfSelectedDate) {
			Integer hourNow = now.get(Calendar.HOUR_OF_DAY) + 2;
			if (hourNow > 24) {
				hourNow = 24;
			}
			Float listHourNow = Utils.convertHourToFloat(hourNow, now.get(Calendar.MINUTE));

			log.debug("now + 2hour : " + DateFormatUtils.format(now.getTime(), "yyyy-MM-dd HH:mm:ss"));
			log.debug("HOUR_OF_DAY: " + now.get(Calendar.HOUR_OF_DAY));
			log.debug("MINUTE: " + now.get(Calendar.MINUTE));
			log.debug("hourNow: " + hourNow);

			Iterator<Float> i = listReservationTime.iterator();
			while (i.hasNext()) {
				Float s = i.next(); // must be called before you can call
									// i.remove()
				if (s <= listHourNow) {
					i.remove();
				}
			}
		}

		// get time can counseling
		List<Float> listTimeEnable = new ArrayList<>();
		List<ReservationTime> reservationTimes = Utils.convertReservationTimeIntervalsToList(listReservationTime,
				listRemoveTime, restTime);

		Float counselingAndRealtime = (counselingTime + restTime) / 100f;
		for (ReservationTime i : reservationTimes) {
			float begin = i.getFromDate();
			float end = addHour(begin, counselingAndRealtime - 0.1f);

			while (end <= i.getToDate()) {
				listTimeEnable.add(begin);
				begin = addHour(begin, counselingAndRealtime);
				end = addHour(end, counselingAndRealtime);
			}
		}
		TreeMap<Integer, List<Integer>> map = new TreeMap<>();
		for (float i : listTimeEnable) {
			Integer hour = (int) i;
			Integer minutes = Math.round(i * 100) - Math.round(hour * 100);
			if (map.containsKey(hour)) {
				List<Integer> lMinutes = map.get(hour);
				if (!lMinutes.contains(minutes)) {
					lMinutes.add(minutes);
				}
			} else {
				List<Integer> lMinutes = new ArrayList<>();
				lMinutes.add(minutes);
				map.put(hour, lMinutes);
			}
		}
		List<ReservationTimeSelect> listReservation = new ArrayList<>();
		for (Entry<Integer, List<Integer>> m : map.entrySet()) {
			ReservationTimeSelect re = new ReservationTimeSelect();
			re.setHour(m.getKey());
			re.setMinutes(m.getValue());
			listReservation.add(re);
		}
		return listReservation;
	}

	public static Float addHour(Float hour, Float add) {
		Integer hourInt = Math.round(hour * 10);
		Integer addInt = Math.round(add * 10);

		Integer minutesInt = hourInt % 10;
		Integer tempHour = hourInt - hourInt % 10;
		if ((minutesInt + addInt) >= 6) {
			Integer addHour = (int) (minutesInt + addInt) / 6;
			tempHour += addHour * 10;
			minutesInt = (minutesInt + addInt - 6 * addHour);
			hour = (tempHour + minutesInt) / 10f;
		} else {
			hour = (tempHour + minutesInt + addInt) / 10f;
		}

		return hour;
	}

	public static Float convertHourToFloat(Integer hour, Integer minutes) {
		Float result;
		if (minutes % 10 != 0) {
			minutes = minutes - minutes % 10;
		}
		result = (hour * 100f + minutes) / 100f;
		return result;
	}

	public static Map<String, String> checkMemberBusiness(User user) {
		try {
			log.debug("============================================================================");
			log.debug("checkMemberBusiness");

			String url = "http://api.aimmed.co.kr/hello/check_user.asp" + "?name="
					+ URLEncoder.encode(String.valueOf(user.getFullname()), "UTF-8") + "&mobile="
					+ URLEncoder.encode(String.valueOf(user.getPhone()), "UTF-8") + "&birth="
					+ DataUtils.parseStringFromDate(user.getDayOfBirth(), "yyMMdd") + "&secret=AimmedHELLO";
			log.debug("url: " + url);
			String respond = RequestUtils.get(url);
			log.debug("respond: " + respond);
			JSONObject json = (JSONObject) new JSONParser().parse(respond);
			Integer statusCode = 204;

			log.debug("============================================================================");
			statusCode = DataUtils.parseInt(String.valueOf(json.get("STATUS_CODE")));

			if (statusCode == 200) {
				String aimmedUserId = json.get("id").toString();
				String businessSubCode = json.get("goods_code").toString();
				Map<String, String> result = new HashMap<String, String>();
				result.put("aimmedUserId", aimmedUserId);
				result.put("businessSubCode", businessSubCode);

				return result;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<ReservationTime> convertTimeIntervalsList(List<Float> reservationTimeFl) {
		List<ReservationTime> reservationTimes = new ArrayList<>();
		if (reservationTimeFl.size() > 0) {
			float iConvert_0 = reservationTimeFl.get(0);
			ReservationTime r = new ReservationTime();
			r.setFromDate(iConvert_0);

			double mind_0 = (double) Math.round(((iConvert_0) - ((int) (iConvert_0))) * 10) / 10;
			if (mind_0 == 0.5) {
				r.setToDate((int) iConvert_0 + 1f);
			} else {
				Float end = Math.round(iConvert_0 * 10 + 1) / 10f;
				r.setToDate(end);
			}
			reservationTimes.add(r);

			float iConvert_i;
			double mind_i;
			for (int i = 1; i < reservationTimeFl.size(); i++) {
				iConvert_i = reservationTimeFl.get(i);
				mind_i = (double) Math.round(((iConvert_i) - ((int) Math.floor(iConvert_i))) * 10) / 10;
				if (iConvert_i == r.getToDate()) {
					if (mind_i == 0.5) {
						r.setToDate((float) Math.round((((int) Math.floor(iConvert_i) + 1)) * 10) / 10);
					} else {
						r.setToDate((float) Math.round(((iConvert_i + 0.1f)) * 10) / 10);
					}
				} else {
					ReservationTime r2 = new ReservationTime();
					r2.setFromDate(iConvert_i);
					if (mind_i == 0.5) {
						r2.setToDate((float) Math.round((((int) Math.floor(iConvert_i) + 1)) * 10) / 10);
					} else {
						r2.setToDate((float) Math.round(((iConvert_i + 0.1f)) * 10) / 10);
					}

					reservationTimes.add(r2);
					iConvert_0 = iConvert_i;
					r = r2;
				}
			}
		}

		return reservationTimes;
	}
}
