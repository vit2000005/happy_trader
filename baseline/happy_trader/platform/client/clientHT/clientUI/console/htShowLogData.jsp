<%@ page contentType="text/html;charset=windows-1251"%>
<jsp:useBean id="SC" class="com.fin.httrader.webserver.HtShowLogData" scope="request"/>
<jsp:setProperty name="SC" property="*"/>
<% 
String methodName = request.getMethod();
if (methodName.equalsIgnoreCase("GET")) {
		SC.initialize_Get(request,response);
}
%>


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
		<meta http-equiv="Cache-Control" content="No-cache">

		<title id='tltObj' >View Logging Data</title>

		<link href="css/console.css" rel="stylesheet" type="text/css"/>
		<link href="css/tbl.css" rel="stylesheet" type="text/css"/>


		<script type="text/javascript" src="js/misc/date.js"></script>


		<!-- calendar stylesheet -->
		<link rel="stylesheet" type="text/css" media="all" href="css/calendar-win2k-cold-1.css" title="win2k-cold-1" />

		<script type="text/javascript" src="js/misc/helper.js"></script>

		<!-- navigation bar -->
		<script type="text/javascript" src="js/misc/navhelper.js"></script>

		<!-- main calendar program -->
		<script type="text/javascript" src="js/calendar.js"></script>

		<!-- language for the calendar -->
		<script type="text/javascript" src="js/lang/calendar-en.js"></script>

		<!-- the following script defines the Calendar.setup helper function, which makes
			 adding a calendar a matter of 1 or 2 lines of code. -->
		<script type="text/javascript" src="js/calendar-setup.js"></script>

		<!-- OLD DHTML grid -->
		<!--
		<link rel="STYLESHEET" type="text/css" href="css/dhtmlxgrid.css">

		<script  src="js/grid/dhtmlxcommon.js"></script>
		<script  src="js/grid/dhtmlxgrid.js"></script>
		<script  src="js/grid/dhtmlxgridcell.js"></script>
		-->

		<!-- NEW GRID -->
		<link rel="STYLESHEET" type="text/css" href="js/grid3/codebase/dhtmlxgrid.css">
		<script  src="js/grid3/codebase/dhtmlxcommon.js"></script>
		<script  src="js/grid3/codebase/dhtmlxgrid.js"></script>
		<script  src="js/grid3/codebase/dhtmlxgridcell.js"></script>


		<script type="text/javascript">

			var xmlhttp_m = getXmlHttp();

			var historyGrid_m = null;

			var navigationBar_m = null;


			/////////////////////////////////////

			function page_load()
			{
				setPageCaption(tltObj.innerText);

				goClearObj.disabled=true;

				navigationBar_m = new NavigationBar(	'Log Data', "navDibObj",		<%= SC.getPageSize()%>,			<%= SC.getNumberPagesToShow()%>,
				usrFun_generUrl,
				usrFun_Navigate,
				usrFun_SwitchOnLoadInProgress,
				usrFun_SwitchOffLoadInProgress		);


				navigationBar_m.disable();
			}




			function usrFun_generUrl(history_pageIndex, pageSize)
			{
				return null;
			}

			function usrFun_Navigate(historyBaseUrl, history_pageIndex, pageSize)
			{
				//reinitHistoryObject();
				//historyGrid_m.loadXML(historyBaseUrl, check_error_after_history_load);

				var url = '/HtShowLogData_HistoryProvider.jsp?'+
					"bdate="+encodeURIComponent(fmain.f_date_b.value)+
					"&edate="+encodeURIComponent(fmain.f_date_e.value) +
					"&log_level="+fmain.logLevelObj.options(fmain.logLevelObj.selectedIndex).value +
					"&context_filter="+encodeURIComponent(fmain.contextFilterObj.value) +
					"&content_filter="+encodeURIComponent(contentFilterObj.value)+
					"&thread_filter="+encodeURIComponent(threadFilterObj.value)+
					"&read_page_num="+history_pageIndex+
					"&read_rows_per_page="+pageSize+
					"&page_uid=<%= SC.getUniquePageId() %>";
				
				makeAsynchXmlHttpRequestExternHandler(
				xmlhttp_m,
					-1,
				'GET',
				url,
				null,
				function(loader)
				{
					if(loader.responseXML!=null && loader.responseText != null && loader.responseText.length>0) {
						reinitHistoryObject();

						//alert(loader.responseText);

						// parse
						dhtmlxError.catchError("ALL",
						function(type, desc, erData)
						{
							// error occured
							alert('Error occured of type: [' + type + "]\ndescription: [" + desc + "]\nstatus: [" + erData[0].status+"]");
							check_error_after_history_load();
						}
					);

						
						historyGrid_m.parse(loader.responseXML);
						dhtmlxError.catchError("ALL", null );

						navigationBar_m.callback_NavigationFinished( historyGrid_m.getRowsNum(), true );
					}
					else {

						alert('No data arrived');
						navigationBar_m.callback_NavigationFinished( -1, false );
					}
				}	,

				function(errmsg)
				{
					alert('Internal error on loading data: '+errmsg);
					navigationBar_m.callback_NavigationFinished( -1, false );
				}
			);


			}

			function usrFun_SwitchOnLoadInProgress()
			{
				bodyObj.disabled=true;
			}

			function usrFun_SwitchOffLoadInProgress()
			{
				bodyObj.disabled=false;
			}


			// checks if any error happend after history load
			function check_error_after_history_load()
			{


				makeAsynchXmlHttpRequestExternHandler(
				xmlhttp_m,
					-1,
				'GET',
				'/HtReturnSessionVar_v2.jsp?response_type=xml&session_var=error_info&cookie=<%=SC.getUniquePageId() %>',
				null,
				function(loader)
				{
					if(loader.responseXML!=null && loader.responseText != null && loader.responseText.length>0) {
						// error message
						navigationBar_m.callback_NavigationFinished( -1, false );
						document.body.innerHTML = extractErrorMessage(loader.responseXML);
					}
					else {

						navigationBar_m.callback_NavigationFinished( historyGrid_m.getRowsNum(), true );
					}
				}	,

				function(errmsg)
				{
					alert('Internal error on get error status: '+errmsg);
					navigationBar_m.callback_NavigationFinished( -1, false );

				}
			);



			}


			function d(id) {
				var url = "/htShowDetailedLog.jsp?id="+id;
				window.open(url);
			}

			function do_clear()
			{
				//



				fmain.f_trigger_b.disabled = false;
				fmain.f_trigger_e.disabled = false;
				fmain.f_date_b.disabled = false;
				fmain.f_date_e.disabled = false;

				fmain.logLevelObj.disabled = false;
				fmain.contextFilterObj.disabled = false;
				contentFilterObj.disabled = false;
				threadFilterObj.disabled = false;


				// disable go
				goBtnObj.disabled=false;
				goClearObj.disabled=true;

				//


				reinitHistoryObject();
				navigationBar_m.disable();


			}

			function do_go()
			{
				// disable


				fmain.f_trigger_b.disabled = true;
				fmain.f_trigger_e.disabled = true;
				fmain.f_date_b.disabled = true;
				fmain.f_date_e.disabled = true;

				fmain.logLevelObj.disabled = true;
				fmain.contextFilterObj.disabled = true;
				contentFilterObj.disabled = true;
				threadFilterObj.disabled = true;


				// disable go
				goBtnObj.disabled=true;
				goClearObj.disabled=false;


				navigationBar_m.enable();
				navigationBar_m.first_page_history();
			}

			//////////////////////////////////////////////
			function initHistoryGridObject()
			{
				historyGrid_m = new dhtmlXGridObject("dataTblObj");


				historyGrid_m.setImagePath("js/grid3/codebase/imgs/");
				historyGrid_m.setEditable(false);
				historyGrid_m.setHeader("ID, Log Time, Level, Thread, Session, Context, Content");
				historyGrid_m.setInitWidths("90,120,80,80,200,200,400");
				historyGrid_m.setColTypes("ro,ro,ro,ro,ro,ro,ro");
				historyGrid_m.setColAlign("left,left,left,left,left,left,left");
				historyGrid_m.setColSorting("na,na,na,na,na,na,na");
				historyGrid_m.enableResizing("true,true,true,true,true,true,true");
				historyGrid_m.enableTooltips("false,false,false,false,false,false,false");

				historyGrid_m.init();

			}

			function reinitHistoryObject()
			{
				if (historyGrid_m != null) {
					historyGrid_m.destructor();
					historyGrid_m = null;
				}

				initHistoryGridObject();
			}



		</script>
	</head>
	<body id="bodyObj"  onload="page_load();">

		<table width=100% height="100%" class=x8>

			<tr> <!-- control header begin -->
				<td>


					<table height="100%" width=100% class=x8>
						<tr>

							<td width="350px" style="border:1px solid black">

								<table height=100% width="100%" class=x8 >

									<tr valign=top>
										<td align=left height=20px width=100%>
											Content
										</td>
										<td></td>
									</tr>

									<tr valign=top>
										<td align=left>
											<input style="width:100%"  id="contentFilterObj" type="text" value="" ></input>
										</td>

										<td></td>
									</tr>

									<tr valign=top>
										<td align=left height=20px width=100%>
											Thread
										</td>
										<td></td>
									</tr>

									<tr valign=top>
										<td align=left>
											<input style="width:100%"  id="threadFilterObj" type="text" value="" ></input>
										</td>

										<td></td>
									</tr>


									<tr>
										<td>
										</td>
										<td>
										</td>
									</tr>



								</table>

							</td>



							<td width=250px style="border:1px solid black" >
								<form action="#" method="get" id="fmain">
									<table width=100% height=100% valign=top class=x8>
										<tr valign=top>
											<td align=left width=80px>
												Select From
											</td>
											<td>


												<table width=100% cellspacing="0" cellpadding="0" style="border-collapse: collapse;">
													<tr>
														<td width=120px>
															<input style="width: 100%" class=x8 type="text" name="date" id="f_date_b" />
														</td>

														<td>
															<img src="imgs/img.gif" id="f_trigger_b" style="cursor: pointer; border: 1px solid red;" title="Date selector"
										onmouseover="this.style.background='red';" onmouseout="this.style.background=''" />
														</td>

													</tr>
												</table>


												<script type="text/javascript">
													Calendar.setup({
														inputField     :    "f_date_b",     // id of the input field
														ifFormat       :    "%d-%m-%Y %H:%M",      // format of the input field
														button         :    "f_trigger_b",  // trigger for the calendar (button ID)
														align          :    "Tl",           // alignment (defaults to "Bl")
														singleClick    :    true,
														timeFormat     :    "24",
														showsTime      :    true
													});
												</script>

											</td>
										</tr>

										<tr valign=top>
											<td align=left width=80px>
												Select To
											</td>
											<td>

												<table width=100% cellspacing="0" cellpadding="0" style="border-collapse: collapse;">
													<tr>
														<td width=120px>
															<input style="width: 100%" class=x8 type="text" name="date" id="f_date_e" />
														</td>

														<td>
															<img src="imgs/img.gif" id="f_trigger_e" style="cursor: pointer; border: 1px solid red;" title="Date selector"
										onmouseover="this.style.background='red';" onmouseout="this.style.background=''" />
														</td>

													</tr>
												</table>


												<script type="text/javascript">
													Calendar.setup({
														inputField     :    "f_date_e",     // id of the input field
														ifFormat       :    "%d-%m-%Y %H:%M",      // format of the input field
														button         :    "f_trigger_e",  // trigger for the calendar (button ID)
														align          :    "Tl",           // alignment (defaults to "Bl")
														singleClick    :    true,
														timeFormat     :    "24",
														showsTime      :    true
													});
												</script>

											</td>
										</tr>


										<tr>
											<td width="90px">
												<div align='left'>
													Log Level
												</div>
											</td>

											<td align=left>
												<select id="logLevelObj" class=x8>
													<%= SC.getStringSessionValue(SC.getUniquePageId(),request, "all_log_levels") %>
												</select>
											</td>
										</tr>

										<tr>
											<td width="90px">
												<div align='left'>
													Context
												</div>
											</td>

											<td align=left>
												<input id="contextFilterObj" type="text"></input>
											</td>
										</tr>



									</table>
								</form>
							</td>


							<td>
								<table class=x8 width=100% height=100% valign=top>
									<tr height=10px>
										<td id="goBtnObj" width="90px" align=left>
											<a class="xd" href="#" onclick="do_go();">GO ></a>
										</td>
										<td></td>
									</tr>

									<tr height=10px>
										<td width=10px></td>
										<td></td>
									</tr>

									<tr height=10px>
										<td id="goClearObj" width="90px" align=left>
											<a class="xd" href="#" onclick="do_clear();">Clear</a>
										</td>
										<td></td>
									</tr>

									<tr>
										<td width=10px></td>
										<td></td>
									</tr>


								</table>
							</td>

							<td></td>

						</tr>
					</table>

				</td>
			</tr>


			<!--  navigation footer -->
			<tr height=30px>
				<td>
					<div id="navDibObj" width="100%" height="100%">



					</div>
				</td>
			</tr>
			<!--  end navigation footer -->

			<tr height="100%" width=100%>
				<td align="center">


					<div  style='height:100%; width:100%; border: 1px solid black'>
						<table  class=x8  width=100% height=100%>
							<tr>
								<td align="center">
									<div id="dataTblObj" style='height:100%; width:100%; '></div>
								</td>
							</tr>
						</table>
					</div>

				</td>
			</tr>


		</table>


	</body>
</html>
