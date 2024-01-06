package br.com.dopad.web.model.page.request;

import br.com.dopad.domain.entities.line.Line;

import java.sql.Timestamp;

public class LineDTO {
    private String text;
    private String author;
    private String changeCode;
    private Timestamp date;

    public LineDTO(String text, String author, String changeCode, Timestamp date) {
        this.text = text;
        this.author = author;
        this.changeCode = changeCode;
        this.date = date;
    }

    public Line convertToLine() {
        return new Line(text, author, changeCode, date);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getChangeCode() {
        return changeCode;
    }

    public void setChangeCode(String changeCode) {
        this.changeCode = changeCode;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
