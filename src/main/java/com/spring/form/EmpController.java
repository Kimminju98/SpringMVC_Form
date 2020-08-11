package com.spring.form;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.beans.Emp;
import com.spring.dao.EmpDao;

@Controller
public class EmpController {
	
	@Autowired
	EmpDao dao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String FirstPage() {
		return "FirstPage";
	}
	
	@RequestMapping(value="/list")
	public String list(Model m) {
		List<Emp> list=dao.getEmployees();
		m.addAttribute("list",list);
		return "redirect:/viewemp";
	}
	
	/**/
	@RequestMapping("/empform")
	public String showform(Model m) {
		m.addAttribute("command",new Emp());
		return "empform";
	}
	
	/*object를 DB에 저장.*/
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(@ModelAttribute("emp")Emp emp) {
		dao.save(emp);
		return "redirect:/viewemp";
	}
	
	/*list를  object로 전달*/
	/*결국, delete건 Update건 여기로 돌아오게 되어 있긴 함. -> 그렇다면?? 여기서 아이디를 바꾸어주는 함수를 만들면 된다.*/
	/*List개수를 알 수 있음. 여기서. -> List개수를 넘겨서, dao에 적절한 함수를 만들어서, id를 바꿀 수 있는 방법이 없을까?*/
	@RequestMapping("/viewemp")
	public String viewemp(Model m) {
		List<Emp> list=dao.getEmployees();

		m.addAttribute("list",list);
		return "viewemp";
	}
	
	/*이는 주어진 id로 object를 display해준다.*/
	@RequestMapping(value="/editemp/{id}")
	public String edit(@PathVariable int id, Model m) {
		Emp emp = dao.getEmpById(id);
		m.addAttribute("command",emp);
		return "empeditform";
	}
	
	/*DB Table's value Updates*/
	@RequestMapping(value="/editsave", method=RequestMethod.POST)
	public String editsave(@ModelAttribute("emp")Emp emp) {
		dao.update(emp);
		return "redirect:/viewemp";
	}
	
	/*DB Table's value Deletes*/
	@RequestMapping(value="/deleteemp/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable int id) {
		dao.delete(id);
		return "redirect:/viewemp";
	}
	
	
	
	

}
