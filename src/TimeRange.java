import java.time.LocalDateTime;
import java.time.Period;
import java.time.Duration;
public class TimeRange {
    LocalDateTime start_time;
    LocalDateTime end_time;
    public LocalDateTime getStart_time(){
        return start_time;
    }
    public LocalDateTime getEnd_time(){
        return end_time;
    }
    public Duration get_Duration(){
        return Duration.between(this.getStart_time(), this.getEnd_time());
    }
}
