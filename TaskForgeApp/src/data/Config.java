package data;

import java.io.Serializable;

public class Config implements Serializable {
	private static final long serialVersionUID = 1L;
	private String lastDir;
	private String lastFileName;
	private String lastFormat;
	
	public void setLastDir(String dir) {
		lastDir = dir;
	}
	
	public String getLastDir() {
		return lastDir;
	}
	
	public void setLastFileName(String fileName) {
		lastFileName = fileName;
	}
	
	public String getLastFileName() {
		return lastFileName;
	}
	
	public void setLastFormat(String format) {
		lastFormat = format;
	}
	
	public String getLastFormat() {
		return lastFormat;
	}
	
	public String getFile() {
		return new String(lastDir + lastFileName + lastFormat);
	}
}
