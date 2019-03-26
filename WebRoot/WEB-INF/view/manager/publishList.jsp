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
					<i class="glyphicon glyphicon-cog"></i> 帖子管理 <span>帖子列表</span>
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
					<h3 class="panel-title">帖子列表</h3>
					<div class="row" style="margin-top: 20px">
							<div class="form-group col-sm-10">
								<div class="col-sm-2">
									<input type="text" id="mobilephone" class="form-control" placeholder="手机号"  value="" />
								</div>
								<div class="col-sm-2">
									<input type="text" id="searchStatus" name=""searchStatus"" class="publish-select-status fullwidth" value="" />
			                    </div>
			                    <div class="col-sm-2">
									<input type="text" id="searchCategory" name="searchCategory" class="publish-select-category fullwidth" value="" />
			                    </div>
								<div class="col-sm-2">
				                    <div class="input-group input-datepicker" style="padding:0;">
				                        <input readonly="readonly" id="searchStartDateTime" type="text" name="searchStartDateTime" class="form-control datepicker" placeholder="请点击输入查询开始日期" value="" autocomplete="on">
				                        <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
				                    </div>
				                </div>
			                    <div class="col-sm-2">
				                    <div class="input-group input-datepicker" style="padding: 0;">
				                        <input readonly="readonly" id="searchEndDateTime" type="text" name="searchEndDateTime" class="form-control datepicker" placeholder="请点击输入查询截止日期" value="" autocomplete="on">
				                        <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
				                    </div>
			                    </div>
								<div class="col-sm-2">
									<input class="btn btn-primary" type="button" id="searchBtn" value="搜索"/>
								</div> 	
							</div>	
						</div>
					</div>
					<div class="panel-body">
						<br />
						<div class="table-responsive">

							<table id="dataTable" class="table">
								<thead>
									<tr>
										<th>内容</th>
										<th>联系人</th>
										<th>联系方式</th>
										<th>状态</th>
										<th>类别</th>
										<th>创建时间</th>
										<th>更新时间</th>
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

<!-- Modal -->
<div class="modal fade" id="agreeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel"><span class="fa fa-warning"></span> 提示</h4>
      </div>
      <div class="modal-body">
        确定审核通过么？
      </div>
      <div class="modal-footer">
      	<input type="hidden" class="hiddenId" value="" />
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-danger">确认</button>
      </div>
    </div><!-- modal-content -->
  </div><!-- modal-dialog -->
</div><!-- modal -->

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
        <button type="button" class="btn btn-danger">确认</button>
      </div>
    </div><!-- modal-content -->
  </div><!-- modal-dialog -->
</div><!-- modal -->


	<script type="text/javascript">
		var category = ${publishCategory};
		var publishStatus = ${publishStatus};
		
		$(".publish-select-status").select2({
	 	     placeholder: '状态',
	 	     minimumResultsForSearch: Infinity,
	 	     data: publishStatus
	 	 });
		$(".publish-select-category").select2({
	 	     placeholder: '类别',
	 	     minimumResultsForSearch: Infinity,
	 	     data: category
	 	 });
		
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
			
			$(".nav-parent").eq(9).addClass("nav-active");
      		$(".nav-parent").eq(9).find(".children").show();
			var t = jQuery('#dataTable').DataTable({
				searching:false,
				pageLength: 10,
				processing: true,
				language: datatable_local_language, // my.js
				serverSide: true,
				ajax: {
					url: '${rootPath}manager/publishContentList.do',
					data: function(data){
						var mobilephone = $('#mobilephone').val();
						var searchStatus=$('#searchStatus').val();
						var searchCategory=$('#searchCategory').val();
						var searchStartTime = $('#searchStartDateTime').val();
						var searchEndTime = $('#searchEndDateTime').val();
						
						if(mobilephone != null && mobilephone !="" ){
							data.mobilephone = mobilephone;
			 			}
						if(searchStatus != null && searchStatus !="" ){
							data.status = searchStatus;
			 			}
						if(searchCategory != null && searchCategory !="" ){
							data.category = searchCategory;
			 			}
						if(searchStartTime != null && searchStartTime !="" ){
							data.searchStartTime = searchStartTime;
			 			}
			 			if(searchEndTime != null && searchEndTime !="" ){
							data.searchEndTime = searchEndTime;
			 			}
					},
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
	                  data: "description",
	                  orderable: false,
	                  render: function ( data, type, full, meta ) {
	                      return '<div>' + data +'</div>';
	                  },
	                  targets: 0
				  },
				  {
	                  data: "contactName",
	                  orderable: false,
	                  render: function ( data, type, full, meta ) {
	                      return '<div>' + data +'</div>';
	                  },
	                  targets: 1
				  },
				  {
	                  data: "mobilephone",
	                  orderable: false,
	                  render: function ( data, type, full, meta ) {
	                      return '<div>' + data +'</div>';
	                  },
	                  targets: 2
				  },
				  {
	                  data: "status",
	                  orderable: false,
	                  render: function ( data, type, full, meta ) {
	                	  /* if (data == '1') {
	                		  return '<div>' + '待审核' +'</div>';
	                	  } else if(data == '2') {
	                		  return '<div>' + '审核通过' +'</div>';
	                	  } else {
	                		  return '<div>' + '审核不通过' +'</div>';
	                	  } */
	                	  if(full.status){
		                		for(var i=0;i <publishStatus.length;i++){
			                		if(full.status==publishStatus[i].id){
			                			return '<div>' + publishStatus[i].text +'</div>';
			                		}				                	
			                	}
			                } else {
			                	return '<div></div>';
			                }
	                  },
	                  targets: 3
				  },
				  {
	                  data: "category",
	                  orderable: false,
	                  render: function ( data, type, full, meta ) {
                	  	if(data){
		                	for(var i=0; i < category.length; i++){
		                		if(data == category[i].id){
		                			return "<div class='hidden-xs'>" + category[i].text + "</div>";               			
		                		}
		                	}
		                	return "<div class='hidden-xs'></div>";
		              	}else{return "<div class='hidden-xs'></div>"}
	                  },
	                  targets: 4
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
	                  targets: 5
				  },
				  {
	                  data: "updateTime",
	                  orderable: false,
	                  render: function ( data, type, full, meta ) {
	                	  var n = full.updateTime.time;
		                	if(full.updateTime){
		                		n=new Date(n).format("yyyy-MM-dd hh:mm:ss");
		                	}else{
		                		n="";
		                	}
		                	return n;
	                  },
	                  targets: 6
				  },
				  {
	                  data: "id",
	                  orderable: false,
	                  render: function ( data, type, full, meta ) {
	                	  return '<a class="btn btn-success btn-xs" id="'+data+'"><span class="fa fa-edit"></span> 编辑</a>&nbsp;<a class="btn btn-danger btn-xs" id="'+data+'"><span class="fa fa-edit"></span> 删除</a>&nbsp;';
	                  },
	                  targets: 7
				  },
				  {
					  orderable: false,
					  searchable: false
				  },
				],
				columns: [
		            { data: "description" },
		            { data: "contactName" },
		            { data: "mobilephone" },
		            { data: "status" },
		            { data: "category" },
		            { data: "createTime" },
		            { data: "updateTime" },
		            { data: "id" }
		        ]
			});
			
 			$('#searchBtn').on( 'click', function () {
		        t.draw();
		    } ); 
			
			$('#dataTable tbody').on( 'click', 'a.btn-success', function () {
				var data = t.row($(this).parents('tr')).data();
				edit($(this).attr('id'));
		    } );
			
			$('#dataTable tbody').on( 'click', 'a.btn-danger', function () {
		        var data = t.row($(this).parents('tr')).data();
		        del($(this).attr('id'));
		    } );
			
			$('#confirmDelModal').on( 'click', 'button.btn-danger', function () {
		        var id = $("#confirmDelModal .hiddenId").val();
		        doDel(id);
		    } ); 
			
			// Select2
		    jQuery('select').select2({
		        minimumResultsForSearch: -1
		    });
		    
		    jQuery('select').removeClass('form-control');
			
			
		});
		
		function edit(id) {
			window.parent.location = "${rootPath}manager/editPublish.html?id="+id;
		}
		
		function del(id) {
			$("#confirmDelModal .hiddenId").val("");
			$("#confirmDelModal .hiddenId").val(id);
			$("#confirmDelModal").modal('show');
		}
		
		function doDel(id){
			$.ajax({
				url: "${rootPath}manager/delPublish.do?id=" + id, 
				async: true,
				success: function(o) {
					window.location.reload();
				},
				error: function(o) {
					alert(2);
				}
			});
			
		}
		
	</script>

</body>
</html>
