<%@ page language="java" pageEncoding="utf-8"%> 
<%@ page contentType="text/html; charset=utf-8"%> 
<!DOCTYPE html>
<html lang="en">
<head>
	<style type="text/css">
        .img-div{
            border: 1px solid #ddd;
            border-radius: 100%;
            float: left;
            line-height: 1;
            margin-left: 5px;
            overflow: hidden;
        }
    </style>
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
					<i class="glyphicon glyphicon-cog"></i> 帖子管理 <span>发布须知</span>
				</h2>
			</div>

			<div class="contentpanel">
				<!-- content goes here... -->

		<div class="panel panel-default">
        <div class="panel-heading">
          <div class="panel-btns">
            <a href="" class="minimize">&minus;</a>
          </div>
          <h4 class="panel-title">发布须知内容</h4>
        </div>
        <form class="form-horizontal" id="form">
        <div class="panel-body panel-body-nopadding">
	        <div class="section-block">
	            <div class="form-group col-sm-4">
	              <label class="col-sm-4 control-label">内容 <span class="asterisk">*</span></label>
	              <div class="col-sm-8">
	                <textarea id="value" name="value" class="form-control" placeholder="" rows="15">${parameter.value}</textarea>
	              </div>
	            </div>
	        </div>
        </div><!-- panel-body -->
        
        <div class="panel-footer align-center">
			<input class="btn btn-primary" type="submit" value="保存"/>&nbsp;
			<input class="btn btn-default" type="button" id="btn-back" value="返回"/>
			<input type="hidden" name="parameterId" value="${parameter.parameterId}">
		</div><!-- panel-footer -->
     </form>   
      </div><!-- panel -->
			</div>

		</div>
		<!-- mainpanel -->
		<%@ include file="../assets/pages/rightpanel.jsp"%>
	</section>

	<%@ include file="../assets/pages/foot.jsp"%>
	<script src="${rootPath}assets/js/jquery.validate.min.js"></script>
	<script src="${rootPath}assets/js/select2.min.js"></script>
	
	<script type="text/javascript">
	jQuery(document).ready(function() {	
		jQuery("#form").validate({
			rules: {
				value: {
					required:true,
				},
			},
			messages: {
				value: {
					chinese:"请输入值",
				},
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
			var reg = '^[0-9]*$';
			return reg.test($("#chinese").val());
		}, "请输入数字");
		jQuery.validator.addMethod("isEnglish", function(value, element) {  
			var length = value.length;  
			return length <=32;    
		}, "请少于32字");
	});
			      
		function form_submit() {
			var f = $("#form").serialize();
			$.post('${rootPath}manager/editNoticeOfPublish.do', f, function(result) {
				var rmsg = result.msg;
				if (result.success) {
					window.parent.location = "${rootPath}manager/editNoticeOfPublish.html?id=${parameter.parameterId}";
				} else {
					$("#msgModal").modal('show');
				}
			}, "JSON");
			/* $.ajax({
			    url: '${rootPath}upload/filesUpload',
			    type: 'post',
			    dataType: 'multipart/form-data',
			    data: {
			    	filePath: JSON.stringify(arr),  //JSON.stringify() 方法用于将JavaScript值转换为 JSON 字符串，缺少此句传到后台的则为空值
			        //sumID: sumID  //datagrid未显示出来的那个字段，表中的三行的这个字段相同，都为1L，传递到后台供保存到数据库时使用
			    },
			    success: function(response) {  //后台返回的要显示的消息
			        $.messager.show({
			            title:'提示',
			            msg: response.msg
			        });
			    }
			}); */
		}
	</script>

</body>
</html>
