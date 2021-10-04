package jp.co.sss.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.book.entity.BookUser;

public interface BookUserRepository extends JpaRepository<BookUser, Integer> {
	BookUser findByBookUserIdAndPassword(int bookUserId, String password);
//	List<BookUser> findByBookUserId(Integer bookUserId);
}
