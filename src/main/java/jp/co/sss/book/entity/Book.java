package jp.co.sss.book.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "book")

public class Book {
	
	@Id
	private Integer bookId;
	
	@Column
	private String bookName;
	
	@Column
	private String author;
	
	@Column
	private Date publicationDate;
	
	@Column
	private Integer stock;
	
	@ManyToOne
	@JoinColumn(name = "genre_id", referencedColumnName = "genreId")
	private Genre genre;

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}


	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}


	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	
	

	
	
	
	

}
