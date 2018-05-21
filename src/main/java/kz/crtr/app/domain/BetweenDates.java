package kz.crtr.app.domain;

import java.util.Date;
import lombok.Data;

@Data
public class BetweenDates {

    private Date beginDate;
    private Date endDate;

    public BetweenDates(Date beginDate, Date endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public BetweenDates() {
    }
}