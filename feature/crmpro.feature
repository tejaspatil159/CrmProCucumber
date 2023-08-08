Feature: Test contacts and deals functionality in crmpro
@SanityTest @EndToEndTest
Scenario: Add new contacts with contacts functionality
Given Verify that user is login successfully
When Mouse on contacts
And Click on new contact
And Enter first name and Enter last name
| FirstName | LastName |
| Rutik | Patil |
| Vivek | Patil |
| Mahesh | Patil |

Then Verify that contacts add successfully

@EndToEndTest @RegressionTest
Scenario: Add new deals
Given Verify that user is login successfully
When Mouse on deals
And Click on new deal
And Enter title and Enter amount and probability
| Title | Amount | probability |
| ASD | 456123 | 50 |
| POI | 852631 | 60 |
| IHB | 741258 | 98 |

Then Verify that deals add successfully