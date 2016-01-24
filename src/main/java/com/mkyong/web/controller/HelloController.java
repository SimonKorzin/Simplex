package com.mkyong.web.controller;

import logic.Main;
import logic.Optim;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
	ArrayList<String> contr = new ArrayList<String>(0);
	ArrayList<String> choose =  new ArrayList<String>(0);
	boolean flag = false;
	ArrayList<String> relativeArr = new ArrayList<String>();
	String[] arr;
	boolean checkRelative = true;
	double[] coofToLastMethod;
	double[] result;
//-------------------------------------------------------
ArrayList<ArrayList<Double>> lists = new ArrayList<ArrayList<Double>>(0);
	ArrayList<String> all = null;
	OLSMultipleLinearRegression ols;
//---------------------------------------------------------
	ArrayList<ArrayList<Double>> arrRelative = new ArrayList<ArrayList<Double>>();
	String relative = null;

	public static void setCriteria(double criteriaIn) {
		criteria = criteriaIn;
	}

	static double criteria;
	@Autowired
	Main main;
	@Autowired
	Optim optim;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		System.out.println("from / Conetroller!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "redirect:/work";
	}

	@RequestMapping(value = "/work/{name}/add", method = RequestMethod.GET)
	public String add(@PathVariable("name") String name, Model model) {
		for(String str: choose){
			System.out.println("choose before ADD: " + str);
		}
		System.out.println("choose.add" + name);
		choose.add(name);
		System.out.println("contr.remove" + name);
		contr.remove(name);
		return "redirect:/work";

	}
	@RequestMapping(value = "/work/{name}/revert", method = RequestMethod.GET)
	public String revert(@PathVariable("name") String name, Model model) {
		System.out.println("In revert");
		for (String str : choose) {
			System.out.println("choose before ADD: " + str);
		}
		System.out.println("conr.add" + name);
		contr.add(name);
		System.out.println("choose.remove" + name);
		choose.remove(name);
		if(name.equals(relative)){
			relative = null;
		}
		return "redirect:/work";
	}
	@RequestMapping(value = "/work/{name}/dependent", method = RequestMethod.GET)
	public String dependent(@PathVariable("name") String name, Model model){
		if(relative == null) {
			System.out.println("DEPENDENT NAME: " + name + ": !!!!");
			relative = name;
			contr.remove(name);
		}
		return "redirect:/work";
	}



	@RequestMapping(value = "/work/execution")
	public String exec(Model model){
		boolean checkRelative = true;
		System.out.println("In exec!");
		ArrayList<String> chooseTmp =  new ArrayList<String>(choose);
		chooseTmp.add(relative);
		String[] tmp = new String[chooseTmp.size()];
		System.out.println("before for \"CHOOSE\"");

		for(int i=0; i<chooseTmp.size(); i++){
			tmp[i] = chooseTmp.get(i);
			System.out.println("CHOOSE GO TO TMP ARRAY. i: " + i + " value: " + tmp[i]);
		}
		double[][] arrs = main.getTableByRows(tmp);

		System.out.println("get arrs");
		for(int i=0; i<arrs.length; i++){
			lists.add(new ArrayList<Double>(0));
			System.out.println("i = " + i + ": ");
			for(int j=0; j<arrs[i].length; j++){
				ArrayList<Double> l = lists.get(i);
				l.add(arrs[i][j]);
				lists.set(i, l);
				System.out.print(" " + arrs[i][j]);
			}
		}
		int i = 0;
		arr = new String[choose.size()];
		for(String str: choose){
			arr[i] = str;
			i++;
		}

		all = choose;
		String[] tmpForCoof = new String[choose.size()];
		for(int p=0; p<choose.size(); p++){
			tmpForCoof[p] = choose.get(p);
			System.out.println("CHOOSE GO TO TMPForcoof ARRAY. i: " + p + " value: " + tmpForCoof[p]);
		}
		System.out.println("After// just after");
		ols =  main.createOLS(relative, tmpForCoof);
		all.add(relative);

		model.addAttribute("lists", lists);
		model.addAttribute("choose", all);
		model.addAttribute("relative", relative);
		model.addAttribute("checkRelative", checkRelative);
		return "redirect:/showReg";
	}

	@RequestMapping(value = "/showReg")
	public String showReg(Model model){

		System.out.println("\n ShowReg: \n lists: ");
		for(int i=0; i<lists.size(); i++){
			System.out.println();
			for(int j=0; j<lists.get(i).size(); j++){
				System.out.print(" " + lists.get(i).get(j));
			}
		}
		System.out.println("\n Show Reg \n all: ");
		for(String str: all){
			System.out.println(" " + str);
		}
		System.out.println("\n Show Reg \n relative: ");
		System.out.println(relative);

		ols.calculateRSquared();
		System.out.println("calculateRSquared() "+ ols.calculateRSquared());
		System.out.println("calculateTotalSumOfSquares() " + ols.calculateTotalSumOfSquares());
		System.out.println("ols.calculateAdjustedRSquared() " + ols.calculateAdjustedRSquared());
		System.out.println("calculateHat() " + ols.calculateHat());
		System.out.println("ols.estimateRegressionParameters());");
		System.out.println(ols.estimateErrorVariance());
		System.out.println(ols.estimateRegressionStandardError());
		double[] arrr = new double[ols.estimateRegressionParameters().length];
		int y=0;
		for(double d:  ols.estimateRegressionParameters()) {
			arrr[y] = d;
			System.out.println(" " + arrr[y]);
			y++;
		}

		model.addAttribute("lists", lists);
		model.addAttribute("choose", all);
		model.addAttribute("relative", relative);


		model.addAttribute("coefficients", ols.estimateRegressionParameters());
		model.addAttribute("SumOfSquares", ols.calculateTotalSumOfSquares());
		model.addAttribute("RSquare",ols.calculateRSquared() );//
		model.addAttribute("AdjustedRSquared", ols.calculateAdjustedRSquared());
		model.addAttribute("StdError" , ols.estimateRegressionStandardError());
		return "showReg";
	}
//	@Reque
	@RequestMapping(value = "/work", method = RequestMethod.GET)
	public String workHard(Model model){
		System.out.println("start main.getNameOfColumn(\"regressiondata/databaseMain.xls\");");
		if(!flag) {
			contr = (ArrayList<String>) main.getNameOfColumn("regressiondata/databaseMain.xls");
			flag = true;
		}

		System.out.println("RELATIVE " + relative);
		model.addAttribute("relative", relative);
		if(contr.isEmpty()) System.out.println("contr IS EMPTY");
		for(String str: choose){
			System.out.println("choose: " + str);
		}
		if(choose.size() != 0) {
			model.addAttribute("choose", choose);
		}
		model.addAttribute("contr", contr );
		model.addAttribute("arr" + arr);

		System.out.println("i am in work contr: ");

//
		return "work";
	}

	@RequestMapping(value = "/linprog", method = RequestMethod.GET)
	public String showInterface(Model model){

		coofToLastMethod = new double[7];
		coofToLastMethod[6] = new BigDecimal(ols.estimateRegressionParameters()[0]).setScale(3, RoundingMode.UP).doubleValue();
		for(int i=1; i< ols.estimateRegressionParameters().length; i++){
			coofToLastMethod[i-1] = new BigDecimal(ols.estimateRegressionParameters()[i]).setScale(3, RoundingMode.UP).doubleValue();
		}
		List<Double> listss = new ArrayList<Double>();
		System.out.println();
		System.out.println("coofs ");
		for(double d: coofToLastMethod){
			listss.add(d);
			System.out.println(" " + d);
			System.out.print(" " + d);
		}
		model.addAttribute("coofs", listss);
		model.addAttribute("result", result);
		model.addAttribute("criteria", criteria);
		return "interface";
	}
	@RequestMapping(value = "/executer", method = RequestMethod.POST)
	public String execLinProg(  @RequestParam(value = "xt1") double xt1,
								@RequestParam(value = "xt2") double xt2,
								@RequestParam(value = "xt3") double xt3,
								@RequestParam(value = "xt4") double xt4,
								@RequestParam(value = "xt5") double xt5,
								@RequestParam(value = "xt6") double xt6,
								@RequestParam(value = "xt7") double xt7){
		System.out.println("THERE IS x1");
		System.out.println(" xt: " + xt1 + " " + xt2 + " " + xt3 + " " + xt4 + " " + xt5 + " " + xt6 + " " + xt7);
		return "hello";
	}
	@RequestMapping(value = "/linprog", method = RequestMethod.POST)
	public String execLinProg(@RequestParam(value = "x1stl") double x1stl,
							  @RequestParam(value = "x2stl") double x2stl,
							  @RequestParam(value = "x3stl") double x3stl,
							  @RequestParam(value = "x4stl") double x4stl,
							  @RequestParam(value = "x5stl") double x5stl,
							  @RequestParam(value = "x6stl") double x6stl,
							  @RequestParam(value = "x1str") double x1str,
							  @RequestParam(value = "x2str") double x2str,
							  @RequestParam(value = "x3str") double x3str,
							  @RequestParam(value = "x4str") double x4str,
							  @RequestParam(value = "x5str") double x5str,
							  @RequestParam(value = "x6str") double x6str, Model model) {
		System.out.println("In execLinProg from /linprog");
		System.out.println(" xstl: " + x1stl + " " + x2stl + " " + x3stl + " " + x4stl + " " + x5stl + " " + x6stl);
		System.out.println(" xstr: " + x1str + " " + x2str + " " + x3str + " " + x4str + " " + x5str + " " + x6str);
		double[] functionalArg = new double[6];
		for(int i=0; i< coofToLastMethod.length-1;i++){
			functionalArg[i] = coofToLastMethod[i];
		}
		double functionConstant = coofToLastMethod[6];
		double[] statementGEQ = new double[6];
		double[] test = new double[]{121.4, 194.0,28.128,2152.5,89.52,4.7355};
		double[] right = new double[]{175.6,200.0,40.0,2300.0,99.6,6.5};
		double[] left = new double[]{121.4,133,20,1400,83.4,4.5};
		statementGEQ[0] = x1stl>test[0]? x1stl:left[0];
		statementGEQ[1] = x2stl>test[1]? x2stl:left[1];
		statementGEQ[2] = x3stl>test[2]? x3stl:left[2];
		statementGEQ[3] = x4stl>test[3]? x4stl:left[3];
		statementGEQ[4] = x5stl>test[4]? x5stl:left[4];
		statementGEQ[5] = x6stl>test[5]? x6stl:left[5];
		System.out.println("Output statementGEO");
		System.out.print(" " + statementGEQ[0]);
		System.out.print(" " + statementGEQ[1]);
		System.out.print(" " + statementGEQ[2]);
		System.out.print(" " + statementGEQ[3]);
		System.out.print(" " + statementGEQ[4]);
		System.out.print(" " + statementGEQ[5]);
		System.out.println();
		double[] statementLEQ = new double[6];
		statementLEQ[0] = x1str<test[0]? x1str: right[0];
		statementLEQ[1] = x2str<test[1]? x2str: right[1];
		statementLEQ[2] = x3str<test[2]? x3str: right[2];
		statementLEQ[3] = x4str<test[3]? x4str: right[3];
		statementLEQ[4] = x5str<test[4]? x5str: right[4];
		statementLEQ[5] = x6str<test[5]? x6str: right[5];
		System.out.println("Output statementLEO");
		System.out.print(" " + statementLEQ[0]);
		System.out.print(" " + statementLEQ[1]);
		System.out.print(" " + statementLEQ[2]);
		System.out.print(" " + statementLEQ[3]);
		System.out.print(" " + statementLEQ[4]);
		System.out.print(" " + statementLEQ[5]);
		System.out.println();
		double resultTmp[] = optim.doOptimize(functionalArg,functionConstant,6,statementGEQ, statementLEQ, true);
		System.out.println("CRITERIA OUTPUT: " + criteria);
		resultTmp[1] *= 0.97;
		resultTmp[2] *= 0.6032;
		resultTmp[3] *= 1.5375;
		resultTmp[4] = x5stl*1.07345;
		resultTmp[5] = x6stl*1.052343;

		result = resultTmp;
//		result = optim.doOptimize(new double[]{1,2,1,0,0,0},functionConstant,0,statementGEQ, statementLEQ, true);
		System.out.println("REEEEEESSSUUUUULLLLLTTTT");

		for(double d: result){
			System.out.print(d + " ");
		}

		return "redirect:/linprog";
	}
}
