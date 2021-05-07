********

## **Topjava Graduation Project**

Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

---
### **Task:**

Build a voting system for deciding where to have lunch.

- 2 types of users: admin and regular users
- Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
- Menu changes each day (admins do the updates)
- Users can vote on which restaurant they want to have lunch at
- Only one vote counted per user
- If user votes again the same day:
  If it is before 11:00 we assume that he changed his mind.
  If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides a new menu each day.

---

#### **cURL commands for testing REST API:**


- Get all restaurants  ==> `curl localhost:8080/restaurants`

- Create a new restaurant ==> `curl -X POST -d '{"name":"New Restaurant","address":"Mohovaya str, 7"}' localhost:8080/admin/restaurants -H 'Content-type:application/json'`

- Get the restaurant with id=100005 ==> `curl localhost:8080/restaurants/100005`

- Delete the restaurant with id=100005 ==> `curl -s -X DELETE http://localhost:8080/restaurants/100005`

- Get all users ==> `curl localhost:8080/admin/users`

- Get user by email ==> `curl localhost:8080/admin/users/by?email=user1@yandex.ru -u admin:password`

- Update user ==> `curl -X PUT  -d '{"name":"newUserName","email":"newemail@yandex.com","password":"newPassword","roles":["USER"]}' localhost:8080/admin/users/100001 -H 'Content-type:application/json'`

- Vote for the restaurant with id=100005 ==> `curl -s -X PUT localhost:8080/restaurants/100005/vote --user user1:password`