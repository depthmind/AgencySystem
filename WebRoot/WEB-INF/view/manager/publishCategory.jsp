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
					<i class="glyphicon glyphicon-cog"></i> 帖子管理 <span>分类列表</span>
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
					<h3 class="panel-title">分类列表</h3>
					</div>
					<div class="panel-body">
						<br />
					<a class="btn btn-success btn-xs" id="addCategory"><span class="fa fa-edit"></span> 添加分类</a>
						<div class="table-responsive">

							<table id="dataTable" class="table">
								<thead>
									<tr>
										<th>顺序</th>
										<th>分类名称</th>
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
					url: '${rootPath}manager/publishCategoryList.do',
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
	                  data: "value",
	                  orderable: false,
	                  render: function ( data, type, full, meta ) {
	                      return '<div>' + data +'</div>';
	                  },
	                  targets: 0
				  },
				  {
	                  data: "chinese",
	                  orderable: false,
	                  render: function ( data, type, full, meta ) {
	                      return '<div>' + data +'</div>';
	                  },
	                  targets: 1
				  },
				  {
	                  data: "isdisplay",
	                  orderable: false,
	                  render: function ( data, type, full, meta ) {
	                      return '<div>' +'</div>';
	                  },
	                  targets: 2
				  },
				  {
	                  data: "parameterId",
	                  orderable: false,
	                  render: function ( data, type, full, meta ) {
	                	  return '<a class="btn btn-success btn-xs" id="'+data+'"><span class="fa fa-edit"></span> 编辑</a>&nbsp;<a class="btn btn-danger btn-xs" id="'+data+'"><span class="fa fa-edit"></span> 删除</a>&nbsp;';
	                  },
	                  targets: 3
				  },
				  {
					  orderable: false,
					  searchable: false
				  },
				],
				columns: [
		            { data: "value" },
		            { data: "chinese" },
		            { data: "isdisplay" },
		            { data: "parameterId" }
		        ]
			});
			
			$('#addCategory').on('click', function() {
				window.parent.location = "${rootPath}manager/addPublishCategory.html";
			});
			
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
			
			function del(id) {
				$("#confirmDelModal .hiddenId").val("");
				$("#confirmDelModal .hiddenId").val(id);
				$("#confirmDelModal").modal('show');
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
			
		    jQuery('select').select2({
		        minimumResultsForSearch: -1
		    });
		    
		    jQuery('select').removeClass('form-control');
			
			
		});
		
		function edit(id) {
			window.parent.location = "${rootPath}manager/editSigninTerm.html?id="+id;
		}
	</script>

</body>
</html>
