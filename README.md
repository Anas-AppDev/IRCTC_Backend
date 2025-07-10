# IRCTC Railway Management System API

A comprehensive railway management system API built with Spring Boot that allows users to check train availability, book seats, and manage railway operations with role-based access control.

## Features

- **User Authentication & Authorization**: JWT-based authentication with role-based access control
- **Train Management**: Admin can add/delete trains and manage routes
- **Seat Booking**: Real-time seat booking with race condition handling
- **Seat Availability**: Check available seats between stations
- **Booking Management**: View booking details and manage reservations
- **Role Management**: Admin and User roles with different permissions
- **Concurrent Booking Safety**: Pessimistic locking to prevent race conditions

## Tech Stack

- **Backend**: Spring Boot 3.x
- **Database**: MySQL
- **Security**: Spring Security with JWT
- **Build Tool**: Maven
- **Java Version**: 17+

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- Git

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/irctc-backend.git
cd irctc-backend
```

### 2. Database Setup
Create a MySQL database (irctcDb) and update the connection details in `application.properties`:

### 3. Build and Run
```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Authentication
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/auth/login` | User/Admin login | Public |

### User Management
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/user/` | Register new user | Public |
| POST | `/api/user/admin` | Create admin user | Admin Only |
| GET | `/api/user/{userId}` | Get user details | Authenticated |
| GET | `/api/user/` | Get all users | Authenticated |
| PUT | `/api/user/{userId}` | Update user | Authenticated |
| DELETE | `/api/user/{userId}` | Delete user | Authenticated |

### Train Management
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/train/` | Add new train | Admin Only |
| GET | `/api/train/` | Get all trains | Authenticated |
| GET | `/api/train/seats` | Get seat availability | Authenticated |
| DELETE | `/api/train/{trainId}` | Delete train | Admin Only |

### Booking Management
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/booking/` | Book a seat | Authenticated |
| GET | `/api/booking/{ticketId}` | Get booking details | Authenticated |

### Role Management
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/role/` | Create new role | Admin Only |
| GET | `/api/role/` | Get all roles | Authenticated |


## Security Features

- **JWT Authentication**: All endpoints (except public ones) require valid JWT token
- **Role-Based Access**: Admin-only endpoints for sensitive operations
- **Password Encryption**: BCrypt password hashing
- **Input Validation**: Request payload validation
- **Exception Handling**: Comprehensive error handling with proper HTTP status codes

## Race Condition Handling

The booking system uses:
- **Pessimistic Locking**: `@Lock(LockModeType.PESSIMISTIC_WRITE)` on seat booking
- **Transactional**: `@Transactional` annotation ensures atomicity
- **Database Constraints**: Proper foreign key constraints


## Assumptions Made

1. **User Registration**: New users are assigned "ROLE_USER" role by default
2. **Admin Creation**: Only existing admins can create new admin users
3. **Seat Booking**: One seat per booking request
4. **Train Routes**: Simple source-destination mapping (no intermediate stations)
5. **Seat Numbering**: Auto-generated seat numbers for bookings

## Testing

### Manual Testing
Use tools like Postman or curl to test the endpoints:

```bash
# Login and get token
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@example.com","password":"admin123"}'

# Use token for authenticated requests
curl -X GET http://localhost:8080/api/train/ \
  -H "Authorization: Bearer <your_jwt_token>"
```

### Database Schema
The application auto-creates the following tables:
- `user_table` - User information
- `role_table` - Role definitions
- `user_role` - User-role mapping
- `train_table` - Train information
- `booking_table` - Booking records


## License

This project is licensed under the MIT License - see the LICENSE file for details.
