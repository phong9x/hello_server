<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselingCenter/layout/include.jsp" %>
<%@ taglib uri="http://www.springframework.org/tags" 	prefix="s" %>

<div class="page-container">
      <div class="page-content-wrapper">
        <div class="page-content">
          <div class="container">
            <div class="main-body-content member-page member-static">
              <div class="page-bar">
                <ul class="page-breadcrumb">
                  <li><a class="home"> <i class="fa fa-home"></i>홈</a></li>
                  <li><a href="/counselingCenter/auth/statistics/period">통계</a></li>
                  <li><a href="/counselingCenter/auth/statistics/period">상담 통계</a></li>
                </ul>
              </div>
              <!-- .page-bar-->
			<div class="member-main-content">
		            <div class="tabbable-custom">
			            <ul class="nav nav-tabs">
		                    <li ><a href="/counselingCenter/auth/statistics/period">기간별</a><jsp:text/></li>
		                    <li ><a href="/counselingCenter/auth/statistics/hour" >요일별</a><jsp:text/></li>
		                    <li class="active"><a  >시간별</a><jsp:text/></li>
	                  	</ul>
		              <div class="">
		                <div class="tab-content">
		                  <div id="tab-12" class="tab-12 tab-pane active">
		                    <div class="md-tab-content tabv2">
		                      <form class="search-from" method="get" >
		                        <div class="member-main-top border-full">
								<div class="item-top">
				                    <div class="left"><span>결제 날짜 :</span></div>
				                    <div class="right">
				                      <div class="item datepicker-item"><span>
				                          <input name="startDate" type="text" data-date-format="yyyy/mm/dd"  value="${s.startDate }" class="form-control date-picker-custom"/></span><span class="text">달력</span>
				                      </div>
				                      <span>※ 최근 30일까지만 조회 가능합니다.</span>
				                    </div>
				                  </div>
		                          <div class="item-top mrb-30">
		                          <div class="left"><span>OS :</span></div>
		                          <div class="right">
		                            <div class="item">
		                              <div data-error-container="#form_2_services_error" class="checkbox-list">
		                                <label>
		                                	<c:choose>
		                                		<c:when test="${s.ios}">
		                                			<input type="checkbox" value="true" name="ios" checked="checked"/> iOS    
		                                		</c:when>
		                                		<c:otherwise>
		                                			<input type="checkbox" value="true" name="ios"/> iOS   
		                                		</c:otherwise>
		                                	</c:choose>
		                                </label>
		                                <label>
		                                  <c:choose>
		                                		<c:when test="${s.android}">
		                                			<input type="checkbox" value="true" name="android" checked="checked"/> Android
		                                		</c:when>
		                                		<c:otherwise>
		                                			<input type="checkbox" value="true" name="android"/> Android
		                                		</c:otherwise>
		                                  </c:choose>
		                                </label>
		                              </div>
		                            </div>
		                          </div>
		                        </div>
		                        </div>
		                        <div class="link-submit">
		                          <button type="submit" class="btn green">검색</button>
		                        </div>
		                      </form>
		                      <div class="member-title-table">
		                        <div class="md-ct">
		                            <span class="title">상담 통계 </span>
		                        </div>
		                      </div>
		                      
		                      <div class="table-member-mng table-responsive">
		                        <FORM method="post">
		                          <table class="table table-striped table-bordered table-hover text-center">
		                            <thead>
		                              <tr>
		                                <th>날짜</th>
		                                <th>예약수</th>
		                                <th>상담수</th>
		                              </tr>
		                              <tr>
		                                <th>합계</th>
		                                <th><fmt:formatNumber value="${total[0]}" maxFractionDigits="3" /></th>
		                                <th><fmt:formatNumber value="${total[1]}" maxFractionDigits="3"/></th>
		                              </tr>
		                            </thead>
		                            <tbody>
		                              <c:forEach items="${list }" var="i" varStatus="loop">
		                                <tr>
		                                	<td>${i.title}</td>
		                                	<td><fmt:formatNumber value="${i.totalReservation}" maxFractionDigits="3"/></td>
		                                	<td><fmt:formatNumber value="${i.totalCounseling}" maxFractionDigits="3"/></td>
		                                </tr>
		                              </c:forEach>
		                            </tbody>
		                          </table>
		                        </FORM>
		                      </div>
		                      	<util:downloadExcel url="" />
		                    </div>
		                  </div>
		                </div>
		              </div>
		            </div>
		          </div>
              <!-- .block-1-->
            </div>
            <!-- .member-main-->
            
          </div>
        </div>
        
	</div>
	
	
	<SCRIPT type="text/javascript">
	
	(function exportExcel(){
  		$('#excelExport').on('click', '', function() {
       	   window.open("/admin/user/counselor/download_excel?${param_url}","_blank");
       	  });
  	})(jQuery);
	
	
	
	$(document).ready(function() {
		(function($) {
		    if (!$(".date-picker-custom").length) return;
		   
		    $('.date-picker-custom').datepicker({
		      rtl: Metronic.isRTL(),
		      orientation: "left",
		      autoclose: true,
		      language: 'kr',
		      startDate: '-30d',
		      endDate: '+0d'
		    });
		  })(jQuery);
	});
    
	</SCRIPT>
</div>
