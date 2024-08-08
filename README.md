# Project 1: Auction House Management App
#Group Memebers
Emmett, Daniel, Ryan, Alex

## Project Overview

Auction Management App
The Auction app is a single-page full stack web application for clients and admins. In addition to registering and signing in, Users can list items for auction, bid on auctions and leave comments on auctioned items. Admins can approve items for auction or manage user accounts. They can also adjust the active status and remaining time on any said auctions.

### Technologies Used

- **Spring Boot 3.3.2**
  - Spring Boot Starter Web
  - Spring Boot Starter Data JPA
  - Spring Boot Starter Actuator
- **Java 17**
- **PostgreSQL**

### Table Setup

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

As a USER I can create an account

As a USER I can login/logout

As a USER I can delete my account

As a USER I can create an item

As a USER I can bid on an item

As a USER I can leave a comment under an item

As an ADMIN I can manage user accounts

As an ADMIN I can approve the items

As an ADMIN I can edit items

As an ADMIN I can detele comments

## Stretch Goals

- Unit Tests
- Logs
- Existence of some testing (Spring Test/JUnit, Postman Collection, etc)
- Secure your Java API endpoints using JWTs
- Reset Password
- Notifications
- Leveraging a 3rd party API
 
