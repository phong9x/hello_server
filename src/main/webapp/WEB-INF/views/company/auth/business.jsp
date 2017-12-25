<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/company/layout/include.jsp" %>
<%@ taglib uri="http://www.springframework.org/tags" 	prefix="s" %>
<div class="page-content-wrapper business-detail">
<div class="page-content">
<div class="container">
            <div class="main-body-content member-page">
              <div class="page-bar">
                <ul class="page-breadcrumb">
                  <li><a href="" class="home"> <i class="fa fa-home"></i>홈</a></li>
                  <li><a href="">회원 관리</a></li>
				  <li><a href="">회원 정보</a></li>
                </ul>
              </div>
              <!-- .page-bar-->
              <div class="member-main-content">
                <div class="tabbable-custom">
                  <div class="tab-content">
 					<div id="tab-12" class="tab-10 tab-pane active">
                      <div class="portlet box preview-mobile">
                        <div class="portlet-body">
                        <div class="body-preview" style=" margin-top: 88px;">
                            <div class="preview-body">
                              <div class="preview-logo"></div>
                              <div class="preview-ctbox"></div>
                              <div class="preview-contetnt"><div id="description_page"></div></div>
                            </div>
                          </div>
                          <form method="post" id="form_business_promotion" class="form-horizontal preview" enctype="multipart/form-data">
                          <input type="hidden" name="idPromotionPage"  value="${item.id }" class="form-control"/>
                          <input type="hidden" name="id"  value="${item.id }" class="form-control"/>
                          <div class="form-title"><span>홍보 페이지 편집</span>
                              <button type="button" data-trams-confirm-popup="#popupResetPromotion" class="btn btn-open-popup trams-need-confirm green pull-right">초기화</button>
                          </div>
                            <div class="form-title">
                            	<div class="text_title" style="font-weight: bold;margin-bottom: 19px;">기업 페이지 편집</div>
                            	<div class="text_default_show" style="">
                            		<p>ㆍ 기업 페이지는 어플리케이션 시작 시 해당 기업에 소속된 회원에게만 보여집니다. </p>
                            		<p>ㆍ 편집 후에는 이미지 깨짐 현상이나 링크 오류 등을 검수한 후 노출되오니 이 점 참고 부탁 드립니다.</p>
                            		<p>ㆍ 검수는 영업일 기준 약 3일 내로 진행됩니다.</p>
                            	</div>
                            </div>
                            
                            
                            <div class="form-body">
                              <div style="margin-bottom: 25px;" class="form-group">
                                <div class="item-group item2">
                                  <label class="control-label col-md-4">로고 이미지 :
                                  </label>
                                  <div class="col-md-8 ct">
                                    <div class="inputfile">
                                    
                                      <div class="tr-file-upload image-upload">
                                        <div class="preview-image"><img src="${item.logoUrl}" /></div>
                                        <label class="btn green btn-select-file">찾아보기
                                          <input type="file" name="logoFile" class="hidden input-file" accept=".png, .jpg, .git"/>
                                        </label>
                                        <div class="input-icon input-large input-inline right"></div>
                                        <div class="note-img">* JPG, GIF, PNG (200kbye 이하)</div>
                                      </div>
                                      
                                    </div>
                                  </div>
                                </div>
                              </div>
                              <div style="margin-bottom: 25px;" class="form-group">
                                <div class="item-group item3">
                                  <label class="control-label col-md-4"><span>문구 ( </span><span class="total-input"></span><span>) :</span>
                                  </label>
                                  <div class="col-md-8 ct add-textbox">
                                    <div class="input-box">
                                    <c:set value="1" var="index"></c:set>
                                      <c:forEach items="${list_link}" var="i">
                                      		<input type="text" id="title${index}" name="title" placeholder="문구를 입력하세요" value="${i.title }" class="form-control"/>
                                      		<input type="text" id="link${index}" name="link"   placeholder="링크를 입력하세요" value="${i.link }" class="form-control"/>
                                      	<c:set value="${index+1}" var="index"></c:set>
                                      </c:forEach>
                                    </div>
                                    <div class="item-button">
                                      <button type="button" class="btn green add-input">+ 추가 </button><span class="note-button">* 최대 10개까지 등록 가능</span>
                                    </div>
                                  </div>
                                </div>
                              </div>
                              <div class="form-group">
                                <div class="item-group item4">
                                  <label class="control-label col-md-4">배경 이미지 :
                                  </label>
                                  <div class="col-md-8 ct">
                                    <div class="inputfile">
                                      <div class="tr-file-upload image-upload">
                                        <div class="preview-image"><img src="${item.backgroundUrl}" /></div>
                                        <label class="btn green btn-select-file">찾아보기
                                          <input type="file" name="backgroundFile" class="hidden input-file" accept=".png, .jpg, .git"/>
                                        </label>
                                        <div class="input-icon input-large input-inline right"></div>
                                        <div class="note-img">* JPG, GIF, PNG (3MB 이하)
                                          <p>* 1920 x 1080 px 사이즈에 최적화</p>
                                        </div>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="form-footer" style="text-align: center;">
                              <button type="button" onclick="checkFormPromotionPage()" class="btn green ">저장 </button>
                            </div>
                          </form>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <!-- .block-1-->
            </div>
            <!-- .member-main-->
          </div>
          </div>
          </div>
<div id="popupResetPromotion" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true" class="modal fade">
      <div class="modal-dialog">
      <form method="post" action="/company/auth/business/reset">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
          </div>
          <div class="modal-body">
            <h4><strong>
            	초기화하시면 복구 불가능합니다. 초기화하시겠습니까? 
            </strong>
            </h4>
          </div>
          <div class="modal-footer" style="text-align: center">
            <button name="resetPromotion" value="1" type="submit" class="btn red"><s:message code="button.confirm" /></button>
            <button type="button" data-dismiss="modal" class="btn btn-default"><s:message code="button.cancel" /></button>
          </div>
        </div>
        </form>
      </div>
    </div>  
    <div id="popupConfirmSavePromotion" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
          	<h4 class="modal-title"><strong>안내</strong></h4>
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
          </div>
          <div class="modal-body">
            <h4><strong>저장하시겠습니까?</strong>
              <!-- Are you sure you want to delete?-->
            </h4>
          </div>
          <div class="modal-footer">
          	<button type="button" onclick="$('#form_business_promotion').submit();" class="btn red"><s:message code="button.confirm" /></button>
            <button type="button" data-dismiss="modal" class="btn btn-default"><s:message code="button.cancel" /></button>
          </div>
        </div>
      </div>
    </div>
     <SCRIPT type="text/javascript">
    	function checkFormPromotionPage(){
    		var logoOldUrl = '${item.logoUrl}';
    		var backgroundOldUrl = '${item.backgroundUrl}';
    		var linkUrl = $("input[name=linkUrl]").val();
    		var logoUrl = $("input[name=logoUrl]").val();
    		var backgroundUrl = $("input[name=backgroundUrl]").val();
    		var decription = $('#decription').val();
    		
 	    			var required_text='';
 	    			if(logoOldUrl == null & logoUrl == '' ){
 	    				required_text = required_text+"<p>[기업 로고]</p>";
 	        		}
 	    			if(backgroundOldUrl == null & backgroundUrl == ''){
 	    				required_text = required_text+"<p>[배경 이미지]</p>";
 	        		}
 	    			if(decription == ''){
 	    				required_text = required_text+"<p>[문구 ]</p>";
 	        		}
 	    			if(required_text != ''){
 	    				$('#required_message').html(required_text);
     	    			$('#popupRequiredInput').modal('show');
     	    			required_text='';
 	    			}else{
 	    				console.log(222);
 	    				$('#popupConfirmSavePromotion').modal('show');
 	    			}
    	
    		
    		
    	}
    </SCRIPT>


