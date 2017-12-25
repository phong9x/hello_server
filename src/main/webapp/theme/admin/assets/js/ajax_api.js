function getSubBusiness(){
	var url = url_base+"api/v1/public/getSubBusiness";
	var bussinessId = $('#bussinessId').val();
	console.log(bussinessId);
		$.ajax({
			type: "GET",
			url: url,
			data: {bussinessId:bussinessId},
			success: function(response)
			{
				console.log(response);
				if(response.code == 0){
					$('#subBussinessId').html('')
					for (var i = 0; i < response.data.length; i++) {
						$('#subBussinessId').html(
							$("<option></option>").attr("value",response.data[i].id).text(response.data[i].businessName)
						); 
					}
				}
			}
		});
}


$("form .hello_coin_search_memeber").click(function() {
	var url = url_base+"api/v1/public/serchMemberByUsername";
	var username = $(this).closest("form").find(".userNameSearch").val();
	var resultDisplay = $(this).closest("form").find(".result-popup");
		$.ajax({
			type: "GET",
			url: url,
			data: {username:username},
			success: function(response)
			{
				if(response.code == 0){
					
					var html="";
					html+="<input type='hidden' name='userId' id='userId' value='"+response.data.id+"' class='form-control'/>";
					html+="<div class='item'><span class='title' >ㆍ아이디</span>";
					html+="<div class='ct'><a  target='_blank' id='username_form' href=/admin/user/member/detail/"+response.data.id+">"+response.data.username+"</a></div>";
					html+="</div>";
					html+="<div class='item'><span class='title'>ㆍ이름</span><span class='ct' id='fullname_form'>"+response.data.fullname+"</span></div>";
					html+="<div class='item'><span class='title'>ㆍ연락처</span><span class='ct' id='phone_form'>"+response.data.phone+"</span></div>";
					html+="<div class='item'><span class='title'>ㆍ생년월일</span><span class='ct' id='yearBirth_form'>"+response.data.dayOfBirth+"</span></div>";
					resultDisplay.html(html);
				}else{
					var html="";
					html+="<div class='item'>아이디를 정확히 입력하세요</div>";
					$('#result').html(html);
				}
			},
			  error: function(XMLHttpRequest, textStatus, errorThrown) {
				     alert("some error");
				  }
		});
	});

$("form #deleteAccountMemmber").click(function() {
	var url = url_base+"api/v1/public/member/checkWithDrawl";
	var userId = $("#deleteAccountMemmber").data("id");
		$.ajax({
			type: "GET",
			url: url,
			data: {userId:userId},
			success: function(response)
			{
				if(response.code == 0){
					console.log("response.data: "+response.data);
					if(response.data > 0){
						$("#popupConfirmDeleteNoAccount").modal("show");
					}else{
						$("#popupConfirmDeleteAccount").modal("show");
					}
				}
			},
			  error: function(XMLHttpRequest, textStatus, errorThrown) {
				     alert("some error");
				  }
		});
	});

function getSubBusiness(){
	var url = url_base+"api/v1/public/getSubBusiness";
	var bussinessId = $('#bussinessId').val();
	console.log(bussinessId);
		$.ajax({
			type: "GET",
			url: url,
			data: {bussinessId:bussinessId},
			success: function(response)
			{
				console.log(response);
				if(response.code == 0){
					$('#subBussinessId').html('')
					for (var i = 0; i < response.data.length; i++) {
						$('#subBussinessId').html(
							$("<option></option>").attr("value",response.data[i].id).text(response.data[i].businessName)
						); 
					}
				}
			}
		});
}

function toggleLoading() {
    if ($('body').find('#ajax_loading').length) {
      $('#ajax_loading').css('display') == 'none' ?
        $('#ajax_loading').css('display', '') :
        $('#ajax_loading').css('display', 'none');
    } else {
      var html = '<div id="ajax_loading" class="loading-page">';
      html += '<div class="img-loading">';
      html += '<img src="/theme/admin/assets/images/loading.svg" alt="loading"/>';
      html += '</div>';
      html += '</div>';
      $('body').append(html);
    }
  }

$("#button-profit-process-upload-excel").click(function(event) {
	event.preventDefault();
	var url = url_base+"api/v1/public/counselor-profit/upload-excel";
	var form = $('#counselor_profit_process_form')[0];
	var formdata = new FormData(form);
	//formdata.append('excel', $('input[name=excel]')[0].files[0]);
	$("#button-profit-process-upload-excel").prop("disabled", true);
	toggleLoading();
		$.ajax({
			type: "POST",
			url: url,
			data: formdata,
	        processData: false,
	        contentType: false,
	        cache: false,
	        timeout: 600000,
	        enctype: 'multipart/form-data',
			success: function(response)
			{
				if(response.code == 0){
					console.log("Process....");
					for (var i = 0; i < response.data.length; i++) {
						if(response.data[i].type != null){
							$('#type'+response.data[i].id).val(1);
							if(response.data[i].status == 1){
								$('#statusName'+response.data[i].id).html('지급완료');
								$('#statusValue'+response.data[i].id).val(1);
							}else{
								$('#statusName'+response.data[i].id).html('미지급');
								$('#statusValue'+response.data[i].id).val(0);
							}
						}
					}
				}else{
					console.log("Error: "+response.message);
					$('.show-file-info').text('');
				  	$('input[name=excel]').val('');
					$('#popupUploadExcelFail').modal('show')
				}
				$('#popupUploadExcel').modal('hide')
				$("#button-profit-process-upload-excel").prop("disabled", false);
			}
			,complete: function(){
				toggleLoading();
		        }
			,error: function(XMLHttpRequest, textStatus, errorThrown) {
				  	$("#button-profit-process-upload-excel").prop("disabled", false);
				  	$('#popupUploadExcelFail').modal('show')
				  	$('.show-file-info').text('');
				  	$('input[name=excel]').val('');
				     console.log("Status: "+textStatus+"- Error: "+errorThrown);
				  }
		});
	});