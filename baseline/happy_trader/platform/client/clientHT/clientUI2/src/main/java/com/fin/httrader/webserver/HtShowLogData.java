/*
 * HtShowLogData.java
 *

 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.fin.httrader.webserver;


import com.fin.httrader.utils.hlpstruct.CommonLog;
import java.io.IOException;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class HtShowLogData extends HtServletsBase {

	public static final int NUMBER_OF_PAGES = 200;
	public static final int ROWS_PER_PAGE = 300;

	public String getContext() {
		return getClass().getSimpleName();
	}

	public int getPageSize()
	{
		return ROWS_PER_PAGE;
	}

	public int getNumberPagesToShow()
	{
		return NUMBER_OF_PAGES;
	}
	

	// ----------------------------------------------------
	public boolean initialize_Get(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=windows-1251");
		forceNoCache(res);

		try {

			// generate new Uid and set initial values
			this.generateUniquePageId();
	
			initLogLevels(req);


		} catch (Throwable e) {
			writeHtmlErrorToOutput(res, e);
			
			return false;
		}
		
		return true;
	}

	// ----------------------------------------------------
	/**
	Helpers
	 */
	private void initLogLevels(HttpServletRequest req) throws Exception {
		//
		String defLogLevel = getStringParameter(req, "log_level", true);

		// default log level
		if (defLogLevel.length() <= 0) {
			defLogLevel = "4";
		}

		
		setStringSessionValue(getUniquePageId(), req, "all_log_levels", HtWebUtils.createOptionMappedList(Integer.valueOf( defLogLevel ), CommonLog.getAvailableLogLevelsMap()));
	//

	}

	
}