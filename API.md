
***1. Read all the members***
---
 fetches all the members in the organization

* **URL**

  /lowesforgeeks/member

* **Method:**
  
  GET
  
*  **URL Params**

   **Required:**
 
   request header `loggedInMemberId=[integer]`

   **Optional:**

* **Data Params**

* **Sample Query**
    `curl localhost:8080/lowesforgeeks/member/1 --header "loggedInMemberId":1`

* **Success Response:**

  * **Code:** 200 
    **Content:** `[
  {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": false,
    "organizationAdmin": true,
    "team_id": null,
    "organization_id": 1
  }
]`


***2. Read members by id***
---ir unique id.

* **URL**

  /lowesforgeeks/member/{id}

* **Method:**
  
  GET
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
   path variable `id=[integer]`

   **Optional:**

* **Data Params**

* **Success Response:**

  * **Code:** 200 
    **Content:** `[
  {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": false,
    "organizationAdmin": true,
    "team_id": null,
    "organization_id": 1
  }
]`

***3. Read members by first name or last name or both***
---
 fetches a member by their first name or last name or both.

* **URL**

  /lowesforgeeks/member/search/

* **Method:**
  GET
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`

   **Optional:**
   `first=[String]`
    `last=[String]`
* **Data Params**

* **Success Response:**

  * **Code:** 200 
    **Content:** `[
  {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": false,
    "organizationAdmin": true,
    "team_id": null,
    "organization_id": 1
  }
]`


***4. Create a member***
---
 creates a member.

* **URL**

  /lowesforgeeks/member

* **Method:**
  
  POST
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`

   **Optional:**
	`teamId=[Integer]`
* **Data Params**
    `{"firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com"}`

* **Success Response:**

  * **Code:** 200 
    **Content:** `[
  {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": false,
    "organizationAdmin": true,
    "team_id": null,
    "organization_id": 1
  }
]`


***5. Update a member's first name***
---
 changes the first name of a member.

* **URL**

  /lowesforgeeks/member/update/changeFirstName/{firstName}

* **Method:**
  
  PUT
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
    path variable `firstName=[string]`
   **Optional:**

* **Data Params**


* **Success Response:**

  * **Code:** 200 
    **Content:** `[
  {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": false,
    "organizationAdmin": true,
    "team_id": null,
    "organization_id": 1
  }
]`

***6. Update a member's last name***
---
 changes a member's last name.

* **URL**

  /lowesforgeeks/member/update/changeLastName/{lastName}

* **Method:**
  
  PUT
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
    path variable `lastName=[string]`
   **Optional:**

* **Data Params**

* **Success Response:**

  * **Code:** 200 
    **Content:** `[
  {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": false,
    "organizationAdmin": true,
    "team_id": null,
    "organization_id": 1
  }
]`


***7. Update a member's email id***
---
 changes a member's email id.

* **URL**

  /lowesforgeeks/member/update/changeEmail/{email}

* **Method:**
  
  PUT
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
    path variable `email=[string]`
   **Optional:**

* **Data Params**

* **Success Response:**

  * **Code:** 200 
    **Content:** `[
  {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": false,
    "organizationAdmin": true,
    "team_id": null,
    "organization_id": 1
  }
]`


***8. Make a member team admin***
---
 makes a member whose id is given as path variable, a team admin

* **URL**

  /lowesforgeeks/member/update/makeTeamAdmin/{memberId}

* **Method:**
  
  PUT
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
    path variable `memberId=[integer]`
   **Optional:**

* **Data Params**

* **Success Response:**

  * **Code:** 200 
    **Content:** `[
  {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": true,
    "organizationAdmin": true,
    "team_id": 1,
    "organization_id": 1
  }
]`



***9. Make a member organization admin***
---
 makes a member whose id is given as path variable, a team admin

* **URL**

  /lowesforgeeks/member/update/makeOrganizationAdmin/{memberId}

* **Method:**
  
  PUT
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
    path variable `memberId=[integer]`
   **Optional:**

* **Data Params**

* **Success Response:**

  * **Code:** 200 
    **Content:** `[
  {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": false,
    "organizationAdmin": true,
    "team_id": null,
    "organization_id": 1
  }
]`


***10. Demote a member who is a team admin***
---
 removes a member whose id is given as path variable, from team admin position

* **URL**

  /lowesforgeeks/member/update/removeTeamAdmin/{memberId}

* **Method:**
  
  PUT
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
    path variable `memberId=[integer]`
   **Optional:**

* **Data Params**

* **Success Response:**

  * **Code:** 200 
    **Content:** `[
  {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": false,
    "organizationAdmin": true,
    "team_id": null,
    "organization_id": 1
  }
]`

***11. Demote a member who is an organization admin***
---
 removes a member whose id is given as path variable, from organization admin position

* **URL**

  /lowesforgeeks/member/update/removeOrganizationAdmin/{memberId}

* **Method:**
  
  PUT
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
    path variable `memberId=[integer]`
   **Optional:**

* **Data Params**

* **Success Response:**

  * **Code:** 200 
    **Content:** `[
  {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": false,
    "organizationAdmin": false,
    "team_id": null,
    "organization_id": 1
  }
]`









-----
-----
----
---

***1. Read all the teams***
---
 fetches all the teams in the organization

* **URL**

  /lowesforgeeks/team

* **Method:**
  
  GET
  
*  **URL Params**

   **Required:**
 
   request header `loggedInMemberId=[integer]`

   **Optional:**

* **Data Params**

* **Sample Query**
    `curl localhost:8080/lowesforgeeks/team --header "loggedInMemberId":1`

* **Success Response:**

  * **Code:** 200 
    **Content:** `{
  "teamId": 1,
  "teamName": "Team1",
  "members": [
    {
      "id": 1,
      "firstName": "Kshitij",
      "lastName": "Raj",
      "email": "kshtjraj05@gmail.com",
      "teamAdmin": true,
      "organizationAdmin": true,
      "team_id": null,
      "organization_id": 1
    }
  ]
}`


***2. Read teams by id***
---
 fetches a team by their unique id.

* **URL**

  /lowesforgeeks/team/{id}

* **Method:**
  
  GET
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
   path variable `id=[integer]`

   **Optional:**

* **Data Params**

* **Success Response:**

  * **Code:** 200 
    **Content:** `{
  "teamId": 1,
  "teamName": "Team1",
  "members": [
    {
      "id": 1,
      "firstName": "Kshitij",
      "lastName": "Raj",
      "email": "kshtjraj05@gmail.com",
      "teamAdmin": true,
      "organizationAdmin": true,
      "team_id": null,
      "organization_id": 1
    }
  ]
}`

***3. Read teams by name***
---
 fetches a team by their name.

* **URL**

  /lowesforgeeks/team/search/

* **Method:**
  GET
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
   query param `name=[string]`

   **Optional:**

* **Data Params**

* **Success Response:**

  * **Code:** 200 
    **Content:** `{
  "teamId": 1,
  "teamName": "Team1",
  "members": [
    {
      "id": 1,
      "firstName": "Kshitij",
      "lastName": "Raj",
      "email": "kshtjraj05@gmail.com",
      "teamAdmin": true,
      "organizationAdmin": true,
      "team_id": null,
      "organization_id": 1
    }
  ]
}`



***4. Create a team***
---
 creates a team.

* **URL**

  /lowesforgeeks/team

* **Method:**
  
  POST
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`

   **Optional:**

* **Data Params**
    `{	"teamName": "Team1"}	`

* **Success Response:**

  * **Code:** 200 
    **Content:** `{
  "teamId": 1,
  "teamName": "Team1",
  "members": [
    {
      "id": 1,
      "firstName": "Kshitij",
      "lastName": "Raj",
      "email": "kshtjraj05@gmail.com",
      "teamAdmin": true,
      "organizationAdmin": true,
      "team_id": null,
      "organization_id": 1
    }
  ]
}`


***5. Add a member to the team***
---
 adds a member to the team

* **URL**

  /lowesforgeeks/member/update/add/{memberId}

* **Method:**
  
  PUT
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
   header 'teamId=[Integer]'
    path variable `memberId=[integer]`
   **Optional:**

* **Data Params**


* **Success Response:**

  * **Code:** 200 
    **Content:** `{
  "teamId": 1,
  "teamName": "Team1",
  "members": [
    {
      "id": 1,
      "firstName": "Kshitij",
      "lastName": "Raj",
      "email": "kshtjraj05@gmail.com",
      "teamAdmin": true,
      "organizationAdmin": true,
      "team_id": 1,
      "organization_id": 1
    },
    {
      "id": 3,
      "firstName": "Sanchit",
      "lastName": "Kapoor",
      "email": "sanchit@gmail.com",
      "teamAdmin": false,
      "organizationAdmin": false,
      "team_id": null,
      "organization_id": 1
    }
  ]
}`

***6. Remove a member from the team***
---
 changes a member's last name.

* **URL**

  /lowesforgeeks/team/update/remove/{memberId}

* **Method:**
  
  PUT
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
    path variable `memberId=[integer]`
   **Optional:**

* **Data Params**

* **Success Response:**

  * **Code:** 200 
    **Content:** `[
  {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": false,
    "organizationAdmin": true,
    "team_id": null,
    "organization_id": 1
  }
]`


***7. Make a team member team admin***
---
 makes an existing member of the team a team admin

* **URL**

  /lowesforgeeks/team/update/makeTeamAdmin/{memberId}

* **Method:**
  
  PUT
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
    path variable `memberId=[integer]`
   **Optional:**

* **Data Params**

* **Success Response:**

  * **Code:** 200 
    **Content:** `{
  "headers": {},
  "body": {
    "teamId": 1,
    "teamName": "Team1",
    "members": [
      {
        "id": 1,
        "firstName": "Kshitij",
        "lastName": "Raj",
        "email": "kshtjraj05@gmail.com",
        "teamAdmin": true,
        "organizationAdmin": true,
        "team_id": 1,
        "organization_id": 1
      },
      {
        "id": 304,
        "firstName": "Arpit",
        "lastName": "Pathak",
        "email": "sanchit@gmail.com",
        "teamAdmin": true,
        "organizationAdmin": false,
        "team_id": 1,
        "organization_id": 1
      }
    ]
  },
  "statusCode": "OK",
  "statusCodeValue": 200
}`


***8. Remove a team member from admin position of the team***
---
 demotes a team member from team admin to normal team member

* **URL**

  /lowesforgeeks/team/update/removeTeamAdmin/{memberId}

* **Method:**
  
  PUT
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
    path variable `memberId=[integer]`
   **Optional:**

* **Data Params**

* **Success Response:**

  * **Code:** 200 
    **Content:** `{
  "headers": {},
  "body": {
    "teamId": 1,
    "teamName": "Team1",
    "members": [
      {
        "id": 1,
        "firstName": "Kshitij",
        "lastName": "Raj",
        "email": "kshtjraj05@gmail.com",
        "teamAdmin": true,
        "organizationAdmin": true,
        "team_id": 1,
        "organization_id": 1
      },
      {
        "id": 304,
        "firstName": "Arpit",
        "lastName": "Pathak",
        "email": "sanchit@gmail.com",
        "teamAdmin": false,
        "organizationAdmin": false,
        "team_id": 1,
        "organization_id": 1
      }
    ]
  },
  "statusCode": "OK",
  "statusCodeValue": 200
}`

-----
-----
-----

***1. Create an event***
---

* **URL**

  /lowesforgeeks/event

* **Method:**
  
  POST
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
   
   **Optional:**

* **Data Params**

{	
	"eventType":"Private",
	"name":"Event1",
	"location":"Lucknow",
	"startDateTime":"2020-05-30",
	"endDateTime":"2020-06-01"
	}	

* **Success Response:**

  * **Code:** 200 
    **Content:** `{
  "eventId": 2,
  "eventType": "Private",
  "name": "Event1",
  "createdBy": {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": true,
    "organizationAdmin": true,
    "team_id": 1,
    "organization_id": 1
  },
  "location": "Lucknow",
  "startDateTime": "2020-05-30",
  "endDateTime": "2020-06-01",
  "creatorTeamId": 1,
  "numberOfViews": 4,
  "numberOfWatchers": 0,
  "numberOfLikes": null,
  "participants": [],
  "numberOfParticipants": null,
  "recurring": false,
  "recurFrequency": null
}`


***2. View events***
---

* **URL**

  /lowesforgeeks/event

* **Method:**
  
  GET
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
   
   **Optional:**

* **Data Params**


* **Success Response:**

  * **Code:** 200 
    **Content:** `{
  "eventId": 2,
  "eventType": "Private",
  "name": "Event1",
  "createdBy": {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": true,
    "organizationAdmin": true,
    "team_id": 1,
    "organization_id": 1
  },
  "location": "Lucknow",
  "startDateTime": "2020-05-30",
  "endDateTime": "2020-06-01",
  "creatorTeamId": 1,
  "numberOfViews": 1,
  "numberOfWatchers": 0,
  "numberOfLikes": null,
  "participants": [],
  "numberOfParticipants": null,
  "recurring": false,
  "recurFrequency": null
}`



***3. View event by id***
---

* **URL**

  /lowesforgeeks/event/{eventId}

* **Method:**
  
  GET
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
   path variable `eventId=[integer]`
   
   **Optional:**

* **Data Params**


* **Success Response:**

  * **Code:** 200 
    **Content:** `{
  "eventId": 2,
  "eventType": "Private",
  "name": "Event1",
  "createdBy": {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": true,
    "organizationAdmin": true,
    "team_id": 1,
    "organization_id": 1
  },
  "location": "Lucknow",
  "startDateTime": "2020-05-30",
  "endDateTime": "2020-06-01",
  "creatorTeamId": 1,
  "numberOfViews": 2,
  "numberOfWatchers": 0,
  "numberOfLikes": null,
  "participants": [],
  "numberOfParticipants": null,
  "recurring": false,
  "recurFrequency": null
}`


***4. View events by name***
---

* **URL**

  /lowesforgeeks/event/search/{name}

* **Method:**
  
  GET
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
   path variable `eventId=[integer]
   
   **Optional:**

* **Data Params**


* **Success Response:**

  * **Code:** 200 
    **Content:** `{
  "eventId": 2,
  "eventType": "Private",
  "name": "Event1",
  "createdBy": {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": true,
    "organizationAdmin": true,
    "team_id": 1,
    "organization_id": 1
  },
  "location": "Lucknow",
  "startDateTime": "2020-05-30",
  "endDateTime": "2020-06-01",
  "creatorTeamId": 1,
  "numberOfViews": 3,
  "numberOfWatchers": 0,
  "numberOfLikes": null,
  "participants": [],
  "numberOfParticipants": null,
  "recurring": false,
  "recurFrequency": null
}`


***5. View event by id***
---

* **URL**

  /lowesforgeeks/event/{eventId}

* **Method:**
  
  GET
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
   path variable `eventId=[integer]`
   
   **Optional:**

* **Data Params**


* **Success Response:**

  * **Code:** 200 
    **Content:** `{
  "eventId": 2,
  "eventType": "Private",
  "name": "Event1",
  "createdBy": {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": true,
    "organizationAdmin": true,
    "team_id": 1,
    "organization_id": 1
  },
  "location": "Lucknow",
  "startDateTime": "2020-05-30",
  "endDateTime": "2020-06-01",
  "creatorTeamId": 1,
  "numberOfViews": 2,
  "numberOfWatchers": 0,
  "numberOfLikes": null,
  "participants": [],
  "numberOfParticipants": null,
  "recurring": false,
  "recurFrequency": null
}`


***6. View trending events***
---

* **URL**

  /lowesforgeeks/event/trending

* **Method:**
  
  GET
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
   
   **Optional:**

* **Data Params**


***2. View popular events***
---

* **URL**

  /lowesforgeeks/event/popular

* **Method:**
  
  GET
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
   
   **Optional:**

* **Data Params**



***7. View upcoming events***
---

* **URL**

  /lowesforgeeks/event/upcoming

* **Method:**
  
  GET
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
   
   **Optional:**

* **Data Params**




***8. Watch an event***
---

* **URL**

  /lowesforgeeks/event/watch/{eventId}

* **Method:**
  
  GET
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
   path variable `eventId=[integer]
   
   **Optional:**

* **Data Params**

* **Success Response:**

  * **Code:** 200 
    **Content:** `{
  "eventId": 2,
  "eventType": "Private",
  "name": "Event1",
  "createdBy": {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": true,
    "organizationAdmin": true,
    "team_id": 1,
    "organization_id": 1
  },
  "location": "Lucknow",
  "startDateTime": "2020-05-30",
  "endDateTime": "2020-06-01",
  "creatorTeamId": 1,
  "numberOfViews": 3,
  "numberOfWatchers": 1,
  "numberOfLikes": null,
  "participants": [],
  "numberOfParticipants": null,
  "recurring": false,
  "recurFrequency": null
}`


***9. Unwatch an event***
---

* **URL**

  /lowesforgeeks/event/unwatch/{eventId}

* **Method:**
  
  GET
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
   path variable `eventId=[integer]
   
   **Optional:**

* **Data Params**

* **Success Response:**

  * **Code:** 200 
    **Content:** `{
  "eventId": 2,
  "eventType": "Private",
  "name": "Event1",
  "createdBy": {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": true,
    "organizationAdmin": true,
    "team_id": 1,
    "organization_id": 1
  },
  "location": "Lucknow",
  "startDateTime": "2020-05-30",
  "endDateTime": "2020-06-01",
  "creatorTeamId": 1,
  "numberOfViews": 3,
  "numberOfWatchers": 0,
  "numberOfLikes": null,
  "participants": [],
  "numberOfParticipants": null,
  "recurring": false,
  "recurFrequency": null
}`

***10. Like an event***
---

* **URL**

  /lowesforgeeks/event/like/{eventId}

* **Method:**
  
  GET
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
   path variable `eventId=[integer]
   
   **Optional:**

* **Data Params**

* **Success Response:**

  * **Code:** 200 
    **Content:** `{
  "eventId": 2,
  "eventType": "Private",
  "name": "Event1",
  "createdBy": {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": true,
    "organizationAdmin": true,
    "team_id": 1,
    "organization_id": 1
  },
  "location": "Lucknow",
  "startDateTime": "2020-05-30",
  "endDateTime": "2020-06-01",
  "creatorTeamId": 1,
  "numberOfViews": 5,
  "numberOfWatchers": 0,
  "numberOfLikes": 1,
  "participants": [],
  "numberOfParticipants": null,
  "recurring": false,
  "recurFrequency": null
}`



***11. Unlike an event***
---

* **URL**

  /lowesforgeeks/event/unlike/{eventId}

* **Method:**
  
  GET
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
   path variable `eventId=[integer]
   
   **Optional:**

* **Data Params**

* **Success Response:**

  * **Code:** 200 
    **Content:** `{
  "eventId": 2,
  "eventType": "Private",
  "name": "Event1",
  "createdBy": {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": true,
    "organizationAdmin": true,
    "team_id": 1,
    "organization_id": 1
  },
  "location": "Lucknow",
  "startDateTime": "2020-05-30",
  "endDateTime": "2020-06-01",
  "creatorTeamId": 1,
  "numberOfViews": 5,
  "numberOfWatchers": 0,
  "numberOfLikes": 0,
  "participants": [],
  "numberOfParticipants": null,
  "recurring": false,
  "recurFrequency": null
}`


***12. Participate in an event***
---

* **URL**

  /lowesforgeeks/event/participate/{eventId}

* **Method:**
  
  PUT
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
   path variable `eventId=[integer]
   
   **Optional:**

* **Data Params**

* **Success Response:**

  * **Code:** 200 
    **Content:** `{
  "eventId": 2,
  "eventType": "Private",
  "name": "Event1",
  "createdBy": {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": true,
    "organizationAdmin": true,
    "team_id": 1,
    "organization_id": 1
  },
  "location": "Lucknow",
  "startDateTime": "2020-05-30",
  "endDateTime": "2020-06-01",
  "creatorTeamId": 1,
  "numberOfViews": 8,
  "numberOfWatchers": 0,
  "numberOfLikes": 1,
  "participants": [
    {
      "id": 1,
      "firstName": "Kshitij",
      "lastName": "Raj",
      "email": "kshtjraj05@gmail.com",
      "teamAdmin": true,
      "organizationAdmin": true,
      "team_id": 1,
      "organization_id": 1
    }
  ],
  "numberOfParticipants": 1,
  "recurring": false,
  "recurFrequency": null
}`

***13. Unparticipate in an event***
---

* **URL**

  /lowesforgeeks/event/uparticipate/{eventId}

* **Method:**
  
  PUT
  
*  **URL Params**

   **Required:**
    
   header `loggedInMemberId=[integer]`
   path variable `eventId=[integer]
   
   **Optional:**

* **Data Params**

* **Success Response:**

  * **Code:** 200 
    **Content:** `{
  "eventId": 2,
  "eventType": "Private",
  "name": "Event1",
  "createdBy": {
    "id": 1,
    "firstName": "Kshitij",
    "lastName": "Raj",
    "email": "kshtjraj05@gmail.com",
    "teamAdmin": true,
    "organizationAdmin": true,
    "team_id": 1,
    "organization_id": 1
  },
  "location": "Lucknow",
  "startDateTime": "2020-05-30",
  "endDateTime": "2020-06-01",
  "creatorTeamId": 1,
  "numberOfViews": 8,
  "numberOfWatchers": 0,
  "numberOfLikes": 1,
  "participants": [],
  "numberOfParticipants": null,
  "recurring": false,
  "recurFrequency": null
}`
