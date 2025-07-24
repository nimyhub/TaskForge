package data;

public class TaskSerializerFactory {
    public static TaskSerializer get(String format) {
        return switch (format.toLowerCase()) {
            case ".dat" -> new DatTaskSerializer();
            case ".txt" -> new TxtTaskSerializer();
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
    }
}
