import java.time.LocalDateTime;

/**
 * @author hlxllo
 * @description
 * @date 2025/2/5
 */
public class TestHour {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        System.out.println(hour);
    }
}
