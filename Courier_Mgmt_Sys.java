import java.io.*;
import java.util.*;
import java.util.Scanner.*;
import java.lang.*;

class InvalidException extends Exception 
{
  public InvalidException(String message)
  {
	  super(message);
  }

}

public class Courier_Mgmt_Sys 
{
	
	public static void main(String args[]) throws IOException, InvalidException
	{
		String temp;
		String n;
	   	int flag=0;
		int sc,sd,id=0;
		String city_list []={"Mumbai","Delhi","Bangalore","Pune","Kolkata","Chennai","Jaipur","Bhopal"};
		int w=0,j; 
		Directory d=new Directory();
		int s,choice;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));	
		System.out.println("***Welcome to Courier Services India Pvt. Ltd.***");
		do
		{
			System.out.println("1.Place a consignment:");
			System.out.println("2.Generate consignment receipt:");
			System.out.println("3.Track your consignment:");
			System.out.println("Enter your choice:");
			temp=br.readLine();
			s=Integer.parseInt(temp);
			switch(s)
			{
				case 1:System.out.println("**Place a consignment**");
                       System.out.println("Enter the customer's name:");
                       n=br.readLine();
                       do
				   	   {
				   		   flag=0;
				   		   System.out.println("Enter the package weight (in g):");
				   		   try
				   		   {
				   			   temp=br.readLine();
				   			   w=Integer.parseInt(temp);
				   		   }
				   		   catch(Exception e)
				   		   {
				   			   System.out.println("Incorrect weight format:"+e);
				   			   flag=1;
				   		   }
				   		   if(w<100)
				   		   {
				   			   System.out.println("Weight cannot be less than 100g.");
				   			   flag=1;
				   		   }
				   		if(w>10000)
				   		   {
				   			   System.out.println("Weight cannot be greater than 10 kg.");
				   			   flag=1;
				   		   }
				   	   }while(flag==1); 
					   j=1;
					   for(int i=0;i<8;i++)
					   {
						   System.out.println(j+"."+city_list[i]);
						   j++;
					   }
					   System.out.println("Enter the source city code:");
					   temp=br.readLine();
					   sc=Integer.parseInt(temp);
					   if(sc<1||sc>8)
						   throw new InvalidException("City code entered does not exist. Please restart.");
					   System.out.println("Enter the destination city code:");
					   temp=br.readLine();
					   sd=Integer.parseInt(temp);
					   if(sd<1||sd>8||sd==sc)
						   throw new InvalidException("City code entered does not exist or cannot be same as source city code. Please restart.");
                       d.accept_order(n,w,sc,sd);
					   break;
					   
				case 2:System.out.println("**Generate consignment receipt**");
				   	   System.out.println("Enter the customer's name:");
				   	   n=br.readLine();
				   	   do
				   	   {
				   		   flag=0;
				   		   System.out.println("Enter the consignment ID:");
				   		   try
				   		   {
				   			   temp=br.readLine();
				   			   id=Integer.parseInt(temp);
				   		   }
				   		   catch(Exception e)
				   		   {
				   			   System.out.println("Incorrect ID format:"+e);
				   			   flag=1;
				   		   }
				   	   }while(flag==1);
				   	   d.print_receipt(n,id);
				   	   break;
				
				case 3:System.out.println("**Track your consignment**");
			   	   	   System.out.println("Enter the customer's name:");
			   	       n=br.readLine();
			   	       do
				   	   {
				   		   flag=0;
				   		   System.out.println("Enter the consignment ID:");
				   		   try
				   		   {
				   			   temp=br.readLine();
				   			   id=Integer.parseInt(temp);
				   		   }
				   		   catch(Exception e)
				   		   {
				   			   System.out.println("Incorrect ID format:"+e);
				   			   flag=1;
				   		   }
				   	   }while(flag==1);
			   	       d.track_order(n,id);   	   
				   	   break;
				
				default:System.out.println("Invalid choice.");
						break;
			}
			System.out.println("Do you want to continue? (Enter 1:YES  Enter 0:NO)");
			temp=br.readLine();
			choice=Integer.parseInt(temp);
			if(choice!=0 && choice!=1)
			{
				System.out.println("Invalid choice entered. Terminating.");
				System.exit(0);
			}
		}while(choice==1);
	}
}