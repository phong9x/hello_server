<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/company/layout/include.jsp" %>

<html>
	<head>
		<meta charset="utf-8"/>
	    <title>Hello || <tiles:insertAttribute name="title" /></title>
	    <meta name="viewport" 		content="width=device-width, initial-scale=1.0" />
	    <meta name="description" 	content="Hello Application" />
	    <meta name="author" 		content="Rubyvu - maivt.web@gmail.com" />
	    <!-- Web Fonts-->
	
	    <!-- Vendor CSS (GLOBAL MANDATORY STYLES)-->
	    <link rel="stylesheet" type="text/css" href="/theme/company/libs/metronic-admin/global/plugins/font-awesome/css/font-awesome.min.css"/>
	    <link rel="stylesheet" type="text/css" href="/theme/company/libs/metronic-admin/global/plugins/simple-line-icons/simple-line-icons.min.css"/>
	    <link rel="stylesheet" type="text/css" href="/theme/company/libs/metronic-admin/global/plugins/bootstrap/css/bootstrap.min.css"/>
	    <link rel="stylesheet" type="text/css" href="/theme/company/libs/metronic-admin/global/plugins/uniform/css/uniform.default.css"/>
	    <link rel="stylesheet" type="text/css" href="/theme/company/libs/metronic-admin/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css"/>
	
	    <!-- Vendor CSS (GLOBAL PAGE LEVEL STYLES)-->
	    <link rel="stylesheet" type="text/css" href="/theme/company/libs/metronic-admin/global/plugins/select2/select2.css"/>
	    <link rel="stylesheet" type="text/css" href="/theme/company/libs/metronic-admin/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"/>
	    <link rel="stylesheet" type="text/css" href="/theme/company/libs/metronic-admin/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"/>
	    <link rel="stylesheet" type="text/css" href="/theme/company/libs/metronic-admin/global/plugins/fullcalendar/fullcalendar.min.css"/>
	    <link rel="stylesheet" type="text/css" href="/theme/company/libs/metronic-admin/global/plugins/owl.carousel/assets/owl.carousel.css">
	
	    <!-- Vendor CSS (THEME STYLES)-->
	    <link rel="stylesheet" type="text/css" href="/theme/company/libs/metronic-admin/global/css/components.css"/>
	    <link rel="stylesheet" type="text/css" href="/theme/company/libs/metronic-admin/global/css/plugins.css"/>
	    <link rel="stylesheet" type="text/css" href="/theme/company/libs/metronic-admin/admin/layout/css/layout.css"/>
	    <link rel="stylesheet" type="text/css" href="/theme/company/libs/metronic-admin/admin/layout/css/themes/darkblue.css"/>
	 	<link rel="stylesheet" type="text/css" href="/theme/company/assets/css/bootstrap-select.min.css"/>
	    <!-- Theme CSS-->
	    <link rel="stylesheet" type="text/css" href="/theme/company/assets/css/admin.css"/>

		<script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/jquery.min.js"></script>
        <script type="text/javascript" src="/theme/company/assets/js/company.js"></script>
		
		<script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/jquery-migrate.min.js"></script>
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/jquery-ui/jquery-ui.min.js"></script>
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/bootstrap/js/bootstrap.min.js"></script>
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"></script>
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/jquery.blockui.min.js"></script>
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/jquery.cokie.min.js"></script>
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/uniform/jquery.uniform.min.js"></script>
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>
	
	    <!-- Vendor jQuery (PAGE LEVEL PLUGINS - METRONIC)-->
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/jquery-validation/js/jquery.validate.min.js"></script>
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/jquery-validation/js/additional-methods.min.js"></script>
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/select2/select2.min.js"></script>
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.kr.min.js"></script>
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/ckeditor/ckeditor.js"></script>
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/ckeditor/adapters/jquery.js"></script>
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/ckeditor/lang/ko.js"></script>
	    <!-- IMPORTANT! fullcalendar depends on jquery-ui.min.js for drag & drop support, and moment.min.js-->
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/moment.min.js"></script>
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/fullcalendar/fullcalendar.min.js"></script>
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/fullcalendar/lang/ko.js"></script>
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/global/plugins/owl.carousel/owl.carousel.min.js"></script>
	    <script type="text/javascript" src="/theme/company/assets/libs/starwars.js"></script>
	    <script type="text/javascript" src="/theme/company/assets/libs/bootstrap-rating.js"></script>
	    <script type="text/javascript" src="/theme/company/assets/libs/Chart.min.js"></script>
	
	    <!-- Vendor jQuery (PAGE LEVEL STYLES - METRONIC)-->
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/global/scripts/metronic.js"></script>
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/admin/layout/scripts/layout.js"></script>
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/admin/layout/scripts/quick-sidebar.js"></script>
	    <script type="text/javascript" src="/theme/company/libs/metronic-admin/admin/layout/scripts/demo.js"></script>
	
		<script type="text/javascript" src="/theme/company/assets/js/sidebar-menu.js"></script>
	    <!-- Theme Script-->
		<script type="text/javascript" src="/theme/company/assets/js/bootstrap-select.min.js"></script>
		<script type="text/javascript" src="/theme/company/assets/js/admin.js"></script>
		<script type="text/javascript" src="/theme/company/assets/js/required.js"></script>
		<script type="text/javascript" src="/theme/company/assets/js/customer.js"></script>
		

		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.6/handlebars.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js"></script>
        
	   
	</head>
	<body class="page-header-fixed page-quick-sidebar-over-content">
	    <tiles:insertAttribute name="header" />
        <tiles:insertAttribute name="body" />
        <tiles:insertAttribute name="footer" />
	</body>
</html>