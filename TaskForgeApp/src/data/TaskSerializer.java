package data;

import java.io.*;
import java.util.*;

public interface TaskSerializer {
	void save(List<Task> tasks, File file) throws IOException;
	List<Task> load(File file) throws IOException, ClassNotFoundException;
}
