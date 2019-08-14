import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.Scanner.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;

class Node
{
	String cust_name;
	int pack_id;
	int pack_wt;
	int sc_code;
	int dc_code;
	String s_city;
	String d_city;
	String dt;
	int t;
	Node next;
	
	Node()
	{
		cust_name="null";
		pack_id=-1;
		pack_wt=0;
		s_city="null";
		d_city="null";
		sc_code=-1;
		dc_code=-1;
	}
};

public class Directory extends Map
{
	Node [] head=new Node[10];
	String city_list []={"Mumbai","Delhi","Bangalore","Pune","Kolkata","Chennai","Jaipur","Bhopal"};
	
	public Directory()
	{
		for(int i=0;i<10;i++)
		{
			head[i]=new Node();
			head[i].next=null;
		}
	}
	
	void accept_order(String n,int w,int sc,int dc)
	{
		int key,index;
		Node curr,temp;
		key=0;
		curr=new Node();
		curr.next=null;
		curr.cust_name=n;
		curr.pack_wt=w;
		curr.s_city=city_list[sc-1];
		curr.d_city=city_list[dc-1];
		curr.sc_code=sc-1;
		curr.dc_code=dc-1;
		curr.pack_id=(sc*1000)+(dc*100)+((sc*10)*2)+(dc+1);
		curr.t=compute(curr.sc_code,curr.dc_code)/50;
		
		key=curr.pack_id;
		index=hashfunction(key);
		temp=head[index];
		while(temp.next!=null)
		{
				temp=temp.next;
		}
		temp.next=curr;
		System.out.println();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		System.out.println("Date and Time:");
		System.out.println(dtf.format(now));
		curr.dt=dtf.format(now);
		System.out.println("Your consignment has been accepted and placed.");
		System.out.println("Your consignment ID is: "+curr.pack_id);
	}

	int hashfunction(int key)
	{
		int index;
		index=key%10;
		return index;
	}

	void print_receipt(String n,int id)
	{
		int key=0,index,flag,dist;
		double amount=0;
		Node temp;
		key=id;
		index=hashfunction(key);
		temp=head[index].next;
		flag=0;
		while(temp!=null)
		{
			if(n.equals(temp.cust_name))
			{
				flag=1;
				System.out.println("***Consignment Summary***");
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();  
				System.out.println("Date and Time of receipt generation:");
				System.out.println(dtf.format(now));
				System.out.println("Customer Name:"+temp.cust_name);
				System.out.println("Consignment ID:"+temp.pack_id);
				System.out.println("Package weight:"+temp.pack_wt);
				System.out.println("City of origin:"+temp.s_city);
				System.out.println("Destination city:"+temp.d_city);
				dist=compute(temp.sc_code,temp.dc_code);
				System.out.println();
				amount=((dist/50)*30)+(temp.pack_wt/100)*2+20;
				System.out.println("Total Amount:"+amount);
				System.out.println("(Charges: Rs.30 per 50km + Rs.2 per 100g+ Rs.20 service charge.)");
				break;
			}
			temp=temp.next;
		}
		if(flag==0)
			System.out.println("Above Consignment ID or name is invalid. No record found.");
	}
	
	void track_order(String n,int id)
	{
		int key=0,index,flag,dist;
		Node temp;
		key=id;
		index=hashfunction(key);
		temp=head[index].next;
		flag=0;
		while(temp!=null)
		{
			if(n.equals(temp.cust_name))
			{
				flag=1;
				System.out.println("***Consignment status***");
				System.out.println("Order placed on (date and time): "+temp.dt);
				System.out.println("Your order has been processed.");
				System.out.println("Consignment will be delivered in "+(temp.t+2)+ " days.");
				dist=compute(temp.sc_code,temp.dc_code);
				System.out.println();
				System.out.println("Distance: "+dist+"km");
				break;
			}
			temp=temp.next;
		}
		if(flag==0)
			System.out.println("Above Consignment ID or name is invalid. No record found.");
	}
}