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
                  <li><a href="">상담사 정보</a></li>
                </ul>
              </div>
              <!-- .page-bar-->

              <form class="form_sample_3">
                <div class="member-main-top border-full">
                  <div class="item-count">
                    <div class="item-left"><span class="title">상태</span>
                      <div class="left-ct">
                        <div data-error-container="#form_2_services_error" id="status_counselor" class="checkbox-list">
                       		<label>
	                            <input type="checkbox" value="1" ${status_counselor_Normal eq 1 ? 'checked' : ''} name="status_counselor_Normal"/> 정상   
	                        </label>
                       		<label>
	                            <input type="checkbox" value="1" ${status_counselor_banned eq 1 ? 'checked' : ''} name="status_counselor_banned"/> 제재
	                        </label>
                       		<label>
	                            <input type="checkbox" value="1" ${status_counselor_display eq 1 ? 'checked' : ''} name="status_counselor_display"/> 제재
	                        </label>
                        </div>
                      </div>
                    </div>
                    <div class="item-right"><span class="title">키워드 :</span>
                      <div class="right-ct">
                        <div class="select-option">
                          <select name="type_search" title="성명" class="form-control selectpickermember">
                          	<c:if test="${typeSearch == 'counselorName'}">
                          		<option value="counselorName" selected="selected">상담사명</option>
                          	</c:if>
                          	<c:if test="${typeSearch != 'counselorName'}">
                          		<option value="counselorName">상담사명</option>
                          	</c:if>
                          	<c:if test="${typeSearch == 'counselorID'}">
                          		<option value="counselorID" selected="selected">상담사 아이디</option>
                          	</c:if>
                            <c:if test="${typeSearch != 'counselorID'}">
                          		<option value="counselorID">상담사 아이디</option>
                          	</c:if>
                            
                          </select>
                        </div>
                        <input type="text" name="keyword" id="keyword" value="${keyword}" data-required="1" class="form-control"/>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="link-submit">
                  <button type="submit" class="btn green">검색</button>
                </div>
              </form>
              
              <div class="member-title-table">
                <div class="md-ct"><span class="title">소속 상담사 리스트 :</span><span>${totalElement}건</span>
                  <select name="type_order" data-status_counselor_Normal="${status_counselor_Normal}" data-status_counselor_banned = "${status_counselor_banned }" data-status_counselor_display="${status_counselor_display }" id="type_order" title="최근 등록일시 순" class="form-control selectpickermember pull-right">
                  	<c:if test="${type_order == 'ord_counselorName' }">
                  		<option value="ord_counselorName" selected="selected">이름 순</option>
                  	</c:if>
                  	<c:if test="${type_order != 'ord_counselorName' }">
                  		<option value="ord_counselorName">이름 순</option>
                  	</c:if>
                    <c:if test="${type_order == 'ord_createDate' }">
                  		<option value="ord_createDate" selected="selected">등록 일시 순</option>
                  	</c:if>
                  	<c:if test="${type_order != 'ord_createDate' }">
                  		<option value="ord_createDate">등록 일시 순</option>
                  	</c:if>
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
                      <th>생년월일</th>
					  <th>휴대폰 번호</th>
					  <th>등록일시</th>
					  <th>최근 로그인일시</th>
					  <th>만족도평가</th>
					  <th>상태</th>
                    </tr>
                  </thead>
                  <tbody>
                  	<c:forEach items="${list}" var="counselor">
                  		<tr>
		                      <td>${counselor.id}</td>
		                      <td><img src="${counselor.thumb }" style="width: 100px;height: 100px;" alt="..." class="profile_img"/></td>
		                      <td>${counselor.name }</td>
		                      <td><fmt:formatDate value="${counselor.dayOfBirth}" pattern="yyyy-MM-dd"/></td>
		                      <td>${counselor.phone }</td>
		                      <td>
								<p><fmt:formatDate value="${counselor.createDate}" pattern="yyyy-MM-dd"/></p>
		                    	<p><fmt:formatDate value="${counselor.createDate}" pattern="HH:mm:ss"/></p>
							  </td>
		                      <td>
								<p><fmt:formatDate value="${counselor.lastLoggedDate}" pattern="yyyy-MM-dd"/></p>
		                    	<p><fmt:formatDate value="${counselor.lastLoggedDate}" pattern="HH:mm:ss"/></p>
							  </td>
		                      <td>
								<p><input type="number" data-readonly="" value="${counselor.points}" class="rating" style="display:none;"/></p>
								<p>(${counselor.points})</p>
							  </td>
							  <td>${counselor.status }</td>
		                   </tr>
                  	</c:forEach>
                  </tbody>
                </table>
              </div>
              <!--.table-member-mng-->

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
				var status_counselor_display = $(this).attr('data-status_counselor_display');
				var status_counselor_Normal = $(this).attr('data-status_counselor_Normal');
				var status_counselor_banned = $(this).attr('data-status_counselor_banned');
				var type_search = $('#type_search').val();
				var keyword = $('#keyword').val();
				var orderType = $('#type_order').val();

				console.log("status_counselor_display:" + status_counselor_display);
				console.log("status_counselor_Normal:" + status_counselor_Normal);
				console.log("status_counselor_banned:" + status_counselor_banned);
				window.location = "/counselingCenter/auth/counselorInfo?type_order="+ orderType + "&status_counselor_display="+ status_counselor_display + "&status_counselor_Normal="+ status_counselor_Normal +"&status_counselor_banned="+ status_counselor_banned +"&keyword="+ keyword ;
			});
		});
	</script>

