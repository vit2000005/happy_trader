<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=windows-1251"%>
<jsp:useBean id="SC" class="com.fin.httrader.webserver.HtEventPlugins" scope="request"/>
<jsp:setProperty name="SC" property="*"/>

<% 
String methodName = request.getMethod();
if (methodName.equalsIgnoreCase("GET")) {

		SC.initialize_Get(request,response);
} else if (methodName.equalsIgnoreCase("POST")) {
		SC.initialize_Post(request,response);
}
%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
		<meta http-equiv="Cache-Control" content="No-cache">
		<link href="css/console.css" rel="stylesheet" type="text/css"/>
		<link href="css/base.css" rel="stylesheet" type="text/css"/>

		<link rel="STYLESHEET" type="text/css" href="css/dhtmlxgrid.css">

		<!--
		<script  src="js/grid/dhtmlxcommon.js"></script>
		<script  src="js/grid/dhtmlxgrid.js"></script>
		<script  src="js/grid/dhtmlxgridcell.js"></script>
		<script  src="js/grid/dhtmlxgrid_start.js"></script>
		-->
	
		<!-- NEW GRID -->
		<!--
		<link rel="STYLESHEET" type="text/css" href="js/grid3/codebase/dhtmlxgrid.css">
		<script  src="js/grid3/codebase/dhtmlxcommon.js"></script>
		<script  src="js/grid3/codebase/dhtmlxgrid.js"></script>
		<script  src="js/grid3/codebase/dhtmlxgridcell.js"></script>
		<script  src="js/grid3/codebase/ext/dhtmlxgrid_start.js"></script>
		-->
		
		<!-- DHTML XGRID -->
        <link rel="stylesheet" type="text/css" href="js/d/codebase/dhtmlxgrid.css">
        <script type="text/javascript" src="js/d/codebase/dhtmlxcommon.js"></script>
        <script type="text/javascript" src="js/d/codebase/dhtmlxgrid.js"></script>
        <script type="text/javascript" src="js/d/codebase/dhtmlxgridcell.js"></script>
		<script type="text/javascript" src="js/d/codebase/ext/dhtmlxgrid_start.js"></script>


		<script type="text/javascript" src="js/misc/helper.js"></script>

		<script type="text/javascript" src="js/misc/property_table.js"></script>


		<title id='tltObj' >Event Plugins Administration</title>
		<script type="text/javascript">

			var xGrid_rt_param_m = null;
			var xGridExporter_m = null;

			function on_load()
			{
				setPageCaption(tltObj.innerText);

				// check id no providers at all
				if (pluginsObj.options.length <=0) {
					// disable everything excepting add provider option
					existProvidersTable.style.display = "none";
					noPluginsTable.style.display="inline";

				} else {
					// normal
					xGrid_rt_param_m = new dhtmlXGridFromTable('param_obj');
					xGrid_rt_param_m.setImagePath("js/grid3/codebase/imgs/");
					xGrid_rt_param_m.enableMultiselect(true);

					xGridExporter_m = new ExportImportXGrid(xGrid_rt_param_m, "<%=SC.getUniquePageId() %>" );
				}
			}

			function add_new_plugin()
			{
				var url = "/htEventPlugins.jsp?operation=add_new_plugin&new_plugin=";

				var plugin_name = window.showModalDialog('htEventPlugins_addNewPlugin.jsp', "", 'resizable:0;dialogHeight=150px;dialogWidth=330px;help:0;status:0;scroll:0');

				if(plugin_name==null)
					return;

				if (plugin_name.length <=0 ) {
					alert("You must enter valid provider name");
					return;
				}

				url += encodeURIComponent(plugin_name);
				url += '&page_uid=<%=SC.getUniquePageId()%>';

				mForm.action = url;
				mForm.submit();
			}

			function delete_plugin()
			{
				if (window.confirm('Are you sure to remove the plugin ' + get_cur_plugin())==true) {
					var url = "/htEventPlugins.jsp?operation=delete_plugin&cur_plugin="+get_cur_plugin();
					url += '&page_uid=<%=SC.getUniquePageId()%>';

					mForm.action = url;
					mForm.submit();
				}

			}

			function get_cur_plugin()
			{
				if (pluginsObj.options.length > 0)
					return encodeURIComponent(pluginsObj.options(pluginsObj.selectedIndex).innerText);

				return "";
			}

			function refresh_page()
			{
				var url = "/htEventPlugins.jsp?operation=refresh_page&cur_plugin="+get_cur_plugin();
				url += '&page_uid=<%=SC.getUniquePageId()%>';

				mForm.action = url;
				mForm.submit();
			}

			function allpy_changes()
			{
				var url = "/htEventPlugins.jsp?operation=apply_changes&cur_plugin=" + get_cur_plugin();

				// new key-value pair
			
				mForm.properties.value = xGridExporter_m.serializeAllRowsAsXmlParameter();
				//mForm.properties.value = getXGridAllRows(xGrid_rt_param_m, new Array(0,1));
				var plugin_class = providersClassObj.options(providersClassObj.selectedIndex).innerText;
                                var launch_flag = launchFlag_sel_obj.options(launchFlag_sel_obj.selectedIndex).innerText;
                                
				if (providersClassObj.options(providersClassObj.selectedIndex).invalid=='true') {
					alert("The provider class: \"" + plugin_class + "\" is not registered. Please change it.");
					return;
				}
                                
                                if (providersClassObj.options(providersClassObj.selectedIndex)._internal_class=='true') {
					if (launch_flag !=='LAUNCH_INTERNAL' ) {
                                            alert('This is internal class, so launch method must be LAUNCH_INTERNAL');
                                            return;
                                        }
                                        
				} else {
                                    if (launch_flag !=='LAUNCH_EXT_JAR' ) {
                                            alert('This is JAR class, so launch method must be LAUNCH_EXT_JAR');
                                            return;
                                     }
                                }

				url += "&plugin_class=" + encodeURIComponent(plugin_class);
                                url += "&launch_flag=" + encodeURIComponent(launch_flag);
				url += '&page_uid=<%=SC.getUniquePageId()%>';

				mForm.action = url;
				mForm.submit();
			}


			function export_param()
			{
				xGridExporter_m.exportSelectedRows(get_cur_plugin());
				
			}

			function import_param()
			{
				xGridExporter_m.importRows();
				
			}

			function insert_new_row()
			{
				addNewRowToXGrid(xGrid_rt_param_m);
			}

			function delete_rows()
			{
				deleteSelectedItems(xGrid_rt_param_m);
			}

			function select_all_rows()
			{
				selectXGridAllRows(xGrid_rt_param_m);
			}

			function deselect_all_rows()
			{
				deselectXGridAllRows(xGrid_rt_param_m);
			}


		</script>
	</head>

	<body onload="on_load();">

		<table id="noPluginsTable" style="display:none;width:100%;height:100%;"  class=x8>
			<tr>
				<td align=left>
					<a href="#" onclick="add_new_plugin();" >Add New Event Plugin</a>
				</td>
			</tr>
		</table>

		<table id="existProvidersTable" style="width:100%;height:100%;" class=x8>

			<tr class="x2">
				<td align="left" height="20px">
					Select Event Plugin
				</td>
			</tr>

			<tr>
				<td height="20px">
					<table class="x8" width="100%">
						<tr>
							<td>
								<select id="pluginsObj" style="width:100%" onchange="refresh_page();">
									<%= SC.getStringSessionValue(SC.getUniquePageId(),request, "plugin_list") %>
								</select>
							</td>
						</tr>
					</table>
				</td>
			</tr>

			<tr>
				<td height=20px bgcolor="buttonface">
					<table width=100% class=x8>
						<tr>
							<td>
							</td>

							

							<td width=110px align=left>
								<a href="#" onclick="delete_plugin();">Delete Plugin</a>
							</td>

							<td width=110px align=left>
								<a href="#" onclick="add_new_plugin();" >Add New Plugin</a>
							</td>


						</tr>
					</table>
				</td>
			</tr>


			<tr >
				<td height="20px">
					<table width=100% class=x8>
						<tr>
							<td align=left width=80px>
								Class Name
							</td>

							<td width=450px align=left>
								<select  id="providersClassObj" style="width:100%">
									<%= SC.getStringSessionValue(SC.getUniquePageId(),request, "plugin_classes") %>
								</select>
							</td>

							<td>
							</td>

						</tr>
					</table>

				</td>
			</tr>


			<tr >
				<td align="center"  style="vertical-align:top;height:100%">
				    
                                    
                                        <div>Launch flag:</div>
                                        <select id="launchFlag_sel_obj">
                                            <%= SC.getStringSessionValue(SC.getUniquePageId(), request, "launch_flag_select_obj") %>
                                        </select>    
                                        
					<div style="border: 1px solid black;overflow:auto;height:100%; ">
				
                                            <table id='param_obj' gridWidth='100%' gridHeight='100%' lightnavigation='false' class='x8'>
						<%= SC.getStringSessionValue(SC.getUniquePageId(), request, "plugin_parameters") %>
                                            </table>
                                        </div>
					
					
				</td>
			</tr>




			<tr >
				<td height="20px" bgcolor="buttonface">
					<table width=100% class =x8>
						<tr>
							<td class="x3" align=left width="38px">
								<a href="#" onclick="allpy_changes();">Apply</a>
							</td>

							<td>
							</td>

							<td  align=right width=95px>
								<a href="#" onclick="import_param();">Import From File</a>
							</td>

							<td  align=right width=95px>
								<a href="#" onclick="export_param();">Export to File</a>
							</td>

							<td  align=right width=95px>
								<a href="#" onclick="deselect_all_rows();">Deselect All</a>
							</td>

							<td  align=right width=95px>
								<a href="#" onclick="select_all_rows();">Select All</a>
							</td>

							<td  align=right width=95px>
								<a href="#" onclick="insert_new_row();">Insert</a>
							</td>

							<td  align=right width=95px>
								<a href="#" onclick="delete_rows();">Remove</a>
							</td>


						</tr>
					</table>
				</td>
			</tr>

			<tr>
				<td height=20px>
				</td>
			</tr>

		</table>

		<form method="post" action="" id="mForm" style="display:none">
			<input type=hidden name="properties" value=""></input>
		</form>

	</body>
</html>
