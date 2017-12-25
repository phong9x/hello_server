<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/company/layout/include.jsp" %>

<div class="page-container">
      <div class="member page-content-wrapper">
        <div class="page-content">
          <div class="container">
            
            <div class="home-top3 row mrb-30">
              <div class="col-md-6">
                <div class="content border-full">
					<div class="title">
						<span class="bold">소속 회원</span>
						<span class="pull-right">${currentDate } 기준</span>
					</div>
					<div class="item-content">
						<div class="col-md-6 text-center">
						  <p class="bold" style="font-size: 22px;">가입 회원</p>
						</div>
						<div class="col-md-6 text-center">
						  <p class="bold" style="font-size: 22px;">${totalMemberByBusinessId }</p>
						</div>
					</div>
                </div>
            </div>
			  
			<div class="col-md-6">
                <div class="content border-full">
					<div class="title" style="text-align:center;">
						<span class="bold">기업 홍보페이지 승인 신청</span>
					</div>
					<div class="md-ct">
                      <p>등록하신 기업 홍보페이지 승인완료</p>
                      <a href="/company/auth/business" class="pull-right">페이지 바로가기</a>
					</div>
                </div>
              </div>
            </div>
            <div class="home-top4 row">
              <div class="col-md-12">
                <div class="content border-full">
					<div class="title mrb-30 border-bottom" style="padding-bottom: 10px;">
						<span class="bold">일별 발급/사용 상담권 통계</span>
						<span class="pull-right">최근 1주일 기준</span>
					</div>
                  <script type="text/javascript" src="/theme/company/assets/js/Chart.bundle.min.js"></script>
                  <canvas id="cavas-chart" data-listDateBeforeSevenDays="${listDateBeforeSevenDays}" data-totalVouchersMember= "${totalVouchersMember}" data-totalVouchers = "${totalVouchers}"></canvas>
                </div>
              </div>
            </div>
            <!-- .member-main-->
          </div>
        </div>
        <!-- .page-content-->
      </div>
      <!-- .member-->
    </div>
    
    <script type="text/javascript">
    	var listDateBeforeSevenDays = $('#cavas-chart').attr('data-listDateBeforeSevenDays');
    	listDateBeforeSevenDays = listDateBeforeSevenDays.replace("[","");
    	listDateBeforeSevenDays = listDateBeforeSevenDays.replace("]","");
    	var listDateBeforeSevenDaysStr = listDateBeforeSevenDays.split(',');
    	
    	var totalVouchers = $('#cavas-chart').attr('data-totalVouchers');
    	totalVouchers = totalVouchers.replace("[","");
    	totalVouchers = totalVouchers.replace("]","");
    	var totalVouchersStr = totalVouchers.split(',');
    	
    	var totalVouchersMember = $('#cavas-chart').attr('data-totalVouchersMember');
    	totalVouchersMember = totalVouchersMember.replace("[","");
    	totalVouchersMember = totalVouchersMember.replace("]","");
    	var totalVouchersMemberStr = totalVouchersMember.split(',');
    	
    	console.log("listDateBeforeSevenDaysStr:" + listDateBeforeSevenDaysStr);
    	console.log("totalVouchersStr:" + totalVouchersStr);
    	console.log("totalVouchersMemberStr:" + totalVouchersMemberStr);
    	
      $(document).ready(function() {

        // chart.js

        var barChartData = {
            labels: listDateBeforeSevenDaysStr,
            datasets: [
            {
                label: 'voucher',
                backgroundColor: "rgba(129,6,35,1)",
                data: totalVouchersStr,
            }, 
            {
                // hidden: true,
                label: 'voucher member',
                // borderWidth: 0;
                backgroundColor: "rgba(67,70,190,1)",
                data: totalVouchersMemberStr,
            }]

        };

        // window.onload = function() {
            var ctx = document.getElementById("cavas-chart").getContext("2d");
            window.myBar = new Chart(ctx, {
                type: 'bar',
                data: barChartData,
                options: {
                    elements: {
                        rectangle: {
                            borderWidth: 0
                        }
                    },
                    responsive: true,
                    legend: {
                        position: 'bottom',
                        borderWidth: 0
                    },
                    borderWidth: 0,
                    title: {
                        display: false
                    }
                }
            });
      });

        // };

        

    </script>


