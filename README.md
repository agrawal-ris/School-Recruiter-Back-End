# School-Recruiter-Back-End

# Website URL 

[http://school-recruiter.herokuapp.com/](http://school-recruiter.herokuapp.com/)

# Front End Code

[Front End Repository](https://github.com/agrawal-ris/School-Recruiter-Front-End)



# Home Page

1. Is mapped to root context (&quot;/&quot;) and (&quot;/home&quot;).
2. Is the first page when visiting the website
3. It displays generic content for anonymous users . The content is dynamic based on the latest data. For instance, **Anonymous users** can see all schools
4. Must display specific content for the logged in user. The content is dynamic based on the most recent data entered by the logged in user. For instance, **recruiter** can see – the schools they  posted and his recent followers and **students** can see – schools they like and recruiters they follow.

# Profile Page

1. Does allow users to change their personal information. If a user is logged in then they can see their profile including sensitive information such as email and phone
2. Is accessible to other users including anonymous users
3. Does hide personal/private information from others visiting the profile. If a user is visiting someone else&#39;s profile, then they can&#39;t see that other user&#39;s sensitive information
4. Is mapped to &quot;/profile&quot; for displaying the profile of the currently logged in user
5. Is mapped to &quot;/profile/{profileId}&quot; for displaying someone elses profile
6. Display lists of snippets and links of all data related to a user. For instance, users can go to the profile page of another user through the followers. Recruiters can see a student&#39;s profile and details through his home page where his list of followers are added.

# Search/Search Results Page

1. Search Schools using search a API from :
2. It a summarized list of schools matching the search criteria. Results come from the remote API, not the local database
3. Each school can be clicked as its a link to navigate to the details page (see below)
4. It is mapped to /search when no search has been executed and no results exist
5. It is mapped to /search/{search criteria} when a search has been executed and according results shown
6. It augment the results with related data in your local database

# Details Page

1. Does retrieve details from the remote API based on SchoolID as a parameter from the search/results page
2. Does display additional related data from the local database.
3.  Does provide links to related data/users. For instance, can view the school on their website
4. Must be mapped to /details/{unique identifier} or /details?identifier={unique identifier} where unique identifier uniquely identies the item being displayed

#  Login Page

The login and register page allow users to register with the web site and then login later on

1. Allow login in and identifying themselves
2. Disallow access to view ALL Recruiters Web page unless logged in
3. Allow access to all other Web pages even when not logged in
4. Adapt content based on whether user is logged in or not for at least the Home page and Profile page
5. Does force login only when identity is required. For instance, an anonymous user might like the school so the application requests the user to login
6. Is mapped to /login

# Register Page

1. Allows users to register and create a new account
2. Allow choosing a role(s) for a user. For instance, recruiter/student.
3. Allow login in and identifying themselves
4. Disallow access to view ALL Recruiters Web page unless logged in
5. Allow access to all other Web pages even when not logged in
6. Adapt content based on whether user is logged in or not for at least the Home page and Profile page
7. Does force login only when identity is required. For instance, an anonymous user might like the school so the application requests the user to login
8. Is mapped to /register
9. Validates all field with their correct data type.
10.  Does not allow same username to register again
