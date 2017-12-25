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
                  <li><a href="/counselingCenter/auth/statistics/counselor">상담사 실적</a></li>
                </ul>
              </div>
              <!-- .page-bar-->
			<div class="member-main-content">
		            <div class="tabbable-custom">
		              <div class="">
		                <div class="tab-content">
		                  <div id="tab-12" class="tab-12 tab-pane active">
		                    <div class="md-tab-content tabv2">
		                      <form class="search-from" method="get" >
		                        <div class="member-main-top border-full">
								<div class="item-top">
				                    <div class="left"><span>일시 :</span></div>
				                    <div class="right">
				                      <div class="item icon-next datepicker-item"><span>
				                          <input name="startDate" type="text" data-date-format="yyyy/mm/dd"  value="${s.startDate }" class="form-control date-picker"/></span><span class="text">달력</span></div>
				                      <div class="item datepicker-item"><span>
				                          <input name="endDate" type="text" data-date-format="yyyy/mm/dd"  value="${s.endDate}" class="form-control date-picker"/></span><span class="text">달력</span></div>
				                    </div>
				                  </div>
		                        </div>
		                        <div class="link-submit">
		                          <button type="submit" class="btn green">검색</button>
		                        </div>
		                      
		                      <div class="member-title-table">
		                        <div class="md-ct">
					            <c:if test="${totalCount == 0 }">
			                		<span class="title" style="width :auto">검색 결과가 없습니다.</span>
			                	</c:if>
			                	<c:if test="${totalCount > 0 }">
			                		<span class="title" style="width :auto">상담사 실적   : ${totalCount }건</span>
			                	</c:if>
					             <select id="orderBy" name="orderBy" style="width: 250px !important;" class="form-control select2me pull-right" onchange="this.form.submit();">
			                    	<option value="fee" selected="selected">상담 금액 순 </option>
			                    	<option value="counselorName" >상담사명 순 </option>
				                    <option value="counseling" >상담수 순</option>
				                  </select>
		                        </div>
		                      </div>
		                      </form>
		                      
		                      <div class="table-member-mng table-responsive">
		                        <FORM method="post">
		                          <table class="table table-striped table-bordered table-hover text-center">
		                            <thead>
		                              <tr>
		                                <th>번호</th>
		                                <th>상담사명</th>
		                                <th>예약수</th>
		                                <th>상담수</th>
		                                <th>헬로코인 사용</th>
		                                <th>상담권 사용</th>
		                                <th>상담 금액</th>
		                              </tr>
		                            </thead>
		                            <tbody>
		                              <c:forEach items="${list}" var="i" varStatus="loop">
			                              <tr>
			                              	<td>${totalCount - loop.index - (currentIndex-1) * size}</td>
		                                	<td>${i.counselorName }</td>
		                                	<td><fmt:formatNumber value="${i.reservation}" maxFractionDigits="3"></fmt:formatNumber> </td>
		                                	<td><fmt:formatNumber value="${i.counseling}" maxFractionDigits="3"></fmt:formatNumber></td>
		                                	<td><fmt:formatNumber value="${i.totalCoin}" maxFractionDigits="3"></fmt:formatNumber></td>
		                                	<td><fmt:formatNumber value="${i.totalVoucher}" maxFractionDigits="3"></fmt:formatNumber></td>
		                                	<td><fmt:formatNumber value="${i.fee}" maxFractionDigits="3"></fmt:formatNumber></td>
			                              </tr>
		                              </c:forEach>
		                              
		                            </tbody>
		                          </table>
		                        </FORM>
		                      </div>
		                      <util:downloadExcel url="" />
		                      <util:pagnation currentIndex="${currentIndex}" url="${pagination_navigator}" totalPages="${page.totalPages}" id="" param_url="${param_url}" />
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
	
	$( document ).ready(function(){
			$("#orderBy").val('${orderBy}');
			
	});
	

	</SCRIPT>
</div>
