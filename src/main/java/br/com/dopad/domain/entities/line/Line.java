package br.com.dopad.domain.entities.line;

import java.sql.Timestamp;
import java.util.*;

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

    public static Line createInstanceFromList(List<String> content) {
            return new Line(content.get(0), content.get(1),
                    content.get(2), Timestamp.valueOf(content.get(3)));
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("text", text);
        map.put("author", author);
        map.put("changeCode", pageChangeCode);
        map.put("date", date.toString()); // Convert Timestamp to String for simplicity
        return map;
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
