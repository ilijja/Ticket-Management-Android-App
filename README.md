Ticket Management Android App
Simple app for managing tickets by status and priority, with user authentication and role-based access.

🔐 Authentication
Login with username/password

Two roles: Admin and User

Persistent login (SharedPreferences)

Splash screen with auto-login

📝 Ticket Management
Create tickets (Bug / Enhancement)

Set priority and estimation

Ticket states: To Do, In Progress, Done

Drag or tap to move tickets between states

Filter & search tickets

Detailed ticket view

🧑‍💼 Role-Based Access
Admin: Full access, can delete tickets

User: Can create and move tickets, no delete

🎨 UI & UX
Bottom navigation with 4 tabs

Tab layout for ticket states

Material Design

Custom ViewPager (non-scrollable)

📁 Structure
Activities

MainActivity: Handles login

AppActivity: Hosts tabs and navigation

Fragments

StatisticsFragment: Overview

AddTicketFragment: Create ticket

TicketsFragment: Ticket tabs

UserProfileFragment: Profile + logout

ToDoFragment, InProgressFragment, DoneFragment: Ticket lists

SingleTicketFragment: Details view

Models & Enums

Ticket, User

TicketType (BUG, ENHANCEMENT)

PriorityType (LOWEST to HIGHEST)

UserType (USER, ADMIN)

⚙️ Architecture & Tech
MVVM with ViewModel + LiveData

RecyclerView + DiffUtil

SharedPreferences for auth

Fragment-based navigation

👤 Login Credentials
Admin

Username: admin

Password: admin

Email: any valid email

User

Username: user

Password: min 5 characters

Email: any valid email

🧩 Dependencies
AndroidX, Material Components

ViewPager2, RecyclerView

Timber, SplashScreen API

🔄 Data Flow
Login via MainActivity

Auth stored with SharedPreferences

AppActivity loads with 4-tab UI

ViewModel holds tickets

Fragments observe LiveData for updates

🚀 Getting Started
Clone repo

Open in Android Studio

Build & run on device/emulator

Log in and explore

🔒 Permissions
No special permissions required.

⚠️ Note
Demo app with hardcoded auth and in-memory storage. Not for production use.
