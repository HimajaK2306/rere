# Remind-and-Recover

## 	App Name = RE&RE (Remind and Recover)

 ## Problem/Issue the App is Going to Address
•	Many patients forget to take their prescribed medications or miss therapy routines on time, which delays recovery and affects overall health outcomes. Forgetfulness, busy schedules, or lack of proper tracking often leads to skipped doses, overdosing, or irregular therapy sessions.
The RE & RE app addresses this issue by:
•	Sending users timely reminders with medicine name, dosage, and quantity.
•	Helping users stay consistent with their recovery by sending notifications for daily routines such as therapy exercises, medication schedules, and proper sleep.
•	Supporting users in building healthy habits that contribute to faster recovery and better long-term health outcomes.

## Indicate the activities that will be created and what their intended purpose is:
## Activity 1: 
Sign Up Page: 
The sign-up page is designed to provide a simple and secure onboarding process for new users. It collects essential personal details such as first name, last name, age, weight, height, and username, along with a password setup for account security.
<img width="347" height="366" alt="image" src="https://github.com/user-attachments/assets/092b3984-daea-4446-8d4d-2eb454ff6383" />


 
## Activity 2: 
Home page:   
The Home Page of the Re&Re application is designed to provide users with quick access to the core features of the app in a clear and organized manner. The page displays six main options:
Add Medication: Allows users to enter and schedule new medications with details such as dosage and timing.
Remove Medication: Provides an option to delete or update medications that are no longer required.
Sleep Tracker: Helps users record and monitor their sleep patterns for better health management.
Appointment Tracker: Enables users to set reminders and track upcoming doctor or therapy appointments.
Therapy: Offers a dedicated section to manage therapy-related activities, notes, or reminders.The layout is designed to be intuitive and user-friendly, ensuring that users can easily navigate to their desired feature directly from the home screen.<img width="469" height="669" alt="image" src="https://github.com/user-attachments/assets/ce1e9f68-6328-4fa4-a28e-007fe69e95ea" />

 

## Activity 3: 
Add Medication
The add medication feature enables users to include a new medicine in their profile. Users can enter details such as the medication name, dosage, frequency, and start/end dates. After saving, the system schedules reminders and notifications to help the user take their medication on time. This feature ensures that all medications are tracked accurately, and users maintain an organized and complete medication schedule.
<img width="353" height="358" alt="image" src="https://github.com/user-attachments/assets/db133660-b831-4b2f-946c-efb982a83619" />

 
## Activity4:   
RemoveMedication
The medication removal feature allows users to delete a medicine from their profile when it is no longer required. Users can select the specific medication from their list, confirm the removal, and the system updates their schedule accordingly. Once removed, all associated reminders and notifications for that medication are automatically deleted, helping users keep their medication list accurate and up to date.
<img width="359" height="369" alt="image" src="https://github.com/user-attachments/assets/2b29311b-6bdb-4e2a-bf1a-6b87a60c6179" />

 

## Activity 5: 
SleepTracker
The purpose of this activity is to promote healthy sleep routines by allowing users to set sleep goals, track bedtime and wake-up times, and reflect on their sleep consistency. This helps in improving overall wellness, productivity, and health outcomes by encouraging adequate rest.
<img width="307" height="389" alt="image" src="https://github.com/user-attachments/assets/3949dcfc-2a70-41f3-9e8d-aab6d43037ef" />

 
## Activity 6: 
Appointment Tracker: 
This activity’s main purpose is to provide an easy way for users to log, track, and get reminders for upcoming appointments, reducing the risk of forgetting or missing important visits. It improves time management, organization, and reliability for health-related commitments.
<img width="311" height="395" alt="image" src="https://github.com/user-attachments/assets/8b7020ab-22dd-48e1-970b-c8153bd27337" />

 





## Activity 7 
Therapy:
The purpose of this activity is to provide users with an easy way to schedule, track, and receive reminders for their therapy sessions. It ensures better consistency, adherence to treatment plans, and improved health outcomes by reducing the chance of forgetting therapy.
<img width="294" height="311" alt="image" src="https://github.com/user-attachments/assets/1afa9ca7-c765-46a6-96b0-b97b436cf483" />


 
## Workflow Diagram
 <img width="975" height="597" alt="image" src="https://github.com/user-attachments/assets/96a0a9b0-18de-4408-bbc1-75b5996151f9" />
 <img width="975" height="536" alt="image" src="https://github.com/user-attachments/assets/e6d15650-64c0-410b-985a-804e12f9327b" />


 

## Data to Persist on the App:
Persist on the app (private to one user)
1.	Things that belong only to the user and stay saved on their device/account.
2.	Examples from your sketches:
3.	Clinic Name
4.	Appointment Date & Time
5.	Therapy Session Time
6.	Therapy Type
7.	Reminders (hrs/day)
8.	Sleep Goal
9.	Sleeping Time
10.	Wakeup Time



## Data to Share Across Users:
•	For the initial version of RE&RE, there will be no data shared publicly or between users. The application is designed as a private, personal health assistant. All data will be stored securely and tied to the individual user's account.
•	Risky Components
•  Data Privacy and Security Risks: Storing personal health information (PHI) like medication details, therapy types, and sleep goals requires strict compliance with data protection regulations . Any mishandling could lead to serious consequences.
•  Notification Reliability: The app relies heavily on reminders and alerts. If notifications fail due to system errors or device restrictions, users may miss critical doses or appointments.
•  User Input Errors: Incorrect data entry could lead to health complications. The app must include validation and confirmation mechanisms to minimize this risk.
•  Device Dependency: If the app is only available on one device and lacks cloud backup, users may lose all data if the device is lost or damaged.

## 	Uses an outside API : 
At this stage, the RE&RE app doesn’t use any external APIs to keep sensitive health data — like medication schedules and therapy routines fully private and secure. All operations are handled locally on the device, minimizing exposure to third-party services. In future updates, we plan to add features like push notifications for reminders and smart appointment scheduling using Android tools, and possibly calendar sync to help users manage health appointments more easily. These enhancements will improve the user experience while keeping privacy and security a top priority.

## Requires functionality we will not talk about : 
•	Currently, the app does not require any of the following 
•	Location Services / Maps: No mention of GPS or clinic location mapping.
•	Media Playback or Recording: No audio/video features are described.
•	Networking or Messaging: No user-to-user interaction or data sharing.
•	In-app Purchases or Ads: Monetization is not part of the current scope.

## 	 Requires functionality we will talk about later (maps, media, data base services, etc.) 
This project will include functionality related to reminders and notifications. Users will be able to set reminders for specific tasks or events, and the system will send notifications at scheduled times. These features involve background services and notification handling, which will be covered in later sessions.
