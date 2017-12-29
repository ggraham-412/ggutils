package org.ggraham.ggutils.test;

import java.text.SimpleDateFormat;

import org.ggraham.ggutils.LogLevel;


public class TestLogger {

	public static void main(String[] args) {

		LogImpl logImpl = new LogImpl();
		
		logImpl.setLogLevel(LogLevel.DEBUG);
		logImpl.logDebug("TestLogger.main", "This is a Debug.");
		logImpl.logInfo("TestLogger.main", "This is an Info.");
		logImpl.logBasic("TestLogger.main", "This is a Basic.");
		logImpl.logWarning("TestLogger.main", "This is a Warning.");
		logImpl.logError("TestLogger.main", "This is an Error.");
		logImpl.logSevere("TestLogger.main", "This is a Severe.");
		
		logImpl.setLogLevel(LogLevel.INFO);
		logImpl.logDebug("TestLogger.main", "This is a Debug.");
		logImpl.logInfo("TestLogger.main", "This is an Info.");
		logImpl.logBasic("TestLogger.main", "This is a Basic.");
		logImpl.logWarning("TestLogger.main", "This is a Warning.");
		logImpl.logError("TestLogger.main", "This is an Error.");
		logImpl.logSevere("TestLogger.main", "This is a Severe.");
		
		logImpl.setLogLevel(LogLevel.BASIC);
		logImpl.logDebug("TestLogger.main", "This is a Debug.");
		logImpl.logInfo("TestLogger.main", "This is an Info.");
		logImpl.logBasic("TestLogger.main", "This is a Basic.");
		logImpl.logWarning("TestLogger.main", "This is a Warning.");
		logImpl.logError("TestLogger.main", "This is an Error.");
		logImpl.logSevere("TestLogger.main", "This is a Severe.");
		
		logImpl.setLogLevel(LogLevel.WARNING);
		logImpl.logDebug("TestLogger.main", "This is a Debug.");
		logImpl.logInfo("TestLogger.main", "This is an Info.");
		logImpl.logBasic("TestLogger.main", "This is a Basic.");
		logImpl.logWarning("TestLogger.main", "This is a Warning.");
		logImpl.logError("TestLogger.main", "This is an Error.");
		logImpl.logSevere("TestLogger.main", "This is a Severe.");
		
		logImpl.setLogLevel(LogLevel.ERROR);
		logImpl.logDebug("TestLogger.main", "This is a Debug.");
		logImpl.logInfo("TestLogger.main", "This is an Info.");
		logImpl.logBasic("TestLogger.main", "This is a Basic.");
		logImpl.logWarning("TestLogger.main", "This is a Warning.");
		logImpl.logError("TestLogger.main", "This is an Error.");
		logImpl.logSevere("TestLogger.main", "This is a Severe.");
		
		logImpl.setLogLevel(LogLevel.SEVERE);
		logImpl.logDebug("TestLogger.main", "This is a Debug.");
		logImpl.logInfo("TestLogger.main", "This is an Info.");
		logImpl.logBasic("TestLogger.main", "This is a Basic.");
		logImpl.logWarning("TestLogger.main", "This is a Warning.");
		logImpl.logError("TestLogger.main", "This is an Error.");
		logImpl.logSevere("TestLogger.main", "This is a Severe.");
		
	}

}
