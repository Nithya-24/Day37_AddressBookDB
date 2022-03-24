
package com;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Iterator;

public class MultipleAddressBook {
	Map<String, AddressBookService> addressBookMap = new HashMap<>(); 
	List<Contact> contacts = new ArrayList<Contact>();
	Scanner scanner = new Scanner(System.in);
	/**
	 * Adding my Address Book
	 */

	public void addAddressBook() {                                                 
		System.out.println("Enter Name of new Address Book: ");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String bookName = scanner.next();
		/**
		 * Check that book name exist or not
		 */
		if (addressBookMap.containsKey(bookName)) {                                
			System.out.println("Address book with this name exists, Enter the New name");
			addAddressBook();
		} else {
			AddressBookService addressBook = new AddressBookService();
			/**
			 * adding address book and contacts to Hashmap
			 */
			addressBookMap.put(bookName, addressBook);                             
			System.out.println("Address Book " + bookName + " successfully added!!");
		}
	}
	/**
	 * Add contact to Address Book
	 */

	public void addContact() {                                                     
		System.out.println("Enter the name of Address book to add the contact.");
		Scanner scanner = new Scanner(System.in);
		String newContact = scanner.nextLine();
		AddressBookService addressBook = addressBookMap.get(newContact);   
		/**
		 * if Address Book is present mean then it add the contact to HashMap
		 */
		if (addressBook == null) {
			System.out.println("No book found");

		} else {
			addressBookMap.get(newContact).addContact();                         
		}
	}

	public void editContactInBook() {
		System.out.println("Enter Name of Address Book you want to edit: ");
		Scanner scanner = new Scanner(System.in);
		String editBookName = scanner.next();
		if (addressBookMap.containsKey(editBookName)) {
			addressBookMap.get(editBookName).editContact();                              
		} else {
			System.out.println("AddressBook doesn't exist, Please enter correct name.");
			editContactInBook();
		}
	}

	public void deleteAddressBook() {
		System.out.println("Enter Name of Address Book you want to delete: ");
		Scanner scanner = new Scanner(System.in);
		String bookName = scanner.next();
		if (addressBookMap.containsKey(bookName)) {                                       
			addressBookMap.remove(bookName);                                              
		} else {
			System.out.println("AddressBook doesn't exist, Please enter correct name.");
			deleteAddressBook();
		}
	}

	public void deleteContactInBook() {
		System.out.println("Enter Name of Address Book you want to delete the contacts in it: ");
		Scanner scanner = new Scanner(System.in);
		String bookName = scanner.next();
		if (addressBookMap.containsKey(bookName)) {
			addressBookMap.get(bookName).deleteContact();                                           
		} else {
			System.out.println("AddressBook doesn't exist, Please enter correct name.");
			deleteContactInBook();
		}
	}

	public void printBook() {
		System.out.println("These are AddressBooks in program.");
		for (String i : addressBookMap.keySet()) {                                    
			System.out.println(i);
		}
	}
	
	public void printContactsInBook() {
		for (String i : addressBookMap.keySet()) {
			System.out.println(i);
			System.out.println(addressBookMap.get(i).contacts);                     
		}
		
	}
	public void searchByCity() {
		
		System.out.println("Enter the name of the City to get the persons : ");
		Scanner scanner = new Scanner(System.in);
		String cityName = scanner.next();
		for (String i : addressBookMap.keySet()) {
		List<Contact>	arr = addressBookMap.get(i).contacts;
		arr.stream().filter(person -> person.getCity().equals(cityName)).forEach(person -> System.out.println(person.getFirstName()));
      }		
    }

      /**
  	 	* In this method, searching the person by the state
  	 	**/
	public void searchByState() {
	
	System.out.println("Enter the name of the State to the get persons : ");
	Scanner scanner = new Scanner(System.in);
	String stateName = scanner.next();
	for (String i : addressBookMap.keySet()) {
	List<Contact>	arr = addressBookMap.get(i).contacts;
	arr.stream().filter(person -> person.getState().equals(stateName)).forEach(person -> System.out.println(person.getFirstName()));
  }
}

	public void displayPeopleByRegion(HashMap<String, ArrayList<Contact>> addressBookMap) {
	List<Contact> contacts;
	for (String name : addressBookMap.keySet()) {
		System.out.println("Person is residing in: " + name);
		contacts = addressBookMap.get(name);
		for (Contact contact : contacts) {
			System.out.println(contact);
		}
	}

}
	public void countPeopleByRegion(HashMap<String, ArrayList<Contact>> listToDisplay) {

		System.out.println("Enter the name of the region :");
		String regionName = scanner.next();
		long countPeople = listToDisplay.values().stream()
				.map(region -> region.stream().filter(person -> person.getState().equals(regionName) || person.getCity().equals(regionName)))
				.count();
					
		System.out.println("Number of People residing in " + regionName+" are: "+countPeople+"\n");
		
	   }
	/**
	
	public void sortAddressBook() {
		for (String i : addressBookMap.keySet()) {
			 Map<String, Contact> con = (Map<String, Contact>) addressBookMap.get(i).contacts;
			
			List<Contact> sorted = con.values().stream().sorted((firstperson, secondperson) -> 
			firstperson.getFirstName().compareTo(secondperson.getFirstName())).collect(Collectors.toList());
			
			System.out.println(" Sorted Address Book");
			Iterator iterator = sorted.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
				System.out.println();
			}
		}
	}
	**/
	public void sortAddressBook(){
        List<Contact> sortedContactList;
        for (String name : addressBookMap.keySet()) {
            List<Contact> contactList = (List<Contact>) addressBookMap.get(name);
            @SuppressWarnings("unchecked")
			int sortingChoice = ScannerUtil.getInt("Sort by\n 1.First Name\n 2.City\n 3.State \n4.Zip");
            switch (sortingChoice) {
                case 1: {
                    sortedContactList = contactList.stream()
                            .sorted(Comparator.comparing(Contact::getFirstName))
                            .collect(Collectors.toList());
                    printSortedList(sortedContactList);
                }
                case 2: {
                    sortedContactList = contactList.stream()
                            .sorted(Comparator.comparing(Contact::getCity))
                            .collect(Collectors.toList());
                    printSortedList(sortedContactList);
                }
                case 3: {
                    sortedContactList = contactList.stream()
                            .sorted(Comparator.comparing(Contact::getState))
                            .collect(Collectors.toList());
                    printSortedList(sortedContactList);
                }
                case 4: {
                    sortedContactList = contactList.stream()
                            .sorted(Comparator.comparing(Contact::getZip))
                            .collect(Collectors.toList());
                    printSortedList(sortedContactList);
                }
            }

        }
    }

    /**
     * printSortedList - method to print contacts in addressBook
     *
     * @param sortedContactList - address book that is to be printed
     */
    public void printSortedList(List<Contact> sortedContactList) {
        for (Contact contact : sortedContactList) {
            System.out.println(contact);
            System.out.println();
        }
    }
}