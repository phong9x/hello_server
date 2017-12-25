<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselingCenter/layout/include.jsp" %>
<c:set var="requestPath" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<div class="sidebar-left">
   <div class="page-sidebar-wrapper">
        <div class="page-sidebar navbar-collapse collapse">
          <div class="page-logo"><a href="/counselingCenter/auth/home"><img src="/theme/counselingCenter/assets/images/logo/logo11.png" alt="logo" class="logo-default"/></a></div>
          <!-- .page-logo -->
          <div class="profile">
            <div class="profile_pic"><img src="/theme/counselingCenter/assets/images/avatar-talk.jpg" alt="..." class="profile_img"/></div>
            <div class="profile_info"><span>Welcome,</span>
              <h3>${sessionScope.counselingCenter.fullname }</h3>
            </div>
          </div>
          <ul class="sidebar-menu">
	            	<li class=${activePage == 'counselorInfo'? 'active':'' }>
						<a href="#">
							<i class="fa fa-user-o"></i><span>상담사 정보</span><i class="fa fa-angle-left pull-right"></i>
						</a>
						<ul class="sidebar-submenu">
							<li class="active"><a href="/counselingCenter/auth/counselorInfo"><i class="fa fa-angle-d/counselingCenter/ht"></i>상담사 정보</a></li>
						</ul>
		            </li>
					<li class=${activePage == 'counselingSummary'? 'active':'' }>
						<a href="#">
							<i class="fa fa-user-o"></i><span>상담 내역</span><i class="fa fa-angle-left pull-right"></i>
						</a>
						<ul class="sidebar-submenu">
							<li class="active"><a href="/counselingCenter/auth/counselingSummary"><i class="fa fa-angle-double-right"></i>상담 내역</a></li>
						</ul>
		            </li>
		            <li class=${activePage == 'statistics'? 'active':'' }>
						<a href="#">
							<i class="fa fa-user-o"></i><span>통계</span><i class="fa fa-angle-left pull-right"></i>
						</a>
						<ul class="sidebar-submenu">
							<li class=${menu == 'counseling'? 'active':'' }><a href="/counselingCenter/auth/statistics/period"><i class="fa fa-angle-double-right"></i>상담 통계</a></li>
							<li class=${menu == 'counselor'? 'active':'' }><a href="/counselingCenter/auth/statistics/counselor"><i class="fa fa-angle-double-right"></i>상담사 실적</a></li>
							<li class=${menu == 'profit'? 'active':'' }><a href="/counselingCenter/auth/statistics/profit"><i class="fa fa-angle-double-right"></i>정산 리포트</a></li>
						</ul>
		            </li>
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
            <span class="username username-hide-on-mobile">${sessionScope.counselingCenter.fullname }  ${sessionScope.counselingCenter.email }</span><i class="fa fa-angle-down"><jsp:text/></i><jsp:text/></a>
              <div class="dropdown-menu dropdown-menu-default">
                <div class="item-menu"><span class="title">최근 로그인</span><span><fmt:formatDate value="${sessionScope.counselingCenter.lastLogin }" pattern="yyyy-MM-dd HH:mm:ss"/></span></div>
                <div class="item-menu"><span class="title">${sessionScope.counselingCenter.fullname }</span><span>${sessionScope.counselingCenter.email }</span></div>
                <div class="footer-menu">
                  <a href="/public/counselingCenter/logout" class="btn green pull-right">로그아웃</a>
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