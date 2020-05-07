package com.example.ranjeetkumarrana.homeworkgurdian;

public class UploadPdf {

    public String namePDF;
    public String url;

    public UploadPdf(){}

    public UploadPdf(String namePDF, String url) {
        this.namePDF = namePDF;
        this.url = url;
    }

    public String getNamePDF() {
        return namePDF;
    }

    //public void setNamePDF(String namePDF) {
      //  this.namePDF = namePDF;
    //}

    public String getUrl() {
        return url;
    }

   // public void setUrl(String url) {
    //    this.url = url;
   // }
}
