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
	
	/*object�� DB�� ����.*/
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(@ModelAttribute("emp")Emp emp) {
		dao.save(emp);
		return "redirect:/viewemp";
	}
	
	/*list��  object�� ����*/
	/*�ᱹ, delete�� Update�� ����� ���ƿ��� �Ǿ� �ֱ� ��. -> �׷��ٸ�?? ���⼭ ���̵� �ٲپ��ִ� �Լ��� ����� �ȴ�.*/
	/*List������ �� �� ����. ���⼭. -> List������ �Ѱܼ�, dao�� ������ �Լ��� ����, id�� �ٲ� �� �ִ� ����� ������?*/
	@RequestMapping("/viewemp")
	public String viewemp(Model m) {
		List<Emp> list=dao.getEmployees();

		m.addAttribute("list",list);
		return "viewemp";
	}
	
	/*�̴� �־��� id�� object�� display���ش�.*/
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
