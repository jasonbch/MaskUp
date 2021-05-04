Importing the Project
In IntelliJ IDEA, you can choose to open the build.gradle file and select “Open as Project” to get started. 

In Eclipse, choose File -> Import... -> Gradle -> Existing Gradle Project (make sure that your freshly generated project is not located inside of your workspace). 

You may need to refresh the Gradle project after the initial import, if some dependencies weren’t downloaded yet. In IntelliJ IDEA, the Reimport all Gradle projects button is a pair of circling arrows in the Gradle tool window, which can be opened with View -> Tool Windows -> Gradle. 

In Eclipse right click on your project Gradle -> Refresh Gradle Project.

Desktop
In IDEA:
Extend the Gradle tab on the right sight of your window:

Expand the tasks of your project and then select: desktop -> other -> run.

Alternatively, you can create a run configuration:
Right click your DesktopLauncher class
Select ‘Run DesktopLauncher.main()’. 
If this fails with missing assets, we need to hook up the assets foldel.
Open up Run Configurations

Edit the Run Configuration that was just created by running the desktop project and set the working directory to point to your core/assets folder.

Run your application using the run button


In Eclipse:
Right click your desktop project -> Run as -> Run Configurations…
On the right side, select Java Application:

At the top left, click the icon to create a new run configuration: 
As Main class select your DesktopLauncher class
After that, click on the Arguments tab
At the bottom, under ‘Working directory’ select ‘Other’ -> Workspace… 
Then select your asset folder located in core/assets.