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
					<i class="glyphicon glyphicon-cog"></i> 员工管理 <span>员工列表</span>
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
					<h3 class="panel-title">员工列表</h3>
					</div>
					<div class="panel-body">
						<br />
						<div class="table-responsive">

							<table id="dataTable" class="table">
								<thead>
									<tr>
										<th>名称</th>
										<th>联系方式</th>
										<th>省</th>
										<th>市</th>
										<th>县/区</th>
										<th>状态</th>
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
<div class="modal fade" id="disagreeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel"><span class="fa fa-warning"></span> 提示</h4>
      </div>
      <div class="modal-body">
        确定审核不通过么？
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
					url: '${rootPath}manager/agencyList.do',
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
	                  data: "agencyName",
	                  orderable: false,
	                  render: function ( data, type, full, meta ) {
	                      return '<div>' + data +'</div>';
	                  },
	                  targets: 0
				  },
				  {
	                  data: "mobilephone",
	                  orderable: false,
	                  render: function ( data, type, full, meta ) {
	                      return '<div>' + data +'</div>';
	                  },
	                  targets: 1
				  },
				  {
	                  data: "province",
	                  orderable: false,
	                  render: function ( data, type, full, meta ) {
	                      return '<div>' + data +'</div>';
	                  },
	                  targets: 2
				  },
				  {
	                  data: "city",
	                  orderable: false,
	                  render: function ( data, type, full, meta ) {
	                      return '<div>' + data +'</div>';
	                  },
	                  targets: 3
				  },
				  {
	                  data: "area",
	                  orderable: false,
	                  render: function ( data, type, full, meta ) {
	                      return '<div>' + data +'</div>';
	                  },
	                  targets: 4
				  },
				  {
	                  data: "status",
	                  orderable: false,
	                  render: function ( data, type, full, meta ) {
	                	  if(data == '1') {
	                		  return '<div>' + '待审核' +'</div>';
	                	  } else if(data == '2') {
	                		  return '<div>' + '审核通过' +'</div>';
	                	  } else {
	                		  return '<div>' + '审核不通过' +'</div>';
	                	  }
	                  },
	                  targets: 5
				  },
				  {
	                  data: "id",
	                  orderable: false,
	                  render: function ( data, type, full, meta ) {
	                	  if (full.status == '1') {
	                      	return '<a class="btn btn-success btn-xs" id="'+data+'"><span class="fa fa-edit"></span> 审核通过</a>&nbsp;<a class="btn btn-danger btn-xs" id="'+data+'"><span class="fa fa-edit"></span> 审核不通过</a>&nbsp;';
	                	  } else {
                		  	return '';
	                	  }
	                  },
	                  targets: 6
				  },
				  {
					  orderable: false,
					  searchable: false
				  },
				],
				columns: [
		            { data: "agencyName" },
		            { data: "mobilephone" },
		            { data: "province" },
		            { data: "city" },
		            { data: "area" },
		            { data: "status" },
		            { data: "id" }
		        ]
			});
			
 			$('#searchBtn').on( 'click', function () {
		        t.draw();
		    } ); 
			
			$('#dataTable tbody').on( 'click', 'a.btn-success', function () {
		        var data = t.row($(this).parents('tr')).data();
		        agree($(this).attr('id'));
		    } );

			$('#dataTable tbody').on( 'click', 'a.btn-danger', function () {
		        var data = t.row($(this).parents('tr')).data();
		        disagree($(this).attr('id'));
		    } );
			
			$('#confirmDelModal').on( 'click', 'button.btn-danger', function () {
		        var id = $("#confirmDelModal .hiddenId").val();
		        doDel(id);
		    } ); 
			
			$('#dataTable tbody').on( 'click', 'a.btn-success', function () {
				var data = t.row($(this).parents('tr')).data();
				agree($(this).attr('id'));
		    } );
			
			$('#dataTable tbody').on( 'click', 'a.btn-danger', function () {
				var data = t.row($(this).parents('tr')).data();
				disagree($(this).attr('id'));
		    } );

			$('#agreeModal').on( 'click', 'button.btn-danger', function () {
		        var id = $("#agreeModal .hiddenId").val();
		        doAgree(id);
		    } ); 
			
			$('#disagreeModal').on( 'click', 'button.btn-danger', function () {
		        var id = $("#disagreeModal .hiddenId").val();
		        doDisagree(id);
		    } );
		    
			// Select2
		    jQuery('select').select2({
		        minimumResultsForSearch: -1
		    });
		    
		    jQuery('select').removeClass('form-control');
			
			
		});
		
		function edit(id) {
			window.parent.location = "${rootPath}parameter/edit.html?id="+id;
		}
		
		function del(id) {
			$("#confirmDelModal .hiddenId").val("");
			$("#confirmDelModal .hiddenId").val(id);
			$("#confirmDelModal").modal('show');
		}
		
		function agree(id) {
			$("#agreeModal .hiddenId").val("");
			$("#agreeModal .hiddenId").val(id);
			$("#agreeModal").modal('show');
		}
		
		function disagree(id) {
			$("#disagreeModal .hiddenId").val("");
			$("#disagreeModal .hiddenId").val(id);
			$("#disagreeModal").modal('show');
		}
		
		function doAgree(id){
			$.ajax({
				url: "${rootPath}manager/updateAgencyBaseStatusById.do?id=" + id + "&status=2", 
				async: true,
				success: function(o) {
					window.location.reload();
				},
				error: function(o) {
					alert(2);
				}
			});
		}
		
		function doDisagree(id){
			$.ajax({
				url: "${rootPath}manager/updateAgencyBaseStatusById.do?id=" + id + "&status=3", 
				async: true,
				success: function(o) {
					window.location.reload();
				},
				error: function(o) {
					alert(2);
				}
			});
		}
		
		function doDel(id){
			$.ajax({
				url: "${rootPath}parameter/del.do?id=" + id, 
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
