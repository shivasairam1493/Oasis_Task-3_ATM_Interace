import java.util.*;
import java.io.*;

  class Account
  {
     static int acc_number=1111;
     String acc_holder_name;
     int pin;
     double balance;
     String unique_id;        //unique_id acts as ATM card
     int a_no;
     
     void createAcc()
     {
        a_no=acc_number;
        Scanner in=new Scanner(System.in);
        System.out.println("Enter account holder name");
        acc_holder_name=in.nextLine();
        System.out.println("\n Enter Username");
        unique_id=in.nextLine();
        int length_pin=0;
        
        do{
          
           System.out.println("Enter secret 4 digit pin");
           pin=in.nextInt();
           length_pin=String.valueOf(pin).length();
           }
        while(length_pin!=4);
        
        System.out.println("Enter initial deposit amount");
        balance=in.nextDouble();
        System.out.println("Congratulations, your Account is successfully created");
        System.out.println("Account Details-\n"+"Account Number-"+a_no+"\nAccount holder name-"+acc_holder_name+"\nBalance-"+balance+"\nThank You");


   // creating a file with the account number
   
   String fileName=acc_number+".txt";
   File file=new File(fileName);
   
   try{
    
         file.createNewFile();
         FileWriter writer=new FileWriter(file);
         writer.write("Account Created\n");
         writer.write("Account Number: "+acc_number+"\n");
         writer.write("User Id-r: "+unique_id+"\n");
         writer.write("Account holder name: "+ acc_holder_name+"\n");
         writer.write("PIN: "+pin+"\n");
         writer.write("Balnace:"+balance+"\n");
         writer.write("Date :"+new Date()+"\n\n\n");
         writer.close();
         
      }
      
    catch(IOException e)
    {
        System.out.println("An error occured");
        e.printStackTrace();
    }
    
    try{
       
        Thread.sleep(5000);
       }
       
     catch(InterruptedException e)
    {
       e.printStackTrace();
    }
    
    acc_number++;
    
   }
   
  }
  
  
  class ATM
 {
    void withdraw(Account acc)
    {
       Scanner in=new Scanner(System.in);
       System.out.flush();
       System.out.println("Money Withdraw mode");
       System.out.println("Enter the amount in multiples of 100");
       double amount=in.nextDouble();
       
       if(amount%100==0)
       {
           if(acc.balance>=amount)
           {   
              acc.balance-=amount;
              System.out.println("Your Transaction is processing");
             
             
            try{
               
               //opening the text file for appending new transaction
                String fileName=acc.a_no+".txt";
                FileWriter fileWriter=new FileWriter(fileName,true);
                BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                
              // write the transaction details to the text file
              
                 bufferedWriter.write("Date: "+new Date()+"\n");
                 bufferedWriter.write("Withdrawl: "+amount+"\n");
                 bufferedWriter.write("Account Number: "+acc.a_no+"\n");
                 bufferedWriter.write("Remaining Balance: "+acc.balance+"\n\n");
                 
              //close the file writer and buffered writer
              
              bufferedWriter.close();
              fileWriter.close();
             }
             
             catch(IOException e)
             {   System.out.println("An error occured");
                 e.printStackTrace();
             }
             
             System.out.println("Thank you ");
             
            //for making some wait time
             
             try
             {
               Thread.sleep(6000);
             }
             
             catch(InterruptedException e)
             {  e.printStackTrace();}
             
             System.out.flush();
         }
         
         else
         {
            System.out.println("Insufficient Balance");
         }
         
       }
       
       else
       {
           System.out.println("Entered amount is not in multiple of 100 ");
            System.out.println("Try again");
       }
       
    }
    
    void deposit_by_transfer(Account acc,double amount)
    {
        acc.balance+=amount;
        
         try{
            
                String fileName=acc.a_no+".txt";
                FileWriter fileWriter=new FileWriter(fileName,true);
                BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                
              // write the transaction details to the text file
                 bufferedWriter.write("Deposit: "+amount+"\n");
                 bufferedWriter.write("Date: "+new Date()+"\n");
                 bufferedWriter.write("Account Number: "+acc.a_no+"\n");
                 bufferedWriter.write("Remaining Balance: "+acc.balance+"\n\n");
                 
              //close the file writer and buffered writer
              
              bufferedWriter.close();
              fileWriter.close();
             }
             
             catch(IOException e)
             {   System.out.println("An error occured");
                 e.printStackTrace();
             }
         
     }
     
     void deposit(Account acc)
     {
        Scanner in=new Scanner(System.in);
        System.out.flush();
        System.out.println("Money Deposit mode");
        System.out.println("Enter the you want to deposit");
        double amount=in.nextDouble();
        acc.balance+=amount;
        System.out.flush();
        
          
        try{
            
                String fileName=acc.a_no+".txt";
                System.out.println("The File Name-"+fileName);
                FileWriter fileWriter=new FileWriter(fileName,true);
                BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                
              // write the transaction details to the text file
                 bufferedWriter.write("Deposit: "+amount+"\n");
                 bufferedWriter.write("Date: "+new Date()+"\n");
                 bufferedWriter.write("Account Number: "+acc.a_no+"\n");
                 bufferedWriter.write("Remaining Balance: "+acc.balance+"\n\n");
                 
              //close the file writer and buffered writer
              
              bufferedWriter.close();
              fileWriter.close();
             }
             
             catch(IOException e)
             {   System.out.println("An error occured");
                 e.printStackTrace();
             }
          
           System.out.println("Your request is processing, please wait");
           
           try{
              Thread.sleep(5000);
            }
            
            catch(InterruptedException e)
            {  e.printStackTrace();}
            
            System.out.flush();
            System.out.println("Transaction completed successfully");
            System.out.println("Going to Home page");
            
            try{
            
              Thread.sleep(3000);
             }
             
             catch(InterruptedException e)
             {  e.printStackTrace();}
             
            
         }
         
         
     void Transfer(Account acc1,Account acc2,double amount)
     {
         if(acc1.balance>=amount)
            {
               acc1.balance-=amount;
               ATM a=new ATM();               
               a.deposit_by_transfer(acc2,amount);
               
               try{
               
                String fileName=acc1.a_no+".txt";
                System.out.println("The File Name-"+fileName);
                FileWriter fileWriter=new FileWriter(fileName,true);
                BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                
              // write the transaction details to the text file
                 bufferedWriter.write("Transferred: "+amount+"\n");
                 bufferedWriter.write("Date: "+new Date()+"\n");
                 bufferedWriter.write("Account Number: "+acc1.a_no+"\n");
                 bufferedWriter.write("Remaining Balance: "+acc1.balance+"\n\n");
                 
              //close the file writer and buffered writer
               
                bufferedWriter.close();
                fileWriter.close();   
               
               }
           
              catch(IOException e)
             {   System.out.println("An error occured");
                 e.printStackTrace();
             }
           
            System.out.println("Your request is processing, please wait");
           
           try{
              Thread.sleep(5000);
            }
            
            catch(InterruptedException e)
            {  e.printStackTrace();}
            
            System.out.flush();
            System.out.println("Transaction completed successfully");
            System.out.println("Going to Home page");
            
            try{
            
              Thread.sleep(5000);
             }
             
             catch(InterruptedException e)
             {  e.printStackTrace();}
             
          }
      
      }
              
         
   void display_details(Account acc)
   {
       System.out.flush();
       System.out.println("Your account details");
       try{
            Thread.sleep(2000);
          }
          
       catch(InterruptedException e)
       { e.printStackTrace();}
       
       String file_name=String.valueOf(acc.a_no)+".txt";
       File file=new File(file_name);
       
       try{
          FileReader fileReader=new FileReader(file);
          BufferedReader bufferReader=new BufferedReader(fileReader);
          String line=null;
          
          while((line=bufferReader.readLine())!=null)
          { System.out.println(line);}
          
          bufferReader.close();
        }
        
        catch(FileNotFoundException e)
        {
           System.out.println("File not found:"+e.getMessage());
        }
        
        catch(IOException e)
        {  System.out.println("Error reading file:" +e.getMessage());}
        
        System.out.println("Timeout in 30 seconds....");
        
        try
        {   Thread.sleep(30000); }
        
        catch(InterruptedException e)
        {  e.printStackTrace();}
        
    }
    
    void quit()
    {
        System.out.println("Thank you");
        return ;
    }
    
  }
   
   
    class run_atm
   {
      int account_search_by_unique_id(Account[] ac, String unique_id) 
      {
        for (int i = 0; i < ac.length; i++) {
            if (Objects.equals(unique_id, ac[i].unique_id)) {
                return i;
            }
        }
        return -1;
     }
     
     int account_search_by_unique_id(Account[] ac,int account_number)
     {
         for(int i=0;i<5;i++)
         {
            if(Objects.equals(account_number,ac[i].a_no))
            return i;
         }
         
        return -1;
     
     }
     
     

    void demo(Account[] ac) {
        Scanner in = new Scanner(System.in);
        System.out.println("\n\nWelcome to ATM");
        System.out.println("Enter your Unique ID");

        String unique_id = in.nextLine();

        int i = account_search_by_unique_id(ac, unique_id);
        if (i == -1) {
            System.out.println("Account not yet registered");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        } else {
            System.out.println("Enter your 4 digit pin");
            int pin = in.nextInt();

            if (pin == ac[i].pin) {
                System.out.println("Select an option below\n1->Withdraw\n2->Deposit\n3->Account Transfer\n4->Display account details\n5->Quit");

                int ch;
                ATM atm = new ATM();
                ch = in.nextInt();

                switch (ch) {
                    case 1:
                        atm.withdraw(ac[i]);
                        break;
                    case 2:
                        atm.deposit(ac[i]);
                        break;
                    case 3:
                          System.out.println("\033[H\033[2J");
                          System.out.flush();
                           System.out.println("Enter Account number to transfer funds");
                           int account_transfer=in.nextInt();
                           int j=account_search_by_unique_id(ac,account_transfer);
                           if(j==-1)
                           { 
                                System.out.println("Account not yet registered");
                                return;
                           }
                           
                           else
                           {
                              System.out.println("Enter the amount to transfer");
                              double amount=in.nextDouble();
                              atm.Transfer(ac[i],ac[j],amount);
                           }
                        
                        break;
                    case 4:
                         atm.display_details(ac[i]);
                        break;
                    case 5:
                        atm.quit();
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            } else {
                System.out.println("Incorrect PIN, Try again");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
    }
}
  
  public class atm_interface
  {
     public static void main(String args[])
     {
         Scanner in=new Scanner(System.in);
         Account[] ac=new Account[5];
         
         for(int i=0;i<5;i++)
         { 
            System.out.println("\033[H\033[2J");
            System.out.flush();
            System.out.println("Creating Account mode");
            ac[i]=new Account();
            ac[i].createAcc();
            System.out.println("\033[H\033[2J");
            System.out.flush();
        }
        
        run_atm ab=new run_atm();
        for(; ;)
        {
           System.out.println("\033[H\033[2J");
            System.out.flush();
            ab.demo(ac);
        }
        
      }
  }
            
    
       
          
          
              
    
    
       
       
       
