package serializers;

public class TaskSerializerFactory {
    public static TaskSerializer get(String format){
        return switch (format.toLowerCase()) {
            case ".dat" -> new DatTaskSerializer();
            case ".txt" -> new TxtTaskSerializer();
            case ".json" -> new JsonTaskSerializer();
            default -> null;
        };
    }
}
