# CodeAlpha_HotelReservationSystem
Java console app to search, book and manage hotel rooms
# Hotel Reservation System

A console-based Java application that lets users search, book, pay for and manage hotel rooms. Built using Object-Oriented Programming (OOP) principles, with File I/O to store bookings permanently.

## Description
This project simulates a simple hotel booking system. A user can look up available rooms by category, reserve a room for a number of nights, simulate a payment and view or cancel bookings. All reservations are saved in a text file, so the data is still there the next time the program runs.

## Features
- Three room categories: Standard, Deluxe and Suite, each with its own price per night
- Search available rooms by category or view all rooms
- Book a room by entering guest name, room number and number of nights
- Total cost is calculated automatically
- Cancel a reservation (the room becomes available again)
- Payment simulation with credit card or cash options
- View details of a single booking or all bookings
- Input validation, so the program does not crash on wrong input
- Bookings are saved to and loaded from a file (File I/O)

## Room Categories and Prices
| Category | Price per night |
|----------|-----------------|
| Standard | $50 |
| Deluxe   | $90 |
| Suite    | $150 |

## Technologies Used
- Java
- Object-Oriented Programming (classes, objects, encapsulation, enum)
- ArrayList (Collections)
- File I/O (BufferedReader, FileWriter)

## Project Structure
- `RoomCategory.java` - enum for room types and their prices
- `Room.java` - represents a hotel room
- `Reservation.java` - represents a booking
- `Hotel.java` - main logic: search, book, cancel, payment, save and load data
- `Main.java` - console menu and user interaction

## How to Run
1. Clone this repository:
   `git clone https://github.com/SayedMuqaddas/CodeAlpha_HotelReservationSystem.git`
2. Open the folder in any Java IDE (VS Code, IntelliJ, Eclipse)
3. Run `Main.java`

Or from the terminal:
`javac *.java`
`java Main`

## Sample Output
