package entity;

import java.time.Duration;
import java.time.LocalDateTime;

public class DateTimeRange {
    private LocalDateTime start;
    private LocalDateTime end;

    public DateTimeRange(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() { return start; }
    public LocalDateTime getEnd() { return end; }

    public boolean overlapsWith(DateTimeRange other) {
        return !(this.end.isBefore(other.start) || this.start.isAfter(other.end));
    }

    public long getDurationInHours() {
        return Duration.between(start, end).toHours();
    }

    @Override
    public String toString() {
        return start + " to " + end;
    }
}
