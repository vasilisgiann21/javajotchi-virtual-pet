import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main{
	public static void main (String[] args){
        	String reply;
        	Scanner sc = new Scanner(System.in);
                ArrayList<GeneralPet> pets = new ArrayList<GeneralPet>();
                System.out.println("Do you want to create a new pet (yes/no) ? ");
                while (true){
			reply = sc.nextLine();
			if (reply.equals("yes") || reply.equals("no")) {
				break;
			}else {
				System.out.println("This is an invalid option. Choose between yes  and no  ! ");
			}
		}
                String kind_of_pet;
                while (reply.equals("yes")) {
		        System.out.println("What is the name of your new pet ? ");
		        String name = sc.nextLine();
				System.out.println("What kind of pet do you want ?");
				System.out.println("Dog/Cat or dog/cat");
				while (true){
					kind_of_pet = sc.nextLine();
					if (kind_of_pet.equals("Dog") || kind_of_pet.equals("dog") || kind_of_pet.equals("cat") || kind_of_pet.equals("Cat")) {
						break;
					} else{
						System.out.println("This is not a correct option, choose again !!! ");
						continue;
					}
				}
				GeneralPet newPet;
				if (kind_of_pet.equals("Dog") || kind_of_pet.equals("dog")) {
					newPet = new Dog(name);
				}else {
					newPet = new Cat(name);
				}

				pets.add(newPet);

		        System.out.println("Do you want to create a new pet ? ");
		        while (true){
                        	reply = sc.nextLine();
                        	if (reply.equals("yes") || reply.equals("no")) {
                                	break;
                        }else {
                               	System.out.println("This is an invalid optin. Choose between yes  and no  ! ");
                        	}
                	}

		}
		for (int i = 0 ; i < pets.size() ; i++){
			GeneralPet temp_pet = pets.get(i);
			temp_pet.startHeartbeat();
		}                
                
                while (true){
		    Iterator<GeneralPet>  iter = pets.iterator();
		    while (iter.hasNext()){
		       GeneralPet temporary_pet  = iter.next();

			if ( !temporary_pet.getIsAlive() ){
				iter.remove();
			}
		    }
		    if (!pets.isEmpty()){
			for (int i = 0 ; i < pets.size() ; i++ ){
				GeneralPet temp_pet = pets.get(i);
				System.out.println(i + ". " + temp_pet.getName());
                        }
/*I can't declare int option; without first assigning a value for it cause the Control Flow Analysis fails.
If i create a int option;. It is currenly empty.
The try block: The programm attempts option = sc.nextInt();
The crash:The user types "ABC". An exception is triggered before the assignment to option is finished.
The catch block: The programm jumps to your catch blocks and prints "This is not a number!"
The logic gate: The programm leaves the catch block and moves to the next line: if (option >= 0 ....)
At this exact moment, option has never successfully received a value. Java refuses to compare "nothing" against 0, so it throws a compile-time error
 to prevent a crash at runtime. */

			int option = -1;
		    	System.out.println("To interact with your pet, enter its number : ");
		        while (true){
				try{
					option = sc.nextInt();
				}catch (Exception e){
					System.out.println("This is not number ! ");
					sc.nextLine();
					continue;
				}
				sc.nextLine();

		    		if ( option >= 0 && option < pets.size()){
					break;
				}else{
					System.out.println("Invalid option choose again !!! ");
				}
			}
		    	System.out.println("-----MENU-----");
		    	System.out.println("Choose an option from the one's below, to interact with your pet ! ");
		    	System.out.println(" ");
		    	System.out.println("Option 1: Check the status of your pet. ");
                    	System.out.println("Option 2: Feed your pet. ");
                    	System.out.println("Option 3: Play with your pet. ");
                    
		    	String option2 = sc.nextLine();
                    
		    	GeneralPet temp_pet = pets.get(option);
		    
		    	if (option2.equals("1")){
                            	temp_pet.checkStatus();

                    	}else if (option2.equals("2")){
			    	temp_pet.feed();

                    	}else if (option2.equals("3")){
                            	temp_pet.play();

                    	}else{
                            	System.out.println("This option is not on the menu! ");
                    	}
		   
		    
		   } else {System.exit(0);}
             }          
	}  

}
