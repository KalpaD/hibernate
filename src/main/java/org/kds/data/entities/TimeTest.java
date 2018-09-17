package org.kds.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "time_test")
public class TimeTest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TIME_TEST_ID")
	private Long timeTestId;

    /**
     * Providing more information to hibernate, how we want time fields in tha db
     * since java.util.Date is pretty abstract.
     *
     * Without providing this information hibernate would not know what we want in these columns.
     */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATETIME_COLUMN")
	private Date datetimeColumn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMESTAMP_COLUMN")
	private Date timestampColumn;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_COLUMN")
	private Date dateColumn;

	@Temporal(TemporalType.TIME)
	@Column(name = "TIME_COLUMN")
	private Date timeColumn;

	@Column(name = "SQL_DATETIME_COLUMN")
	private java.sql.Timestamp sqlDatetimeColumn;

	@Column(name = "SQL_TIMESTAMP_COLUMN")
	private java.sql.Timestamp sqlTimestampColumn;

	@Column(name = "SQL_DATE_COLUMN")
	private java.sql.Date sqlDateColumn;

	@Column(name = "SQL_TIME_COLUMN")
	private java.sql.Time sqlTimeColumn;

	public TimeTest(){
		
	}
	
	public TimeTest(Date date) {
		this.datetimeColumn = date;
		this.timestampColumn = date;
		this.timeColumn = date;
		this.dateColumn = date;
		
		this.sqlDatetimeColumn = new java.sql.Timestamp(date.getTime());
		this.sqlTimestampColumn = new java.sql.Timestamp(date.getTime());
		this.sqlDateColumn = new java.sql.Date(date.getTime());
		this.sqlTimeColumn = new java.sql.Time(date.getTime());
	}


    @Override
    public String toString() {
        return "TimeTest{" +
                "timeTestId=" + timeTestId +
                ", datetimeColumn=" + datetimeColumn +
                ", timestampColumn=" + timestampColumn +
                ", dateColumn=" + dateColumn +
                ", timeColumn=" + timeColumn +
                ", sqlDatetimeColumn=" + sqlDatetimeColumn +
                ", sqlTimestampColumn=" + sqlTimestampColumn +
                ", sqlDateColumn=" + sqlDateColumn +
                ", sqlTimeColumn=" + sqlTimeColumn +
                '}';
    }
}
