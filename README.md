# FCS5-SC2002-Group-3
# BTO Management System

The **BTO Management System** is a Java-based application designed to support multiple user roles and interactions, each with appropriate workflows and functionalities. This system is intended to streamline processes for applicants, HDB officers, and HDB managers within the Build-To-Order (BTO) system.

## Features

### **Applicant**
- **View Projects**: Consider open and eligible projects based on the applicant's profile.
- **Apply for Projects**: Apply for a project if eligible and have not yet applied.
- **Check Application Status**: Track the status of submitted applications.
- **Withdraw Application**: Request withdrawal from a project if already applied.
- **Submit Enquiries**: Submit enquiries related to projects.
- **View Enquiries**: View submitted enquiries.
- **Edit Enquiries**: Edit enquiries by ID.
- **Delete Enquiries**: Delete enquiries that are not yet closed.
- **Set and Get Flat Types**: Manage preferred flat types.
- **Profile and Summary**: Display personal profile and application summary.

### **HDB Officer**
- **Applicant Features**: Implements all features available to applicants (apply, check status, withdraw, etc.).
- **Request Project Assignment**: Request assignment to projects, and get assigned by a manager.
- **View Assigned Projects**: View details of assigned projects.
- **View All Requests**: View all requests for the assigned projects.
- **Respond to Enquiries**: Respond to applicant enquiries by ID.
- **Book Flats**: Book flats for successful applicants, if assigned to the project.
- **Generate Receipts**: Generate receipts for applicant bookings.
- **Track Assigned Projects**: Track all projects assigned to the officer.

### **HDB Manager**
- **Project Management**: Create new projects with elaborate configurations, modify project details (name, flat type, dates, prices), and delete projects.
- **Toggle Project Visibility**: Change the visibility of projects as required.
- **View Projects**: View all projects or only those created by the manager.
- **Manage Enquiries**: View and respond to enquiries on projects created by the manager.
- **Approve Officers**: Approve officers for project assignments.
- **Manage Applicants**: Approve or reject applicants for projects and allocate application statuses.
- **Track Withdrawals**: Accept applicant withdrawal requests and update project status.
- **Filtered Reports**: Generate filtered reports on candidates based on specific criteria.

## Requirements
- **Java 8+**
- IDE like IntelliJ IDEA or Eclipse
- Git for version control

## Installation
1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/BTO-Management-System.git