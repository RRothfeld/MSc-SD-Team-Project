# Team Foxtrot

```
              |     |
              |  _  |
______________|_( )_|______________
   o   +|+   [ ( o ) ]   +|+
            *[_]---[_]*
```

## Introduction
**Programming workflow:**
+ Git clone into Eclipse workspace `<project name>/`
+ The project should not exist and should be empty
+ The Eclipse > File > New Java Project
+ Name it the same way as you named the folder

**In the terminal you do:**
```bash
	#template
	git clone <remote> <project folder> 

	#could look like this
	git clone https://github.com/mickeypash/team-f-project.git AE4/
```
**In Eclipse you do:**
	`Eclipse > File > New Java Project > Folder: <project folder>`



**Basic git workflow**
+ You make changes to `foo.java`
+ You do `git status` to see if the changes are registered
+ You could do `git diff` to check what differes from the original file
+ You do `git add` to add your changes to your local repo
+ You do `git commit -m "info about what you changed/fixed"` to commit things (if they are ready)
+ Finally `git push` to push your changes to the shared project on github

**Example in action**
```bash
git add README.md
git commit -m "hello world"
git push
# username and password
# todo: set-up SSH authentication
```

## Team Project Specification Brief

###Description
###Functionality
###Coding Standards