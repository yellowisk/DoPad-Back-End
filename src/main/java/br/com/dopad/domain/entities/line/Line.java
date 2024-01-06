package br.com.dopad.domain.entities.line;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;

public class Line {
    private String text;
    private String author;
    private String changeCode;
    private Timestamp date;

    public Line(String text, String author, String changeCode, Timestamp date) {
        this.text = text;
        this.author = author;
        this.changeCode = changeCode;
        this.date = date;
    }

    public static String generateLineChangeCode(int index, String username, String title) {
        try {
            String inputString = username + title + System.currentTimeMillis() + index;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(inputString.getBytes(StandardCharsets.UTF_8));
            String encodedHash = Base64.getEncoder().encodeToString(hashBytes);
            return encodedHash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Line> setForPage(List<Line> lines) {
        return lines.stream().map(line -> {
            line.setChangeCode(generateLineChangeCode(lines.indexOf(line), line.getAuthor(), line.getText()));
            return line;
        }).toList();
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Line{");
        sb.append("text='").append(text).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", changeCode='").append(changeCode).append('\'');
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
