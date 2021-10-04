package jp.co.sss.book.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.book.entity.BookUser;
import jp.co.sss.book.form.LoginForm;
import jp.co.sss.book.repository.BookUserRepository;

@Controller
public class SessionController {
	@Autowired
	private BookUserRepository userRepository;
//
//	@RequestMapping("/")
//	public String index() {
//		return "index";
//	}
	
	@RequestMapping("/")
	public String index(@ModelAttribute LoginForm form) {
		return "index";
	}
	
	//ログイン（入力チェック実装）
	@RequestMapping(path = "/book/login", method = RequestMethod.POST)
	public String doLogin(@Valid @ModelAttribute LoginForm form, BindingResult result, HttpSession session, Model model) {		
		
		int userId = form.getBookUserId();
		String password = form.getPassword();
		BookUser user = userRepository.findByBookUserIdAndPassword(userId, password);

		if (user != null) {
			session.setAttribute("userName", user.getBookUserName());
			session.setAttribute("bookuserId", user.getBookUserId());
			// TODO 下記コードを、書籍一覧表示用のビューにリダイレクトするように編集してください。
			return "redirect:/book/findAll";

		} else {
				model.addAttribute("errMessage", "ユーザID、またはパスワードが間違っています。");	
				return "index";
		}
		

	}

	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		// セッションの破棄
		session.invalidate();
		return "redirect:/";
	}

}
