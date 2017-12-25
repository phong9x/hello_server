var global_list =[];
var global_list_old =[];
$(document).ready(function() {
	(function($){
		$(document.body).on('click','.add-massage',function(){
			$("input[name='search_keyword']").val("");					
			user_message_popup(function() {
				$('#popupaddmassage').modal('show');
			});
		});
	})(jQuery);
	
	(function($){
		$(document.body).on('click','.btn-search-listuser',function(){
			user_message_popup();
		});
	})(jQuery);
	
	$('.btn-policy-create').on("click", function(e) {
		var type = $(this).attr('data-type');
		var data_content = showHTML();
		var version = $('#version').val();
		var selected = $("#typePolicy").val();
		if(data_content == '<p><br></p>' || version.length == 0 ) {					
			if(data_content == '<p><br></p>'){
				alert("입력되지 않은 항목이 있습니다.[내용]");
				return false;
			} else {
				alert("입력되지 않은 항목이 있습니다.[버전]");
				return false;
			}
		} else {
			$.ajax({
				method : "POST",
				url : "/api/v1/public/ajax_policy_create",
				data : {
					"type" : type,
					"data_content" : data_content,
					"version" : version,
					"typePolicy" : selected
				},
				success : function(rs) {
					if (rs.status == 200) {
						console.log("rs.status:" + rs.status)
						window.location="/admin/cs/policy/list";
					} else {
						alert("can not request !!!")
					}
				}
			});
		}
	});
	
	function user_message_popup(callback){
		$('#all').prop('checked',false);
		$('.checked').removeClass('checked');
		var url = window.location.protocol +"//" + window.location.host + "/v1/public/ajax_message_get_list_user";
		var keyword = $("input[name='search_keyword']").val();
		$.ajax({
		   type: "GET",
		   url: url,
		   data: {keyword:keyword},
		   success: function(response) {
			     var html = '';
			     $(".list_user").html('');
			     for (var i = 0; i < response.data.length; i++) {
			    	 if(jQuery.inArray(response.data[i].id,global_list) == -1){
			    		 html +='<div class="mrb-20 full check">';
			    	 	 html +='<label>';
			    	 	 html +='<input data-fullname="'+response.data[i].fullname+'" type="checkbox" name="userIdMess" value="'+response.data[i].id+'"/><span>'+response.data[i].fullname+'</span>';
			    	 	 html +='<label>';
			    	 	 html +='</div>';
			    		};
			    	 
			     }
			     $(".list_user").html(html);
			     $('.check input').uniform('refresh');
			    if(callback) {
			    	callback();
			    };
		   	}
		});
	}
	
	$('.btn-add-user-send-message').on("click", function(e){
		var html_old = $('#add-user-mess').html();
		$('#add-user-mess').html('');
		var html = '';
		$('input[name=userIdMess]:checked').each(function(i, elem) {
			var fullname = $(elem).data('fullname');
			
			
			var id = $(elem).val();
			if(global_list.indexOf(parseInt(id)) == -1) {
				console.log("fullname"+i+":"+fullname);
				global_list.push(parseInt(id));
				html = '<div class="data'+id+' classAdd"><span class="btn green pull-left" style="margin:0 5px 5px 0;" data-id-user='+id+'>'+
				'<span>'+fullname+'</span><i aria-hidden="true" class="fa fa-close mrl-10"></i>'+
		  		'</span></div>';
				console.log("global_list:"+global_list);
				console.log($('#add-user-mess').html());
				$('#add-user-mess').html(html+$('#add-user-mess').html());
			} 
			
		});
		
		var classAddLength = $('.classAdd').length;
		document.getElementById("totalEleSelect").innerHTML = classAddLength;
		
  		$('#modal').modal('toggle');
  		
	});
	
	// notice create
	var category_notice = $('.createSelect #category_notice').val();
	
	if (category_notice > 0) {
		render_dropdownCreate(category_notice);
	}

	$('.createSelect #category_notice').on('change', function() {
				var selected = $(this).find(
						"option:selected").val();
				render_dropdownCreate(selected);
	});
	
	
	function render_dropdownCreate(selected) {
		$.ajax({
			method : "POST",
			url : "/api/v1/public/ajax_get_companies_bussiness_insurance",
			data : {
				"category" : selected
			},
			success : function(rs) {
				if (rs.status == 200) {
					$(".select_notice").html('');
					var dataBusinessId = $('.select_notice').attr('data-businessId');
					if (rs.data.list.length > 0) {
						var tmpl_data = [];
						tmpl_data['list'] = [];
						$(rs.data.list).each(function(i, item) {
							if(item.id == dataBusinessId) {
								item['selected'] = 'selected';
							}else{
								item['selected'] = '';
							}
							tmpl_data['list'].push(item);
						});
						var render = Mustache.render($('#tmpl_01').html(), tmpl_data);
						$(".select_notice").append(render);

					}
					$('.selectpickermember').selectpicker('refresh');

				} else {
					alert("can not request !!!")
				}
			}
		})
	}
	$('.btn-create-notice').on("click", function(e) {
		var category_notice = $('#category_notice').find("option:selected").val();
		render_dropdownCreate(category_notice);
		var companies_cate = $('#companies_cate').find("option:selected").val();
		if (companies_cate == null) {
			companies_cate = 0;
		}
		var data_businessId = $('.select_notice').find("option:selected").val();
		if (data_businessId == null) {
			data_businessId = 0;
		}
		
		//var json = JSON.stringify(["android"]);
  		var data_isShow = $('#isShow_:checked').val();
		var data_title = $('#title_notice').val();
		var data_content = showHTML();
		var data_type = $('.member-main-top').attr('data-type');
		var dataAdminId = $('.member-main-top').attr('data-adminId');
		data_content = data_content.replace("<p><br></p>","");
		
		var showAndroid = false;
		var showIos = false;
		var showWeb = false;
		var numOfChecked = $("input[type='checkbox']:checked").size();
		if (data_type == 1) {
			category_notice = $('#category_notice').find("option:selected").val();
			//var data_osType = ($('input[name=osType]:checked').map(function() {return $(this).val();})).get();
	  		//json = JSON.stringify(data_osType);
			showAndroid = $("input[name='showAndroid']").val();
			showIos = $("input[name='showIos']").val();
			showWeb = $("input[name='showWeb']").val();
			
		} else {
			category_notice = $('#id_').val();
		}
		
		if ((numOfChecked > 0)&&(data_title.length > 0)&&(data_content.length > 0)) {
			$('#popup-confirm-create-notice').modal('show');
			$('.btn-save-notice-confirm').on("click", function(e) {
				$.ajax({
					method : "POST",
					url : "/api/v1/public/ajax_create_notice",
					data : {
						"category_notice" : category_notice,
						"companies_cate" : companies_cate,
						"data_businessId" : data_businessId,
						//"json" : json,
						"data_isShow" : data_isShow,
						"data_title" : data_title,
						"data_content" : data_content,
						"data_type" : data_type,
						"dataAdminId" : dataAdminId,
						"showAndroid" : showAndroid,
						"showIos" : showIos,
						"showWeb" : showWeb
					},
					success : function(rs) {
						if (rs.status == 200) {
							if (data_type == 1) {
								window.location = "/admin/cs/notice/list?type=1";
							} else {
								window.location = "/admin/cs/notice/list?type=2";
							}
							
						} else {
							alert("can not request !!!")
						}
					}
				})
			});
		} else {
			if (data_type == 1) {
				if(numOfChecked == 0) {
					alert("입력되지 않은 항목이 있습니다.[노출 채널]");
				} else if (data_title.length == 0) {
					alert("입력되지 않은 항목이 있습니다.[제목]");
				} else if (data_content.length == 0) {
					alert("입력되지 않은 항목이 있습니다.[내용]");
				} else {
					alert("입력되지 않은 항목이 있습니다.");
				}
			} else {
				if (data_title.length == 0) {
					alert("입력되지 않은 항목이 있습니다.[제목]");
				} else if (data_content.length == 0) {
					alert("입력되지 않은 항목이 있습니다.[내용]");
				} else {
					alert("입력되지 않은 항목이 있습니다.");
				}
			}
		}
	});
	
	
	(function($) {
		function inputuploadimg() {
	        if (!$('.tr-file-upload.image-upload').length) return;

	        $('.tr-file-upload.image-upload').each(function() {
	          var fileUpload = $(this);
	          var showFileInfo = fileUpload.find('.show-file-info');
	          var inputFile = fileUpload.find('.input-file');
	          var previewImage = fileUpload.find('.preview-image > img');
	          var previewImageBg = fileUpload.find('.preview-image > .image-bg');

	          previewImage.attr('src') == '' ? previewImage.closest('.preview-image').addClass('no-image') : previewImage.closest('.preview-image').removeClass('no-image');
	          previewImageBg.attr('style') == undefined ? previewImageBg.closest('.preview-image').addClass('no-image') : previewImageBg.closest('.preview-image').removeClass('no-image');
	          
	          var readURL = function(input) {
	            if (input.files && input.files[0]) {
	              var reader = new FileReader();

	              reader.onload = function(e) {
	                previewImage.attr('src', e.target.result);
	                previewImageBg.attr('style', 'background-image: url("' + e.target.result + '");');
	              }

	              reader.readAsDataURL(input.files[0]);
	            }
	          };
	          inputFile.on('change', function() {
	            var thisInputFile = $(this);
	            var file = thisInputFile[0].files[0];
	            var fileName = file.name;
	            // alert(fileName);
	            showFileInfo.empty().text(fileName);
	          });

	          fileUpload.find('.input-file').on('change', function() {
	            readURL(this);
	          });
	        });
	    }
		
		// notice edit
		var category_notice_edit = $('.editSelect #category_notice').val();
		if (category_notice_edit > 0) {
			render_dropdown_edit(category_notice_edit);
		}

		$('.editSelect #category_notice').on('change',
				function() {
					var selected = $(this).find("option:selected").val();
					render_dropdown_edit(selected);
				});

		function render_dropdown_edit(selected) {
			$.ajax({
				method : "POST",
				url : "/api/v1/public/ajax_get_companies_bussiness_insurance",
				data : {
					"category" : selected
				},
				success : function(rs) {
					if (rs.status == 200) {
						$(".select_notice").html('');
						var dataBusinessId = $('.select_notice').attr('data-businessId');
						if (rs.data.list.length > 0) {
							var tmpl_data = [];
							tmpl_data['list'] = [];
							$(rs.data.list).each(function(i, item) {
								if(item.id == dataBusinessId) {
									item['selected'] = 'selected';
								}else{
									item['selected'] = '';
								}
								tmpl_data['list'].push(item);
							});
							var render = Mustache.render($('#tmpl_01').html(), tmpl_data);
							$(".select_notice").append(render);

						}
						$('.selectpickermember').selectpicker('refresh');

					} else {
						alert("can not request !!!")
					}
				}
			})
		}
		
		$('.btn-edit-notice').on("click", function(e) {
			var category_notice = $('#category_notice').find("option:selected").val();
			var companies_cate = $('#companies_cate').find("option:selected").val();
			if (companies_cate == null) {
				companies_cate = 0;
			}
			
			var data_businessId = $(this).attr('data-businessId');
			if (data_businessId == null) {
				data_businessId = 0;
			}
			
	  		//var json = JSON.stringify(["android"]);
	  		var data_isShow = $('#isShow_:checked').val();
			
			var data_title = $('#title_notice').val();
			var data_content = showHTML();
			var data_noticeId = $(this).attr('data-noticeId');
			var data_type_noticeId = $(this).attr('data-type-noticeId');
			
			var showAndroid = false;
			var showIos = false;
			var showWeb = false;
			var numOfChecked = $("input[type='checkbox']:checked").size();
			
			if (data_type_noticeId == 1) {
//				var data_osType = ($('input[name=osType]:checked').map(function() {return $(this).val();})).get();
//		  		json = JSON.stringify(data_osType);
		  		showAndroid = $("input[name='showAndroid']").val();
				showIos = $("input[name='showIos']").val();
				showWeb = $("input[name='showWeb']").val();
			}
			data_content = data_content.replace("<p><br></p>","");
			
			if ((numOfChecked > 0)&&(data_title.length > 0)&&(data_content.length > 0)) {
				$('#popup-confirm-edit-notice').modal('show');
				$('.btn-save-notice-confirm').on("click", function(e) {
					$.ajax({
						method : "POST",
						url : "/api/v1/public/ajax_edit_notice",
						data : {
							"category_notice" : category_notice,
							"companies_cate" : companies_cate,
							"data_businessId" : data_businessId,
							//"json" : json,
							"data_isShow" : data_isShow,
							"data_title" : data_title,
							"data_content" : data_content,
							"data_noticeId" : data_noticeId,
							"data_type_noticeId" : data_type_noticeId,
							"showAndroid" : showAndroid,
							"showIos" : showIos,
							"showWeb" : showWeb
						},
						success : function(rs) {
							if (rs.status == 200) {
								if (data_type_noticeId == 1) {
									window.location = "/admin/cs/notice/list?type=1";
								} else {
									window.location = "/admin/cs/notice/list?type=2";
								}
							} else {
								alert("can not request !!!")
							}
						}
					})
				});
			} else {
				if (data_type_noticeId == 1) {
					if(numOfChecked == 0) {
						alert("입력되지 않은 항목이 있습니다.[노출 채널]");
					} else if (data_title.length == 0) {
						alert("입력되지 않은 항목이 있습니다.[제목]");
					} else if (data_content.length == 0) {
						alert("입력되지 않은 항목이 있습니다.[내용]");
					} else {
						alert("입력되지 않은 항목이 있습니다.");
					}
				} else {
					if (data_title.length == 0) {
						alert("입력되지 않은 항목이 있습니다.[제목]");
					} else if (data_content.length == 0) {
						alert("입력되지 않은 항목이 있습니다.[내용]");
					} else {
						console.log(123);
						alert("입력되지 않은 항목이 있습니다.");
					}
				}
				
			}
		});
		
		
		
		
	    // slide banner

//	    $(window).on('load', function() {
//	      addpanner();
//	    });

	    // intro page banner
	    function addintropage() {
	      $('#intro-page1').on('change', function(){
	        var minditem = $('#intro-page1').selectpicker('val');
	        var testimonials= $('.content-mind');
	          for (var i = 0; i < 5; i++) {
	            if(i< minditem){
	            $(testimonials[i]).html('<div class="item-upload"><div class="inputfile"><div class="tr-file-upload image-upload"><div class="preview-image"><img src="/theme/admin/assets/images/demo_single_2.jpg" alt="thumbnail"/></div><label class="btn green btn-select-file">찾아보기<input type="file" class="hidden input-file"/></label><div class="input-icon input-large input-inline right"><span class="show-file-info"></span></div></div></div></div>');
	            inputuploadimg(); 
	            }else{
	               $(testimonials[i]).html('');
	            }
	          }
	    
	      });
	    }
	    
	    
	    
	    
	    $(window).on('load', function() {
	      addintropage();
	    });
	    
	    // faq create
	    $('.confirm-create-faq-member').on("click" , function(){ 
			var data_isShow = $('#isShow_:checked').val();
			var data_osType = ($('input[name=osType]:checked').map(function() {return $(this).val();})).get();
			json = JSON.stringify(data_osType);
			var data_title = $('#title_faq').val();
			var data_content = showHTML();
			var dataAdminId = $(this).attr('data-adminId')
			data_content = data_content.replace("<p><br></p>","");
			if ((json.length != 2)&&(data_title.length > 0)&&(data_content.length > 0)) {
				$('#popup-create-faq-member').modal('show');
				$('.btn-create-faq-member-confirm').on("click", function(e) {
					$.ajax({
						method : "POST",
						url : "/api/v1/public/ajax_create_faq_member",
						data : {
							"data_isShow" : data_isShow,
							"json" : json,
							"data_title" : data_title,
							"data_content" : data_content,
							"dataAdminId" : dataAdminId
						},
						success : function(rs) {
							if (rs.status == 200) {
								window.location = "/admin/cs/faq/list?type=1";
							} else {
								alert("can not request !!!")
							}
						}
					})
				});
			} else {
				if(json.length == 2) {
					alert("입력되지 않은 항목이 있습니다.[노출 OS]");
				} else if (data_title.length == 0) {
					alert("입력되지 않은 항목이 있습니다.[제목]");
				} else if (data_content.length == 0) {
					alert("입력되지 않은 항목이 있습니다.[내용]");
				} else {
					alert("입력되지 않은 항목이 있습니다.");
				}
			}
		});
	    
	    // faq edit
	    $('.confirm-edit-faq-member').on("click", function(e) {
			var data_faqId = $(this).attr('data-faqId');
			var data_isShow = $('#isShow_:checked').val();
			var data_osType = ($('input[name=osType]:checked').map(function() {return $(this).val();})).get();
	  		json = JSON.stringify(data_osType);
	  		var data_title = $('#title_faq').val();
			var data_content = showHTML();
			data_content = data_content.replace("<p><br></p>","");
			if ((json.length != 2)&&(data_title.length > 0)&&(data_content.length > 0)) {
				$('#popup-edit-faq-member').modal('show');
				$('.btn-save-faq-member-confirm').on("click", function(e) {
					$.ajax({
						method : "POST",
						url : "/api/v1/public/ajax_edit_faq_member",
						data : {
							"data_faqId" : data_faqId,
							"data_isShow" : data_isShow,
							"json" : json,
							"data_title" : data_title,
							"data_content" : data_content
						},
						success : function(rs) {
							if (rs.status == 200) {
								window.location = "/admin/cs/faq/list";
							} else {
								alert("can not request !!!")
							}
						}
					})
				});
			} else {
				if(json.length == 2) {
					alert("입력되지 않은 항목이 있습니다.[노출 OS]");
				} else if (data_title.length == 0) {
					alert("입력되지 않은 항목이 있습니다.[제목]");
				} else if (data_content.length == 0) {
					alert("입력되지 않은 항목이 있습니다.[내용]");
				} else {
					alert("입력되지 않은 항목이 있습니다.");
				}
			}
	  		
		});
	    
	    
	})(jQuery);
});



