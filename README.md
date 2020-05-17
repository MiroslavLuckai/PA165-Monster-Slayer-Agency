# PA165 - Monster Slayer Agency

Monster Slayer Agency is a web application focused on monsters. Are you a mighty hero looking for some easy coins? Poor peasant who is not capable of harvesting his plants? Or just nerd who is interested in learning fact or two about monsters? If your answer to any of this questions is yes, our web application is for you. Create your own account and discover all the options our application have yourself.

Monster Slayer Agency will allow its users to create new job offers or take the existing one. If you have problem with slaying monsters full list of well know beasts with all the details(weaknesses, strengths, resistance, ...) is available. Have you discovered a new beast? Feel free to add it into our database and earn some coins. Do you have enough of paying gold for things you can do yourself? Update your profile and try monster hunting adventure yourself.

Install guide:
1. Download https://nodejs.org/en/
2. Clone project
3. Use `mvn clean install` inside main directory, then `mvn cargo:run` in msa-rest directory
4. Add https://chrome.google.com/webstore/detail/redux-devtools/lmhkpmbekcpmknklioeibfkpmmfibljd (Chrome) or https://addons.mozilla.org/en-US/firefox/addon/reduxdevtools/ (Firefox)
5. Open another terminal and use `npm install` in msa-frontend directory, then start the application with `npm start`
6. Application is then available on http://localhost:3000
7. At http://localhost:8080/pa165/rest/users you can find all pre-created users, and use their email, with the password: Password1 to log in
