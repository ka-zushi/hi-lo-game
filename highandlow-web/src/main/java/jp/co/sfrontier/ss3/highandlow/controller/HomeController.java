package jp.co.sfrontier.ss3.highandlow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.sfrontier.ss3.highandlow.card.Card;
import jp.co.sfrontier.ss3.highandlow.card.Rank;
import jp.co.sfrontier.ss3.highandlow.card.Suit;

/**
 * ホーム画面へのリクエストを処理するクラス
 */
@Controller
public class HomeController {

	/**
	 * ホーム画面を表示する。
	 * 
	 * @param model 画面に渡すデータ
	 * @return ホーム画面のテンプレート名
	 */
	@GetMapping("/")
	public String home(Model model) {
		
		Card card = new Card(Suit.HEART, Rank.SEVEN);
		
		model.addAttribute("card", card);
		
		return "home";
	}
	
}
