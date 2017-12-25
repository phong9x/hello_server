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
                  <li><a href="/counselingCenter/auth/statistics/profit">상담사 실적</a></li>
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
		                      <div class="member-title-table">
		                        <div class="md-ct">
			                	 <span class="title" style="width :auto">정산 요약</span>
					             <select id="year" name="year" style="width: 150px !important;" class="form-control select2me pull-right" onchange="this.form.submit();">
			                    	<c:forEach begin="${s.yearNow -2 }" end="${s.yearNow }" var="i">
			                    		<c:choose>
			                    			<c:when test="${i == s.year }">
			                    				<option value="${i }" selected="selected">${i}년  </option>
			                    			</c:when>
			                    			<c:otherwise>
			                    				<option value="${i }" >${i}년  </option>
			                    			</c:otherwise>
			                    		</c:choose>
			                    	</c:forEach>
				                  </select>
				                  <select id="month" name="month" style="width: 150px !important;" class="form-control select2me pull-right" onchange="this.form.submit();">
			                    	<c:forEach begin="1" end="12" var="i">
			                    		<c:choose>
			                    			<c:when test="${i == s.month }">
			                    				<option value="${i}" selected="selected">${i}월 </option>
			                    			</c:when>
			                    			<c:otherwise>
			                    				<option value="${i}" >${i}월 </option>
			                    			</c:otherwise>
			                    		</c:choose>
			                    		
			                    	</c:forEach>
				                  </select>
		                        </div>
		                      </div>
		                      </form>
		                      
		                      <div class="table-member-mng table-responsive">
		                        <FORM method="post">
		                          <table class="table table-striped table-bordered table-hover text-center">
		                            <thead>
		                              <tr>
		                                <th></th>
		                                <th>헬로코인 사용</th>
		                                <th>상담권 사용</th>
		                                <th>합계</th>
		                                <th>수익 분배율</th>
		                                <th>지급 금액(부가세 포함)</th>
		                                <th>상태</th>
		                              </tr>
		                            </thead>
		                            <tbody>
			                              <tr>
			                              	<td>${s.month }월</td>
		                                	<td><fmt:formatNumber value="${p.coin}" maxFractionDigits="3"></fmt:formatNumber> </td>
		                                	<td><fmt:formatNumber value="${p.voucher}" maxFractionDigits="3"></fmt:formatNumber></td>
		                                	<td>
		                                	<c:if test="${ p.coin != null}">
		                                		<fmt:formatNumber value="${p.coin + p.voucher}" maxFractionDigits="3"></fmt:formatNumber>
		                                	</c:if>
		                                	</td>
		                                	<td>
		                                	<c:if test="${p.percent != null}">${p.percent }%</c:if>
		                                	</td>
		                                	<td><fmt:formatNumber value="${p.adminRefund}" maxFractionDigits="3"></fmt:formatNumber></td>
		                                	<td>
		                                		<c:if test="${ p.status == 0 }">
		                                			미지급
		                                		</c:if>
		                                		<c:if test="${ p.status == 1 }">
		                                			지급 완료
		                                		</c:if>
		                                	</td>
			                              </tr>
		                              
		                            </tbody>
		                          </table>
		                        </FORM>
		                      </div>
		                    </div>
		                  </div>
		                </div>
		              </div>
		            </div>
		          </div>
		          
		          <div class="member-main-content">
		            <div class="tabbable-custom">
		              <div class="">
		                <div class="tab-content">
		                  <div id="tab-12" class="tab-12 tab-pane active">
		                    <div class="md-tab-content tabv2">
		                      <form class="search-from" method="get" >
		                      <div class="member-title-table">
		                        <div class="md-ct">
			                	 <span class="title" style="width :auto">정산 요약</span>
					             <select id="orderBy" name="orderBy" style="width: 250px !important;" class="form-control select2me pull-right" onchange="this.form.submit();">
			                    	<option value="startTime" selected="selected">최근 상담 일시 순 </option>
			                    	<option value="counselorName" >상담사명 순  </option>
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
		                                <th>회원명</th>
		                                <th>상담일시</th>
		                                <th>결제 구분</th>
		                                <th>금액</th>
		                              </tr>
		                            </thead>
		                            <tbody>
		                              <c:forEach items="${list}" var="i" varStatus="loop">
			                              <tr>
			                              	<td>${i.id }</td>
		                                	<td>${i.counselorName }</td>
		                                	<td>${i.userName }</td>
		                                	<td>
		                                		<p><fmt:formatDate value="${i.startTime }" pattern="yyyy-MM-dd"/></p>
		                                		<p><fmt:formatDate value="${i.startTime }" pattern="HH:mm:ss"/></p>
		                                	 </td>
		                                	 <td>${i.typeCoin}</td>
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
			$("#orderBy").val('${s.orderBy}');
			
	});
	

	</SCRIPT>
</div>
