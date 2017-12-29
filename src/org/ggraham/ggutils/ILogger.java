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

public interface ILogger {

	public void log(String message, int level);
	public void log(String source, String message, int level);
	
	public void logDebug(String message);
	public void logDebug(String source, String message);
	public void logInfo(String message);
	public void logInfo(String source, String message);
	public void logBasic(String message);
	public void logBasic(String source, String message);
	public void logWarning(String message);
	public void logWarning(String source, String message);
	public void logError(String message);
	public void logError(String source, String message);
	public void logSevere(String message);
	public void logSevere(String source, String message);
	
	public void setLogLevel(int level);
	public int getLogLevel();
	
}
