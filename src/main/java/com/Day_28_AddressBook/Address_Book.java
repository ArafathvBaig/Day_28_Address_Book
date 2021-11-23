package com.Day_28_AddressBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Address_Book 
{
	String firstName;
	String lastName;
	String email;
	String address;
	String city;
	String state;
	String zip;
	long phoneNo;
	
	public ArrayList<Contacts>list = new ArrayList<>();
	public static HashMap<String, Address_Book> addressBooks = new HashMap<>();
	
	/*
	 * This Method Allows You To Delete Any Specific Contact From The Array List
	 * With The Help Of The First Name.
	 */
	
	public void deleteContact()
	{
		Scanner sc = new Scanner(System.in);
        System.out.println("Enter The Name to Delete Contact: ");
        String name=sc.nextLine();
        for (Contacts C : list) 
        {
            if (name.equalsIgnoreCase(C.getFirstName()))
            {
                System.out.println("Entered Name found in the Address Book, deleting contact");
                list.remove(C);
            }
            else
            {
                System.out.println("Entered Name not found in the Address Book");
            }
        }
    }
	
	/*
	 * This Method Allows You To Edit The Details Of a Specific Contact and
	 * Adds That Edited Contact Details To The Array List.
	 */
	
	public void editContact()
	{
		Scanner sc = new Scanner(System.in);
        System.out.println("Enter The Name to Edit Contact: ");
        String name=sc.nextLine();
        for (Contacts C : list) 
        {
            if (name.equalsIgnoreCase(C.getFirstName())) 
            {
                System.out.println("Entered Name found in the Contacts");
                System.out.println("Enter the updated first name");
                this.firstName=sc.next();
                C.setFirstName(firstName);
                System.out.println("Enter the updated last name");
                this.lastName=sc.next();
                C.setLastName(lastName);
                System.out.println("Enter the updated emailID");
                this.email = sc.next();
                C.setEmailId(email);
                System.out.println("Enter the updated address");
                this.address=sc.next();
                C.setAddress(address);
                System.out.println("Enter the updated city");
                this.city=sc.next();
                C.setCity(city);
                System.out.println("Enter the updated State");
                this.state=sc.next();
                C.setState(state);
                System.out.println("Enter the updated zipcode");
                this.zip = sc.nextLine();
                C.setZip(zip);
                System.out.println("Enter the updated phoneNumber");
                this.phoneNo = sc.nextInt();
                C.setPhoneNo(phoneNo);
                C.display();
            } 
            else
            {
                System.out.println("Entered name not  found in the AddressBook");
            }
        }
    }
	
	/*
	 * This Method will Get The Details From the USER and
	 * ADD those details to the Array List Name Contacts through the object
	 */
	
	public void ContactsDetails()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("First Name:: ");
		this.firstName = sc.nextLine();
		System.out.println("Last Name:: ");
		this.lastName = sc.nextLine();
		System.out.println("Email:: ");
		this.email = sc.nextLine();	
		System.out.println("Address:: ");
		this.address = sc.nextLine();
		System.out.println("City Name:: ");
		this.city = sc.nextLine();
		System.out.println("State Name:: ");
		this.state = sc.nextLine();
		System.out.println("Zip Code:: ");
		this.zip = sc.nextLine();
		System.out.println("Phone Number:: ");
		this.phoneNo = sc.nextLong();
		Contacts person = new Contacts(firstName, lastName, email, address, city, state, zip, phoneNo);
        list.add(person);
        person.display();
	}
	
	/*
	 * This method is used to search by first name
	 */
	public void searchByFirstName(String firstName)
	{
        for (Map.Entry<String, Address_Book> entry : addressBooks.entrySet()) 
        {
            System.out.println(entry.getKey());
            Stream<Contacts> search = entry.getValue().list.stream().filter(i -> i.getFirstName().equals(firstName));
            search.forEach(str -> System.out.println(str.toString()));
        }
    }
	
	 /*
     * This method is used to search by city or state
     * */
    public void searchByCityOrState() 
    {
    	Scanner sc = new Scanner(System.in);
        System.out.println("1. Search by City:");
        System.out.println("2. Search by State");
        int option = sc.nextInt();
        if (option == 1)
        {
            System.out.println("Enter City Name:");
            String city = sc.next();
            for (Map.Entry<String, Address_Book> entry : addressBooks.entrySet())
            {
                System.out.println(entry.getKey());
                Stream<Contacts> search = entry.getValue().list.stream().filter(i -> i.getCity().equals(city));
                search.forEach(str -> System.out.println(str.toString()));
            }
        } 
        else if (option == 2)
        {
            System.out.println("Enter State Name:");
            String state = sc.next();
            for (Map.Entry<String, Address_Book> entry : addressBooks.entrySet()) 
            {
                System.out.println(entry.getKey());
                Stream<Contacts> search = entry.getValue().list.stream().filter(i -> i.getState().equals(state));
                search.forEach(str -> System.out.println(str.toString()));
            }
        } 
        else 
        {
        	System.out.println("Wrong Input");
        }
    }
    
    /*
     * This method is used to search number of contacts by city or state
     * */
    public void countByCityOrState() 
    {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("1. Count by City:");
        System.out.println("2. Count by State");
        int option = sc.nextInt();
        if (option == 1)
        {
            System.out.println("Enter City Name:");
            String city = sc.next();
            for (Map.Entry<String, Address_Book> entry : addressBooks.entrySet()) 
            {
                System.out.println(entry.getKey());
                Stream<Contacts> search = entry.getValue().list.stream().filter(i -> i.getCity().equals(city));
                System.out.println("Number of contacts in " + city + " are : " + search.count());
            }
        } 
        else if (option == 2)
        {
            System.out.println("Enter State Name:");
            String state = sc.next();
            for (Map.Entry<String, Address_Book> entry : addressBooks.entrySet()) 
            {
                System.out.println(entry.getKey());
                Stream<Contacts> search = entry.getValue().list.stream().filter(i -> i.getState().equals(state));
                System.out.println("Number of contacts in " + state + " are : " + search.count());
            }
        } 
        else
        {
        	System.out.println("Wrong Input");
        }
    }
    
	/**
	 * sort by First Name
	 */
	public void sortByFirstName() 
	{
		Collections.sort(list, Sort.compareFirstName);
		showAllContacts();
	}

	/**
	 * sort by City
	 */
	public void sortByCity() 
	{
		Collections.sort(list, Sort.compareCity);
		showAllContacts();
	}

	/**
	 * sort by State
	 */
	public void sortByState() 
	{
		Collections.sort(list, Sort.compareState);
		showAllContacts();
	}
	
	/**
	 * sort by Zip Code
	 */
	public void sortByZipCode() 
	{
		Collections.sort(list, Sort.compareZipCode);
		showAllContacts();
	}

	public void writeFile(String file) throws IOException
	{
		try 
		{
			FileWriter writer = new FileWriter(file, true);
			for (Contacts c : list) 
			{
				writer.write("First Name:: "+c.getFirstName());
				writer.write("\n");
				writer.write("Last Name:: "+c.getLastName());
				writer.write("\n");
				writer.write("Email:: "+c.getEmailId());
				writer.write("\n");
				writer.write("Address:: "+c.getAddress());
				writer.write("\n");
				writer.write("City :: "+c.getCity());
				writer.write("\n");
				writer.write("State :: "+c.getState());
				writer.write("\n");
				writer.write("Zip Code:: "+c.getZip());
				writer.write("\n");
				writer.write("Phone Number:: "+c.getPhoneNo());
				writer.write("\n");
				writer.close();
			}	
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void readFile(String file)
	{
		try {
            FileReader reader = new FileReader(file);
            int character;
 
            while ((character = reader.read()) != -1) 
            {
                System.out.print((char) character);
            }
            reader.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void writeJSONFile(String file) throws IOException, ParseException 
	{
		JSONArray arr = new JSONArray();
		FileWriter writer = new FileWriter(file);
		try 
		{
			for (Contacts c : list)
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("First Name:: ", c.getFirstName());
				jsonObject.put("Last Name:: ", c.getLastName());
				jsonObject.put("Email:: ", c.getEmailId());
				jsonObject.put("Address:: ", c.getAddress());
				jsonObject.put("City :: ", c.getCity());
				jsonObject.put("State :: ", c.getState());
				jsonObject.put("Zip Code:: ", c.getZip());
				jsonObject.put("Phone Number:: ", c.getPhoneNo());
				arr.add(jsonObject);
			}
			writer.write(arr.toJSONString());
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void readJSONFile(String file) throws Exception
	{
		JSONParser jsonP = new JSONParser();
		FileReader reader = new FileReader(file);
		try
		{
			Object obj = jsonP.parse(reader);
			JSONArray array = (JSONArray) obj;
			System.out.println(array);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void writeCSVFile(String file) 
	{
		File fileWrite = new File(file);
		try 
		{
			FileWriter outputfile = new FileWriter(fileWrite);
			CSVWriter writer = new CSVWriter(outputfile);

			for (Contacts c : list) 
			{
				String[] data = { 
						c.getFirstName(),
						c.getLastName(),
						c.getEmailId(),
						c.getAddress(),
						c.getCity(),
						c.getState(),
						c.getZip(),
						String.valueOf(c.getPhoneNo())
						};
				writer.writeNext(data);
			}
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void readCSVFile(String file)
	{
		try (Reader reader = Files.newBufferedReader(Paths.get(file)); 
								CSVReader csvreader = new CSVReader(reader);) 
		{
			List<String[]> readcsv = csvreader.readAll();
			for (String[] nextRecord : readcsv) 
			{
				System.out.println("First Name:: " + nextRecord[0]);
				System.out.println("Last Name:: " + nextRecord[1]);
				System.out.println("Email:: " + nextRecord[2]);
				System.out.println("Address:: " + nextRecord[3]);
				System.out.println("City:: " + nextRecord[4]);
				System.out.println("State:: " + nextRecord[5]);	
				System.out.println("Zip code:: " + nextRecord[6]);
				System.out.println("Phone Number:: " + nextRecord[7]);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void showAllContacts()
	{
		for(Contacts c: list)
		{
			System.out.println(" ");
			System.out.println("First Name:: "+c.getFirstName());
			System.out.println("Last Name:: "+c.getLastName());
			System.out.println("Email:: "+c.getEmailId());
			System.out.println("Address:: "+c.getAddress());
			System.out.println("City :: "+c.getCity());
			System.out.println("State :: "+c.getState());
			System.out.println("Zip Code:: "+c.getZip());
			System.out.println("Phone Number:: "+c.getPhoneNo());
		}
	}
	
	/**
     * showContacts is used to display contacts
     */
    public void showContacts() 
    {
        int i = 1;
        for (Contacts contact : list)
        {
            System.out.println("Contact Details of :: "+i);
            System.out.println(contact.toString());
            i++;
        }
    }
	
	public static void main(String[] args) throws Exception
	{	
		Address_Book  address_Book = new Address_Book();
		Scanner sc = new Scanner(System.in);
		Address_Book  book1 = new Address_Book();
		Address_Book  book2 = new Address_Book();
		Address_Book  book3 = new Address_Book();
		addressBooks.put("AddressBook1", book1);
		addressBooks.put("AddressBook2", book2);
		addressBooks.put("AddressBook3", book3);
		System.out.println("Choose Address Book");
		System.out.println("1. Address Book 1");
		System.out.println("2. Address Book 2");
		System.out.println("3. Address Book 3");
		int chooseAddressBook = sc.nextInt();
		System.out.println("Choose What to do in this Address Book");
		System.out.println("0. Exit");
		System.out.println("1. Add Contacts");
		System.out.println("2. Show All Contacts");
		System.out.println("3. Edit Contact");
		System.out.println("4. Delete Contact");
		System.out.println("5. Search By City or State");
		System.out.println("6. Count By City or State");
		System.out.println("7. Sort By First Name");
		System.out.println("8. Sort ");
		System.out.println("9. Write and Read into a JSON File");
		System.out.println("10. Write and Read into a CSV File");
		System.out.println("11. Write and Read into a TEXT File");
		System.out.println("Enter Your Choice");
		int choice = sc.nextInt();
		while(choice!=0)
		{
			switch(choice)
			{
				case 1:
					if(chooseAddressBook == 1)
					{
						book1.ContactsDetails();
					}
					else if(chooseAddressBook == 2)
					{
						book2.ContactsDetails();						
					}
					else if(chooseAddressBook == 3)
					{
						book3.ContactsDetails();
					}
					break;
					
				case 2:
					if(chooseAddressBook == 1)
					{
						book1.showContacts();
					}
					else if(chooseAddressBook == 2)
					{
						book2.showContacts();						
					}
					else if(chooseAddressBook == 3)
					{
						book3.showContacts();						
					}				
					break;
					
				case 3:
					if(chooseAddressBook == 1)
					{
						book1.editContact();
					}
					else if(chooseAddressBook == 2)
					{
						book2.editContact();						
					}
					else if(chooseAddressBook == 3)
					{
						book3.editContact();						
					}
					break;
					
				case 4:
					if(chooseAddressBook == 1)
					{
						book1.deleteContact();
					}
					else if(chooseAddressBook == 2)
					{
						book2.deleteContact();						
					}
					else if(chooseAddressBook == 3)
					{
						book3.deleteContact();						
					}					
					break;
					
				case 5:
					address_Book.searchByCityOrState();
					break;
					
				case 6:
					address_Book.countByCityOrState();
					break;
					
				case 7:
					address_Book.sortByFirstName();
					break;
					
				case 8:
					System.out.println("1. Sort By State. "
									 + "2. Sort By City. "
									 + "3. Sort By Zip Code. ");
					int opt = sc.nextInt();
					if(opt==1)
					{
						address_Book.sortByState();
					}
					else if(opt==2)
					{
						address_Book.sortByCity();
					}
					else if(opt==3)
					{
						address_Book.sortByZipCode();
					}
					else
					{
						System.out.println("Wrong Input");
					}
					break;
					
				case 9:
                    if (chooseAddressBook == 1) 
                    {
                    	book1.writeJSONFile("C:\\Users\\Arafath Baig\\eclipse-workspace\\Day_28_AddressBook\\src\\main\\resources\\AddressBook1.json");
                    	book1.readJSONFile("C:\\Users\\Arafath Baig\\eclipse-workspace\\Day_28_AddressBook\\src\\main\\resources\\AddressBook1.json");
                    }
                    else if (chooseAddressBook == 2) 
                    {
                    	book2.writeJSONFile("C:\\Users\\Arafath Baig\\eclipse-workspace\\Day_28_AddressBook\\src\\main\\resources\\AddressBook2.json");
                    	book2.readJSONFile("C:\\Users\\Arafath Baig\\eclipse-workspace\\Day_28_AddressBook\\src\\main\\resources\\AddressBook2.json");
                    }
                    else if (chooseAddressBook == 3) 
                    {
                    	book3.writeJSONFile("C:\\Users\\Arafath Baig\\eclipse-workspace\\Day_28_AddressBook\\src\\main\\resources\\AddressBook3.json");
                    	book3.readJSONFile("C:\\Users\\Arafath Baig\\eclipse-workspace\\Day_28_AddressBook\\src\\main\\resources\\AddressBook3.json");
                    }
                    else 
                    	System.out.println("Wrong Input");
                    break;
                    
				case 10:
                    if (chooseAddressBook == 1) 
                    {
                    	book1.writeCSVFile("C:\\Users\\Arafath Baig\\eclipse-workspace\\Day_28_AddressBook\\src\\main\\resources\\AddressBook1.csv");
                    	book1.readCSVFile("C:\\Users\\Arafath Baig\\eclipse-workspace\\Day_28_AddressBook\\src\\main\\resources\\AddressBook1.csv");
                    }
                    else if (chooseAddressBook == 2) 
                    {
                    	book2.writeCSVFile("C:\\Users\\Arafath Baig\\eclipse-workspace\\Day_28_AddressBook\\src\\main\\resources\\AddressBook2.csv");
                    	book2.readCSVFile("C:\\Users\\Arafath Baig\\eclipse-workspace\\Day_28_AddressBook\\src\\main\\resources\\AddressBook2.csv");
                    }
                    else if (chooseAddressBook == 3) 
                    {
                    	book3.writeCSVFile("C:\\Users\\Arafath Baig\\eclipse-workspace\\Day_28_AddressBook\\src\\main\\resources\\AddressBook3.csv");
                    	book3.readCSVFile("C:\\Users\\Arafath Baig\\eclipse-workspace\\Day_28_AddressBook\\src\\main\\resources\\AddressBook3.csv");
                    }
                    else 
                    	System.out.println("Wrong Input");
                    break;
                    
				case 11:
                    if (chooseAddressBook == 1) 
                    {
                    	book1.writeFile("C:\\Users\\Arafath Baig\\eclipse-workspace\\Day_28_AddressBook\\src\\main\\resources\\AddressBook1.txt");
                    	book1.readFile("C:\\Users\\Arafath Baig\\eclipse-workspace\\Day_28_AddressBook\\src\\main\\resources\\AddressBook1.txt");
                    }
                    else if (chooseAddressBook == 2) 
                    {
                    	book2.writeFile("C:\\Users\\Arafath Baig\\eclipse-workspace\\Day_28_AddressBook\\src\\main\\resources\\AddressBook2.txt");
                    	book2.readFile("C:\\Users\\Arafath Baig\\eclipse-workspace\\Day_28_AddressBook\\src\\main\\resources\\AddressBook2.txt");
                    }
                    else if (chooseAddressBook == 3) 
                    {
                    	book3.writeFile("C:\\Users\\Arafath Baig\\eclipse-workspace\\Day_28_AddressBook\\src\\main\\resources\\AddressBook3.txt");
                    	book3.readFile("C:\\Users\\Arafath Baig\\eclipse-workspace\\Day_28_AddressBook\\src\\main\\resources\\AddressBook3.txt");
                    }
                    else 
                    	System.out.println("Wrong Input");
                    break;
		
				default:
					System.out.println("Wrong InPut");
					break;
			}
			System.out.println(" ");
			System.out.println("Choose Address Book");
			System.out.println("1. Address Book 1");
			System.out.println("2. Address Book 2");
			System.out.println("3. Address Book 3");
			chooseAddressBook = sc.nextInt();
			System.out.println(" ");
			System.out.println("Choose What to do in this Address Book");
			System.out.println("0. Exit");
			System.out.println("1. Add Contacts");
			System.out.println("2. Show All Contacts");
			System.out.println("3. Edit Contact");
			System.out.println("4. Delete Contact");
			System.out.println("5. Search By City or State");
			System.out.println("6. Count By City or State");
			System.out.println("7. Sort By First Name");
			System.out.println("8. Sort ");
			System.out.println("9. Write and Read into a JSON File");
			System.out.println("10. Write and Read into a CSV File");
			System.out.println("11. Write and Read into a TEXT File");
			System.out.println("Enter Your Choice");
			choice = sc.nextInt();
		}
		System.out.println("The Program Got Exit.");		
	}
}

class Contacts
{
    private String firstName = " ";
    private String lastName = " ";
    private String email = " ";
    private String address = " ";
    private String city = " ";
    private String state = " ";
    private String zip = " ";
    private long phoneNo = 0;

    Contacts(String firstName, String lastName, String email, String address, String city, String state, String zip, long phoneNo) 
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNo = phoneNo;
    }
    
    /*
     * These Get Methods get the data from Address Book Class to Contacts.
     * These Set Methods set the data from Address Book variables to the Contacts variable.
     */
    
    public String getFirstName() 
    {
        return firstName;
    }
    public void setFirstName(String firstName) 
    {
        this.firstName=firstName;
    }
    public String getLastName() 
    {
        return lastName;
    }
    public void setLastName(String lastName) 
    {
        this.lastName=lastName;
    }
    public String getEmailId() 
    {
        return email;
    }
    public void setEmailId(String email) 
    {
        this.email=email;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address=address;
    }
    public String getCity() 
    {
        return city;
    }
    public void setCity(String city) 
    {
        this.city=city;
    }
    public String getState() 
    {
        return state;
    }
    public void setState(String state) 
    {
        this.state=state;
    }
    public String getZip()
    {
        return zip;
    }
    public void setZip(String zip)
    {
        this.zip=zip;
    }
    public long getPhoneNo() 
    {
        return phoneNo;
    }
    public void setPhoneNo(long phoneNo) 
    {
        this.phoneNo=phoneNo;
    }
    
    /*
     *This Display Method Displays Details Just Got Added To The Array List. 
     */
    
    public void display()
	{
    	System.out.println(" ");
		System.out.println("First Name :: "+firstName);
		System.out.println("Last Name :: "+lastName);
		System.out.println("Email :: "+email);
		System.out.println("Address :: "+address);
		System.out.println("City :: "+city);
		System.out.println("State :: "+state);
		System.out.println("Zip Code :: "+zip);
		System.out.println("Phone Number :: "+phoneNo);
	}
    
    @Override
	public String toString() 
    {		
		return "\nPerson Details:"+
				"\nFirst Name :: "+firstName+
				"\nLast Name :: "+lastName+
				"\nEmail :: "+email+
				"\nAddress :: "+address+
				"\nCity :: "+city+
				"\nState :: "+state+
				"\nZip Code :: "+zip+
				"\nPhone Number :: "+phoneNo;				
	}
}










