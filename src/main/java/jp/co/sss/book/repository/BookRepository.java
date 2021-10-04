package jp.co.sss.book.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.book.entity.Book;
import jp.co.sss.book.entity.Genre;


public interface BookRepository extends JpaRepository<Book, Integer> {
	
	//全件検索（並び替え）
	List<Book> findAllByOrderByBookIdAsc();
	
	//書籍名曖昧検索
	List<Book> findByBookNameLike(String keyword);
	
	//ジャンル名外部キー条件検索
	List<Book> findByGenre(Genre genre);
	
	
	

}
