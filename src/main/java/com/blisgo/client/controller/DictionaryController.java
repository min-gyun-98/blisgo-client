package com.blisgo.client.controller;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.blisgo.client.dto.DictionaryDTO;
import com.blisgo.client.dto.GuideDTO;
import com.blisgo.client.service.DictionaryService;

@Controller
public class DictionaryController {

	@Autowired
	private DictionaryService dictionaryService;

	// 분리배출 사전
	@GetMapping("dictionary")
	public String dictionary(Model model, HttpServletRequest request) {

		String category_big = request.getParameter("category_big");
		String category_mid = request.getParameter("category_mid");
		String name = request.getParameter("category_small");

		System.out.println(category_big);
		System.out.println(category_mid);
		System.out.println(name);

		DictionaryDTO search_dic = new DictionaryDTO();
		search_dic.setCategory_big(category_big);
		search_dic.setCategory_mid(category_mid);
		search_dic.setName(name);

		System.out.println(search_dic.getName());
		ArrayList<DictionaryDTO> products = dictionaryService.searchDictionary(search_dic);
		model.addAttribute("products", products);

		return "dictionary";
	}

	// 분리배출 물품 안내
	@GetMapping("product")
	public String product(Model model, HttpServletRequest request) {
		// 선택한 product 정보 출력
		int dic_no = Integer.parseInt(request.getParameter("dic_no"));
		System.out.println("dic_no>" + dic_no);

		DictionaryDTO product = dictionaryService.productInfo(dic_no);

		model.addAttribute("product", product);
		// 선택한 product의 중분류를 토큰으로 나누어 출력해야할 내용을 저장하여 tabs 출력
		ArrayList<String> category_mid_list = new ArrayList<String>();

		StringTokenizer tag_token_str = new StringTokenizer(product.getCategory_mid(), "/");

		System.out.println("받은 태그 토큰>" + tag_token_str);

		ArrayList<GuideDTO> guides = dictionaryService.printGuide(product.getCategory_mid());

		while (tag_token_str.hasMoreTokens()) {
			category_mid_list.add(tag_token_str.nextToken());
		}

		model.addAttribute("category_mid_list", category_mid_list);
		model.addAttribute("guides", guides);
		
		// 중분류 기준 연관된 product 나열
		ArrayList<DictionaryDTO> relatedProducts = dictionaryService.relatedProduct(category_mid_list);		
		model.addAttribute("relatedProducts", relatedProducts);

		return "product";
	}
	// -----------------------------------------------------//

}
