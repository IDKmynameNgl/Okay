
import java.util.Scanner;
import java.io.*;
import java.io.File;
import java.io.IOException;

class mortgageapplacations{
	public static void main (String[]args){
		Scanner in = new Scanner(System.in); 
		System.out.println("Welcome to loan CALCULATOR Please select the option that best fits you ");
		System.out.println("1 - Loan Approval Calculator");
		System.out.println("2 - Monthly Payment Calculator");
		System.out.println("3 - Loan Approval Reader");
		System.out.println("4 - Exit");
int Choice = in.nextInt(); 	// Switch statement allows user to select what they want to do  
			switch (Choice) {
				case 1: System.out.println("You have selected the Loan Approval Calculator");
					System.out.println("Lets see if you are approved");
					System.out.println("Type your first name");
					String firstName = in.next();
					System.out.println("Type your Last name");
					String lastName = in.next();	//Takes in users name and last name here to implement inside the print 
				if (loanCalc() == false){	//opens the method and checks the conditons and gives the following print based on true or false
					System.out.println("Sorry, " + firstName + " " + lastName + " You have been DENIED for a mortgage loan!");
				}else{
					System.out.println("CONGRAGULATIONS, " + firstName + " " + lastName + " You have been APPROVED for a mortgage loan!");

				}
				break;
				case 2:
				System.out.println("You have selected the Monthly Payment Calculator");
				System.out.println("Please enter your credit score (300-850) ");
				double creditScore = in.nextInt();
				System.out.println("Please enter your desired home value (nearest dollar) ");
				int homeValue = in.nextInt();
				System.out.println("Please enter the amount of month on this loan 180 or 360");
				int numMonth = in.nextInt();
				// monthlyCalc(creditScore,totalSavings,homeValue,numMonth); // takes all user input then it all goes inside the method to result in getting the total value of monthly payments  
				System.out.println(monthlyCalc(creditScore,homeValue,numMonth));
				break;
				case 3:
				System.out.println("you have selected the Loan Approval Reader");
				System.out.println(loanDecisions());		//calls the following method

				break;
				case 4:
				break;
		
		
	}
	
}					// Opening a method that takes the values needed if the loan is approved 
	public static boolean loanCalc (){
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter your Gross annual income (nearest dollar)");
		int annualIncome = in.nextInt();
		System.out.println("Please enter your Total Savings (nearest dollar)");
		int totalSavings = in.nextInt();
		System.out.println("Please enter your credit score (300-850) ");
		double creditScore = in.nextInt();
		System.out.println("Please enter the amount of month on this loan 180 or 360");
		int numMonth = in.nextInt();
		System.out.println("Please enter your desired home value (nearest dollar) ");
		int homeValue = in.nextInt();
		return isLoanapproved(annualIncome, totalSavings, creditScore, numMonth, homeValue);
	}			// This method is called with the follwing values to see if the statement is true or false
	public static boolean isLoanapproved (int annualIncome, int totalSavings, double creditScore, int numMonth,int homeValue ){
		Scanner in = new Scanner(System.in);
		boolean flag = true;
		
		double netIncome = annualIncome*.68;
		double netMonthly = netIncome/12;
		if (homeValue*3.5 > annualIncome){
			flag = false;
		}
		if (downPayment(creditScore, homeValue)*homeValue >= totalSavings ){
			flag = false;
		}
		if(netMonthly <= (monthlyCalc(creditScore,homeValue,numMonth)*.48)){
			flag = false;
		}
		return flag;
	}		// This method is called to find the interest rate 
	public static double interestRate(double creditScore){
		double interestRate= 0.00;
		if(799 < creditScore && creditScore < 851){
			interestRate= 0.028;		
		}
		if(739 < creditScore && creditScore < 800){
			interestRate= 0.03;
		}
		if(669 < creditScore && creditScore <740){
			interestRate= 0.032;
		}
		if(579 < creditScore && creditScore < 560){
			interestRate= 0.35;
		}
		if(299 < creditScore && creditScore < 580){
			interestRate= 0.04;
		}
		return interestRate;
	}		//This method is called to find the value of the down payment
	public static double downPayment(double creditScore, int homeValue){
		double downPayment = 0.05;
		if(799 < creditScore && creditScore < 851){
			downPayment = 5*homeValue/100;
		}
		if(739 < creditScore && creditScore < 800){
			downPayment = 7*homeValue/100;
		}
		if(669 < creditScore && creditScore <740){
			downPayment = 10*homeValue/100;
		}
		if(579 < creditScore && creditScore < 560){
			downPayment = 15*homeValue/100;
		}
		if(299 < creditScore && creditScore < 580){
			downPayment = 20*homeValue/100;
		}
		return downPayment;
	}				//This is a calculator to find the amount the user has to pay per month for a ceratin home
	public static double monthlyCalc (double creditScore, int homeValue, int numMonth){
		double interestRate = interestRate(creditScore);
		double downPayment = downPayment(creditScore, homeValue);
		double totalLoan = homeValue - downPayment;
		double monthlyRate = interestRate/12;
		double totalPayment = ((totalLoan * (monthlyRate * Math.pow(1+monthlyRate, numMonth)))/(Math.pow(1 + monthlyRate, numMonth)-1));
		// System.out.println("Hi" + totalPaymet);
		return totalPayment;
	}			// This metod opens the csv file and puts it into an array
	public static boolean loanDecisions (){
		
		boolean flag = false;
		
		File Apps = new File("MortgageApplications.csv");
		try{
		Scanner in = new Scanner (Apps);
		int count = 0;
		while (in.hasNext()){
			String temp = in.nextLine();
			String[][] applacations = new String [37][7];
			applacations[count]= temp.split(",");
			count++;
		String firstName = "";
		for(int i=1; i< applacations.length; i++) {
				 firstName = applacations[i][0];
		}
		String lastName = "";
		for(int i=1; i< applacations.length; i++){
				lastName =  applacations[i][1];
		}
		int annualIncome = 0;
		for(int i=1; i< applacations.length; i++) {
				 annualIncome = Integer.parseInt(applacations[i][2]);
		}
		int totalSavings = 0;
		for(int i=1; i< applacations.length; i++) {
				 totalSavings = Integer.parseInt(applacations[i][3]);
		}
		double creditScore = 0.00;
		for(int i=1; i< applacations.length; i++) {
				 creditScore = Double.parseDouble(applacations[i][4]);
		}
		int numMonth = 0;
		for(int i=1; i< applacations.length; i++) {
				 numMonth = Integer.parseInt(applacations[i][5]);
		}
		int homeValue = 0;
		for(int i=1; i< applacations.length; i++) {
				 homeValue = Integer.parseInt(applacations[i][6]);
		}
		isLoanapproved(annualIncome, totalSavings, creditScore, numMonth, homeValue);
		}
		}catch (IOException e) {
           e.printStackTrace();
		}
		return flag; 
	}
}



		
}

