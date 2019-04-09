<%@ page language="java" pageEncoding="utf-8"%> 
<%@ page contentType="text/html; charset=utf-8"%> 
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../assets/pages/head.jsp"%>
</head>

<body>
	<%@ include file="../assets/pages/preloader.jsp"%>
	<section>
		<%@ include file="../assets/pages/leftpanel-manager.jsp"%>
		<div class="mainpanel">
			<%@ include file="../assets/pages/headerbar.jsp"%>
			<div class="pageheader">
				<h2>
					<i class="glyphicon glyphicon-cog"></i> 帖子管理 <span>新增帖子分类</span>
				</h2>
			</div>

			<div class="contentpanel">
				<!-- content goes here... -->

		<div class="panel panel-default">
        <div class="panel-heading">
          <div class="panel-btns">
            <a href="" class="minimize">&minus;</a>
          </div>
          <h4 class="panel-title">帖子分类基本信息</h4>
          <p>填写下表，完成分类创建。</p>
        </div>
        <form class="form-horizontal" id="form">
        <div class="panel-body panel-body-nopadding">
          
          
	        <div class="section-block">    
	            <div class="form-group col-sm-4" style="display:none;">
	              <label class="col-sm-4 control-label">作用域 <span class="asterisk">*</span></label>
	              <div class="col-sm-8">
	                <input type="text" id="domain" name="domain" class="form-control" value="publish.category"/>
	              </div>
	            </div>
	            <div class="form-group col-sm-4">
	              <label class="col-sm-4 control-label">顺序 <span class="asterisk">*</span></label>
	              <div class="col-sm-8">
	                <input type="text" id="value" name="value" placeholder="顺序（数字）" class="form-control" />
	              </div>
	            </div> 			
	        </div>
	        <div class="section-block"> 
	            <div class="form-group col-sm-4">
	              <label class="col-sm-4 control-label">分类名称 <span class="asterisk">*</span></label>
	              <div class="col-sm-8">
	                <input type="text" name="chinese" placeholder="中文（少于30字）" class="form-control" />
	              </div>
	            </div>    
	        </div>                                          
        </div><!-- panel-body -->
        
        <div class="panel-footer align-center">
			<input class="btn btn-primary" type="submit" value="保存"/>&nbsp;
			<input class="btn btn-default" type="button" id="btn-back" value="取消"/>
		</div><!-- panel-footer -->
     </form>   
      </div><!-- panel -->

			</div>

		</div>
		<!-- mainpanel -->
		<%@ include file="../assets/pages/rightpanel.jsp"%>
	</section>


<!-- Modal -->
<div class="modal fade" id="msgModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">信息</h4>
      </div>
      <div class="modal-body">
        保存数据时出错了
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div><!-- modal-content -->
  </div><!-- modal-dialog -->
</div><!-- modal -->


	<%@ include file="../assets/pages/foot.jsp"%>
	<script src="${rootPath}assets/js/jquery.validate.min.js"></script>
	
	<script type="text/javascript">
	jQuery(document).ready(function() {	

		$(".nav-parent").eq(9).addClass("nav-active");
      	$(".nav-parent").eq(9).find(".children").show();

		jQuery("#form").validate({
			rules: {
				value:{  
			         	required:true,  
			            remote:{                           
			            	url:"${rootPath}validate.do",
			            	type:"post",
			            	data: {
		                         table: function () { return "parameter"},
		                         field: function () { return "para_value"},
		                         filter_field: function () { return "para_domain"},
		                         filter_name: function () { return $("#domain").val();},
		                         name: function () { return $("#value").val();},
		                         }
			            },
			        	isValue:true,
			        },
				chinese: {
					required:true,
					isChinese:true,
				}
			},
			messages: {
				value: {
					required:"请输入值",
					remote:"该值已存在，请重新输入",
				},
				chinese: {
					required:"请输入中文",
				}
			},
		    highlight: function(element) {
		      jQuery(element).closest('.form-group').removeClass('has-success').addClass('has-error');
		    },
		    success: function(element) {
		      jQuery(element).closest('.form-group').removeClass('has-error');
		    },
		    invalidHandler : function(){
		      return false;
		    },
		    submitHandler : function(){
		      form_submit();
		      return false;
		    }
		  });
		$("#btn-back").click( function () {
			history.go(-1);
	    } ); 
		jQuery.validator.addMethod("isDomain", function(value, element) {  
			var length = value.length;  
			return length <=32;    
		}, "请少于32字");
		jQuery.validator.addMethod("isValue", function(value, element) {  
			var length = value.length;  
			return length <=512;    
		}, "请少于512字");
		jQuery.validator.addMethod("isDepict", function(value, element) {  
			var length = value.length;  
			return length <=30;    
		}, "请少于30字");
		jQuery.validator.addMethod("isChinese", function(value, element) {  
			var length = value.length;  
			return length <=30;    
		}, "请少于30字");
		jQuery.validator.addMethod("isEnglish", function(value, element) {  
			var length = value.length;  
			return length <=32;    
		}, "请少于32字");
	});
			      
		function form_submit() {
			var f = $("#form").serialize();
			$.post('${rootPath}parameter/add.do', f, function(result) {
				var rmsg = result.msg;
				if (result.success) {
					window.parent.location = "${rootPath}manager/publishCategoryList.html";
				} else {
					$("#msgModal").modal('show');
				}
			}, "JSON");
		}
	
		
	</script>

</body>
</html>
