# MyContactsApp

## UC-09: Search Contacts
The Search Contacts feature allows a user to search contacts by name, phone number, email, or tags using regular expressions.  
After selecting a search category, the user enters a keyword (e.g., "ra"), and the system displays all matching contacts with their original index numbers.  
The user can then select a specific contact by entering its displayed number to view detailed information.

## UC-08: Bulk Operations
The Bulk Operations feature allows a logged-in user to perform actions on multiple contacts at once.  
Users can select multiple contacts and choose to delete them, add a common tag, or export them (dummy operation).  
This feature improves efficiency by enabling batch processing instead of handling contacts individually.

## UC-07: Delete Contact
The Delete Contact feature allows users to permanently remove an existing contact from their contact list.  
Users can select a contact and choose the delete option with confirmation before removal.  
The contact is safely removed from the user's contact collection, ensuring data consistency.

## UC-06: Edit Contact
The Edit Contact feature allows users to update existing contact details.  
Users can modify the name, number, or email of a selected contact.  
Changes are applied instantly while maintaining proper encapsulation.