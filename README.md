# Readme for the code walk through

### Problem break down 
1. Setup HorseDetails
   - number
   - Horse name
   - Odds
   - won

2. Setup inventory
    - currency
    - inventory

3. Acceptable input
    - R or r
    - Q or q
    - W or w [1-7]
    - [1-7] <amount>
   
4. Core logic
   - Betting
   - Cash payment/distribution if wins

5. Validations
   - Invalid input
   - Insufficient amount (then do restock)
   - Invalid bet (must be digit)

### Implementation
   - Command line setup with different commands
   - Set up initial inventory and Horse details
     - Currently, the details are hard coded
   - Printing the inventory and horse details
   - Quting the application
   - Restocking the inventory
   - Setting the winning horse
   - Betting and Payment

### Betting
   - A Simple concept used for betting:
     - if the horse is winning horse the betting amount will be calcuated with this formula: payment = amount * odds
### Payment
   - A typical payment distribution logic where the bills will be despense based on decending order of the inventory or fund
   - I used Tree Map with decending order to get optimal cash despense.

### Unit test
   - I had put a demo unit test just to show the framwork utility.
   - I might add more unit test if time permits.

