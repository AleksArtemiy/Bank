package Classes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class OverdueBook extends Book {
    private LocalDate dueDate; // Дата, когда книга должна быть возвращена

    public OverdueBook(String title, String author, String edition, String genre, int isbn, LocalDate dueDate) {
        super(title, author, edition, genre, isbn);
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isOverdue() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.isAfter(dueDate);
    }

    public long getDaysOverdue() {
        if (isOverdue()) {
            return ChronoUnit.DAYS.between(dueDate, LocalDate.now());
        }
        return 0;
    }

    @Override
    public String toString() {
        String overdueInfo = isOverdue() ? " (Просрочена на " + getDaysOverdue() + " дней)" : " (Не просрочена)";
        return super.toString() + overdueInfo;
    }
}