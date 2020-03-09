package com.example.ranjeetkumarrana.homeworkgurdian;

public class ListItem
{
    //public int image;
    public String name;
    public String description;

    public ListItem()
    {

    }

    public ListItem(String name, String description)
    {
        //this.image = image;
        this.name = name;
        this.description = description;
    }

    /*public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
