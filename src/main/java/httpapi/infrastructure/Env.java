package httpapi.infrastructure;

public class Env {

    public static Integer getPort(Integer defaultPort) {
        try{
            return Integer.valueOf(System.getenv("PORT"));
        } catch (NumberFormatException e) {
            return defaultPort;
        }
    }
}
