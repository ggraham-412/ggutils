package org.ggraham.ggutils;

/*
 * 
 * Apache License 2.0 
 * 
 * Copyright (c) [2017] [Gregory Graham]
 * 
 * See LICENSE.txt for details.
 * 
 */

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.ggraham.ggutils.logging.ILogger;
import org.ggraham.ggutils.logging.LogImpl;
import org.ggraham.ggutils.logging.LogLevel;

/**
 * 
 * Package configuration service
 * 
 * @author ggraham
 *
 */
public class PackageService {
	
	private ILogger m_loggerService;
	public ILogger getLogImpl() { return m_loggerService; } 
	
	private PackageService(ILogger logImpl) {
		// default logger - stdout
		m_loggerService = logImpl == null ? new LogImpl() : logImpl;
		m_loggerService.setLogLevel(LogLevel.BASIC);
	}
	
	private static volatile PackageService s_packageService;
	private static Object s_lock = new Object();
	
	public static PackageService getPackageService(ILogger logImpl) {		
		if ( s_packageService == null ) {
			synchronized(s_lock) {
				if ( s_packageService == null ) {
					s_packageService = new PackageService(logImpl);
				}				
			}
		}
		return s_packageService;
	}
	
	public static PackageService getPackageService() {				
		return getPackageService(null);
	}
	
	public static ILogger getLog() {
		return getPackageService().getLogImpl();
	}
}
