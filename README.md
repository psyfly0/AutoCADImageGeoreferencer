# AutoCADImageGeoreferencer
This is a very simple software, both in design and function. It reads a folder containing images, 
specifically in the .jpg format (as this is the most common extension for mobile cameras).
The software extracts GPS information from these images, converts it into Hungarian EOV coordinates, 
and generates a script (.scr) file. This script file can be opened in AutoCAD, where it attaches the images with the correct EOV coordinates.
Since it creates a script file, it invokes a specific command in AutoCAD, the "-image" command, to execute these processes.

Usage:
Execute the program
Select a folder containing images.
The program generates a file inside the image folder: imageConvert.scr.
In AutoCAD, the .scr file can be opened using the _script command.
The command reads the script and performs its operations, resulting in a drawing with the images correctly attached to the correct coordinates.*

*The coordinates represent the location where the picture was taken and refer to the bottom-left corner of the picture.
