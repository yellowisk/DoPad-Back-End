package br.com.dopad.domain.entities.line;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Line {
    private String text;
    private String author;
    private String pageChangeCode;
    private Timestamp date;

    public Line(String text, String author, String pageChangeCode, Timestamp date) {
        this.text = text;
        this.author = author;
        this.pageChangeCode = pageChangeCode;
        this.date = date;
    }
}
