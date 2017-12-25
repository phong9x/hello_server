<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/company/layout/include.jsp" %>

<div class="page-container">
      <div class="page-content-wrapper">
        <div class="page-content">
          <div class="container">
            <div class="main-body-content member-page push-list">
              <div class="page-bar">
                <ul class="page-breadcrumb">
                  <li><a href="" class="home"> <i class="fa fa-home"></i>홈</a></li>
                  <li><a href="">회원 관리</a></li>
				  <li><a href="">회원 정보</a></li>
                </ul>
              </div>
              <!-- .page-bar-->

              <form class="form_sample_3">
                <div class="member-main-top border-full">
                  <div class="row mrb-30">
						<div class="col-md-6">
							<div class="line-30 col-md-3">
								<span>연도</span>
							</div>
							<div class="col-md-6">
								  <div class="item">
									<select name="year_search" id="year_search" class="form-control selectpickermember text-left">
									  <c:forEach begin="2016" end="2030" var="year">
									  	<c:if test="${yearSearch == year}">
									  		<option value="${year}" selected="selected">${year}</option>
									  	</c:if>
									  	<c:if test="${yearSearch != year}">
									  		<option value="${year}">${year}</option>
									  	</c:if>
									  </c:forEach>
									</select>
								  </div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="line-30 col-md-3">
								<span>기업 상세 분류</span>
							</div>
							<div class="col-md-6">
								  <div class="item">
									<select name="sub_company" id="sub_company" class="form-control selectpickermember text-left">
										<c:if test="${subCompany == 0}">
											<option value="0" selected="selected">전체</option>
											<c:forEach items="${listBusinessSub}" var="subCom">
											  	<option value="${subCom.id}">${subCom.managerName}</option>
											</c:forEach>
										</c:if>
										<c:if test="${subCompany != 0}">
											<option value="0">전체</option>
											<c:forEach items="${listBusinessSub}" var="subCom">
												<c:if test="${subCom.id == subCompany}"><option value="${subCom.id}" selected="selected">${subCom.managerName}</option></c:if>
												<c:if test="${subCom.id != subCompany}"><option value="${subCom.id}">${subCom.managerName}</option></c:if>
											</c:forEach>
										</c:if>
									</select>
								  </div>
							</div>
						</div>
                  </div>
				  <div class="row">
						<div class="col-md-6">
							<div class="line-30 col-md-3">
								<span>키워드</span>
							</div>
							<div class="col-md-4">
								  <div class="item">
									<select name="type_search" id="type_search" class="form-control selectpickermember text-left">
										<c:if test="${typeSearch ==  'name_search'}">
											<option value="name_search" selected="selected">이름</option>
										</c:if>
										<c:if test="${typeSearch !=  'name_search'}">
											<option value="name_search">이름</option>
										</c:if>
										
										<c:if test="${typeSearch ==  'phone_search'}">
											<option value="phone_search" selected="selected">휴대폰 번호</option>
										</c:if>
										<c:if test="${typeSearch !=  'phone_search'}">
											<option value="phone_search">휴대폰 번호</option>
										</c:if>
									</select>
								  </div>
							</div>
							<div class="col-md-4">
								<input type="text" name="keyword" id="keyword" value="${keyword}" data-required="1" style="width: 500px;" class="form-control"/>
							</div>
						</div>
                  </div>
                  
                </div>
                <div class="link-submit">
                  <button type="submit" class="btn green">검색</button>
                </div>
              </form>
              <div class="member-title-table">
                <div class="md-ct"><span class="title">소속 회원 리스트 :</span><span>${totalCount}건</span>
                  <div class="pull-right">
                    <select name="rs_order" id="rs_order" class="form-control selectpickermember text-left">
                    	<c:if test="${rsOrder == 'name_order' }"><option value="name_order" selected="selected">이름순</option></c:if>
                    	<c:if test="${rsOrder != 'name_order' }"><option value="name_order">이름순</option></c:if>
                    	<c:if test="${rsOrder == 'createDate_order' }"><option value="createDate_order" selected="selected">등록 일시 순</option></c:if>
                    	<c:if test="${rsOrder != 'createDate_order' }"><option value="createDate_order">등록 일시 순</option></c:if>
                    </select>
                  </div>
                </div>
              </div>
              <div class="table-member-mng table-responsive">
                <table class="table table-striped table-bordered table-hover text-center checkall">
                  <thead>
                    <tr>
                      <th>번호</th>
                      <th>이름</th>
                      <th>생년월일</th>
                      <th>휴대폰 번호</th>
                      <th>연도</th>
					  <th>기업 상세 분류</th>
					  <th>등록일시</th>
                    </tr>
                  </thead>
                  <tbody>
                  	<c:forEach items="${list}" var="l">
                  		<tr>
	                      <td>${l.id}</td>
	                      <td>${l.fullname}</td>
	                      <td><fmt:formatDate value="${l.dayOfBirth}" pattern="yyyy-MM-dd"/></td>
	                      <td>${l.phone}</td>
	                      <td>${l.manageYear}</td>
						  <td>${l.businessSubName}</td>
						  <td>
						  	<p><fmt:formatDate value="${l.createDate}" pattern="yyyy-MM-dd"/></p>
		                    <p><fmt:formatDate value="${l.createDate}" pattern="HH:mm:ss"/></p>
						  </td>
	                    </tr>
                  	</c:forEach>
                    
                    
                  </tbody>
                </table>
              </div>
              <!--.table-member-mng-->
				<jsp:include page="/WEB-INF/views/company/layout/pagination_custom.jsp">
                     <jsp:param name="currentIndex"             value="${currentIndex}"/>
                     <jsp:param name="url"              value="${pagination_navigator}"/>
                     <jsp:param name="totalPages"        value="${totalPages}"/>
                     <jsp:param name="param_url"     value="${param_url}"/>
	             </jsp:include>
              
            </div>
            <!-- .member-main-->
          </div>
        </div>
        <!-- .page-content-->
      </div>
      <!-- .member-->
    </div>
    
    <script type="text/javascript">
		$(document).ready(function() {
			$('#rs_order').on('change', function() {
				var year_search = $('#year_search').val();
				var sub_company = $('#sub_company').val();
				var type_search = $('#type_search').val();
				var keyword = $('#keyword').val();
				var orderType = $('#rs_order').val();
				window.location = "/company/auth/member?rs_order="+ orderType + "&year_search="+ year_search + "&type_search="+ type_search +"&sub_company="+ sub_company +"&keyword="+ keyword  ;
			});
		});
	</script>


