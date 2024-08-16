# Project 1: Auction House Management App
**Group Members**:
Emmett, Daniel, Ryan, Alex

## Project Overview

Auction Management App
The Auction app is a single-page full stack web application for clients and admins. In addition to registering and signing in, Users can list items for auction, bid on auctions and leave comments on auctioned items. Admins can approve items for auction or manage user accounts. They can also adjust the active status and remaining time on any said auctions.

## Technologies Used

- **Spring Boot 3.3.2**
  - Spring Boot Starter Web
  - Spring Boot Starter Data JPA
  - Spring Boot Starter Actuator
- **Java 17**
- **PostgreSQL**
- **Git**

## Table Setup

- **User**
	- user_id
	- username
  	- userImage
	- password
	- isAdmin?
	
- **Bid**
	- bid_id
	- amount
	- user_bidder
	- item_bidFor
	- timestamp
 
- **Item**
  - item_id
  - user_owner
  - isApproved?
  - itemImage
  - itemName
  - itemDescription
 
- **Comment**
	- comment_id
	- user_commenter
	- item_commentOn
	- message
	- timestamp

## Core User Stories

- As a USER I can create an account
	- Decription: User can create an account with usernamme and password.
 	- Expected Result: New User is created

- As a USER I can login/logout
	- Decription: User can login using password and logout when done with app.
 	- Expected Result: User is validated on correct login

- As a USER I can delete my account
	- Decription: User can delete account from database
 	- Expected Result: Account is deleted

- As a USER I can create an item
	- Decription: User and can create an item to be auctioned off user cannot approve said item
 	- Expected Result: User adds a valid item into the database to be approved for auction

- As a USER I can bid on an item
	- Decription: User can specify a bid for a specific item in the database
 	- Expected Result: The item recieves a bid tied to a specific user

- As a USER I can leave a comment under an item
	- Decription: The user can leave a comment under an item
 	- Expected Result: The item now has a comment associated with it tied a specific user

- As an ADMIN I can manage user accounts
	- Decription: Admin can view all accounts and items and messages associated with accounts
 	- Expected Result: A list of user accounts

- As an ADMIN I can approve the items
	- Decription: An admin can change an items approval status to true
 	- Expected Result: An item now has approved set to true

- As an ADMIN I can edit items
	- Decription: An admin can edit Items that were not posted by themselves
 	- Expected Result: Edits made to any specified item

- As an ADMIN I can detele comments
	- Decription: Admin can delete comments that are associated with a specific item
 	- Expected Result: Comments with Id that are associated with Item are deleted
 
## Unit Tests
- Implemented using https://workik.com/ai-powered-unit-test-case-generator

## Stretch Goals

- Unit Tests
- Logs
- Existence of some testing (Spring Test/JUnit, Postman Collection, etc)
- Secure your Java API endpoints using JWTs
- Reset Password
- Notifications
- Leveraging a 3rd party API
 
