<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselingCenter/layout/include.jsp" %>

<div class="page-container">
      <div class="member page-content-wrapper">
        <div class="page-content">
          <div class="container">
            
            <div class="home-top3 row mrb-30">
              <div class="col-md-6">
                <div class="content border-full">
					<div class="title">
						<span class="bold">소속 상담사</span>
						<span class="pull-right">${currentDate } 기준</span>
					</div>
					<div class="item-content">
						<div class="col-md-4 text-center">
							<div class="text-center">
							  <p>전체 소속 회원</p>
							  <p class="bold" style="font-size: 22px;">${counselingStatistics.totalCounselor_normal}</p>
							</div>
						</div>
						<div class="col-md-4 text-center">
						  	<div class="text-center">
							  <p>노출중지</p>
							  <p class="bold" style="font-size: 22px;">${counselingStatistics.totalCounselor_NotShowInApp}</p>
							</div>
						</div>
						<div class="col-md-4 text-center">
						  	<div class="text-center">
							  <p>비활성화</p>
							  <p class="bold" style="font-size: 22px;">${counselingStatistics.totalCounselor_Inactive}</p>
							</div>
						</div>
					</div>
                </div>
            </div>
            
            <div class="col-md-6">
                <div class="content border-full">
					<div class="title text-center" >
						<span class="bold">전일 기준 예약/상담 현황</span>
					</div>
					<div class="item-content">
						<div class="col-md-6 text-center">
							<div class="text-center">
							  <p>예약 수</p>
							  <p class="bold" style="font-size: 22px;">${counselingStatistics.totalCounseling}</p>
							</div>
						</div>
						<div class="col-md-6 text-center">
						  	<div class="text-center">
							  <p>상담 수</p>
							  <p class="bold" style="font-size: 22px;">${counselingStatistics.totalCounseled}</p>
							</div>
						</div>
					</div>
                </div>
            </div>
			  
			
            </div>
            <div class="home-top4 row">
              <div class="col-md-12">
                <div class="content border-full">
					<div class="title mrb-30 border-bottom" style="padding-bottom: 10px;">
						<span class="bold">상담 통계</span>
						<span class="pull-right">최근 1주일 기준</span>
					</div>
                  <script type="text/javascript" src="/theme/counselingCenter/assets/js/Chart.bundle.min.js"></script>
                  <canvas id="cavas-chart" data-listDateBeforeSevenDays="${listDateBeforeSevenDays}" data-totalCounselings= "${totalCounselings}" data-totalCounseleds = "${totalCounseleds}"></canvas>
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
    	
    	var totalCounselings = $('#cavas-chart').attr('data-totalCounselings');
    	totalCounselings = totalCounselings.replace("[","");
    	totalCounselings = totalCounselings.replace("]","");
    	var totalCounselingsStr = totalCounselings.split(',');
    	
    	var totalCounseleds = $('#cavas-chart').attr('data-totalCounseleds');
    	totalCounseleds = totalCounseleds.replace("[","");
    	totalCounseleds = totalCounseleds.replace("]","");
    	var totalCounseledsStr = totalCounseleds.split(',');
    	
    	console.log("listDateBeforeSevenDaysStr:" + listDateBeforeSevenDaysStr);
    	console.log("totalCounselingsStr:" + totalCounselingsStr);
    	console.log("totaltotalCounseledsStr:" + totalCounseledsStr);
    	
      $(document).ready(function() {

        // chart.js

        var barChartData = {
            labels: listDateBeforeSevenDaysStr,
            datasets: [
            {
                label: '예약 수',
                backgroundColor: "rgba(129,6,35,1)",
                data: totalCounselingsStr,
            }, 
            {
                // hidden: true,
                label: '상담 수',
                // borderWidth: 0;
                backgroundColor: "rgba(67,70,190,1)",
                data: totalCounseledsStr,
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
