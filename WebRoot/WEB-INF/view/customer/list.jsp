<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../assets/pages/head.jsp"%>
<link href="${rootPath }assets/css/jquery.datatables.css" rel="stylesheet">
</head>

<body>
	<%@ include file="../assets/pages/preloader.jsp"%>
	<section>
		<%@ include file="../assets/pages/leftpanel-manager.jsp"%>
		<div class="mainpanel">
			<%@ include file="../assets/pages/headerbar.jsp"%>
			<div class="pageheader">
				<h2>
					<i class="fa fa-address-book"></i> 会员管理 <span>会员列表</span>
				</h2>
			</div>
			<div class="contentpanel">
				<!-- content goes here... -->

				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="panel-btns">
							<a href="" class="minimize">&minus;</a>
						</div>
						<!-- panel-btns -->
						<h3 class="panel-title">会员列表</h3>
						<div class="row" style="margin-top: 20px">
						</div>
					</div>
				<div class="panel-body">
					<br />
					<div class="table-responsive">
						<table id="dataTable" class="table">
							<thead>
								<tr>
									<th>ID</th>
									<th>昵称</th>
									<th>openId</th>
									<th>性别</th>
									<th>国家</th>
									<th>省份</th>
									<th>城市</th>
									<th>状态</th>
									<th>注册时间</th>
									<th>操作</th>
								</tr>
							</thead>								
						</table>
					</div>
					<!-- table-responsive -->
				</div>
				<!-- panel-body -->
				</div>
				<!-- panel -->

			</div>

		</div>
		<!-- mainpanel -->
		<%@ include file="../assets/pages/rightpanel.jsp"%>
	</section>

	<%@ include file="../assets/pages/foot.jsp"%>
	<script src="${rootPath}assets/js/jquery.datatables.min.js"></script>
	<script src="${rootPath}assets/js/select2.min.js"></script>
	<script src="${rootPath}assets/js/jquery-ui-1.10.3.min.js"></script>
	<script src="${rootPath}assets/js/jquery.validate.min.js"></script>
	<script src="${rootPath}assets/js/datepicker-zh-CN.js"></script>
	<script src="${rootPath}assets/js/datetimepicker-cn.js"></script>
	<script src="${rootPath}assets/js/jquery.messager.js"></script>
	
<div class="nextModal modal fade" id="secondModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
		<div class="modal-dialog">
			<div class="modal-content">
			   <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<div class="nextModal-title">提示信息</div>
			   </div>
				<div class="modal-body align-center" style="text-align:center">
							  文件格式不正确，请选择xls格式上传！
			   </div>
		   </div>
	    </div>
	</div>
<!-- Modal -->
<div class="modal fade" id="confirmDelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel"><span class="fa fa-warning"></span> 提示</h4>
      </div>
      <div class="modal-body">
        确定删除么？
      </div>
      <div class="modal-footer">
      	<input type="hidden" class="hiddenId" value="" />
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-danger">删除</button>
      </div>
    </div><!-- modal-content -->
  </div><!-- modal-dialog -->
</div><!-- modal -->
<!-- Modal -->
<div class="modal fade" id="laheiModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel"><span class="fa fa-warning"></span> 提示</h4>
      </div>
      <div class="modal-body">
        确定拉黑么？
      </div>
      <div class="modal-footer">
      	<input type="hidden" class="hiddenId" value="" />
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-danger">删除</button>
      </div>
    </div><!-- modal-content -->
  </div><!-- modal-dialog -->
</div><!-- modal -->


	<script type="text/javascript">
	jQuery("#searchStartDateTime").datepicker({
        dateFormat: "yy-mm-dd",
        changeYear: true,
        changeMonth: true,
     });
    
    jQuery("#searchEndDateTime").datepicker({
        dateFormat: "yy-mm-dd",
        changeYear: true,
        changeMonth: true,
     });
	
		jQuery(document).ready(function() {
			$(".nav-parent").eq(2).addClass("nav-active");
      		$(".nav-parent").eq(2).find(".children").show();
			
			var t = jQuery('#dataTable').DataTable({
				searching:false,
				pageLength: 10,
				processing: true,
				language: datatable_local_language, // my.js
				serverSide: true,
				stateSave:true,
				ajax: {
					url: '${rootPath}customer/customerList.do',
					dataFilter: function(data){
			            var json = jQuery.parseJSON( data );
			            json.recordsTotal = json.countTotal;
			            json.recordsFiltered = json.countFiltered;
			            json.data = json.data;
			            return JSON.stringify( json );
			        }
				}, 
				columnDefs: [
						{
						    data: "id",
						    orderable: false,
						    render: function ( data, type, full, meta ) {
						        return '<div>' + data +'</div>';
						    },
						    targets: 0
						},
						{
						    data: "nickName",
						    orderable: false,
						    render: function ( data, type, full, meta ) {
						        return '<div>' + data +'</div>';
						    },
						    targets: 1
						},
						{
						    data: "openId",
						    orderable: false,
						    render: function ( data, type, full, meta ) {
						        return '<div>' + data +'</div>';
						    },
						    targets: 2
						},
						{
						    data: "gender",
						    orderable: false,
						    render: function ( data, type, full, meta ) {
						    	if (data == 0) {
						        	return '<div>' + '未知' +'</div>';
						    	}
						    	if (data == 1) {
						        	return '<div>' + '男' +'</div>';
						    	}
						    	if (data == 2) {
						        	return '<div>' + '女' +'</div>';
						    	}
						    },
						    targets: 3
						},
						{
						    data: "country",
						    orderable: false,
						    render: function ( data, type, full, meta ) {
						        return '<div>' + data +'</div>';
						    },
						    targets: 4
						},
						{
						    data: "province",
						    orderable: false,
						    render: function ( data, type, full, meta ) {
						        return '<div>' + data +'</div>';
						    },
						    targets: 5
						},
						{
						    data: "city",
						    orderable: false,
						    render: function ( data, type, full, meta ) {
						        return '<div>' + data +'</div>';
						    },
						    targets: 6
						},
						{
						    data: "isValid",
						    orderable: false,
						    render: function ( data, type, full, meta ) {
						    	if (data == 0) {
						    		return '<div>' + '拉黑' +'</div>';
						    	}
						        return '<div>' + '正常' +'</div>';
						    },
						    targets: 7
						},
						{
			                  data: "createTime",
			                  orderable: false,
			                  render: function ( data, type, full, meta ) {
			                	  var n = full.createTime.time;
				                	if(full.createTime){
				                		n=new Date(n).format("yyyy-MM-dd hh:mm:ss");
				                	}else{
				                		n="";
				                	}
				                return n;
			                  },
			                  targets: 8
						  },
						  
						  {
			                  data: "id",
			                  orderable: false,
			                  render: function ( data, type, full, meta ) {
		                	  	var n = '<a class="btn btn-default btn-xs" id="' + data + '"><span class="fa fa-edit"></span> 拉黑</a>&nbsp;<a class="btn btn-danger btn-xs" id="' + data + '"><span class="fa fa-edit"></span> 删除</a>&nbsp;';
	                		  	return n;
			                  },
			                  targets: 9
						  },
				    {
					  orderable: false,
					  searchable: false,
				    },
				],
				columns: [
		            { data: "id" },
		            { data: "nickName" },
		            { data: "openId" },
		            { data: "gender" },
		            { data: "country" },
		            { data: "province" },
		            { data: "city" },
		            { data: "isValid" },
		            { data: "createTime" },
		            { data: "id" }
		        ]
			});
			
			if($('#searchFlag').val()=="restart"){
				t.ajax.reload();
			}
		  	$('#searchBtn').on( 'click', function () {
		  		var customerId=$("#searchCustomerId").val();
		  		if(isNaN(customerId)){
		  			msg("提示信息","请正确输入客人Id！");
		  		}else{
		  		//通知后台，使用界面的条件来重绘table
				$('#searchFlag').val("restart");
		        t.draw();
		        }
		    } );
		  	
			$('#dataTable tbody').on( 'click', 'a.btn-success', function () {
		        var data = t.row($(this).parents('tr')).data();
		        edit($(this).attr('id'));
		    } );
			
			$('#dataTable tbody').on( 'click', 'a.btn-default', function () {
		        var data = t.row($(this).parents('tr')).data();
		        lahei($(this).attr('id'));
		    } );

			$('#dataTable tbody').on( 'click', 'a.btn-danger', function () {
		        var data = t.row($(this).parents('tr')).data();
		        del($(this).attr('id'));
		    } );
			
			$('#confirmDelModal').on( 'click', 'button.btn-danger', function () {
		        var id = $("#confirmDelModal .hiddenId").val();
		        doDel(id);
		    } ); 
			
			$('#laheiModal').on( 'click', 'button.btn-danger', function () {
		        var id = $("#laheiModal .hiddenId").val();
		        doLahei(id);
		    } ); 
		    
			// Select2
		    jQuery('select').select2({
		        minimumResultsForSearch: -1
		    });
		    	    
		    jQuery('select').removeClass('form-control');		
		});
		
		function edit(id) {
			window.parent.location = "${rootPath}customer/edit.html?id="+id;
		}
		
		function lahei(id) {
			$("#laheiModal .hiddenId").val("");
			$("#laheiModal .hiddenId").val(id);
			$("#laheiModal").modal('show');
		}
		
		function del(id) {
			$("#confirmDelModal .hiddenId").val("");
			$("#confirmDelModal .hiddenId").val(id);
			$("#confirmDelModal").modal('show');
		}
		
		function doDel(id){
			$.ajax({
				url: "${rootPath}customer/delCustomer.do?id=" + id, 
				async: true,
				success: function(o) {
					window.location.reload();
				},
				error: function(o) {
					alert(2);
				}
			});			
		}
		function doLahei(id){
			$.ajax({
				url: "${rootPath}customer/notValid.do?id=" + id, 
				async: true,
				success: function(o) {
					window.location.reload();
				},
				error: function(o) {
					alert(2);
				}
			});			
		}
		 function checkfile(param){  
		        var fileSize = param.files[0].size/1024/1024;
		        var file = $("#file").val();
		        var sp = file.lastIndexOf(".");
		        var sufix = file.substring(sp+1).toLowerCase();
		        if(sufix!="xls"){
		            $("#secondModal").modal('show'); 
		        } 
		    }  
	</script>
	<script>
$(document).ready(function () {
	$(".thismonth").click(function () {
  		$(this).siblings().removeClass("active");
  		$(this).addClass("active");

  		var today = new Date();
  		var year = today.getFullYear();
  		var month= today.getMonth()+1;//本月
  		var day = today.getDate();
  		 if(month<10){
  	  		$("#searchStartDateTime").val(year + "-"+"0"+ month + "-"+"0"+ 1);
  	        }else{
  	        $("#searchStartDateTime").val(year + "-"+ month + "-"+"0"+ 1);
  	        }
  		 
  		 if(month<10&&day<10){
  			 $("#searchEndDateTime").val(year + "-"+"0"+ month + "-"+"0" + day);
  	       }else if(month>=10&&day>=10){
  	    	 $("#searchEndDateTime").val(year + "-"+ month + "-" + day);
  	       }else if(month>10&&day<10){
  	    	 $("#searchEndDateTime").val(year + "-"+ month + "-"+"0" + day);
  	       }else if(month<10&&day>10){
  	    	 $("#searchEndDateTime").val(year + "-"+"0"+ month + "-" + day);
  	       }
  	});
  	$(".lastmonth").click(function () {
  		$(this).siblings().removeClass("active");
  		$(this).addClass("active");

  		var today = new Date();
  		var year = today.getFullYear();
  		var month= today.getMonth();//上月
  		var day = today.getDate();

  		var myDate = new Date(year, month, 0);
		//上个月的最后一天
  		if(month<10){
        	if(month==0){
        		$("#searchStartDateTime").val(year-1 + "-"+"12" + "-"+"0"+ 1);
        	}else{
        		$("#searchStartDateTime").val(year + "-"+"0" + month + "-"+"0"+ 1);
        	}
        }else{
        	$("#searchStartDateTime").val(year + "-"+ month + "-"+"0"+ 1);
        }
		if(month<10){
			if(month==0){
        		$("#searchEndDateTime").val(year-1 + "-"+"12"+ "-" + myDate.getDate());
        	}else{
        		$("#searchEndDateTime").val(year + "-"+"0"+ month + "-" + myDate.getDate());
        	}
		}else{
			$("#searchEndDateTime").val(year + "-"+ month + "-" + myDate.getDate());
		}
  	});
  	$(".thishalfyear").click(function () {
  		$(this).siblings().removeClass("active");
  		$(this).addClass("active");

  		var today = new Date();
  		var year = today.getFullYear();
  		var year1 = year;
  		var month= today.getMonth()+1;//本月
  		if(month<7){
  			year1 = year1-1;
  		}
  		var day = today.getDate();
  		/* var mymonth = 12+month-6; */
  		var curDate = (new Date()).getTime();
  	    var halfYear = 365 / 2 * 24 * 3600 * 1000;
  	    var pastResult = curDate - halfYear;  // 半年前的时间（毫秒单位）
  	    var pastDate = new Date(pastResult),
  	        pastYear = pastDate.getFullYear(),
  	        pastMonth = pastDate.getMonth() + 1,
  	        pastDay = pastDate.getDate();
  	    
  	    if(pastMonth<10&&pastDay<10){
 	  		$("#searchStartDateTime").val(pastYear + "-"+"0"+ pastMonth + "-"+"0" + pastDay);
 	       }else if(pastMonth>=10&&pastDay>=10){
 	    	$("#searchStartDateTime").val(pastYear +"-"+ pastMonth + "-" + pastDay); 
 	       }else if(pastMonth>10&&pastDay<10){
 	    	 $("#searchStartDateTime").val(pastYear +"-"+ pastMonth + "-"+"0"+ pastDay); 
 	       }else if(pastMonth<10&&pastDay>10){
 	    	 $("#searchStartDateTime").val(pastYear +"-"+"0"+ pastMonth + "-" + pastDay); 
 	       }
  		 
  		   if(month<10&&day<10){
  			 $("#searchEndDateTime").val(year + "-"+"0"+ month + "-"+"0" + day);
  	       }else if(month>=10&&day>=10){
  	    	 $("#searchEndDateTime").val(year + "-"+ month + "-" + day);
  	       }else if(month>10&&day<10){
  	    	 $("#searchEndDateTime").val(year + "-"+ month + "-"+"0" + day);
  	       }else if(month<10&&day>10){
  	    	 $("#searchEndDateTime").val(year + "-"+"0"+ month + "-" + day);
  	       }
  	});
  	$(".alltime").click(function () {
  		$(this).siblings().removeClass("active");
  		$(this).addClass("active");

  		var today = new Date();
  		var year = today.getFullYear();
  		var year1 = year;
  		var month= today.getMonth()+1;//本月
  		// if(month<7){
  		// 	year1 = year1-1;
  		// }
  		var day = today.getDate();
  		//var mymonth = 12+month-6;

  		$("#searchStartDateTime").val(2016 + "-" +"0"+ 1 + "-" +"0"+ 1);
  		 if(month<10&&day<10){
  			 $("#searchEndDateTime").val(year + "-"+"0"+ month + "-"+"0" + day);
  	       }else if(month>=10&&day>=10){
  	    	 $("#searchEndDateTime").val(year + "-"+ month + "-" + day);
  	       }else if(month>10&&day<10){
  	    	 $("#searchEndDateTime").val(year + "-"+ month + "-"+"0" + day);
  	       }else if(month<10&&day>10){
  	    	 $("#searchEndDateTime").val(year + "-"+"0"+ month + "-" + day);
  	       }
  	});
 })
    function msg(title,context){
    		$.messager.lays(200, 100); //窗口大小
    		$.messager.anim('show',1000); 
    		$.messager.show(title, context,3000); //标题，内容，时间（秒）
    	}
  </script>
</body>
</html>
