
![Logo](https://github.com/CS211-651/project211-hardcodeexecutable/blob/featureUser/docs/image/Group%202.png)
# Project Student Complaints 
This project is part of 01418211 Software Construction course.

![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Bootstrap](https://img.shields.io/badge/bootstrap-%23563D7C.svg?style=for-the-badge&logo=bootstrap&logoColor=white)
![Stack Overflow](https://img.shields.io/badge/-Stackoverflow-FE7A16?style=for-the-badge&logo=stack-overflow&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Shell Script](https://img.shields.io/badge/shell_script-%23121011.svg?style=for-the-badge&logo=gnu-bash&logoColor=white)
![Adobe](https://img.shields.io/badge/adobe-%23FF0000.svg?style=for-the-badge&logo=adobe&logoColor=white)
![PowerShell](https://img.shields.io/badge/PowerShell-%235391FE.svg?style=for-the-badge&logo=powershell&logoColor=white)
![Discord](https://img.shields.io/badge/Discord-%237289DA.svg?style=for-the-badge&logo=discord&logoColor=white)
![Windows](https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white)
![macOS](https://img.shields.io/badge/mac%20os-000000?style=for-the-badge&logo=macos&logoColor=F0F0F0)
![Linux](https://img.shields.io/badge/Linux-FCC624?style=for-the-badge&logo=linux&logoColor=black)
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
- [Atomic design](https://atomicdesign.bradfrost.com/chapter-2/)
- [Basic CSS](https://www.w3schools.com/css/)
- [Maven](https://maven.apache.org/)
- [Git](https://git-scm.com/docs)
## Roadmap

* Week 1 - WireFrame,Prototype,Research Time
    * WireFrame  [(Click)](https://www.figma.com/file/FBnZ0LUVRLlzaK6GxCX3n9/ProjectHardCodeExcutable?node-id=50%3A234) - [6410450753 Jirapat Surungsi](https://github.com/j1kid1412)
    * Prototype [(Click)](https://www.figma.com/file/FBnZ0LUVRLlzaK6GxCX3n9/ProjectHardCodeExcutable?node-id=50%3A234) - [6410450974 Thanadon Kritveeranant](https://github.com/oOTEEOo)
    * Research [6410450257 Ratchaphon Hinsui](https://github.com/Ratchaphon1412) , [Witthawat Praphanwong3](https://github.com/Poomffi)
* Week 2 - UML Class Diagram
  * UML diagram [(Click)]()
* Week 3 - Feature Admin
* Week 4 - Feature User,Feature Staff


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

#### macOS dmg
- download Student Complaints.dmg from Release or click the link [download](https://github.com/Ratchaphon1412/Student-Complaints/releases/download/v1.0.0-lastest/Student.Complaints.Installer.dmg)
  * open dmg and drag Student Complaints to Application folder
  * allow open application in System Preferences --> Security & Privacy --> click open anyway
  * click icon Student Complaints in launchpad
  * launch the Application
#### windows OS exe
- download Student Complaints.dmg from Release or click the link [download]() 
  * open Student Complaints.exe and click next 
  * waiting to done
  * see icon Student Complaint in desktop or search in windows "Student Complaint"
  * launch the Application
#### jar file
- download Student Complaints.jar from Release or click the link [download]()
  * extract file zip and open the folder name "Student Complaints"
  * see Student Complaints.jar and click it.
  * launch the Application
#### sh file
- download Student Complaints.sh from Release or click the link [download]()
  * click Student Complaints.sh 
  * launch the Application

## Usage/Examples

This is User Manual [Guide](https://github.com/CS211-651/project211-hardcodeexecutable/blob/main/docs/pdf/fileProjectJavaPDF.pdf)


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
<a href="https://discord.gg/Ry3ek3YX7K" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/discord.svg" alt="https://discord.gg/qDNMWSnW" height="30" width="40" /></a>
</p>