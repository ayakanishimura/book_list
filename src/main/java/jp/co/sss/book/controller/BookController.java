package jp.co.sss.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.sss.book.entity.Book;
import jp.co.sss.book.entity.Genre;
import jp.co.sss.book.form.BookForm;
import jp.co.sss.book.repository.BookRepository;
import jp.co.sss.book.repository.GenreRepository;

@Controller
public class BookController {

	@Autowired
	BookRepository bookrepository;
	
	@Autowired
	GenreRepository genrerepository;
	
  

	// 全件検索
	@RequestMapping("/book/findAll")
	public String showBookList(Model model) {
		model.addAttribute("books", bookrepository.findAllByOrderByBookIdAsc());
		model.addAttribute("genres", genrerepository.findAll());
		return "list";
	}

	// 書籍名あいまい検索
	
	@GetMapping(path ="/book/findByBookNameLike/")
    public String findBookName(){
        return "list";
    }
		
    @RequestMapping(path ="/book/findByBookNameLike/", method = RequestMethod.POST)
    public String findByBookNameLike(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("keyword", keyword);
        if (keyword != null && keyword.length() > 0) {
        	keyword = "%" + keyword + "%";
        } else {
        	keyword = "%";
        }

        model.addAttribute("books", bookrepository.findByBookNameLike(keyword));
        model.addAttribute("genres", genrerepository.findAll());
        return "list";
    }

    //ジャンル名検索
	@GetMapping(path ="/book/findByGenreName/")
    public String findGenreName(){	
        return "list";
    }
   
    @RequestMapping(path ="/book/findByGenreName/", method = RequestMethod.POST)
    public String findByGenreName(@RequestParam("genreId") int genreId, Model model) {
    	Genre genre = new Genre();
    	genre.setGenreId(genreId);
    	List<Book> books = bookrepository.findByGenre(genre);
    	model.addAttribute("books", books);
    	model.addAttribute("genres", genrerepository.findAll());
    	return "list";
    }
    
   //更新
    @RequestMapping("/book/update/{bookId}")
    public String updateInput(@PathVariable int bookId, Model model) {
    model.addAttribute("book", bookrepository.getOne(bookId));
    model.addAttribute("genres", genrerepository.findAll());
    return "update";
    }
    @RequestMapping(path = "/book/update/complete/{bookId}", method = RequestMethod.POST)
    public String updateComplete (@PathVariable int bookId, BookForm form) {
    //更新前の情報を取得して代入	
    Book book = bookrepository.getOne(bookId);
    book.setBookId(form.getBookId());
    book.setBookName(form.getBookName());
    book.setAuthor(form.getAuthor());
    book.setPublicationDate(form.getPublicationDate());
    book.setStock(form.getStock());
    
    //genreidをhtmlのフォームから取得して、genreテーブルからganreNameを持って来たものをsetする
    int genreId = form.getGenreId();
    Genre genre = genrerepository.getOne(genreId);
    book.setGenre(genre);
    
    bookrepository.save(book);
    return "redirect:/book/findAll";
    
    }
    
    //削除
    @RequestMapping("/book/delete/input")
    public String deleteInput() {
    return "delete";
    }
    @RequestMapping(path = "/book/delete/complete")
    public String deleteComplete(BookForm form) {
    bookrepository.deleteById(form.getBookId());
    return "redirect:/book/findAll";
    }

    //登録
    @RequestMapping("/book/create/input")
    public String createInput(Model model) {
     model.addAttribute("genres", genrerepository.findAll());
    return "create";
    }

    @RequestMapping(path = "/book/create/complete", method = RequestMethod.POST)
    public String createComplete(BookForm form) {
    Book book = new Book();
    book.setBookId(form.getBookId());
    book.setBookName(form.getBookName());
    book.setAuthor(form.getAuthor());
    book.setPublicationDate(form.getPublicationDate());
    book.setStock(form.getStock());
    
    int genreId = form.getGenreId();
    Genre genre = genrerepository.getOne(genreId);
    book.setGenre(genre);
    
    bookrepository.save(book);
    return "redirect:/book/findAll";
    }

    //条件検索（主キー）
    
    @RequestMapping("/book/getOne/{bookId}")
    public String showBook(@PathVariable int bookId, Model model) {
    model.addAttribute("book", bookrepository.getOne(bookId));
    return "detail";
    }

    
    
    
    
 
    
    
    

   

	
	

}
