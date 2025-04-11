package niroj.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class MovieApplication {
public static void main(String[] args) {
        // Try to load .env, fallback to system environment
        try {
            Dotenv dotenv = Dotenv.load();
            String databaseUserName = getEnvOrDefault("Database_Name", dotenv);
            String databasePassword = getEnvOrDefault("Database_Password", dotenv);
			
            System.setProperty("Database_Name", databaseUserName);
            System.setProperty("Database_Password", databasePassword);
        } catch (Exception e) {
            System.out.println(".env not found, falling back to system environment variables");
        }

        SpringApplication.run(MovieApplication.class, args);
    }

	private static String getEnvOrDefault(String key, Dotenv dotenv) {
        String systemValue = System.getenv(key);
		System.out.println( key + ": " + systemValue);
        if (systemValue != null) return systemValue;
        return dotenv.get(key);
    }

}
