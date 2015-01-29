# Team Foxtrot

```
		              |     |
		              |  _  |
		______________|_( )_|______________
		   o   +|+   [ ( o ) ]   +|+    o
		            *[_]---[_]*
```

## Introduction
**Programming workflow:**
+ Git clone into Eclipse workspace `<project name>/`
+ The project should not exist and should be empty
+ The `Eclipse > File > New Java Project`
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

1. The functionality of the program should be accessed by a GUI.

2. A text file giving the details of the referees should be read in automatically at the start of
the program, and the details stored into an array. 

3. All the referees should be displayed in ID order, one line per referee, showing all the
details and using the same format as the input text file, with the information aligned in
columns. 

4. On request, all details of a particular referee can be displayed individually. 
	- The referee is identified by the user supplying their first and last names. 
	- If a referee with these names cannot be found in the list, a suitable message should
be displayed. 
	- The referee details should be searched for using text fields / combo boxes etc. as
appropriate. 
	- You should use a button to initiate the search. 

5. On request, any of the referee details except the `ID`, first name and last name and the
number of match allocations can be updated. 

6. On request, a referee can be deleted from the array. 

7. On request, a new referee can be added to the array. 

8. On request, the program should display a bar chart showing the number of allocations for
each referee above each bar. The referees should be identified in the chart by their `ID`,
and the bars should be displayed in `ID` order. 

9. On request, allocate two referees to a match. 

10. By the end of the program, a text file should be produced giving details of allocations to
each match. The text file should be called `MatchAllocs.txt`. 

11. There should be an ‘exit’ button. When this button is clicked, the updated referee list is
written to an output text file, in exactly the same format as the input text file (and ordered
on referee `ID`). The text file should be called `RefereesOut.txt`. The list of allocations, `MatchAllocs.txt`, in order of allocation, is also produced (or closed).
Then the program exits.

12. Create a jar file and a batch file so that by placing a shortcut icon on the desk top the
program can be run by double clicking the icon.
The jar file should be called `TeamProj.jar`. (Eclipse can be used to create the jar file –
see Part II, Lecture 18.) The batch file should be called `TeamProj.bat`. 

#### Don't use absolute paths
After submission your program will be tested, so it is particularly important that the filehandling
within your program does not refer to the absolute location of your text files. 


#### Usability NOT Design
Fine details of the GUI (colour scheme, font size etc.) are not important, however there
are marks for GUI design which primarily relate to “usability” issues, and how they
impact on functionality. For example, a text field with no label beside it may not, strictly
speaking, in itself cause the program to output an incorrect result, but it makes it very
difficult for a user to know exactly what should be entered in that text field! 

### Team Submission
Your submission should contain the following parts:
- The report
- The code .pdf
- The individual Java files.

They should all be put in a `.zip` file called TeamF (if you are team F).
###Coding Standards
