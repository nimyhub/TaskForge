package serializers;

import java.io.*;
import java.util.*;

import data.Task;

public interface TaskSerializer {
	void save(List<Task> tasks, File file) throws IOException;
	List<Task> load(File file) throws IOException, ClassNotFoundException;
}
