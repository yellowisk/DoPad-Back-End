package br.com.dopad.web.model.page.request;

import br.com.dopad.domain.entities.line.Line;

import java.sql.Timestamp;

public class LineDTO {
    private String text;
    private String author;
    private String pageChangeCode;
    private Timestamp date;

    public LineDTO(String text, String author, String pageChangeCode, Timestamp date) {
        this.text = text;
        this.author = author;
        this.pageChangeCode = pageChangeCode;
        this.date = date;
    }

    public Line convertToLine() {
        return new Line(text, author, pageChangeCode, date);
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

    public String getPageChangeCode() {
        return pageChangeCode;
    }

    public void setPageChangeCode(String pageChangeCode) {
        this.pageChangeCode = pageChangeCode;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
