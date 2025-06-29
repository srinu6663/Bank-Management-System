# Bank Management System

A comprehensive Bank Management System built with Java Swing GUI that provides various banking operations with a user-friendly interface.

## Features

- **User Authentication**: Secure login system with PIN verification
- **Account Management**: Create new accounts with customer details
- **Banking Operations**:
  - Cash Deposit
  - Cash Withdrawal
  - Balance Enquiry
  - Mini Statement Generation
  - PIN Change
- **Loan Services**: Loan application and management
- **Email Notifications**: Automated email notifications for transactions
- **PDF Reports**: Generate mini statements in PDF format

## Technology Stack

- **Language**: Java
- **GUI Framework**: Java Swing
- **Database**: MySQL
- **Libraries**:
  - MySQL Connector/J (8.0.28)
  - iText PDF (5.5.13)
  - JavaMail API
  - JCalendar
  - FlatLaf Look and Feel

## Prerequisites

- Java Development Kit (JDK) 8 or later
- MySQL Server
- IDE (IntelliJ IDEA recommended)

## Setup Instructions

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd "Bank Management System"
   ```

2. **Database Setup**
   - Install MySQL Server
   - Create a database named `banksystem`
   - Execute the SQL queries from `src/SQL/SQL Quary` file

3. **Configure Database Connection**
   - Copy `src/bank/management/system/Con.java.template` to `src/bank/management/system/Con.java`
   - Update the database connection details:
     ```java
     connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem", "your_username", "your_password");
     ```

4. **Add Required Libraries**
   - MySQL Connector/J
   - iText PDF
   - JavaMail API
   - JCalendar
   - FlatLaf

5. **Run the Application**
   - Compile and run `SplashScreen.java` to start the application

## Project Structure

```
Bank Management System/
├── src/
│   ├── bank/management/system/
│   │   ├── BalanceEnquiry.java      # Balance inquiry functionality
│   │   ├── Con.java                 # Database connection (excluded from repo)
│   │   ├── Deposit.java             # Cash deposit operations
│   │   ├── EmailNotificationService.java # Email notifications
│   │   ├── Home.java                # Main dashboard
│   │   ├── Loans.java               # Loan management
│   │   ├── Login.java               # User authentication
│   │   ├── mini.java                # Mini statement generation
│   │   ├── Pin.java & Pinc.java     # PIN management
│   │   ├── Signup*.java             # Account registration
│   │   ├── SplashScreen.java        # Application entry point
│   │   ├── Transaction.java         # Transaction history
│   │   └── Withdrawal.java          # Cash withdrawal
│   ├── icn/                         # Application icons and images
│   ├── META-INF/                    # Manifest files
│   └── SQL/                         # Database scripts
├── .gitignore
└── README.md
```

## Screenshots

The application features a modern and intuitive interface with:
- Splash screen with loading animation
- Login/Registration forms
- Dashboard with quick access to all banking operations
- Transaction forms with validation
- PDF statement generation

## Security Features

- PIN-based authentication
- Database credentials are kept secure (excluded from repository)
- Input validation and sanitization
- Secure transaction processing

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact

For any questions or support, please contact the development team.

---

**Note**: Make sure to configure your database connection in `Con.java` before running the application. The template file is provided for security purposes.
