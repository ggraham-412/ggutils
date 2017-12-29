package org.ggraham.ggutils;

/*

MIT License

Copyright (c) [2017] [Gregory Graham]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

*/

/**
 * 
 * Package level logging interface.  The idea is that classes in a
 * package can use this interface for logging, and then an implementation
 * can be injected at runtime to print to console or bridge to another
 * logging system as needed.
 * 
 * @author ggraham
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
