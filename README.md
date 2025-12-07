# Remind-and-Recover
Remind-and-Recover: Stay on Track, Never Miss a Beat
## 	App Name = RE&RE (Remind and Recover)

 ## Problem/Issue the App is Going to Address
-	Many patients forget to take their prescribed medications or miss therapy routines on time, which delays recovery and affects overall health outcomes. Forgetfulness, busy schedules, or lack of proper tracking often leads to skipped doses, overdosing, or irregular therapy sessions.
  
The RE & RE app addresses this issue by:
-	Sending users timely reminders with medicine name, dosage, and quantity.
- Helping users stay consistent with their recovery by sending notifications for daily routines such as therapy exercises, medication schedules, and proper sleep.
-	Supporting users in building healthy habits that contribute to faster recovery and better long-term health outcomes.

## Detailed Descriptions of Each Activity
## Activity 1: 
Sign Up Page: 
The sign-up page is designed to provide a simple and secure onboarding process for new users. It collects essential personal details such as first name, last name, age, weight, height, and username, along with a password setup for account security.

<img width="500" height="800" alt="image" src="https://github.com/user-attachments/assets/084f248f-c78f-4e71-b7bd-43c92127a71e" />


## Activity 2:

Sign-In Page
The Sign-In Page enables existing users to securely access their account and continue using the RE&RE application. This activity is designed with simplicity, security, and user convenience in mind. It includes input fields for username and password, ensuring that only authorized users can log in.
Upon entering valid credentials, the system verifies the information against stored user data and grants access to the Home Page. In case of incorrect input, the user receives a validation message prompting them to recheck their credentials. This page ensures safe and reliable authentication before accessing personal health and reminder information.

<img width="500" height="800" alt="image" src="https://github.com/user-attachments/assets/f9658cb3-4bf3-4b8d-9ead-95b6443cb86c" />


 
## Activity 2: 
Home page:   
The Home Page of the Re&Re application is designed to provide users with quick access to the core features of the app in a clear and organized manner. The page displays six main options:
Add Medication: Allows users to enter and schedule new medications with details such as dosage and timing.
Remove Medication: Provides an option to delete or update medications that are no longer required.
Sleep Tracker: Helps users record and monitor their sleep patterns for better health management.
Appointment Tracker: Enables users to set reminders and track upcoming doctor or therapy appointments.
Therapy: Offers a dedicated section to manage therapy-related activities, notes, or reminders.The layout is designed to be intuitive and user-friendly, ensuring that users can easily navigate to their desired feature directly from the home screen.
<img width="532" height="875" alt="image" src="https://github.com/user-attachments/assets/7e65f5ce-5d36-42d9-9bcd-9da9aaaa5eee" />


 

## Activity 3: 
Add Medication
The add medication feature enables users to include a new medicine in their profile. Users can enter details such as the medication name, dosage, frequency, and start/end dates. After saving, the system schedules reminders and notifications to help the user take their medication on time. This feature ensures that all medications are tracked accurately, and users maintain an organized and complete medication schedule.

<img width="519" height="825" alt="image" src="https://github.com/user-attachments/assets/472178d2-2fef-41d2-8ae2-94326e21dea6" />


 
## Activity4:   
RemoveMedication
The medication removal feature allows users to delete a medicine from their profile when it is no longer required. Users can select the specific medication from their list, confirm the removal, and the system updates their schedule accordingly. Once removed, all associated reminders and notifications for that medication are automatically deleted, helping users keep their medication list accurate and up to date.

<img width="475" height="775" alt="image" src="https://github.com/user-attachments/assets/f097ce38-850a-4bac-9131-dfc07e6b613b" />


 

## Activity 5: 
SleepTracker
The purpose of this activity is to promote healthy sleep routines by allowing users to set sleep goals, track bedtime and wake-up times, and reflect on their sleep consistency. This helps in improving overall wellness, productivity, and health outcomes by encouraging adequate rest.

<img width="449" height="810" alt="image" src="https://github.com/user-attachments/assets/786bd57d-2923-41bf-a178-c26955b14bc1" />


 
## Activity 6: 
Appointment Tracker: 
This activity’s main purpose is to provide an easy way for users to log, track, and get reminders for upcoming appointments, reducing the risk of forgetting or missing important visits. It improves time management, organization, and reliability for health-related commitments.

<img width="537" height="999" alt="image" src="https://github.com/user-attachments/assets/a06f0ade-2eca-47e8-9f6a-a0bd17c4dc18" />

## Activity 7 
Therapy:
The purpose of this activity is to provide users with an easy way to schedule, track, and receive reminders for their therapy sessions. It ensures better consistency, adherence to treatment plans, and improved health outcomes by reducing the chance of forgetting therapy.
<img width="452" height="855" alt="image" src="https://github.com/user-attachments/assets/2b977cf0-0697-4f69-9f62-4a8bd15392b2" />


## Notification
The RE&RE application includes a comprehensive notification system designed to remind users of their scheduled health activities. This system uses Android’s built-in Notification Manager along with AlarmManager or WorkManager to deliver timely alerts without requiring internet access or external APIs.

Notifications are automatically generated for the following events:
Medication Reminders: Alerts users at scheduled times with medication name, dosage, and frequency.
Therapy Session Reminders: Notifies users of upcoming therapy activities to ensure consistency in their recovery plan.
Appointment Reminders: Sends alerts prior to medical or therapy appointments to reduce the risk of missed visits.
Sleep Schedule Tracking: Provides optional reminders to promote regular sleep routines.
Each notification appears on the device at the exact user-defined time and works reliably even when the application is running in the background. This system ensures that all critical health tasks are completed consistently, promoting better recovery and long-term wellness.

<img width="513" height="924" alt="image" src="https://github.com/user-attachments/assets/43355d98-1705-42f3-863e-8ecaff614db0" />


## Workflow Diagram
 <img width="975" height="597" alt="image" src="https://github.com/user-attachments/assets/96a0a9b0-18de-4408-bbc1-75b5996151f9" />
 <img width="975" height="536" alt="image" src="https://github.com/user-attachments/assets/e6d15650-64c0-410b-985a-804e12f9327b" />


 

## Data to Persist on the App:
Persist on the app ((private to one user) roomdatabase SQLlite)
1.	Things that belong only to the user and stay saved on their device/account.
2.	Examples from your sketches:
3.	Clinic Name
4.	Appointment Date & Time
5.	Therapy Session Time
6.	Therapy Type
7.	Reminders (hrs/day)
8.	Sleep Goal
9.	Sleeping Time



## Data to Share Across Users:
-	For the initial version of RE&RE, there will be no data shared publicly or between users. The application is designed as a private, personal health assistant. All data will be stored securely and tied to the individual user's account.
-	Risky Components
-  Data Privacy and Security Risks: Storing personal health information (PHI) like medication details, therapy types, and sleep goals requires strict compliance with data protection regulations . Any mishandling could lead to serious consequences.
-  Notification Reliability: The app relies heavily on reminders and alerts. If notifications fail due to system errors or device restrictions, users may miss critical doses or appointments.
-  User Input Errors: Incorrect data entry could lead to health complications. The app must include validation and confirmation mechanisms to minimize this risk.
-  Device Dependency: If the app is only available on one device and lacks cloud backup, users may lose all data if the device is lost or damaged.

## 	Uses an outside API : 
At this stage, the RE&RE app doesn’t use any external APIs to keep sensitive health data — like medication schedules and therapy routines fully private and secure. All operations are handled locally on the device, minimizing exposure to third-party services. In future updates, we plan to add features like push notifications for reminders and smart appointment scheduling using Android tools, and possibly calendar sync to help users manage health appointments more easily. These enhancements will improve the user experience while keeping privacy and security a top priority.

## Requires functionality we will not talk about : 
-	Currently, the app does not require any of the following 
-	Location Services / Maps: No mention of GPS or clinic location mapping.
-	Media Playback or Recording: No audio/video features are described.
-	Networking or Messaging: No user-to-user interaction or data sharing.
-	In-app Purchases or Ads: Monetization is not part of the current scope.

## 	 Requires functionality we will talk about later (maps, media, data base services, etc.) 
This project will include functionality related to reminders and notifications. Users will be able to set reminders for specific tasks or events, and the system will send notifications at scheduled times. These features involve background services and notification handling, which will be covered in later sessions.

## Team members:
Vamshi Krishna Balupari(S578138)
Himaja Kaishetty(S575197)
Vaishnavi Sidduri(S574385)
Praneetha Reddy Lakkam(S57492)

## Application information
## Test credentials   
user1 / password1
user2 / password2
admin / admin123
## APK
We have added reminder and notification functionality, but these reminders are created using Android’s built-in Notification and Alarm/WorkManager services, which do not require any external API or internet connection.
## Supported devices
Android Virtual Devices (AVD) running Android 9+
## Sequence information
User → Login → Home Screen  
        → Add Medication → DB Save → Notifications  
        → Remove Medication → DB Update  
        → Sleep Tracker → DB Save  
        → Appointment Tracker → DB Save → Notifications  
        → Therapy Tracker → DB Save → Notifications  


## Data Persistence
Data Stored Privately (Per-User)

- All data is stored locally on the user’s device/account such as:
- Clinic Name
- Appointment Date & Time
- Therapy Session Time & Type
- Medication details
- Sleep Goals
- Sleep/Wake-up Times
- Reminders (frequency, hours/day)
- Data Shared Across Users

### None
This version of RE&RE does not share any data publicly or between users. All information remains private to the device/account.

### Outside API Usage

The RE&RE app does not use any external API.
- All data is processed locally.
- Notifications are handled through Android Notification Manager, AlarmManager, or WorkManager.
- No internet connection is required.
- Future enhancements may include:
- Calendar integration
- Push-notification enhancements
- Smart scheduling tools

## FUNCTION

The application incorporates all required components, including:
- Multiple activities and intent-based navigation
- Local data persistence using appropriate storage mechanisms
- Android notification framework
- AlarmManager or WorkManager for scheduled reminders
- Form components and RecyclerView where applicable
- Input validation
- Adherence to fundamental Android UI and UX design principles
- All course concepts have been applied correctly and effectively.

## Application Status Features and Current Status

All implemented features have been tested and are confirmed to be functioning properly. This includes:

- Medication tracking
- Therapy scheduling
- Sleep monitoring
- Appointment tracking
- Reminder and notification systems
The application performs reliably on all supported Android versions running Android 9 (Pie) and above.
