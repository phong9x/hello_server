<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselingCenter/layout/include.jsp" %>
<%@ taglib uri="http://www.springframework.org/tags" 	prefix="s" %>

<div class="page-container">

      <div class="page-content-wrapper countselor">
        <div class="page-content">
          <div class="container">
            <div class="main-body-content member-page business">
              <div class="page-bar">
                <ul class="page-breadcrumb">
                  <li><a href="" class="home"> <i class="fa fa-home"></i>홈</a></li>
                  <li><a href="">상담 내역</a></li>
                </ul>
              </div>
              <!-- .page-bar-->

              <form class="form_sample_3">
                <div class="member-main-top border-full">
                  <div class="item-count">
					<div class="item-top">
                    <div class="left"><span>상담 날짜</span></div>
                    <div class="right">
                      <div class="item icon-next datepicker-item"><span>
                          <input name="start_search" type="text" data-date-format="yyyy-mm-dd" value="${startSearch}" class="form-control date-picker"/></span><span class="text">달력</span></div>
                      <div class="item datepicker-item"><span>
                          <input name="end_search" type="text" data-date-format="yyyy-mm-dd" value="${endSearch}" class="form-control date-picker"/></span><span class="text">달력</span></div>
                    </div>
                  </div>
                    <div class="item-left"><span class="title">결제 구분</span>
                      <div class="left-ct">
                        <div data-error-container="#form_2_services_error" id="type_payment" class="checkbox-list">
                       		<label>
	                            <input type="checkbox" value="1"  ${type_payment_coin eq 1 ? 'checked' : ''} name="type_payment_coin"/> 헬로코인 사용   
	                        </label>
                      			<label>
	                            <input type="checkbox" value="1" ${type_payment_voucher eq 1 ? 'checked' : ''} name="type_payment_voucher"/> 상담권 사용
	                         </label>		
                        </div>
                      </div>
                    </div>
                    <div class="item-right"><span class="title">키워드 </span>
                      <div class="right-ct">
                        <div class="select-option">
                          <select name="type_search" id="type_search" title="성명" class="form-control selectpickermember">
                          	<c:if test="${typeSearch == 'counselorName'}"><option value="counselorName" selected="selected">상담사명</option></c:if>
                          	<c:if test="${typeSearch != 'counselorName'}"><option value="counselorName">상담사명</option></c:if>
                          	<c:if test="${typeSearch == 'memberName'}"><option value="memberName" selected="selected">회원명</option></c:if>
                          	<c:if test="${typeSearch != 'memberName'}"><option value="memberName">회원명</option></c:if>
                          </select>
                        </div>
                        <input type="text" name="keyword" value="${keyword}" id="keyword" data-required="1" class="form-control"/>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="link-submit">
                  <button type="submit" class="btn green">검색</button>
                </div>
              </form>
              <div class="member-title-table">
                <div class="md-ct"><span class="title">상담 내역 :</span><span>${totalElement}건</span>
                  <select name="type_order" id="type_order" data-start_search="${startSearch}" data-end_search= "${endSearch }" data-type_payment_coin="${type_payment_coin }"
					data-type_payment_voucher="${type_payment_voucher}" title="상담 내역" class="form-control selectpickermember pull-right">
                  	<c:if test="${typeOrder == 'ord_endTime'}"><option value="ord_endTime" selected="selected">최근 상담 순</option></c:if>
                  	<c:if test="${typeOrder != 'ord_endTime'}"><option value="ord_endTime">최근 상담 순</option></c:if>
                  	<c:if test="${typeOrder == 'ord_point'}"><option value="ord_point" selected="selected">만족도점수 높은 순</option></c:if>
                  	<c:if test="${typeOrder != 'ord_point'}"><option value="ord_point">만족도점수 높은 순</option></c:if>
                  	<c:if test="${typeOrder == 'ord_counselorName'}"><option value="ord_counselorName" selected="selected">상담사명 순</option></c:if>
                  	<c:if test="${typeOrder != 'ord_counselorName'}"><option value="ord_counselorName">상담사명 순</option></c:if>
                  	<c:if test="${typeOrder == 'ord_memberName'}"><option value="ord_memberName" selected="selected">회원명 순</option></c:if>
                  	<c:if test="${typeOrder != 'ord_memberName'}"><option value="ord_memberName">회원명 순</option></c:if>
                  </select>
                </div>
              </div>
              <div class="table-member-mng table-responsive">
                <table class="table table-striped table-bordered table-hover text-center">
                  <thead>
                    <tr>
                      <th>번호</th>
                      <th>썸네일</th>
					  <th>상담사명</th>
                      <th>회원명</th>
					  <th>상담시작</th>
					  <th>상담종료</th>
					  <th>상담 시간</th>
					  <th>결제 구분</th>
					  <th>만족도점수</th>
                    </tr>
                  </thead>
                  <tbody>
                  	<c:forEach items="${list}" var="counselor">
                  		<tr>
	                      <td>${counselor.id}</td>
	                      <td><img src="${counselor.thumb}" alt="..." style="width: 100px;height: 100px;" class="profile_img"/></td>
	                      <td>${counselor.nameCounselor}</td>
	                      <td>${counselor.nameMember}</td>
	                      <td>
	                      		<c:if test="${counselor.startTimeCounseling == null}">
		                      		-
		                      	</c:if>
		                      	<c:if test="${counselor.startTimeCounseling != null}">
		                      		<p><fmt:formatDate value="${counselor.startTimeCounseling}" pattern="yyyy-MM-dd"/></p>
		                    		<p><fmt:formatDate value="${counselor.startTimeCounseling}" pattern="HH:mm:ss"/></p>
		                      	</c:if>
						  </td>
						  <td>
						  		<c:if test="${counselor.endTimeCounseling == null}">
		                      		-
		                      	</c:if>
		                      	<c:if test="${counselor.endTimeCounseling != null}">
		                      		<p><fmt:formatDate value="${counselor.endTimeCounseling}" pattern="yyyy-MM-dd"/></p>
		                    		<p><fmt:formatDate value="${counselor.endTimeCounseling}" pattern="HH:mm:ss"/></p>
		                      	</c:if>
						  </td>
	                      <td>
							${counselor.timeCounseling}
						  </td>
						  <td>${counselor.typePayment}</td>
	                      <td>
							<p><input type="number" data-readonly="" value="${counselor.points}" class="rating" style="display:none;"/></p>
							<p>(${counselor.points})</p>
						  </td>
	                    </tr>
                  	</c:forEach>
                  </tbody>
                </table>
              </div>

              <jsp:include page="/WEB-INF/views/counselingCenter/layout/pagination_custom.jsp">
                   <jsp:param name="first"             value="${sessionEntities.first}"/>
                   <jsp:param name="last"              value="${sessionEntities.last}"/>
                   <jsp:param name="totalPages"        value="${sessionEntities.totalPages}"/>
                   <jsp:param name="totalElements"     value="${sessionEntities.totalElements}"/>
                   <jsp:param name="numberOfElements"  value="${sessionEntities.numberOfElements}"/>
                   <jsp:param name="size"              value="${sessionEntities.size}"/>
                   <jsp:param name="number"            value="${sessionEntities.number}"/>
               </jsp:include>
              <!-- .rw-pagination-->
              <!-- .member-main-->
            </div>
          </div>
          <!-- .page-content-->
        </div>
      </div>
      <!-- .member-->
    </div>

<script type="text/javascript">
		$(document).ready(function() {
			$('#type_order').on('change', function() {
				var startSearch = $(this).attr('data-start_search');
				var endSearch = $(this).attr('data-end_search');
				var type_payment_coin = $(this).attr('data-type_payment_coin');
				var type_payment_voucher = $(this).attr('data-type_payment_voucher');
				var type_search = $('#type_search').val();
				var keyword = $('#keyword').val();
				var orderType = $('#type_order').val();

				window.location = "/counselingCenter/auth/counselingSummary?type_order="+ orderType + "&start_search="+ startSearch + "&end_search="+ endSearch +"&type_payment_coin="+ type_payment_coin +"&type_payment_voucher="+ type_payment_voucher+"&type_search="+ type_search+"&keyword="+ keyword;

			});
		});
	</script>
