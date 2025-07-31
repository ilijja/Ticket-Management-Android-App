# Ticket Management Android App

A simple Android app for managing tickets by status and priority, with user authentication and role-based access control.

---

## Authentication

- Login with **username/password**
- Two roles: **Admin** and **User**
- Persistent login using `SharedPreferences`
- Splash screen with auto-login functionality

---

## Ticket Management

- Create tickets of type **Bug** or **Enhancement**
- Set **priority** and **estimation**
- Ticket states:
  - To Do
  - In Progress
  - Done
- Move tickets between states via **drag & drop** or tap
- **Filter** and **search** tickets
- Detailed view per ticket

---

## Role-Based Access

- **Admin**:
  - Full access
  - Can delete tickets
- **User**:
  - Can create and move tickets
  - Cannot delete tickets

---

## UI & UX

- **Bottom navigation** with 4 main tabs
- **Tab layout** representing ticket states
- Built with **Material Design**
- Custom, non-scrollable **ViewPager**

---

## App Structure

### Activities

- `MainActivity`: Handles login  
- `AppActivity`: Hosts main UI and navigation

### Fragments

- `StatisticsFragment`: Overview and metrics  
- `AddTicketFragment`: Create new ticket  
- `TicketsFragment`: Holds tabs (To Do, In Progress, Done)  
- `UserProfileFragment`: Profile view and logout  
- `SingleTicketFragment`: Ticket detail view  
- `ToDoFragment`, `InProgressFragment`, `DoneFragment`: Ticket lists by state

### Models & Enums

- `Ticket`, `User`
- `TicketType`: `BUG`, `ENHANCEMENT`
- `PriorityType`: `LOWEST`, `LOW`, `MEDIUM`, `HIGH`, `HIGHEST`
- `UserType`: `USER`, `ADMIN`

---

## Architecture & Technologies

- MVVM architecture with `ViewModel` and `LiveData`
- `RecyclerView` with `DiffUtil`
- `SharedPreferences` for authentication persistence
- Fragment-based UI navigation

---

## Login Credentials

### Admin

- **Username**: `admin`  
- **Password**: `admin`  
- **Email**: any valid format

### User

- **Username**: `user`  
- **Password**: min 5 characters  
- **Email**: any valid format

---

## Dependencies

- AndroidX & Material Components
- `ViewPager2`, `RecyclerView`
- `Timber`
- `SplashScreen API`

---

## Data Flow

1. User logs in via `MainActivity`
2. Auth token is stored in `SharedPreferences`
3. `AppActivity` loads and displays tabbed UI
4. `ViewModel` holds all tickets
5. Fragments observe `LiveData` for updates

---

## Getting Started

1. Clone the repository  
2. Open in Android Studio  
3. Build and run on an emulator or device  
4. Use provided credentials to log in

---

## Permissions

- No special permissions required

---

## Note

> This is a **demo app** with hardcoded authentication and **in-memory storage**.  
> It is **not intended for production** use.
