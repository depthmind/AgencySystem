<%@ page language="java" pageEncoding="utf-8"%> 
<%@ page contentType="text/html; charset=utf-8"%> 
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../assets/pages/head.jsp"%>
	<link rel="stylesheet" type="text/css" href="../assets/jquery-easyui-1.7.0themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../assets/jquery-easyui-1.7.0/themes/icon.css">
	<script type="text/javascript" src="../assets/jquery-easyui-1.7.0／jquery.min.js"></script>
	<script type="text/javascript" src="../../jquery.easyui.min.js"></script>
</head>

<body>
	<%@ include file="../assets/pages/preloader.jsp"%>
	<section>
		<%@ include file="../assets/pages/leftpanel.jsp"%>
		<div class="mainpanel">
			<%@ include file="../assets/pages/headerbar.jsp"%>
			<div class="pageheader">
				<h2>
					<i class="glyphicon glyphicon-cog"></i> 商品管理 <span>新增商品</span>
				</h2>
			</div>

			<div class="contentpanel">
				<!-- content goes here... -->

		<div class="panel panel-default">
        <div class="panel-heading">
          <div class="panel-btns">
            <a href="" class="minimize">&minus;</a>
          </div>
          <h4 class="panel-title">商品基本信息</h4>
          <p>填写下表，完成商品创建。</p>
        </div>
        <form class="form-horizontal" id="form">
        <div class="panel-body panel-body-nopadding">
	        <div class="section-block">    
	            <div class="form-group col-sm-4">
	              <label class="col-sm-4 control-label">商品名称 <span class="asterisk">*</span></label>
	              <div class="col-sm-8">
	                <input type="text" id="goodsName" name="goodsName" placeholder="商品名称" class="form-control" />
	              </div>
	            </div>
	            <div class="form-group col-sm-4">
	              <label class="col-sm-4 control-label">商品编码 </label>
	              <div class="col-sm-8">
	                <input type="text" id="goodsCode" name="goodsCode" placeholder="商品编码" class="form-control" />
	              </div>
	            </div> 		
	            <div class="form-group col-sm-4">
	              <label class="col-sm-4 control-label">已售数量 </label>
	              <div class="col-sm-8">
	                <input type="text" id="soldAmount" name=""soldAmount"" placeholder="前端展示的销量=实际销量+已出售量" class="form-control" />
	              </div>
	            </div> 	
	            <div class="form-group col-sm-4">
	              <label class="col-sm-4 control-label"> 缩略图 <span class="asterisk">*</span></label>
	              <div class="col-sm-8">
	                <input type="file" onchange="xmTanUploadImg(this)" id="thumbnail" name="thumbnail" class="form-control" />
	              </div>
	            </div>
	            <div id="pic"></div>
	            <div class="form-group col-sm-4">
	              <label class="col-sm-4 control-label"> 商品图片 <span class="asterisk">*</span></label>
	              <div class="col-sm-8">
	                <input type="file" onchange="uploadGoodsPic(this)" id="goodsPic" name="goodsPic" class="form-control" />
	              </div>
	            </div>
	            <div id="pics"></div>
	            <div class="form-group col-sm-4">
	              <label class="col-sm-4 control-label">售价 <span class="asterisk">&nbsp;</span></label>
	              <div class="col-sm-8">
	                <input type="text" name="sellPrice" placeholder="售价（单位：元）" class="form-control" />
	              </div>
	            </div> 	
	            <div class="form-group col-sm-4">
	              <label class="col-sm-4 control-label">商品库存 <span class="asterisk">&nbsp;</span></label>
	              <div class="col-sm-8">
	                <input type="text" name="stock" placeholder="商品库存" class="form-control" />
	              </div>
	            </div> 
	        </div>
	        <div class="section-block"> 
	        	<table id="dg" class="easyui-datagrid" title="Row Editing in DataGrid" style="width:700px;height:auto"
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				url: 'datagrid_data1.json',
				method: 'get',
				onClickRow: onClickRow
			">
		<thead>
			<tr>
				<th data-options="field:'itemid',width:80">Item ID</th>
				<th data-options="field:'productid',width:100,
						formatter:function(value,row){
							return row.productname;
						},
						editor:{
							type:'combobox',
							options:{
								valueField:'productid',
								textField:'productname',
								method:'get',
								url:'products.json',
								required:true
							}
						}">Product</th>
				<th data-options="field:'listprice',width:80,align:'right',editor:{type:'numberbox',options:{precision:1}}">List Price</th>
				<th data-options="field:'unitcost',width:80,align:'right',editor:'numberbox'">Unit Cost</th>
				<th data-options="field:'attr1',width:250,editor:'textbox'">Attribute</th>
				<th data-options="field:'status',width:60,align:'center',editor:{type:'checkbox',options:{on:'P',off:''}}">Status</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">Append</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">Remove</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">Accept</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">Reject</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">GetChanges</a>
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

	<%@ include file="../assets/pages/foot.jsp"%>
	<script src="${rootPath}assets/js/jquery.validate.min.js"></script>
	
	<script type="text/javascript">
	var selectedGoodsPic = ''; //定义全局变量，用于存储已选图片
	if (typeof FileReader == 'undefined') {
        document.getElementById("xmTanDiv").InnerHTML = "<h1>当前浏览器不支持FileReader接口</h1>";
        //使选择控件不可操作
        document.getElementById("thumbnail").setAttribute("disabled", "disabled");
    }
	jQuery(document).ready(function() {	

		$(".nav-parent").eq(9).addClass("nav-active");
      	$(".nav-parent").eq(9).find(".children").show();

		jQuery("#form").validate({
			rules: {
				domain: {
					required:true,
					isDomain:true,
				},
				value:{  
			         	required:true,  
			            remote:{                           
			            	url:"${rootPath}validate.do",
			            	type:"post",
			            	data: {
		                         table: function () { return "tm_parameter"},
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
				},
				sort:"digits",
				depict:{
					isDepict:true,
				},
				english:{
					isEnglish:true,
				}
			},
			messages: {
				domain: {
					required:"请输入作用域",
				},
				value: {
					required:"请输入值",
					remote:"该值已存在，请重新输入",
				},
				chinese: {
					required:"请输入中文",
				},
				sort: "排序标记必须是整数"
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
					window.parent.location = "${rootPath}parameter/add.html";
				} else {
					$("#msgModal").modal('show');
				}
			}, "JSON");
		}
	
		function xmTanUploadImg(obj) {
            var file = obj.files[0];
            
            console.log(obj);console.log(file);
            console.log("file.size = " + file.size);  //file.size 单位为byte

            var reader = new FileReader();

            //读取文件过程方法
            reader.onloadstart = function (e) {
                console.log("开始读取....");
            }
            reader.onprogress = function (e) {
                console.log("正在读取中....");
            }
            reader.onabort = function (e) {
                console.log("中断读取....");
            }
            reader.onerror = function (e) {
                console.log("读取异常....");
            }
            reader.onload = function (e) {
                console.log("成功读取....");
                var thumbnail = '<img src=\"' + e.target.result + '\"</img>'
                document.getElementById('pic').innerHTML = thumbnail;
            }
            reader.readAsDataURL(file)
        }
		var arr = []; //定义存储文件的数组，用于删除已选图片
		function uploadGoodsPic(obj) {
            var file = obj.files[0];
            arr.push(obj.files[0]);
            console.log(arr);
            //console.log(obj);console.log(file);
            //console.log("file.size = " + file.size);  //file.size 单位为byte

            var reader = new FileReader();

            //读取文件过程方法
            reader.onloadstart = function (e) {
                console.log("开始读取....");
            }
            reader.onprogress = function (e) {
                console.log("正在读取中....");
            }
            reader.onabort = function (e) {
                console.log("中断读取....");
            }
            reader.onerror = function (e) {
                console.log("读取异常....");
            }
            reader.onload = function (e) {
                console.log("成功读取....");
                selectedGoodsPic = selectedGoodsPic + '<img style="width:40px;height:40px" src=\"' + e.target.result + '\"</img>'
                document.getElementById('pics').innerHTML = selectedGoodsPic;
            }
            reader.readAsDataURL(file)
        }
	</script>

</body>
</html>
