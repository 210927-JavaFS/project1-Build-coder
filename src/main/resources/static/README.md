# User
int:         id
String:      username
int:         password
String:      firstName
String:      lastName
String:      email
Role:        role


# Invoice
int:        id
double:     amount
Date:       submitted
Date:       resolves
String:     desc
String:     receipt
User:       author
User:       resolver
Status:     status
Type:       type

# Role
EMPLOYEE
MANAGER

# Status
PENDING
APPROVED
DENIED

# Type
LODGING
TRAVEL
FOOD
OTHER

