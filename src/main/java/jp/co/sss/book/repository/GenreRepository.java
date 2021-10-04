package jp.co.sss.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.book.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
