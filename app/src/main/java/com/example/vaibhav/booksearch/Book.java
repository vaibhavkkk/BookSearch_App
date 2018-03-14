package com.example.vaibhav.booksearch;

/**
 * Created by vaibhav on 07-07-2017.
 */

public class Book {
    String id,selfLink,title,subTitle,authors,publisher,publishedDate,description,pageCount,categories,averageRating,maturity,
            smallThumbnail,LargeThumbnail,language,previewLink,infoLink,buyLink,downloadLink,webreaderLink, country,saleability;
    Boolean isEbook,pdfAvailable;

    public Book(String id, String selfLink, String title, String subTitle, String authors, String publisher, String publishedDate, String description, String pageCount, String categories, String averageRating, String maturity, String smallThumbnail, String largeThumbnail, String language, String previewLink, String infoLink, String country, String saleability,String buyLink,String downloadLink,String webreaderLink, Boolean isEbook,  Boolean pdfAvailable) {
        this.id = id;
        this.selfLink = selfLink;
        this.title = title;
        this.subTitle = subTitle;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.pageCount = pageCount;
        this.categories = categories;
        this.averageRating = averageRating;
        this.maturity = maturity;
        this.smallThumbnail = smallThumbnail;
        LargeThumbnail = largeThumbnail;
        this.language = language;
        this.previewLink = previewLink;
        this.infoLink = infoLink;
        this.country = country;
        this.saleability = saleability;
        this.buyLink=buyLink;
        this.downloadLink=downloadLink;
        this.webreaderLink=webreaderLink;
        this.isEbook = isEbook;
        this.pdfAvailable = pdfAvailable;
    }

    public String getId() {
        return id;
    }

    public String getBuyLink() {
        return buyLink;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public String getWebreaderLink() {
        return webreaderLink;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public String getPageCount() {
        return pageCount;
    }

    public String getCategories() {
        return categories;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public String getMaturity() {
        return maturity;
    }

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public String getLargeThumbnail() {
        return LargeThumbnail;
    }

    public String getLanguage() {
        return language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public String getCountry() {
        return country;
    }

    public String getSaleability() {
        return saleability;
    }

    public Boolean getEbook() {
        return isEbook;
    }

    public Boolean getPdfAvailable() {
        return pdfAvailable;
    }

}
