//popup coin
  $("#buttonGiveRetrieveHeart").on("click", function(event){
      $('#popupGiveRetrieveHelloCoins').modal('show');
      $("[href='#tab-2']").tab('show');
  });
  
  $("#buttonGiveRetrieveHelloCoin").on("click", function(event){
      $('#popupGiveRetrieveHelloCoins').modal('show');
      $("[href='#tab-1']").tab('show');
  });
  
  $("#buttonGiveHeartVoucher").on("click", function(event){
      $('#popupGiveRetrieveHelloCoins').modal('show');
      $("[href='#tab-3']").tab('show');
  });
  
  $("#buttonSendBussinessVoucher").on("click", function(event){
      $('#popupSendBussinessVoucher').modal('show');
  });
  
  function required_form_hello_coin(){
		var coin = $('input[name=coin]:not([disabled])').val();
		var userId = $('input[name=userId]').val();
		if($('input[name=type_hellocoin]').is(':checked') == true & $('input[name=payment_reason_hellocoin]:not([disabled])').is(':checked') == true & coin !='' && $('input[name=userId]').length > 0 && userId !=''){
			if($('input[name=type_hellocoin]:checked').val() == 0){
				$('#popupConfirmAscHelloCoin').modal('show');
			}else{
				$('#popupConfirmDescHelloCoin').modal('show');
			}
		}else{
			$('#popupConfirmInput').modal('show');
		}
		
		
	}
  
  function required_form_heart(){
		var coin = $('input[name=heart]:not([disabled])').val();
		var userId = $('input[name=userId]').val();
		if($('input[name=type_heart]').is(':checked') == true & $('input[name=payment_reason_heart]:not([disabled])').is(':checked') == true & coin !='' && $('input[name=userId]').length > 0 && userId !=''){
			if($('input[name=type_heart]:checked').val() == 0){
				$('#popupConfirmAscHeart').modal('show');
			}else{
				$('#popupConfirmDescHeart').modal('show');
			}
		}else{
			$('#popupConfirmInput').modal('show');
		}
	}
  
  function required_form_heart_voucher(){
	    var userId = $('input[name=userId]').val();
		var coin = $('input[name=counseling_number]').val();
		if($('input[name=payment_reason_heart_voucher]').is(':checked') == true & coin !='' && $('input[name=userId]').length > 0 && userId !=''){
			$('#form_change_heart_voucher').submit();
		}else{
			$('#popupConfirmInput').modal('show');
		}
	}
  
  function required_form_business_voucher(){
		var coin = $('input[name=numberBusineesVoucher]').val();
		if($('input[name=businessVoucherId]').is(':checked') == true & coin !=''){
			$('#form_send_bussines_voucher').submit();
		}else{
			$('#popupConfirmInputBusinessVoucher').modal('show');
		}
	}
  
  function checkFormMemberDetail(){
		var fullname_input = $('#fullname').val();
		var phone_input = $('#phone').val();
		
		if(fullname_input != '' & phone_input != ''){
			$('#popupConfirmSaveDate').modal('show');
			
		}else{
			var required_text='';
			if(fullname_input == ''){
				required_text = required_text+"<p>[회원명]</p>";
  		}
			if(phone_input == ''){
				required_text = required_text+"<p>[연락처]</p>";
  		}
			$('#required_message').html(required_text);
			required_text='';
			$('#popupRequiredInput').modal('show');
			
		}
	}
  
  function closePushBusinessPopup(){
		var number = $('input[name=businessSubIds]:checked').size();
		$('#numberBusiness').text(number+'개');
		$('#popupSelectBusiness').modal('hide');
	}
  
  function closePushIOSPopup(){
		var number = $('input[name=versionIosIds]:checked').size();
		$('#numberVersionIos').text(number+'개');
		$('#popupSelectVersionIOS').modal('hide');
	}
  
  function closePushAndroidPopup(){
		var number = $('input[name=versionAndroidIds]:checked').size();
		$('#numberVersionAndroid').text(number+'개');
		$('#popupSelectVersionAndroid').modal('hide');
	};
	
	$('#delete-search-member-full').click(function(event) {
	  	event.preventDefault();
	  	var deleteUserIds = new Array();
	  	$("input:checkbox[name=deleteMember]:checked").each(function() {
	  		deleteUserIds.push($(this).val());
	   });
	  	console.log(deleteUserIds);
	  	if(deleteUserIds.length > 0){
	  		var url = "/api/v1/public/push-member/delete";
		  	$.ajax({
				type: "GET",
				url: url,
			    dataType:'json',
				data: {deleteUserIds:deleteUserIds},
				success: function(response)
				{
					if(response.code == 0){
						updateTableSeachMember(response.data);
					}
				}
			});
	  	}else{
	  		$('#popupRequiredDelete').modal('show');
	  	}
	})
  
 function ajax_paging_member(page){
	  var url = "/api/v1/public/push-member/paging";
	  $.ajax({
			type: "GET",
			url: url,
			data: {page:page},
			success: function(response)
			{
				if(response.code == 0){
					updateTableSeachMember(response.data)
				}
			}
		});
  };
	
  $('.next-slide-member#step1').click(function(event) {
	  	event.preventDefault();
	  	var fields =$('#form_push_member').serialize()
	  	var url = "/api/v1/public/push-member";
	  	$.ajax({
			type: "GET",
			url: url,
			data: fields,
			success: function(response)
			{
				
				if(response.code == 0){
					updateTableSeachMember(response.data)
				}
			}
		});
	  	
	  	var owl = $('.owl-carousel');
	  	owl.trigger('owl.next');
      window.scrollTo(0,0);
	})
  $('.next-slide-member#step2').click(function() {
	  	var owl = $('.owl-carousel');
	  	owl.trigger('owl.next');
      window.scrollTo(0,0);
	})

  
  function updateTableSeachMember(data){
	  $('#table-body').html('')
		var html ='';
		
		
		//table
		for (var i = 0; i < data.list.length; i++) {
			html +="<tr>";
			html+='<td> '
				html+='<label>'
					html+='<input type="checkbox" value="'+data.list[i].id+'" name="deleteMember"/>'
				html+='</label>'
			html+='</td>';
			html+='<td>'+data.list[i].id+'</td>';
			html+='<td>'+data.list[i].username+'</td>';
			html+='<td>'+data.list[i].fullname+'</td>';
			html+='<td>'+data.list[i].osName+'</td>';
			html+='<td> ';
				html+=data.list[i].address;
			html+='</td>';
			html +="</tr>";
		}
		$('#table-body').html(html)
		$('#totalAndroid').text(data.other[0]);
		$('#totalIos').text(data.other[1]);
		
		//paging
		var paging ="";
		if(data.totalPages > 1){
			paging +='<li ><a onclick="ajax_paging_member(1);return false;"><i class="fa fa-angle-left"><jsp:text/></i><jsp:text/></a><jsp:text/></li>';
			for (var int = 0; int < data.totalPages; int++) {
				if(data.current == (int+1)){
					paging +='<li class="active"><a onclick="ajax_paging_member('+(int+1)+');return false;">'+(int+1)+'</a><jsp:text/></li>';
				}else{
					paging +='<li><a onclick="ajax_paging_member('+(int+1)+');return false;">'+(int+1)+'</a><jsp:text/></li>';
				}
				
			}
			paging +='<li><a onclick="ajax_paging_member('+data.totalPages+');return false;"><i class="fa fa-angle-right"><jsp:text/></i><jsp:text/></a><jsp:text/></li>';
			
		}
		$('.pagination').html(paging);
  }
  
  $('.next-slide-device#step1').click(function(event) {
	  	event.preventDefault();
	  	var fields =$('#form_push_member').serialize()
	  	var url = "/api/v1/public/push-device";
	  	$.ajax({
			type: "GET",
			url: url,
			data: fields,
			success: function(response)
			{
				if(response.code == 0){
					updateTableSeachDecive(response.data)
				}
			}
		});
	  	var owl = $('.owl-carousel');
	  	owl.trigger('owl.next');
    window.scrollTo(0,0);
	})
	
$('.next-slide-device#step2').click(function() {
	  	var owl = $('.owl-carousel');
	  	owl.trigger('owl.next');
    window.scrollTo(0,0);
	})
	
	 function ajax_paging_device(page){
	  var url = "/api/v1/public/push-device/paging";
	  $.ajax({
			type: "GET",
			url: url,
			data: {page:page},
			success: function(response)
			{
				if(response.code == 0){
					updateTableSeachDecive(response.data)
				}
			}
		});
  };
  
  $('#delete-search-device-full').click(function(event) {
	  	event.preventDefault();
	  	var deleteUserIds = new Array();
	  	$("input:checkbox[name=deleteDevice]:checked").each(function() {
	  		deleteUserIds.push($(this).val());
	   });
	  	console.log(deleteUserIds);
	  	if(deleteUserIds.length > 0){
	  		var url = "/api/v1/public/push-device/delete";
		  	$.ajax({
				type: "GET",
				url: url,
			    dataType:'json',
				data: {deleteUserIds:deleteUserIds},
				success: function(response)
				{
					if(response.code == 0){
						updateTableSeachDecive(response.data);
					}
				}
			});
	  	}else{
	  		$('#popupRequiredDelete').modal('show');
	  	}
	})
	
	function updateTableSeachDecive(data){
	  $('#table-body').html('')
		var html ='';
		//table
		for (var i = 0; i < data.list.length; i++) {
			html +="<tr>";
			html+='<td> '
				html+='<label>'
					html+='<input type="checkbox" value="'+data.list[i].id+'" name="deleteDevice"/>'
				html+='</label>'
			html+='</td>';
			html+='<td>'+data.list[i].id+'</td>';
			html+='<td>'+data.list[i].deviceId+'</td>';
			html+='<td>'+data.list[i].osName+'</td>';
			html+='<td> ';
				html+=data.list[i].address;
			html+='</td>';
			html +="</tr>";
		}
		$('#table-body').html(html)
		$('#totalAndroid').text(data.other[0]);
		$('#totalIos').text(data.other[1]);
		
		//paging
		var paging ="";
		if(data.totalPages > 1){
			paging +='<li ><a onclick="ajax_paging_device(1);return false;"><i class="fa fa-angle-left"><jsp:text/></i><jsp:text/></a><jsp:text/></li>';
			for (var int = 0; int < data.totalPages; int++) {
				if(data.current == (int+1)){
					paging +='<li class="active"><a onclick="ajax_paging_device('+(int+1)+');return false;">'+(int+1)+'</a><jsp:text/></li>';
				}else{
					paging +='<li><a onclick="ajax_paging_device('+(int+1)+');return false;">'+(int+1)+'</a><jsp:text/></li>';
				}
				
			}
			paging +='<li><a onclick="ajax_paging_device('+data.totalPages+');return false;"><i class="fa fa-angle-right"><jsp:text/></i><jsp:text/></a><jsp:text/></li>';
			
		}
		$('.pagination').html(paging);
  }
  
  Date.prototype.yyyymmdd = function() {
	  var mm = this.getMonth() + 1; // getMonth() is zero-based
	  var dd = this.getDate();

	  return [this.getFullYear(),
	          (mm>9 ? '' : '0') + mm,
	          (dd>9 ? '' : '0') + dd
	         ].join('');
	};
	
  Date.prototype.addDays = function(days) {
	    this.setDate(this.getDate() + parseInt(days));
	    return this;
	};
	
	
  function dateFromWeekNumberByYearWeek(year, week) {
	  var simple = new Date(year, 0, 1 + (week - 1) * 7);
	  var dow = simple.getDay();
	  var startWeek = simple;
	  if (dow <= 4)
		  startWeek.setDate(simple.getDate() - simple.getDay() + 1);
	  else
		  startWeek.setDate(simple.getDate() + 8 - simple.getDay());
	  var start = $.datepicker.formatDate('mm/dd', startWeek);
	  var endWeek = startWeek.addDays(6);
	  var end = $.datepicker.formatDate('mm/dd', endWeek);
	  var date = "W"+week+" ("+start+"~"+end+")";
	  $('#fromWeek').append(
				$("<option></option>").attr("value",week).text(date)
			);

	  $('#toWeek').append(
				$("<option></option>").attr("value",week).text(date)
			);
	  
	}
  
  $(document.body).on('click', 'tr .btn-confirm-show-popup-icon', function(e) {
	    $(this).closest('tr').addClass('add-image');
	    $(".file-image-upload-1").val("");
	    $(".file-image-upload-2").val("");
	    $("show-file-info").val("");
	    $("#popupUploadIcon").modal("show");
	  });
  
//check validate file upload avatar #popupcontent2file
  $(document.body).on('click', '#popupUploadIcon .btn-confirm', function(e) {
    if ($('#popupUploadIcon .vali-file1').html().length > 0 && $('#popupUploadIcon .vali-file2').html().length > 0 ) {
    	$(".add-image .preview-image").empty();
    	$('.add-image1 img').clone().appendTo(".add-image .preview-image.img1");
    	$('.add-image2 img').clone().appendTo(".add-image .preview-image.img2");
    	$('#popupUploadIcon .bs-example').removeClass('hidden');
    	
    	$('.add-image input[name=imageCheckUrl]').remove();
    	$('.add-image input[name=imageUncheckUrl]').remove();
    	$(".file-image-upload-1").clone().appendTo('.add-image .image1');
    	$(".file-image-upload-2").clone().appendTo('.add-image .image2');
    	
    	$('.add-image').removeClass('add-image');
    	$("#popupUploadIcon").modal("hide");
      if( $('#popupUploadIcon .bs-example').hasClass('hidden')){
      }
      else{
        $('#popupUploadIcon .bs-example').addClass('hidden');
      }
    }else{
    	alert(123);
    	$(".add-image .preview-image").empty();
    	$('.add-image1').appendTo(".add-image .preview-image");
    	$('#popupUploadIcon .bs-example').removeClass('hidden');
    	$('.add-image').removeClass('add-image');
    	$("#popupUploadIcon").modal("hidden");
    }
      
  });
  
  
  
  
	
 