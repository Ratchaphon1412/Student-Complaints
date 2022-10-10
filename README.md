
![Logo](https://github.com/CS211-651/project211-hardcodeexecutable/blob/featureUser/docs/image/Group%202.png)
# Project Student Complaints 
This project is part of 01418211 Software Construction course.

<h2 align="left">Languages and Tools:</h2>
<p align="left"> 
<a href="https://git-scm.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/git-scm/git-scm-icon.svg" alt="git" width="40" height="40"/> </a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="https://www.w3schools.com/css/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/css3/css3-original-wordmark.svg" alt="css3" width="40" height="40"/> </a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="https://getbootstrap.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/bootstrap/bootstrap-plain-wordmark.svg" alt="bootstrap" width="40" height="40"/> </a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="https://www.figma.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/figma/figma-icon.svg" alt="figma" width="40" height="40"/> </a> 
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="https://www.docker.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/docker/docker-original-wordmark.svg" alt="docker" width="40" height="40"/> </a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="https://www.gnu.org/software/bash/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/gnu_bash/gnu_bash-icon.svg" alt="bash" width="40" height="40"/> </a>
</p>

## Acknowledgements

- [Java](https://www.w3schools.com/java/java_intro.asp)
- [OOP](https://www.w3schools.com/java/java_oop.asp#:~:text=Java%20%2D%20What%20is%20OOP%3F,contain%20both%20data%20and%20methods.)
- [JavaFX Framework](https://openjfx.io/)
- [MVC Architecture](https://www.javatpoint.com/mvc-architecture-in-java)
- [Unit test with JUnit](https://www.vogella.com/tutorials/JUnit/article.html)
- [Basic CSS](https://www.w3schools.com/css/)
- [Maven](https://maven.apache.org/)
- [Git](https://git-scm.com/docs)
## Roadmap

- Week 1 - WireFrame,Prototype,Research Time

- Week 2 - UML Class Diagram

- Week 3 - Feature Admin

- Week 4 - Feature User,Feature Staff


## Architecture project
    .
    ├── database (store csv)
        ├── account.csv 
        ├── likepost.csv
        ├── log.csv
        ├── pattern.csv
        ├── report.csv
        ├── requestban.csv
        ├── requestunban.csv
        ├── staffAgencyList.csv
    ├── docs 
        ├──  image
    ├── image (program will save image here)
        ├── accounts
        ├── reports
    ├── src
        ├── main
            ├── java
                ├── animatefx 
                ├── com.github.saacsos
                ├── ku.cs
                    ├── controller (store all controller)
                        ├── admin 
                        ├── components 
                        ├── login  
                        ├── staff 
                        ├── user 
                    ├── models (store all models)
                        ├── admin
                        ├── user
                        ├── staff
                        ├── report
                    ├── service (store all service)
                    Application.java (set up Application)
                    ApplicationController.java (router application)
                    Main.java (run main program)
                    Preloader.java (splash screen loading with Preloader stage)
                    State.java (state store temporary data when application runing)
                module-info.java
            ├── resources
                ├── ku.cs
                    ├── assets (store font and image)
                        ├── fonts
                        ├── images
                    ├── components (store fxml components)
                        ├── admin
                        ├── staff
                        ├── user
                    ├── style (store css file)
                    ├── views (store all fxml)
                        ├── admin
                        ├── staff
                        ├── user
                    AnimateFX-1.2.3.jar
                    FXRouter.jar
    config.properties (save theme and font)
## Documentation

Coming soon


## Features
#### Login
- Create User Account
- Login
- Change PassWord
#### Admin
- Light/dark mode toggle
- Change Fonts
- Ban/Unban User
- Remove/Ignore Post
- Create Agency
- Create Staff Account
- Change Agency
- Show Log user Login
#### User
- Light/dark mode toggle
- Change Fonts
- Change PassWord
- View All Post From Another User
- Like Post
- View Detial Post from select
- Sort Post from newPost , OldPost, Most Like, Least Like Or Category and Vote
- Create Post
- See your Post
#### Staff
- Light/dark mode toggle
- Change Fonts
- Change PassWord
- View specific complaints of responsibility
- handle complaints

## Third-party libraries

### FXrouter
[FXrouter](https://github.com/saacsos/fxrouter)

<p>&nbsp;<img align="center" src="https://github-readme-stats.vercel.app/api?username=saacsos&show_icons=true&locale=en" alt="saacsos" /></p>

### AnimateFX
[AnimateFX](https://github.com/Typhon0/AnimateFX)

<p>&nbsp;<img align="center" src="https://github-readme-stats.vercel.app/api?username=Typhon0&show_icons=true&locale=en" alt="Typhon0" /></p>

### Jackson Dataformats Text
[Jackson Dataformats Text](https://github.com/FasterXML/jackson-dataformats-text/tree/master/csv)

## Installation Program

#### use terminal
- important don't forget directory  database and image 
```bash
  git clone https://github.com/CS211-651/project211-hardcodeexecutable.git
  cd project211-hardcodeexecutable
  java -jar CS211-Project-1.0-SNAPSHOT-shaded.jar
```
#### download from snapshot
- see it in release

## Usage/Examples

```javascript
import Component from 'my-project'

function App() {
  return <Component />
}
```


## Account Tests

To run tests, run the following command


|account| password | role  |
|-------|----------|-------|
|Ratchaphon1412@gmail.com| Nueng111 | admin |
|poomffit@gmail.com| 123456   | admin |
|sevenknight5570@gmail.com| Nueng111 | user  |
|teeteetee251@gmail.com| 123      | user  |
|Ratchaphon.hi@ku.th| Nueng111 | staff |
|Ratchanon1412@gmail.com|Nueng1412| staff |



## Authors

- [@Ratchaphon1412 6410450257 Ratchaphon Hinsui](https://github.com/Ratchaphon1412)
<p><img align="left" src="https://github-readme-stats.vercel.app/api/top-langs?username=ratchaphon1412&show_icons=true&locale=en&layout=compact" alt="ratchaphon1412" /><br><br><br><br><br><br><br><br></p>

- [@Poomffi 6410450265 Witthawat Praphanwong](https://github.com/Poomffi)
<p><img align="left" src="https://github-readme-stats.vercel.app/api/top-langs?username=Poomffi&show_icons=true&locale=en&layout=compact" alt="Poomffi" /><br><br><br><br><br></p>

- [@J1kid1412 6410450753 Jirapat Surungsi](https://github.com/j1kid1412)
<p><img align="left" src="https://github-readme-stats.vercel.app/api/top-langs?username=j1kid1412&show_icons=true&locale=en&layout=compact" alt="j1kid1412" /><br><br><br><br></p>

- [@oOTEEOo 6410450974 Thanadon Kritveeranant](https://github.com/oOTEEOo)
<p><img align="left" src="https://github-readme-stats.vercel.app/api/top-langs?username=j1kid1412&show_icons=true&locale=en&layout=compact" alt="j1kid1412" /><br><br><br><br></p>

## Screenshots

![App Screenshot](https://github.com/CS211-651/project211-hardcodeexecutable/blob/featureUser/docs/image/50-11-22-005023.png)

## Connect with me:
<p align="left">
<a href="https://discord.gg/https://discord.gg/qDNMWSnW" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/discord.svg" alt="https://discord.gg/qDNMWSnW" height="30" width="40" /></a>
</p>