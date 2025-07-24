package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatTaskSerializer implements TaskSerializer {

	@Override
	public void save(List<Task> tasks, File file) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public List<Task> load(File file) throws IOException, ClassNotFoundException {
		System.out.println("DatTaskSerializer::load. trying to load file: "+file);
		if(!file.exists()) return new ArrayList<>();
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
			return (List<Task>) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}
