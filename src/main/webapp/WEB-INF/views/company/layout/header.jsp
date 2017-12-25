<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/company/layout/include.jsp" %>
<c:set var="requestPath" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<div class="sidebar-left">
   <div class="page-sidebar-wrapper">
        <div class="page-sidebar navbar-collapse collapse">
          <div class="page-logo"><a href="/company/auth/home"><img src="/theme/company/assets/images/logo/logo11.png" alt="logo" class="logo-default"/></a></div>
          <!-- .page-logo -->
          <div class="profile">
            <div class="profile_pic"><img src="/theme/company/assets/images/avatar-talk.jpg" alt="..." class="profile_img"/></div>
            <div class="profile_info"><span>Welcome,</span>
              <h3>${sessionScope.company.fullname }</h3>
            </div>
          </div>
          <ul class="sidebar-menu">
          	<c:if test="${requestPath == '/company/auth/member'}">
          		<li class="active">
					<a href="#">
						<i class="fa fa-user-o"></i><span>회원 관리</span><i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="sidebar-submenu">
						<li class="active"><a href="/company/auth/member"><i class="fa fa-angle-double-right"></i>회원 정보</a></li>
					</ul>
	            </li>
				<li>
					<a href="#">
						<i class="fa fa-user-o"></i><span>기업 페이지</span><i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="sidebar-submenu">
						<li><a href="/company/auth/business"><i class="fa fa-angle-double-right"></i>기업 페이지 편집</a></li>
					</ul>
	            </li>
	            <li>
					<a href="#">
						<i class="fa fa-user-o"></i><span>통계</span><i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="sidebar-submenu">
						<li><a href="/company/auth/statistics/voucher"><i class="fa fa-angle-double-right"></i>상담권 사용 통계</a></li>
						<li><a href="/company/auth/statistics/member_voucher"><i class="fa fa-angle-double-right"></i>회원별 사용 현황</a></li>
					</ul>
	            </li>
          	</c:if>
          	<c:if test="${requestPath == '/company/auth/business'}">
          		<li>
					<a href="#">
						<i class="fa fa-user-o"></i><span>회원 관리</span><i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="sidebar-submenu">
						<li><a href="/company/auth/member"><i class="fa fa-angle-double-right"></i>회원 정보</a></li>
					</ul>
	            </li>
				<li class="active">
					<a href="#">
						<i class="fa fa-user-o"></i><span>기업 페이지</span><i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="sidebar-submenu">
						<li class="active"><a href="/company/auth/business"><i class="fa fa-angle-double-right"></i>기업 페이지 편집</a></li>
					</ul>
	            </li>
	            <li>
					<a href="#">
						<i class="fa fa-user-o"></i><span>통계</span><i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="sidebar-submenu">
						<li><a href="/company/auth/statistics/voucher"><i class="fa fa-angle-double-right"></i>상담권 사용 통계</a></li>
						<li><a href="/company/auth/statistics/member_voucher"><i class="fa fa-angle-double-right"></i>회원별 사용 현황</a></li>
					</ul>
	            </li>
          	</c:if>
          	<c:if test="${requestPath == '/company/auth/statistics/voucher'}">
          		<li>
					<a href="#">
						<i class="fa fa-user-o"></i><span>회원 관리</span><i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="sidebar-submenu">
						<li><a href="/company/auth/member"><i class="fa fa-angle-double-right"></i>회원 정보</a></li>
					</ul>
	            </li>
				<li>
					<a href="#">
						<i class="fa fa-user-o"></i><span>기업 페이지</span><i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="sidebar-submenu">
						<li><a href="/company/auth/business"><i class="fa fa-angle-double-right"></i>기업 페이지 편집</a></li>
					</ul>
	            </li>
	            <li class="active">
					<a href="#">
						<i class="fa fa-user-o"></i><span>통계</span><i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="sidebar-submenu">
						<li class="active"><a href="/company/auth/statistics/voucher"><i class="fa fa-angle-double-right"></i>상담권 사용 통계</a></li>
						<li><a href="/company/auth/statistics/member_voucher"><i class="fa fa-angle-double-right"></i>회원별 사용 현황</a></li>
					</ul>
	            </li>
          	</c:if>
          	<c:if test="${requestPath == '/company/auth/statistics/member_voucher'}">
          		<li>
					<a href="#">
						<i class="fa fa-user-o"></i><span>회원 관리</span><i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="sidebar-submenu">
						<li><a href="/company/auth/member"><i class="fa fa-angle-double-right"></i>회원 정보</a></li>
					</ul>
	            </li>
				<li>
					<a href="#">
						<i class="fa fa-user-o"></i><span>기업 페이지</span><i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="sidebar-submenu">
						<li><a href="/company/auth/business"><i class="fa fa-angle-double-right"></i>기업 페이지 편집</a></li>
					</ul>
	            </li>
	            <li class="active">
					<a href="#">
						<i class="fa fa-user-o"></i><span>통계</span><i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="sidebar-submenu">
						<li><a href="/company/auth/statistics/voucher"><i class="fa fa-angle-double-right"></i>상담권 사용 통계</a></li>
						<li class="active"><a href="/company/auth/statistics/member_voucher"><i class="fa fa-angle-double-right"></i>회원별 사용 현황</a></li>
					</ul>
	            </li>
          	</c:if>
          	
          	<c:if test="${requestPath == '/company/auth/home'}">
          		<li>
					<a href="#">
						<i class="fa fa-user-o"></i><span>회원 관리</span><i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="sidebar-submenu">
						<li><a href="/company/auth/member"><i class="fa fa-angle-double-right"></i>회원 정보</a></li>
					</ul>
	            </li>
				<li>
					<a href="#">
						<i class="fa fa-user-o"></i><span>기업 페이지</span><i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="sidebar-submenu">
						<li><a href="/company/auth/business"><i class="fa fa-angle-double-right"></i>기업 페이지 편집</a></li>
					</ul>
	            </li>
	            <li>
					<a href="#">
						<i class="fa fa-user-o"></i><span>통계</span><i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="sidebar-submenu">
						<li><a href="/company/auth/statistics/voucher"><i class="fa fa-angle-double-right"></i>상담권 사용 통계</a></li>
						<li><a href="/company/auth/statistics/member_voucher"><i class="fa fa-angle-double-right"></i>회원별 사용 현황</a></li>
					</ul>
	            </li>
          	</c:if>
          </ul>
        </div>
    </div>
</div>

<div class="page-header">
      <div class="page-header-inner container">
        <!-- BEGIN LOGO-->
        <!-- .responsive-toggler-->
        <div class="top-menu">
          <ul class="nav navbar-nav pull-right">
            <li class="dropdown dropdown-user"><a href="javascript:;" data-toggle="dropdown" data-hover="dropdown" data-close-others="true" class="dropdown-toggle">
            <span class="username username-hide-on-mobile">${sessionScope.company.fullname }  ${sessionScope.company.email }</span><i class="fa fa-angle-down"><jsp:text/></i><jsp:text/></a>
              <div class="dropdown-menu dropdown-menu-default">
                <div class="item-menu"><span class="title">최근 로그인</span><span><fmt:formatDate value="${sessionScope.company.lastLogin }" pattern="yyyy-MM-dd HH:mm:ss"/></span></div>
                <div class="item-menu"><span class="title">${sessionScope.company.fullname }</span><span>${sessionScope.company.email }</span></div>
                <div class="footer-menu">
                  <a href="/public/company/logout" class="btn green pull-right">로그아웃</a>
                </div>
              </div>
            </li>
            <!-- .dropdown-user-->
          </ul>
        </div>
        <!-- .top-menu-->
      </div>
    </div>
<div class="clearfix"></div>