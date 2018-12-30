package model;
public class DataNote
{
    String text;
    Integer img;
    String date;

    public DataNote(String text, Integer img, String date)
    {
        this.text = text;
        this.img = img;
        this.date = date;
    }

    public String getText()
    {
        return text;
    }

    public Integer getImg()
    {
        return img;
    }

    public String getDate()
    {
        return date;
    }
}